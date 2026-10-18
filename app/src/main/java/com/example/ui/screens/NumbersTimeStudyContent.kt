package com.example.ui.screens

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
import com.example.data.datasource.JapaneseNumberItem
import com.example.data.datasource.JapaneseTimeItem
import com.example.data.datasource.MinnaNumbersTimeData
import com.example.data.datasource.NumberCategory
import com.example.data.datasource.TimeItemType
import com.example.service.SoundEffectsManager
import com.example.ui.theme.GoldenYellow
import com.example.ui.theme.JapanRedPrimary
import com.example.ui.theme.MatchaGreen
import com.example.ui.theme.StreakOrange

enum class NumbersTimeSubTab {
    NUMBERS,
    TIME
}

@Composable
fun NumbersTimeStudyContent(
    onSpeak: (String) -> Unit,
    onAddXp: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var activeSubTab by remember { mutableStateOf(NumbersTimeSubTab.TIME) } // Default to Time since it has the prominent requested particularities!
    var selectedNumberCategory by remember { mutableStateOf<NumberCategory?>(null) } // null = All
    var selectedTimeType by remember { mutableStateOf<TimeItemType?>(null) } // null = All
    var filterOnlyIrregularTime by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxSize()) {
        // Sub-tabs: Chiffres & Nombres vs Heures & Minutes
        TabRow(
            selectedTabIndex = if (activeSubTab == NumbersTimeSubTab.TIME) 0 else 1,
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        ) {
            Tab(
                selected = activeSubTab == NumbersTimeSubTab.TIME,
                onClick = { activeSubTab = NumbersTimeSubTab.TIME },
                text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("⏰ Heures & Minutes", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Surface(
                            shape = CircleShape,
                            color = JapanRedPrimary.copy(alpha = 0.15f)
                        ) {
                            Text(
                                "Pièges",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Black,
                                color = JapanRedPrimary,
                                modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                            )
                        }
                    }
                },
                modifier = Modifier.testTag("subtab_time_particularities")
            )
            Tab(
                selected = activeSubTab == NumbersTimeSubTab.NUMBERS,
                onClick = { activeSubTab = NumbersTimeSubTab.NUMBERS },
                text = { Text("🔢 Chiffres & Nombres", fontWeight = FontWeight.Bold, fontSize = 13.sp) },
                modifier = Modifier.testTag("subtab_numbers")
            )
        }

        when (activeSubTab) {
            NumbersTimeSubTab.TIME -> {
                TimeParticularitiesView(
                    selectedType = selectedTimeType,
                    onSelectType = { selectedTimeType = it },
                    filterOnlyIrregular = filterOnlyIrregularTime,
                    onToggleFilterIrregular = { filterOnlyIrregularTime = !filterOnlyIrregularTime },
                    onSpeak = onSpeak
                )
            }
            NumbersTimeSubTab.NUMBERS -> {
                NumbersStudyView(
                    selectedCategory = selectedNumberCategory,
                    onSelectCategory = { selectedNumberCategory = it },
                    onSpeak = onSpeak
                )
            }
        }
    }
}

@Composable
fun TimeParticularitiesView(
    selectedType: TimeItemType?,
    onSelectType: (TimeItemType?) -> Unit,
    filterOnlyIrregular: Boolean,
    onToggleFilterIrregular: () -> Unit,
    onSpeak: (String) -> Unit
) {
    val allItems = remember {
        MinnaNumbersTimeData.hoursList + MinnaNumbersTimeData.minutesList + MinnaNumbersTimeData.timeExpressions
    }

    val filteredList = remember(selectedType, filterOnlyIrregular) {
        allItems.filter { item ->
            (selectedType == null || item.type == selectedType) &&
            (!filterOnlyIrregular || item.isIrregular)
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Educational Highlight Box for Irregularities
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFEF2F2)),
                border = androidx.compose.foundation.BorderStroke(1.dp, JapanRedPrimary.copy(alpha = 0.3f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("⚠️", fontSize = 20.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Particularités phonétiques majeures (Leçon 4)",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = JapanRedPrimary
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "• Heures (時 ji) : Les 3 pièges absolus sont 4h (よじ yoji - jamais 'yonji'), 7h (しちじ shichiji) et 9h (くじ kuji - jamais 'kyuuji') !",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "• Minutes (分 fun/pun) : Les chiffres 1, 3, 4, 6, 8 et 10 prennent la terminaison 'ぷん' (pun) avec redoublement 'っ' (ippun, sanpun, yonpun, roppun, happun, juppun). Les autres prennent 'ふん' (fun) !",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        // Filter Chips Row
        item {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    item {
                        FilterChip(
                            selected = selectedType == null,
                            onClick = { onSelectType(null) },
                            label = { Text("Tout voir (${allItems.size})") }
                        )
                    }
                    items(TimeItemType.values()) { type ->
                        FilterChip(
                            selected = selectedType == type,
                            onClick = { onSelectType(type) },
                            label = { Text(type.labelFr) }
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${filteredList.size} entrées affichées",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    FilterChip(
                        selected = filterOnlyIrregular,
                        onClick = onToggleFilterIrregular,
                        label = { Text("⚠️ Uniquement les pièges", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = JapanRedPrimary.copy(alpha = 0.15f),
                            selectedLabelColor = JapanRedPrimary
                        )
                    )
                }
            }
        }

        // List of Time Items
        items(filteredList) { item ->
            TimeCardItem(item = item, onSpeak = onSpeak)
        }
    }
}

@Composable
fun TimeCardItem(
    item: JapaneseTimeItem,
    onSpeak: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSpeak(item.kana.substringBefore("/").replace("〜", "").trim()) },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (item.isIrregular) Color(0xFFFFF7ED) else MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (item.isIrregular) 2.dp else 1.dp),
        border = if (item.isIrregular) androidx.compose.foundation.BorderStroke(1.2.dp, StreakOrange.copy(alpha = 0.5f)) else null
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (item.isIrregular) StreakOrange.copy(alpha = 0.2f) else MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = item.display,
                            fontWeight = FontWeight.Black,
                            fontSize = 13.sp,
                            color = if (item.isIrregular) StreakOrange else MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = item.kanji,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "【${item.kana}】",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = if (item.isIrregular) JapanRedPrimary else MaterialTheme.colorScheme.primary
                            )
                        }

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = "${item.romaji} • ${item.french}",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (item.isIrregular) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = StreakOrange.copy(alpha = 0.15f),
                            modifier = Modifier.padding(end = 6.dp)
                        ) {
                            Text(
                                text = "⚠️ Irrégulier",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = StreakOrange,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                            )
                        }
                    }

                    IconButton(
                        onClick = { onSpeak(item.kana.substringBefore("/").replace("〜", "").trim()) },
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                            contentDescription = "Écouter",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            if (!item.particularityNote.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color.White.copy(alpha = 0.7f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = item.particularityNote,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFFC2410C),
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun NumbersStudyView(
    selectedCategory: NumberCategory?,
    onSelectCategory: (NumberCategory?) -> Unit,
    onSpeak: (String) -> Unit
) {
    val allNumbers = remember {
        MinnaNumbersTimeData.digits0To10 +
        MinnaNumbersTimeData.tens11To99 +
        MinnaNumbersTimeData.hundredsAndThousands +
        MinnaNumbersTimeData.largeNumbers +
        MinnaNumbersTimeData.countersTsu
    }

    val filteredList = remember(selectedCategory) {
        if (selectedCategory == null) allNumbers
        else allNumbers.filter { it.category == selectedCategory }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Japanese Number System Explanation Card
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("💡", fontSize = 18.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Règles fondamentales des nombres",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "• Chiffres 4, 7, 9 : ont deux lectures (4 = yon/shi, 7 = nana/shichi, 9 = kyuu/ku).\n• Centaines : 300 (sanbyaku), 600 (roppyaku), 800 (happyaku) subissent une altération phonétique.\n• Grands nombres : Le japonais compte par tranche de 4 zéros : 10 000 = 一万 (ichiman) !",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // Category Filter Chips
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    FilterChip(
                        selected = selectedCategory == null,
                        onClick = { onSelectCategory(null) },
                        label = { Text("Tous (${allNumbers.size})") }
                    )
                }
                items(NumberCategory.values()) { cat ->
                    FilterChip(
                        selected = selectedCategory == cat,
                        onClick = { onSelectCategory(cat) },
                        label = { Text(cat.labelFr) }
                    )
                }
            }
        }

        // Number Cards
        items(filteredList) { num ->
            NumberCardItem(item = num, onSpeak = onSpeak)
        }
    }
}

@Composable
fun NumberCardItem(
    item: JapaneseNumberItem,
    onSpeak: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSpeak(item.kana.substringBefore("/").trim()) },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (item.hasSpecialReading) Color(0xFFFEF3C7) else MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        border = if (item.hasSpecialReading) androidx.compose.foundation.BorderStroke(1.dp, GoldenYellow) else null
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = item.arabic,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = item.kanji,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "【${item.kana}】",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = JapanRedPrimary
                            )
                        }

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = "${item.romaji} • ${item.french}",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (item.hasSpecialReading) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = GoldenYellow.copy(alpha = 0.25f),
                            modifier = Modifier.padding(end = 6.dp)
                        ) {
                            Text(
                                text = "Particularité",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFB45309),
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }

                    IconButton(
                        onClick = { onSpeak(item.kana.substringBefore("/").trim()) },
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                            contentDescription = "Écouter",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            if (!item.noteFr.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = item.noteFr,
                    fontSize = 11.sp,
                    color = Color(0xFF92400E)
                )
            }
        }
    }
}

@Composable
fun NumbersTimeQuizView(
    onSpeak: (String) -> Unit,
    onAddXp: (Int) -> Unit = {},
    onExitQuiz: () -> Unit
) {
    data class QuizQuestion(
        val promptFr: String,
        val targetJp: String,
        val correctAnswer: String,
        val options: List<String>,
        val explanation: String
    )

    val questions = remember {
        listOf(
            QuizQuestion(
                promptFr = "Comment dit-on '4 heures' en japonais (Piège majeur !) ?",
                targetJp = "四時",
                correctAnswer = "よじ (yoji)",
                options = listOf("よじ (yoji)", "よんじ (yonji)", "しじ (shiji)", "よんじかん (yonjikan)").shuffled(),
                explanation = "4h se dit TOUJOURS 'よじ' (yoji) ! 'yonji' et 'shiji' n'existent pas."
            ),
            QuizQuestion(
                promptFr = "Comment dit-on '9 heures' en japonais (Piège majeur !) ?",
                targetJp = "九時",
                correctAnswer = "くじ (kuji)",
                options = listOf("くじ (kuji)", "きゅうじ (kyuuji)", "きゅじ (kyuji)", "ここのつじ (kokonotsuji)").shuffled(),
                explanation = "9h se dit TOUJOURS 'くじ' (kuji) et jamais 'kyuuji'."
            ),
            QuizQuestion(
                promptFr = "Comment dit-on '7 heures' en japonais ?",
                targetJp = "七時",
                correctAnswer = "しちじ (shichiji)",
                options = listOf("しちじ (shichiji)", "ななじ (nanaji)", "ひちじ (hichiji)", "しじ (shiji)").shuffled(),
                explanation = "7h se dit 'しちじ' (shichiji) dans Minna no Nihongo."
            ),
            QuizQuestion(
                promptFr = "Comment se prononce '1 minute' (一分) ?",
                targetJp = "一分",
                correctAnswer = "いっぷん (ippun)",
                options = listOf("いっぷん (ippun)", "いちふん (ichifun)", "いちぷん (ichipun)", "ひとふん (hitofun)").shuffled(),
                explanation = "1 minute prend 'ぷん' (pun) avec double consonne : 'いっぷん' (ippun)."
            ),
            QuizQuestion(
                promptFr = "Comment se prononce '6 minutes' (六分) ?",
                targetJp = "六分",
                correctAnswer = "ろっぷん (roppun)",
                options = listOf("ろっぷん (roppun)", "ろくふん (rokufun)", "ろくぷん (rokupun)", "むつふん (mutsufun)").shuffled(),
                explanation = "6 minutes prend 'ぷん' avec assimilation 'っ' : 'ろっぷん' (roppun)."
            ),
            QuizQuestion(
                promptFr = "Comment dit-on '300' (三百 - Altération phonétique) ?",
                targetJp = "三百",
                correctAnswer = "さんびゃく (sanbyaku)",
                options = listOf("さんびゃく (sanbyaku)", "さんひゃく (sanhyaku)", "さんぴゃく (sanpyaku)", "さんぜん (sanzen)").shuffled(),
                explanation = "Pour 300, le son 'h' devient voisé 'b' : 'さんびゃく' (sanbyaku) !"
            ),
            QuizQuestion(
                promptFr = "Comment dit-on '600' (六百 - Altération phonétique) ?",
                targetJp = "六百",
                correctAnswer = "ろっぴゃく (roppyaku)",
                options = listOf("ろっぴゃく (roppyaku)", "ろくひゃく (rokuhyaku)", "ろくびゃく (rokubyaku)", "ろく百 (roku)").shuffled(),
                explanation = "Pour 600, assimilation en 'p' avec petit tsu : 'ろっぴゃく' (roppyaku) !"
            ),
            QuizQuestion(
                promptFr = "Comment dit-on '8 000' (八千) ?",
                targetJp = "八千",
                correctAnswer = "はっせん (hassen)",
                options = listOf("はっせん (hassen)", "はちせん (hachisen)", "はちぜん (hachizen)", "はっぜん (hazzen)").shuffled(),
                explanation = "8 000 prend la double consonne : 'はっせん' (hassen)."
            ),
            QuizQuestion(
                promptFr = "Combien vaut '一万' (ichiman) en français ?",
                targetJp = "一万",
                correctAnswer = "10 000",
                options = listOf("10 000", "1 000", "100 000", "1 000 000").shuffled(),
                explanation = "Le japonais compte par tranches de 4 zéros : 万 (man) vaut 10 000."
            ),
            QuizQuestion(
                promptFr = "Comment dit-on '1 objet' avec le compteur général 'つ' ?",
                targetJp = "一つ",
                correctAnswer = "ひとつ (hitotsu)",
                options = listOf("ひとつ (hitotsu)", "いちつ (ichitsu)", "ひと (hito)", "いっこ (ikko)").shuffled(),
                explanation = "1 objet se dit 'ひとつ' (hitotsu)."
            )
        ).shuffled()
    }

    var currentIndex by remember { mutableIntStateOf(0) }
    val currentQ = questions[currentIndex % questions.size]
    var selectedOpt by remember { mutableStateOf<String?>(null) }
    var isAnswered by remember { mutableStateOf(false) }
    var isCorrect by remember { mutableStateOf(false) }

    LaunchedEffect(currentQ) {
        onSpeak(currentQ.targetJp)
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
                    text = "Quiz Pièges Heures & Nombres",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = JapanRedPrimary
                )
                TextButton(onClick = onExitQuiz) {
                    Text("Quitter le quiz")
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = currentQ.promptFr,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = currentQ.targetJp,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Black,
                    color = JapanRedPrimary
                )
                Spacer(modifier = Modifier.width(10.dp))
                IconButton(
                    onClick = { onSpeak(currentQ.targetJp) },
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
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Options
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                currentQ.options.forEach { opt ->
                    val isSelected = selectedOpt == opt
                    val isThisCorrect = opt == currentQ.correctAnswer

                    val buttonColor = when {
                        !isAnswered -> MaterialTheme.colorScheme.surfaceVariant
                        isThisCorrect -> Color(0xFFDCFCE7)
                        isSelected && !isThisCorrect -> Color(0xFFFFE4E6)
                        else -> MaterialTheme.colorScheme.surfaceVariant
                    }

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .clickable(enabled = !isAnswered) {
                                selectedOpt = opt
                                isAnswered = true
                                if (isThisCorrect) {
                                    isCorrect = true
                                    SoundEffectsManager.playCorrectSound()
                                } else {
                                    isCorrect = false
                                    SoundEffectsManager.playIncorrectSound()
                                }
                            },
                        color = buttonColor,
                        border = androidx.compose.foundation.BorderStroke(
                            width = 1.dp,
                            color = if (isAnswered && isThisCorrect) MatchaGreen else MaterialTheme.colorScheme.outlineVariant
                        )
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.padding(horizontal = 12.dp)
                        ) {
                            Text(
                                text = opt,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }

            if (isAnswered) {
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = if (isCorrect) "Bravo !" else "Attention : ${currentQ.explanation}",
                    fontWeight = FontWeight.Bold,
                    color = if (isCorrect) MatchaGreen else JapanRedPrimary,
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {
                        currentIndex++
                        selectedOpt = null
                        isAnswered = false
                        isCorrect = false
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = JapanRedPrimary),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("QUESTION SUIVANTE", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
