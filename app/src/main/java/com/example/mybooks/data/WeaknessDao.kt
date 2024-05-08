package com.example.mybooks.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface WeaknessDao {
    @Query("SELECT * from weaknesses_table ORDER BY description ASC")
    fun getAllWeaknesses(): Flow<List<Weakness>>

    @Query("SELECT COUNT(*) FROM weaknesses_table")
    fun getTotalWeaknesses(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(weakness: Weakness)

    @Update
    suspend fun update(weakness: Weakness)

    @Delete
    suspend fun delete(weakness: Weakness)

    @Update
    suspend fun updateWeakness(weakness: Weakness)
}
