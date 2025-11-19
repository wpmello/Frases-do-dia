package com.example.frasesdodia.model.ui

import android.app.Application
import com.example.frasesdodia.model.db.AppDatabase
import com.example.frasesdodia.model.db.dao.PhraseDao
import com.example.frasesdodia.model.repository.PhraseRepository
import com.example.frasesdodia.model.repository.PhraseRepositoryImpl
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
    fun providesDatabase(@ApplicationContext application: Application): AppDatabase {
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
}