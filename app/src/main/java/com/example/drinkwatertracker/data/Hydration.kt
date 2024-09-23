package com.example.drinkwatertracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hydrations")
class Hydration (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val mililiters: Int
)
