package com.example.mybooks.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MrDao {
    @Query("SELECT * from mr_table ORDER BY name ASC")
    fun getAllMrs(): Flow<List<Mr>>

    @Query("SELECT COUNT(*) FROM mr_table")
    fun getTotalMrs(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(mr: Mr)

    @Update
    suspend fun update(mr: Mr)

    @Delete
    suspend fun delete(mr: Mr)

    @Update
    suspend fun updateHr(mr: Mr)
}
