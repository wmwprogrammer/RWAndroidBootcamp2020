package com.wmw.movieviewer

import android.app.Application
import androidx.room.Room
import com.wmw.movieviewer.helper.SharedPrefsManager
import com.wmw.movieviewer.model.DATABASE_NAME
import com.wmw.movieviewer.model.MovieDatabase
import com.wmw.movieviewer.networking.RemoteApi
import com.wmw.movieviewer.networking.buildApiService
import com.wmw.movieviewer.repository.MovieRepositoryImpl
import com.wmw.movieviewer.repository.UserRepository
import com.wmw.movieviewer.ui.LoginViewModelFactory
import com.wmw.movieviewer.ui.MovieDetailViewModelFactory
import com.wmw.movieviewer.ui.MovieViewModelFactory
import com.wmw.movieviewer.validators.CredentialsValidator
import kotlinx.serialization.UnstableDefault

/**
 * Using an App class, so that I can init the movieDb one time.
 */
@UnstableDefault
class App : Application() {
    companion object {
        private lateinit var instance: App
        private val movieDb: MovieDatabase by lazy {
            Room.databaseBuilder(instance, MovieDatabase::class.java, DATABASE_NAME).build()
        }

        private val movieDao by lazy { movieDb.movieDao() }

        private val apiService by lazy { buildApiService() }

        val credentialsValidator by lazy { CredentialsValidator() }

        val remoteMovieApi by lazy { RemoteApi(apiService) }

        val userRepository by lazy { UserRepository(SharedPrefsManager()) }

        val movieRepository: MovieRepositoryImpl by lazy { MovieRepositoryImpl(movieDao, remoteMovieApi) }

        val movieViewModelFactory by lazy { MovieViewModelFactory(movieRepository, userRepository) }

        val detailViewModelFactory by lazy { MovieDetailViewModelFactory() }

        val loginViewModelFactory by lazy { LoginViewModelFactory(credentialsValidator, userRepository)}

        fun getAppContext() = instance

        fun getToken() = BuildConfig.IMDB_TOKEN

    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}