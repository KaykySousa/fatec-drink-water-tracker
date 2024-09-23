package com.example.drinkwatertracker.data

import kotlinx.coroutines.flow.Flow

interface HydrationRepository {
    fun find(): Flow<List<Hydration>>

    fun findOne(id: Int): Flow<Hydration?>

    suspend fun insert(hydration: Hydration)

    suspend fun update(hydration: Hydration)

    suspend fun delete(hydration: Hydration)
}