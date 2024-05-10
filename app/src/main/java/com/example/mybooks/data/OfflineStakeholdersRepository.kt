package com.example.mybooks.data

import kotlinx.coroutines.flow.Flow

class OfflineStakeholdersRepository(private val stakeholdersDao: StakeholdersDao): StakeholdersRepository {
    override fun getAllStakeholdersStream(): Flow<List<Stakeholders>> = stakeholdersDao.getAllStakeholders()

    override fun getTotalStakeholdersStream(): Int = stakeholdersDao.getTotalStakeholders()

    override suspend fun insertStakeholders(item: Stakeholders) = stakeholdersDao.insert(item)

    override suspend fun deleteStakeholders(item: Stakeholders) = stakeholdersDao.delete(item)

    override suspend fun updateStakeholders(item: Stakeholders) = stakeholdersDao.update(item)
}