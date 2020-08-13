package com.wmw.movieviewer.di

import com.wmw.movieviewer.networking.MoviesApi
import org.junit.Assert.assertNotNull
import okhttp3.OkHttpClient
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.inject
import retrofit2.Retrofit

class NetworkTest : KoinTest {
    val api: MoviesApi by inject()
    val retrofit: Retrofit by inject()
    val okHttpClient: OkHttpClient by inject()
    val baseUrl: String by lazy { get(named("BASE_URL")) as String}

    @Before
    fun setup() {
        startKoin {
            modules(listOf(networkModule))
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `Test Retrofit`() {
        assertNotNull(retrofit)
        assert(baseUrl == "https://www.myapifilms.com/")
    }

    @Test
    fun `Test API`() {
        assertNotNull(api)
    }

    @Test
    fun `Test OkHttp`() {
        assertNotNull(okHttpClient)
        assertTrue(okHttpClient.connectTimeoutMillis == 30000)
    }
}