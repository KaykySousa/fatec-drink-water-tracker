package com.example.drinkwatertracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.drinkwatertracker.ui.screens.CreateScreen
import com.example.drinkwatertracker.ui.screens.HomeScreen
import com.example.drinkwatertracker.ui.screens.UpdateScreen

@Composable
fun InventoryNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "home",
    ) {
        composable(route = "home") {
            HomeScreen(navController = navController)
        }

        composable(route = "create") {
            CreateScreen(navController = navController)
        }

        composable(route = "update/{hydrationId}", arguments = listOf(navArgument("hydrationId") {
            type = NavType.IntType
        })) {
            UpdateScreen(navController = navController)
        }
    }
}