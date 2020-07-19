package com.wmw.movieviewer.model

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wmw.movieviewer.App
import com.wmw.movieviewer.networking.Converters

const val DATABASE_NAME = "movie_database"

@Database(entities = [(Movie::class)], version = 1)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {

    companion object {
        fun getMovieDatabase(context: Context): MovieDatabase {
            return App.movieDb
        }
    }

    abstract fun movieDao(): MovieDao
}

