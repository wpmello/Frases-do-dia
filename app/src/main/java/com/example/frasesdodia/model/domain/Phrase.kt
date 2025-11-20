package com.example.frasesdodia.model.domain

import com.example.frasesdodia.model.db.entity.FavoritePhraseEntity
import com.example.frasesdodia.model.db.entity.PhraseEntity

data class Phrase(
    val id: Int,
    val text: String,
    val author: String,
    val date: String
)

fun Phrase.toFavoritePhraseEntity() = FavoritePhraseEntity(
    id = id,
    text = text,
    author = author,
    date = date,
    isFavorite = true
)

fun Phrase.toPhraseEntity() = PhraseEntity(
    id = id,
    text = text,
    author = author,
    date = date
)