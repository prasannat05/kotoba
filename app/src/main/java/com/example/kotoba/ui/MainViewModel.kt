package com.example.kotoba.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotoba.data.AlphabetData
import com.example.kotoba.data.Chapter
import com.example.kotoba.data.KanaCharacter
import com.example.kotoba.data.local.CharacterProgress
import com.example.kotoba.data.local.KotobaDatabase
import com.example.kotoba.data.local.UserProgress
import com.example.kotoba.data.local.UserProfile
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = KotobaDatabase.getDatabase(application).kotobaDao()

    val userProfile: StateFlow<UserProfile?> = dao.getUserProfile()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val allProgress: StateFlow<List<UserProgress>> = dao.getAllProgress()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val characterProgress: StateFlow<List<CharacterProgress>> = dao.getAllCharacterProgress()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _currentChapter = MutableStateFlow<Chapter?>(null)
    val currentChapter: StateFlow<Chapter?> = _currentChapter.asStateFlow()

    init {
        viewModelScope.launch {
            if (dao.getUserProfile().first() == null) {
                dao.updateUserProfile(UserProfile())
            }
        }
    }

    fun selectChapter(chapter: Chapter) {
        _currentChapter.value = chapter
        updateLastStudied(chapter.id)
    }

    private fun updateLastStudied(chapterId: Int) {
        viewModelScope.launch {
            val existing = dao.getProgressForChapter(chapterId)
            val updated = existing?.copy(lastStudied = System.currentTimeMillis()) 
                ?: UserProgress(chapterId = chapterId, lastStudied = System.currentTimeMillis())
            dao.updateProgress(updated)
        }
    }

    fun toggleCharacterLearned(character: KanaCharacter) {
        viewModelScope.launch {
            val charId = "${character.type.name}_${character.japanese}"
            val existing = characterProgress.value.find { it.charId == charId }
            val newState = !(existing?.isLearned ?: false)
            dao.updateCharacterProgress(CharacterProgress(charId, newState))
            
            if (newState) {
                userProfile.value?.let { profile ->
                    dao.updateUserProfile(profile.copy(totalXp = profile.totalXp + 10))
                }
            }
        }
    }

    fun completeChapter(chapterId: Int, score: Int, total: Int) {
        viewModelScope.launch {
            val accuracy = if (total > 0) score.toFloat() / total else 1f
            val existing = dao.getProgressForChapter(chapterId)
            
            val newProgress = UserProgress(
                chapterId = chapterId,
                isCompleted = true,
                bestScore = maxOf(existing?.bestScore ?: 0, score),
                accuracy = maxOf(existing?.accuracy ?: 0f, accuracy),
                lastStudied = System.currentTimeMillis()
            )
            dao.updateProgress(newProgress)
            
            userProfile.value?.let { profile ->
                val xpGained = score * 5 + (if (accuracy == 1f) 50 else 0)
                
                val lastActivity = Calendar.getInstance().apply { timeInMillis = profile.lastActivityDate }
                val today = Calendar.getInstance()
                
                val isSameDay = lastActivity.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
                                lastActivity.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)
                
                val yesterday = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, -1) }
                val isConsecutive = lastActivity.get(Calendar.YEAR) == yesterday.get(Calendar.YEAR) &&
                                    lastActivity.get(Calendar.DAY_OF_YEAR) == yesterday.get(Calendar.DAY_OF_YEAR)

                val newStreak = when {
                    isSameDay -> profile.streak
                    isConsecutive -> profile.streak + 1
                    else -> 1
                }

                dao.updateUserProfile(profile.copy(
                    totalXp = profile.totalXp + xpGained,
                    streak = newStreak,
                    lastActivityDate = System.currentTimeMillis()
                ))
            }
        }
    }
}
