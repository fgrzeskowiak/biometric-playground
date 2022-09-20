package com.filippo.biometric.data.remote

import com.filippo.biometric.data.model.FeedItem
import com.filippo.biometric.data.model.UserToken
import java.util.*

class FeedApi {
    fun getFeed(userToken: UserToken) = if (userToken.value == FAKE_TOKEN) {
        List(20) { FeedItem(UUID.randomUUID().toString(), System.currentTimeMillis()) }
    } else {
        throw AuthException
    }
}

object AuthException : Throwable("Not authorised")