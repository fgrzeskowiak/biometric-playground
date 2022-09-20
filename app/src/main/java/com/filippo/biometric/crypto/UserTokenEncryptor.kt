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
class UserTokenEncryptor @Inject constructor(
    private val preferencesEditor: PreferencesEditor,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {
    suspend fun encrypt(token: UserToken, cipher: Cipher) {
        withContext(ioDispatcher) {
            with(preferencesEditor) {
                saveUserToken(cipher.doFinal(token.value.encodeToByteArray()))
                saveInitialisationVector(cipher.iv)
            }
        }
    }
}