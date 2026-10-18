package com.example.data.repository

import android.content.Context
import com.example.data.datasource.MinnaDatabase
import com.example.data.model.Lesson
import com.example.data.model.RomajiDisplayMode
import com.example.data.model.UserStats
import com.example.service.DailyNotificationHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.*

class LearningRepository(private val context: Context) {

    private val prefs = context.getSharedPreferences("minna_learning_prefs", Context.MODE_PRIVATE)

    private val _userStats = MutableStateFlow(loadUserStats())
    val userStats: StateFlow<UserStats> = _userStats.asStateFlow()

    private val _lessons = MutableStateFlow(loadLessons())
    val lessons: StateFlow<List<Lesson>> = _lessons.asStateFlow()

    init {
        // Ensure reminder is scheduled if enabled
        val stats = _userStats.value
        DailyNotificationHelper.scheduleDailyReminder(
            context,
            stats.reminderHour,
            stats.reminderMinute,
            stats.dailyReminderEnabled
        )
    }

    private fun loadUserStats(): UserStats {
        // If not initialized yet with the fresh reset flag, reset completely to zero
        val isFresh = prefs.getBoolean("app_fresh_downloaded_v3", false)
        if (!isFresh) {
            prefs.edit().clear().putBoolean("app_fresh_downloaded_v3", true).apply()
            return UserStats(
                streakDays = 0,
                lastActiveDate = "",
                totalXp = 0,
                todayXp = 0,
                dailyGoalXp = 0,
                lives = 5,
                maxLives = 5,
                completedLessonIds = emptySet(),
                bookmarkedWordIds = emptySet(),
                masteredWordIds = emptySet(),
                lessonsProgress = emptyMap(),
                totalQuestionsAnswered = 0,
                correctAnswers = 0,
                dailyReminderEnabled = true,
                reminderHour = 19,
                reminderMinute = 0,
                romajiDisplayMode = RomajiDisplayMode.BOTH,
                masteredKana = emptySet()
            )
        }

        val completedLessons = prefs.getStringSet("completed_lessons", emptySet())
            ?.mapNotNull { it.toIntOrNull() }?.toSet() ?: emptySet()
        val bookmarked = prefs.getStringSet("bookmarked_words", emptySet()) ?: emptySet()
        val mastered = prefs.getStringSet("mastered_words", emptySet()) ?: emptySet()
        val totalQuestions = prefs.getInt("total_questions", 0)
        val correctAnswers = prefs.getInt("correct_answers", 0)
        val reminderEnabled = prefs.getBoolean("reminder_enabled", true)
        val reminderHour = prefs.getInt("reminder_hour", 19)
        val reminderMin = prefs.getInt("reminder_min", 0)
        val romajiModeStr = prefs.getString("romaji_display_mode", RomajiDisplayMode.BOTH.name) ?: RomajiDisplayMode.BOTH.name
        val romajiMode = try {
            RomajiDisplayMode.valueOf(romajiModeStr)
        } catch (_: Exception) {
            RomajiDisplayMode.BOTH
        }
        val masteredKana = prefs.getStringSet("mastered_kana", emptySet()) ?: emptySet()
        val masteredKanji = prefs.getStringSet("mastered_kanji", emptySet()) ?: emptySet()
        val autoPronounce = prefs.getBoolean("auto_pronounce_words", true)

        return UserStats(
            streakDays = 0,
            lastActiveDate = "",
            totalXp = 0,
            todayXp = 0,
            dailyGoalXp = 0,
            lives = 5,
            maxLives = 5,
            completedLessonIds = completedLessons,
            bookmarkedWordIds = bookmarked,
            masteredWordIds = mastered,
            totalQuestionsAnswered = totalQuestions,
            correctAnswers = correctAnswers,
            dailyReminderEnabled = reminderEnabled,
            reminderHour = reminderHour,
            reminderMinute = reminderMin,
            romajiDisplayMode = romajiMode,
            autoPronounceWords = autoPronounce,
            masteredKana = masteredKana,
            masteredKanji = masteredKanji
        )
    }

    private fun loadLessons(): List<Lesson> {
        val completed = _userStats.value.completedLessonIds
        return MinnaDatabase.lessons.map { lesson ->
            val isCompleted = completed.contains(lesson.id)
            // All units unlocked by default from the start
            val isUnlocked = true
            val stars = prefs.getInt("lesson_stars_${lesson.id}", if (isCompleted) 3 else 0)
            lesson.copy(isUnlocked = isUnlocked, isCompleted = isCompleted, stars = stars)
        }
    }

    fun completeLesson(lessonId: Int, starsEarned: Int, xpEarned: Int = 0, correctCount: Int = 0, totalCount: Int = 0) {
        val current = _userStats.value
        val newCompleted = current.completedLessonIds + lessonId
        val newTotalQ = current.totalQuestionsAnswered + totalCount
        val newCorrectQ = current.correctAnswers + correctCount

        prefs.edit()
            .putStringSet("completed_lessons", newCompleted.map { it.toString() }.toSet())
            .putInt("lesson_stars_$lessonId", maxOf(starsEarned, prefs.getInt("lesson_stars_$lessonId", 0)))
            .putInt("total_questions", newTotalQ)
            .putInt("correct_answers", newCorrectQ)
            .apply()

        updateStats {
            it.copy(
                completedLessonIds = newCompleted,
                totalQuestionsAnswered = newTotalQ,
                correctAnswers = newCorrectQ
            )
        }

        _lessons.value = loadLessons()
    }

    fun addXp(xp: Int) {
        // Experience system removed; no-op
    }

    fun loseLife(): Boolean {
        // Life system removed; always allow learner to continue without penalty
        return true
    }

    fun refillLives() {
        // No-op
    }

    fun toggleBookmarkWord(wordId: String) {
        val current = _userStats.value.bookmarkedWordIds.toMutableSet()
        if (current.contains(wordId)) {
            current.remove(wordId)
        } else {
            current.add(wordId)
        }
        prefs.edit().putStringSet("bookmarked_words", current).apply()
        updateStats { it.copy(bookmarkedWordIds = current) }
    }

    fun toggleMasteredWord(wordId: String) {
        val current = _userStats.value.masteredWordIds.toMutableSet()
        if (current.contains(wordId)) {
            current.remove(wordId)
        } else {
            current.add(wordId)
        }
        prefs.edit().putStringSet("mastered_words", current).apply()
        updateStats { it.copy(masteredWordIds = current) }
    }

    fun setDailyReminder(enabled: Boolean, hour: Int, minute: Int) {
        prefs.edit()
            .putBoolean("reminder_enabled", enabled)
            .putInt("reminder_hour", hour)
            .putInt("reminder_min", minute)
            .apply()

        updateStats {
            it.copy(
                dailyReminderEnabled = enabled,
                reminderHour = hour,
                reminderMinute = minute
            )
        }

        DailyNotificationHelper.scheduleDailyReminder(context, hour, minute, enabled)
    }

    fun updateRomajiDisplayMode(mode: RomajiDisplayMode) {
        prefs.edit().putString("romaji_display_mode", mode.name).apply()
        updateStats { it.copy(romajiDisplayMode = mode) }
    }

    fun updateAutoPronounce(enabled: Boolean) {
        prefs.edit().putBoolean("auto_pronounce_words", enabled).apply()
        updateStats { it.copy(autoPronounceWords = enabled) }
    }

    fun toggleMasterKana(kana: String) {
        val current = _userStats.value.masteredKana.toMutableSet()
        if (current.contains(kana)) {
            current.remove(kana)
        } else {
            current.add(kana)
        }
        prefs.edit().putStringSet("mastered_kana", current).apply()
        updateStats { it.copy(masteredKana = current) }
    }

    fun toggleMasterKanji(kanji: String) {
        val current = _userStats.value.masteredKanji.toMutableSet()
        if (current.contains(kanji)) {
            current.remove(kanji)
        } else {
            current.add(kanji)
        }
        prefs.edit().putStringSet("mastered_kanji", current).apply()
        updateStats { it.copy(masteredKanji = current) }
    }

    /**
     * Resets the application completely to 0 as if it was just downloaded.
     */
    fun resetAllProgress() {
        prefs.edit().clear().putBoolean("app_fresh_downloaded_v3", true).apply()
        _userStats.value = UserStats(
            streakDays = 0,
            lastActiveDate = "",
            totalXp = 0,
            todayXp = 0,
            dailyGoalXp = 0,
            lives = 5,
            maxLives = 5,
            completedLessonIds = emptySet(),
            bookmarkedWordIds = emptySet(),
            masteredWordIds = emptySet(),
            lessonsProgress = emptyMap(),
            totalQuestionsAnswered = 0,
            correctAnswers = 0,
            dailyReminderEnabled = true,
            reminderHour = 19,
            reminderMinute = 0,
            romajiDisplayMode = RomajiDisplayMode.BOTH,
            autoPronounceWords = true,
            masteredKana = emptySet(),
            masteredKanji = emptySet()
        )
        _lessons.value = loadLessons()
    }

    private fun updateStats(transform: (UserStats) -> UserStats) {
        _userStats.value = transform(_userStats.value)
    }
}
