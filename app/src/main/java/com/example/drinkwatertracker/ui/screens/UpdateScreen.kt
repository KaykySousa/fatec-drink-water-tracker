package com.example.drinkwatertracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.drinkwatertracker.HydrationApplication
import com.example.drinkwatertracker.data.Hydration
import com.example.drinkwatertracker.ui.theme.DrinkWaterTrackerTheme
import com.example.drinkwatertracker.ui.viewmodels.AppViewModelProvider
import com.example.drinkwatertracker.ui.viewmodels.HydrationCreateViewModel
import com.example.drinkwatertracker.ui.viewmodels.HydrationUpdateViewModel
import kotlinx.coroutines.launch

@Composable
fun UpdateScreen(navController: NavHostController, viewModel: HydrationUpdateViewModel = viewModel(factory = AppViewModelProvider.Factory)) {

    val coroutineScope = rememberCoroutineScope()

    fun handleSubmit() {
        coroutineScope.launch {
            viewModel.update()
            navController.navigate("home")
        }
    }

    DrinkWaterTrackerTheme(false) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
        ) { innerPadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(top = 48.dp, start = 24.dp, end = 24.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = "Editar Hidratação",
                    color = Color(0xFF0EA5E9),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    value = viewModel.hydrationUiState.hydrationDetails.mililiters,
                    onValueChange = {
                        viewModel.updateUiState(
                            viewModel.hydrationUiState.hydrationDetails.copy(mililiters = it)
                        )
                    },
                    label = { Text(text = "Mililitros") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors().copy(
                        focusedIndicatorColor = Color(0xFF0369A1),
                        unfocusedIndicatorColor = Color(0xFF0EA5E9),
                        cursorColor = Color(0xFF0EA5E9),
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        focusedLabelColor = Color(0xFF0EA5E9)
                    )
                )
                Button(
                    onClick = { handleSubmit() },
                    colors = ButtonColors(
                        contentColor = Color.White,
                        containerColor = Color(0xFF0EA5E9),
                        disabledContentColor = Color.Unspecified,
                        disabledContainerColor = Color.Unspecified
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Salvar")
                }
            }
        }
    }
}