package com.example.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.data.datasource.MinnaExercisesData
import com.example.data.model.Exercise
import com.example.data.model.ExerciseType
import com.example.data.model.Lesson
import com.example.service.SpeechRecognizerHelper
import com.example.ui.components.DuolingoFeedbackSheet
import com.example.ui.components.JapaneseAudioButton
import com.example.ui.components.JapaneseFuriganaText
import com.example.ui.components.WordTile
import com.example.ui.theme.GoldenYellow
import com.example.ui.theme.JapanRedPrimary
import com.example.ui.theme.MatchaGreen

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExerciseScreen(
    lesson: Lesson,
    targetSection: com.example.data.model.ChapterSectionType? = null,
    onBack: () -> Unit,
    onComplete: (stars: Int, xp: Int, correct: Int, total: Int) -> Unit,
    onSpeak: (String) -> Unit,
    onLoseLife: () -> Boolean,
    currentLives: Int,
    romajiMode: com.example.data.model.RomajiDisplayMode = com.example.data.model.RomajiDisplayMode.BOTH,
    autoPronounceEnabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val speechHelper = remember { SpeechRecognizerHelper(context) }
    val isListening by speechHelper.isListening.collectAsState()
    val recognizedText by speechHelper.recognizedText.collectAsState()

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            speechHelper.startListening { /* recognized text updated in flow */ }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            speechHelper.stopListening()
        }
    }

    var sessionVersion by remember { mutableIntStateOf(0) }

    val exercises = remember(lesson.id, targetSection, sessionVersion) {
        val list = MinnaExercisesData.getExercisesForLesson(lesson.id, targetSection)
        if (list.isEmpty()) {
            MinnaExercisesData.getExercisesForLesson(1, targetSection)
        } else list
    }

    var currentIndex by remember { mutableIntStateOf(0) }
    var selectedOption by remember { mutableStateOf<String?>(null) }
    var selectedWords by remember { mutableStateOf<List<String>>(emptyList()) }
    var userSpokenText by remember { mutableStateOf("") }
    var pronunciationAccuracy by remember { mutableFloatStateOf(0.0f) }

    var isChecked by remember { mutableStateOf(false) }
    var isCurrentCorrect by remember { mutableStateOf(false) }
    var correctCount by remember { mutableIntStateOf(0) }
    var isSessionFinished by remember { mutableStateOf(false) }

    val currentExercise = exercises.getOrNull(currentIndex)

    // Trigger TTS on exercise load if listening type
    LaunchedEffect(currentIndex, sessionVersion) {
        selectedOption = null
        selectedWords = emptyList()
        userSpokenText = ""
        pronunciationAccuracy = 0.0f
        isChecked = false

        val ex = exercises.getOrNull(currentIndex)
        val textToSpeak = ex?.audioText 
            ?: if (ex?.type == ExerciseType.SPEECH_PRONUNCIATION) ex.targetSentenceJp 
            else if (!ex?.promptJp.isNullOrBlank()) ex?.promptJp 
            else null

        if (autoPronounceEnabled && !textToSpeak.isNullOrBlank()) {
            onSpeak(textToSpeak)
        }
    }

    // React to speech recognizer results
    LaunchedEffect(recognizedText) {
        if (recognizedText.isNotBlank()) {
            userSpokenText = recognizedText
            val ex = exercises.getOrNull(currentIndex)
            if (ex != null) {
                val score = speechHelper.evaluatePronunciation(
                    recognizedText,
                    ex.targetSentenceJp,
                    ex.correctOptions
                )
                pronunciationAccuracy = score
            }
        }
    }

    LaunchedEffect(isSessionFinished) {
        if (isSessionFinished) {
            com.example.service.SoundEffectsManager.playLessonCompleteSound()
        }
    }

    if (isSessionFinished || currentExercise == null) {
        // Victory Screen
        ExerciseVictoryCard(
            correctCount = correctCount,
            totalCount = exercises.size,
            isRevisionMode = targetSection == com.example.data.model.ChapterSectionType.REVISION,
            onRepeatSession = {
                sessionVersion++
                currentIndex = 0
                selectedOption = null
                selectedWords = emptyList()
                userSpokenText = ""
                isChecked = false
                correctCount = 0
                isSessionFinished = false
            },
            onFinish = {
                val accuracy = correctCount.toFloat() / exercises.size.toFloat()
                val stars = when {
                    accuracy >= 0.85f -> 3
                    accuracy >= 0.60f -> 2
                    else -> 1
                }
                val xpEarned = 15 + (stars * 5)
                onComplete(stars, xpEarned, correctCount, exercises.size)
            }
        )
        return
    }

    val progress = (currentIndex.toFloat() / exercises.size.toFloat())

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.testTag("exercise_quit_button")
                    ) {
                        Icon(Icons.Default.Close, contentDescription = "Quitter")
                    }

                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier
                            .weight(1f)
                            .height(10.dp)
                            .padding(horizontal = 12.dp)
                            .clip(RoundedCornerShape(5.dp)),
                        color = JapanRedPrimary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant
                    )

                    Text(
                        text = "${currentIndex + 1} / ${exercises.size}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                if (targetSection == com.example.data.model.ChapterSectionType.REVISION) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "🔄 Entraînement Répétable Vocabulaire & Grammaire • Unité ${lesson.id}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = JapanRedPrimary,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        },
        bottomBar = {
            if (isChecked) {
                DuolingoFeedbackSheet(
                    isCorrect = isCurrentCorrect,
                    explanation = currentExercise.hint ?: "",
                    targetSentenceJp = currentExercise.targetSentenceJp,
                    onSpeakTarget = { onSpeak(currentExercise.audioText ?: currentExercise.targetSentenceJp) },
                    onContinue = {
                        if (currentIndex + 1 < exercises.size) {
                            currentIndex++
                        } else {
                            isSessionFinished = true
                        }
                    },
                    romajiMode = romajiMode
                )
            } else {
                Surface(
                    color = MaterialTheme.colorScheme.surface,
                    shadowElevation = 8.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(modifier = Modifier.padding(16.dp)) {
                        val canCheck = when (currentExercise.type) {
                            ExerciseType.MULTIPLE_CHOICE, ExerciseType.LISTENING_SELECT, ExerciseType.FILL_BLANK -> selectedOption != null
                            ExerciseType.WORD_ORDER -> selectedWords.isNotEmpty()
                            ExerciseType.SPEECH_PRONUNCIATION -> userSpokenText.isNotBlank() || pronunciationAccuracy > 0f
                        }

                        Button(
                            onClick = {
                                val correct = checkAnswer(
                                    currentExercise,
                                    selectedOption,
                                    selectedWords,
                                    userSpokenText,
                                    pronunciationAccuracy
                                )
                                isCurrentCorrect = correct
                                isChecked = true
                                if (correct) {
                                    correctCount++
                                    com.example.service.SoundEffectsManager.playCorrectSound()
                                } else {
                                    com.example.service.SoundEffectsManager.playIncorrectSound()
                                }
                            },
                            enabled = canCheck,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .testTag("exercise_check_button"),
                            colors = ButtonDefaults.buttonColors(containerColor = JapanRedPrimary),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(
                                text = "VÉRIFIER",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
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
                .padding(horizontal = 20.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.Top
        ) {
            // Book reference badge if from Minna no Nihongo manual
            if (currentExercise.bookReference != null) {
                Surface(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.padding(bottom = 6.dp)
                ) {
                    Text(
                        text = "📖 Manuel Minna no Nihongo : ${currentExercise.bookReference}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }

            // Exercise Question Title
            Text(
                text = currentExercise.questionFr,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Specific UI according to exercise type
            when (currentExercise.type) {
                ExerciseType.MULTIPLE_CHOICE, ExerciseType.FILL_BLANK -> {
                    if (currentExercise.promptJp != null) {
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                currentExercise.audioText?.let {
                                    JapaneseAudioButton(textToSpeak = it, onSpeak = onSpeak)
                                    Spacer(modifier = Modifier.width(12.dp))
                                }
                                JapaneseFuriganaText(
                                    text = currentExercise.promptJp,
                                    romajiMode = romajiMode,
                                    fontSize = 19.sp,
                                    rubySize = 10.sp,
                                    romajiSize = 11.sp
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }

                    // Options list
                    currentExercise.options.forEach { option ->
                        val isSelected = selectedOption == option
                        OptionSelectionCard(
                            text = option,
                            isSelected = isSelected,
                            romajiMode = romajiMode,
                            onClick = { if (!isChecked) selectedOption = option }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }

                ExerciseType.LISTENING_SELECT -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = { onSpeak(currentExercise.audioText ?: currentExercise.targetSentenceJp) },
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                            modifier = Modifier
                                .height(64.dp)
                                .testTag("exercise_audio_repeat_button")
                        ) {
                            Icon(
                                Icons.AutoMirrored.Filled.VolumeUp,
                                contentDescription = "Réécouter",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(32.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Réécouter la phrase",
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    currentExercise.options.forEach { option ->
                        val isSelected = selectedOption == option
                        OptionSelectionCard(
                            text = option,
                            isSelected = isSelected,
                            onClick = { if (!isChecked) selectedOption = option }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }

                ExerciseType.WORD_ORDER -> {
                    // Answer Construction Area
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 90.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
                            .border(
                                1.5.dp,
                                MaterialTheme.colorScheme.outlineVariant,
                                RoundedCornerShape(16.dp)
                            )
                            .padding(12.dp)
                    ) {
                        if (selectedWords.isEmpty()) {
                            Text(
                                text = "Touchez les mots ci-dessous pour construire la phrase...",
                                color = MaterialTheme.colorScheme.outline,
                                fontSize = 14.sp,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        } else {
                            FlowRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                selectedWords.forEachIndexed { wordIdx, word ->
                                    WordTile(
                                        word = word,
                                        isSelected = false,
                                        onClick = {
                                            if (!isChecked) {
                                                selectedWords = selectedWords.toMutableList().apply { removeAt(wordIdx) }
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Banque de mots :",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.outline
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Word Pool Area
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        currentExercise.options.forEach { word ->
                            val isAlreadyUsed = selectedWords.contains(word)
                            WordTile(
                                word = word,
                                isSelected = isAlreadyUsed,
                                onClick = {
                                    if (!isChecked && !isAlreadyUsed) {
                                        selectedWords = selectedWords + word
                                    }
                                }
                            )
                        }
                    }
                }

                ExerciseType.SPEECH_PRONUNCIATION -> {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = currentExercise.promptJp ?: currentExercise.targetSentenceJp,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = currentExercise.targetSentenceRomaji,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.outline
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            JapaneseAudioButton(
                                textToSpeak = currentExercise.audioText ?: currentExercise.targetSentenceJp,
                                onSpeak = onSpeak
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Voice Recording Action
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Surface(
                            modifier = Modifier
                                .size(76.dp)
                                .clip(CircleShape)
                                .clickable {
                                    if (isListening) {
                                        speechHelper.stopListening()
                                    } else {
                                        val hasPermission = ContextCompat.checkSelfPermission(
                                            context,
                                            Manifest.permission.RECORD_AUDIO
                                        ) == PackageManager.PERMISSION_GRANTED
                                        if (hasPermission) {
                                            speechHelper.startListening { /* recognized */ }
                                        } else {
                                            permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                                        }
                                    }
                                }
                                .testTag("exercise_mic_button"),
                            shape = CircleShape,
                            color = if (isListening) JapanRedPrimary else MaterialTheme.colorScheme.primaryContainer,
                            shadowElevation = 6.dp
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = if (isListening) Icons.Default.MicOff else Icons.Default.Mic,
                                    contentDescription = "Parler",
                                    tint = if (isListening) Color.White else MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(36.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = if (isListening) "Écoute en cours... Parlez maintenant !" else "Appuyez sur le micro pour prononcer",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = if (isListening) JapanRedPrimary else MaterialTheme.colorScheme.outline
                        )

                        if (userSpokenText.isNotBlank()) {
                            Spacer(modifier = Modifier.height(14.dp))
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = Color(0xFFF1F5F9),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(
                                        text = "Ce que nous avons entendu :",
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.outline
                                    )
                                    Text(
                                        text = userSpokenText,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    if (pronunciationAccuracy > 0) {
                                        Text(
                                            text = "Score de prononciation : ${(pronunciationAccuracy * 100).toInt()}%",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = if (pronunciationAccuracy > 0.7f) MatchaGreen else JapanRedPrimary
                                        )
                                    }
                                }
                            }
                        }

                        // Simulation button if speech recognition fails or is muted in emulator
                        TextButton(
                            onClick = {
                                userSpokenText = currentExercise.targetSentenceJp
                                pronunciationAccuracy = 1.0f
                            },
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Text("Simuler une prononciation réussie (mode test)", fontSize = 12.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OptionSelectionCard(
    text: String,
    isSelected: Boolean,
    romajiMode: com.example.data.model.RomajiDisplayMode = com.example.data.model.RomajiDisplayMode.BOTH,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .clickable { onClick() }
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) JapanRedPrimary else MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(14.dp)
            )
            .testTag("option_card_$text"),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f)
            else MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 3.dp else 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = isSelected,
                onClick = onClick,
                colors = RadioButtonDefaults.colors(selectedColor = JapanRedPrimary)
            )
            Spacer(modifier = Modifier.width(8.dp))

            val hasJapanese = text.any { it.code in 0x3040..0x9FFF }
            if (hasJapanese) {
                JapaneseFuriganaText(
                    text = text,
                    romajiMode = romajiMode,
                    fontSize = 16.sp,
                    rubySize = 9.sp,
                    romajiSize = 10.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                )
            } else {
                Text(
                    text = text,
                    fontSize = 16.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

private fun checkAnswer(
    exercise: Exercise,
    selectedOption: String?,
    selectedWords: List<String>,
    spokenText: String,
    pronunciationScore: Float
): Boolean {
    return when (exercise.type) {
        ExerciseType.MULTIPLE_CHOICE, ExerciseType.LISTENING_SELECT, ExerciseType.FILL_BLANK -> {
            exercise.correctOptions.contains(selectedOption)
        }
        ExerciseType.WORD_ORDER -> {
            selectedWords == exercise.correctOptions
        }
        ExerciseType.SPEECH_PRONUNCIATION -> {
            pronunciationScore >= 0.65f || exercise.correctOptions.any {
                spokenText.contains(it) || it.contains(spokenText)
            }
        }
    }
}

@Composable
fun ExerciseVictoryCard(
    correctCount: Int,
    totalCount: Int,
    isRevisionMode: Boolean = false,
    onRepeatSession: (() -> Unit)? = null,
    onFinish: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = if (isRevisionMode) "🔄" else "🎉", fontSize = 52.sp)
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = if (isRevisionMode) "Révision Terminée !" else "Leçon Terminée !",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = if (isRevisionMode)
                        "Mots de l'unité consolidés dans votre mémoire à long terme."
                    else
                        "Félicitations ! Vous progressez dans Minna no Nihongo.",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ScoreStatBadge(label = "Précision", value = "${((correctCount.toFloat() / totalCount.toFloat()) * 100).toInt()}%", icon = "🎯")
                    ScoreStatBadge(label = "Réussite", value = "$correctCount / $totalCount", icon = "✅")
                    ScoreStatBadge(label = "Statut", value = if (correctCount >= totalCount * 0.7f) "Validé" else "À revoir", icon = "📖")
                }

                Spacer(modifier = Modifier.height(24.dp))

                if (onRepeatSession != null) {
                    OutlinedButton(
                        onClick = onRepeatSession,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "REFAIRE UNE RÉVISION (SRS)",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }

                Button(
                    onClick = onFinish,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("victory_finish_button"),
                    colors = ButtonDefaults.buttonColors(containerColor = JapanRedPrimary),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text(
                        text = "CONTINUER",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}

@Composable
fun ScoreStatBadge(label: String, value: String, icon: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(14.dp))
            .background(Color(0xFFF8FAFC))
            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(14.dp))
            .padding(horizontal = 14.dp, vertical = 10.dp)
    ) {
        Text(text = icon, fontSize = 22.sp)
        Text(text = value, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = MaterialTheme.colorScheme.onSurface)
        Text(text = label, fontSize = 11.sp, color = MaterialTheme.colorScheme.outline)
    }
}
