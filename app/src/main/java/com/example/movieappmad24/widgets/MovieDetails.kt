package com.example.movieappmad24.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.movieappmad24.models.Movie

@Composable
fun MovieDetails(movie: Movie, isExpanded: Boolean) {
    Column {
        if (isExpanded) {
            Text("Director: ${movie.director}")
            Text("Released: ${movie.year}")
            Text("Genre: ${movie.genre}")
            Text("Actors: ${movie.actors}")
            Text("Rating: ${movie.rating}")
            HorizontalDivider()
            Text("Plot: ${movie.plot}")
        }
    }
}