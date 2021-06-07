package com.fahimezv.paradoxcat.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fahimezv.paradoxcat.data.LoginRepository
import com.fahimezv.paradoxcat.data.Result

import com.fahimezv.paradoxcat.R
import com.fahimezv.paradoxcat.data.model.Profile
import com.fahimezv.paradoxcat.ui.register.RegisterResult
import com.fahimezv.paradoxcat.ui.register.ValidationFieldState
import com.fahimezv.paradoxcat.util.ValidatorUtils

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val mValidatorUtils: ValidatorUtils = ValidatorUtils()

    private val loginMutable = MutableLiveData<ValidationFieldState>()
    val getLoginLiveData: LiveData<ValidationFieldState> = loginMutable

    private val loginResultMutable = MutableLiveData<RegisterResult>()
    val getRegisterResultLiveData: LiveData<RegisterResult> = loginResultMutable


    fun validateLogin( username: String, password: String): Boolean {
        return when {

            mValidatorUtils.validateEmail(username).not() -> {
                loginMutable.value = ValidationFieldState(usernameError = R.string.invalid_username)
                false
            }
            mValidatorUtils.validatePassword(password).not() -> {
                loginMutable.value = ValidationFieldState(passwordError = R.string.invalid_password)
                false
            }
            else -> {
                requestLogin( username, password)
                true
            }
        }
    }

    private fun requestLogin( username: String, password: String) {
        val result = loginRepository.login( username, password)
        when (result) {
            is Result.Success ->
                loginResultMutable.value = RegisterResult(success = Profile( result.data.fullName))

            is Result.Error ->
                loginResultMutable.value = RegisterResult(error = R.string.login_failed)
        }
    }


}