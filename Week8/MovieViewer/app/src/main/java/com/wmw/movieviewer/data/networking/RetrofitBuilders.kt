package com.wmw.movieviewer.data.networking

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.wmw.movieviewer.App
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

private const val HEADER_AUTHORIZATION = "Authorization"

fun buildClient(): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
//        .addInterceptor(buildAuthorizationInterceptor())
        .build()

fun buildRetrofit(): Retrofit {
    val contentType = "application/json".toMediaType()

    return Retrofit.Builder()
        .client(buildClient())
        .baseUrl(BASE_URL)
        .addConverterFactory(Json.nonstrict.asConverterFactory(contentType))
        .build()
}

fun buildApiService(): RemoteApiService =
    buildRetrofit().create(RemoteApiService::class.java)

fun buildAuthorizationInterceptor() = object : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        if (App.getToken().isBlank()) return chain.proceed(originalRequest)

        val new = originalRequest.newBuilder()
            .addHeader(HEADER_AUTHORIZATION, App.getToken())
            .build()

        return chain.proceed(new)
    }


}
//fun buildClient(): OkHttpClient = OkHttpClient.Builder().build()
//
//@kotlinx.serialization.UnstableDefault
//fun buildRetrofit(): Retrofit {
//    val contentType = "application/json".toMediaType()
//
//    return Retrofit.Builder()
//        .client(buildClient())
//        .baseUrl(BASE_URL)
//        .addConverterFactory(Json(JsonConfiguration(false, true)).asConverterFactory(contentType))
//        .build()
//}
//
//@UnstableDefault
//fun buildApiService(): RemoteApiService = buildRetrofit().create(RemoteApiService::class.java)