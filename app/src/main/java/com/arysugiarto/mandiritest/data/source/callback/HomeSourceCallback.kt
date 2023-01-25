package com.arysugiarto.mandiritest.data.source.callback

import com.arysugiarto.mandiritest.data.remote.Result
import com.arysugiarto.mandiritest.data.remote.model.NewsResponse
import kotlinx.coroutines.flow.Flow

interface HomeSourceCallback {
    fun requestNews(
    ): Flow<Result<NewsResponse>>
}