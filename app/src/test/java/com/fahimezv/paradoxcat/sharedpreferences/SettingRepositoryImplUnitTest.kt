package com.fahimezv.paradoxcat.sharedpreferences

import com.fahimezv.paradoxcat.data.sharedpreferences.*
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class SettingRepositoryImplUnitTest {

    @Mock
    private lateinit var mSharedPref: SharedPref


    private lateinit var mSettingRepository: SettingRepository

    @Before
    fun setUp() {
        mSettingRepository = SettingRepositoryImpl(mSharedPref)
    }

    //Username
    @Test
    fun `saveUserName() call write() with correct arguments`() {
        val username = "doesn't matter"
        mSettingRepository.saveUserName(username)
        verify(mSharedPref).write(eq(KEY_USERNAME), eq(username))
    }

    @Test
    fun `getUserName() call read() with correct arguments`() {
        mSettingRepository.getUserName()
        verify(mSharedPref).read(eq(KEY_USERNAME), eq(null))
    }

    @Test
    fun `getUserName() return null when read return null`() {
        whenever(mSharedPref.read(KEY_USERNAME, null)).thenReturn(null)
        val expected = mSettingRepository.getUserName()
        assertNull(expected)
    }

    @Test
    fun `getUserName() return username when read return not null`() {
        val username = "doesn't matter"
        whenever(mSharedPref.read(KEY_USERNAME, null)).thenReturn(username)
        val actual = mSettingRepository.getUserName()
        assertEquals(username, actual)
    }

    //FullName
    @Test
    fun `saveFullName() call write() with correct arguments`() {
        val fullName = "doesn't matter"
        mSettingRepository.saveFullName(fullName)
        verify(mSharedPref).write(eq(KEY_FULL_NAME), eq(fullName))
    }

    @Test
    fun `getFullName() call read() with correct arguments`() {
        mSettingRepository.getFullName()
        verify(mSharedPref).read(eq(KEY_FULL_NAME), eq(null))
    }

    @Test
    fun `getFullName() return null when read return null`() {
        whenever(mSharedPref.read(KEY_FULL_NAME, null)).thenReturn(null)
        val expected = mSettingRepository.getFullName()
        assertNull(expected)
    }

    @Test
    fun `getFullName() return fullName when read return not null`() {
        val fullName = "doesn't matter"
        whenever(mSharedPref.read(KEY_FULL_NAME, null)).thenReturn(fullName)
        val actual = mSettingRepository.getFullName()
        assertEquals(fullName, actual)
    }

    //Password
    @Test
    fun `savePassword() call write() with correct arguments`() {
        val password = "1234ll"
        mSettingRepository.savePassword(password)
        verify(mSharedPref).write(eq(KEY_PASSWORD), eq(password))
    }

    @Test
    fun `getPassword() call read() with correct arguments`() {
        mSettingRepository.getPassword()
        verify(mSharedPref).read(eq(KEY_PASSWORD), eq(null))
    }

    @Test
    fun `getPassword() return null when read return null`() {
        whenever(mSharedPref.read(KEY_PASSWORD, null)).thenReturn(null)
        val expected = mSettingRepository.getPassword()
        assertNull(expected)
    }


    //has register
    @Test
    fun `getHasRegister() call read() with correct arguments`() {
        mSettingRepository.getHasRegister()
        verify(mSharedPref).read(eq(KEY_HAS_REGISTER), eq(false))
    }



    @Test
    fun `getHasRegister() call write() with correct arguments`() {
        val notification = true
        mSettingRepository.saveHasRegister(notification)
        verify(mSharedPref).write(eq(KEY_HAS_REGISTER), eq(notification))
    }
}