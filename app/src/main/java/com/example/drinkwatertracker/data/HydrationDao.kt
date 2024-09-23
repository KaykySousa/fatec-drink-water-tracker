package com.example.drinkwatertracker.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface HydrationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(hydration: Hydration)

    @Update
    suspend fun update(hydration: Hydration)

    @Delete
    suspend fun delete(hydration: Hydration)

    @Query("SELECT * FROM hydrations WHERE id = :id")
    fun findOne(id: Int): Flow<Hydration>

    @Query("SELECT * FROM hydrations")
    fun find(): Flow<List<Hydration>>
}