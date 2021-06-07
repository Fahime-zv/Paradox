package com.fahimezv.paradoxcat.util

import android.content.ContentValues.TAG
import android.util.Base64
import android.util.Log
import java.io.UnsupportedEncodingException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.security.spec.InvalidKeySpecException
import javax.crypto.*
import javax.crypto.spec.DESKeySpec

 class SecureUtils {
    private val cryptoPass = "fah9832ziv2"

    fun encryptIt(value: String): String {
        try {
            val keySpec = DESKeySpec(cryptoPass.toByteArray(charset("UTF8")))
            val keyFactory: SecretKeyFactory = SecretKeyFactory.getInstance("DES")
            val key: SecretKey = keyFactory.generateSecret(keySpec)
            val clearText = value?.toByteArray(charset("UTF8"))
            // Cipher is not thread safe
            val cipher: Cipher = Cipher.getInstance("DES")
            cipher.init(Cipher.ENCRYPT_MODE, key)
            return Base64.encodeToString(cipher.doFinal(clearText), Base64.DEFAULT)
        } catch (e: InvalidKeyException) {
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        } catch (e: InvalidKeySpecException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        } catch (e: NoSuchPaddingException) {
            e.printStackTrace()
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        }
        return value
    }
    fun decryptIt(value: String?): String? {
        try {
            val keySpec = DESKeySpec(cryptoPass.toByteArray(charset("UTF8")))
            val keyFactory = SecretKeyFactory.getInstance("DES")
            val key = keyFactory.generateSecret(keySpec)
            val encryptedPwdBytes = Base64.decode(value, Base64.DEFAULT)
            // cipher is not thread safe
            val cipher = Cipher.getInstance("DES")
            cipher.init(Cipher.DECRYPT_MODE, key)
            val decryptedValueBytes = cipher.doFinal(encryptedPwdBytes)
            val decryptedValue = String(decryptedValueBytes)
            Log.d(TAG, "Decrypted: $value -> $decryptedValue")
            return decryptedValue
        } catch (e: InvalidKeyException) {
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        } catch (e: InvalidKeySpecException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        } catch (e: NoSuchPaddingException) {
            e.printStackTrace()
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        }
        return value
    }
}