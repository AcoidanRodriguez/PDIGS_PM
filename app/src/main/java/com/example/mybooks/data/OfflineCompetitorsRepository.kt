package com.example.mybooks.data

import kotlinx.coroutines.flow.Flow

class OfflineCompetitorsRepository(private val competitorDao: CompetitorsDao): CompetitorsRepository {
    override fun getAllCompetitorsStream(): Flow<List<Competitors>> = competitorDao.getAllCompetitorss()

    override fun getTotalCompetitorsStream(): Int = competitorDao.getTotalCompetitors()

    override suspend fun insertCompetitors(item: Competitors) = competitorDao.insert(item)

    override suspend fun deleteCompetitors(item: Competitors) = competitorDao.delete(item)

    override suspend fun updateCompetitors(item: Competitors) = competitorDao.update(item)
}