package com.example.frasesdodia.model.repository

import com.example.frasesdodia.model.db.dao.PhraseDao
import com.example.frasesdodia.model.db.entity.toPhrase
import com.example.frasesdodia.model.domain.Phrase
import com.example.frasesdodia.model.domain.toPhraseEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhraseRepositoryImpl @Inject constructor(
    private val phraseDao: PhraseDao
) : PhraseRepository {
    override suspend fun save(phrase: Phrase) {
        withContext(Dispatchers.IO) {
            phraseDao.save(phrase.toPhraseEntity())
        }
    }

    override suspend fun saveAll(phrases: List<Phrase>) {
        withContext(Dispatchers.IO) {
            phraseDao.saveAll(phrases.map { it.toPhraseEntity() })
        }
    }

    override suspend fun getAll(): List<Phrase> = withContext(Dispatchers.IO) {
        phraseDao.getAll().map { it.toPhrase() }
    }


    override suspend fun delete(id: Int) {
        withContext(Dispatchers.IO) {
            phraseDao.delete(id)
        }
    }
}