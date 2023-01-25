package com.arysugiarto.mandiritest.base

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arysugiarto.mandiritest.data.preferences.AccessManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    private val _hasInternetConnectionLiveData = MutableLiveData<Boolean>()

    private val connectivityManager = application.getSystemService(
            Context.CONNECTIVITY_SERVICE
    ) as ConnectivityManager

    private val internetCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            _hasInternetConnectionLiveData.postValue(true)
        }

        override fun onUnavailable() {
            super.onUnavailable()
            _hasInternetConnectionLiveData.postValue(false)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            _hasInternetConnectionLiveData.postValue(false)
        }
    }

    override fun onCleared() {
        super.onCleared()

        connectivityManager.runCatching {
            unregisterNetworkCallback(internetCallback)
        }
    }
}