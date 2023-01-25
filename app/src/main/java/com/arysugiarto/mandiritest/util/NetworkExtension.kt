package com.arysugiarto.mandiritest.util

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import com.arysugiarto.mandiritest.data.remote.Result


/**
 * Convert [ResponseBody.charStream] to [T] object
 */
inline fun <reified T : Any?> ResponseBody.parse(): T? {
    val classType = object : TypeToken<T>() {}.type
    return Gson().fromJson(charStream(), classType)
}

// Exception Handler
private fun Throwable.handleException() = when (this) {
    is IOException -> "Failed to read response!"
    is SocketTimeoutException -> "Timeout!"
    is UnknownHostException -> "Check your internet connection!"
    else -> "An error occurred!"
}

/**
 * Convert [Exception]/[Throwable]  to Custom Error Message
 */
val Throwable.parsedMessage get() = handleException()

/**
 * @return [Flow] of [Result] object with 3 Step of Result Handling
 * @param responseBody Used for [Response] callback value from ApiCallback
 */
inline fun <reified T> flowResponse(
    handleError: Boolean = true,
    crossinline errorMessage: (String) -> String = { emptyString },
    crossinline responseBody: suspend () -> Response<T>
) = flow<Result<T>> {
    val response = responseBody.invoke()
    val body= response.body()

    if(response.isSuccessful) {
        emit(Result.success((body)))
    } else {
        emit(
            Result.error(
                message = "Error",
                code = response.code()
            )
        )
    }}
    .onStart { emit(Result.loading()) }
    .flowOn(Dispatchers.IO)
    .retryWhen { cause, attempt ->
        attempt <= 3 && cause is SocketTimeoutException
    }
    .catch { throwable ->
        Timber.e("Error @${T::class.java} : $throwable")
        emit(Result.error<T>(throwable.parsedMessage, code = 500))
    }