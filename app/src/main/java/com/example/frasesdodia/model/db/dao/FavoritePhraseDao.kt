package com.example.frasesdodia.model.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.frasesdodia.model.db.entity.FavoritePhraseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritePhraseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(favorite: FavoritePhraseEntity)

    @Delete
    suspend fun delete(favorite: FavoritePhraseEntity)

    @Query("SELECT * FROM favorite_phrases")
    fun getAllFavorites(): Flow<List<FavoritePhraseEntity>>
}
