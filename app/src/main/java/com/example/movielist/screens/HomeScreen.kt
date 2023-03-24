package com.example.movielist.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movielist.menu.MainAppBar
import com.example.movielist.models.Movie
import com.example.movielist.models.getMovies
import com.example.movielist.navigation.Screens
import com.example.movielist.widgets.MovieRow

@Composable
fun HomeScreen(navController: NavController) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            MainAppBar(navController)
            MyList(navController)
        }
    }
}

@Preview
@Composable
fun MyList(navController: NavController = rememberNavController(),
           movies: List<Movie> = getMovies()){

    LazyColumn{
        items(movies) {movie ->
            MovieRow(
                movie = movie,
            ) { movieId ->
                navController.navigate(Screens.DetailScreen.route + "/$movieId")
            }
        }
    }
}

