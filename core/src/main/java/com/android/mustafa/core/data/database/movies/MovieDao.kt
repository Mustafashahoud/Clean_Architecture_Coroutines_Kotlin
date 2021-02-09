package com.android.mustafa.core.data.database.movies

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movie: List<Movie>)

    @Query("SELECT * FROM Movie")
    suspend fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM Movie WHERE page in (:pages) ORDER BY id")
    suspend fun getMoviesForPages(pages: List<Int>): List<Movie>

    @Query("SELECT * FROM Movie WHERE page = :page")
    suspend fun getMoviesPerPage(page: Int): List<Movie>

    @Query("DELETE FROM Movie WHERE id = :id")
    suspend fun deleteMovie(id: Int)

    @Query("DELETE FROM Movie WHERE page = :page")
    suspend fun deleteMoviesPerPage(page: Int)

    @Query("DELETE FROM Movie")
    suspend fun deleteAll()

}
