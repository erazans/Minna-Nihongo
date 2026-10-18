package com.example.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.data.model.RomajiDisplayMode
import com.example.data.model.UserStats
import com.example.service.DailyNotificationHelper
import com.example.ui.components.RomajiModeSelectorBar
import com.example.ui.theme.GoldenYellow
import com.example.ui.theme.JapanRedPrimary
import com.example.ui.theme.MatchaGreen
import com.example.ui.theme.StreakOrange

data class Achievement(
    val title: String,
    val description: String,
    val emoji: String,
    val isUnlocked: Boolean,
    val progress: Float
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatsStreakScreen(
    userStats: UserStats,
    onBack: () -> Unit,
    onUpdateReminder: (enabled: Boolean, hour: Int, minute: Int) -> Unit,
    onUpdateRomajiMode: (RomajiDisplayMode) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var reminderEnabled by remember { mutableStateOf(userStats.dailyReminderEnabled) }
    var notificationSentFeedback by remember { mutableStateOf(false) }

    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            DailyNotificationHelper.showDailyStreakReminder(context, userStats.streakDays)
            notificationSentFeedback = true
        }
    }

    val achievements = listOf(
        Achievement("Premier pas", "Terminez votre première leçon", "🌱", userStats.completedLessonIds.isNotEmpty(), 1f),
        Achievement("Flamme 3 jours", "Maintenez une série de 3 jours", "🔥", userStats.streakDays >= 3, (userStats.streakDays / 3f).coerceAtMost(1f)),
        Achievement("Semaine d'or", "Maintenez une série de 7 jours", "⚡", userStats.streakDays >= 7, (userStats.streakDays / 7f).coerceAtMost(1f)),
        Achievement("Polyglotte IMC", "Terminez les 5 premières leçons", "🏢", userStats.completedLessonIds.size >= 5, (userStats.completedLessonIds.size / 5f).coerceAtMost(1f)),
        Achievement("Maître des Particules", "Atteignez 90% de précision globale", "🎯", userStats.totalQuestionsAnswered > 20 && (userStats.correctAnswers.toFloat() / userStats.totalQuestionsAnswered) >= 0.85f, 0.9f),
        Achievement("Vocabulaire Express", "Maîtrisez 20 mots dans le glossaire", "📚", userStats.masteredWordIds.size >= 20, (userStats.masteredWordIds.size / 20f).coerceAtMost(1f))
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Motivation & Statistiques", fontWeight = FontWeight.Bold, fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.testTag("stats_back_button")
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Streak Big Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Brush.verticalGradient(
                                    listOf(Color(0xFFFFF7ED), Color(0xFFFEF2F2))
                                )
                            )
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "🔥", fontSize = 64.sp)
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "${userStats.streakDays} Jours de Série !",
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Black,
                                color = StreakOrange
                            )
                            Text(
                                text = "Revenez chaque jour pour faire vivre la flamme !",
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(18.dp))

                            // 7 Days Week Ribbon
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                val days = listOf("L", "M", "M", "J", "V", "S", "D")
                                days.forEachIndexed { i, dayName ->
                                    val isDayActive = i < minOf(userStats.streakDays, 7)
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(text = dayName, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.outline)
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Box(
                                            modifier = Modifier
                                                .size(36.dp)
                                                .clip(CircleShape)
                                                .background(if (isDayActive) StreakOrange else Color(0xFFE2E8F0)),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = if (isDayActive) "✓" else "",
                                                color = Color.White,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Stats Grid
            item {
                Text(text = "Vos Statistiques", fontSize = 17.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))

                val accuracy = if (userStats.totalQuestionsAnswered > 0) {
                    ((userStats.correctAnswers.toFloat() / userStats.totalQuestionsAnswered) * 100).toInt()
                } else 100

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    StatCard(title = "Total XP", value = "${userStats.totalXp}", icon = "⚡", color = GoldenYellow, modifier = Modifier.weight(1f))
                    StatCard(title = "Précision", value = "$accuracy%", icon = "🎯", color = MatchaGreen, modifier = Modifier.weight(1f))
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    StatCard(title = "Leçons réussies", value = "${userStats.completedLessonIds.size}/25", icon = "📖", color = JapanRedPrimary, modifier = Modifier.weight(1f))
                    StatCard(title = "Mots maîtrisés", value = "${userStats.masteredWordIds.size}", icon = "🎴", color = Color(0xFF6366F1), modifier = Modifier.weight(1f))
                }
            }

            // Daily Notification Reminder Settings
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.NotificationsActive, contentDescription = null, tint = JapanRedPrimary)
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(text = "Rappel Quotidien", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                                    Text(text = "Notification chaque jour à 19h00", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                            }
                            Switch(
                                checked = reminderEnabled,
                                onCheckedChange = {
                                    reminderEnabled = it
                                    onUpdateReminder(it, 19, 0)
                                }
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                    val hasPerm = ContextCompat.checkSelfPermission(
                                        context,
                                        Manifest.permission.POST_NOTIFICATIONS
                                    ) == PackageManager.PERMISSION_GRANTED
                                    if (hasPerm) {
                                        DailyNotificationHelper.showDailyStreakReminder(context, userStats.streakDays)
                                        notificationSentFeedback = true
                                    } else {
                                        notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                                    }
                                } else {
                                    DailyNotificationHelper.showDailyStreakReminder(context, userStats.streakDays)
                                    notificationSentFeedback = true
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("test_notification_button"),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = if (notificationSentFeedback) "Notification envoyée ! 🔔" else "Tester la notification de rappel",
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                        }
                    }
                }
            }

            // Display Preferences: Alphabet Latin (Romaji) & Furigana
            item {
                Text(text = "Affichage & Furigana", fontSize = 17.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Tous les kanji de l'application affichent des furigana. Choisissez où afficher l'alphabet latin (Romaji) :",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(6.dp))
                RomajiModeSelectorBar(
                    currentMode = userStats.romajiDisplayMode,
                    onModeSelected = onUpdateRomajiMode,
                    modifier = Modifier.padding(horizontal = 0.dp)
                )
                Spacer(modifier = Modifier.height(14.dp))
            }

            // Achievements
            item {
                Text(text = "Succès & Trophées", fontSize = 17.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
            }

            items(achievements.size) { idx ->
                val ach = achievements[idx]
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(if (ach.isUnlocked) Color(0xFFFEF3C7) else Color(0xFFF1F5F9)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = ach.emoji, fontSize = 22.sp)
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = ach.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = if (ach.isUnlocked) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.outline
                            )
                            Text(
                                text = ach.description,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            LinearProgressIndicator(
                                progress = { ach.progress },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(4.dp)
                                    .clip(RoundedCornerShape(2.dp)),
                                color = if (ach.isUnlocked) MatchaGreen else GoldenYellow,
                                trackColor = Color(0xFFE2E8F0)
                            )
                        }

                        if (ach.isUnlocked) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "⭐", fontSize = 18.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StatCard(
    title: String,
    value: String,
    icon: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = icon, fontSize = 20.sp)
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(color)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = value, fontSize = 20.sp, fontWeight = FontWeight.Black, color = MaterialTheme.colorScheme.onSurface)
            Text(text = title, fontSize = 12.sp, color = MaterialTheme.colorScheme.outline)
        }
    }
}
