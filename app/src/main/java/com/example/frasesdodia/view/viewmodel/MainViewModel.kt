package com.example.frasesdodia.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frasesdodia.intent.PhraseIntent
import com.example.frasesdodia.model.domain.Phrase
import com.example.frasesdodia.model.remote.PhraseRemoteConfigManager
import com.example.frasesdodia.model.repository.PhraseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: PhraseRepository,
    private val remoteConfigManager: PhraseRemoteConfigManager
) : ViewModel() {

    private val _currentPhrase = MutableStateFlow<Phrase?>(null)
    val currentPhrase: StateFlow<Phrase?> = _currentPhrase.asStateFlow()

    init {
        fetchAndSavePhrasesFromRemoteConfig()
    }

    private fun fetchAndSavePhrasesFromRemoteConfig() {
        viewModelScope.launch(Dispatchers.IO) {
            remoteConfigManager.fetchAndActivateConfig { phrases ->
                if (phrases != null && phrases.isNotEmpty()) {
                    viewModelScope.launch {
                        repository.saveAll(phrases)
                    }
                    //TODO: change random phrase to data layer responsability
                    _currentPhrase.value = phrases.randomOrNull()
                }
            }
        }
    }

    fun processIntent(intent: PhraseIntent) {
        when (intent) {
            is PhraseIntent.Save -> {
                viewModelScope.launch(Dispatchers.IO) {
                    repository.save(intent.phrase)
                }
            }
            else -> { }
        }
    }
}