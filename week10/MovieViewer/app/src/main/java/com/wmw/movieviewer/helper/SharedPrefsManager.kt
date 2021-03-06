package com.wmw.movieviewer.helper

import android.content.Context
import com.wmw.movieviewer.App

class SharedPrefsManager(val context: Context) {
    private val prefs =
        context.getSharedPreferences("MovieViewerPreferences", Context.MODE_PRIVATE)

    fun setUserLoggedIn(isLoggedIn: Boolean) {
        prefs.edit().putBoolean("MovieViewerPreferences", isLoggedIn).apply()
    }

    fun isUserLoggedIn() = prefs.getBoolean("MovieViewerPreferences", false)
}