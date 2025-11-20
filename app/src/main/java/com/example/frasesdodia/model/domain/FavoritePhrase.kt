package com.example.frasesdodia.model.domain

import com.example.frasesdodia.model.db.entity.FavoritePhraseEntity

data class FavoritePhrase(
    val id: Int,
    val text: String,
    val author: String,
    val date: String,
    val isFavorite: Boolean = true
)

fun FavoritePhrase.toFavoritePhraseEntity() = FavoritePhraseEntity(
    id = id,
    text = text,
    author = author,
    date = date,
    isFavorite = true
)