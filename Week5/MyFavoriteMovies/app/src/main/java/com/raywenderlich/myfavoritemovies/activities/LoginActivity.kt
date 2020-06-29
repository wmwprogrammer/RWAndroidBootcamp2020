package com.raywenderlich.myfavoritemovies.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.raywenderlich.myfavoritemovies.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPrefs =
            getSharedPreferences(MainActivity.LOGGED_IN_STATUS_KEY, Context.MODE_PRIVATE)
        val isLoggedIn = sharedPrefs.getBoolean(MainActivity.IS_LOGGED_IN, false)

        if (isLoggedIn) startMainActivity()

        loginButton.setOnClickListener {
            if (validateData()) {
                sharedPrefs.edit().putBoolean(MainActivity.IS_LOGGED_IN, true).apply()
            }
        }
    }

    private fun validateData(): Boolean {
        if (usernameEditText.text.isEmpty() || passwordEditText.text.isEmpty()) {
            Toast.makeText(
                this, "Username/Password cannot be empty. Please fill out all fields",
                Toast.LENGTH_LONG
            ).show()
            return false
        } else if (usernameEditText.text.length < 8 || passwordEditText.text.length < 8) {
            Toast.makeText(
                this, "Username/Password must be more than 8 or greater characters",
                Toast.LENGTH_LONG
            ).show()
            return false
        } else {
            startMainActivity()
            return true
        }
    }

    private fun startMainActivity() {
        val login = Intent(this, MainActivity::class.java)
        startActivity(login)
    }
}