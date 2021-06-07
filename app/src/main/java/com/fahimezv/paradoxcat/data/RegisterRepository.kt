package com.fahimezv.paradoxcat.data

import com.fahimezv.paradoxcat.data.model.Login
import com.fahimezv.paradoxcat.data.sharedpreferences.SettingRepository
import com.fahimezv.paradoxcat.util.SecureUtils
import java.io.IOException



class RegisterRepository( private val settingRepository: SettingRepository,private val secureUtils: SecureUtils) {

    fun register(fullName:String, username: String, password: String): Result<Login> {
        try {
            settingRepository.saveFullName(fullName)
            settingRepository.saveUserName(username)
            settingRepository.savePassword(secureUtils.encryptIt(password))
            settingRepository.saveHasRegister(true)
            val dataUser = Login( fullName=fullName,userName = username)
            return Result.Success(dataUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }

    }

}