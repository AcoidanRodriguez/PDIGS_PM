package com.example.mybooks.data

import kotlinx.coroutines.flow.Flow

interface HrRepository {
    fun getAllHrsStream(): Flow<List<Hr>>

    fun getTotalHrsStream(): Int

    suspend fun insertHr(item: Hr)

    suspend fun deleteHr(item: Hr)

    suspend fun updateHr(item: Hr)
}