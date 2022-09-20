package com.filippo.biometric.crypto

import com.filippo.biometric.data.PreferencesEditor
import com.filippo.biometric.data.model.UserToken
import com.filippo.biometric.di.IoDispatcher
import javax.crypto.Cipher
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

@Singleton
class UserTokenDecryptor @Inject constructor(
    private val preferencesEditor: PreferencesEditor,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun decrypt(cipher: Cipher): UserToken = withContext(ioDispatcher) {
        val encryptedToken = preferencesEditor.getUserToken()

        UserToken(cipher.doFinal(encryptedToken).decodeToString())
    }
}