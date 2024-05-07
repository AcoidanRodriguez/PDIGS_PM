package com.example.mybooks.data

import kotlinx.coroutines.flow.Flow

class OfflineMarketsRepository(private val MarketDao: MarketDao): MarketRepository {
    override fun getAllMarketsStream(): Flow<List<Market>> = MarketDao.getAllMarkets()

    override fun getTotalMarketsStream(): Int = MarketDao.getTotalMarkets()

    override suspend fun insertMarket(item: Market) = MarketDao.insert(item)

    override suspend fun deleteMarket(item: Market) = MarketDao.delete(item)

    override suspend fun updateMarket(item: Market) = MarketDao.update(item)
}