package com.wmw.movieviewer.di

import com.wmw.movieviewer.App
import com.wmw.movieviewer.BuildConfig
import com.wmw.movieviewer.networking.RemoteApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single(named("BASE_URL")) {
        "https://www.myapifilms.com/"
    }
    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        interceptor
    }
    single {
        val authInterceptor = authorizationInterceptor()
        authInterceptor
    }
    single {
        val client = OkHttpClient().newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
        client.addInterceptor(authorizationInterceptor())
        if (BuildConfig.DEBUG) {
            client.addInterceptor(get<HttpLoggingInterceptor>())
        }
        client.build()
    }
//    single {
//        Moshi.Builder()
//            .add(KotlinJsonAdapterFactory())
//            .build()
//    }
    single {
        Retrofit.Builder().baseUrl(get<String>(named("BASE_URL")))
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }
    single {
        get<Retrofit>().create(RemoteApiService::class.java)
    }
}

private fun authorizationInterceptor() = object : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        if (App.getToken().isBlank()) return chain.proceed(originalRequest)

        val new = originalRequest.newBuilder()
            .addHeader("Authorization", App.getToken())
            .build()

        return chain.proceed(new)
    }


}