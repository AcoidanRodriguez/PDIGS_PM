package com.example.mybooks.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface IncomeDao {
    @Query("SELECT * from Incomes_table ORDER BY description ASC")
    fun getAllIncome(): Flow<List<Income>>

    @Query("SELECT COUNT(*) FROMIncome_table")
    fun getTotalIncome(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(income:Income)

    @Update
    suspend fun update(income:Income)

    @Delete
    suspend fun delete(income:Income)

    @Update
    suspend fun updateIncome(income:Income)
}