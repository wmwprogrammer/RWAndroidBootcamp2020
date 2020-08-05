package com.wmw.movieviewer.di

import androidx.room.Room
import com.wmw.movieviewer.model.DATABASE_NAME
import com.wmw.movieviewer.model.MovieDao
import com.wmw.movieviewer.model.MovieDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    single { Room.databaseBuilder(androidContext(), MovieDatabase::class.java, DATABASE_NAME).build() }

    single { get<MovieDatabase>().movieDao() }
}