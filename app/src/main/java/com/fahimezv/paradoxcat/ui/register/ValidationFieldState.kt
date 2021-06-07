package com.fahimezv.paradoxcat.ui.register

 // Data validation state of the  ui.

data class ValidationFieldState(
    val fullNameError: Int? = null,
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val done: Boolean = true,
)