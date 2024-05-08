package com.example.mybooks.data

import kotlinx.coroutines.flow.Flow

class OfflineIncomesRepository(private val incomeDao: IncomeDao): IncomeRepository {
    override fun getAllIncomesStream(): Flow<List<Income>> = incomeDao.getAllIncome()

    override fun getTotalIncomesStream(): Int = incomeDao.getTotalIncome()

    override suspend fun insertIncome(item: Income) = incomeDao.insert(item)

    override suspend fun deleteIncome(item: Income) = incomeDao.delete(item)

    override suspend fun updateIncome(item: Income) = incomeDao.update(item)
}