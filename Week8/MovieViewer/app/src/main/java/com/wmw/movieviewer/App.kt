package com.wmw.movieviewer

import android.app.Application
import androidx.room.Room
import com.wmw.movieviewer.data.networking.RemoteApi
import com.wmw.movieviewer.data.networking.buildApiService
import com.wmw.movieviewer.model.DATABASE_NAME
import com.wmw.movieviewer.model.MovieDatabase

/**
 * Using an App class, so that I can init the movieDb one time.
 */

private const val TOKEN = "08e2d505-51b6-4bae-8fc3-a12a0cbe8951"

class App : Application() {
    companion object {
        private lateinit var instance: App
        lateinit var movieDb: MovieDatabase

        fun getAppContext() = instance

        fun getToken() = TOKEN

        private val apiService by lazy { buildApiService() }

        val remoteApi by lazy { RemoteApi(apiService) }

    }

    override fun onCreate() {
        instance = this
        super.onCreate()
        initDatabase()
    }

    private fun initDatabase() {
        movieDb = Room.databaseBuilder(this, MovieDatabase::class.java, DATABASE_NAME)
            .build()
    }
}