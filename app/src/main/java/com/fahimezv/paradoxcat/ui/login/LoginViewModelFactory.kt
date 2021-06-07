package com.fahimezv.paradoxcat.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fahimezv.paradoxcat.data.LoginRepository
import com.fahimezv.paradoxcat.data.sharedpreferences.SettingRepositoryImpl
import com.fahimezv.paradoxcat.data.sharedpreferences.SharedPref
import com.fahimezv.paradoxcat.util.SecureUtils

class LoginViewModelFactory(private val sharedPreferences: SharedPref) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(LoginRepository(SettingRepositoryImpl(sharedPreferences),SecureUtils())) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}