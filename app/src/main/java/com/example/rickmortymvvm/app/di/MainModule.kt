package com.example.rickmortymvvm.app.di

import com.example.rickmortymvvm.app.util.TimberLoggingInterceptor
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "url api"

val mainModule = module {

    val client = OkHttpClient.Builder()
        .addInterceptor(TimberLoggingInterceptor())
        .build()

    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named(BASE_URL)))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }
    single(named(BASE_URL)) { "https://rickandmortyapi.com/api/" }
}
