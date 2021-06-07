package com.fahimezv.paradoxcat.data

import com.fahimezv.paradoxcat.data.model.Login
import com.fahimezv.paradoxcat.data.sharedpreferences.SettingRepository
import com.fahimezv.paradoxcat.util.SecureUtils
import java.io.IOException


class LoginRepository( private val settingRepository: SettingRepository,private val secureUtils: SecureUtils) {

    fun login(username: String, password: String): Result<Login> =
        if (settingRepository.getUserName()!=username && secureUtils.decryptIt(settingRepository.getPassword())!=password){
             Result.Error(IOException("Error logging in"))
        } else{
            val fakeUser = Login( fullName= settingRepository.getFullName()!!,userName = username)
            Result.Success(fakeUser)

        }


}