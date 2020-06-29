package com.raywenderlich.myfavoritemovies.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {
    @Insert
    fun insert(movie: Movie)

    @Delete
    fun deleteMovies(vararg movie: Movie)

    @Query("SELECT * FROM movies_table ORDER BY title ASC")
    fun getAllMovies(): LiveData<List<Movie>>
}