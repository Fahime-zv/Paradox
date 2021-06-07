package com.fahimezv.paradoxcat.util

import androidx.core.util.PatternsCompat

class ValidatorUtils() {

    fun validateEmail(email: String): Boolean {
        // Use PatternsCompat instead of Patterns for be able to mock in tests
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validatePassword(password: String): Boolean {
        return password.length >= 3
    }


    fun validateFullName(fullName: String): Boolean {
        return fullName.isNotBlank()
    }
}