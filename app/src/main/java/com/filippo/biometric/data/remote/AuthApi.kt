package com.filippo.biometric.data.remote

import com.filippo.biometric.data.model.UserToken
import javax.inject.Inject
import kotlinx.coroutines.delay

class AuthApi @Inject constructor() {
    suspend fun login(username: String, password: String): UserToken {
        delay(1000)

        return if (username == "Filippo" && password == "zaq12wsx") {
            UserToken(FAKE_TOKEN)
        } else {
            throw LoginError
        }
    }
}

object LoginError : Throwable("Wrong credentials")