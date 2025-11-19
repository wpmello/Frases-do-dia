package com.example.frasesdodia.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.frasesdodia.data.db.entity.PhraseEntity

@Dao
interface PhraseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(phrase: PhraseEntity)

    @Query("SELECT * FROM phrases")
    suspend fun getAll(): List<PhraseEntity>

    @Query("DELETE FROM phrases WHERE id = :id")
    suspend fun delete(id: Int)
}