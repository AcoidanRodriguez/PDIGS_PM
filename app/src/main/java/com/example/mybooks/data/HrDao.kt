package com.example.mybooks.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface HrDao {
    @Query("SELECT * from hr_table ORDER BY name ASC")
    fun getAllHrs(): Flow<List<Hr>>

    @Query("SELECT COUNT(*) FROM hr_table")
    fun getTotalHrs(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(hr: Hr)

    @Update
    suspend fun update(hr: Hr)

    @Delete
    suspend fun delete(hr: Hr)

    @Update
    suspend fun updateHr(hr: Hr)
}
