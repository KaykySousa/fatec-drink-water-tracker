package com.example.drinkwatertracker.data

import android.content.Context

interface AppContainer {
    val hydrationRepository: HydrationRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val hydrationRepository by lazy {
        OfflineHydrationRepository(HydrationDatabase.getDatabase(context).hydrationDao())
    }
}