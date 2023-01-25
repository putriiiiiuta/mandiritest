package com.arysugiarto.mandiritest.data.repositories

import com.arysugiarto.mandiritest.data.source.callback.AppleNewsSourceCallback
import com.arysugiarto.mandiritest.data.source.data.AppleNewsRemoteDataSource

class AppleNewsRepository(
    appleRemoteDataSource: AppleNewsRemoteDataSource
) : AppleNewsSourceCallback {
    private val remoteDataSource = appleRemoteDataSource

    override fun requestNewsApple() = remoteDataSource.requestNewsAppleDataSource()

}