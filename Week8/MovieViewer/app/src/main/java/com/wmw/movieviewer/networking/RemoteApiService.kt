package com.wmw.movieviewer.networking

import com.wmw.movieviewer.model.response.MovieTopLevelResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApiService {

    @GET("/imdb/idIMDB")
    suspend fun getMovie(
        @Query("token") token: String,
        @Query("title") title: String
    ): MovieTopLevelResponse

    /**
     * This will retreive the top number of movies from the remote api
     * @param token is the api token to send to the server
     * @param start First film to retrieve: defaults to 1
     * @param end Last film to retrieve. If not reported, the film will recover the start parameter: defaults to 10
     * @param data 0: Retrieves the basic information about the film (chart/top)
     *             1: Retrieves all information about the film, with defaults options
     * @param format Format in which the data is returned. It's important that it be lowercase: defaults to 'json'
     */
    @GET("/imdb/top")
    suspend fun getTopMovies(
        @Query("token") token: String,
        @Query("start") start: String,
        @Query("end") end: String,
        @Query("data") data: String,
        @Query("format") format: String
    ): MovieTopLevelResponse
}