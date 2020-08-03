package com.wmw.movieviewer.model

import androidx.lifecycle.LiveData
import androidx.room.*
import org.koin.core.KoinComponent

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Delete
    suspend fun deleteMovie(vararg movie: Movie)

    @Query("DELETE FROM movies_table WHERE id = :movieId")
    suspend fun deleteMovieById(movieId: String)

    @Query("SELECT * FROM movies_table ORDER BY ranking ASC")
    fun getAllMoviesSortedByTitle(): LiveData<List<Movie>>

    @Query("SELECT * FROM movies_table WHERE id = :movieId")
    suspend fun getMovieById(movieId: String?): Movie
}