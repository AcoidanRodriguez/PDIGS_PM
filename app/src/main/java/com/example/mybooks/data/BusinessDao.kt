package com.example.mybooks.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BusinessDao {
    @Query("SELECT * from Businesses_table ORDER BY description ASC")
    fun getAllBusinesses(): Flow<List<Business>>

    @Query("SELECT COUNT(*) FROM Businesses_table")
    fun getTotalBusinesses(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(business: Business)

    @Update
    suspend fun update(business: Business)

    @Delete
    suspend fun delete(business: Business)

    @Update
    suspend fun updateBusiness(business: Business)
}