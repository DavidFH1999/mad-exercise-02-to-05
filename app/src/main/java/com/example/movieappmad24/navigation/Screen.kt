package com.example.movieappmad24.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("homeScreen")
    object DetailScreen : Screen("detailScreen/{movieId}") {
        fun createRoute(movieId: String) = "detailScreen/$movieId"
    }
    object WatchlistScreen : Screen("watchlistScreen")
}