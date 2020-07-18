package com.raywenderlich.myfavoritemovies.model.response

import kotlinx.serialization.Serializable

@Serializable
data class MovieTopLevelResponse(val data: MoviesResultResponse, val about: AboutResultResponse)

@Serializable
data class AboutResultResponse(val version: String, val serverTime: String)

@Serializable
data class MoviesResultResponse(val movies: Array<MovieResponse>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MoviesResultResponse

        if (!movies.contentEquals(other.movies)) return false

        return true
    }

    override fun hashCode(): Int {
        return movies.contentHashCode()
    }
}

@Serializable
data class MovieResponse(
    var idIMDB: String,
    var releaseDate: String,
    var title: String,
    var plot: String,
    var genres: List<String>,
    var urlPoster: String
)