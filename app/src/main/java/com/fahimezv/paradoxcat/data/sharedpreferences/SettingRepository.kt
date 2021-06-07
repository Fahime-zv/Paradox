package com.fahimezv.paradoxcat.data.sharedpreferences


interface SettingRepository {
    fun saveHasRegister(enable: Boolean)
    fun getHasRegister(): Boolean
    fun saveUserName(username: String)
    fun saveFullName(fullName: String)
    fun savePassword(password: String)
    fun getUserName(): String?
    fun getFullName(): String?
    fun getPassword(): String?
    fun clearProfile()
}

const val KEY_HAS_REGISTER = "hasRegister"
const val KEY_FULL_NAME = "fullName"
const val KEY_USERNAME = "username"
const val KEY_PASSWORD = "password"


class SettingRepositoryImpl(private val sharedPref: SharedPref) : SettingRepository {

    override fun getHasRegister(): Boolean {
        val read = sharedPref.read(KEY_HAS_REGISTER, false)
        return read != null
    }

    override fun saveHasRegister(enable: Boolean) {
        sharedPref.write(KEY_HAS_REGISTER, enable)
    }

    override fun saveUserName(username: String) {
        sharedPref.write(KEY_USERNAME, username)
    }

    override fun saveFullName(fullName: String) {
        sharedPref.write(KEY_FULL_NAME, fullName)
    }

    override fun savePassword(password: String) {
        sharedPref.write(KEY_PASSWORD, password)
    }

    override fun getUserName(): String? {
        return sharedPref.read(KEY_USERNAME, null)

    }

    override fun getFullName(): String? {
        return sharedPref.read(KEY_FULL_NAME, null)

    }

    override fun getPassword(): String? {
        return sharedPref.read(KEY_PASSWORD, null)
    }


    override fun clearProfile() {
        sharedPref.remove(KEY_PASSWORD)
        sharedPref.remove(KEY_USERNAME)
        sharedPref.remove(KEY_FULL_NAME)
    }


}