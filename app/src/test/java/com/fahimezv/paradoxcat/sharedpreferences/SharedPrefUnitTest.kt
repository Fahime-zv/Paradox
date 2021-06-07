package com.fahimezv.paradoxcat.sharedpreferences

import android.content.SharedPreferences
import com.fahimezv.paradoxcat.data.sharedpreferences.SharedPref
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SharedPrefUnitTest {

    @Mock
    private lateinit var mSharedPreferences: SharedPreferences
    @Mock
    private lateinit var mEditor: SharedPreferences.Editor

    private lateinit var mSharedPref: SharedPref


    @Before
    fun setUp() {
        mSharedPref = Mockito.spy(SharedPref(mSharedPreferences ))

    }

    // String
    @Test
    fun `check read() calls getString`() {
        mSharedPref.read("key",null)
        verify(mSharedPreferences).getString(eq("key"), eq(null))
    }

    @Test
    fun `check write() calls putString`() {
        whenever(mSharedPreferences.edit()).thenReturn(mEditor)
        mSharedPref.write("key","hi")
        inOrder(mSharedPreferences, mEditor, mEditor) {
            verify(mSharedPreferences).edit()
            verify(mEditor).putString(eq("key"), eq("hi"))
            verify(mEditor).apply()
        }
    }

    //  Boolean
    @Test
    fun `check read() calls getBoolean`() {
        mSharedPref.read("key",true)
        verify(mSharedPreferences).getBoolean(eq("key"), eq(true))
    }

    @Test
    fun `check write() calls putBoolean`() {
        whenever(mSharedPreferences.edit()).thenReturn(mEditor)
        mSharedPref.write("key",true)
        inOrder(mSharedPreferences, mEditor, mEditor) {
            verify(mSharedPreferences).edit()
            verify(mEditor).putBoolean(eq("key"), eq(true))
            verify(mEditor).apply()
        }
    }



    @Test
    fun `check remove() calls remove`() {
        whenever(mSharedPreferences.edit()).thenReturn(mEditor)
        mSharedPref.remove("key")
        inOrder(mSharedPreferences, mEditor, mEditor) {
            verify(mSharedPreferences).edit()
            verify(mEditor).remove(eq("key"))
            verify(mEditor).apply()
        }
    }

}