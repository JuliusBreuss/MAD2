package com.example.movielist.navigation

sealed class Screens (val route: String) {
    object MainScreen: Screens("home")
    object DetailScreen: Screens("details")
    object FavoritesScreen: Screens("favorites")
}