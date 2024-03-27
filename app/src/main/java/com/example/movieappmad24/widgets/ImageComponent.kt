package com.example.movieappmad24.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.Scale

@Composable
fun ImageComponent(imageUrl: String, contentDesc: String) {
    Image(
        painter = rememberImagePainter(
            data = imageUrl,
            builder = {
                scale(Scale.FILL)
            }
        ),
        contentDescription = contentDesc,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        contentScale = ContentScale.FillWidth
    )
}