package com.example.guidomia.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(movie: Movie): Long

    @Query("SELECT * FROM movie_data_table")
    fun getAll(): Flow<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllData(movies: List<Movie>): List<Long>
}