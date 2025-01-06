package com.example.savethebird.ui.navigation

sealed class Destinations (val route: String) {
    data object Home : Destinations("home")
    data object Game : Destinations("game")
}