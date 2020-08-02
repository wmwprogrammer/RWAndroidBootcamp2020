package com.wmw.movieviewer.networking

import com.wmw.movieviewer.App
import com.wmw.movieviewer.model.Failure
import com.wmw.movieviewer.model.Result
import com.wmw.movieviewer.model.Success
import com.wmw.movieviewer.model.response.MovieTopLevelResponse


const val BASE_URL = "https://www.myapifilms.com/"

class RemoteApi(private val apiService: RemoteApiService) {

    /**
     * Gets the top movies
     * @param start First film to retrieve
     * @param end Last film to retrieve. If not reported, the film will recover the start parameter
     */
    suspend fun getTopMovies(
        start: Int = 1,
        end: Int = 10
    ): Result<MovieTopLevelResponse> = try {
        val response = apiService.getTopMovies(App.getToken(), start, end, "1", "json")

        Success(response)
    } catch (error: Throwable) {
        Failure(error)
    }
}