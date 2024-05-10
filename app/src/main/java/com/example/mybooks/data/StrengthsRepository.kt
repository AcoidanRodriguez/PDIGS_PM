package com.example.mybooks.data

import kotlinx.coroutines.flow.Flow

interface StrengthsRepository {
    fun getAllStrengthsStream(): Flow<List<Strength>>

    fun getTotalStrengthsStream(): Int

    suspend fun insertStrength(item: Strength)

    suspend fun deleteStrength(item: Strength)

    suspend fun updateStrength(item: Strength)
}