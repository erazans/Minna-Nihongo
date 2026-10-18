package com.example.data.model

enum class ExerciseType {
    MULTIPLE_CHOICE,
    WORD_ORDER,
    LISTENING_SELECT,
    SPEECH_PRONUNCIATION,
    FILL_BLANK
}

enum class ChapterSectionType(val titleFr: String, val shortLabel: String, val iconEmoji: String) {
    VOCABULARY("Vocabulaire & Audio", "語彙", "🎴"),
    RENSHUU_A("Modèles de phrases", "練習A", "📐"),
    RENSHUU_B("Exercices du livre", "練習B", "✏️"),
    RENSHUU_C("Dialogues & Rôleplay", "練習C", "💬"),
    MONDAI("Test de validation", "問題", "🎯"),
    REVISION("Révision Répétable", "復習", "🔄")
}

enum class WordCategory(val labelFr: String) {
    GREETING("Salutations & Politesse"),
    NOUN("Noms"),
    VERB_G1("Verbes Groupe I (Godan)"),
    VERB_G2("Verbes Groupe II (Ichidan)"),
    VERB_G3("Verbes Groupe III (Irréguliers)"),
    ADJ_I("Adjectifs en -i"),
    ADJ_NA("Adjectifs en -na"),
    PARTICLE("Particules"),
    ADVERB("Adverbes & Conjonctions"),
    EXPRESSION("Expressions courantes")
}

data class VocabularyWord(
    val id: String,
    val lessonId: Int,
    val kanji: String,
    val kana: String,
    val romaji: String,
    val french: String,
    val category: WordCategory,
    val exampleJp: String = "",
    val exampleRomaji: String = "",
    val exampleFr: String = ""
)

data class GrammarPoint(
    val id: String,
    val lessonId: Int,
    val pattern: String,
    val meaningFr: String,
    val explanation: String,
    val formula: String,
    val examples: List<GrammarExample>
)

data class GrammarExample(
    val jp: String,
    val romaji: String,
    val fr: String
)

data class Exercise(
    val id: String,
    val lessonId: Int,
    val section: ChapterSectionType = ChapterSectionType.RENSHUU_B,
    val bookReference: String? = null, // e.g. "練習B-1", "問題-3"
    val type: ExerciseType,
    val questionFr: String,
    val promptJp: String? = null,
    val targetSentenceJp: String,
    val targetSentenceRomaji: String,
    val options: List<String> = emptyList(),
    val correctOptions: List<String> = emptyList(), // For word order or MC
    val hint: String? = null,
    val audioText: String? = null
)

data class DialogueLine(
    val speaker: String,
    val avatarEmoji: String,
    val textJp: String,
    val textRomaji: String,
    val textFr: String,
    val isUserRole: Boolean = false
)

data class LessonDialogue(
    val id: String,
    val lessonId: Int,
    val title: String,
    val titleFr: String,
    val settingDescription: String,
    val lines: List<DialogueLine>
)

data class Lesson(
    val id: Int,
    val unitNumber: Int = id, // 1 Unit per chapter!
    val unitTitleFr: String,
    val titleJp: String,
    val titleRomaji: String,
    val titleFr: String,
    val descriptionFr: String,
    val iconEmoji: String,
    val wordsCount: Int,
    val grammarPointsCount: Int,
    val isUnlocked: Boolean = true,
    val isCompleted: Boolean = false,
    val stars: Int = 0, // 0 to 3
    val completedSections: Set<ChapterSectionType> = emptySet()
)

data class UserStats(
    val streakDays: Int = 0,
    val lastActiveDate: String = "",
    val totalXp: Int = 0,
    val todayXp: Int = 0,
    val dailyGoalXp: Int = 0,
    val lives: Int = 5,
    val maxLives: Int = 5,
    val completedLessonIds: Set<Int> = emptySet(),
    val bookmarkedWordIds: Set<String> = emptySet(),
    val masteredWordIds: Set<String> = emptySet(),
    val lessonsProgress: Map<Int, Float> = emptyMap(),
    val totalQuestionsAnswered: Int = 0,
    val correctAnswers: Int = 0,
    val dailyReminderEnabled: Boolean = true,
    val reminderHour: Int = 19,
    val reminderMinute: Int = 0,
    val romajiDisplayMode: RomajiDisplayMode = RomajiDisplayMode.BOTH,
    val autoPronounceWords: Boolean = true,
    val masteredKana: Set<String> = emptySet(),
    val masteredKanji: Set<String> = emptySet()
)

data class AiChatMessage(
    val id: String,
    val isUser: Boolean,
    val text: String,
    val romaji: String = "",
    val translationFr: String = "",
    val grammarNotes: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)

data class RoleplayScenario(
    val id: String,
    val title: String,
    val titleFr: String,
    val characterName: String,
    val characterEmoji: String,
    val settingFr: String,
    val systemPrompt: String,
    val initialAiMessageJp: String,
    val initialAiMessageRomaji: String,
    val initialAiMessageFr: String,
    val suggestions: List<String>
)
