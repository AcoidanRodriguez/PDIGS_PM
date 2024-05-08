package com.example.mybooks.data

import kotlinx.coroutines.flow.Flow

interface FrRepository {
    fun getAllFrsStream(): Flow<List<Fr>>

    fun getTotalFrsStream(): Int

    suspend fun insertFr(item: Fr)

    suspend fun deleteFr(item: Fr)

    suspend fun updateFr(item: Fr)
}