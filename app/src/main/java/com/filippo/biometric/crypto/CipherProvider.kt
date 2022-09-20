package com.filippo.biometric.crypto

import com.filippo.biometric.crypto.SecretKeyGenerator.Companion.ENCRYPTION_ALGORITHM
import com.filippo.biometric.crypto.SecretKeyGenerator.Companion.ENCRYPTION_BLOCK_MODE
import com.filippo.biometric.crypto.SecretKeyGenerator.Companion.ENCRYPTION_PADDING
import com.filippo.biometric.data.PreferencesEditor
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec
import javax.inject.Inject

class CipherProvider @Inject constructor(
    private val keyGenerator: SecretKeyGenerator,
    private val preferencesEditor: PreferencesEditor,
) {

    fun provideCipherForEncryption(): Cipher = Cipher.getInstance(TRANSFORMATION).apply {
        init(Cipher.ENCRYPT_MODE, keyGenerator.getOrCreateSecretKey())
    }

    suspend fun provideCipherForDecryption(): Cipher {
        val initialisationVector = preferencesEditor.getInitialisationVector()
        return Cipher.getInstance(TRANSFORMATION).apply {
            init(
                Cipher.DECRYPT_MODE,
                keyGenerator.getOrCreateSecretKey(),
                GCMParameterSpec(SecretKeyGenerator.KEY_SIZE, initialisationVector)
            )
        }
    }

    companion object {
        private const val TRANSFORMATION =
            "${ENCRYPTION_ALGORITHM}/${ENCRYPTION_BLOCK_MODE}/${ENCRYPTION_PADDING}"
    }
}