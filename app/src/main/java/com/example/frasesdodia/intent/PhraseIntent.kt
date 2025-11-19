package com.example.frasesdodia.intent

import com.example.frasesdodia.model.domain.Phrase

sealed class PhraseIntent {
    data class Save(val phrase: Phrase) : PhraseIntent()
    data class Delete(val id: Int) : PhraseIntent()
}