package com.wmw.movieviewer

import android.app.Application
import android.content.Context
import com.wmw.movieviewer.di.dbModule
import com.wmw.movieviewer.di.networkModule
import com.wmw.movieviewer.di.repositoryModule
import com.wmw.movieviewer.di.viewModelModule
import com.wmw.movieviewer.helper.SharedPrefsManager
import com.wmw.movieviewer.repository.UserRepositoryImpl
import com.wmw.movieviewer.ui.LoginViewModelFactory
import com.wmw.movieviewer.validators.CredentialsValidatorImpl
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    companion object {
        private lateinit var instance: App

        private val credentialsValidator by lazy { CredentialsValidatorImpl() }

        private val userRepository by lazy { UserRepositoryImpl(SharedPrefsManager()) }

        val loginViewModelFactory by lazy { LoginViewModelFactory(credentialsValidator, userRepository)}

        fun getAppContext(): Context = instance.applicationContext

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