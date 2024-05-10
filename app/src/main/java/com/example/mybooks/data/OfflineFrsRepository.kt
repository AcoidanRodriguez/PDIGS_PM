package com.example.mybooks.data

import kotlinx.coroutines.flow.Flow

class OfflineFrsRepository(private val frDao: FrDao): FrRepository {
    override fun getAllFrsStream(): Flow<List<Fr>> = frDao.getAllFrs()

    override fun getTotalFrsStream(): Int = frDao.getTotalFrs()

    override suspend fun insertFr(item: Fr) = frDao.insert(item)

    override suspend fun deleteFr(item: Fr) = frDao.delete(item)

    override suspend fun updateFr(item: Fr) = frDao.update(item)
}
