package com.example.movielist.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movielist.menu.SimpleAppBar
import com.example.movielist.models.getMovies

@Composable
fun FavoriteScreen(navController: NavController) {
    val movies = getMovies()
    val favorites = listOf(movies[1], movies[3], movies[5])
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Column {
            SimpleAppBar(text = "Favorites", navController = navController)
            MyList(movies = favorites, navController = navController)
        }
    }
}