package com.fahimezv.paradoxcat.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fahimezv.paradoxcat.data.RegisterRepository
import com.fahimezv.paradoxcat.data.sharedpreferences.SettingRepositoryImpl
import com.fahimezv.paradoxcat.data.sharedpreferences.SharedPref
import com.fahimezv.paradoxcat.util.SecureUtils

@Suppress("UNCHECKED_CAST")
class RegisterViewModelFactory(private val sharedPreferences: SharedPref) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(
                registerRepository = RegisterRepository(
                    SettingRepositoryImpl(sharedPreferences), SecureUtils()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}