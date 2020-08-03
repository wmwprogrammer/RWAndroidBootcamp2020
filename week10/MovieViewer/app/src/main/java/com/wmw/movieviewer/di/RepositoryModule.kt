package com.wmw.movieviewer.di

import com.wmw.movieviewer.helper.SharedPrefsManager
import com.wmw.movieviewer.repository.MoviesRepository
import com.wmw.movieviewer.repository.MoviesRepositoryImpl
import com.wmw.movieviewer.repository.UserRepository
import com.wmw.movieviewer.repository.UserRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    
    single { SharedPrefsManager() }
    single { MoviesRepositoryImpl() as MoviesRepository}
    single { UserRepositoryImpl(get()) as UserRepository }
}