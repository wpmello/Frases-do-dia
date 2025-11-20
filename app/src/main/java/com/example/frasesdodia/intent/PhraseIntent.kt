package com.example.frasesdodia.intent

import com.example.frasesdodia.model.domain.FavoritePhrase
import com.example.frasesdodia.model.domain.Phrase

sealed class PhraseIntent {
    data class AddToFavorite(val phrase: Phrase) : PhraseIntent()
    data class RemoveFromFavorite(val favoritePhrase: FavoritePhrase) : PhraseIntent()
}