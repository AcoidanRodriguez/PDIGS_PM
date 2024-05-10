package com.example.mybooks.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FrDao {
    @Query("SELECT * from fr_table ORDER BY name ASC")
    fun getAllFrs(): Flow<List<Fr>>

    @Query("SELECT COUNT(*) FROM fr_table")
    fun getTotalFrs(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(fr: Fr)

    @Update
    suspend fun update(fr: Fr)

    @Delete
    suspend fun delete(fr: Fr)

    @Update
    suspend fun updateHr(fr: Fr)
}
