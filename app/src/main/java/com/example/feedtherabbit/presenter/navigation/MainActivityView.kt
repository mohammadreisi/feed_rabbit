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
import com.example.feedtherabbit.presenter.view_model.MainViewModel
import com.example.feedtherabbit.presenter.views.viewgroup.GameRootView
import com.example.feedtherabbit.presenter.views.viewgroup.HomeRootView

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainActivityView(
    mainViewModel: MainViewModel
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
                    mainViewModel = mainViewModel,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable
                ) { rabbitElementName, musicIconElementName ->
                    navController.navigate("${Destinations.Game.route}/$rabbitElementName/$musicIconElementName")
                }
            }

            composable(route = "${Destinations.Game.route}/{rabbit_element_name}/{music_icon_element_name}",
                arguments = listOf(
                    navArgument("rabbit_element_name") {
                        type = NavType.StringType
                    },
                    navArgument("music_icon_element_name") {
                        type = NavType.StringType
                    }
                )) { navBackStackEntry ->
                val rabbitElementName = navBackStackEntry.arguments?.getString("rabbit_element_name") ?: ""
                val musicIconElementName = navBackStackEntry.arguments?.getString("music_icon_element_name") ?: ""
                GameRootView(
                    Modifier
                        .fillMaxSize(),
                    rabbitSharedElementName = rabbitElementName,
                    musicIconSharedElementName = musicIconElementName,
                    mainViewModel = mainViewModel,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable
                )
            }
        }
    }
}