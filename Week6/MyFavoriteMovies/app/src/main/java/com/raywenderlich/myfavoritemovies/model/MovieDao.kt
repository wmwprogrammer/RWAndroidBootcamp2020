package com.raywenderlich.myfavoritemovies.model

import androidx.room.*

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movie>)

    @Delete
    fun deleteMovies(vararg movie: Movie)

    @Query("DELETE FROM movies_table WHERE id = :movieId")
    fun deleteMovieById(movieId: Int)

    @Query("SELECT * FROM movies_table ORDER BY title ASC")
    fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM movies_table WHERE id = :movieId")
    fun getMovieById(movieId: Int?): Movie
}