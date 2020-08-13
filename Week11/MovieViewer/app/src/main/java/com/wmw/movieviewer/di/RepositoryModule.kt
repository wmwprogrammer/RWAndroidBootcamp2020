package com.wmw.movieviewer.di

import com.wmw.movieviewer.helper.SharedPrefsManager
import com.wmw.movieviewer.repository.MoviesRepository
import com.wmw.movieviewer.repository.MoviesRepositoryImpl
import com.wmw.movieviewer.repository.UserRepository
import com.wmw.movieviewer.repository.UserRepositoryImpl
import com.wmw.movieviewer.validators.CredentialsValidator
import com.wmw.movieviewer.validators.CredentialsValidatorImpl
import org.koin.dsl.module

val repositoryModule = module {
    
    single { SharedPrefsManager(get()) }
    single { MoviesRepositoryImpl(get(), get()) as MoviesRepository}
    single { UserRepositoryImpl(get()) as UserRepository }
    single { CredentialsValidatorImpl() as CredentialsValidator }
}