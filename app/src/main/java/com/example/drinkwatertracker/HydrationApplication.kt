package com.example.drinkwatertracker

import android.app.Application
import com.example.drinkwatertracker.data.AppContainer
import com.example.drinkwatertracker.data.AppDataContainer

class HydrationApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}