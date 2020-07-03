package com.raywenderlich.myfavoritemovies.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(Movie::class)], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}

const val DATABASE_NAME = "movie_database"
