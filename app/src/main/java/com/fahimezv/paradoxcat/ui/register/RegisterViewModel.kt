package com.fahimezv.paradoxcat.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fahimezv.paradoxcat.data.Result

import com.fahimezv.paradoxcat.R
import com.fahimezv.paradoxcat.data.RegisterRepository
import com.fahimezv.paradoxcat.data.model.Profile
import com.fahimezv.paradoxcat.util.ValidatorUtils

class RegisterViewModel(private val registerRepository: RegisterRepository) : ViewModel() {

    private val mValidatorUtils: ValidatorUtils = ValidatorUtils()

    private val registerMutable = MutableLiveData<ValidationFieldState>()
    val getRegisterLiveData: LiveData<ValidationFieldState> = registerMutable

    private val registerResultMutable = MutableLiveData<RegisterResult>()
    val getRegisterResultLiveData: LiveData<RegisterResult> = registerResultMutable

    fun validateRegister(fullName: String, username: String, password: String): Boolean {
        return when {
            mValidatorUtils.validateFullName(fullName).not() -> {
                registerMutable.value = ValidationFieldState(fullNameError = R.string.invalid_full_name)
                false
            }
            mValidatorUtils.validateEmail(username).not() -> {
                registerMutable.value = ValidationFieldState(usernameError = R.string.invalid_username)
                false
            }
            mValidatorUtils.validatePassword(password).not() -> {
                registerMutable.value = ValidationFieldState(passwordError = R.string.invalid_password)
                false
            }
            else -> {
                requestLogin(fullName, username, password)
                true
            }
        }
    }

    private fun requestLogin(fullName: String, username: String, password: String) {
        val result = registerRepository.register(fullName, username, password)
        when (result) {
            is Result.Success ->
                registerResultMutable.value =
                    RegisterResult(success = Profile( result.data.fullName))

            is Result.Error ->
                registerResultMutable.value = RegisterResult(error = R.string.register_failed)
        }
    }

}