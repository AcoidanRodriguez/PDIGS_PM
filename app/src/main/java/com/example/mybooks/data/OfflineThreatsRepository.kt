package com.example.mybooks.data

import kotlinx.coroutines.flow.Flow

class OfflineThreatsRepository(private val threatDao: ThreatDao): ThreatsRepository {
    override fun getAllThreatsStream(): Flow<List<Threat>> = threatDao.getAllThreats()

    override fun getTotalThreatsStream(): Int = threatDao.getTotalThreats()

    override suspend fun insertThreat(item: Threat) = threatDao.insert(item)

    override suspend fun deleteThreat(item: Threat) = threatDao.delete(item)

    override suspend fun updateThreat(item: Threat) = threatDao.update(item)
}
