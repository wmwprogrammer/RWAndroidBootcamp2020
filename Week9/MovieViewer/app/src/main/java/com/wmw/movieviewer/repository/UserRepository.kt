package com.wmw.movieviewer.repository

import com.wmw.movieviewer.helper.SharedPrefsManager

class UserRepository(private val sharedPrefsManager: SharedPrefsManager) {

    fun setUserLoggedIn(isLoggedIn: Boolean) = sharedPrefsManager.setUserLoggedIn(isLoggedIn)

    fun isUserLoggedIn() = sharedPrefsManager.isUserLoggedIn()
}