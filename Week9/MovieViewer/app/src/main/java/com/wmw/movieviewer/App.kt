package com.wmw.movieviewer

import android.app.Application
import androidx.room.Room
import com.wmw.movieviewer.helper.SharedPrefsManager
import com.wmw.movieviewer.model.DATABASE_NAME
import com.wmw.movieviewer.model.MovieDatabase
import com.wmw.movieviewer.networking.RemoteApi
import com.wmw.movieviewer.networking.buildApiService
import com.wmw.movieviewer.repository.MovieRepository
import com.wmw.movieviewer.repository.UserRepository
import com.wmw.movieviewer.ui.MovieViewModelFactory
import kotlinx.serialization.UnstableDefault

/**
 * Using an App class, so that I can init the movieDb one time.
 */

class App : Application() {
    companion object {
        private lateinit var instance: App
        private val movieDb: MovieDatabase by lazy {
            Room.databaseBuilder(instance, MovieDatabase::class.java, DATABASE_NAME).build()
        }

        private val movieDao by lazy { movieDb.movieDao() }

        @UnstableDefault
        private val apiService by lazy { buildApiService() }

        @UnstableDefault
        val remoteApi by lazy { RemoteApi(apiService) }

        val userRepository by lazy { UserRepository(SharedPrefsManager()) }

        @UnstableDefault
        val repository: MovieRepository by lazy { MovieRepository(movieDao, remoteApi) }

        @UnstableDefault
        val viewModelFactory by lazy { MovieViewModelFactory(repository, userRepository) }

        fun getAppContext() = instance

        fun getToken() = BuildConfig.IMDB_TOKEN

    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}