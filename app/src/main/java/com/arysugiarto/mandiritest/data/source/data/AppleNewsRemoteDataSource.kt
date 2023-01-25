package com.arysugiarto.mandiritest.data.source.data

import com.arysugiarto.mandiritest.data.remote.api.ApiCallback
import com.arysugiarto.mandiritest.util.flowResponse

class AppleNewsRemoteDataSource(callback: ApiCallback) {
    private val apiCallback = callback

    fun requestNewsAppleDataSource() =
        flowResponse {
            apiCallback.requestAppleNews()
        }

}