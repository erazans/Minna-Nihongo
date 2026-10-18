package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.data.datasource.MinnaDatabase
import com.example.data.model.Lesson
import com.example.data.model.UserStats
import com.example.data.repository.LearningRepository
import com.example.service.TtsManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class AppScreen {
    HOME,
    KANA_STUDY,
    LESSON_DETAIL,
    EXERCISE_SESSION,
    AI_CONVERSATION,
    GLOSSARY,
    STATS
}

class LearningViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = LearningRepository(application)
    val ttsManager = TtsManager(application)

    val userStats: StateFlow<UserStats> = repository.userStats
    val lessons: StateFlow<List<Lesson>> = repository.lessons

    private val _currentScreen = MutableStateFlow(AppScreen.HOME)
    val currentScreen: StateFlow<AppScreen> = _currentScreen.asStateFlow()

    private val _selectedLesson = MutableStateFlow<Lesson?>(null)
    val selectedLesson: StateFlow<Lesson?> = _selectedLesson.asStateFlow()

    private val _selectedSection = MutableStateFlow<com.example.data.model.ChapterSectionType?>(null)
    val selectedSection: StateFlow<com.example.data.model.ChapterSectionType?> = _selectedSection.asStateFlow()

    fun navigateTo(screen: AppScreen) {
        _currentScreen.value = screen
    }

    fun openLesson(lesson: Lesson) {
        _selectedLesson.value = lesson
        _selectedSection.value = null
        _currentScreen.value = AppScreen.LESSON_DETAIL
    }

    fun startExercises(lesson: Lesson, section: com.example.data.model.ChapterSectionType? = null) {
        _selectedLesson.value = lesson
        _selectedSection.value = section
        _currentScreen.value = AppScreen.EXERCISE_SESSION
    }

    fun onBack() {
        when (_currentScreen.value) {
            AppScreen.EXERCISE_SESSION -> {
                // If exiting exercise, back to lesson detail
                _currentScreen.value = AppScreen.LESSON_DETAIL
            }
            AppScreen.KANA_STUDY, AppScreen.LESSON_DETAIL, AppScreen.AI_CONVERSATION, AppScreen.GLOSSARY, AppScreen.STATS -> {
                _currentScreen.value = AppScreen.HOME
            }
            AppScreen.HOME -> {
                // Already at root
            }
        }
    }

    fun completeExerciseSession(stars: Int, xp: Int, correct: Int, total: Int) {
        val lessonId = _selectedLesson.value?.id ?: 1
        repository.completeLesson(lessonId, stars, xp, correct, total)
        _currentScreen.value = AppScreen.LESSON_DETAIL
    }

    fun addXp(xp: Int) {
        repository.addXp(xp)
    }

    fun loseLife(): Boolean {
        return repository.loseLife()
    }

    fun refillLives() {
        repository.refillLives()
    }

    fun toggleBookmarkWord(wordId: String) {
        repository.toggleBookmarkWord(wordId)
    }

    fun toggleMasteredWord(wordId: String) {
        repository.toggleMasteredWord(wordId)
    }

    fun setDailyReminder(enabled: Boolean, hour: Int, minute: Int) {
        repository.setDailyReminder(enabled, hour, minute)
    }

    fun updateRomajiDisplayMode(mode: com.example.data.model.RomajiDisplayMode) {
        repository.updateRomajiDisplayMode(mode)
    }

    fun updateAutoPronounce(enabled: Boolean) {
        repository.updateAutoPronounce(enabled)
    }

    fun toggleMasterKana(kana: String) {
        repository.toggleMasterKana(kana)
    }

    fun toggleMasterKanji(kanji: String) {
        repository.toggleMasterKanji(kanji)
    }

    fun speak(text: String) {
        ttsManager.speak(text)
    }

    fun resetAllProgress() {
        repository.resetAllProgress()
    }

    override fun onCleared() {
        super.onCleared()
        ttsManager.destroy()
    }
}
