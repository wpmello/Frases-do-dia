package com.example.frasesdodia.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("phrases")
data class PhraseEntity(
    @PrimaryKey
    val id: Int,
    val text: String,
    val author: String,
    val date: String,
)