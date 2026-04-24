package com.example.eventmaster.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eventmaster.ui.screen.AddCategoryScreen
import com.example.eventmaster.ui.screen.AddEventScreen
import com.example.eventmaster.ui.screen.HomeScreen
import com.example.eventmaster.ui.viewmodel.EventViewModel

@Composable
fun AppNavigation(viewModel: EventViewModel = viewModel()) {

    val navController = rememberNavController()

    NavHost(navController, startDestination = "home") {

        composable("home") {
            HomeScreen(navController, viewModel)
        }

        composable("add_event") {
            AddEventScreen(navController, viewModel)
        }
        composable("add_category") {
            AddCategoryScreen(navController, viewModel)
        }
    }
}