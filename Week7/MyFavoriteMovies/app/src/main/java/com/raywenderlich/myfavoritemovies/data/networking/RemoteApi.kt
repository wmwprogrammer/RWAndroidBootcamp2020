package com.raywenderlich.myfavoritemovies.data.networking

import com.raywenderlich.myfavoritemovies.App
import com.raywenderlich.myfavoritemovies.model.Failure
import com.raywenderlich.myfavoritemovies.model.Result
import com.raywenderlich.myfavoritemovies.model.Success
import com.raywenderlich.myfavoritemovies.model.response.MovieTopLevelResponse


const val BASE_URL = "https://www.myapifilms.com"

class RemoteApi(private val apiService: RemoteApiService) {

    suspend fun getMovie(movieName: String): Result<MovieTopLevelResponse> = try {
        val response = apiService.getMovie(App.getToken(), movieName)

        Success(response)
    } catch (error: Throwable) {
        Failure(error)
    }
}