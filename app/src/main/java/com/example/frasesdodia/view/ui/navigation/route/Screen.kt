package com.example.frasesdodia.view.ui.navigation.route

sealed class Screen(val route: String) {
    object DailyQuote : Screen("daily_quote")
    object Favorites : Screen("favorites")
}