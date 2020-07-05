package com.raywenderlich.myfavoritemovies.model

import android.app.Application
import android.content.Context
import androidx.room.Room

class MovieApplication : Application() {
    companion object {
        lateinit var database: MovieDatabase
        private lateinit var instance: MovieApplication

        fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        instance = this
        database = Room.databaseBuilder(this, MovieDatabase::class.java, "movie_database").build()
        super.onCreate()
    }
}