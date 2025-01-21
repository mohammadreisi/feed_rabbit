package com.example.feedtherabbit.presenter.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.feedtherabbit.R
import com.example.feedtherabbit.presenter.view_model.GameViewModel
import com.example.feedtherabbit.presenter.view_model.HomeViewModel
import com.example.feedtherabbit.presenter.views.viewgroup.GameRootView
import com.example.feedtherabbit.presenter.views.viewgroup.HomeRootView

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainActivityView(
    homeViewModel: HomeViewModel,
    gameViewModel: GameViewModel
) {
    SharedTransitionLayout {
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
                    homeViewModel = homeViewModel,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable
                ) { sharedElementName ->
                    navController.navigate("${Destinations.Game.route}/$sharedElementName")
                }
            }

            composable(route = "${Destinations.Game.route}/{element_name}", arguments = listOf(
                navArgument("element_name") {
                    type = NavType.StringType
                }
            )) { navBackStackEntry ->
                val sharedElementName = navBackStackEntry.arguments?.getString("element_name") ?: ""
                GameRootView(
                    Modifier
                        .fillMaxSize(),
                    shareElementName = sharedElementName,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable
                ) {
                    navController.navigate(Destinations.Home.route)
                }
            }
        }
    }
}