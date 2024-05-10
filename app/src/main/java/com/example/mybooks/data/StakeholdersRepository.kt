package com.example.mybooks.data;
import kotlinx.coroutines.flow.Flow;
public interface StakeholdersRepository {

    fun getAllStakeholdersStream(): Flow<List<Stakeholders>>

    fun getTotalStakeholdersStream(): Int

    suspend fun insertStakeholders(item: Stakeholders)

    suspend fun deleteStakeholders(item: Stakeholders)

    suspend fun updateStakeholders(item: Stakeholders)
}
