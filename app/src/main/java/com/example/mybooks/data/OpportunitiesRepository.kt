package com.example.mybooks.data

import kotlinx.coroutines.flow.Flow

interface OpportunitiesRepository {
    fun getAllOpportunitiesStream(): Flow<List<Opportunity>>

    fun getTotalOpportunitiesStream(): Int

    suspend fun insertOpportunity(item: Opportunity)

    suspend fun deleteOpportunity(item: Opportunity)

    suspend fun updateOpportunity(item: Opportunity)
}