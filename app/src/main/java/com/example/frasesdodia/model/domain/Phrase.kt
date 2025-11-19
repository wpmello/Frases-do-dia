package com.example.frasesdodia.model.domain

import com.example.frasesdodia.model.db.entity.PhraseEntity

data class Phrase(
    val id: Int,
    val text: String,
    val author: String,
    val date: String,
)

fun Phrase.toPhraseEntity() = PhraseEntity(
    id = id,
    text = text,
    author = author,
    date = date,
)
