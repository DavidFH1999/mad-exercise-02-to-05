package com.example.movieappmad24

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.screens.DetailScreen

@Composable
fun MovieAppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "homeScreen") {
        composable("homeScreen") {
            // You might want to create a dedicated HomeScreen composable
            MovieList(navController = navController, movies = getMovies())
        }
        composable("detailScreen/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId") ?: return@composable
            DetailScreen(navController = navController, movieId = movieId)
        }
    }
}