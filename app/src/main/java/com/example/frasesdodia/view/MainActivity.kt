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
import com.example.frasesdodia.view.ui.component.BottomNavigation
import com.example.frasesdodia.view.ui.component.DailyQuoteScreen
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
            FrasesDoDiaTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigation()
                    }) { innerPadding ->
                    var isLiked by remember { mutableStateOf(false) }
                    val currentPhrase by viewModel.currentPhrase.collectAsState()

                    DailyQuoteScreen(
                        currentPhrase = currentPhrase,
                        isLiked = isLiked,
                        onLikeClick = { isLiked = !isLiked },
                        onSaveClick = { intent ->
                            viewModel.processIntent(intent)
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}