package com.example.mybooks.data

import kotlinx.coroutines.flow.Flow

interface ThreatsRepository {
    fun getAllThreatsStream(): Flow<List<Threat>>

    fun getTotalThreatsStream(): Int

    suspend fun insertThreat(item: Threat)

    suspend fun deleteThreat(item: Threat)

    suspend fun updateThreat(item: Threat)
}