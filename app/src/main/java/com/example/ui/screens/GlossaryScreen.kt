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
import com.example.ui.components.RomajiModeSelectorBar
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
    onUpdateRomajiMode: (RomajiDisplayMode) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedTab by remember { mutableIntStateOf(0) }
    var selectedCategory by remember { mutableStateOf<WordCategory?>(null) }
    var onlyBookmarks by remember { mutableStateOf(false) }

    val allWords = MinnaDatabase.vocabulary
    val allGrammar = MinnaDatabase.grammarPoints

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

    Scaffold(
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
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .testTag("glossary_search_input"),
                placeholder = { Text("Rechercher (kanji, romaji, français, leçon...)") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = {
                    if (searchQuery.isNotBlank()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(Icons.Default.Clear, contentDescription = "Effacer")
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp)
            )

            // Tabs: Vocabulaire vs Grammaire
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = { Text("Vocabulaire (${filteredWords.size})", fontWeight = FontWeight.Bold) }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = { Text("Grammaire (${filteredGrammar.size})", fontWeight = FontWeight.Bold) }
                )
            }

            // Latin alphabet display options bar
            RomajiModeSelectorBar(
                currentMode = romajiMode,
                onModeSelected = onUpdateRomajiMode
            )

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
            } else {
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
            }
        }
    }
}
