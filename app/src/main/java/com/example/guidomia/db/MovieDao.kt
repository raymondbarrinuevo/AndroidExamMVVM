package com.example.guidomia.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import android.provider.SyncStateContract.Helpers.update

import android.provider.SyncStateContract.Helpers.insert


@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_data_table")
    fun getAll(): Flow<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllData(movies: List<Movie>): List<Long>

    @Query("SELECT COUNT(trackId) FROM movie_data_table")
    suspend fun count(): Int



}