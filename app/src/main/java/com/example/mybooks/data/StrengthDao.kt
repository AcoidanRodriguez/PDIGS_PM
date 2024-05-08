package com.example.mybooks.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface StrengthDao {
    @Query("SELECT * from strengths_table ORDER BY description ASC")
    fun getAllStrengths(): Flow<List<Strength>>

    @Query("SELECT COUNT(*) FROM strengths_table")
    fun getTotalStrengths(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(strength: Strength)

    @Update
    suspend fun update(strength: Strength)

    @Delete
    suspend fun delete(strength: Strength)

    @Update
    suspend fun updateStrength(strength: Strength)
}
