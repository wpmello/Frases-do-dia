package com.example.frasesdodia.model.di

import android.content.Context
import com.example.frasesdodia.model.db.AppDatabase
import com.example.frasesdodia.model.db.dao.PhraseDao
import com.example.frasesdodia.model.remote.PhraseRemoteConfigManager
import com.example.frasesdodia.model.repository.PhraseRepository
import com.example.frasesdodia.model.repository.PhraseRepositoryImpl
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext application: Context): AppDatabase {
        return AppDatabase.getDatabase(application)
    }

    @Singleton
    @Provides
    fun providesPhraseDao(database: AppDatabase): PhraseDao {
        return database.phraseDao()
    }

    @Singleton
    @Provides
    fun providesPhraseRepository(phraseDao: PhraseDao): PhraseRepository {
        return PhraseRepositoryImpl(phraseDao)
    }

    @Provides
    @Singleton
    fun provideFirebaseRemoteConfig(): FirebaseRemoteConfig {
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        val settings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(settings)
        return remoteConfig
    }

    @Singleton
    @Provides
    fun providesPhraseRemoteConfigManager(gson: Gson, remoteConfig: FirebaseRemoteConfig): PhraseRemoteConfigManager {
        return PhraseRemoteConfigManager(gson, remoteConfig)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }
}