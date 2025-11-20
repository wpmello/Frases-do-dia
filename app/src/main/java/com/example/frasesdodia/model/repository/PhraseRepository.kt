package com.example.frasesdodia.model.repository

import com.example.frasesdodia.model.domain.FavoritePhrase
import com.example.frasesdodia.model.domain.Phrase
import kotlinx.coroutines.flow.Flow

interface PhraseRepository {
    suspend fun saveAll(phrases: List<Phrase>)
    suspend fun addToFavorite(phrase: Phrase)
    fun getAllFavorites(): Flow<List<FavoritePhrase>>
    suspend fun removeFromFavorite(favoritePhrase: FavoritePhrase)
}