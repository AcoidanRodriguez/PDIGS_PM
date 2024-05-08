package com.example.mybooks.data;
import kotlinx.coroutines.flow.Flow;
public interface CompetitorsRepository {
    fun getAllCompetitorsStream(): Flow<List<Competitors>>

    fun getTotalCompetitorsStream(): Int

    suspend fun insertCompetitors(item: Competitors)

    suspend fun deleteCompetitors(item: Competitors)

    suspend fun updateCompetitors(item: Competitors)
}
