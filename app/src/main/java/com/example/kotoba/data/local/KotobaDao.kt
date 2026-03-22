package com.example.kotoba.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface KotobaDao {
    @Query("SELECT * FROM user_progress")
    fun getAllProgress(): Flow<List<UserProgress>>

    @Query("SELECT * FROM user_progress WHERE chapterId = :id")
    suspend fun getProgressForChapter(id: Int): UserProgress?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProgress(progress: UserProgress)

    @Query("SELECT * FROM character_progress")
    fun getAllCharacterProgress(): Flow<List<CharacterProgress>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCharacterProgress(progress: CharacterProgress)

    @Query("SELECT * FROM user_profile WHERE id = 1")
    fun getUserProfile(): Flow<UserProfile?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUserProfile(profile: UserProfile)
}
