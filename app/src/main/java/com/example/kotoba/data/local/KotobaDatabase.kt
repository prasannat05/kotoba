package com.example.kotoba.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [UserProgress::class, UserProfile::class, CharacterProgress::class], 
    version = 2, 
    exportSchema = false
)
abstract class KotobaDatabase : RoomDatabase() {
    abstract fun kotobaDao(): KotobaDao

    companion object {
        @Volatile
        private var INSTANCE: KotobaDatabase? = null

        fun getDatabase(context: Context): KotobaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KotobaDatabase::class.java,
                    "kotoba_database"
                )
                .fallbackToDestructiveMigration() // For development simplicity
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
