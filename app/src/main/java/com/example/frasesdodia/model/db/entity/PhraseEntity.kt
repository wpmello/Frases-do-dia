package com.example.frasesdodia.model.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.frasesdodia.model.domain.Phrase

@Entity("phrases")
data class PhraseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val text: String,
    val author: String,
    val date: String,
)

fun PhraseEntity.toPhrase() = Phrase(
    id = id,
    text = text,
    author = author,
    date = date,
)