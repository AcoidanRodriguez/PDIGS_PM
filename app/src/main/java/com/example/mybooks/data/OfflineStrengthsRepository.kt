package com.example.mybooks.data

import kotlinx.coroutines.flow.Flow

class OfflineStrengthsRepository(private val strengthDao: StrengthDao): StrengthsRepository {
    override fun getAllStrengthsStream(): Flow<List<Strength>> = strengthDao.getAllStrengths()

    override fun getTotalStrengthsStream(): Int = strengthDao.getTotalStrengths()

    override suspend fun insertStrength(item: Strength) = strengthDao.insert(item)

    override suspend fun deleteStrength(item: Strength) = strengthDao.delete(item)

    override suspend fun updateStrength(item: Strength) = strengthDao.update(item)
}
