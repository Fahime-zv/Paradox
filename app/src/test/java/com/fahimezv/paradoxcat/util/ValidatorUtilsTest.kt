
package com.fahimezv.paradoxcat.util

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ValidatorUtilsTest {

    private lateinit var mValidatorUtils: ValidatorUtils

    @Before
    fun setUp() {
        mValidatorUtils = ValidatorUtils()
    }

    // Email
    @Test
    fun `validateEmail() must return true when email is correct`() {
        assertTrue(mValidatorUtils.validateEmail("fahime.zv@gmail.com"))
    }

    @Test
    fun `validateEmail() must return false when email is not correct`() {
        assertFalse(mValidatorUtils.validateEmail(""))
        assertFalse(mValidatorUtils.validateEmail("fahime.zv@"))
        assertFalse(mValidatorUtils.validateEmail("fahime.zv@gmail"))
        assertFalse(mValidatorUtils.validateEmail("@gmail.com"))
        assertFalse(mValidatorUtils.validateEmail("@gmail"))
    }

    // Password
    @Test
    fun `validatePassword() must return true when password is equal or larger than 3 characters`() {
        assertTrue(mValidatorUtils.validatePassword("123"))
    }

    @Test
    fun `validatePassword() must return false when password is smaller than 3 characters`() {
        assertFalse(mValidatorUtils.validatePassword(""))
        assertFalse(mValidatorUtils.validatePassword("12"))
    }
     // FullName
    @Test
    fun `validateName() must return true when name is not blank`() {
        assertTrue(mValidatorUtils.validateFullName("fahime"))
    }

    @Test
    fun `validateName() must return false when name is blank or empty`() {
        assertFalse(mValidatorUtils.validateFullName(""))
        assertFalse(mValidatorUtils.validateFullName("   "))
    }






}