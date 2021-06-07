package com.fahimezv.paradoxcat.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */

data class Login(
    val fullName: String,
    val userName: String,
    val password:String?=null
)