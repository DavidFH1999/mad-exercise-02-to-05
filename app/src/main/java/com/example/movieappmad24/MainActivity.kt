package com.example.movieappmad24

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.navigation.MovieAppNavigation
import com.example.movieappmad24.navigation.Screen
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme
import com.example.movieappmad24.widgets.BottomBarItem
import com.example.movieappmad24.widgets.ExpandableMovieCard
import com.example.movieappmad24.widgets.SimpleBottomAppBar
import com.example.movieappmad24.widgets.SimpleTopAppBar


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppMAD24Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MovieAppNavigation()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieList(navController: NavController, movies: List<Movie> = getMovies(), title: String) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(topAppBarState)
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination
    val isStartDestination = currentDestination?.route == navController.graph.startDestinationRoute

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = title,
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    if (!isStartDestination) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                }
            )
        },
        bottomBar = {
            SimpleBottomAppBar(
                navController = navController,
                currentRoute = currentDestination?.route ?: "",
                items = listOf(
                    BottomBarItem(Icons.Filled.Home, "Home", Screen.HomeScreen.route) { controller ->
                        controller.navigate(Screen.HomeScreen.route) {
                            popUpTo(controller.graph.startDestinationId)
                        }
                    },
                    BottomBarItem(Icons.Filled.Star, "Watchlist", Screen.WatchlistScreen.route) { controller ->
                        controller.navigate(Screen.WatchlistScreen.route) {
                            popUpTo(controller.graph.startDestinationId)
                        }
                    }
                )
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(movies) { movie ->
                ExpandableMovieCard(movie, onClick = {
                    navController.navigate(Screen.DetailScreen.createRoute(movie.id))
                })
            }
        }
    }
}

@Composable
fun previewNavController(): NavController {
    return rememberNavController()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieList(navController = previewNavController(), title = "Preview Title")
}