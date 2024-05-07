package com.example.mybooks.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MarketDao {
    @Query("SELECT * from Markets_table ORDER BY description ASC")
    fun getAllMarkets(): Flow<List<Market>>

    @Query("SELECT COUNT(*) FROM Markets_table")
    fun getTotalMarkets(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(market: Market)

    @Update
    suspend fun update(market: Market)

    @Delete
    suspend fun delete(market: Market)

    @Update
    suspend fun updateMarket(market: Market)
}