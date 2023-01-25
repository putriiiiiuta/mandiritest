package com.arysugiarto.mandiritest.data.di

import com.arysugiarto.mandiritest.data.remote.api.ApiCallback
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideApiCallback(retrofit: Retrofit): ApiCallback =
        retrofit.create(ApiCallback::class.java)

}