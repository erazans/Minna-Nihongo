package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.example.data.datasource.KanaDatabase
import com.example.data.model.KanaCharacter
import com.example.data.model.KanaGroup
import com.example.data.model.KanaType
import com.example.data.model.RomajiDisplayMode
import com.example.service.SoundEffectsManager
import com.example.ui.components.RomajiModeSelectorBar
import com.example.ui.theme.GoldenYellow
import com.example.ui.theme.JapanRedPrimary
import com.example.ui.theme.MatchaGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KanaStudyScreen(
    onBack: () -> Unit,
    onSpeak: (String) -> Unit,
    currentRomajiMode: RomajiDisplayMode,
    onUpdateRomajiMode: (RomajiDisplayMode) -> Unit,
    masteredKana: Set<String>,
    onToggleMasterKana: (String) -> Unit,
    onAddXp: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedKanaType by remember { mutableStateOf(KanaType.HIRAGANA) }
    var selectedGroup by remember { mutableStateOf(KanaGroup.SEION) }
    var selectedCharacterForDetail by remember { mutableStateOf<KanaCharacter?>(null) }
    var isQuizModeActive by remember { mutableStateOf(false) }

    val currentKanaList = remember(selectedKanaType, selectedGroup) {
        val baseList = if (selectedKanaType == KanaType.HIRAGANA) KanaDatabase.hiraganaList else KanaDatabase.katakanaList
        baseList.filter { it.group == selectedGroup }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Apprentissage des Kana",
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp
                        )
                        Text(
                            text = "Hiragana (ひらがな) & Katakana (カタカナ)",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.testTag("kana_study_back_button")
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour")
                    }
                },
                actions = {
                    IconButton(
                        onClick = { isQuizModeActive = !isQuizModeActive },
                        modifier = Modifier.testTag("kana_quiz_toggle_button")
                    ) {
                        Icon(
                            imageVector = if (isQuizModeActive) Icons.Default.GridOn else Icons.Default.Quiz,
                            contentDescription = "Mode Quiz",
                            tint = JapanRedPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Main Kana Syllabary Tabs (Hiragana vs Katakana)
            TabRow(
                selectedTabIndex = if (selectedKanaType == KanaType.HIRAGANA) 0 else 1,
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                Tab(
                    selected = selectedKanaType == KanaType.HIRAGANA,
                    onClick = {
                        selectedKanaType = KanaType.HIRAGANA
                        selectedCharacterForDetail = null
                    },
                    text = { Text("あ Hiragana (46)", fontWeight = FontWeight.Bold, fontSize = 14.sp) },
                    modifier = Modifier.testTag("tab_hiragana")
                )
                Tab(
                    selected = selectedKanaType == KanaType.KATAKANA,
                    onClick = {
                        selectedKanaType = KanaType.KATAKANA
                        selectedCharacterForDetail = null
                    },
                    text = { Text("ア Katakana (46)", fontWeight = FontWeight.Bold, fontSize = 14.sp) },
                    modifier = Modifier.testTag("tab_katakana")
                )
            }

            // Latin Alphabet (Romaji) option bar as explicitly requested
            RomajiModeSelectorBar(
                currentMode = currentRomajiMode,
                onModeSelected = onUpdateRomajiMode
            )

            if (isQuizModeActive) {
                // Interactive Kana Quiz Challenge
                KanaQuizView(
                    kanaType = selectedKanaType,
                    onSpeak = onSpeak,
                    onAddXp = onAddXp,
                    onExitQuiz = { isQuizModeActive = false }
                )
            } else {
                // Kana Group Chips (Sons purs, Voisés, Combinaisons)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = selectedGroup == KanaGroup.SEION,
                        onClick = { selectedGroup = KanaGroup.SEION },
                        label = { Text("Base 46") }
                    )
                    FilterChip(
                        selected = selectedGroup == KanaGroup.DAKUON,
                        onClick = { selectedGroup = KanaGroup.DAKUON },
                        label = { Text("Voisés ゛゜") }
                    )
                    FilterChip(
                        selected = selectedGroup == KanaGroup.YOON,
                        onClick = { selectedGroup = KanaGroup.YOON },
                        label = { Text("Combinés ゃゅょ") }
                    )
                }

                // Selected Kana Detail Card if tapped
                selectedCharacterForDetail?.let { kana ->
                    KanaDetailCard(
                        kana = kana,
                        isMastered = masteredKana.contains(kana.character),
                        showRomaji = shouldShowRomaji(kana.type, currentRomajiMode),
                        onSpeak = onSpeak,
                        onToggleMaster = { onToggleMasterKana(kana.character) },
                        onDismiss = { selectedCharacterForDetail = null }
                    )
                }

                // Grid of Kana characters
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 64.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(bottom = 24.dp)
                ) {
                    items(currentKanaList) { kana ->
                        val isMastered = masteredKana.contains(kana.character)
                        val showRomaji = shouldShowRomaji(kana.type, currentRomajiMode)

                        KanaGridCell(
                            kana = kana,
                            showRomaji = showRomaji,
                            isMastered = isMastered,
                            isSelected = selectedCharacterForDetail == kana,
                            onClick = {
                                selectedCharacterForDetail = kana
                                onSpeak(kana.character)
                            }
                        )
                    }
                }
            }
        }
    }
}

private fun shouldShowRomaji(type: KanaType, mode: RomajiDisplayMode): Boolean {
    return when (mode) {
        RomajiDisplayMode.NONE -> false
        RomajiDisplayMode.HIRAGANA_ONLY -> type == KanaType.HIRAGANA
        RomajiDisplayMode.KATAKANA_ONLY -> type == KanaType.KATAKANA
        RomajiDisplayMode.BOTH -> true
    }
}

@Composable
fun KanaGridCell(
    kana: KanaCharacter,
    showRomaji: Boolean,
    isMastered: Boolean,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(14.dp))
            .clickable { onClick() }
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) JapanRedPrimary
                else if (isMastered) MatchaGreen.copy(alpha = 0.5f)
                else MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(14.dp)
            )
            .testTag("kana_cell_${kana.character}"),
        shape = RoundedCornerShape(14.dp),
        color = if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
        else MaterialTheme.colorScheme.surface,
        shadowElevation = if (isSelected) 3.dp else 1.dp
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = kana.character,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                if (showRomaji) {
                    Text(
                        text = kana.romaji,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            if (isMastered) {
                Text(
                    text = "✓",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = MatchaGreen,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                )
            }
        }
    }
}

@Composable
fun KanaDetailCard(
    kana: KanaCharacter,
    isMastered: Boolean,
    showRomaji: Boolean,
    onSpeak: (String) -> Unit,
    onToggleMaster: () -> Unit,
    onDismiss: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = kana.character, fontSize = 42.sp, fontWeight = FontWeight.Bold, color = JapanRedPrimary)
                    Spacer(modifier = Modifier.width(14.dp))
                    Column {
                        Text(
                            text = "Romaji : ${kana.romaji}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${kana.type.labelFr} • ${kana.strokeCount} traits",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Row {
                    IconButton(
                        onClick = { onSpeak(kana.character) },
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                            contentDescription = "Écouter",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    IconButton(
                        onClick = onToggleMaster,
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(if (isMastered) Color(0xFFDCFCE7) else MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Icon(
                            imageVector = if (isMastered) Icons.Default.CheckCircle else Icons.Default.CheckCircleOutline,
                            contentDescription = "Maîtrisé",
                            tint = if (isMastered) MatchaGreen else MaterialTheme.colorScheme.outline,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Fermer")
                    }
                }
            }

            if (kana.mnemonicFr.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "💡 Moyen mnémotechnique : ${kana.mnemonicFr}",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (kana.exampleWordJp.isNotBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Exemple Minna no Nihongo : ${kana.exampleWordJp} (${kana.exampleWordRomaji} = ${kana.exampleWordFr})",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    IconButton(
                        onClick = { onSpeak(kana.exampleWordJp) },
                        modifier = Modifier.size(26.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun KanaQuizView(
    kanaType: KanaType,
    onSpeak: (String) -> Unit,
    onAddXp: (Int) -> Unit,
    onExitQuiz: () -> Unit
) {
    val pool = remember(kanaType) {
        if (kanaType == KanaType.HIRAGANA) KanaDatabase.hiraganaList else KanaDatabase.katakanaList
    }

    var questionIndex by remember { mutableIntStateOf(0) }
    var currentTarget by remember { mutableStateOf(pool.shuffled().first()) }
    var options by remember { mutableStateOf<List<KanaCharacter>>(emptyList()) }
    var selectedOption by remember { mutableStateOf<KanaCharacter?>(null) }
    var isAnswered by remember { mutableStateOf(false) }
    var isCorrect by remember { mutableStateOf(false) }
    var score by remember { mutableIntStateOf(0) }

    fun nextQuestion() {
        currentTarget = pool.shuffled().first()
        val distractors = pool.filter { it != currentTarget }.shuffled().take(3)
        options = (distractors + currentTarget).shuffled()
        selectedOption = null
        isAnswered = false
        isCorrect = false
        questionIndex++
        onSpeak(currentTarget.character)
    }

    LaunchedEffect(Unit) {
        nextQuestion()
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Quiz ${kanaType.labelFr.substringBefore(" ")}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
                TextButton(onClick = onExitQuiz) {
                    Text("Quitter le quiz")
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Quel caractère correspond à la prononciation :",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "[ ${currentTarget.romaji.uppercase()} ]",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Black,
                    color = JapanRedPrimary
                )
                Spacer(modifier = Modifier.width(10.dp))
                IconButton(
                    onClick = { onSpeak(currentTarget.character) },
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = "Écouter",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 4 Options Grid
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                options.chunked(2).forEach { rowOptions ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        rowOptions.forEach { opt ->
                            val isThisSelected = selectedOption == opt
                            val isThisTarget = opt == currentTarget

                            val buttonColor = when {
                                !isAnswered -> MaterialTheme.colorScheme.surfaceVariant
                                isThisTarget -> Color(0xFFDCFCE7)
                                isThisSelected && !isThisTarget -> Color(0xFFFFE4E6)
                                else -> MaterialTheme.colorScheme.surfaceVariant
                            }

                            Surface(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(64.dp)
                                    .clip(RoundedCornerShape(14.dp))
                                    .clickable(enabled = !isAnswered) {
                                        selectedOption = opt
                                        isAnswered = true
                                        if (isThisTarget) {
                                            isCorrect = true
                                            score++
                                            onAddXp(5)
                                            SoundEffectsManager.playCorrectSound()
                                        } else {
                                            isCorrect = false
                                            SoundEffectsManager.playIncorrectSound()
                                        }
                                    },
                                color = buttonColor,
                                border = androidx.compose.foundation.BorderStroke(
                                    width = 1.dp,
                                    color = if (isAnswered && isThisTarget) MatchaGreen else MaterialTheme.colorScheme.outlineVariant
                                )
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(
                                        text = opt.character,
                                        fontSize = 26.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }
                }
            }

            if (isAnswered) {
                Spacer(modifier = Modifier.height(18.dp))
                Text(
                    text = if (isCorrect) "Bravo ! +5 XP" else "C'était : ${currentTarget.character} (${currentTarget.romaji})",
                    fontWeight = FontWeight.Bold,
                    color = if (isCorrect) MatchaGreen else JapanRedPrimary,
                    fontSize = 15.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = { nextQuestion() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = JapanRedPrimary),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("SUIVANT", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
