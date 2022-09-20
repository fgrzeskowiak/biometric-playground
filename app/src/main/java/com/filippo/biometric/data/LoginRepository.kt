package com.filippo.biometric.data

import com.filippo.biometric.data.model.Username
import com.filippo.biometric.data.model.Password
import com.filippo.biometric.data.model.UserToken
import com.filippo.biometric.data.remote.AuthApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(private val authApi: AuthApi) {

    suspend fun login(username: Username, password: Password): UserToken =
        authApi.login(username.value, password.value)
}