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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.data.datasource.MinnaDatabase
import com.example.data.model.AiChatMessage
import com.example.data.model.RoleplayScenario
import com.example.data.model.RomajiDisplayMode
import com.example.service.GeminiChatService
import com.example.service.SpeechRecognizerHelper
import com.example.ui.components.JapaneseAudioButton
import com.example.ui.components.JapaneseFuriganaText
import com.example.ui.theme.GoldenYellow
import com.example.ui.theme.JapanRedPrimary
import com.example.ui.theme.MatchaGreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiConversationScreen(
    onBack: () -> Unit,
    onSpeak: (String) -> Unit,
    onAddXp: (Int) -> Unit,
    romajiMode: RomajiDisplayMode = RomajiDisplayMode.BOTH,
    autoPronounceEnabled: Boolean = true,
    bottomBar: @Composable () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val chatService = remember { GeminiChatService() }
    val speechHelper = remember { SpeechRecognizerHelper(context) }
    val isListening by speechHelper.isListening.collectAsState()
    val recognizedText by speechHelper.recognizedText.collectAsState()

    var selectedScenario by remember { mutableStateOf(MinnaDatabase.roleplayScenarios.first()) }
    var inputText by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val messages = remember {
        mutableStateListOf(
            AiChatMessage(
                id = "init_1",
                isUser = false,
                text = selectedScenario.initialAiMessageJp,
                romaji = selectedScenario.initialAiMessageRomaji,
                translationFr = selectedScenario.initialAiMessageFr,
                grammarNotes = "Bienvenue ! Parlez ou tapez en japonais. L'IA corrigera avec bienveillance votre grammaire selon Minna no Nihongo."
            )
        )
    }

    val listState = rememberLazyListState()

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            speechHelper.startListening { result ->
                inputText = result
            }
        }
    }

    LaunchedEffect(recognizedText) {
        if (recognizedText.isNotBlank()) {
            inputText = recognizedText
        }
    }

    fun handleSendMessage(textToSend: String) {
        if (textToSend.isBlank() || isLoading) return
        val userMsg = AiChatMessage(
            id = "user_${System.currentTimeMillis()}",
            isUser = true,
            text = textToSend.trim()
        )
        messages.add(userMsg)
        inputText = ""
        isLoading = true

        coroutineScope.launch {
            listState.animateScrollToItem(messages.size - 1)

            val history = messages.map { it.text to it.isUser }
            val response = chatService.sendMessage(
                systemPrompt = selectedScenario.systemPrompt,
                conversationHistory = history,
                userMessage = textToSend
            )

            messages.add(
                AiChatMessage(
                    id = "ai_${System.currentTimeMillis()}",
                    isUser = false,
                    text = response.japanese,
                    romaji = response.romaji,
                    translationFr = response.french,
                    grammarNotes = response.grammarTip
                )
            )
            onAddXp(10) // Conversational practice XP
            isLoading = false
            listState.animateScrollToItem(messages.size - 1)

            // Auto-speak response in Japanese if enabled
            if (autoPronounceEnabled) {
                onSpeak(response.japanese)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = selectedScenario.characterEmoji, fontSize = 24.sp)
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = selectedScenario.characterName,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = selectedScenario.titleFr,
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.testTag("ai_conversation_back_button")
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shadowElevation = 8.dp,
                    color = MaterialTheme.colorScheme.surface
                ) {
                Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
                    // Suggested reply chips
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        items(selectedScenario.suggestions) { suggestion ->
                            val cleanJp = suggestion.substringBefore(" (")
                            Surface(
                                shape = RoundedCornerShape(14.dp),
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(14.dp))
                                    .clickable { handleSendMessage(cleanJp) }
                            ) {
                                Text(
                                    text = suggestion,
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    // Input Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Voice input button
                        IconButton(
                            onClick = {
                                if (isListening) {
                                    speechHelper.stopListening()
                                } else {
                                    val hasPerm = ContextCompat.checkSelfPermission(
                                        context,
                                        Manifest.permission.RECORD_AUDIO
                                    ) == PackageManager.PERMISSION_GRANTED
                                    if (hasPerm) {
                                        speechHelper.startListening { result ->
                                            inputText = result
                                        }
                                    } else {
                                        permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                                    }
                                }
                            },
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(if (isListening) JapanRedPrimary else MaterialTheme.colorScheme.primaryContainer)
                                .testTag("ai_mic_input_button")
                        ) {
                            Icon(
                                imageVector = if (isListening) Icons.Default.MicOff else Icons.Default.Mic,
                                contentDescription = "Parler",
                                tint = if (isListening) Color.White else MaterialTheme.colorScheme.primary
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        OutlinedTextField(
                            value = inputText,
                            onValueChange = { inputText = it },
                            placeholder = { Text("Écrivez en japonais ou romaji...") },
                            modifier = Modifier
                                .weight(1f)
                                .testTag("ai_chat_input_field"),
                            shape = RoundedCornerShape(20.dp),
                            maxLines = 3
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        IconButton(
                            onClick = { handleSendMessage(inputText) },
                            enabled = inputText.isNotBlank() && !isLoading,
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(
                                    if (inputText.isNotBlank()) JapanRedPrimary
                                    else MaterialTheme.colorScheme.surfaceVariant
                                )
                                .testTag("ai_send_message_button")
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Send,
                                contentDescription = "Envoyer",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
            bottomBar()
        }
    },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Roleplay Scenario Selector Chips
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(MinnaDatabase.roleplayScenarios) { scenario ->
                    val isSelected = selectedScenario.id == scenario.id
                    FilterChip(
                        selected = isSelected,
                        onClick = {
                            selectedScenario = scenario
                            messages.clear()
                            messages.add(
                                AiChatMessage(
                                    id = "init_${scenario.id}",
                                    isUser = false,
                                    text = scenario.initialAiMessageJp,
                                    romaji = scenario.initialAiMessageRomaji,
                                    translationFr = scenario.initialAiMessageFr,
                                    grammarNotes = "Nouveau scénario lancé : ${scenario.titleFr}."
                                )
                            )
                        },
                        label = { Text("${scenario.characterEmoji} ${scenario.title}") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = JapanRedPrimary,
                            selectedLabelColor = Color.White
                        )
                    )
                }
            }

            // Chat Messages Stream
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(vertical = 12.dp)
            ) {
                items(messages) { msg ->
                    ChatMessageBubble(
                        message = msg,
                        onSpeak = onSpeak,
                        romajiMode = romajiMode
                    )
                }

                if (isLoading) {
                    item {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                strokeWidth = 2.dp,
                                color = JapanRedPrimary
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "${selectedScenario.characterName} réfléchit...",
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.outline
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ChatMessageBubble(
    message: AiChatMessage,
    onSpeak: (String) -> Unit,
    romajiMode: RomajiDisplayMode = RomajiDisplayMode.BOTH
) {
    var showDetails by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (message.isUser) Alignment.End else Alignment.Start
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = if (message.isUser) Arrangement.End else Arrangement.Start,
            modifier = Modifier.fillMaxWidth(0.92f)
        ) {
            Surface(
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomStart = if (message.isUser) 16.dp else 4.dp,
                    bottomEnd = if (message.isUser) 4.dp else 16.dp
                ),
                color = if (message.isUser) JapanRedPrimary else MaterialTheme.colorScheme.surface,
                shadowElevation = if (message.isUser) 0.dp else 2.dp,
                border = if (message.isUser) null else androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (message.isUser) {
                            Text(
                                text = message.text,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White,
                                modifier = Modifier.weight(1f)
                            )
                        } else {
                            Box(modifier = Modifier.weight(1f)) {
                                JapaneseFuriganaText(
                                    text = message.text,
                                    romajiMode = romajiMode,
                                    fontSize = 17.sp,
                                    rubySize = 9.sp,
                                    romajiSize = 10.sp,
                                    textColor = MaterialTheme.colorScheme.onSurface,
                                    rubyColor = MaterialTheme.colorScheme.primary
                                )
                            }
                        }

                        if (!message.isUser) {
                            Spacer(modifier = Modifier.width(6.dp))
                            JapaneseAudioButton(
                                textToSpeak = message.text,
                                onSpeak = onSpeak,
                                size = 32
                            )
                        }
                    }

                    if (!message.isUser && (message.romaji.isNotBlank() || message.translationFr.isNotBlank())) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable { showDetails = !showDetails }
                        ) {
                            Text(
                                text = if (showDetails) "Masquer romaji / FR ▲" else "Afficher romaji & traduction ▼",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Medium
                            )
                        }

                        AnimatedVisibility(visible = showDetails) {
                            Column(modifier = Modifier.padding(top = 4.dp)) {
                                if (message.romaji.isNotBlank()) {
                                    Text(
                                        text = message.romaji,
                                        fontSize = 13.sp,
                                        color = MaterialTheme.colorScheme.outline
                                    )
                                }
                                if (message.translationFr.isNotBlank()) {
                                    Text(
                                        text = message.translationFr,
                                        fontSize = 13.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }

                    if (!message.isUser && message.grammarNotes != null) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFFEF9C3))
                                .border(1.dp, Color(0xFFFEF08A), RoundedCornerShape(8.dp))
                                .padding(8.dp)
                        ) {
                            Text(
                                text = "💡 Conseil du tuteur : ${message.grammarNotes}",
                                fontSize = 12.sp,
                                color = Color(0xFF854D0E)
                            )
                        }
                    }
                }
            }
        }
    }
}
