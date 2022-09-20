package com.filippo.biometric.di

import android.content.Context
import android.content.SharedPreferences
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object BiometricsModule {

    @Provides
    @Named("canUseBiometrics")
    fun canUseBiometrics(@ApplicationContext context: Context): Boolean =
        BiometricManager.from(context).canAuthenticate(BIOMETRIC_STRONG) == BIOMETRIC_SUCCESS
}