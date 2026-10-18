package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.datasource.MinnaDatabase
import com.example.data.model.GrammarPoint
import com.example.data.model.Lesson
import com.example.data.model.LessonDialogue
import com.example.data.model.RomajiDisplayMode
import com.example.data.model.VocabularyWord
import com.example.ui.components.JapaneseAudioButton
import com.example.ui.components.JapaneseFuriganaText
import com.example.ui.theme.JapanRedPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonDetailScreen(
    lesson: Lesson,
    onBack: () -> Unit,
    onStartExercises: (Lesson) -> Unit,
    onStartSection: (Lesson, com.example.data.model.ChapterSectionType) -> Unit,
    onSpeak: (String) -> Unit,
    bookmarkedWords: Set<String>,
    onToggleBookmark: (String) -> Unit,
    romajiMode: RomajiDisplayMode = RomajiDisplayMode.BOTH,
    modifier: Modifier = Modifier
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Vocabulaire", "Grammaire", "Dialogue (会話)")

    // Filter data for this lesson
    val lessonVocab = remember(lesson.id) {
        val list = MinnaDatabase.vocabulary.filter { it.lessonId == lesson.id }
        if (list.isEmpty()) {
            // Generate standard preview items for later lessons from textbook
            listOf(
                VocabularyWord("gen_${lesson.id}_1", lesson.id, "勉強", "べんきょう", "benkyou", "Étude", com.example.data.model.WordCategory.NOUN, "日本語を 勉強します。", "Nihongo o benkyoushimasu.", "J'étudie le japonais."),
                VocabularyWord("gen_${lesson.id}_2", lesson.id, "会話", "かいわ", "kaiwa", "Conversation", com.example.data.model.WordCategory.NOUN, "会話の 練習を します。", "Kaiwa no renshuu o shimasu.", "Je fais des exercices de conversation.")
            )
        } else list
    }

    val lessonGrammar = remember(lesson.id) {
        val list = MinnaDatabase.grammarPoints.filter { it.lessonId == lesson.id }
        if (list.isEmpty()) {
            listOf(
                GrammarPoint(
                    id = "g_default_${lesson.id}",
                    lessonId = lesson.id,
                    pattern = "第${lesson.id}課の 文型 (Modèle de phrase)",
                    meaningFr = "Structure clé de la leçon ${lesson.id}",
                    explanation = "Consultez les exercices pour appliquer les règles de conjugaison et de syntaxe de cette leçon.",
                    formula = "[Sujet] は [Complément] を [Verbe]",
                    examples = emptyList()
                )
            )
        } else list
    }

    val lessonDialogue = remember(lesson.id) {
        MinnaDatabase.dialogues.firstOrNull { it.lessonId == lesson.id } ?: MinnaDatabase.dialogues.first()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "${lesson.titleJp} - ${lesson.titleRomaji}",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = lesson.titleFr,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.testTag("lesson_detail_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Retour"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shadowElevation = 8.dp,
                color = MaterialTheme.colorScheme.surface
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedButton(
                            onClick = { onStartSection(lesson, com.example.data.model.ChapterSectionType.RENSHUU_B) },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("✏️ 練習B Livre", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }

                        OutlinedButton(
                            onClick = { onStartSection(lesson, com.example.data.model.ChapterSectionType.MONDAI) },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("🎯 問題 Test", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }

                        OutlinedButton(
                            onClick = { onStartExercises(lesson) },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("▶️ Tout", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = { onStartSection(lesson, com.example.data.model.ChapterSectionType.REVISION) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("start_lesson_exercises_button"),
                        colors = ButtonDefaults.buttonColors(containerColor = JapanRedPrimary),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Icon(Icons.Default.Refresh, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "🔄 RÉVISION RÉPÉTABLE DE L'UNITÉ (SRS)",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Overview Info Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = lesson.iconEmoji, fontSize = 36.sp)
                    Spacer(modifier = Modifier.width(14.dp))
                    Column {
                        Text(
                            text = lesson.titleFr,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = lesson.descriptionFr,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // Tabs
            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = {
                            Text(
                                text = title,
                                fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal,
                                fontSize = 13.sp
                            )
                        },
                        modifier = Modifier.testTag("lesson_tab_$index")
                    )
                }
            }



            // Tab Content
            when (selectedTabIndex) {
                0 -> VocabularyTabContent(
                    vocabList = lessonVocab,
                    onSpeak = onSpeak,
                    bookmarkedWords = bookmarkedWords,
                    onToggleBookmark = onToggleBookmark,
                    romajiMode = romajiMode,
                    onStartReview = { onStartSection(lesson, com.example.data.model.ChapterSectionType.REVISION) }
                )
                1 -> GrammarTabContent(
                    grammarList = lessonGrammar,
                    onSpeak = onSpeak,
                    romajiMode = romajiMode
                )
                2 -> DialogueTabContent(
                    dialogue = lessonDialogue,
                    onSpeak = onSpeak,
                    romajiMode = romajiMode
                )
            }
        }
    }
}

@Composable
fun VocabularyTabContent(
    vocabList: List<VocabularyWord>,
    onSpeak: (String) -> Unit,
    bookmarkedWords: Set<String>,
    onToggleBookmark: (String) -> Unit,
    romajiMode: RomajiDisplayMode,
    onStartReview: () -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Repeatable Review Banner Card for this Unit
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("🔄", fontSize = 20.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Entraînement Répétable (Mémoire Long Terme)",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Révisez les ${vocabList.size} mots de l'unité avec des exercices variés (QCM, écoute, prononciation, remise en ordre) renouvelés à l'infini pour ancrer le vocabulaire.",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = onStartReview,
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = JapanRedPrimary),
                        modifier = Modifier.fillMaxWidth().height(44.dp)
                    ) {
                        Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("LANCER UNE RÉVISION RÉPÉTÉE", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        items(vocabList) { word ->
            val isBookmarked = bookmarkedWords.contains(word.id)
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
                    JapaneseAudioButton(
                        textToSpeak = word.kana,
                        onSpeak = onSpeak
                    )

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        val wordText = if (word.kanji.isNotBlank() && word.kanji != word.kana) {
                            "[${word.kanji}|${word.kana}]"
                        } else {
                            word.kana
                        }

                        JapaneseFuriganaText(
                            text = wordText,
                            romajiMode = romajiMode,
                            fontSize = 19.sp,
                            rubySize = 10.sp,
                            romajiSize = 11.sp
                        )

                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = word.french,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    IconButton(onClick = { onToggleBookmark(word.id) }) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Favori",
                            tint = if (isBookmarked) JapanRedPrimary else MaterialTheme.colorScheme.outline
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GrammarTabContent(
    grammarList: List<GrammarPoint>,
    onSpeak: (String) -> Unit,
    romajiMode: RomajiDisplayMode
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        items(grammarList) { point ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Box(modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)) {
                            JapaneseFuriganaText(
                                text = point.pattern,
                                romajiMode = romajiMode,
                                fontSize = 16.sp,
                                rubySize = 9.sp,
                                romajiSize = 10.sp,
                                textColor = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = point.meaningFr,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = point.explanation,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Formula Box
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFFEF3C7))
                            .border(1.dp, Color(0xFFFDE68A), RoundedCornerShape(8.dp))
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "💡 Formule : ${point.formula}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF92400E)
                        )
                    }

                    if (point.examples.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Exemples du manuel :",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.outline
                        )

                        point.examples.forEach { eg ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                JapaneseAudioButton(
                                    textToSpeak = eg.jp,
                                    onSpeak = onSpeak,
                                    size = 32
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    JapaneseFuriganaText(
                                        text = eg.jp,
                                        romajiMode = romajiMode,
                                        fontSize = 15.sp,
                                        rubySize = 9.sp,
                                        romajiSize = 10.sp
                                    )
                                    Text(
                                        text = eg.fr,
                                        fontSize = 12.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DialogueTabContent(
    dialogue: LessonDialogue,
    onSpeak: (String) -> Unit,
    romajiMode: RomajiDisplayMode
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "会話 : ${dialogue.title} (${dialogue.titleFr})",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = dialogue.settingDescription,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        items(dialogue.lines) { line ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (line.isUserRole) Color(0xFFF0FDF4) else MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surfaceVariant),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = line.avatarEmoji, fontSize = 20.sp)
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = line.speaker,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = if (line.isUserRole) Color(0xFF15803D) else MaterialTheme.colorScheme.primary
                            )
                            JapaneseAudioButton(
                                textToSpeak = line.textJp,
                                onSpeak = onSpeak,
                                size = 32
                            )
                        }

                        JapaneseFuriganaText(
                            text = line.textJp,
                            romajiMode = romajiMode,
                            fontSize = 15.sp,
                            rubySize = 9.sp,
                            romajiSize = 10.sp
                        )

                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = line.textFr,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}
