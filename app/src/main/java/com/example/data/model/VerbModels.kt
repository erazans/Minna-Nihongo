package com.example.data.model

enum class VerbGroup(val labelFr: String, val shortLabel: String, val badgeColorHex: Long) {
    GROUP_1("Groupe 1 (五段 - Godan)", "G1", 0xFF2563EB), // Bleu
    GROUP_2("Groupe 2 (一段 - Ichidan)", "G2", 0xFF059669), // Vert émeraude
    GROUP_3("Groupe 3 (不規則 - Irrégulier)", "G3", 0xFFDC2626) // Rouge
}

data class ConjugatedForm(
    val titleFr: String,
    val kanji: String,
    val kana: String,
    val romaji: String,
    val descriptionFr: String = ""
)

data class VerbConjugation(
    val id: String,
    val lessonId: Int,
    val dictionaryKanji: String,
    val dictionaryKana: String,
    val dictionaryRomaji: String,
    val masuKanji: String,
    val masuKana: String,
    val masuRomaji: String,
    val french: String,
    val group: VerbGroup,
    val teForm: ConjugatedForm,
    val taForm: ConjugatedForm,
    val naiForm: ConjugatedForm,
    val potentialForm: ConjugatedForm,
    val volitionalForm: ConjugatedForm,
    val conditionalForm: ConjugatedForm,
    val imperativeForm: ConjugatedForm,
    val masuNegative: ConjugatedForm,
    val masuPast: ConjugatedForm,
    val masuPastNegative: ConjugatedForm,
    val teRuleExplanation: String,
    val exampleSentenceJp: String = "",
    val exampleSentenceRomaji: String = "",
    val exampleSentenceFr: String = ""
)
