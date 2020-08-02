package com.wmw.movieviewer

import android.app.Application
import androidx.room.Room
import com.wmw.movieviewer.helper.SharedPrefsManager
import com.wmw.movieviewer.model.DATABASE_NAME
import com.wmw.movieviewer.model.MovieDatabase
import com.wmw.movieviewer.networking.RemoteApi
import com.wmw.movieviewer.networking.buildApiService
import com.wmw.movieviewer.repository.MovieRepositoryImpl
import com.wmw.movieviewer.repository.UserRepositoryImpl
import com.wmw.movieviewer.ui.LoginViewModelFactory
import com.wmw.movieviewer.ui.MovieDetailViewModelFactory
import com.wmw.movieviewer.ui.MovieViewModelFactory
import com.wmw.movieviewer.validators.CredentialsValidatorImpl

class App : Application() {
    companion object {
        private lateinit var instance: App
        private val movieDb: MovieDatabase by lazy {
            Room.databaseBuilder(instance, MovieDatabase::class.java, DATABASE_NAME).build()
        }

        private val movieDao by lazy { movieDb.movieDao() }

        private val apiService by lazy { buildApiService() }

        private val credentialsValidator by lazy { CredentialsValidatorImpl() }

        private val remoteMovieApi by lazy { RemoteApi(apiService) }

        private val userRepository by lazy { UserRepositoryImpl(SharedPrefsManager()) }

        val movieRepository: MovieRepositoryImpl by lazy { MovieRepositoryImpl(movieDao, remoteMovieApi) }

        val movieViewModelFactory by lazy { MovieViewModelFactory(movieRepository, userRepository) }

        val detailViewModelFactory by lazy { MovieDetailViewModelFactory(movieRepository) }

        val loginViewModelFactory by lazy { LoginViewModelFactory(credentialsValidator, userRepository)}

        fun getAppContext() = instance

        fun getToken() = BuildConfig.IMDB_TOKEN

    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}