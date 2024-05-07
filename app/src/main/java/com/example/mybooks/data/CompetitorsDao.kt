package com.example.mybooks.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CompetitorsDao {
    @Query("SELECT * from Competitors_table ORDER BY description ASC")
    fun getAllCompetitorss(): Flow<List<Competitors>>

    @Query("SELECT COUNT(*) FROM Competitors_table")
    fun getTotalCompetitors(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(competitors: Competitors)

    @Update
    suspend fun update(competitors: Competitors)

    @Delete
    suspend fun delete(competitors: Competitors)

    @Update
    suspend fun updateCompetitors(competitors: Competitors)
}