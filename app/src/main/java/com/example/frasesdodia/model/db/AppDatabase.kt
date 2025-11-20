package com.example.frasesdodia.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.frasesdodia.model.db.dao.FavoritePhraseDao
import com.example.frasesdodia.model.db.dao.PhraseDao
import com.example.frasesdodia.model.db.entity.FavoritePhraseEntity
import com.example.frasesdodia.model.db.entity.PhraseEntity

@Database(entities = [PhraseEntity::class, FavoritePhraseEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun phraseDao(): PhraseDao
    abstract fun favoritePhraseDao(): FavoritePhraseDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}