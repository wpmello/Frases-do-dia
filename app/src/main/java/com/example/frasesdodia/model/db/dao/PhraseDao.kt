package com.example.frasesdodia.model.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.frasesdodia.model.db.entity.PhraseEntity

@Dao
interface PhraseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(phrase: PhraseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(phrases: List<PhraseEntity>)

    @Query("SELECT * FROM phrases")
    suspend fun getAll(): List<PhraseEntity>

    @Query("DELETE FROM phrases WHERE id = :id")
    suspend fun delete(id: Int)
}