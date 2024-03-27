package com.example.movieappmad24.widgets

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController

@Composable
fun SimpleBottomAppBar(
    navController: NavController,
    currentRoute: String,
    items: List<BottomBarItem>
) {
    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = { if (currentRoute != item.route) item.onClick(navController) }
            )
        }
    }
}

data class BottomBarItem(
    val icon: ImageVector,
    val label: String,
    val route: String,
    val onClick: (NavController) -> Unit
)