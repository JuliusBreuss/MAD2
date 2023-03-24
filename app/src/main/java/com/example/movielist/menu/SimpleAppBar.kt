package com.example.movielist.menu

import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SimpleAppBar(text: String, navController: NavController){
    Row {
        TopAppBar(
            elevation = 4.dp,
            title = {
                Text(text)
            },
            backgroundColor = MaterialTheme.colors.primarySurface,
            navigationIcon = {
                             IconButton(onClick = { navController.popBackStack()}) {
                                 Icon(Icons.Default.ArrowBack, "backToPrevious")
                             }
            })
    }
}