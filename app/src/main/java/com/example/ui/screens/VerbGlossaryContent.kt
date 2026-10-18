package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.datasource.MinnaVerbData
import com.example.data.model.ConjugatedForm
import com.example.data.model.RomajiDisplayMode
import com.example.data.model.VerbConjugation
import com.example.data.model.VerbGroup
import com.example.ui.components.JapaneseAudioButton
import com.example.ui.components.JapaneseFuriganaText
import com.example.ui.theme.GoldenYellow
import com.example.ui.theme.JapanRedPrimary
import com.example.ui.theme.MatchaGreen

enum class VerbConjugationFocus(val labelFr: String, val shortBadge: String) {
    ALL("Toutes les formes", "Toutes"),
    TE("Forme en -TE", "て形"),
    DICTIONARY("Dictionnaire (Neutre)", "辞書形"),
    NAI("Négatif neutre", "ない形"),
    TA("Passé neutre", "た形"),
    POTENTIAL("Potentiel (Capacité)", "可能形"),
    VOLITIONAL("Volitif (Suggestion)", "意向形")
}

@Composable
fun VerbGlossaryContent(
    searchQuery: String,
    bookmarkedWordIds: Set<String>,
    onToggleBookmark: (String) -> Unit,
    onSpeak: (String) -> Unit,
    romajiMode: RomajiDisplayMode = RomajiDisplayMode.BOTH,
    modifier: Modifier = Modifier
) {
    val allVerbs = remember { MinnaVerbData.allVerbs }
    var selectedGroup by remember { mutableStateOf<VerbGroup?>(null) }
    var onlyBookmarks by remember { mutableStateOf(false) }
    var selectedFocus by remember { mutableStateOf(VerbConjugationFocus.ALL) }
    var expandedVerbId by remember { mutableStateOf<String?>(null) }

    val filteredVerbs = remember(searchQuery, selectedGroup, onlyBookmarks, bookmarkedWordIds) {
        allVerbs.filter { verb ->
            val matchesSearch = searchQuery.isBlank() ||
                    verb.masuKanji.contains(searchQuery, ignoreCase = true) ||
                    verb.masuKana.contains(searchQuery, ignoreCase = true) ||
                    verb.masuRomaji.contains(searchQuery, ignoreCase = true) ||
                    verb.dictionaryKanji.contains(searchQuery, ignoreCase = true) ||
                    verb.dictionaryKana.contains(searchQuery, ignoreCase = true) ||
                    verb.dictionaryRomaji.contains(searchQuery, ignoreCase = true) ||
                    verb.teForm.kanji.contains(searchQuery, ignoreCase = true) ||
                    verb.teForm.kana.contains(searchQuery, ignoreCase = true) ||
                    verb.teForm.romaji.contains(searchQuery, ignoreCase = true) ||
                    verb.naiForm.kanji.contains(searchQuery, ignoreCase = true) ||
                    verb.taForm.kanji.contains(searchQuery, ignoreCase = true) ||
                    verb.french.contains(searchQuery, ignoreCase = true) ||
                    "leçon ${verb.lessonId}".contains(searchQuery, ignoreCase = true)

            val matchesGroup = selectedGroup == null || verb.group == selectedGroup
            val matchesBookmark = !onlyBookmarks || bookmarkedWordIds.contains(verb.id)

            matchesSearch && matchesGroup && matchesBookmark
        }
    }

    val g1Count = remember { allVerbs.count { it.group == VerbGroup.GROUP_1 } }
    val g2Count = remember { allVerbs.count { it.group == VerbGroup.GROUP_2 } }
    val g3Count = remember { allVerbs.count { it.group == VerbGroup.GROUP_3 } }
    val bookmarkedCount = remember(bookmarkedWordIds) {
        allVerbs.count { bookmarkedWordIds.contains(it.id) }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
        // Group Filter Chips & Bookmarks
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                FilterChip(
                    selected = selectedGroup == null && !onlyBookmarks,
                    onClick = {
                        selectedGroup = null
                        onlyBookmarks = false
                    },
                    label = { Text("Tous (${allVerbs.size})", fontWeight = FontWeight.SemiBold) }
                )
            }
            item {
                FilterChip(
                    selected = onlyBookmarks,
                    onClick = { onlyBookmarks = !onlyBookmarks },
                    label = { Text("⭐ Favoris ($bookmarkedCount)") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFFFEF3C7),
                        selectedLabelColor = Color(0xFF92400E)
                    )
                )
            }
            item {
                FilterChip(
                    selected = selectedGroup == VerbGroup.GROUP_1 && !onlyBookmarks,
                    onClick = {
                        selectedGroup = if (selectedGroup == VerbGroup.GROUP_1) null else VerbGroup.GROUP_1
                        onlyBookmarks = false
                    },
                    label = { Text("Groupe 1 • 五段 ($g1Count)") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFFDBEAFE),
                        selectedLabelColor = Color(0xFF1E40AF)
                    )
                )
            }
            item {
                FilterChip(
                    selected = selectedGroup == VerbGroup.GROUP_2 && !onlyBookmarks,
                    onClick = {
                        selectedGroup = if (selectedGroup == VerbGroup.GROUP_2) null else VerbGroup.GROUP_2
                        onlyBookmarks = false
                    },
                    label = { Text("Groupe 2 • 一段 ($g2Count)") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFFD1FAE5),
                        selectedLabelColor = Color(0xFF065F46)
                    )
                )
            }
            item {
                FilterChip(
                    selected = selectedGroup == VerbGroup.GROUP_3 && !onlyBookmarks,
                    onClick = {
                        selectedGroup = if (selectedGroup == VerbGroup.GROUP_3) null else VerbGroup.GROUP_3
                        onlyBookmarks = false
                    },
                    label = { Text("Groupe 3 • 不規則 ($g3Count)") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFFFEE2E2),
                        selectedLabelColor = Color(0xFF991B1B)
                    )
                )
            }
        }

        // Conjugation Focus Selector (Te, Dict, Nai, Ta, Potential, Volitional)
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 2.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(VerbConjugationFocus.entries.toTypedArray()) { focus ->
                val isSelected = selectedFocus == focus
                Surface(
                    onClick = { selectedFocus = focus },
                    shape = RoundedCornerShape(12.dp),
                    color = if (isSelected) JapanRedPrimary else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    contentColor = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(vertical = 2.dp)
                ) {
                    Text(
                        text = focus.labelFr,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }
            }
        }

        // Verb Cards List
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 6.dp),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 80.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                // Educational Tip Card
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("💡", fontSize = 18.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Tous les 105 verbes du manuel Minna no Nihongo avec leurs 3 groupes (Godan, Ichidan, Irréguliers) et leurs déclinaisons complètes (Forme en -TE, dictionnaire, négatif, passé, potentiel, volitif...). Appuyez sur une carte pour explorer toutes ses formes !",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 15.sp
                        )
                    }
                }
            }

            items(filteredVerbs, key = { it.id }) { verb ->
                val isBookmarked = bookmarkedWordIds.contains(verb.id)
                val isExpanded = expandedVerbId == verb.id

                VerbCardItem(
                    verb = verb,
                    isBookmarked = isBookmarked,
                    isExpanded = isExpanded,
                    selectedFocus = selectedFocus,
                    romajiMode = romajiMode,
                    onToggleExpand = {
                        expandedVerbId = if (isExpanded) null else verb.id
                    },
                    onToggleBookmark = { onToggleBookmark(verb.id) },
                    onSpeak = onSpeak
                )
            }
        }
    }

        // Persistent floating bar to close the declensions volet at any time
        AnimatedVisibility(
            visible = expandedVerbId != null,
            enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp)
        ) {
            val expandedVerb = remember(expandedVerbId) { allVerbs.find { it.id == expandedVerbId } }
            Surface(
                onClick = { expandedVerbId = null },
                shape = RoundedCornerShape(24.dp),
                color = JapanRedPrimary,
                shadowElevation = 8.dp,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .testTag("floating_close_declensions_button")
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 18.dp, vertical = 11.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Fermer le volet",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Fermer le volet des déclinaisons ${expandedVerb?.let { "• ${it.masuKanji}" } ?: ""}",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.Default.ExpandLess,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun VerbCardItem(
    verb: VerbConjugation,
    isBookmarked: Boolean,
    isExpanded: Boolean,
    selectedFocus: VerbConjugationFocus,
    romajiMode: RomajiDisplayMode,
    onToggleExpand: () -> Unit,
    onToggleBookmark: () -> Unit,
    onSpeak: (String) -> Unit
) {
    val groupColor = Color(verb.group.badgeColorHex)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isExpanded) 3.dp else 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            // Header Row: Group Tag, Lesson Tag, Verb Title, Audio & Bookmark
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Group Badge (G1, G2, G3)
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = groupColor.copy(alpha = 0.15f)
                ) {
                    Text(
                        text = verb.group.shortLabel,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black,
                        color = groupColor,
                        modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                    )
                }

                Spacer(modifier = Modifier.width(6.dp))

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Text(
                        text = "Leçon ${verb.lessonId}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                // Audio Button for base masu form
                JapaneseAudioButton(
                    textToSpeak = verb.masuKana,
                    onSpeak = onSpeak,
                    size = 34
                )

                Spacer(modifier = Modifier.width(4.dp))

                // Bookmark Icon Button
                IconButton(
                    onClick = onToggleBookmark,
                    modifier = Modifier.size(34.dp)
                ) {
                    Icon(
                        imageVector = if (isBookmarked) Icons.Default.Star else Icons.Default.StarBorder,
                        contentDescription = "Favori",
                        tint = if (isBookmarked) GoldenYellow else MaterialTheme.colorScheme.outline,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Main Verb Names: Kanji + Furigana, Romaji, and French translation
            val mainWordFurigana = if (verb.masuKanji.isNotBlank() && verb.masuKanji != verb.masuKana) {
                "[${verb.masuKanji}|${verb.masuKana}]"
            } else {
                verb.masuKana
            }

            JapaneseFuriganaText(
                text = mainWordFurigana,
                romajiMode = romajiMode,
                fontSize = 20.sp,
                rubySize = 11.sp,
                romajiSize = 12.sp
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = verb.french,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Key Quick Declensions Row (Masu, Dictionnaire, Te)
            when (selectedFocus) {
                VerbConjugationFocus.ALL -> {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        QuickConjugationPill(
                            title = "辞書形 (Dict.)",
                            kanji = verb.dictionaryKanji,
                            kana = verb.dictionaryKana,
                            romaji = verb.dictionaryRomaji,
                            romajiMode = romajiMode,
                            modifier = Modifier.weight(1f),
                            onSpeak = { onSpeak(verb.dictionaryKana) }
                        )

                        QuickConjugationPill(
                            title = "て形 (Te-form)",
                            kanji = verb.teForm.kanji,
                            kana = verb.teForm.kana,
                            romaji = verb.teForm.romaji,
                            romajiMode = romajiMode,
                            highlight = true,
                            modifier = Modifier.weight(1f),
                            onSpeak = { onSpeak(verb.teForm.kana) }
                        )
                    }
                }
                VerbConjugationFocus.TE -> {
                    QuickFocusedFormCard(
                        form = verb.teForm,
                        ruleNote = verb.teRuleExplanation,
                        romajiMode = romajiMode,
                        onSpeak = onSpeak
                    )
                }
                VerbConjugationFocus.DICTIONARY -> {
                    QuickFocusedFormCard(
                        form = ConjugatedForm("Forme Dictionnaire (辞書形)", verb.dictionaryKanji, verb.dictionaryKana, verb.dictionaryRomaji, "Forme neutre / présent d'infinitif"),
                        ruleNote = "Forme neutre utilisée pour ~ことができます (pouvoir), 趣味は~ことです (loisir), etc.",
                        romajiMode = romajiMode,
                        onSpeak = onSpeak
                    )
                }
                VerbConjugationFocus.NAI -> {
                    QuickFocusedFormCard(
                        form = verb.naiForm,
                        ruleNote = "Forme négative neutre utilisée pour ~ないでください (ne pas faire) et ~なければなりません (devoir faire).",
                        romajiMode = romajiMode,
                        onSpeak = onSpeak
                    )
                }
                VerbConjugationFocus.TA -> {
                    QuickFocusedFormCard(
                        form = verb.taForm,
                        ruleNote = "Forme passée neutre utilisée pour exprimer l'expérience : ~たことがあります (avoir déjà fait).",
                        romajiMode = romajiMode,
                        onSpeak = onSpeak
                    )
                }
                VerbConjugationFocus.POTENTIAL -> {
                    QuickFocusedFormCard(
                        form = verb.potentialForm,
                        ruleNote = "Exprime la capacité : être capable de / pouvoir faire l'action.",
                        romajiMode = romajiMode,
                        onSpeak = onSpeak
                    )
                }
                VerbConjugationFocus.VOLITIONAL -> {
                    QuickFocusedFormCard(
                        form = verb.volitionalForm,
                        ruleNote = "Suggestion amicale et enthousiaste : Faisons ! / Allons-y !",
                        romajiMode = romajiMode,
                        onSpeak = onSpeak
                    )
                }
            }

            // Te-form Rule memo strip
            if (selectedFocus == VerbConjugationFocus.ALL) {
                Spacer(modifier = Modifier.height(6.dp))
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = groupColor.copy(alpha = 0.08f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("📐", fontSize = 12.sp)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = verb.teRuleExplanation,
                            fontSize = 11.sp,
                            color = groupColor,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Expand / Collapse Full Declension Table Toggle
            Surface(
                onClick = onToggleExpand,
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = if (isExpanded) "Masquer le tableau des déclinaisons" else "Voir toutes les déclinaisons (9 formes & audio)",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = JapanRedPrimary
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = null,
                        tint = JapanRedPrimary,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            // Expanded Full Declension Table
            AnimatedVisibility(visible = isExpanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Tableau complet des conjugaisons",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Surface(
                            onClick = onToggleExpand,
                            shape = RoundedCornerShape(8.dp),
                            color = JapanRedPrimary.copy(alpha = 0.12f)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Fermer",
                                    tint = JapanRedPrimary,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(
                                    text = "Fermer",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = JapanRedPrimary
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // 1. Formes Polies (~ます)
                    DeclensionGroupSection(title = "Formes Polies (丁寧語 - Teineigo)") {
                        DeclensionRow(
                            label = "Présent Poli (~ます)",
                            kanji = verb.masuKanji,
                            kana = verb.masuKana,
                            romaji = verb.masuRomaji,
                            romajiMode = romajiMode,
                            onSpeak = { onSpeak(verb.masuKana) }
                        )
                        DeclensionRow(
                            label = "Négatif Poli (~ません)",
                            kanji = verb.masuNegative.kanji,
                            kana = verb.masuNegative.kana,
                            romaji = verb.masuNegative.romaji,
                            romajiMode = romajiMode,
                            onSpeak = { onSpeak(verb.masuNegative.kana) }
                        )
                        DeclensionRow(
                            label = "Passé Poli (~ました)",
                            kanji = verb.masuPast.kanji,
                            kana = verb.masuPast.kana,
                            romaji = verb.masuPast.romaji,
                            romajiMode = romajiMode,
                            onSpeak = { onSpeak(verb.masuPast.kana) }
                        )
                        DeclensionRow(
                            label = "Passé Négatif (~ませんでした)",
                            kanji = verb.masuPastNegative.kanji,
                            kana = verb.masuPastNegative.kana,
                            romaji = verb.masuPastNegative.romaji,
                            romajiMode = romajiMode,
                            onSpeak = { onSpeak(verb.masuPastNegative.kana) }
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // 2. Formes Neutres & Clés (Dictionnaire, Te, Nai, Ta)
                    DeclensionGroupSection(title = "Formes Neutres & Fondamentales (普通形)") {
                        DeclensionRow(
                            label = "Forme Dictionnaire (辞書形)",
                            kanji = verb.dictionaryKanji,
                            kana = verb.dictionaryKana,
                            romaji = verb.dictionaryRomaji,
                            romajiMode = romajiMode,
                            isHighlighted = true,
                            onSpeak = { onSpeak(verb.dictionaryKana) }
                        )
                        DeclensionRow(
                            label = "Forme en -TE (て形)",
                            kanji = verb.teForm.kanji,
                            kana = verb.teForm.kana,
                            romaji = verb.teForm.romaji,
                            romajiMode = romajiMode,
                            isHighlighted = true,
                            onSpeak = { onSpeak(verb.teForm.kana) }
                        )
                        DeclensionRow(
                            label = "Forme Négative Neutre (ない形)",
                            kanji = verb.naiForm.kanji,
                            kana = verb.naiForm.kana,
                            romaji = verb.naiForm.romaji,
                            romajiMode = romajiMode,
                            onSpeak = { onSpeak(verb.naiForm.kana) }
                        )
                        DeclensionRow(
                            label = "Forme Passée Neutre (た形)",
                            kanji = verb.taForm.kanji,
                            kana = verb.taForm.kana,
                            romaji = verb.taForm.romaji,
                            romajiMode = romajiMode,
                            onSpeak = { onSpeak(verb.taForm.kana) }
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // 3. Formes Avancées Minna no Nihongo (Potentiel, Volitif, Conditionnel, Impératif)
                    DeclensionGroupSection(title = "Déclinaisons Avancées (Modalités)") {
                        DeclensionRow(
                            label = "Forme Potentielle (可能形 - Pouvoir)",
                            kanji = verb.potentialForm.kanji,
                            kana = verb.potentialForm.kana,
                            romaji = verb.potentialForm.romaji,
                            romajiMode = romajiMode,
                            onSpeak = { onSpeak(verb.potentialForm.kana) }
                        )
                        DeclensionRow(
                            label = "Forme Volitive (意向形 - Faisons !)",
                            kanji = verb.volitionalForm.kanji,
                            kana = verb.volitionalForm.kana,
                            romaji = verb.volitionalForm.romaji,
                            romajiMode = romajiMode,
                            onSpeak = { onSpeak(verb.volitionalForm.kana) }
                        )
                        DeclensionRow(
                            label = "Forme Conditionnelle (ば形 - Si...)",
                            kanji = verb.conditionalForm.kanji,
                            kana = verb.conditionalForm.kana,
                            romaji = verb.conditionalForm.romaji,
                            romajiMode = romajiMode,
                            onSpeak = { onSpeak(verb.conditionalForm.kana) }
                        )
                        DeclensionRow(
                            label = "Forme Impérative (命令形 - Ordre)",
                            kanji = verb.imperativeForm.kanji,
                            kana = verb.imperativeForm.kana,
                            romaji = verb.imperativeForm.romaji,
                            romajiMode = romajiMode,
                            onSpeak = { onSpeak(verb.imperativeForm.kana) }
                        )
                    }

                    // Textbook Example Sentence
                    if (verb.exampleSentenceJp.isNotBlank()) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "📖 Exemple du manuel :",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.outline
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    JapaneseAudioButton(
                                        textToSpeak = verb.exampleSentenceJp,
                                        onSpeak = onSpeak,
                                        size = 28
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                JapaneseFuriganaText(
                                    text = verb.exampleSentenceJp,
                                    romajiMode = romajiMode,
                                    fontSize = 13.sp,
                                    rubySize = 8.sp,
                                    romajiSize = 9.sp
                                )
                                Text(
                                    text = verb.exampleSentenceFr,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Prominent button to close the large declensions panel at the bottom
                    Surface(
                        onClick = onToggleExpand,
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.8f),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.ExpandLess,
                                contentDescription = null,
                                tint = JapanRedPrimary,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Fermer le volet des déclinaisons ▲",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = JapanRedPrimary
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun QuickConjugationPill(
    title: String,
    kanji: String,
    kana: String,
    romaji: String,
    romajiMode: RomajiDisplayMode,
    highlight: Boolean = false,
    modifier: Modifier = Modifier,
    onSpeak: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = if (highlight) JapanRedPrimary.copy(alpha = 0.08f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        border = if (highlight) androidx.compose.foundation.BorderStroke(1.dp, JapanRedPrimary.copy(alpha = 0.3f)) else null,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (highlight) JapanRedPrimary else MaterialTheme.colorScheme.outline
                )
                Text(
                    text = kanji,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                if (romajiMode != RomajiDisplayMode.NONE) {
                    Text(
                        text = romaji,
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
            IconButton(
                onClick = onSpeak,
                modifier = Modifier.size(26.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                    contentDescription = "Écouter",
                    tint = if (highlight) JapanRedPrimary else MaterialTheme.colorScheme.outline,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
private fun QuickFocusedFormCard(
    form: ConjugatedForm,
    ruleNote: String,
    romajiMode: RomajiDisplayMode,
    onSpeak: (String) -> Unit
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = JapanRedPrimary.copy(alpha = 0.08f),
        border = androidx.compose.foundation.BorderStroke(1.dp, JapanRedPrimary.copy(alpha = 0.3f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = form.titleFr,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = JapanRedPrimary
                    )
                    Text(
                        text = form.kanji,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Black,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    if (romajiMode != RomajiDisplayMode.NONE) {
                        Text(
                            text = "${form.kana} • ${form.romaji}",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                }
                JapaneseAudioButton(
                    textToSpeak = form.kana,
                    onSpeak = onSpeak,
                    size = 32
                )
            }

            if (ruleNote.isNotBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "💡 $ruleNote",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 15.sp
                )
            }
        }
    }
}

@Composable
private fun DeclensionGroupSection(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = title,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
            )
            Spacer(modifier = Modifier.height(2.dp))
            content()
        }
    }
}

@Composable
private fun DeclensionRow(
    label: String,
    kanji: String,
    kana: String,
    romaji: String,
    romajiMode: RomajiDisplayMode,
    isHighlighted: Boolean = false,
    onSpeak: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .clickable(onClick = onSpeak)
            .padding(horizontal = 6.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                fontSize = 10.sp,
                color = if (isHighlighted) JapanRedPrimary else MaterialTheme.colorScheme.outline,
                fontWeight = if (isHighlighted) FontWeight.Bold else FontWeight.Normal
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = kanji,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                if (kanji != kana && kana.isNotBlank()) {
                    Text(
                        text = " ($kana)",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
            if (romajiMode != RomajiDisplayMode.NONE) {
                Text(
                    text = romaji,
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }

        IconButton(
            onClick = onSpeak,
            modifier = Modifier.size(28.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                contentDescription = "Écouter $kanji",
                tint = if (isHighlighted) JapanRedPrimary else MaterialTheme.colorScheme.outline,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
