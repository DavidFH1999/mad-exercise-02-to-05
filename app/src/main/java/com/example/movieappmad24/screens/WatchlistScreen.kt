package com.example.movieappmad24.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.movieappmad24.MovieList
import com.example.movieappmad24.models.getMovies

@Composable
fun WatchlistScreen(navController: NavController) {
    val watchlistMovies = remember { getMovies().take(2) }
    MovieList(navController = navController, movies = watchlistMovies, title = "Your Watchlist")
}