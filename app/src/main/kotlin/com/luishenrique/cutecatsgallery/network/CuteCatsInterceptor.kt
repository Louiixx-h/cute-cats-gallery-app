package com.luishenrique.cutecatsgallery.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class CuteCatsInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Log.v(TAG, "${request.method}: ${request.url}")
        request.headers.forEachIndexed { _, pair ->
            Log.v(TAG, "Header --> ${pair.first} : ${pair.second}")
        }
        if (request.method == POST_METHOD) {
            Log.v(TAG, "Body: ${request.body}")
        }
        return chain.proceed(request)
    }

    companion object {
        private const val TAG = "RequestInterceptor"
        private const val POST_METHOD = "POST"
    }
}