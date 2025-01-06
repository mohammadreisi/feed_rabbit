package com.example.savethebird.ui.views.viewgroup

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.savethebird.ui.navigation.Destinations

@Composable
fun MainActivityView() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destinations.Home.route
    ) {
        composable(route = Destinations.Home.route) {
            HomeRootView(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                navController.navigate(Destinations.Game.route)
            }
        }

        composable(route = Destinations.Game.route) {
            HomeRootView(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                navController.navigate(Destinations.Game.route)
            }
        }
    }
}