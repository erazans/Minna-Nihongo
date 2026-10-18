package com.example.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.data.datasource.MinnaDatabase
import com.example.data.model.Lesson
import com.example.data.model.RomajiDisplayMode
import com.example.data.model.UserStats
import com.example.data.model.VocabularyWord
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class LearningRepository(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("minna_nihongo_prefs", Context.MODE_PRIVATE)

    private val _userStats = MutableStateFlow(loadUserStats())
    val userStats: StateFlow<UserStats> = _userStats.asStateFlow()

    private val _lessons = MutableStateFlow(loadLessons())
    val lessons: StateFlow<List<Lesson>> = _lessons.asStateFlow()

    init {
        checkStreakOnLaunch()
    }

    private fun loadUserStats(): UserStats {
        val streak = prefs.getInt("streak_days", 3)
        val lastDate = prefs.getString("last_active_date", "") ?: ""
        val totalXp = prefs.getInt("total_xp", 240)
        val todayXp = prefs.getInt("today_xp", 45)
        val dailyGoal = prefs.getInt("daily_goal_xp", 50)
        val lives = prefs.getInt("lives", 5)
        val maxLives = prefs.getInt("max_lives", 5)
        val completedLessons = prefs.getStringSet("completed_lessons", setOf("1"))?.mapNotNull { it.toIntOrNull() }?.toSet() ?: setOf(1)
        val bookmarked = prefs.getStringSet("bookmarked_words", emptySet()) ?: emptySet()
        val mastered = prefs.getStringSet("mastered_words", setOf("1_1", "1_2", "1_3")) ?: setOf("1_1", "1_2", "1_3")
        val totalQuestions = prefs.getInt("total_questions", 48)
        val correctAnswers = prefs.getInt("correct_answers", 44)
        val reminderEnabled = prefs.getBoolean("reminder_enabled", true)
        val reminderHour = prefs.getInt("reminder_hour", 19)
        val reminderMin = prefs.getInt("reminder_min", 0)
        val romajiModeStr = prefs.getString("romaji_display_mode", RomajiDisplayMode.BOTH.name) ?: RomajiDisplayMode.BOTH.name
        val romajiMode = try {
            RomajiDisplayMode.valueOf(romajiModeStr)
        } catch (_: Exception) {
            RomajiDisplayMode.BOTH
        }
        val defaultKana = setOf("あ", "い", "う", "え", "お", "ア", "イ", "ウ", "エ", "オ")
        val masteredKana = prefs.getStringSet("mastered_kana", defaultKana) ?: defaultKana

        return UserStats(
            streakDays = streak,
            lastActiveDate = lastDate,
            totalXp = totalXp,
            todayXp = todayXp,
            dailyGoalXp = dailyGoal,
            lives = lives,
            maxLives = maxLives,
            completedLessonIds = completedLessons,
            bookmarkedWordIds = bookmarked,
            masteredWordIds = mastered,
            totalQuestionsAnswered = totalQuestions,
            correctAnswers = correctAnswers,
            dailyReminderEnabled = reminderEnabled,
            reminderHour = reminderHour,
            reminderMinute = reminderMin,
            romajiDisplayMode = romajiMode,
            masteredKana = masteredKana
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

    private fun checkStreakOnLaunch() {
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val lastDate = _userStats.value.lastActiveDate

        if (lastDate.isEmpty()) {
            updateStats { it.copy(lastActiveDate = today) }
            prefs.edit().putString("last_active_date", today).apply()
            return
        }

        if (lastDate != today) {
            val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            try {
                val lastCalendar = Calendar.getInstance().apply { time = format.parse(lastDate) ?: Date() }
                val todayCalendar = Calendar.getInstance().apply { time = format.parse(today) ?: Date() }
                val diffDays = ((todayCalendar.timeInMillis - lastCalendar.timeInMillis) / (1000 * 60 * 60 * 24)).toInt()

                if (diffDays == 1) {
                    // Streak maintained! Reset today's XP counter
                    updateStats { it.copy(todayXp = 0, lastActiveDate = today) }
                    prefs.edit().putInt("today_xp", 0).putString("last_active_date", today).apply()
                } else if (diffDays > 1) {
                    // Streak lost (or reset to 1)
                    updateStats { it.copy(streakDays = 1, todayXp = 0, lastActiveDate = today) }
                    prefs.edit().putInt("streak_days", 1).putInt("today_xp", 0).putString("last_active_date", today).apply()
                }
            } catch (e: Exception) {
                // Ignore parse errors
            }
        }
    }

    fun completeLesson(lessonId: Int, starsEarned: Int, xpEarned: Int, correctCount: Int, totalCount: Int) {
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val current = _userStats.value

        val newStreak = if (current.lastActiveDate != today) current.streakDays + 1 else current.streakDays
        val newTotalXp = current.totalXp + xpEarned
        val newTodayXp = current.todayXp + xpEarned
        val newCompleted = current.completedLessonIds + lessonId
        val newTotalQ = current.totalQuestionsAnswered + totalCount
        val newCorrectQ = current.correctAnswers + correctCount

        prefs.edit()
            .putInt("streak_days", newStreak)
            .putString("last_active_date", today)
            .putInt("total_xp", newTotalXp)
            .putInt("today_xp", newTodayXp)
            .putStringSet("completed_lessons", newCompleted.map { it.toString() }.toSet())
            .putInt("lesson_stars_$lessonId", maxOf(starsEarned, prefs.getInt("lesson_stars_$lessonId", 0)))
            .putInt("total_questions", newTotalQ)
            .putInt("correct_answers", newCorrectQ)
            .apply()

        updateStats {
            it.copy(
                streakDays = newStreak,
                lastActiveDate = today,
                totalXp = newTotalXp,
                todayXp = newTodayXp,
                completedLessonIds = newCompleted,
                totalQuestionsAnswered = newTotalQ,
                correctAnswers = newCorrectQ
            )
        }

        _lessons.value = loadLessons()
    }

    fun addXp(xp: Int) {
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val current = _userStats.value
        val newTotal = current.totalXp + xp
        val newToday = current.todayXp + xp

        prefs.edit()
            .putInt("total_xp", newTotal)
            .putInt("today_xp", newToday)
            .putString("last_active_date", today)
            .apply()

        updateStats { it.copy(totalXp = newTotal, todayXp = newToday, lastActiveDate = today) }
    }

    fun loseLife(): Boolean {
        val current = _userStats.value.lives
        if (current <= 1) {
            updateStats { it.copy(lives = 0) }
            prefs.edit().putInt("lives", 0).apply()
            return false // Game over / No lives
        }
        val newLives = current - 1
        updateStats { it.copy(lives = newLives) }
        prefs.edit().putInt("lives", newLives).apply()
        return true
    }

    fun refillLives() {
        updateStats { it.copy(lives = it.maxLives) }
        prefs.edit().putInt("lives", _userStats.value.maxLives).apply()
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
    }

    fun updateRomajiDisplayMode(mode: RomajiDisplayMode) {
        prefs.edit().putString("romaji_display_mode", mode.name).apply()
        updateStats { it.copy(romajiDisplayMode = mode) }
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

    private fun updateStats(transform: (UserStats) -> UserStats) {
        _userStats.value = transform(_userStats.value)
    }
}
