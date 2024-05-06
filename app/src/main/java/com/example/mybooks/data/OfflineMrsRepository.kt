package com.example.mybooks.data

import kotlinx.coroutines.flow.Flow

class OfflineMrsRepository(private val mrDao: MrDao): MrRepository {
    override fun getAllMrsStream(): Flow<List<Mr>> = mrDao.getAllMrs()

    override fun getTotalMrsStream(): Int = mrDao.getTotalMrs()

    override suspend fun insertMr(item: Mr) = mrDao.insert(item)

    override suspend fun deleteMr(item: Mr) = mrDao.delete(item)

    override suspend fun updateMr(item: Mr) = mrDao.update(item)
}
