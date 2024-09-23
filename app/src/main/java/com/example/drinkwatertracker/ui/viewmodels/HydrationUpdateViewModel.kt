package com.example.drinkwatertracker.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.drinkwatertracker.data.HydrationRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class HydrationUpdateViewModel(
    savedStateHandle: SavedStateHandle,
    private val hydrationRepository: HydrationRepository
): ViewModel() {
    var hydrationUiState by mutableStateOf(HydrationUiState())
        private set

    private val hydrationId: Int = checkNotNull(savedStateHandle["hydrationId"])

    fun updateUiState(hydrationDetails: HydrationDetails) {
        hydrationUiState = HydrationUiState(
            hydrationDetails = hydrationDetails,
            isValid = hydrationDetails.mililiters.isNotEmpty()
        )
    }

    suspend fun update() {
        if (!hydrationUiState.isValid) return
        hydrationRepository.update(hydrationUiState.hydrationDetails.toHydration())
    }

    init {
        viewModelScope.launch {
            hydrationUiState = hydrationRepository.findOne(hydrationId)
                .filterNotNull()
                .first()
                .toHydrationUiState(true)
        }
    }
}