package com.example.drinkwatertracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Hydration::class],
    version = 1,
    exportSchema = false
)
abstract class HydrationDatabase : RoomDatabase()  {
    abstract fun hydrationDao(): HydrationDao

    companion object {
        @Volatile
        private var Instance: HydrationDatabase? = null

        fun getDatabase(context: Context): HydrationDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, HydrationDatabase::class.java, "hydration_database")
                    .build()
                    .also {
                        Instance = it
                    }
            }
        }
    }
}