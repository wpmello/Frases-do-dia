package com.example.frasesdodia.view.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.frasesdodia.view.ui.navigation.route.Screen

@Composable
fun BottomNavigation(navController: NavController) {
    NavigationBar(contentColor = MaterialTheme.colorScheme.onPrimary) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.ListAlt, contentDescription = null) },
            label = { Text("Frases Favotiras") },
            selected = true,
            onClick = {
                navController.navigate(Screen.Favorites.route) {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                }
            }
        )
    }
}