package com.example.frasesdodia.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frasesdodia.intent.PhraseIntent
import com.example.frasesdodia.model.domain.FavoritePhrase
import com.example.frasesdodia.model.domain.Phrase
import com.example.frasesdodia.model.remote.PhraseRemoteConfigManager
import com.example.frasesdodia.model.repository.PhraseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: PhraseRepository,
    private val remoteConfigManager: PhraseRemoteConfigManager
) : ViewModel() {

    private val _currentPhrase = MutableStateFlow<Phrase?>(null)
    val currentPhrase: StateFlow<Phrase?> = _currentPhrase.asStateFlow()

    val favoritePhrases: StateFlow<List<FavoritePhrase>> = repository.getAllFavorites()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())


    init {
        fetchAndSavePhrasesFromRemoteConfig()
    }

    private fun fetchAndSavePhrasesFromRemoteConfig() {
        viewModelScope.launch {
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
            is PhraseIntent.AddToFavorite -> {
                viewModelScope.launch {
                    repository.addToFavorite(intent.phrase)
                }
            }

            is PhraseIntent.RemoveFromFavorite -> {
                viewModelScope.launch {
                    repository.removeFromFavorite(intent.favoritePhrase)
                }
            }
        }
    }
}