package com.example.mybooks.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface StakeholdersDao {
    @Query("SELECT * from Stakeholders_table ORDER BY description ASC")
    fun getAllStakeholders(): Flow<List<Stakeholders>>

    @Query("SELECT COUNT(*) FROMStakeholders_table")
    fun getTotalStakeholders(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(stakeholders:Stakeholders)

    @Update
    suspend fun update(stakeholders:Stakeholders)

    @Delete
    suspend fun delete(stakeholders:Stakeholders)

    @Update
    suspend fun updateStakeholders(stakeholders:Stakeholders)
}