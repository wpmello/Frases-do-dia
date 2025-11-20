package com.example.frasesdodia.model.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.frasesdodia.model.domain.FavoritePhrase

@Entity(tableName = "favorite_phrases")
data class FavoritePhraseEntity(
    @PrimaryKey val id: Int,
    val text: String,
    val author: String,
    val date: String,
    val isFavorite: Boolean = true
) {
    fun toFavoritePhrase(): FavoritePhrase = FavoritePhrase(id, text, author, date, true)
}