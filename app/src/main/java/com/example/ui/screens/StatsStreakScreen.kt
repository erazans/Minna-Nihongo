package com.example.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.VolumeOff
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.ui.theme.JapanRedPrimary
import com.example.ui.theme.MatchaGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatsStreakScreen(
    userStats: UserStats,
    onBack: () -> Unit,
    onUpdateReminder: (enabled: Boolean, hour: Int, minute: Int) -> Unit,
    onUpdateRomajiMode: (RomajiDisplayMode) -> Unit = {},
    onToggleAutoPronounce: (Boolean) -> Unit = {},
    onResetProgress: () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var reminderEnabled by remember { mutableStateOf(userStats.dailyReminderEnabled) }
    var selectedHour by remember { mutableIntStateOf(userStats.reminderHour) }
    var selectedMinute by remember { mutableIntStateOf(userStats.reminderMinute) }
    var notificationSentFeedback by remember { mutableStateOf(false) }
    var showResetConfirmDialog by remember { mutableStateOf(false) }

    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            DailyNotificationHelper.showDailyReminderNotification(context)
            notificationSentFeedback = true
        }
    }

    if (showResetConfirmDialog) {
        AlertDialog(
            onDismissRequest = { showResetConfirmDialog = false },
            title = {
                Text(
                    text = "Remettre l'application à zéro ?",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    "Cette action réinitialisera toute votre progression, vos leçons complétées et vos favoris comme si l'application venait tout juste d'être installée. Toutes les 25 unités resteront déverrouillées."
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showResetConfirmDialog = false
                        onResetProgress()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = JapanRedPrimary)
                ) {
                    Text("Oui, tout réinitialiser", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetConfirmDialog = false }) {
                    Text("Annuler")
                }
            }
        )
    }

    Scaffold(
        bottomBar = bottomBar,
        topBar = {
            TopAppBar(
                title = { Text("Options & Réglages", fontWeight = FontWeight.Bold, fontSize = 18.sp) },
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
                                Box(
                                    modifier = Modifier
                                        .size(42.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFFFEE2E2)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(Icons.Default.NotificationsActive, contentDescription = null, tint = JapanRedPrimary)
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(text = "Rappel Quotidien", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                    val formattedTime = String.format("%02dh%02d", selectedHour, selectedMinute)
                                    Text(
                                        text = if (reminderEnabled) "Programmé chaque jour à $formattedTime" else "Rappels désactivés",
                                        fontSize = 12.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                            Switch(
                                checked = reminderEnabled,
                                onCheckedChange = {
                                    reminderEnabled = it
                                    onUpdateReminder(it, selectedHour, selectedMinute)
                                }
                            )
                        }

                        if (reminderEnabled) {
                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "Choisissez l'heure de rappel :",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            // Quick preset times
                            val presets = listOf(
                                Pair(8, 0) to "08:00 (Matin)",
                                Pair(12, 30) to "12:30 (Midi)",
                                Pair(18, 0) to "18:00 (Soir)",
                                Pair(19, 0) to "19:00 (Défaut)",
                                Pair(20, 30) to "20:30 (Nuit)",
                                Pair(21, 0) to "21:00 (Tard)"
                            )

                            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                presets.chunked(3).forEach { rowPresets ->
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        rowPresets.forEach { (time, label) ->
                                            val isSelected = selectedHour == time.first && selectedMinute == time.second
                                            FilterChip(
                                                selected = isSelected,
                                                onClick = {
                                                    selectedHour = time.first
                                                    selectedMinute = time.second
                                                    onUpdateReminder(true, time.first, time.second)
                                                },
                                                label = {
                                                    Text(
                                                        text = label,
                                                        fontSize = 11.sp,
                                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                                    )
                                                },
                                                modifier = Modifier.weight(1f)
                                            )
                                        }
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            // Precise Hour & Minute Adjuster
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                                    .padding(horizontal = 14.dp, vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Heure personnalisée :",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Medium
                                )

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    // Hour -
                                    IconButton(
                                        onClick = {
                                            selectedHour = if (selectedHour <= 0) 23 else selectedHour - 1
                                            onUpdateReminder(true, selectedHour, selectedMinute)
                                        },
                                        modifier = Modifier.size(32.dp)
                                    ) {
                                        Text("-", fontWeight = FontWeight.Black, fontSize = 16.sp)
                                    }

                                    Text(
                                        text = String.format("%02d", selectedHour),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(horizontal = 4.dp)
                                    )

                                    // Hour +
                                    IconButton(
                                        onClick = {
                                            selectedHour = (selectedHour + 1) % 24
                                            onUpdateReminder(true, selectedHour, selectedMinute)
                                        },
                                        modifier = Modifier.size(32.dp)
                                    ) {
                                        Text("+", fontWeight = FontWeight.Black, fontSize = 16.sp)
                                    }

                                    Text(text = " : ", fontWeight = FontWeight.Bold, fontSize = 16.sp)

                                    // Minute Toggle (00, 15, 30, 45)
                                    Button(
                                        onClick = {
                                            selectedMinute = (selectedMinute + 15) % 60
                                            onUpdateReminder(true, selectedHour, selectedMinute)
                                        },
                                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp),
                                        modifier = Modifier.height(32.dp),
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Text(
                                            text = String.format("%02d min", selectedMinute),
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Test Notification Button
                        Button(
                            onClick = {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                    val hasPerm = ContextCompat.checkSelfPermission(
                                        context,
                                        Manifest.permission.POST_NOTIFICATIONS
                                    ) == PackageManager.PERMISSION_GRANTED
                                    if (hasPerm) {
                                        DailyNotificationHelper.showDailyReminderNotification(context)
                                        notificationSentFeedback = true
                                    } else {
                                        notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                                    }
                                } else {
                                    DailyNotificationHelper.showDailyReminderNotification(context)
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

                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "ℹ️ L'application est optimisée pour ne pas encombrer votre barre d'état. Les notifications s'effacent automatiquement dès que vous cliquez dessus.",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 15.sp
                        )
                    }
                }
            }

            // Display Preferences: Alphabet Latin (Romaji) & Furigana
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Translate, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Affichage du Romaji & Furigana",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Choisissez comment les kana et kanji sont affichés dans les leçons et les révisions.",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        RomajiModeSelectorBar(
                            currentMode = userStats.romajiDisplayMode,
                            onModeSelected = onUpdateRomajiMode
                        )
                    }
                }
            }

            // Audio Preferences: Prononciation automatique des mots japonais
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape)
                                        .background(MaterialTheme.colorScheme.primaryContainer),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = if (userStats.autoPronounceWords) Icons.AutoMirrored.Filled.VolumeUp else Icons.AutoMirrored.Filled.VolumeOff,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(22.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(
                                        text = "Prononciation automatique",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = if (userStats.autoPronounceWords) "Lecture audio active" else "Lecture audio désactivée",
                                        fontSize = 12.sp,
                                        color = if (userStats.autoPronounceWords) Color(0xFF059669) else MaterialTheme.colorScheme.outline
                                    )
                                }
                            }

                            Switch(
                                checked = userStats.autoPronounceWords,
                                onCheckedChange = onToggleAutoPronounce,
                                modifier = Modifier.testTag("switch_auto_pronounce"),
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = JapanRedPrimary
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Prononce automatiquement les mots japonais, phrases et questions lors des exercices, quiz et conversations IA. Les boutons d'écoute manuelle restent toujours disponibles.",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 16.sp
                        )
                    }
                }
            }

            // Course Learning Statistics (clean learning stats without gamification)
            item {
                Text(
                    text = "Bilan d'apprentissage",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            item {
                val accuracy = if (userStats.totalQuestionsAnswered > 0) {
                    ((userStats.correctAnswers.toFloat() / userStats.totalQuestionsAnswered) * 100).toInt()
                } else 100

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    StatCard(
                        title = "Leçons terminées",
                        value = "${userStats.completedLessonIds.size} / 25",
                        icon = "📖",
                        color = JapanRedPrimary,
                        modifier = Modifier.weight(1f)
                    )
                    StatCard(
                        title = "Précision globale",
                        value = "$accuracy%",
                        icon = "🎯",
                        color = MatchaGreen,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    StatCard(
                        title = "Questions traitées",
                        value = "${userStats.totalQuestionsAnswered}",
                        icon = "✏️",
                        color = Color(0xFF6366F1),
                        modifier = Modifier.weight(1f)
                    )
                    StatCard(
                        title = "Favoris sauvegardés",
                        value = "${userStats.bookmarkedWordIds.size}",
                        icon = "⭐",
                        color = Color(0xFFF59E0B),
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Reset to factory zero
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(Color(0xFFFECDD3))),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.RestartAlt, contentDescription = null, tint = JapanRedPrimary)
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Remise à zéro de l'application",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = JapanRedPrimary
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Efface l'historique de complétion et remet l'application à zéro comme au premier téléchargement.",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        OutlinedButton(
                            onClick = { showResetConfirmDialog = true },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("reset_app_button"),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = JapanRedPrimary)
                        ) {
                            Icon(Icons.Default.DeleteOutline, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Remettre l'application à zéro",
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
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
            Text(text = icon, fontSize = 22.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = value, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = color)
            Text(text = title, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
