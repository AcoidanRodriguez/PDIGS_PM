
package com.example.mybooks.data;
import kotlinx.coroutines.flow.Flow;
public interface MarketRepository {
    fun getAllMarketsStream(): Flow<List<Market>>

    fun getTotalMarketsStream(): Int

    suspend fun insertMarket(item: Market)

    suspend fun deleteMarket(item: Market)

    suspend fun updateMarket(item: Market)
}
