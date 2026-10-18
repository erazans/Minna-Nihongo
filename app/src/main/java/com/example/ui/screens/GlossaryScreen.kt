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
import androidx.compose.foundation.text.BasicTextField
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
import com.example.data.model.RomajiDisplayMode
import com.example.data.model.VocabularyWord
import com.example.data.model.WordCategory
import com.example.ui.components.JapaneseAudioButton
import com.example.ui.components.JapaneseFuriganaText
import com.example.ui.theme.GoldenYellow
import com.example.ui.theme.JapanRedPrimary
import com.example.ui.theme.MatchaGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GlossaryScreen(
    onBack: () -> Unit,
    onSpeak: (String) -> Unit,
    bookmarkedWordIds: Set<String>,
    masteredWordIds: Set<String>,
    onToggleBookmark: (String) -> Unit,
    onToggleMastered: (String) -> Unit,
    romajiMode: RomajiDisplayMode = RomajiDisplayMode.BOTH,
    autoPronounceEnabled: Boolean = true,
    bottomBar: @Composable () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedTab by remember { mutableIntStateOf(0) }
    var selectedCategory by remember { mutableStateOf<WordCategory?>(null) }
    var onlyBookmarks by remember { mutableStateOf(false) }

    val allWords = MinnaDatabase.vocabulary
    val allGrammar = MinnaDatabase.grammarPoints
    val allParticles = com.example.data.datasource.MinnaParticlesData.allParticles

    val filteredWords = remember(searchQuery, selectedCategory, onlyBookmarks, bookmarkedWordIds) {
        allWords.filter { word ->
            val matchesSearch = searchQuery.isBlank() ||
                    word.kanji.contains(searchQuery, ignoreCase = true) ||
                    word.kana.contains(searchQuery, ignoreCase = true) ||
                    word.romaji.contains(searchQuery, ignoreCase = true) ||
                    word.french.contains(searchQuery, ignoreCase = true) ||
                    "leçon ${word.lessonId}".contains(searchQuery, ignoreCase = true)

            val matchesCategory = selectedCategory == null || word.category == selectedCategory
            val matchesBookmark = !onlyBookmarks || bookmarkedWordIds.contains(word.id)

            matchesSearch && matchesCategory && matchesBookmark
        }
    }

    val filteredGrammar = remember(searchQuery) {
        allGrammar.filter { g ->
            searchQuery.isBlank() ||
                    g.pattern.contains(searchQuery, ignoreCase = true) ||
                    g.meaningFr.contains(searchQuery, ignoreCase = true) ||
                    g.explanation.contains(searchQuery, ignoreCase = true) ||
                    "leçon ${g.lessonId}".contains(searchQuery, ignoreCase = true)
        }
    }

    val filteredParticles = remember(searchQuery) {
        allParticles.filter { p ->
            searchQuery.isBlank() ||
                    p.name.contains(searchQuery, ignoreCase = true) ||
                    p.romaji.contains(searchQuery, ignoreCase = true) ||
                    p.meaningFr.contains(searchQuery, ignoreCase = true) ||
                    p.functionFr.contains(searchQuery, ignoreCase = true) ||
                    p.lessonReference.contains(searchQuery, ignoreCase = true) ||
                    p.nuancesFr.contains(searchQuery, ignoreCase = true)
        }
    }

    Scaffold(
        bottomBar = bottomBar,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Glossaire & Grammaire",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.testTag("glossary_back_button")
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Compact Single-Line Search Bar
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f),
                border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(38.dp)
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = MaterialTheme.colorScheme.outline
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    BasicTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("glossary_search_input"),
                        singleLine = true,
                        textStyle = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 13.sp
                        ),
                        decorationBox = { innerTextField ->
                            if (searchQuery.isEmpty()) {
                                Text(
                                    text = "Rechercher un mot, verbe, kanji, leçon...",
                                    fontSize = 13.sp,
                                    color = MaterialTheme.colorScheme.outline
                                )
                            }
                            innerTextField()
                        }
                    )
                    if (searchQuery.isNotEmpty()) {
                        IconButton(
                            onClick = { searchQuery = "" },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Effacer",
                                modifier = Modifier.size(16.dp),
                                tint = MaterialTheme.colorScheme.outline
                            )
                        }
                    }
                }
            }

            // Tabs: Vocabulaire, Verbes, Grammaire, Particules, Révisions & Heures/Nombres
            val totalVerbsCount = remember { com.example.data.datasource.MinnaVerbData.allVerbs.size }
            ScrollableTabRow(
                selectedTabIndex = selectedTab,
                edgePadding = 8.dp,
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = { Text("Vocabulaire (${filteredWords.size})", fontWeight = FontWeight.Bold, fontSize = 13.sp) },
                    modifier = Modifier.testTag("tab_vocab")
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = { Text("🥋 Verbes ($totalVerbsCount)", fontWeight = FontWeight.Bold, fontSize = 13.sp) },
                    modifier = Modifier.testTag("tab_verbs")
                )
                Tab(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    text = { Text("Grammaire (${filteredGrammar.size})", fontWeight = FontWeight.Bold, fontSize = 13.sp) },
                    modifier = Modifier.testTag("tab_grammar")
                )
                Tab(
                    selected = selectedTab == 3,
                    onClick = { selectedTab = 3 },
                    text = { Text("Particules (${filteredParticles.size})", fontWeight = FontWeight.Bold, fontSize = 13.sp) },
                    modifier = Modifier.testTag("tab_particles")
                )
                Tab(
                    selected = selectedTab == 4,
                    onClick = { selectedTab = 4 },
                    text = { Text("🔄 Révisions (復習)", fontWeight = FontWeight.Bold, fontSize = 13.sp) },
                    modifier = Modifier.testTag("tab_reviews")
                )
                Tab(
                    selected = selectedTab == 5,
                    onClick = { selectedTab = 5 },
                    text = { Text("⏰ Heures & Nombres", fontWeight = FontWeight.Bold, fontSize = 13.sp) },
                    modifier = Modifier.testTag("tab_hours_numbers")
                )
            }

            if (selectedTab == 0) {
                // Category Filter Chips
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        FilterChip(
                            selected = selectedCategory == null && !onlyBookmarks,
                            onClick = {
                                selectedCategory = null
                                onlyBookmarks = false
                            },
                            label = { Text("Tous") }
                        )
                    }
                    item {
                        FilterChip(
                            selected = onlyBookmarks,
                            onClick = { onlyBookmarks = !onlyBookmarks },
                            label = { Text("⭐ Favoris (${bookmarkedWordIds.size})") },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = Color(0xFFFEF3C7),
                                selectedLabelColor = Color(0xFF92400E)
                            )
                        )
                    }
                    items(WordCategory.entries.toTypedArray()) { cat ->
                        FilterChip(
                            selected = selectedCategory == cat && !onlyBookmarks,
                            onClick = {
                                selectedCategory = if (selectedCategory == cat) null else cat
                                onlyBookmarks = false
                            },
                            label = { Text(cat.labelFr) }
                        )
                    }
                }

                // Vocabulary Items List
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filteredWords) { word ->
                        val isBookmarked = bookmarkedWordIds.contains(word.id)
                        val isMastered = masteredWordIds.contains(word.id)

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
                                JapaneseAudioButton(textToSpeak = word.kana, onSpeak = onSpeak)

                                Spacer(modifier = Modifier.width(12.dp))

                                Column(modifier = Modifier.weight(1f)) {
                                    val wordText = if (word.kanji.isNotBlank() && word.kanji != word.kana) {
                                        "[${word.kanji}|${word.kana}]"
                                    } else {
                                        word.kana
                                    }

                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        JapaneseFuriganaText(
                                            text = wordText,
                                            romajiMode = romajiMode,
                                            fontSize = 18.sp,
                                            rubySize = 10.sp,
                                            romajiSize = 11.sp
                                        )

                                        Spacer(modifier = Modifier.width(8.dp))
                                        Surface(
                                            shape = RoundedCornerShape(6.dp),
                                            color = MaterialTheme.colorScheme.surfaceVariant
                                        ) {
                                            Text(
                                                text = "Leçon ${word.lessonId}",
                                                fontSize = 10.sp,
                                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                                color = MaterialTheme.colorScheme.outline
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(3.dp))

                                    Text(
                                        text = word.french,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )

                                    if (word.exampleJp.isNotBlank()) {
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = "例: ${word.exampleJp} (${word.exampleFr})",
                                            fontSize = 11.sp,
                                            color = MaterialTheme.colorScheme.outline,
                                            lineHeight = 15.sp
                                        )
                                    }
                                }

                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    IconButton(
                                        onClick = { onToggleBookmark(word.id) },
                                        modifier = Modifier.size(36.dp)
                                    ) {
                                        Icon(
                                            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                                            contentDescription = "Favori",
                                            tint = if (isBookmarked) GoldenYellow else MaterialTheme.colorScheme.outline
                                        )
                                    }

                                    IconButton(
                                        onClick = { onToggleMastered(word.id) },
                                        modifier = Modifier.size(36.dp)
                                    ) {
                                        Icon(
                                            imageVector = if (isMastered) Icons.Default.CheckCircle else Icons.Default.CheckCircleOutline,
                                            contentDescription = "Appris",
                                            tint = if (isMastered) MatchaGreen else MaterialTheme.colorScheme.outline
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (selectedTab == 1) {
                // Verbes avec toutes leurs déclinaisons et conjugaisons
                VerbGlossaryContent(
                    searchQuery = searchQuery,
                    bookmarkedWordIds = bookmarkedWordIds,
                    onToggleBookmark = onToggleBookmark,
                    onSpeak = onSpeak,
                    romajiMode = romajiMode
                )
            } else if (selectedTab == 2) {
                // Grammar List
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(filteredGrammar) { g ->
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
                                        color = MaterialTheme.colorScheme.primaryContainer,
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Box(modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)) {
                                            JapaneseFuriganaText(
                                                text = g.pattern,
                                                romajiMode = romajiMode,
                                                fontSize = 15.sp,
                                                rubySize = 9.sp,
                                                romajiSize = 10.sp,
                                                textColor = MaterialTheme.colorScheme.onPrimaryContainer
                                            )
                                        }
                                    }

                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = MaterialTheme.colorScheme.surfaceVariant
                                    ) {
                                        Text(
                                            text = "Leçon ${g.lessonId}",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = g.meaningFr,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = g.explanation,
                                    fontSize = 13.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )

                                Spacer(modifier = Modifier.height(8.dp))
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color(0xFFFEF3C7))
                                        .padding(8.dp)
                                ) {
                                    Text(
                                        text = "Formule : ${g.formula}",
                                        fontSize = 12.sp,
                                        color = Color(0xFF92400E)
                                    )
                                }
                            }
                        }
                    }
                }
            } else if (selectedTab == 3) {
                // Particles List (助詞)
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(filteredParticles) { p ->
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
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Surface(
                                            color = MaterialTheme.colorScheme.primaryContainer,
                                            shape = RoundedCornerShape(10.dp)
                                        ) {
                                            Box(modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)) {
                                                Text(
                                                    text = "${p.name} [${p.romaji}]",
                                                    fontWeight = FontWeight.Black,
                                                    fontSize = 18.sp,
                                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                                )
                                            }
                                        }
                                        Spacer(modifier = Modifier.width(8.dp))
                                        JapaneseAudioButton(
                                            textToSpeak = p.name,
                                            onSpeak = onSpeak,
                                            size = 34
                                        )
                                    }

                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = MaterialTheme.colorScheme.surfaceVariant
                                    ) {
                                        Text(
                                            text = p.lessonReference,
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = p.meaningFr,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )

                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = p.functionFr,
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
                                        text = "💡 Formule : ${p.formula}",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = Color(0xFF92400E)
                                    )
                                }

                                if (p.nuancesFr.isNotBlank()) {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(Color(0xFFEFF6FF))
                                            .border(1.dp, Color(0xFFDBEAFE), RoundedCornerShape(8.dp))
                                            .padding(8.dp)
                                    ) {
                                        Text(
                                            text = "🔍 Nuance clé : ${p.nuancesFr}",
                                            fontSize = 12.sp,
                                            color = Color(0xFF1E40AF),
                                            lineHeight = 16.sp
                                        )
                                    }
                                }

                                if (p.examples.isNotEmpty()) {
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        text = "Exemples du manuel :",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.outline
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                        p.examples.forEach { ex ->
                                            Surface(
                                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                                                shape = RoundedCornerShape(8.dp),
                                                modifier = Modifier.fillMaxWidth()
                                            ) {
                                                Row(
                                                    modifier = Modifier.padding(8.dp),
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    Column(modifier = Modifier.weight(1f)) {
                                                        JapaneseFuriganaText(
                                                            text = ex.jp,
                                                            romajiMode = romajiMode,
                                                            fontSize = 13.sp,
                                                            rubySize = 8.sp,
                                                            romajiSize = 9.sp,
                                                            textColor = MaterialTheme.colorScheme.onSurface
                                                        )
                                                        Text(
                                                            text = ex.fr,
                                                            fontSize = 12.sp,
                                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                                        )
                                                    }
                                                    JapaneseAudioButton(
                                                        textToSpeak = ex.jp,
                                                        onSpeak = onSpeak,
                                                        size = 30
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
            } else if (selectedTab == 4) {
                // Leçons de Révision (復習)
                GlossaryReviewContent(
                    onSpeak = onSpeak,
                    romajiMode = romajiMode,
                    autoPronounceEnabled = autoPronounceEnabled
                )
            } else if (selectedTab == 5) {
                // Heures & Nombres with particularities
                NumbersTimeStudyContent(
                    onSpeak = onSpeak
                )
            }
        }
    }
}
