package com.wmw.movieviewer.validators

class CredentialsValidator {
    private lateinit var username: String
    private lateinit var password: String

    fun setCredentials(username: String, password: String) {
        this.username = username
        this.password = password
    }

    fun isUsernameValid() = username.length > 7

    fun isPasswordValid() = password.length > 7

    fun areCredentialsValid() = isUsernameValid() && isPasswordValid()
}