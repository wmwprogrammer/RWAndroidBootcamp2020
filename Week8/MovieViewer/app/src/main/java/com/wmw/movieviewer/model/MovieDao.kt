package com.wmw.movieviewer.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

//    @Delete
//    suspend fun deleteMovies(vararg movie: Movie)

    @Query("DELETE FROM movies_table WHERE id = :movieId")
    suspend fun deleteMovieById(movieId: String)

    @Query("SELECT * FROM movies_table ORDER BY title ASC")
    fun getAllMoviesSortedByTitle(): LiveData<List<Movie>>

    @Query("SELECT * FROM movies_table WHERE id = :movieId")
    suspend fun getMovieById(movieId: String?): Movie
}