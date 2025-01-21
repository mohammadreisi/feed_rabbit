package com.example.feedtherabbit.presenter.navigation

sealed class Destinations (val route: String) {
    data object Home : Destinations("home")
    data object Game : Destinations("game")
}