package com.example.mybooks.data

import kotlinx.coroutines.flow.Flow

interface IrRepository {
    fun getAllIrsStream(): Flow<List<Ir>>

    fun getTotalIrsStream(): Int

    suspend fun insertIr(item: Ir)

    suspend fun deleteIr(item: Ir)

    suspend fun updateIr(item: Ir)
}