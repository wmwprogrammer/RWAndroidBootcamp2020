package com.raywenderlich.myfavoritemovies

import android.app.Application
import androidx.room.Room
import com.raywenderlich.myfavoritemovies.model.DATABASE_NAME
import com.raywenderlich.myfavoritemovies.model.MovieDatabase

/**
 * Using an App class, so that I can init the movieDb one time.
 */
class App : Application() {
    companion object {
        private lateinit var instance: App
        lateinit var movieDb: MovieDatabase

        fun getAppContext() = instance
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
        initDatabase()
    }

    private fun initDatabase() {
        movieDb = Room.databaseBuilder(this, MovieDatabase::class.java, DATABASE_NAME)
            .allowMainThreadQueries()
            .build()
    }
}