package com.wmw.movieviewer

import android.app.Application
import androidx.room.Room
import com.wmw.movieviewer.di.dbModule
import com.wmw.movieviewer.di.networkModule
import com.wmw.movieviewer.di.repositoryModule
import com.wmw.movieviewer.di.viewModelModule
import com.wmw.movieviewer.helper.SharedPrefsManager
import com.wmw.movieviewer.model.DATABASE_NAME
import com.wmw.movieviewer.model.MovieDatabase
import com.wmw.movieviewer.networking.MoviesApi
import com.wmw.movieviewer.repository.MoviesRepositoryImpl
import com.wmw.movieviewer.repository.UserRepositoryImpl
import com.wmw.movieviewer.ui.LoginViewModelFactory
import com.wmw.movieviewer.ui.MovieDetailViewModelFactory
import com.wmw.movieviewer.ui.MovieViewModelFactory
import com.wmw.movieviewer.validators.CredentialsValidatorImpl
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    companion object {
        private lateinit var instance: App
//        private val movieDb: MovieDatabase by lazy {
//            Room.databaseBuilder(instance, MovieDatabase::class.java, DATABASE_NAME).build()
//        }

//        private val movieDao by lazy { movieDb.movieDao() }

//        private val moviesApiService by lazy { buildApiService() }

        private val credentialsValidator by lazy { CredentialsValidatorImpl() }

//        private val moviesApi by lazy { MoviesApi(moviesApiService) }

        private val userRepository by lazy { UserRepositoryImpl(SharedPrefsManager()) }

//        val moviesRepository: MoviesRepositoryImpl by lazy { MoviesRepositoryImpl(movieDao, moviesApi) }

//        val movieViewModelFactory by lazy { MovieViewModelFactory(moviesRepository, userRepository) }

//        val movieDetailViewModelFactory by lazy { MovieDetailViewModelFactory(moviesRepository) }

        val loginViewModelFactory by lazy { LoginViewModelFactory(credentialsValidator, userRepository)}

        fun getAppContext() = instance.applicationContext

        fun getToken() = BuildConfig.IMDB_TOKEN

    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(networkModule, viewModelModule, repositoryModule, dbModule))
        }
    }
}