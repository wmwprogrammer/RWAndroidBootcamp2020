package com.raywenderlich.myfavoritemovies.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.raywenderlich.myfavoritemovies.data.networking.Converters

@Database(entities = [(Movie::class)], version = 2)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}

const val DATABASE_NAME = "movie_database"
