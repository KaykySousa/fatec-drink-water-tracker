package com.example.drinkwatertracker.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.drinkwatertracker.data.Hydration
import com.example.drinkwatertracker.data.HydrationRepository

data class HydrationDetails (
    val id: Int = 0,
    val mililiters: String = "",
)

data class HydrationUiState (
    val hydrationDetails: HydrationDetails = HydrationDetails(),
    val isValid: Boolean = false
)

fun HydrationDetails.toHydration(): Hydration {
    return Hydration(
        id = id,
        mililiters = mililiters.toIntOrNull() ?: 0,
    )
}

fun Hydration.toHydrationUiState(isValid: Boolean = false): HydrationUiState = HydrationUiState(
    hydrationDetails = this.toItemDetails(),
    isValid = isValid
)

fun Hydration.toItemDetails(): HydrationDetails = HydrationDetails(
    id = id,
    mililiters = mililiters.toString(),
)

class HydrationCreateViewModel(private val hydrationRepository: HydrationRepository) : ViewModel() {
    var hydrationUiState by mutableStateOf(HydrationUiState())
        private set

    fun updateUiState(hydrationDetails: HydrationDetails) {
        hydrationUiState = HydrationUiState(
            hydrationDetails = hydrationDetails,
            isValid = hydrationDetails.mililiters.isNotEmpty()
        )
    }

    suspend fun insert() {
        if (!hydrationUiState.isValid) return
        val hydration = hydrationUiState.hydrationDetails.toHydration()
        hydrationRepository.insert(hydration)
    }
}