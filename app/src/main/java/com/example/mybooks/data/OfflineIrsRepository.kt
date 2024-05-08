package com.example.mybooks.data

import kotlinx.coroutines.flow.Flow

class OfflineIrsRepository(private val irDao: IrDao): IrRepository {
    override fun getAllIrsStream(): Flow<List<Ir>> = irDao.getAllIrs()

    override fun getTotalIrsStream(): Int = irDao.getTotalIrs()

    override suspend fun insertIr(item: Ir) = irDao.insert(item)

    override suspend fun deleteIr(item: Ir) = irDao.delete(item)

    override suspend fun updateIr(item: Ir) = irDao.update(item)
}
