package com.example.data.datasource

enum class NumberCategory(val labelFr: String) {
    DIGITS_0_10("Chiffres 0 à 10"),
    TENS_11_99("Dizaines & 11 à 99"),
    HUNDREDS_THOUSANDS("Centaines & Milliers"),
    LARGE_NUMBERS("Grands Nombres (万・億)"),
    COUNTERS_TSU("Objets généraux (つ)")
}

enum class TimeItemType(val labelFr: String) {
    HOUR("Les Heures (〜時)"),
    MINUTE("Les Minutes (〜分)"),
    EXPRESSION("Expressions de temps")
}

data class JapaneseNumberItem(
    val arabic: String,
    val kanji: String,
    val kana: String,
    val romaji: String,
    val french: String,
    val hasSpecialReading: Boolean = false,
    val noteFr: String? = null,
    val category: NumberCategory
)

data class JapaneseTimeItem(
    val display: String,
    val kanji: String,
    val kana: String,
    val romaji: String,
    val french: String,
    val isIrregular: Boolean = false,
    val particularityNote: String? = null,
    val type: TimeItemType
)

object MinnaNumbersTimeData {

    // ==========================================
    // 1. CHIFFRES ET NOMBRES JAPONAIS
    // ==========================================

    val digits0To10: List<JapaneseNumberItem> = listOf(
        JapaneseNumberItem(
            arabic = "0",
            kanji = "〇 / 零",
            kana = "ゼロ / れい",
            romaji = "zero / rei",
            french = "Zéro",
            hasSpecialReading = true,
            noteFr = "On utilise couramment 'ゼロ' (zero), 'れい' (rei) pour les décimales ou la météo.",
            category = NumberCategory.DIGITS_0_10
        ),
        JapaneseNumberItem(
            arabic = "1",
            kanji = "一",
            kana = "いち",
            romaji = "ichi",
            french = "Un",
            category = NumberCategory.DIGITS_0_10
        ),
        JapaneseNumberItem(
            arabic = "2",
            kanji = "二",
            kana = "に",
            romaji = "ni",
            french = "Deux",
            category = NumberCategory.DIGITS_0_10
        ),
        JapaneseNumberItem(
            arabic = "3",
            kanji = "三",
            kana = "さん",
            romaji = "san",
            french = "Trois",
            category = NumberCategory.DIGITS_0_10
        ),
        JapaneseNumberItem(
            arabic = "4",
            kanji = "四",
            kana = "よん / し",
            romaji = "yon / shi",
            french = "Quatre",
            hasSpecialReading = true,
            noteFr = "⚠️ 'よん' (yon) est le plus usuel. 'し' (shi) est évité car homophone de la mort (死), sauf dans les mois (四月 = しがつ).",
            category = NumberCategory.DIGITS_0_10
        ),
        JapaneseNumberItem(
            arabic = "5",
            kanji = "五",
            kana = "ご",
            romaji = "go",
            french = "Cinq",
            category = NumberCategory.DIGITS_0_10
        ),
        JapaneseNumberItem(
            arabic = "6",
            kanji = "六",
            kana = "ろく",
            romaji = "roku",
            french = "Six",
            category = NumberCategory.DIGITS_0_10
        ),
        JapaneseNumberItem(
            arabic = "7",
            kanji = "七",
            kana = "なな / しち",
            romaji = "nana / shichi",
            french = "Sept",
            hasSpecialReading = true,
            noteFr = "⚠️ 'なな' (nana) évite la confusion avec 'いち' (1) ou 'し' (4). 'しち' s'utilise pour les heures (七時 = しちじ) et les mois (七月 = しちがつ).",
            category = NumberCategory.DIGITS_0_10
        ),
        JapaneseNumberItem(
            arabic = "8",
            kanji = "八",
            kana = "はち",
            romaji = "hachi",
            french = "Huit",
            category = NumberCategory.DIGITS_0_10
        ),
        JapaneseNumberItem(
            arabic = "9",
            kanji = "九",
            kana = "きゅう / く",
            romaji = "kyuu / ku",
            french = "Neuf",
            hasSpecialReading = true,
            noteFr = "⚠️ 'きゅう' (kyuu) est le plus courant. 'く' (ku) est utilisé pour les heures (九時 = くじ) et les mois (九月 = くがつ).",
            category = NumberCategory.DIGITS_0_10
        ),
        JapaneseNumberItem(
            arabic = "10",
            kanji = "十",
            kana = "じゅう",
            romaji = "juu",
            french = "Dix",
            category = NumberCategory.DIGITS_0_10
        )
    )

    val tens11To99: List<JapaneseNumberItem> = listOf(
        JapaneseNumberItem("11", "十一", "じゅういち", "juuichi", "Onze", category = NumberCategory.TENS_11_99),
        JapaneseNumberItem("14", "十四", "じゅうよん", "juuyon", "Quatorze", hasSpecialReading = true, noteFr = "Se prononce 'juu-yon'.", category = NumberCategory.TENS_11_99),
        JapaneseNumberItem("17", "十七", "じゅうなな / じゅうしち", "juunana / juushichi", "Dix-sept", category = NumberCategory.TENS_11_99),
        JapaneseNumberItem("19", "十九", "じゅうきゅう / じゅうく", "juukyuu / juuku", "Dix-neuf", category = NumberCategory.TENS_11_99),
        JapaneseNumberItem("20", "二十", "にじゅう", "nijuu", "Vingt", category = NumberCategory.TENS_11_99),
        JapaneseNumberItem("30", "三十", "さんじゅう", "sanjuu", "Trente", category = NumberCategory.TENS_11_99),
        JapaneseNumberItem("40", "四十", "よんじゅう", "yonjuu", "Quarante", hasSpecialReading = true, noteFr = "Toujours 'yon-juu' (jamais 'shijuu').", category = NumberCategory.TENS_11_99),
        JapaneseNumberItem("50", "五十", "ごじゅう", "gojuu", "Cinquante", category = NumberCategory.TENS_11_99),
        JapaneseNumberItem("60", "六十", "ろくじゅう", "rokujuu", "Soixante", category = NumberCategory.TENS_11_99),
        JapaneseNumberItem("70", "七十", "ななじゅう", "nanajuu", "Soixante-dix", hasSpecialReading = true, noteFr = "Préféré à 'shichijuu' pour plus de clarté.", category = NumberCategory.TENS_11_99),
        JapaneseNumberItem("80", "八十", "はちじゅう", "hachijuu", "Quatre-vingts", category = NumberCategory.TENS_11_99),
        JapaneseNumberItem("90", "九十", "きゅうじゅう", "kyuujuu", "Quatre-vingt-dix", hasSpecialReading = true, noteFr = "Toujours 'kyuu-juu' (jamais 'kujuu').", category = NumberCategory.TENS_11_99),
        JapaneseNumberItem("99", "九十九", "きゅうじゅうきゅう", "kyuujuukyuu", "Quatre-vingt-dix-neuf", category = NumberCategory.TENS_11_99)
    )

    val hundredsAndThousands: List<JapaneseNumberItem> = listOf(
        JapaneseNumberItem("100", "百", "ひゃく", "hyaku", "Cent", category = NumberCategory.HUNDREDS_THOUSANDS),
        JapaneseNumberItem("200", "二百", "にひゃく", "nihyaku", "Deux cents", category = NumberCategory.HUNDREDS_THOUSANDS),
        JapaneseNumberItem(
            arabic = "300",
            kanji = "三百",
            kana = "さんびゃく",
            romaji = "sanbyaku",
            french = "Trois cents",
            hasSpecialReading = true,
            noteFr = "⚠️ IRRÉGULIER : Altération phonétique h -> b (san-BYAKU).",
            category = NumberCategory.HUNDREDS_THOUSANDS
        ),
        JapaneseNumberItem("400", "四百", "よんひゃく", "yonhyaku", "Quatre cents", category = NumberCategory.HUNDREDS_THOUSANDS),
        JapaneseNumberItem("500", "五百", "ごひゃく", "gohyaku", "Cinq cents", category = NumberCategory.HUNDREDS_THOUSANDS),
        JapaneseNumberItem(
            arabic = "600",
            kanji = "六百",
            kana = "ろっぴゃく",
            romaji = "roppyaku",
            french = "Six cents",
            hasSpecialReading = true,
            noteFr = "⚠️ IRRÉGULIER : Assimilation consonne double (ROP-PYAKU).",
            category = NumberCategory.HUNDREDS_THOUSANDS
        ),
        JapaneseNumberItem("700", "七百", "ななひゃく", "nanahyaku", "Sept cents", category = NumberCategory.HUNDREDS_THOUSANDS),
        JapaneseNumberItem(
            arabic = "800",
            kanji = "八百",
            kana = "はっぴゃく",
            romaji = "happyaku",
            french = "Huit cents",
            hasSpecialReading = true,
            noteFr = "⚠️ IRRÉGULIER : Assimilation consonne double (HAP-PYAKU).",
            category = NumberCategory.HUNDREDS_THOUSANDS
        ),
        JapaneseNumberItem("900", "九百", "きゅうひゃく", "kyuuhyaku", "Neuf cents", category = NumberCategory.HUNDREDS_THOUSANDS),
        JapaneseNumberItem("1 000", "千", "せん", "sen", "Mille", category = NumberCategory.HUNDREDS_THOUSANDS),
        JapaneseNumberItem("2 000", "二千", "にせん", "nisen", "Deux mille", category = NumberCategory.HUNDREDS_THOUSANDS),
        JapaneseNumberItem(
            arabic = "3 000",
            kanji = "三千",
            kana = "さんぜん",
            romaji = "sanzen",
            french = "Trois mille",
            hasSpecialReading = true,
            noteFr = "⚠️ IRRÉGULIER : Voirement s -> z (san-ZEN).",
            category = NumberCategory.HUNDREDS_THOUSANDS
        ),
        JapaneseNumberItem("4 000", "四千", "よんせん", "yonsen", "Quatre mille", category = NumberCategory.HUNDREDS_THOUSANDS),
        JapaneseNumberItem("5 000", "五千", "ごせん", "gosen", "Cinq mille", category = NumberCategory.HUNDREDS_THOUSANDS),
        JapaneseNumberItem("6 000", "六千", "ろくせん", "rokusen", "Six mille", category = NumberCategory.HUNDREDS_THOUSANDS),
        JapaneseNumberItem("7 000", "七千", "ななせん", "nanasen", "Sept mille", category = NumberCategory.HUNDREDS_THOUSANDS),
        JapaneseNumberItem(
            arabic = "8 000",
            kanji = "八千",
            kana = "はっせん",
            romaji = "hassen",
            french = "Huit mille",
            hasSpecialReading = true,
            noteFr = "⚠️ IRRÉGULIER : Assimilation consonne double (HAS-SEN).",
            category = NumberCategory.HUNDREDS_THOUSANDS
        ),
        JapaneseNumberItem("9 000", "九千", "きゅうせん", "kyuusen", "Neuf mille", category = NumberCategory.HUNDREDS_THOUSANDS)
    )

    val largeNumbers: List<JapaneseNumberItem> = listOf(
        JapaneseNumberItem(
            arabic = "10 000",
            kanji = "一万",
            kana = "いちまん",
            romaji = "ichiman",
            french = "Dix mille",
            hasSpecialReading = true,
            noteFr = "💡 Le système japonais compte par tranches de 4 zéros (万 = 10 000) et non de 3 zéros comme en français !",
            category = NumberCategory.LARGE_NUMBERS
        ),
        JapaneseNumberItem("50 000", "五万", "ごまん", "goman", "Cinquante mille", category = NumberCategory.LARGE_NUMBERS),
        JapaneseNumberItem("100 000", "十万", "じゅうまん", "juuman", "Cent mille", category = NumberCategory.LARGE_NUMBERS),
        JapaneseNumberItem("1 000 000", "百万", "ひゃくまん", "hyakuman", "Un million (100 fois 10 000)", category = NumberCategory.LARGE_NUMBERS),
        JapaneseNumberItem("10 000 000", "一千万", "いっせんまん", "issenman", "Dix millions", category = NumberCategory.LARGE_NUMBERS),
        JapaneseNumberItem("100 000 000", "一億", "いちおく", "ichioku", "Cent millions (tranche 億 oku)", category = NumberCategory.LARGE_NUMBERS)
    )

    // Compteurs généraux d'objets (Minna no Nihongo Leçon 11)
    val countersTsu: List<JapaneseNumberItem> = listOf(
        JapaneseNumberItem("1 objet", "一つ", "ひとつ", "hitotsu", "Une chose / 1 objet", hasSpecialReading = true, noteFr = "Compteur indigène universel japonais en 〜つ", category = NumberCategory.COUNTERS_TSU),
        JapaneseNumberItem("2 objets", "二つ", "ふたつ", "futatsu", "Deux choses / 2 objets", hasSpecialReading = true, category = NumberCategory.COUNTERS_TSU),
        JapaneseNumberItem("3 objets", "三つ", "みっつ", "mittsu", "Trois choses / 3 objets", hasSpecialReading = true, category = NumberCategory.COUNTERS_TSU),
        JapaneseNumberItem("4 objets", "四つ", "よっつ", "yottsu", "Quatre choses / 4 objets", hasSpecialReading = true, category = NumberCategory.COUNTERS_TSU),
        JapaneseNumberItem("5 objets", "五つ", "いつつ", "itsutsu", "Cinq choses / 5 objets", hasSpecialReading = true, category = NumberCategory.COUNTERS_TSU),
        JapaneseNumberItem("6 objets", "六つ", "むっつ", "muttsu", "Six choses / 6 objets", hasSpecialReading = true, category = NumberCategory.COUNTERS_TSU),
        JapaneseNumberItem("7 objets", "七つ", "ななつ", "nanatsu", "Sept choses / 7 objets", hasSpecialReading = true, category = NumberCategory.COUNTERS_TSU),
        JapaneseNumberItem("8 objets", "八つ", "やっつ", "yattsu", "Huit choses / 8 objets", hasSpecialReading = true, category = NumberCategory.COUNTERS_TSU),
        JapaneseNumberItem("9 objets", "九つ", "ここのつ", "kokonotsu", "Neuf choses / 9 objets", hasSpecialReading = true, category = NumberCategory.COUNTERS_TSU),
        JapaneseNumberItem("10 objets", "十", "とお", "too", "Dix choses / 10 objets", hasSpecialReading = true, noteFr = "⚠️ Pas de 〜つ pour dix : c'est simplement 'とお' (too) !", category = NumberCategory.COUNTERS_TSU),
        JapaneseNumberItem("Combien ?", "幾つ", "いくつ", "ikutsu", "Combien de choses / d'objets ?", hasSpecialReading = true, noteFr = "Question pour demander une quantité générale.", category = NumberCategory.COUNTERS_TSU)
    )

    // ==========================================
    // 2. LES HEURES ET LES MINUTES (時 & 分)
    // ==========================================

    val hoursList: List<JapaneseTimeItem> = listOf(
        JapaneseTimeItem(
            display = "1:00",
            kanji = "一時",
            kana = "いちじ",
            romaji = "ichiji",
            french = "1 heure",
            type = TimeItemType.HOUR
        ),
        JapaneseTimeItem(
            display = "2:00",
            kanji = "二時",
            kana = "にじ",
            romaji = "niji",
            french = "2 heures",
            type = TimeItemType.HOUR
        ),
        JapaneseTimeItem(
            display = "3:00",
            kanji = "三時",
            kana = "さんじ",
            romaji = "sanji",
            french = "3 heures",
            type = TimeItemType.HOUR
        ),
        JapaneseTimeItem(
            display = "4:00",
            kanji = "四時",
            kana = "よじ",
            romaji = "yoji",
            french = "4 heures",
            isIrregular = true,
            particularityNote = "⚠️ PIÈGE MAJEUR : Se prononce 'よじ' (yoji) et JAMAIS 'yonji' ni 'shiji' !",
            type = TimeItemType.HOUR
        ),
        JapaneseTimeItem(
            display = "5:00",
            kanji = "五時",
            kana = "ごじ",
            romaji = "goji",
            french = "5 heures",
            type = TimeItemType.HOUR
        ),
        JapaneseTimeItem(
            display = "6:00",
            kanji = "六時",
            kana = "ろくじ",
            romaji = "rokuji",
            french = "6 heures",
            type = TimeItemType.HOUR
        ),
        JapaneseTimeItem(
            display = "7:00",
            kanji = "七時",
            kana = "しちじ",
            romaji = "shichiji",
            french = "7 heures",
            isIrregular = true,
            particularityNote = "⚠️ PARTICULARITÉ : Se prononce 'しちじ' (shichiji). 'nanaji' est exceptionnel.",
            type = TimeItemType.HOUR
        ),
        JapaneseTimeItem(
            display = "8:00",
            kanji = "八時",
            kana = "はちじ",
            romaji = "hachiji",
            french = "8 heures",
            type = TimeItemType.HOUR
        ),
        JapaneseTimeItem(
            display = "9:00",
            kanji = "九時",
            kana = "くじ",
            romaji = "kuji",
            french = "9 heures",
            isIrregular = true,
            particularityNote = "⚠️ PIÈGE MAJEUR : Se prononce 'くじ' (kuji) et JAMAIS 'kyuuji' !",
            type = TimeItemType.HOUR
        ),
        JapaneseTimeItem(
            display = "10:00",
            kanji = "十時",
            kana = "じゅうじ",
            romaji = "juuji",
            french = "10 heures",
            type = TimeItemType.HOUR
        ),
        JapaneseTimeItem(
            display = "11:00",
            kanji = "十一時",
            kana = "じゅういちじ",
            romaji = "juuichiji",
            french = "11 heures",
            type = TimeItemType.HOUR
        ),
        JapaneseTimeItem(
            display = "12:00",
            kanji = "十二時",
            kana = "じゅうにじ",
            romaji = "juuniji",
            french = "12 heures (Midi / Minuit)",
            type = TimeItemType.HOUR
        ),
        JapaneseTimeItem(
            display = "Quelle heure ?",
            kanji = "何時",
            kana = "なんじ",
            romaji = "nanji",
            french = "Quelle heure ?",
            isIrregular = true,
            particularityNote = "Question officielle pour demander l'heure dans Minna no Nihongo.",
            type = TimeItemType.HOUR
        ),
        JapaneseTimeItem(
            display = "…h et demie",
            kanji = "〜時半",
            kana = "〜じはん",
            romaji = "...ji han",
            french = "… heures et demie",
            isIrregular = true,
            particularityNote = "Exemple : 九時半 (くじはん - kujihan) = 9h30.",
            type = TimeItemType.HOUR
        )
    )

    val minutesList: List<JapaneseTimeItem> = listOf(
        JapaneseTimeItem(
            display = "1 min",
            kanji = "一分",
            kana = "いっぷん",
            romaji = "ippun",
            french = "1 minute",
            isIrregular = true,
            particularityNote = "⚠️ IRRÉGULIER : Se termine en 'ぷん' (pun) avec 'っ' (ip-pun).",
            type = TimeItemType.MINUTE
        ),
        JapaneseTimeItem(
            display = "2 min",
            kanji = "二分",
            kana = "にふん",
            romaji = "nifun",
            french = "2 minutes",
            type = TimeItemType.MINUTE
        ),
        JapaneseTimeItem(
            display = "3 min",
            kanji = "三分",
            kana = "さんぷん",
            romaji = "sanpun",
            french = "3 minutes",
            isIrregular = true,
            particularityNote = "⚠️ IRRÉGULIER : Se termine en 'ぷん' (pun) : san-pun.",
            type = TimeItemType.MINUTE
        ),
        JapaneseTimeItem(
            display = "4 min",
            kanji = "四分",
            kana = "よんぷん",
            romaji = "yonpun",
            french = "4 minutes",
            isIrregular = true,
            particularityNote = "⚠️ IRRÉGULIER : Se termine en 'ぷん' (pun) : yon-pun.",
            type = TimeItemType.MINUTE
        ),
        JapaneseTimeItem(
            display = "5 min",
            kanji = "五分",
            kana = "ごふん",
            romaji = "gofun",
            french = "5 minutes",
            type = TimeItemType.MINUTE
        ),
        JapaneseTimeItem(
            display = "6 min",
            kanji = "六分",
            kana = "ろっぷん",
            romaji = "roppun",
            french = "6 minutes",
            isIrregular = true,
            particularityNote = "⚠️ IRRÉGULIER : Se termine en 'ぷん' (pun) avec 'っ' : rop-pun.",
            type = TimeItemType.MINUTE
        ),
        JapaneseTimeItem(
            display = "7 min",
            kanji = "七分",
            kana = "ななふん",
            romaji = "nanafun",
            french = "7 minutes",
            type = TimeItemType.MINUTE
        ),
        JapaneseTimeItem(
            display = "8 min",
            kanji = "八分",
            kana = "はっぷん",
            romaji = "happun",
            french = "8 minutes",
            isIrregular = true,
            particularityNote = "⚠️ IRRÉGULIER : Se termine en 'ぷん' (pun) avec 'っ' : hap-pun (parfois 'hachifun').",
            type = TimeItemType.MINUTE
        ),
        JapaneseTimeItem(
            display = "9 min",
            kanji = "九分",
            kana = "きゅうふん",
            romaji = "kyuufun",
            french = "9 minutes",
            type = TimeItemType.MINUTE
        ),
        JapaneseTimeItem(
            display = "10 min",
            kanji = "十分",
            kana = "じゅっぷん / じっぷん",
            romaji = "juppun / jippun",
            french = "10 minutes",
            isIrregular = true,
            particularityNote = "⚠️ IRRÉGULIER : Se termine en 'ぷん' (pun) avec 'っ' : jup-pun.",
            type = TimeItemType.MINUTE
        ),
        JapaneseTimeItem(
            display = "15 min",
            kanji = "十五分",
            kana = "じゅうごふん",
            romaji = "juugofun",
            french = "15 minutes",
            type = TimeItemType.MINUTE
        ),
        JapaneseTimeItem(
            display = "20 min",
            kanji = "二十分",
            kana = "にじゅっぷん",
            romaji = "nijuppun",
            french = "20 minutes",
            isIrregular = true,
            particularityNote = "⚠️ Terminaison en 'じゅっぷん' (juppun).",
            type = TimeItemType.MINUTE
        ),
        JapaneseTimeItem(
            display = "30 min",
            kanji = "三十分 / 半",
            kana = "さんじゅっぷん / はん",
            romaji = "sanjuppun / han",
            french = "30 minutes (ou demie)",
            isIrregular = true,
            particularityNote = "⚠️ On dit 'さんじゅっぷん' ou simplement 'はん' (han).",
            type = TimeItemType.MINUTE
        ),
        JapaneseTimeItem(
            display = "45 min",
            kanji = "四十五分",
            kana = "よんじゅうごふん",
            romaji = "yonjuugofun",
            french = "45 minutes",
            type = TimeItemType.MINUTE
        ),
        JapaneseTimeItem(
            display = "Combien de min ?",
            kanji = "何分",
            kana = "なんぷん",
            romaji = "nanpun",
            french = "Combien de minutes ?",
            isIrregular = true,
            particularityNote = "⚠️ La question prend 'ぷん' (nan-PUN).",
            type = TimeItemType.MINUTE
        )
    )

    val timeExpressions: List<JapaneseTimeItem> = listOf(
        JapaneseTimeItem("AM / Matin", "午前", "ごぜん", "gozen", "Matin / Matinée (AM)", type = TimeItemType.EXPRESSION),
        JapaneseTimeItem("PM / Après-midi", "午後", "ごご", "gogo", "Après-midi / Soir (PM)", type = TimeItemType.EXPRESSION),
        JapaneseTimeItem("Maintenant", "今", "いま", "ima", "Maintenant", type = TimeItemType.EXPRESSION),
        JapaneseTimeItem("Midi", "昼", "ひる", "hiru", "Midi / Milieu de journée", type = TimeItemType.EXPRESSION),
        JapaneseTimeItem("Soir", "晩 / 夜", "ばん / よる", "ban / yoru", "Soir / Nuit", type = TimeItemType.EXPRESSION),
        JapaneseTimeItem("De … à …", "〜から 〜まで", "〜から 〜まで", "...kara ...made", "De (heure) à (heure)", isIrregular = true, particularityNote = "Exemple : 九時から五時まで (kuji kara goji made) = De 9h à 17h.", type = TimeItemType.EXPRESSION)
    )
}
