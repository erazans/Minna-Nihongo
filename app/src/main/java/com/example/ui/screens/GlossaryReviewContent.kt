package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.datasource.MinnaDatabase
import com.example.data.datasource.MinnaReviewGenerator
import com.example.data.datasource.MinnaVerbData
import com.example.data.model.Exercise
import com.example.data.model.RomajiDisplayMode
import com.example.service.SoundEffectsManager
import com.example.ui.components.JapaneseAudioButton
import com.example.ui.components.JapaneseFuriganaText
import com.example.ui.theme.GoldenYellow
import com.example.ui.theme.JapanRedPrimary
import com.example.ui.theme.MatchaGreen

data class ReviewBlock(
    val id: String,
    val title: String,
    val subtitle: String,
    val lessonRange: IntRange,
    val emoji: String,
    val themeColor: Color
)

@Composable
fun GlossaryReviewContent(
    onSpeak: (String) -> Unit,
    romajiMode: RomajiDisplayMode = RomajiDisplayMode.BOTH,
    autoPronounceEnabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    val reviewBlocks = remember {
        listOf(
            ReviewBlock("b1", "Révision 1 (Leçons 1 à 5)", "Bases, Présentation, Heures & Verbes quotidiens", 1..5, "⛩️", Color(0xFF2563EB)),
            ReviewBlock("b2", "Révision 2 (Leçons 6 à 10)", "Transitifs, Adjectifs い/な & Existence (あります/います)", 6..10, "🍵", Color(0xFF059669)),
            ReviewBlock("b3", "Révision 3 (Leçons 11 à 15)", "Compteurs, Désirs (~たい) & Forme en -TE (~てください)", 11..15, "🍱", Color(0xFFD97706)),
            ReviewBlock("b4", "Révision 4 (Leçons 16 à 20)", "Succession d'actions, Forme ~ない, Dictionnaire & Expérience (~た)", 16..20, "🚅", Color(0xFF7C3AED)),
            ReviewBlock("b5", "Révision 5 (Leçons 21 à 25)", "Discours indirect, Subordonnées & Conditions (~たら/~ても)", 21..25, "🗻", Color(0xFFDC2626)),
            ReviewBlock("verbs", "Révision Spéciale : Verbes & Déclinaisons", "Entraînement intensif sur les formes en -TE, Nai, Ta & Dictionnaire", 4..25, "🥋", Color(0xFFBE185D))
        )
    }

    var selectedBlock by remember { mutableStateOf<ReviewBlock?>(null) }
    var selectedSingleLesson by remember { mutableIntStateOf(1) }
    var isSingleLessonMode by remember { mutableStateOf(false) }

    // Active Review Exercise Session State
    var activeExercises by remember { mutableStateOf<List<Exercise>?>(null) }
    var currentExerciseIndex by remember { mutableIntStateOf(0) }
    var selectedOption by remember { mutableStateOf<String?>(null) }
    var isAnswerChecked by remember { mutableStateOf(false) }
    var isAnswerCorrect by remember { mutableStateOf(false) }
    var scoreCorrect by remember { mutableIntStateOf(0) }
    var isSessionComplete by remember { mutableStateOf(false) }

    fun startReviewSession(block: ReviewBlock?, singleLessonId: Int? = null) {
        val exercises = mutableListOf<Exercise>()

        if (singleLessonId != null) {
            exercises.addAll(MinnaReviewGenerator.generateRepeatableReview(singleLessonId, 8))
        } else if (block != null) {
            if (block.id == "verbs") {
                // Generate specialized verb declension exercises
                val sampleVerbs = MinnaVerbData.allVerbs.shuffled().take(8)
                sampleVerbs.forEachIndexed { idx, v ->
                    val isTeQuestion = idx % 2 == 0
                    val targetForm = if (isTeQuestion) v.teForm else v.dictionaryKanji
                    val questionTitle = if (isTeQuestion) {
                        "Quelle est la forme en -TE de [${v.masuKanji}|${v.masuKana}] (${v.french}) ?"
                    } else {
                        "Quelle est la forme Dictionnaire de [${v.masuKanji}|${v.masuKana}] (${v.french}) ?"
                    }
                    val correctAns = if (isTeQuestion) v.teForm.kanji else v.dictionaryKanji
                    val distractors = sampleVerbs.filter { it.id != v.id }.map { if (isTeQuestion) it.teForm.kanji else it.dictionaryKanji }.take(3)
                    val opts = (distractors + correctAns).distinct().shuffled()

                    exercises.add(
                        Exercise(
                            id = "rev_verb_${v.id}_$idx",
                            lessonId = v.lessonId,
                            type = com.example.data.model.ExerciseType.MULTIPLE_CHOICE,
                            questionFr = questionTitle,
                            promptJp = v.masuKanji,
                            targetSentenceJp = correctAns,
                            targetSentenceRomaji = if (isTeQuestion) v.teForm.romaji else v.dictionaryRomaji,
                            options = opts,
                            correctOptions = listOf(correctAns),
                            hint = if (isTeQuestion) v.teRuleExplanation else "Forme neutre / dictionnaire",
                            audioText = correctAns
                        )
                    )
                }
            } else {
                // Generate balanced questions across the lesson range
                val lessons = block.lessonRange.toList().shuffled().take(4)
                lessons.forEach { lId ->
                    exercises.addAll(MinnaReviewGenerator.generateRepeatableReview(lId, 2))
                }
            }
        }

        activeExercises = exercises.shuffled()
        currentExerciseIndex = 0
        selectedOption = null
        isAnswerChecked = false
        isAnswerCorrect = false
        scoreCorrect = 0
        isSessionComplete = false

        // Auto speak first exercise if enabled and available
        if (autoPronounceEnabled) {
            exercises.firstOrNull()?.audioText?.let { onSpeak(it) }
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        if (activeExercises == null) {
            // Review Blocks and Lessons Picker View
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    // Header Card
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("🔄", fontSize = 24.sp)
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = "Leçons de Révision (復習)",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = "Révisez et ancrez vos acquis par bloc ou leçon",
                                        fontSize = 12.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }

                item {
                    Text(
                        text = "Révisions par Paliers du Manuel :",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.outline
                    )
                }

                // Review Blocks Cards
                items(reviewBlocks) { block ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { startReviewSession(block) },
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
                            Surface(
                                shape = CircleShape,
                                color = block.themeColor.copy(alpha = 0.12f),
                                modifier = Modifier.size(46.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(text = block.emoji, fontSize = 22.sp)
                                }
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = block.title,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = block.subtitle,
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    lineHeight = 15.sp
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Button(
                                onClick = { startReviewSession(block) },
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = block.themeColor),
                                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text("Lancer", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Ou Réviser une Leçon Précise (1 à 25) :",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.outline
                    )
                }

                // Horizontal Lesson Pills (1 to 25)
                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items((1..25).toList()) { lessonNum ->
                            Surface(
                                onClick = { startReviewSession(null, singleLessonId = lessonNum) },
                                shape = RoundedCornerShape(12.dp),
                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                                border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text("Leçon $lessonNum", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(14.dp), tint = JapanRedPrimary)
                                }
                            }
                        }
                    }
                }
            }
        } else {
            // Active Interactive Review Exercise Screen
            val exercises = activeExercises!!
            val currentEx = exercises.getOrNull(currentExerciseIndex)

            if (isSessionComplete || currentEx == null) {
                // Completion Summary
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "🎉", fontSize = 48.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Révision Terminée !",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Score : $scoreCorrect / ${exercises.size} réponses correctes",
                        fontSize = 15.sp,
                        color = if (scoreCorrect >= exercises.size / 2) MatchaGreen else JapanRedPrimary,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = { activeExercises = null },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth(0.8f)
                    ) {
                        Text("Retour aux Leçons de Révision", fontWeight = FontWeight.Bold)
                    }
                }
            } else {
                // Interactive Question Card
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    // Top Progress Bar & Exit Button
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        LinearProgressIndicator(
                            progress = { (currentExerciseIndex + 1).toFloat() / exercises.size.toFloat() },
                            modifier = Modifier
                                .weight(1f)
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            color = JapanRedPrimary
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        IconButton(
                            onClick = { activeExercises = null },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(Icons.Default.Close, contentDescription = "Quitter la révision", tint = MaterialTheme.colorScheme.outline)
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Question Box
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = JapanRedPrimary.copy(alpha = 0.1f)
                                ) {
                                    Text(
                                        text = "Question ${currentExerciseIndex + 1}/${exercises.size} • Leçon ${currentEx.lessonId}",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = JapanRedPrimary,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }

                                currentEx.audioText?.let { audio ->
                                    JapaneseAudioButton(textToSpeak = audio, onSpeak = onSpeak)
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = currentEx.questionFr,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            if (!currentEx.promptJp.isNullOrBlank()) {
                                Spacer(modifier = Modifier.height(6.dp))
                                JapaneseFuriganaText(
                                    text = currentEx.promptJp,
                                    romajiMode = romajiMode,
                                    fontSize = 18.sp,
                                    rubySize = 10.sp,
                                    romajiSize = 11.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Options List
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        currentEx.options.forEach { option ->
                            val isSelected = selectedOption == option
                            val isCorrect = currentEx.correctOptions.contains(option)

                            val containerColor = when {
                                isAnswerChecked && isCorrect -> Color(0xFFD1FAE5)
                                isAnswerChecked && isSelected && !isCorrect -> Color(0xFFFEE2E2)
                                isSelected -> JapanRedPrimary.copy(alpha = 0.12f)
                                else -> MaterialTheme.colorScheme.surface
                            }

                            val borderColor = when {
                                isAnswerChecked && isCorrect -> MatchaGreen
                                isAnswerChecked && isSelected && !isCorrect -> JapanRedPrimary
                                isSelected -> JapanRedPrimary
                                else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                            }

                            Surface(
                                onClick = {
                                    if (!isAnswerChecked) {
                                        selectedOption = option
                                    }
                                },
                                shape = RoundedCornerShape(12.dp),
                                color = containerColor,
                                border = androidx.compose.foundation.BorderStroke(1.5.dp, borderColor),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(14.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = option,
                                        fontSize = 15.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier.weight(1f)
                                    )

                                    if (isAnswerChecked && isCorrect) {
                                        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = MatchaGreen)
                                    } else if (isAnswerChecked && isSelected && !isCorrect) {
                                        Icon(Icons.Default.Cancel, contentDescription = null, tint = JapanRedPrimary)
                                    }
                                }
                            }
                        }
                    }

                    // Hint if answered
                    if (isAnswerChecked && currentEx.hint != null) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "💡 ${currentEx.hint}",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    // Bottom Validation / Next Button
                    Button(
                        onClick = {
                            if (!isAnswerChecked) {
                                if (selectedOption != null) {
                                    isAnswerChecked = true
                                    val correct = currentEx.correctOptions.contains(selectedOption)
                                    isAnswerCorrect = correct
                                    if (correct) {
                                        scoreCorrect++
                                        SoundEffectsManager.playCorrectSound()
                                    } else {
                                        SoundEffectsManager.playIncorrectSound()
                                    }
                                    currentEx.audioText?.let { onSpeak(it) }
                                }
                            } else {
                                // Next question
                                if (currentExerciseIndex + 1 < exercises.size) {
                                    currentExerciseIndex++
                                    selectedOption = null
                                    isAnswerChecked = false
                                    isAnswerCorrect = false
                                    if (autoPronounceEnabled) {
                                        exercises[currentExerciseIndex].audioText?.let { onSpeak(it) }
                                    }
                                } else {
                                    isSessionComplete = true
                                }
                            }
                        },
                        enabled = selectedOption != null,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isAnswerChecked) (if (isAnswerCorrect) MatchaGreen else JapanRedPrimary) else JapanRedPrimary
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    ) {
                        Text(
                            text = if (!isAnswerChecked) "VÉRIFIER" else (if (currentExerciseIndex + 1 < exercises.size) "SUIVANT" else "TERMINER"),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
