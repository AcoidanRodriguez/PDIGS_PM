package com.example.mybooks.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ThreatDao {
    @Query("SELECT * from threats_table ORDER BY description ASC")
    fun getAllThreats(): Flow<List<Threat>>

    @Query("SELECT COUNT(*) FROM threats_table")
    fun getTotalThreats(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(threat: Threat)

    @Update
    suspend fun update(threat: Threat)

    @Delete
    suspend fun delete(threat: Threat)

    @Update
    suspend fun updateThreat(threat: Threat)
}
