package com.example.movielist.menu

import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movielist.navigation.Screens

@Composable
fun MainAppBar(navController: NavController){
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
                        navController.navigate(Screens.FavoritesScreen.route)
                    }) {
                        Icon(Icons.Default.Favorite, null)
                        Text(text = "Favorites")
                    }
                }
            })
    }
}