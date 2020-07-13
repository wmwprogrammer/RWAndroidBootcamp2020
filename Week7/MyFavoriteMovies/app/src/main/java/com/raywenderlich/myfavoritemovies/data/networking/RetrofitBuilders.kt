package com.raywenderlich.myfavoritemovies.data.networking

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

fun buildClient(): OkHttpClient = OkHttpClient.Builder().build()

@kotlinx.serialization.UnstableDefault
fun buildRetrofit(): Retrofit {
    val contentType = "application/json".toMediaType()

    return Retrofit.Builder()
        .client(buildClient())
        .baseUrl(BASE_URL)
        .addConverterFactory(Json(JsonConfiguration(false, true)).asConverterFactory(contentType))
        .build()
}

@UnstableDefault
fun buildApiService(): RemoteApiService = buildRetrofit().create(RemoteApiService::class.java)