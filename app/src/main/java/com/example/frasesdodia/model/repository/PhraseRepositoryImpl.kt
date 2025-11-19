package com.example.frasesdodia.model.repository

import com.example.frasesdodia.model.db.dao.PhraseDao
import com.example.frasesdodia.model.db.entity.toPhrase
import com.example.frasesdodia.model.domain.Phrase
import com.example.frasesdodia.model.domain.toPhraseEntity

class PhraseRepositoryImpl(private val phraseDao: PhraseDao) : PhraseRepository {
    override suspend fun save(phrase: Phrase) {
        phraseDao.save(phrase.toPhraseEntity())
    }

    override suspend fun getAll(): List<Phrase> {
        return phraseDao.getAll().map { it.toPhrase() }
    }

    override suspend fun delete(id: Int) {
        phraseDao.delete(id)
    }
}