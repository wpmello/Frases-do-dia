package com.example.frasesdodia.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.frasesdodia.view.ui.component.BottomNavigation
import com.example.frasesdodia.view.ui.component.DailyQuoteScreen
import com.example.frasesdodia.view.ui.component.FavoritePhrasesList
import com.example.frasesdodia.view.ui.navigation.route.Screen
import com.example.frasesdodia.view.ui.theme.FrasesDoDiaTheme
import com.example.frasesdodia.view.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            FrasesDoDiaTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigation(navController)
                    }) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.DailyQuote.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.DailyQuote.route) {
                            var isLiked by remember { mutableStateOf(false) }
                            var isSaved by remember { mutableStateOf(false) }
                            val currentPhrase by viewModel.currentPhrase.collectAsState()

                            DailyQuoteScreen(
                                currentPhrase = currentPhrase,
                                isLiked = isLiked,
                                isSaved = isSaved,
                                onLikeClick = { isLiked = !isLiked },
                                onSaveClick = { intent ->
                                    isSaved = !isSaved
                                    viewModel.processIntent(intent)
                                },
                                modifier = Modifier.padding(innerPadding)
                            )
                        }

                        composable(Screen.Favorites.route) {
                            val favoritePhrases by viewModel.favoritePhrases.collectAsState()
                            FavoritePhrasesList(phrases = favoritePhrases, onPhraseClick = {})
                        }
                    }
                }
            }
        }
    }
}