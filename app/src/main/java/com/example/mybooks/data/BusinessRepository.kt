package com.example.mybooks.data

import kotlinx.coroutines.flow.Flow

interface BusinessRepository {
    fun getAllBusinessStream(): Flow<List<Business>>

    fun getTotalBusinessStream(): Int

    suspend fun insertBusiness(item: Business)

    suspend fun deleteBusiness(item: Business)

    suspend fun updateBusiness(item: Business)
}
