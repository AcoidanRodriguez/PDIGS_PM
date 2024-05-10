package com.example.mybooks.data;
import kotlinx.coroutines.flow.Flow;
public interface IncomeRepository {
    fun getAllIncomesStream(): Flow<List<Income>>

    fun getTotalIncomesStream(): Int

    suspend fun insertIncome(item: Income)

    suspend fun deleteIncome(item: Income)

    suspend fun updateIncome(item: Income)
}