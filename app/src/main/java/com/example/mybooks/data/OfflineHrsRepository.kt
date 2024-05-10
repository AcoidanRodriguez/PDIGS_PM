package com.example.mybooks.data

import kotlinx.coroutines.flow.Flow

class OfflineHrsRepository(private val hrDao: HrDao): HrRepository {
    override fun getAllHrsStream(): Flow<List<Hr>> = hrDao.getAllHrs()

    override fun getTotalHrsStream(): Int = hrDao.getTotalHrs()

    override suspend fun insertHr(item: Hr) = hrDao.insert(item)

    override suspend fun deleteHr(item: Hr) = hrDao.delete(item)

    override suspend fun updateHr(item: Hr) = hrDao.update(item)
}
