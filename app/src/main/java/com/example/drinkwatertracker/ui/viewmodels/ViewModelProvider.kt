package com.example.drinkwatertracker.ui.viewmodels

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.drinkwatertracker.HydrationApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HydrationCreateViewModel(
                hydrationRepository = hydrationApplication().container.hydrationRepository
            )
        }

        initializer {
            HomeViewModel(
                hydrationRepository = hydrationApplication().container.hydrationRepository
            )
        }

        initializer {
            HydrationUpdateViewModel(
                this.createSavedStateHandle(),
                hydrationRepository = hydrationApplication().container.hydrationRepository
            )
        }
    }
}

fun CreationExtras.hydrationApplication(): HydrationApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as HydrationApplication)