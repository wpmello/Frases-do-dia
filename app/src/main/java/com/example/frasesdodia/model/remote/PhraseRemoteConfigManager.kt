package com.example.frasesdodia.model.remote

import com.example.frasesdodia.model.domain.Phrase
import com.google.common.reflect.TypeToken
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhraseRemoteConfigManager @Inject constructor(
    private val gson: Gson,
    private var remoteConfig: FirebaseRemoteConfig
) {

    private val PHRASES_KEY = "daily_quotes_list"

    fun fetchAndActivateConfig(onComplete: (List<Phrase>?) -> Unit) {
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val json = remoteConfig.getString(PHRASES_KEY)
                    val phrases = parsePhrasesJson(json)
                    onComplete(phrases)
                } else {
                    onComplete(null)
                }
            }
    }

    private fun parsePhrasesJson(json: String): List<Phrase>? {
        return try {
            val type = object : TypeToken<List<Phrase>>() {}.type
            gson.fromJson(json, type)
        } catch (e: Exception) {
            null
        }
    }
}