package com.example.frasesdodia.model.repository

import com.example.frasesdodia.model.domain.Phrase

interface PhraseRepository {
    suspend fun save(phrase: Phrase)
    suspend fun getAll(): List<Phrase>
    suspend fun delete(id: Int)
}