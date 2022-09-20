package com.filippo.biometric.crypto

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyStore
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.inject.Inject

class SecretKeyGenerator @Inject constructor() {

    fun getOrCreateSecretKey(): SecretKey {
        val keyStore = KeyStore.getInstance(ANDROID_KEYSTORE)
        keyStore.load(null)
        val key = keyStore.getKey(KEY_NAME, null)
        if (key != null) {
            return key as SecretKey
        }

        val keyGenParams = KeyGenParameterSpec
            .Builder(KEY_NAME, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
            .setBlockModes(ENCRYPTION_BLOCK_MODE)
            .setEncryptionPaddings(ENCRYPTION_PADDING)
            .setKeySize(KEY_SIZE)
            .setUserAuthenticationRequired(true)
            .build()

        val keyGenerator = KeyGenerator.getInstance(
            ENCRYPTION_ALGORITHM,
            ANDROID_KEYSTORE
        )
        keyGenerator.init(keyGenParams)

        return keyGenerator.generateKey()
    }

    companion object {
        private const val ANDROID_KEYSTORE = "AndroidKeyStore"
        private const val KEY_NAME = "SecretKeyName"
        const val ENCRYPTION_BLOCK_MODE = KeyProperties.BLOCK_MODE_GCM
        const val ENCRYPTION_PADDING = KeyProperties.ENCRYPTION_PADDING_NONE
        const val ENCRYPTION_ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
        const val KEY_SIZE = 128
    }

}