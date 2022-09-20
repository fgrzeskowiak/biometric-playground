package com.filippo.biometric.feature.feed

import com.filippo.biometric.data.AuthRepository
import com.filippo.biometric.data.FeedItem
import com.filippo.biometric.data.FeedRepository
import javax.inject.Inject

class FeedUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val feedRepository: FeedRepository
) {
    suspend fun getFeed(): List<FeedItem> = authRepository.callWithAuthToken(feedRepository::getFeed)
}