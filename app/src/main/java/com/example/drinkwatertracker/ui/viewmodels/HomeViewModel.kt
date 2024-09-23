package com.example.drinkwatertracker.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drinkwatertracker.data.Hydration
import com.example.drinkwatertracker.data.HydrationRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(private val hydrationRepository: HydrationRepository) : ViewModel() {
    val homeUiState: StateFlow<HomeUiState> = hydrationRepository.find().map {
        HomeUiState(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
        initialValue = HomeUiState()
    )

    suspend fun delete(hydration: Hydration) {
        hydrationRepository.delete(hydration)
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class HomeUiState(val hydrationsList: List<Hydration> = listOf())