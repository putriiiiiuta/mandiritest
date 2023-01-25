package com.arysugiarto.mandiritest.data.source.callback

import com.arysugiarto.mandiritest.data.remote.Result
import com.arysugiarto.mandiritest.data.remote.model.AppleNewsResponse
import kotlinx.coroutines.flow.Flow

interface AppleNewsSourceCallback {
    fun requestNewsApple(
    ): Flow<Result<AppleNewsResponse>>
}