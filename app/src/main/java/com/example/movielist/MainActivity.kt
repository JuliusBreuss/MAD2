package com.example.movielist

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movielist.models.Movie
import com.example.movielist.models.getMovies
import com.example.movielist.ui.theme.MovieListTheme
import coil.compose.AsyncImage
import androidx.compose.ui.platform.*
import coil.request.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) { Column {

                    TopAppBar()
                    MyList()

                }
                    //DropdownDemo()

                    //Greeting("Android")
                }
            }
        }
    }
}

@Preview
@Composable
fun MyList(movies: List<Movie> = getMovies()){

    LazyColumn{

        items(movies) {movie ->
            MovieRow(movie = movie)
        }
    }
}
@Composable
fun MovieRow(movie: Movie) {

    var arrow by remember { mutableStateOf(Icons.Default.KeyboardArrowUp) }
    var show by remember {mutableStateOf(false)}

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 5.dp
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(150.dp)

            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        .crossfade(true)
                        .build(),
                    contentDescription = movie.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Icon(
                        tint = MaterialTheme.colors.secondary,
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Add to favorites"
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(movie.title, style = MaterialTheme.typography.h6)
                Icon(
                    imageVector = arrow,
                    contentDescription = "Show details",
                    modifier = Modifier
                        .clickable(onClick = {
                            arrow =
                                if (arrow == Icons.Default.KeyboardArrowUp) {
                                    Icons.Default.KeyboardArrowDown
                                } else {
                                    Icons.Default.KeyboardArrowUp
                                }
                                show = !show
                        })
                )
            }
            Row {
                AnimatedVisibility(visible = show) {
                    Column(modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 10.dp)) {
                        Text(text = "Director: ${movie.director}")
                        Text(text = "Released: ${movie.year}")
                        Text(text = "Genre: ${movie.genre}")
                        Text(text = "Actors: ${movie.actors}")
                        Text(text = "Rating: ${movie.rating}")
                        Text(
                            text = "Plot: ${movie.plot}",
                            modifier = Modifier
                                .padding(horizontal = 25.dp, vertical = 15.dp)
                        )
                    }
                }
            }

        }
    }
}
@Composable
fun TopAppBar(){
    var expanded by remember { mutableStateOf(false) }
    Row {
        TopAppBar(
            elevation = 4.dp,
            title = {
                Text("Movies")
            },
            backgroundColor = MaterialTheme.colors.primarySurface,
            actions = {
                IconButton(onClick = {
                    expanded = !expanded
                }) {
                    Icon(Icons.Default.MoreVert, null)
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(onClick = {
                        expanded = false
                    }) {
                        Icon(Icons.Default.Favorite, null)
                        Text(text = "Favorites")
                    }
                }
            })
    }
}


