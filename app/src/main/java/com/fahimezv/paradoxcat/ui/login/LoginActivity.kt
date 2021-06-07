package com.fahimezv.paradoxcat.ui.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fahimezv.customebuttonlib.CustomizableGenericButton
import com.fahimezv.paradoxcat.R
import com.fahimezv.paradoxcat.data.model.Profile
import com.fahimezv.paradoxcat.data.sharedpreferences.SharedPref
import com.fahimezv.paradoxcat.ui.register.RegisterActivity.Companion.KEY_PUT_FULL_NAME
import com.fahimezv.paradoxcat.ui.welcome.WelcomeActivity

class LoginActivity : AppCompatActivity() {

    //UI
    private lateinit var mUserNameEdittext: EditText
    private lateinit var mPasswordEdittext: EditText
    private lateinit var mLoginButton: CustomizableGenericButton
    private lateinit var mLoadingProgressBar: ProgressBar

    private lateinit var mSharedPref: SharedPref
    private lateinit var mLoginViewModel: LoginViewModel
    private lateinit var mSharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Setup
        mSharedPreferences= getSharedPreferences(packageName, Context.MODE_PRIVATE)

        mSharedPref = SharedPref(mSharedPreferences)
        mLoginViewModel = ViewModelProvider(this, LoginViewModelFactory(mSharedPref)).get(LoginViewModel::class.java)

        mUserNameEdittext = findViewById(R.id.username_editText)
        mPasswordEdittext = findViewById(R.id.password_editText)
        mLoginButton = findViewById(R.id.login_Button)
        mLoadingProgressBar = findViewById(R.id.loading_progressBar)

        //Observer
        mLoginViewModel.getLoginLiveData.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            mLoginButton.isEnabled = loginState.done

            if (loginState.usernameError != null) {
                mLoadingProgressBar.visibility = View.INVISIBLE
                mUserNameEdittext.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                mLoadingProgressBar.visibility = View.INVISIBLE
                mPasswordEdittext.error = getString(loginState.passwordError)
            }
        })

        mLoginViewModel.getRegisterResultLiveData.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            mLoadingProgressBar.visibility = View.GONE
            if (loginResult.error != null) {
                showMessage(loginResult.error)
            }
            if (loginResult.success != null) {
                updateStateToWelcomeActivity(loginResult.success)
            }
        })


        mLoginButton.setOnClickListener {
            mLoadingProgressBar.visibility = View.VISIBLE
            mLoginButton.isEnabled=false

            mLoginViewModel.validateLogin(
                mUserNameEdittext.text.toString(),
                mPasswordEdittext.text.toString()
            )
        }
    }

    private fun updateStateToWelcomeActivity(model: Profile) {
        val intent = Intent(this, WelcomeActivity::class.java)
        intent.putExtra(KEY_PUT_FULL_NAME, model.fullName)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        this.finish()
    }


    private fun showMessage(@StringRes messageString: Int) {
        Toast.makeText(applicationContext, messageString, Toast.LENGTH_SHORT).show()
    }
}

