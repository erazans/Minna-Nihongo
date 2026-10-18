package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import com.example.ui.viewmodel.AppScreen
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.GoldenYellow
import com.example.ui.theme.JapanRedPrimary
import com.example.ui.theme.MatchaGreen
import com.example.ui.theme.StreakOrange

@Composable
fun TopAppHeader(
    completedUnitsCount: Int = 0,
    totalUnitsCount: Int = 25,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "⛩️", fontSize = 20.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Minna no Nihongo",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "みんなの日本語 • Leçons 1 à 25",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Quick options & settings button
            IconButton(
                onClick = onSettingsClick,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f))
                    .testTag("settings_header_button")
            ) {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = "Options & Réglages",
                    tint = JapanRedPrimary,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun JapaneseAudioButton(
    textToSpeak: String,
    onSpeak: (String) -> Unit,
    modifier: Modifier = Modifier,
    size: Int = 40
) {
    IconButton(
        onClick = { onSpeak(textToSpeak) },
        modifier = modifier
            .size(size.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .testTag("audio_tts_button_${textToSpeak.take(5)}")
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.VolumeUp,
            contentDescription = "Écouter la prononciation japonaise",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size((size * 0.55).dp)
        )
    }
}

@Composable
fun WordTile(
    word: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val bgColor by animateColorAsState(
        if (isSelected) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
        else MaterialTheme.colorScheme.surface,
        label = "tileBg"
    )
    val textColor = if (isSelected) MaterialTheme.colorScheme.outline else MaterialTheme.colorScheme.onSurface
    val borderColor = if (isSelected) Color.Transparent else MaterialTheme.colorScheme.outlineVariant

    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(enabled = !isSelected) { onClick() }
            .testTag("word_tile_$word"),
        color = bgColor,
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, borderColor),
        shadowElevation = if (isSelected) 0.dp else 2.dp
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = word,
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
                color = textColor
            )
        }
    }
}

@Composable
fun DuolingoFeedbackSheet(
    isCorrect: Boolean,
    explanation: String,
    targetSentenceJp: String,
    onSpeakTarget: () -> Unit,
    onContinue: () -> Unit,
    romajiMode: com.example.data.model.RomajiDisplayMode = com.example.data.model.RomajiDisplayMode.BOTH,
    modifier: Modifier = Modifier
) {
    val containerColor = if (isCorrect) Color(0xFFDCFCE7) else Color(0xFFFFE4E6)
    val contentColor = if (isCorrect) Color(0xFF166534) else Color(0xFF991B1B)
    val buttonColor = if (isCorrect) MatchaGreen else JapanRedPrimary

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = containerColor,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = if (isCorrect) Icons.Default.CheckCircle else Icons.Default.Cancel,
                        contentDescription = if (isCorrect) "Correct" else "Incorrect",
                        tint = contentColor,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = if (isCorrect) "Excellent !" else "Correction :",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = contentColor
                    )
                }

                IconButton(
                    onClick = onSpeakTarget,
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.8f))
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = "Écouter la phrase",
                        tint = contentColor,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            JapaneseFuriganaText(
                text = targetSentenceJp,
                romajiMode = romajiMode,
                fontSize = 18.sp,
                rubySize = 10.sp,
                romajiSize = 11.sp,
                textColor = contentColor,
                rubyColor = contentColor
            )

            if (explanation.isNotBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = explanation,
                    fontSize = 14.sp,
                    color = contentColor.copy(alpha = 0.9f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onContinue,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("feedback_continue_button"),
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text(
                    text = "CONTINUER",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun AppBottomNavigationBar(
    currentScreen: AppScreen,
    onNavigate: (AppScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp,
        modifier = modifier
    ) {
        NavigationBarItem(
            selected = currentScreen == AppScreen.HOME,
            onClick = {
                if (currentScreen != AppScreen.HOME) onNavigate(AppScreen.HOME)
            },
            icon = { Icon(Icons.Default.School, contentDescription = "Parcours") },
            label = { Text("Parcours") },
            modifier = Modifier.testTag("nav_home_button")
        )
        NavigationBarItem(
            selected = currentScreen == AppScreen.KANA_STUDY,
            onClick = {
                if (currentScreen != AppScreen.KANA_STUDY) onNavigate(AppScreen.KANA_STUDY)
            },
            icon = { Icon(Icons.Default.Translate, contentDescription = "Kana & Kanji") },
            label = { Text("Kana/Kanji") },
            modifier = Modifier.testTag("nav_kana_button")
        )
        NavigationBarItem(
            selected = currentScreen == AppScreen.GLOSSARY,
            onClick = {
                if (currentScreen != AppScreen.GLOSSARY) onNavigate(AppScreen.GLOSSARY)
            },
            icon = { Icon(Icons.AutoMirrored.Filled.MenuBook, contentDescription = "Glossaire") },
            label = { Text("Glossaire") },
            modifier = Modifier.testTag("nav_glossary_button")
        )
        NavigationBarItem(
            selected = currentScreen == AppScreen.AI_CONVERSATION,
            onClick = {
                if (currentScreen != AppScreen.AI_CONVERSATION) onNavigate(AppScreen.AI_CONVERSATION)
            },
            icon = { Icon(Icons.Default.Psychology, contentDescription = "Tuteur IA") },
            label = { Text("Tuteur IA") },
            modifier = Modifier.testTag("nav_ai_button")
        )
        NavigationBarItem(
            selected = currentScreen == AppScreen.STATS,
            onClick = {
                if (currentScreen != AppScreen.STATS) onNavigate(AppScreen.STATS)
            },
            icon = { Icon(Icons.Default.Settings, contentDescription = "Options") },
            label = { Text("Options") },
            modifier = Modifier.testTag("nav_options_button")
        )
    }
}

