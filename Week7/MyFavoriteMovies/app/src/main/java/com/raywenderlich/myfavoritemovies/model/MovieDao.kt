package com.raywenderlich.myfavoritemovies.model

import androidx.room.*

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Delete
    suspend fun deleteMovies(vararg movie: Movie)

    @Query("DELETE FROM movies_table WHERE id = :movieId")
    suspend fun deleteMovieById(movieId: String)

    @Query("SELECT * FROM movies_table ORDER BY title ASC")
    suspend fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM movies_table WHERE id = :movieId")
    suspend fun getMovieById(movieId: String?): Movie
}