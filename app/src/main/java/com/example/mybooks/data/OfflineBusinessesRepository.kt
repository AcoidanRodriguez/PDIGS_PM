package com.example.mybooks.data

import kotlinx.coroutines.flow.Flow

class OfflineBusinessesRepository(private val businessDao: BusinessDao): BusinessRepository {
    override fun getAllBusinessStream(): Flow<List<Business>> = businessDao.getAllBusinesses()

    override fun getTotalBusinessStream(): Int = businessDao.getTotalBusinesses()

    override suspend fun insertBusiness(item: Business) = businessDao.insert(item)

    override suspend fun deleteBusiness(item: Business) = businessDao.delete(item)

    override suspend fun updateBusiness(item: Business) = businessDao.update(item)
}