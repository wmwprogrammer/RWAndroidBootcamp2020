package com.raywenderlich.myfavoritemovies.helper

import android.content.Context
import com.raywenderlich.myfavoritemovies.App

class SharedPrefsManager {
    private val context = App.getAppContext()
    private val prefs =
        context.getSharedPreferences("FavoriteMoviePreferences", Context.MODE_PRIVATE)

    fun setUserLoggedIn(isLoggedIn: Boolean) {
        prefs.edit().putBoolean("FavoriteMoviePreferences", isLoggedIn).apply()
    }

    fun isUserLoggedIn() = prefs.getBoolean("FavoriteMoviePreferences", false)
}