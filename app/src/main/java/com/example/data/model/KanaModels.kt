package com.example.data.model

enum class RomajiDisplayMode(val labelFr: String) {
    NONE("Désactivé (Japonais pur)"),
    HIRAGANA_ONLY("Sous les Hiragana"),
    KATAKANA_ONLY("Sous les Katakana"),
    BOTH("Sous les deux (Hiragana + Katakana)")
}

enum class KanaType(val labelFr: String) {
    HIRAGANA("Hiragana (ひらがな)"),
    KATAKANA("Katakana (カタカナ)")
}

enum class KanaGroup(val labelFr: String) {
    SEION("Sons purs (46 de base)"),
    DAKUON("Sons voisés (Dakuon & Handakuon)"),
    YOON("Combinaisons (Yōon / Contractés)")
}

data class KanaCharacter(
    val character: String,
    val romaji: String,
    val type: KanaType,
    val group: KanaGroup,
    val strokeCount: Int = 1,
    val mnemonicFr: String = "",
    val exampleWordJp: String = "",
    val exampleWordRomaji: String = "",
    val exampleWordFr: String = ""
)
