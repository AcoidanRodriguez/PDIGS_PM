package com.example.mybooks.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface IrDao {
    @Query("SELECT * from ir_table ORDER BY name ASC")
    fun getAllIrs(): Flow<List<Ir>>

    @Query("SELECT COUNT(*) FROM ir_table")
    fun getTotalIrs(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(ir: Ir)

    @Update
    suspend fun update(ir: Ir)

    @Delete
    suspend fun delete(ir: Ir)

    @Update
    suspend fun updateIr(ir: Ir)
}
