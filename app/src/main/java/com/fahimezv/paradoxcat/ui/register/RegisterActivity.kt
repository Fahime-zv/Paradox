package com.fahimezv.paradoxcat.ui.register

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fahimezv.customebuttonlib.CustomizableGenericButton
import com.fahimezv.paradoxcat.R
import com.fahimezv.paradoxcat.data.model.Profile
import com.fahimezv.paradoxcat.data.sharedpreferences.SharedPref
import com.fahimezv.paradoxcat.data.sharedpreferences.KEY_HAS_REGISTER
import com.fahimezv.paradoxcat.ui.login.LoginActivity
import com.fahimezv.paradoxcat.ui.welcome.WelcomeActivity
import com.fahimezv.paradoxcat.util.showMessage

class RegisterActivity : AppCompatActivity() {
    companion object {
        const val KEY_PUT_FULL_NAME = "fullName"
    }

    //Ui
    private lateinit var mLoginViewModel: RegisterViewModel
    private lateinit var mFullNameEdittext: EditText
    private lateinit var mUserNameEdittext: EditText
    private lateinit var mPasswordEdittext: EditText
    private lateinit var mLoginButton: CustomizableGenericButton
    private lateinit var mLoadingProgressBar: ProgressBar
    private lateinit var mSharedPreferences: SharedPreferences


    private lateinit var mSharedPref: SharedPref


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mSharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)

        mSharedPref = SharedPref(mSharedPreferences)

        mLoginViewModel = ViewModelProvider(this, RegisterViewModelFactory(mSharedPref)).get(RegisterViewModel::class.java)

        //check if already register
        if (mSharedPref.read(KEY_HAS_REGISTER, false) == true) {
            startActivity(Intent(this, LoginActivity::class.java))
            this.finish()
        }

        mFullNameEdittext = findViewById(R.id.fullName_editText)
        mUserNameEdittext = findViewById(R.id.username_editText)
        mPasswordEdittext = findViewById(R.id.password_editText)
        mLoginButton = findViewById(R.id.register_Button)
        mLoadingProgressBar = findViewById(R.id.loading_progressBar)


        //Observer
        mLoginViewModel.getRegisterLiveData.observe(this@RegisterActivity, Observer {
            val registerState = it ?: return@Observer

            mLoginButton.isEnabled = registerState.done

            if (registerState.fullNameError != null) {
                mLoadingProgressBar.visibility = View.INVISIBLE
                mFullNameEdittext.error = getString(registerState.fullNameError)
            }
            if (registerState.usernameError != null) {
                mLoadingProgressBar.visibility = View.INVISIBLE
                mUserNameEdittext.error = getString(registerState.usernameError)
            }
            if (registerState.passwordError != null) {
                mLoadingProgressBar.visibility = View.INVISIBLE
                mPasswordEdittext.error = getString(registerState.passwordError)
            }
        })

        mLoginViewModel.getRegisterResultLiveData.observe(this@RegisterActivity, Observer {
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
            mLoginButton.isEnabled = false

            mLoginViewModel.validateRegister(
                mFullNameEdittext.text.toString(),
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


}

