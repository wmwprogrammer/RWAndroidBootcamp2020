package com.wmw.movieviewer.validators

class CredentialsValidatorImpl : CredentialsValidator {
    private lateinit var username: String
    private lateinit var password: String

    override fun setCredentials(username: String, password: String) {
        this.username = username
        this.password = password
    }

    override fun isUsernameValid() = username.length > 7

    override fun isPasswordValid() = password.length > 7

    override fun areCredentialsValid() = isUsernameValid() && isPasswordValid()
}