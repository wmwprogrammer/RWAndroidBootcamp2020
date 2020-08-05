package com.wmw.movieviewer.repository

interface UserRepository {
    fun setUserLoggedIn(isLoggedIn: Boolean)
    fun isUserLoggedIn(): Boolean
}