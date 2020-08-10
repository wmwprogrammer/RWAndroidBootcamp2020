package com.wmw.movieviewer

import android.app.Application
import android.content.Context
import com.wmw.movieviewer.di.dbModule
import com.wmw.movieviewer.di.networkModule
import com.wmw.movieviewer.di.repositoryModule
import com.wmw.movieviewer.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    companion object {
        private lateinit var instance: App

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