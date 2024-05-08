package com.example.mybooks.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface OpportunityDao {
    @Query("SELECT * from opportunities_table ORDER BY description ASC")
    fun getAllOpportunities(): Flow<List<Opportunity>>

    @Query("SELECT COUNT(*) FROM opportunities_table")
    fun getTotalOpportunities(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(opportunity: Opportunity)

    @Update
    suspend fun update(opportunity: Opportunity)

    @Delete
    suspend fun delete(opportunity: Opportunity)

    @Update
    suspend fun updateOpportunity(opportunity: Opportunity)
}
