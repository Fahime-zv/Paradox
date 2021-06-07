package com.fahimezv.paradoxcat.ui.welcome

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.fahimezv.customebuttonlib.CustomizableGenericButton
import com.fahimezv.paradoxcat.MyApp
import com.fahimezv.paradoxcat.R
import com.fahimezv.paradoxcat.data.manager.SessionManager
import com.fahimezv.paradoxcat.data.manager.StateExpireListener
import com.fahimezv.paradoxcat.ui.login.LoginActivity
import com.fahimezv.paradoxcat.ui.register.RegisterActivity.Companion.KEY_PUT_FULL_NAME
import com.fahimezv.paradoxcat.util.showMessage

class WelcomeActivity : AppCompatActivity() {

    //UI
    private lateinit var mFullNameTextView: TextView
    private lateinit var mLogoutButton: CustomizableGenericButton
    private lateinit var mTest1Button: CustomizableGenericButton
    private lateinit var mTest2Button: CustomizableGenericButton

    private lateinit var mSessionManager :SessionManager


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        //Setup
        mFullNameTextView = findViewById(R.id.fullName_textView)
        mLogoutButton = findViewById(R.id.logout_button)
        mTest1Button = findViewById(R.id.test1_button)
        mTest2Button = findViewById(R.id.text2_button)

        //for check  state app
        mSessionManager= SessionManager((application as MyApp).lifeCycleManager)

        val fullName = intent.extras?.getString(KEY_PUT_FULL_NAME) ?: "Not Value"

        mFullNameTextView.text = "${getString(R.string.welcome)}< $fullName >"

        mLogoutButton.setOnClickListener {
            onLogOut()
        }
        mSessionManager.mStateExpireListener = object : StateExpireListener {

            override fun onExpire() {

                showMessage(R.string.session_expired)
                onLogOut()

            }
        }

        mTest1Button.setOnClickListener {
            showMessage(R.string.test1_message)
        }
        mTest2Button.setOnClickListener {
            showMessage(R.string.test2_message)
        }
    }

    fun onLogOut() {
        // when early signOut from screen handler stopped
        mSessionManager.mHandler.removeCallbacksAndMessages(null)

        startActivity(Intent(this, LoginActivity::class.java))
        finish()

    }


}
