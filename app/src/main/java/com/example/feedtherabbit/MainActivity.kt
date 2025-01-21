package com.example.feedtherabbit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.feedtherabbit.presenter.navigation.MainActivityView
import com.example.feedtherabbit.presenter.theme.SaveTheBirdTheme
import com.example.feedtherabbit.presenter.view_model.GameViewModel
import com.example.feedtherabbit.presenter.view_model.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels()
    private val gameViewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            SaveTheBirdTheme {
                MainActivityView(
                    homeViewModel = homeViewModel,
                    gameViewModel = gameViewModel
                )
            }
        }
    }

}

