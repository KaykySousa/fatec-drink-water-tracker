package com.example.drinkwatertracker.data

import kotlinx.coroutines.flow.Flow

class OfflineHydrationRepository(private val hydrationDao: HydrationDao) : HydrationRepository {
    override fun find(): Flow<List<Hydration>> = hydrationDao.find()

    override fun findOne(id: Int): Flow<Hydration?> = hydrationDao.findOne(id)

    override suspend fun insert(hydration: Hydration) = hydrationDao.insert(hydration)

    override suspend fun update(hydration: Hydration) = hydrationDao.update(hydration)

    override suspend fun delete(hydration: Hydration) = hydrationDao.delete(hydration)
}