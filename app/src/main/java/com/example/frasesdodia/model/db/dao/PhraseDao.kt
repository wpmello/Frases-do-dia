package com.example.frasesdodia.model.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.frasesdodia.model.db.entity.PhraseEntity

@Dao
interface PhraseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(phrases: List<PhraseEntity>)
}