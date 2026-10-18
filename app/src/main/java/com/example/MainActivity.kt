package com.example

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.ui.screens.*
import com.example.ui.theme.MinnaNihongoTheme
import com.example.ui.viewmodel.AppScreen
import com.example.ui.viewmodel.LearningViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: LearningViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Clear any old or lingering notifications so the app does not remain in the notification bar
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancelAll()

        setContent {
            MinnaNihongoTheme {
                val currentScreen by viewModel.currentScreen.collectAsState()
                val userStats by viewModel.userStats.collectAsState()
                val lessons by viewModel.lessons.collectAsState()
                val selectedLesson by viewModel.selectedLesson.collectAsState()

                Surface(modifier = Modifier.fillMaxSize()) {
                    val selectedSection by viewModel.selectedSection.collectAsState()

                    val bottomNavBar: @Composable () -> Unit = {
                        com.example.ui.components.AppBottomNavigationBar(
                            currentScreen = currentScreen,
                            onNavigate = { screen -> viewModel.navigateTo(screen) }
                        )
                    }

                    when (currentScreen) {
                        AppScreen.HOME -> {
                            HomeScreen(
                                userStats = userStats,
                                lessons = lessons,
                                onLessonClick = { lesson ->
                                    viewModel.openLesson(lesson)
                                },
                                onStartSection = { lesson, section ->
                                    viewModel.startExercises(lesson, section)
                                },
                                onOpenKanaStudy = {
                                    viewModel.navigateTo(AppScreen.KANA_STUDY)
                                },
                                onOpenAiConversation = {
                                    viewModel.navigateTo(AppScreen.AI_CONVERSATION)
                                },
                                onOpenGlossary = {
                                    viewModel.navigateTo(AppScreen.GLOSSARY)
                                },
                                onOpenStats = {
                                    viewModel.navigateTo(AppScreen.STATS)
                                },
                                onRefillLives = { /* Lives system removed */ },
                                bottomBar = bottomNavBar
                            )
                        }

                        AppScreen.KANA_STUDY -> {
                            BackHandler { viewModel.onBack() }
                            KanaStudyScreen(
                                onBack = { viewModel.onBack() },
                                onSpeak = { text -> viewModel.speak(text) },
                                currentRomajiMode = userStats.romajiDisplayMode,
                                masteredKana = userStats.masteredKana,
                                onToggleMasterKana = { kana -> viewModel.toggleMasterKana(kana) },
                                masteredKanji = userStats.masteredKanji,
                                onToggleMasterKanji = { kanji -> viewModel.toggleMasterKanji(kanji) },
                                onAddXp = { /* XP system removed */ },
                                bottomBar = bottomNavBar
                            )
                        }

                        AppScreen.LESSON_DETAIL -> {
                            BackHandler { viewModel.onBack() }
                            selectedLesson?.let { lesson ->
                                LessonDetailScreen(
                                    lesson = lesson,
                                    onBack = { viewModel.onBack() },
                                    onStartExercises = { l -> viewModel.startExercises(l) },
                                    onStartSection = { l, section -> viewModel.startExercises(l, section) },
                                    onSpeak = { text -> viewModel.speak(text) },
                                    bookmarkedWords = userStats.bookmarkedWordIds,
                                    onToggleBookmark = { id -> viewModel.toggleBookmarkWord(id) },
                                    romajiMode = userStats.romajiDisplayMode
                                )
                            } ?: run {
                                viewModel.onBack()
                            }
                        }

                        AppScreen.EXERCISE_SESSION -> {
                            BackHandler { viewModel.onBack() }
                            selectedLesson?.let { lesson ->
                                ExerciseScreen(
                                    lesson = lesson,
                                    targetSection = selectedSection,
                                    onBack = { viewModel.onBack() },
                                    onComplete = { stars, xp, correct, total ->
                                        viewModel.completeExerciseSession(stars, xp, correct, total)
                                    },
                                    onSpeak = { text -> viewModel.speak(text) },
                                    onLoseLife = { viewModel.loseLife() },
                                    currentLives = 5,
                                    romajiMode = userStats.romajiDisplayMode
                                )
                            } ?: run {
                                viewModel.onBack()
                            }
                        }

                        AppScreen.AI_CONVERSATION -> {
                            BackHandler { viewModel.onBack() }
                            AiConversationScreen(
                                onBack = { viewModel.onBack() },
                                onSpeak = { text -> viewModel.speak(text) },
                                onAddXp = { /* XP removed */ },
                                romajiMode = userStats.romajiDisplayMode,
                                bottomBar = bottomNavBar
                            )
                        }

                        AppScreen.GLOSSARY -> {
                            BackHandler { viewModel.onBack() }
                            GlossaryScreen(
                                onBack = { viewModel.onBack() },
                                onSpeak = { text -> viewModel.speak(text) },
                                bookmarkedWordIds = userStats.bookmarkedWordIds,
                                masteredWordIds = userStats.masteredWordIds,
                                onToggleBookmark = { id -> viewModel.toggleBookmarkWord(id) },
                                onToggleMastered = { id -> viewModel.toggleMasteredWord(id) },
                                romajiMode = userStats.romajiDisplayMode,
                                bottomBar = bottomNavBar
                            )
                        }

                        AppScreen.STATS -> {
                            BackHandler { viewModel.onBack() }
                            StatsStreakScreen(
                                userStats = userStats,
                                onBack = { viewModel.onBack() },
                                onUpdateReminder = { enabled, hour, min ->
                                    viewModel.setDailyReminder(enabled, hour, min)
                                },
                                onUpdateRomajiMode = { mode -> viewModel.updateRomajiDisplayMode(mode) },
                                onResetProgress = { viewModel.resetAllProgress() },
                                bottomBar = bottomNavBar
                            )
                        }
                    }
                }
            }
        }
    }
}
