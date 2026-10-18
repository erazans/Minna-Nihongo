package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.datasource.KanaDatabase
import com.example.data.datasource.MinnaKanjiData
import com.example.data.model.KanaCharacter
import com.example.data.model.KanaGroup
import com.example.data.model.KanaType
import com.example.data.model.KanjiCharacter
import com.example.data.model.RomajiDisplayMode
import com.example.service.SoundEffectsManager
import com.example.ui.theme.GoldenYellow
import com.example.ui.theme.JapanRedPrimary
import com.example.ui.theme.MatchaGreen

enum class WritingSystemTab {
    HIRAGANA,
    KATAKANA,
    KANJI,
    NUMBERS_TIME
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KanaStudyScreen(
    onBack: () -> Unit,
    onSpeak: (String) -> Unit,
    currentRomajiMode: RomajiDisplayMode,
    masteredKana: Set<String>,
    onToggleMasterKana: (String) -> Unit,
    masteredKanji: Set<String> = emptySet(),
    onToggleMasterKanji: (String) -> Unit = {},
    onAddXp: (Int) -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedWritingTab by remember { mutableStateOf(WritingSystemTab.HIRAGANA) }
    var selectedKanaGroup by remember { mutableStateOf(KanaGroup.SEION) }
    var selectedKanjiCategory by remember { mutableStateOf("Tous") }
    var selectedCharacterForDetail by remember { mutableStateOf<KanaCharacter?>(null) }
    var selectedKanjiForDetail by remember { mutableStateOf<KanjiCharacter?>(null) }
    var isQuizModeActive by remember { mutableStateOf(false) }

    val currentKanaList = remember(selectedWritingTab, selectedKanaGroup) {
        val baseList = if (selectedWritingTab == WritingSystemTab.HIRAGANA) KanaDatabase.hiraganaList else KanaDatabase.katakanaList
        baseList.filter { it.group == selectedKanaGroup }
    }

    val currentKanjiList = remember(selectedKanjiCategory) {
        if (selectedKanjiCategory == "Tous") {
            MinnaKanjiData.allKanji
        } else {
            MinnaKanjiData.allKanji.filter { it.categoryFr == selectedKanjiCategory }
        }
    }

    Scaffold(
        bottomBar = bottomBar,
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = when (selectedWritingTab) {
                                WritingSystemTab.HIRAGANA, WritingSystemTab.KATAKANA -> "Apprentissage des Kana"
                                WritingSystemTab.KANJI -> "Kanji du livre Minna"
                                WritingSystemTab.NUMBERS_TIME -> "Chiffres, Heures & Minutes"
                            },
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp
                        )
                        Text(
                            text = when (selectedWritingTab) {
                                WritingSystemTab.HIRAGANA -> "Hiragana (ひらがな) • 46 syllabes"
                                WritingSystemTab.KATAKANA -> "Katakana (カタカナ) • Mots d'origine étrangère"
                                WritingSystemTab.KANJI -> "Kanji essentiels • Leçons 1 à 25"
                                WritingSystemTab.NUMBERS_TIME -> "Particularités phonétiques • Minna Leçon 4"
                            },
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
            // Main Tabs (Hiragana, Katakana, Kanji, and now Heures & Chiffres)
            ScrollableTabRow(
                selectedTabIndex = when (selectedWritingTab) {
                    WritingSystemTab.HIRAGANA -> 0
                    WritingSystemTab.KATAKANA -> 1
                    WritingSystemTab.KANJI -> 2
                    WritingSystemTab.NUMBERS_TIME -> 3
                },
                edgePadding = 8.dp,
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                Tab(
                    selected = selectedWritingTab == WritingSystemTab.HIRAGANA,
                    onClick = {
                        selectedWritingTab = WritingSystemTab.HIRAGANA
                        selectedCharacterForDetail = null
                        selectedKanjiForDetail = null
                    },
                    text = { Text("あ Hiragana", fontWeight = FontWeight.Bold, fontSize = 13.sp) },
                    modifier = Modifier.testTag("tab_hiragana")
                )
                Tab(
                    selected = selectedWritingTab == WritingSystemTab.KATAKANA,
                    onClick = {
                        selectedWritingTab = WritingSystemTab.KATAKANA
                        selectedCharacterForDetail = null
                        selectedKanjiForDetail = null
                    },
                    text = { Text("ア Katakana", fontWeight = FontWeight.Bold, fontSize = 13.sp) },
                    modifier = Modifier.testTag("tab_katakana")
                )
                Tab(
                    selected = selectedWritingTab == WritingSystemTab.KANJI,
                    onClick = {
                        selectedWritingTab = WritingSystemTab.KANJI
                        selectedCharacterForDetail = null
                        selectedKanjiForDetail = null
                    },
                    text = { Text("漢 Kanji Minna", fontWeight = FontWeight.Bold, fontSize = 13.sp) },
                    modifier = Modifier.testTag("tab_kanji")
                )
                Tab(
                    selected = selectedWritingTab == WritingSystemTab.NUMBERS_TIME,
                    onClick = {
                        selectedWritingTab = WritingSystemTab.NUMBERS_TIME
                        selectedCharacterForDetail = null
                        selectedKanjiForDetail = null
                    },
                    text = { Text("⏰ Heures & Chiffres", fontWeight = FontWeight.Bold, fontSize = 13.sp) },
                    modifier = Modifier.testTag("tab_numbers_time")
                )
            }



            if (isQuizModeActive) {
                when (selectedWritingTab) {
                    WritingSystemTab.NUMBERS_TIME -> {
                        NumbersTimeQuizView(
                            onSpeak = onSpeak,
                            onAddXp = onAddXp,
                            onExitQuiz = { isQuizModeActive = false }
                        )
                    }
                    WritingSystemTab.KANJI -> {
                        KanjiQuizView(
                            kanjiList = MinnaKanjiData.allKanji,
                            onSpeak = onSpeak,
                            onAddXp = onAddXp,
                            onExitQuiz = { isQuizModeActive = false }
                        )
                    }
                    WritingSystemTab.HIRAGANA, WritingSystemTab.KATAKANA -> {
                        val kanaType = if (selectedWritingTab == WritingSystemTab.HIRAGANA) KanaType.HIRAGANA else KanaType.KATAKANA
                        KanaQuizView(
                            kanaType = kanaType,
                            onSpeak = onSpeak,
                            onAddXp = onAddXp,
                            onExitQuiz = { isQuizModeActive = false }
                        )
                    }
                }
            } else {
                when (selectedWritingTab) {
                    WritingSystemTab.NUMBERS_TIME -> {
                        NumbersTimeStudyContent(
                            onSpeak = onSpeak,
                            onAddXp = onAddXp
                        )
                    }
                    WritingSystemTab.KANJI -> {
                    // === KANJI CATEGORY VIEW ===
                    // Kanji Categories Scrollable Chips
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(MinnaKanjiData.categories) { cat ->
                            FilterChip(
                                selected = selectedKanjiCategory == cat,
                                onClick = { selectedKanjiCategory = cat },
                                label = { Text(cat, fontSize = 12.sp) }
                            )
                        }
                    }

                    // Selected Kanji Detail Card if tapped
                    selectedKanjiForDetail?.let { kanji ->
                        KanjiDetailCard(
                            kanji = kanji,
                            isMastered = masteredKanji.contains(kanji.kanji),
                            onSpeak = onSpeak,
                            onToggleMaster = { onToggleMasterKanji(kanji.kanji) },
                            onDismiss = { selectedKanjiForDetail = null }
                        )
                    }

                    // Grid of Kanji characters
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = 90.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 12.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(bottom = 24.dp)
                    ) {
                        items(currentKanjiList) { kanji ->
                            val isMastered = masteredKanji.contains(kanji.kanji)
                            KanjiGridCell(
                                kanji = kanji,
                                isMastered = isMastered,
                                isSelected = selectedKanjiForDetail == kanji,
                                onClick = {
                                    selectedKanjiForDetail = kanji
                                    onSpeak(kanji.kanji)
                                }
                            )
                        }
                    }
                }
                WritingSystemTab.HIRAGANA, WritingSystemTab.KATAKANA -> {
                    // === KANA VIEW ===
                    // Kana Group Chips (Sons purs, Voisés, Combinaisons)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FilterChip(
                            selected = selectedKanaGroup == KanaGroup.SEION,
                            onClick = { selectedKanaGroup = KanaGroup.SEION },
                            label = { Text("Base 46") }
                        )
                        FilterChip(
                            selected = selectedKanaGroup == KanaGroup.DAKUON,
                            onClick = { selectedKanaGroup = KanaGroup.DAKUON },
                            label = { Text("Voisés ゛゜") }
                        )
                        FilterChip(
                            selected = selectedKanaGroup == KanaGroup.YOON,
                            onClick = { selectedKanaGroup = KanaGroup.YOON },
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
fun KanjiGridCell(
    kanji: KanjiCharacter,
    isMastered: Boolean,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)
            .clip(RoundedCornerShape(14.dp))
            .clickable { onClick() }
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) JapanRedPrimary
                else if (isMastered) MatchaGreen.copy(alpha = 0.6f)
                else MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(14.dp)
            )
            .testTag("kanji_cell_${kanji.kanji}"),
        shape = RoundedCornerShape(14.dp),
        color = if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.25f)
        else MaterialTheme.colorScheme.surface,
        shadowElevation = if (isSelected) 3.dp else 1.dp
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(6.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = kanji.kanji,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = kanji.meaningFr,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "${kanji.strokeCount} traits • L.${kanji.lessonId}",
                    fontSize = 9.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (isMastered) {
                Text(
                    text = "✓",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = MatchaGreen,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(5.dp)
                )
            }
        }
    }
}

@Composable
fun KanjiDetailCard(
    kanji: KanjiCharacter,
    isMastered: Boolean,
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
                    Text(text = kanji.kanji, fontSize = 44.sp, fontWeight = FontWeight.Bold, color = JapanRedPrimary)
                    Spacer(modifier = Modifier.width(14.dp))
                    Column {
                        Text(
                            text = kanji.meaningFr,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "${kanji.strokeCount} traits • Minna no Nihongo Leçon ${kanji.lessonId}",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Row {
                    IconButton(
                        onClick = { onSpeak(kanji.kanji) },
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

            Spacer(modifier = Modifier.height(10.dp))

            // Readings (音読み & 訓読み)
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "音読み (On'yomi) : ${kanji.onyomi}",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = JapanRedPrimary
                        )
                        IconButton(
                            onClick = { onSpeak(kanji.onyomi.substringBefore("(").trim()) },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(Icons.AutoMirrored.Filled.VolumeUp, contentDescription = null, tint = JapanRedPrimary, modifier = Modifier.size(16.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "訓読み (Kun'yomi) : ${kanji.kunyomi}",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MatchaGreen
                        )
                        IconButton(
                            onClick = { onSpeak(kanji.kunyomi.substringBefore("(").replace("-", "").replace("・", "").trim()) },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(Icons.AutoMirrored.Filled.VolumeUp, contentDescription = null, tint = MatchaGreen, modifier = Modifier.size(16.dp))
                        }
                    }
                }
            }

            if (kanji.mnemonicFr.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "💡 Moyen mnémotechnique : ${kanji.mnemonicFr}",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Authentic Minna no Nihongo Examples
            if (kanji.examples.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Vocabulaire du manuel Minna no Nihongo :",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    kanji.examples.forEach { ex ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f))
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "${ex.wordJp} 【${ex.readingKana}】 • ${ex.romaji}",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = ex.meaningFr,
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            IconButton(
                                onClick = { onSpeak(ex.readingKana) },
                                modifier = Modifier.size(28.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                    contentDescription = "Écouter exemple",
                                    tint = JapanRedPrimary,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
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
                    text = if (isCorrect) "Bravo ! Bonne réponse" else "C'était : ${currentTarget.character} (${currentTarget.romaji})",
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

@Composable
fun KanjiQuizView(
    kanjiList: List<KanjiCharacter>,
    onSpeak: (String) -> Unit,
    onAddXp: (Int) -> Unit,
    onExitQuiz: () -> Unit
) {
    var currentTarget by remember { mutableStateOf(kanjiList.shuffled().first()) }
    var options by remember { mutableStateOf<List<KanjiCharacter>>(emptyList()) }
    var selectedOption by remember { mutableStateOf<KanjiCharacter?>(null) }
    var isAnswered by remember { mutableStateOf(false) }
    var isCorrect by remember { mutableStateOf(false) }
    var score by remember { mutableIntStateOf(0) }

    fun nextQuestion() {
        currentTarget = kanjiList.shuffled().first()
        val distractors = kanjiList.filter { it != currentTarget }.shuffled().take(3)
        options = (distractors + currentTarget).shuffled()
        selectedOption = null
        isAnswered = false
        isCorrect = false
        onSpeak(currentTarget.kanji)
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
                    text = "Quiz Kanji Minna no Nihongo",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
                TextButton(onClick = onExitQuiz) {
                    Text("Quitter le quiz")
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Quel est le sens de ce kanji :",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = currentTarget.kanji,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Black,
                    color = JapanRedPrimary
                )
                Spacer(modifier = Modifier.width(12.dp))
                IconButton(
                    onClick = { onSpeak(currentTarget.kanji) },
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = "Écouter",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // 4 Options Grid (Meaning in French)
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                options.forEach { opt ->
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
                            .fillMaxWidth()
                            .height(54.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .clickable(enabled = !isAnswered) {
                                selectedOption = opt
                                isAnswered = true
                                if (isThisTarget) {
                                    isCorrect = true
                                    score++
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
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.padding(horizontal = 12.dp)
                        ) {
                            Text(
                                text = opt.meaningFr,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface,
                                textAlign = TextAlign.Center,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }

            if (isAnswered) {
                Spacer(modifier = Modifier.height(18.dp))
                Text(
                    text = if (isCorrect) "Bravo ! Bonne réponse" else "C'était : ${currentTarget.kanji} (${currentTarget.meaningFr})",
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
