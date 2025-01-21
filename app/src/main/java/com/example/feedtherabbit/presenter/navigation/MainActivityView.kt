package com.example.feedtherabbit.presenter.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.savethebird.R
import com.example.feedtherabbit.presenter.view_model.GameViewModel
import com.example.feedtherabbit.presenter.view_model.HomeViewModel
import com.example.feedtherabbit.presenter.views.viewgroup.GameRootView
import com.example.feedtherabbit.presenter.views.viewgroup.HomeRootView

@Composable
fun MainActivityView(
    homeViewModel: HomeViewModel,
    gameViewModel: GameViewModel
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destinations.Home.route
    ) {
        composable(route = Destinations.Home.route) {
            HomeRootView(
                modifier = Modifier
                    .fillMaxSize()
                    .paint(
                        painterResource(R.drawable.ms2),
                        contentScale = ContentScale.FillBounds
                    ),
                homeViewModel = homeViewModel
            ) {
                navController.navigate(Destinations.Game.route)
            }
        }

        composable(route = Destinations.Game.route) {
            GameRootView(
                Modifier
                    .fillMaxSize()
            ) {
                navController.navigate(Destinations.Home.route)
            }
        }
    }
}