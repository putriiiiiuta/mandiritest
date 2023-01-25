package com.arysugiarto.mandiritest.data.repositories

import com.arysugiarto.mandiritest.data.source.callback.HomeSourceCallback
import com.arysugiarto.mandiritest.data.source.data.HomeRemoteDataSource

class HomeRepository(
    homeRemoteDataSource: HomeRemoteDataSource
) : HomeSourceCallback {
    private val remoteDataSource = homeRemoteDataSource

    override fun requestNews() = remoteDataSource.requestNewsDataSource()

}