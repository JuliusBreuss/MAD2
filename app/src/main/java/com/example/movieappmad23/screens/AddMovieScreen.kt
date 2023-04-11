package com.example.movieappmad23.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieappmad23.R
import com.example.movieappmad23.models.Genre
import com.example.movieappmad23.models.ListItemSelectable
import com.example.movieappmad23.widgets.MoviesViewModel
import com.example.movieappmad23.widgets.SimpleTopAppBar

@Composable
fun AddMovieScreen(navController: NavController, moviesViewModel: MoviesViewModel) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
                Text(text = stringResource(id = R.string.add_movie))
            }
        },
    ) { padding ->
        MainContent(
            Modifier.padding(padding),
            checkStringValid = { toCheck -> moviesViewModel.checkStringValid(toCheck)},
            checkFloatValid = { toCheck -> moviesViewModel.checkFloatValid(toCheck)},
            checkGenreValid = { toCheck -> moviesViewModel.checkGenreValid(toCheck)},
            addMovie = {title, year, genre, director, actors, plot, rating -> moviesViewModel.addMovie(title, year, genre, director, actors, plot, rating)})
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    checkStringValid: (String) -> Boolean,
    checkFloatValid: (String) -> Boolean,
    checkGenreValid: (List<ListItemSelectable>) -> Boolean,
    addMovie: (String, String, List<ListItemSelectable>, String, String, String, Float) -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
    ) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            var title by remember {
                mutableStateOf("")
            }
            var titleValid by remember {
                mutableStateOf(false)
            }

            var year by remember {
                mutableStateOf("")
            }
            var yearValid by remember {
                mutableStateOf(false)
            }

            val genres = Genre.values().toList()

            var genreItems by remember {
                mutableStateOf(
                    genres.map { genre ->
                        ListItemSelectable(
                            title = genre.toString(),
                            isSelected = false
                        )
                    }
                )
            }

            var genreValid by remember {
                mutableStateOf(false)
            }

            var director by remember {
                mutableStateOf("")
            }
            var directorValid by remember {
                mutableStateOf(false)
            }

            var actors by remember {
                mutableStateOf("")
            }
            var actorsValid by remember {
                mutableStateOf(false)
            }

            var plot by remember {
                mutableStateOf("")
            }

            var rating by remember {
                mutableStateOf("")
            }
            var ratingValid by remember {
                mutableStateOf(false)
            }

            var isEnabledSaveButton by remember {
                mutableStateOf(false)
            }

            OutlinedTextField(
                value = title,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    title = it
                    titleValid = checkStringValid(it)
                },
                label = { Text(text = stringResource(R.string.enter_movie_title)) },
                isError = !titleValid
            )
            if (!titleValid) {
                Text(
                    text = "Title is required",
                    color = MaterialTheme.colors.error
                )
            }


            OutlinedTextField(
                value = year,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    year = it
                    yearValid = checkStringValid(it)
                },
                label = { Text(stringResource(R.string.enter_movie_year)) },
                isError = !yearValid
            )
            if (!yearValid) {
                Text(
                    text = "Year is required",
                    color = MaterialTheme.colors.error
                )
            }

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = stringResource(R.string.select_genres),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h6
            )

            LazyHorizontalGrid(
                modifier = Modifier.height(100.dp),
                rows = GridCells.Fixed(3)
            ) {
                items(genreItems) { genreItem ->
                    Chip(
                        modifier = Modifier.padding(2.dp),
                        colors = ChipDefaults.chipColors(
                            backgroundColor = if (genreItem.isSelected)
                                colorResource(id = R.color.purple_200)
                            else
                                colorResource(id = R.color.white)
                        ),
                        onClick = {
                            genreItems = genreItems.map {
                                if (it.title == genreItem.title) {
                                    genreItem.copy(isSelected = !genreItem.isSelected)
                                } else {
                                    it
                                }

                            }
                            genreValid = checkGenreValid(genreItems)
                        }
                    ) {
                        Text(text = genreItem.title)
                    }
                }
            }
            if (!genreValid) {
                Text(
                    text = "At least 1 Genre must be selected",
                    color = MaterialTheme.colors.error
                )
            }

            OutlinedTextField(
                value = director,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    director = it
                    directorValid = checkStringValid(it)
                },
                label = { Text(stringResource(R.string.enter_director)) },
                isError = !directorValid
            )
            if (!directorValid) {
                Text(
                    text = "Director is required",
                    color = MaterialTheme.colors.error
                )
            }

            OutlinedTextField(
                value = actors,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    actors = it
                    actorsValid = checkStringValid(it)
                },
                label = { Text(stringResource(R.string.enter_actors)) },
                isError = !actorsValid
            )
            if (!actorsValid) {
                Text(
                    text = "Actors are required",
                    color = MaterialTheme.colors.error
                )
            }

            OutlinedTextField(
                value = plot,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                onValueChange = { plot = it },
                label = {
                    Text(
                        textAlign = TextAlign.Start,
                        text = stringResource(R.string.enter_plot)
                    )
                },
                isError = false
            )


            OutlinedTextField(
                value = rating,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    rating = if (it.startsWith("0")) {
                        ""
                    } else {
                        it
                    }
                    ratingValid = checkFloatValid(it)
                },
                label = { Text(stringResource(R.string.enter_rating)) },
                isError = !ratingValid
            )
            if (!ratingValid) {
                Text(
                    text = "Rating as float is required",
                    color = MaterialTheme.colors.error
                )
            }
            if (titleValid && yearValid && genreValid && directorValid && actorsValid && ratingValid){
                isEnabledSaveButton = true;
            }

            Button(
                enabled = isEnabledSaveButton,
                onClick = { addMovie(title, year, genreItems, director, actors, plot, rating.toFloat()) }) {
                Text(text = stringResource(R.string.add))
            }
        }
    }
}