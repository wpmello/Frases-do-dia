package com.example.frasesdodia.model.repository

import com.example.frasesdodia.model.db.dao.FavoritePhraseDao
import com.example.frasesdodia.model.db.dao.PhraseDao
import com.example.frasesdodia.model.domain.FavoritePhrase
import com.example.frasesdodia.model.domain.Phrase
import com.example.frasesdodia.model.domain.toFavoritePhraseEntity
import com.example.frasesdodia.model.domain.toPhraseEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhraseRepositoryImpl @Inject constructor(
    private val phraseDao: PhraseDao,
    private val favoritePhraseDao: FavoritePhraseDao
) : PhraseRepository {
    override suspend fun saveAll(phrases: List<Phrase>) {
        withContext(Dispatchers.IO) {
            phraseDao.saveAll(phrases.map { it.toPhraseEntity() })
        }
    }

    override suspend fun addToFavorite(phrase: Phrase) {
        withContext(Dispatchers.IO) {
            favoritePhraseDao.save(phrase.toFavoritePhraseEntity())
        }
    }

    override fun getAllFavorites(): Flow<List<FavoritePhrase>> =
        favoritePhraseDao.getAllFavorites().map { entities ->
            entities.map { it.toFavoritePhrase() }
        }

    override suspend fun removeFromFavorite(favoritePhrase: FavoritePhrase) {
        withContext(Dispatchers.IO) {
            favoritePhraseDao.delete(favoritePhrase.toFavoritePhraseEntity())
        }
    }
}