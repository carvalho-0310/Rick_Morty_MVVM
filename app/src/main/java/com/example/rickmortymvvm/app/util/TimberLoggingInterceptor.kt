package com.example.rickmortymvvm.app.util

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer
import timber.log.Timber
import java.io.IOException

class TimberLoggingInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val t1 = System.nanoTime()
        Timber.i("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers())
        Timber.v("REQUEST BODY BEGIN\n%s\nREQUEST BODY END", bodyToString(request))

        val response = chain.proceed(request)

        val responseBody: ResponseBody? = response.body()
        val responseBodyString = response.body()?.string()

        val newResponse = response.newBuilder().body(ResponseBody.create(responseBody?.contentType(), responseBodyString?.toByteArray())).build()

        val t2 = System.nanoTime()
        Timber.i("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6, response.headers())
        Timber.i("Code status ${ response.code()}")
        Timber.v("RESPONSE BODY BEGIN:\n%s\nRESPONSE BODY END", responseBodyString)

        return newResponse
    }

    private fun bodyToString(request: Request): String {
        return try {
            val copy: Request = request.newBuilder().build()
            val buffer = Buffer()
            copy.body()?.writeTo(buffer)
            buffer.readUtf8()
        } catch (e: IOException) {
            "did not work"
        }
    }
}
