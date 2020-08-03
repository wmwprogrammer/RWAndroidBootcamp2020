package com.wmw.movieviewer.repository

import org.koin.core.KoinComponent

interface UserRepository : KoinComponent {
    fun setUserLoggedIn(isLoggedIn: Boolean)
    fun isUserLoggedIn(): Boolean
}