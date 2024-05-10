package com.example.mybooks.data

import kotlinx.coroutines.flow.Flow

class OfflineOpportunitiesRepository(private val opportunityDao: OpportunityDao): OpportunitiesRepository {
    override fun getAllOpportunitiesStream(): Flow<List<Opportunity>> = opportunityDao.getAllOpportunities()

    override fun getTotalOpportunitiesStream(): Int = opportunityDao.getTotalOpportunities()

    override suspend fun insertOpportunity(item: Opportunity) = opportunityDao.insert(item)

    override suspend fun deleteOpportunity(item: Opportunity) = opportunityDao.delete(item)

    override suspend fun updateOpportunity(item: Opportunity) = opportunityDao.update(item)
}
