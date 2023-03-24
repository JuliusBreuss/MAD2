package com.example.movielist.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movielist.widgets.ImageLoader
import com.example.movielist.models.Movie
import com.example.movielist.models.getMovies
import com.example.movielist.menu.SimpleAppBar
import com.example.movielist.widgets.MovieRow

@Composable
fun DetailScreen(navController: NavController, movieId: String?) {
    val movies = getMovies()
    var movie = movies[0]
    for (item: Movie in movies) {
        if (item.id == movieId) {
            movie = item
        }
    }
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Column {
            SimpleAppBar(text = movie.title, navController = navController)
            MovieRow(movie=movie)
            Images(imageList = movie.images)
        }
    }
}

@Composable
fun Images(imageList: List<String>){
    Row{
        Divider(startIndent = 1.dp, thickness = 1.dp, color = Color.LightGray)
    }
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()){
        Text(text = "Movie Images",
        fontSize = 25.sp)
    }
    LazyRow(){
        items(imageList) {image ->
            Card(shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
            ){
                ImageLoader(url = image)
            }
        }
    }
}