package com.example.eventmaster.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eventmaster.ui.screen.AddCategoryScreen
import com.example.eventmaster.ui.screen.AddEventScreen
import com.example.eventmaster.ui.screen.CategoryDetailScreen
import com.example.eventmaster.ui.screen.EventDetailScreen
import com.example.eventmaster.ui.screen.HomeScreen
import com.example.eventmaster.ui.viewmodel.EventViewModel

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val viewModel: EventViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController, viewModel)
        }
        composable("category/{categoryId}") { backStackEntry ->
            val categoryId = backStackEntry.arguments
                ?.getString("categoryId")
                ?.toIntOrNull() ?: 0

            CategoryDetailScreen(
                navController = navController,
                viewModel = viewModel,
                categoryId = categoryId
            )
        }
        composable("event/{eventId}") { backStackEntry ->

            val eventId = backStackEntry.arguments
                ?.getString("eventId")
                ?.toIntOrNull() ?: 0

            EventDetailScreen(
                navController = navController,
                viewModel = viewModel,
                eventId = eventId
            )
        }

        // -------------------------
        // AGREGAR CATEGORÍA
        // -------------------------
        composable("add_category") {
            AddCategoryScreen(navController, viewModel)
        }

        // -------------------------
        // AGREGAR EVENTO
        // -------------------------
        composable("add_event/{categoryId}") { backStackEntry ->

            val categoryId = backStackEntry.arguments
                ?.getString("categoryId")
                ?.toIntOrNull() ?: 0

            AddEventScreen(
                navController = navController,
                viewModel = viewModel,
                categoryId = categoryId
            )
        }
    }
}