package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.ChapterSectionType
import com.example.data.model.Lesson
import com.example.data.model.UserStats
import com.example.ui.components.TopAppHeader
import com.example.ui.theme.GoldenYellow
import com.example.ui.theme.JapanRedPrimary
import com.example.ui.theme.MatchaGreen
import com.example.ui.theme.StreakOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    userStats: UserStats,
    lessons: List<Lesson>,
    onLessonClick: (Lesson) -> Unit,
    onStartSection: (Lesson, ChapterSectionType) -> Unit,
    onOpenKanaStudy: () -> Unit,
    onOpenAiConversation: () -> Unit,
    onOpenGlossary: () -> Unit,
    onOpenStats: () -> Unit,
    onRefillLives: () -> Unit,
    bottomBar: @Composable () -> Unit = {
        com.example.ui.components.AppBottomNavigationBar(
            currentScreen = com.example.ui.viewmodel.AppScreen.HOME,
            onNavigate = { screen ->
                when (screen) {
                    com.example.ui.viewmodel.AppScreen.HOME -> {}
                    com.example.ui.viewmodel.AppScreen.KANA_STUDY -> onOpenKanaStudy()
                    com.example.ui.viewmodel.AppScreen.GLOSSARY -> onOpenGlossary()
                    com.example.ui.viewmodel.AppScreen.AI_CONVERSATION -> onOpenAiConversation()
                    com.example.ui.viewmodel.AppScreen.STATS -> onOpenStats()
                    else -> {}
                }
            }
        )
    },
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            Column(modifier = Modifier.fillMaxWidth().background(Color.Black)) {
                // Barre noire physique en haut du menu principal pour éviter le chevauchement avec la barre de notification et l'appareil photo/poinçon
                val statusBarInset = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
                val topBarHeight = maxOf(statusBarInset, 36.dp)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(topBarHeight)
                        .background(Color.Black)
                )
                TopAppHeader(
                    completedUnitsCount = userStats.completedLessonIds.size,
                    totalUnitsCount = lessons.size,
                    onSettingsClick = onOpenStats
                )
            }
        },
        bottomBar = bottomBar,
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 90.dp)
        ) {
            // Course Progression Banner
            item {
                MotivationalHeroBanner(
                    completedCount = userStats.completedLessonIds.size,
                    totalLessons = lessons.size,
                    onOpenStats = onOpenStats
                )
            }

            // Dedicated Kana Foundation Card (Hiragana & Katakana learning & Romaji option)
            item {
                KanaLearningBannerCard(
                    masteredCount = userStats.masteredKana.size,
                    romajiMode = userStats.romajiDisplayMode,
                    onOpenKana = onOpenKanaStudy
                )
            }

            // Exactly 1 Unit per chapter of Minna no Nihongo (25 units total)
            itemsIndexed(lessons) { index, lesson ->
                ChapterUnitBlock(
                    lesson = lesson,
                    unitIndex = index + 1,
                    isUnlocked = lesson.isUnlocked,
                    isCompleted = lesson.isCompleted,
                    onMainClick = { onLessonClick(lesson) },
                    onSectionClick = { section -> onStartSection(lesson, section) }
                )
            }
        }
    }
}

@Composable
fun ChapterUnitBlock(
    lesson: Lesson,
    unitIndex: Int,
    isUnlocked: Boolean,
    isCompleted: Boolean,
    onMainClick: () -> Unit,
    onSectionClick: (ChapterSectionType) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .testTag("unit_card_${lesson.id}"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isUnlocked) MaterialTheme.colorScheme.surface
            else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isUnlocked) 3.dp else 0.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Unit Header Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        if (isCompleted) Brush.horizontalGradient(listOf(MatchaGreen, Color(0xFF15803D)))
                        else if (isUnlocked) Brush.horizontalGradient(listOf(JapanRedPrimary, Color(0xFFBE123C)))
                        else Brush.horizontalGradient(listOf(Color(0xFF94A3B8), Color(0xFF64748B)))
                    )
                    .clickable(enabled = isUnlocked) { onMainClick() }
                    .padding(horizontal = 16.dp, vertical = 14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = lesson.iconEmoji, fontSize = 22.sp)
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Text(
                                text = "UNITÉ $unitIndex • ${lesson.titleJp}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Black,
                                color = Color.White.copy(alpha = 0.9f),
                                letterSpacing = 1.sp
                            )
                            Text(
                                text = lesson.titleFr,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }

                    if (isCompleted) {
                        Row {
                            repeat(3) { i ->
                                Text(
                                    text = if (i < lesson.stars) "⭐" else "☆",
                                    fontSize = 14.sp
                                )
                            }
                        }
                    } else if (!isUnlocked) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Verrouillé",
                            tint = Color.White
                        )
                    }
                }
            }

            // Description of Chapter
            Text(
                text = lesson.descriptionFr,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 8.dp)
            )

            // Book Sections along the progression path
            Text(
                text = "Exercices du manuel Minna no Nihongo :",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp)
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    SectionProgressChip(
                        type = ChapterSectionType.VOCABULARY,
                        isUnlocked = isUnlocked,
                        onClick = { onSectionClick(ChapterSectionType.VOCABULARY) }
                    )
                }
                item {
                    SectionProgressChip(
                        type = ChapterSectionType.RENSHUU_A,
                        isUnlocked = isUnlocked,
                        onClick = { onSectionClick(ChapterSectionType.RENSHUU_A) }
                    )
                }
                item {
                    SectionProgressChip(
                        type = ChapterSectionType.RENSHUU_B,
                        isUnlocked = isUnlocked,
                        isFeatured = true,
                        onClick = { onSectionClick(ChapterSectionType.RENSHUU_B) }
                    )
                }
                item {
                    SectionProgressChip(
                        type = ChapterSectionType.RENSHUU_C,
                        isUnlocked = isUnlocked,
                        onClick = { onSectionClick(ChapterSectionType.RENSHUU_C) }
                    )
                }
                item {
                    SectionProgressChip(
                        type = ChapterSectionType.REVISION,
                        isUnlocked = isUnlocked,
                        isFeatured = true,
                        onClick = { onSectionClick(ChapterSectionType.REVISION) }
                    )
                }
                item {
                    SectionProgressChip(
                        type = ChapterSectionType.MONDAI,
                        isUnlocked = isUnlocked,
                        onClick = { onSectionClick(ChapterSectionType.MONDAI) }
                    )
                }
            }
        }
    }
}

@Composable
fun SectionProgressChip(
    type: ChapterSectionType,
    isUnlocked: Boolean,
    isFeatured: Boolean = false,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(enabled = isUnlocked) { onClick() }
            .border(
                width = if (isFeatured) 1.5.dp else 1.dp,
                color = if (isFeatured) JapanRedPrimary else MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(12.dp)
            ),
        color = if (isFeatured) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
        else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = type.iconEmoji, fontSize = 14.sp)
            Spacer(modifier = Modifier.width(6.dp))
            Column {
                Text(
                    text = type.shortLabel,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    color = if (isFeatured) JapanRedPrimary else MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = type.titleFr.substringBefore(" ("),
                    fontSize = 9.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun MotivationalHeroBanner(
    completedCount: Int,
    totalLessons: Int,
    onOpenStats: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onOpenStats() }
            .testTag("motivational_banner"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFFEF2F2),
                            Color(0xFFFFF7ED)
                        )
                    )
                )
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFEE2E2)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "⛩️", fontSize = 26.sp)
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Progression du Manuel",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Minna no Nihongo : $completedCount/$totalLessons unités validées",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    LinearProgressIndicator(
                        progress = { (completedCount.toFloat() / totalLessons.toFloat()).coerceIn(0f, 1f) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = JapanRedPrimary,
                        trackColor = Color(0xFFFEE2E2)
                    )
                }
            }
        }
    }
}

@Composable
fun KanaLearningBannerCard(
    masteredCount: Int,
    romajiMode: com.example.data.model.RomajiDisplayMode,
    onOpenKana: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .clickable { onOpenKana() }
            .testTag("kana_learning_banner"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFEFF6FF),
                            Color(0xFFF5F3FF)
                        )
                    )
                )
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFDBEAFE)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "あ時", fontSize = 18.sp, fontWeight = FontWeight.Black, color = Color(0xFF1D4ED8))
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Kana, Kanji, Heures & Nombres",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Kana, Kanji, Chiffres & Heures (Particularités L.4)",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFF2563EB)
                    ) {
                        Text(
                            text = "Étudier",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color.White.copy(alpha = 0.9f)
                ) {
                    Text(
                        text = "✓ $masteredCount kana maîtrisés",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1D4ED8),
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                    )
                }
            }
        }
    }
}

