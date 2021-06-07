package com.fahimezv.paradoxcat.ui.register

import com.fahimezv.paradoxcat.data.model.Profile

/**
 * Authentication result : success (user details) or error message.
 */
data class RegisterResult(
    val success: Profile? = null,
    val error: Int? = null
)