package com.example.drinkwatertracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.drinkwatertracker.data.Hydration
import com.example.drinkwatertracker.ui.theme.DrinkWaterTrackerTheme
import com.example.drinkwatertracker.ui.viewmodels.AppViewModelProvider
import com.example.drinkwatertracker.ui.viewmodels.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val homeUiState by viewModel.homeUiState.collectAsState()

    val coroutineScope = rememberCoroutineScope()

    fun handleDelete(hydration: Hydration) {
        coroutineScope.launch {
            viewModel.delete(hydration)
        }
    }

    fun handleUpdate(hydration: Hydration) {
        coroutineScope.launch {
            navController.navigate("update/${hydration.id}")
        }
    }

    DrinkWaterTrackerTheme(false) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            floatingActionButton = {
                AddWater(onClick = { navController.navigate("create") })
            }
        ) { innerPadding ->
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(top = 48.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = "${homeUiState.hydrationsList.sumOf { it.mililiters }} mL",
                    color = Color(0xFF0EA5E9),
                    fontSize = 72.sp,
                    fontWeight = FontWeight.Bold,
                )
                LazyColumn (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                ) {

                    items(homeUiState.hydrationsList) { hydration ->
                        WaterRegister(
                            hydration = hydration,
                            onUpdate = { handleUpdate(hydration) },
                            onDelete = { handleDelete(hydration) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AddWater(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick, shape = CircleShape, containerColor = Color(0xFF0EA5E9), contentColor = Color.White) {
        Icon(Icons.Default.Add, contentDescription = "Adicionar água")
    }
}

@Composable
fun WaterRegister(
    hydration: Hydration,
    onUpdate: () -> Unit,
    onDelete: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column {
//            Text(
//                text = "10:00",
//                color = Color(0xFF1F2937),
//                fontSize = 12.sp,
//            )
            Text(
                text = "${hydration.mililiters} mL",
                color = Color(0xFF0EA5E9),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Row {
            FilledTonalIconButton (
                onClick = onUpdate,
                colors = IconButtonColors(
                    containerColor = Color(0xFFE0F2FE),
                    contentColor = Color(0xFF0EA5E9),
                    disabledContentColor = Color.Unspecified,
                    disabledContainerColor = Color.Unspecified
                )
            ) {
                Icon(Icons.Default.Create, contentDescription = "Editar")
            }
            FilledTonalIconButton (
                onClick = onDelete,
                colors = IconButtonColors(
                    containerColor = Color(0xFFE0F2FE),
                    contentColor = Color(0xFF0EA5E9),
                    disabledContentColor = Color.Unspecified,
                    disabledContainerColor = Color.Unspecified
                )
            ) {
                Icon(Icons.Default.Delete, contentDescription = "Excluir")
            }
        }
    }
}