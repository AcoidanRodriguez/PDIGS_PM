package com.example.mybooks.data

import kotlinx.coroutines.flow.Flow

interface MrRepository {
    fun getAllMrsStream(): Flow<List<Mr>>

    fun getTotalMrsStream(): Int

    suspend fun insertMr(item: Mr)

    suspend fun deleteMr(item: Mr)

    suspend fun updateMr(item: Mr)
}