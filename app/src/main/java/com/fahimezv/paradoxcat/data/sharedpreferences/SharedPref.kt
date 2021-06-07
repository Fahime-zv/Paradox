package com.fahimezv.paradoxcat.data.sharedpreferences

import android.content.SharedPreferences

class SharedPref(private val sharedPreferences: SharedPreferences) {

    // Strings
    fun read(key: String, defValue: String?): String? {
        return sharedPreferences?.getString(key, defValue)
    }

    fun write(key: String, value: String) {
        val prefsEditor = sharedPreferences?.edit()
        prefsEditor?.putString(key, value)
        prefsEditor?.apply()
    }

    // Boolean
    fun read(key: String, defValue: Boolean): Boolean? {
        return sharedPreferences?.getBoolean(key, defValue)
    }

    fun write(key: String, value: Boolean) {
        val prefsEditor = sharedPreferences?.edit()
        prefsEditor?.putBoolean(key, value)
        prefsEditor?.apply()
    }


    // General
    fun remove(key: String) {
        val prefsEditor = sharedPreferences?.edit()
        prefsEditor?.remove(key)
        prefsEditor?.apply()
    }


}
