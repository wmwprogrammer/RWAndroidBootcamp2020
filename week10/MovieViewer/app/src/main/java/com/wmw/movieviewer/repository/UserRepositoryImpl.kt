package com.wmw.movieviewer.repository

import com.wmw.movieviewer.helper.SharedPrefsManager

class UserRepositoryImpl(private val sharedPrefsManager: SharedPrefsManager) : UserRepository {

    override fun setUserLoggedIn(isLoggedIn: Boolean) = sharedPrefsManager.setUserLoggedIn(isLoggedIn)

    override fun isUserLoggedIn() = sharedPrefsManager.isUserLoggedIn()
}