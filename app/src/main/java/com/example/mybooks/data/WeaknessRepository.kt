package com.example.mybooks.data

import kotlinx.coroutines.flow.Flow

interface WeaknessesRepository {
    fun getAllWeaknessesStream(): Flow<List<Weakness>>

    fun getTotalWeaknessesStream(): Int

    suspend fun insertWeakness(item: Weakness)

    suspend fun deleteWeakness(item: Weakness)

    suspend fun updateWeakness(item: Weakness)
}