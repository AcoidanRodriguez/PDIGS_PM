package com.example.mybooks.data

import kotlinx.coroutines.flow.Flow

class OfflineWeaknessesRepository(private val weaknessDao: WeaknessDao): WeaknessesRepository {
    override fun getAllWeaknessesStream(): Flow<List<Weakness>> = weaknessDao.getAllWeaknesses()

    override fun getTotalWeaknessesStream(): Int = weaknessDao.getTotalWeaknesses()

    override suspend fun insertWeakness(item: Weakness) = weaknessDao.insert(item)

    override suspend fun deleteWeakness(item: Weakness) = weaknessDao.delete(item)

    override suspend fun updateWeakness(item: Weakness) = weaknessDao.update(item)
}
