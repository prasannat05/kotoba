package com.example.kotoba.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_progress")
data class UserProgress(
    @PrimaryKey val chapterId: Int,
    val isCompleted: Boolean = false,
    val bestScore: Int = 0,
    val accuracy: Float = 0f,
    val lastStudied: Long = System.currentTimeMillis()
)

@Entity(tableName = "character_progress")
data class CharacterProgress(
    @PrimaryKey val charId: String, // e.g. "H_あ" or "K_ア"
    val isLearned: Boolean = false
)

@Entity(tableName = "user_profile")
data class UserProfile(
    @PrimaryKey val id: Int = 1,
    val username: String = "Learner",
    val avatar: String = "default_avatar",
    val totalXp: Int = 0,
    val streak: Int = 0,
    val lastActivityDate: Long = System.currentTimeMillis()
)
