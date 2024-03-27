package com.example.movieappmad24.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappmad24.MovieList
import com.example.movieappmad24.R
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.screens.DetailScreen
import com.example.movieappmad24.screens.WatchlistScreen

@Composable
fun MovieAppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(Screen.HomeScreen.route) {
            MovieList(navController = navController, movies = getMovies(), title = stringResource(id = R.string.app_name))
        }
        composable(
            route = Screen.DetailScreen.route,
            arguments = listOf(navArgument("movieId") { type = NavType.StringType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId") ?: return@composable
            DetailScreen(navController = navController, movieId = movieId)
        }
        composable(Screen.WatchlistScreen.route) {
            WatchlistScreen(navController = navController)
        }
    }
}