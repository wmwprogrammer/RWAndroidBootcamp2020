package com.raywenderlich.myfavoritemovies.repository

import com.raywenderlich.myfavoritemovies.helper.SharedPrefsManager

class UserRepository {
    private val sharedPrefsManager = SharedPrefsManager()

    fun setUserLoggedIn(isLoggedIn: Boolean) = sharedPrefsManager.setUserLoggedIn(isLoggedIn)

    fun isUserLoggedIn() = sharedPrefsManager.isUserLoggedIn()
}