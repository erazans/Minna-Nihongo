package com.example.data.datasource

import com.example.data.model.ConjugatedForm
import com.example.data.model.VerbConjugation
import com.example.data.model.VerbGroup
import com.example.data.model.WordCategory

object MinnaVerbData {

    val allVerbs: List<VerbConjugation> by lazy {
        MinnaVocabularyData.allVocabulary
            .filter { it.category == WordCategory.VERB_G1 || it.category == WordCategory.VERB_G2 || it.category == WordCategory.VERB_G3 }
            .map { word ->
                buildConjugation(
                    id = word.id,
                    lessonId = word.lessonId,
                    kanji = word.kanji,
                    kana = word.kana,
                    romaji = word.romaji,
                    french = word.french,
                    category = word.category,
                    exampleJp = word.exampleJp,
                    exampleRomaji = word.exampleRomaji,
                    exampleFr = word.exampleFr
                )
            }
    }

    private fun buildConjugation(
        id: String,
        lessonId: Int,
        kanji: String,
        kana: String,
        romaji: String,
        french: String,
        category: WordCategory,
        exampleJp: String,
        exampleRomaji: String,
        exampleFr: String
    ): VerbConjugation {
        val group = when (category) {
            WordCategory.VERB_G1 -> VerbGroup.GROUP_1
            WordCategory.VERB_G2 -> VerbGroup.GROUP_2
            WordCategory.VERB_G3 -> VerbGroup.GROUP_3
            else -> VerbGroup.GROUP_1
        }

        val kStem = if (kanji.endsWith("ます")) kanji.dropLast(2) else kanji
        val hStem = if (kana.endsWith("ます")) kana.dropLast(2) else kana
        val rStem = if (romaji.endsWith("masu")) romaji.dropLast(4) else romaji

        // Masu forms
        val masuNeg = ConjugatedForm("Forme Négative Polie (~ません)", "${kStem}ません", "${hStem}ません", "${rStem}masen", "Présent / futur négatif poli")
        val masuPast = ConjugatedForm("Forme Passée Polie (~ました)", "${kStem}ました", "${hStem}ました", "${rStem}mashita", "Passé affirmatif poli")
        val masuPastNeg = ConjugatedForm("Forme Passée Négative (~ませんでした)", "${kStem}ませんでした", "${hStem}ませんでした", "${rStem}masendeshita", "Passé négatif poli")

        val dictK: String
        val dictH: String
        val dictR: String

        val teK: String
        val teH: String
        val teR: String

        val taK: String
        val taH: String
        val taR: String

        val naiK: String
        val naiH: String
        val naiR: String

        val potK: String
        val potH: String
        val potR: String

        val volK: String
        val volH: String
        val volR: String

        val condK: String
        val condH: String
        val condR: String

        val impK: String
        val impH: String
        val impR: String

        val teExplanation: String

        if (group == VerbGroup.GROUP_3) {
            if (kanji == "来ます" || kana == "きます") {
                dictK = "来る"; dictH = "くる"; dictR = "kuru"
                teK = "来て"; teH = "きて"; teR = "kite"
                taK = "来た"; taH = "きた"; taR = "kita"
                naiK = "来ない"; naiH = "こない"; naiR = "konai"
                potK = "来られる"; potH = "こられる"; potR = "korareru"
                volK = "来よう"; volH = "こよう"; volR = "koyou"
                condK = "来れば"; condH = "くれば"; condR = "kureba"
                impK = "来い"; impH = "こい"; impR = "koi"
                teExplanation = "Groupe 3 (Irrégulier) : 来ます (き) → 来て (きて) / 来ない (こない)."
            } else if (kanji.endsWith("持って来ます") || kana.endsWith("もってきます")) {
                val prefixK = kanji.dropLast(3)
                val prefixH = kana.dropLast(3)
                dictK = "${prefixK}来る"; dictH = "${prefixH}くる"; dictR = "motte kuru"
                teK = "${prefixK}来て"; teH = "${prefixH}きて"; teR = "motte kite"
                taK = "${prefixK}来た"; taH = "${prefixH}きた"; taR = "motte kita"
                naiK = "${prefixK}来ない"; naiH = "${prefixH}こない"; naiR = "motte konai"
                potK = "${prefixK}来られる"; potH = "${prefixH}こられる"; potR = "motte korareru"
                volK = "${prefixK}来よう"; volH = "${prefixH}こよう"; volR = "motte koyou"
                condK = "${prefixK}来れば"; condH = "${prefixH}くれば"; condR = "motte kureba"
                impK = "${prefixK}来い"; impH = "${prefixH}こい"; impR = "motte koi"
                teExplanation = "Groupe 3 (Irrégulier) : 持って来ます → 持って来て (motte kite)."
            } else if (kanji.endsWith("連れて来ます") || kana.endsWith("つれてきます")) {
                val prefixK = kanji.dropLast(3)
                val prefixH = kana.dropLast(3)
                dictK = "${prefixK}来る"; dictH = "${prefixH}くる"; dictR = "tsurete kuru"
                teK = "${prefixK}来て"; teH = "${prefixH}きて"; teR = "tsurete kite"
                taK = "${prefixK}来た"; taH = "${prefixH}きた"; taR = "tsurete kita"
                naiK = "${prefixK}来ない"; naiH = "${prefixH}こない"; naiR = "tsurete konai"
                potK = "${prefixK}来られる"; potH = "${prefixH}こられる"; potR = "tsurete korareru"
                volK = "${prefixK}来よう"; volH = "${prefixH}こよう"; volR = "tsurete koyou"
                condK = "${prefixK}来れば"; condH = "${prefixH}くれば"; condR = "tsurete kureba"
                impK = "${prefixK}来い"; impH = "${prefixH}こい"; impR = "tsurete koi"
                teExplanation = "Groupe 3 (Irrégulier) : 連れて来ます → 連れて来て (tsurete kite)."
            } else {
                // Verbes en 〜します / 〜する
                val kBase = if (kStem.endsWith("し")) kStem.dropLast(1) else kStem
                val hBase = if (hStem.endsWith("し")) hStem.dropLast(1) else hStem
                val rBase = if (rStem.endsWith("shi")) rStem.dropLast(3) else rStem
                dictK = "${kBase}する"; dictH = "${hBase}する"; dictR = "${rBase}suru"
                teK = "${kBase}して"; teH = "${hBase}して"; teR = "${rBase}shite"
                taK = "${kBase}した"; taH = "${hBase}した"; taR = "${rBase}shita"
                naiK = "${kBase}しない"; naiH = "${hBase}しない"; naiR = "${rBase}shinai"
                potK = "${kBase}できる"; potH = "${hBase}できる"; potR = "${rBase}dekiru"
                volK = "${kBase}しよう"; volH = "${hBase}しよう"; volR = "${rBase}shiyou"
                condK = "${kBase}すれば"; condH = "${hBase}すれば"; condR = "${rBase}sureba"
                impK = "${kBase}しろ"; impH = "${hBase}しろ"; impR = "${rBase}shiro"
                teExplanation = "Groupe 3 (Irrégulier en ~する) : ~します → ~して / ~した / ~しない."
            }
        } else if (group == VerbGroup.GROUP_2) {
            // Groupe 2 : Ichidan -> retirer ます, ajouter る, て, た, ない, られる, よう, れば, ろ
            dictK = "${kStem}る"; dictH = "${hStem}る"; dictR = "${rStem}ru"
            teK = "${kStem}て"; teH = "${hStem}て"; teR = "${rStem}te"
            taK = "${kStem}た"; taH = "${hStem}た"; taR = "${rStem}ta"
            naiK = "${kStem}ない"; naiH = "${hStem}ない"; naiR = "${rStem}nai"
            potK = "${kStem}られる"; potH = "${hStem}られる"; potR = "${rStem}rareru"
            volK = "${kStem}よう"; volH = "${hStem}よう"; volR = "${rStem}you"
            condK = "${kStem}れば"; condH = "${hStem}れば"; condR = "${rStem}reba"
            impK = "${kStem}ろ"; impH = "${hStem}ろ"; impR = "${rStem}ro"
            teExplanation = "Groupe 2 (一段 - Ichidan) : on remplace simplement ~ます par ~て (ou ~た / ~ない)."
        } else {
            // Groupe 1 : Godan
            if (kanji == "行きます" || kana == "いきます") {
                dictK = "行く"; dictH = "いく"; dictR = "iku"
                teK = "行って"; teH = "いって"; teR = "itte"
                taK = "行った"; taH = "いった"; taR = "itta"
                naiK = "行かない"; naiH = "いかない"; naiR = "ikanai"
                potK = "行ける"; potH = "いける"; potR = "ikeru"
                volK = "行こう"; volH = "いこう"; volR = "ikou"
                condK = "行けば"; condH = "いけば"; condR = "ikeba"
                impK = "行け"; impH = "いけ"; impR = "ike"
                teExplanation = "Exception célèbre G1 : 行きます (き) devient 行って (いって) au lieu de いて !"
            } else if (kanji.endsWith("連れて行きます") || kana.endsWith("つれていきます")) {
                val pK = kanji.dropLast(4)
                val pH = kana.dropLast(4)
                dictK = "${pK}行く"; dictH = "${pH}いく"; dictR = "tsurete iku"
                teK = "${pK}行って"; teH = "${pH}いって"; teR = "tsurete itte"
                taK = "${pK}行った"; taH = "${pH}いった"; taR = "tsurete itta"
                naiK = "${pK}行かない"; naiH = "${pH}いかない"; naiR = "tsurete ikanai"
                potK = "${pK}行ける"; potH = "${pH}いける"; potR = "tsurete ikeru"
                volK = "${pK}行こう"; volH = "${pH}いこう"; volR = "tsurete ikou"
                condK = "${pK}行けば"; condH = "${pH}いけば"; condR = "tsurete ikeba"
                impK = "${pK}行け"; impH = "${pH}いけ"; impR = "tsurete ike"
                teExplanation = "Exception G1 : 行きます devient 行って (いって)."
            } else if (kanji.endsWith("持って行きます") || kana.endsWith("もっていきます")) {
                val pK = kanji.dropLast(4)
                val pH = kana.dropLast(4)
                dictK = "${pK}行く"; dictH = "${pH}いく"; dictR = "motte iku"
                teK = "${pK}行って"; teH = "${pH}いって"; teR = "motte itte"
                taK = "${pK}行った"; taH = "${pH}いった"; taR = "motte itta"
                naiK = "${pK}行かない"; naiH = "${pH}いかない"; naiR = "motte ikanai"
                potK = "${pK}行ける"; potH = "${pH}いける"; potR = "motte ikeru"
                volK = "${pK}行こう"; volH = "${pH}いこう"; volR = "motte ikou"
                condK = "${pK}行けば"; condH = "${pH}いけば"; condR = "motte ikeba"
                impK = "${pK}行け"; impH = "${pH}いけ"; impR = "motte ike"
                teExplanation = "Exception G1 : 行きます devient 行って (いって)."
            } else if (kanji == "あります" || kana == "あります") {
                dictK = "ある"; dictH = "ある"; dictR = "aru"
                teK = "あって"; teH = "あって"; teR = "atte"
                taK = "あった"; taH = "あった"; taR = "atta"
                naiK = "ない"; naiH = "ない"; naiR = "nai"
                potK = "あり得る"; potH = "ありえる"; potR = "arieru"
                volK = "あろう"; volH = "あろう"; volR = "arou"
                condK = "あれば"; condH = "あれば"; condR = "areba"
                impK = "あれ"; impH = "あれ"; impR = "are"
                teExplanation = "Groupe 1 (り → って) : あります → あって. Piège : sa forme négative neutre est ない."
            } else {
                val lastH = hStem.lastOrNull()?.toString() ?: ""
                val kRoot = if (kStem.isNotEmpty()) kStem.dropLast(1) else ""
                val hRoot = if (hStem.isNotEmpty()) hStem.dropLast(1) else ""

                when (lastH) {
                    "い" -> {
                        val rRoot = rStem.dropLast(1)
                        dictK = "${kRoot}う"; dictH = "${hRoot}う"; dictR = "${rRoot}u"
                        teK = "${kRoot}って"; teH = "${hRoot}って"; teR = "${rRoot}tte"
                        taK = "${kRoot}った"; taH = "${hRoot}った"; taR = "${rRoot}tta"
                        naiK = "${kRoot}わない"; naiH = "${hRoot}わない"; naiR = "${rRoot}wanai"
                        potK = "${kRoot}える"; potH = "${hRoot}える"; potR = "${rRoot}eru"
                        volK = "${kRoot}おう"; volH = "${hRoot}おう"; volR = "${rRoot}ou"
                        condK = "${kRoot}えば"; condH = "${hRoot}えば"; condR = "${rRoot}eba"
                        impK = "${kRoot}え"; impH = "${hRoot}え"; impR = "${rRoot}e"
                        teExplanation = "Règle G1 : terminaison en い / ち / り → って (assimilation petit tsu)."
                    }
                    "ち" -> {
                        val rRoot = if (rStem.endsWith("chi")) rStem.dropLast(3) else rStem.dropLast(1)
                        dictK = "${kRoot}つ"; dictH = "${hRoot}つ"; dictR = "${rRoot}tsu"
                        teK = "${kRoot}って"; teH = "${hRoot}って"; teR = "${rRoot}tte"
                        taK = "${kRoot}った"; taH = "${hRoot}った"; taR = "${rRoot}tta"
                        naiK = "${kRoot}たない"; naiH = "${hRoot}たない"; naiR = "${rRoot}tanai"
                        potK = "${kRoot}てる"; potH = "${hRoot}てる"; potR = "${rRoot}teru"
                        volK = "${kRoot}とう"; volH = "${hRoot}とう"; volR = "${rRoot}tou"
                        condK = "${kRoot}てば"; condH = "${hRoot}てば"; condR = "${rRoot}teba"
                        impK = "${kRoot}て"; impH = "${hRoot}て"; impR = "${rRoot}te"
                        teExplanation = "Règle G1 : terminaison en い / ち / り → って (assimilation petit tsu)."
                    }
                    "り" -> {
                        val rRoot = if (rStem.endsWith("ri")) rStem.dropLast(2) else rStem.dropLast(1)
                        dictK = "${kRoot}る"; dictH = "${hRoot}る"; dictR = "${rRoot}ru"
                        teK = "${kRoot}って"; teH = "${hRoot}って"; teR = "${rRoot}tte"
                        taK = "${kRoot}った"; taH = "${hRoot}った"; taR = "${rRoot}tta"
                        naiK = "${kRoot}らない"; naiH = "${hRoot}らない"; naiR = "${rRoot}ranai"
                        potK = "${kRoot}れる"; potH = "${hRoot}れる"; potR = "${rRoot}reru"
                        volK = "${kRoot}ろう"; volH = "${hRoot}ろう"; volR = "${rRoot}rou"
                        condK = "${kRoot}れば"; condH = "${hRoot}れば"; condR = "${rRoot}reba"
                        impK = "${kRoot}れ"; impH = "${hRoot}れ"; impR = "${rRoot}re"
                        teExplanation = "Règle G1 : terminaison en い / ち / り → って (assimilation petit tsu)."
                    }
                    "み" -> {
                        val rRoot = if (rStem.endsWith("mi")) rStem.dropLast(2) else rStem.dropLast(1)
                        dictK = "${kRoot}む"; dictH = "${hRoot}む"; dictR = "${rRoot}mu"
                        teK = "${kRoot}んで"; teH = "${hRoot}んで"; teR = "${rRoot}nde"
                        taK = "${kRoot}んだ"; taH = "${hRoot}んだ"; taR = "${rRoot}nda"
                        naiK = "${kRoot}まない"; naiH = "${hRoot}まない"; naiR = "${rRoot}manai"
                        potK = "${kRoot}める"; potH = "${hRoot}める"; potR = "${rRoot}meru"
                        volK = "${kRoot}もう"; volH = "${hRoot}もう"; volR = "${rRoot}mou"
                        condK = "${kRoot}めば"; condH = "${hRoot}めば"; condR = "${rRoot}meba"
                        impK = "${kRoot}め"; impH = "${hRoot}め"; impR = "${rRoot}me"
                        teExplanation = "Règle G1 : terminaison en み / び / に → んで (forme sonore avec -de)."
                    }
                    "び" -> {
                        val rRoot = if (rStem.endsWith("bi")) rStem.dropLast(2) else rStem.dropLast(1)
                        dictK = "${kRoot}ぶ"; dictH = "${hRoot}ぶ"; dictR = "${rRoot}bu"
                        teK = "${kRoot}んで"; teH = "${hRoot}んで"; teR = "${rRoot}nde"
                        taK = "${kRoot}んだ"; taH = "${hRoot}んだ"; taR = "${rRoot}nda"
                        naiK = "${kRoot}ばない"; naiH = "${hRoot}ばない"; naiR = "${rRoot}banai"
                        potK = "${kRoot}べる"; potH = "${hRoot}べる"; potR = "${rRoot}beru"
                        volK = "${kRoot}ぼう"; volH = "${hRoot}ぼう"; volR = "${rRoot}bou"
                        condK = "${kRoot}べば"; condH = "${hRoot}べば"; condR = "${rRoot}beba"
                        impK = "${kRoot}べ"; impH = "${hRoot}べ"; impR = "${rRoot}be"
                        teExplanation = "Règle G1 : terminaison en み / び / に → んで (forme sonore avec -de)."
                    }
                    "に" -> {
                        val rRoot = if (rStem.endsWith("ni")) rStem.dropLast(2) else rStem.dropLast(1)
                        dictK = "${kRoot}ぬ"; dictH = "${hRoot}ぬ"; dictR = "${rRoot}nu"
                        teK = "${kRoot}んで"; teH = "${hRoot}んで"; teR = "${rRoot}nde"
                        taK = "${kRoot}んだ"; taH = "${hRoot}んだ"; taR = "${rRoot}nda"
                        naiK = "${kRoot}なない"; naiH = "${hRoot}なない"; naiR = "${rRoot}nanai"
                        potK = "${kRoot}ねる"; potH = "${hRoot}ねる"; potR = "${rRoot}neru"
                        volK = "${kRoot}のう"; volH = "${hRoot}のう"; volR = "${rRoot}nou"
                        condK = "${kRoot}ねば"; condH = "${hRoot}ねば"; condR = "${rRoot}neba"
                        impK = "${kRoot}ね"; impH = "${hRoot}ね"; impR = "${rRoot}ne"
                        teExplanation = "Règle G1 : terminaison en み / び / に → んで (forme sonore avec -de)."
                    }
                    "き" -> {
                        val rRoot = if (rStem.endsWith("ki")) rStem.dropLast(2) else rStem.dropLast(1)
                        dictK = "${kRoot}く"; dictH = "${hRoot}く"; dictR = "${rRoot}ku"
                        teK = "${kRoot}いて"; teH = "${hRoot}いて"; teR = "${rRoot}ite"
                        taK = "${kRoot}いた"; taH = "${hRoot}いた"; taR = "${rRoot}ita"
                        naiK = "${kRoot}かない"; naiH = "${hRoot}かない"; naiR = "${rRoot}kanai"
                        potK = "${kRoot}ける"; potH = "${hRoot}ける"; potR = "${rRoot}keru"
                        volK = "${kRoot}こう"; volH = "${hRoot}こう"; volR = "${rRoot}kou"
                        condK = "${kRoot}けば"; condH = "${hRoot}けば"; condR = "${rRoot}keba"
                        impK = "${kRoot}け"; impH = "${hRoot}け"; impR = "${rRoot}ke"
                        teExplanation = "Règle G1 : terminaison en き → いて (ite)."
                    }
                    "ぎ" -> {
                        val rRoot = if (rStem.endsWith("gi")) rStem.dropLast(2) else rStem.dropLast(1)
                        dictK = "${kRoot}ぐ"; dictH = "${hRoot}ぐ"; dictR = "${rRoot}gu"
                        teK = "${kRoot}いで"; teH = "${hRoot}いで"; teR = "${rRoot}ide"
                        taK = "${kRoot}いだ"; taH = "${hRoot}いだ"; taR = "${rRoot}ida"
                        naiK = "${kRoot}がない"; naiH = "${hRoot}がない"; naiR = "${rRoot}ganai"
                        potK = "${kRoot}げる"; potH = "${hRoot}げる"; potR = "${rRoot}geru"
                        volK = "${kRoot}ごう"; volH = "${hRoot}ごう"; volR = "${rRoot}gou"
                        condK = "${kRoot}げば"; condH = "${hRoot}げば"; condR = "${rRoot}geba"
                        impK = "${kRoot}げ"; impH = "${hRoot}げ"; impR = "${rRoot}ge"
                        teExplanation = "Règle G1 : terminaison en ぎ → いで (ide sonore)."
                    }
                    "し" -> {
                        val rRoot = if (rStem.endsWith("shi")) rStem.dropLast(3) else rStem.dropLast(1)
                        dictK = "${kRoot}す"; dictH = "${hRoot}す"; dictR = "${rRoot}su"
                        teK = "${kRoot}して"; teH = "${hRoot}して"; teR = "${rRoot}shite"
                        taK = "${kRoot}した"; taH = "${hRoot}した"; taR = "${rRoot}shita"
                        naiK = "${kRoot}さない"; naiH = "${hRoot}さない"; naiR = "${rRoot}sanai"
                        potK = "${kRoot}せる"; potH = "${hRoot}せる"; potR = "${rRoot}seru"
                        volK = "${kRoot}そう"; volH = "${hRoot}そう"; volR = "${rRoot}sou"
                        condK = "${kRoot}せば"; condH = "${hRoot}せば"; condR = "${rRoot}seba"
                        impK = "${kRoot}せ"; impH = "${hRoot}せ"; impR = "${rRoot}se"
                        teExplanation = "Règle G1 : terminaison en し → して (shite)."
                    }
                    else -> {
                        dictK = kanji; dictH = kana; dictR = romaji
                        teK = kanji; teH = kana; teR = romaji
                        taK = kanji; taH = kana; taR = romaji
                        naiK = kanji; naiH = kana; naiR = romaji
                        potK = kanji; potH = kana; potR = romaji
                        volK = kanji; volH = kana; volR = romaji
                        condK = kanji; condH = kana; condR = romaji
                        impK = kanji; impH = kana; impR = romaji
                        teExplanation = "Conjugaison standard."
                    }
                }
            }
        }

        return VerbConjugation(
            id = id,
            lessonId = lessonId,
            dictionaryKanji = dictK,
            dictionaryKana = dictH,
            dictionaryRomaji = dictR,
            masuKanji = kanji,
            masuKana = kana,
            masuRomaji = romaji,
            french = french,
            group = group,
            teForm = ConjugatedForm("Forme en -TE (て形)", teK, teH, teR, "Connexion d'actions, requêtes (~てください), état en cours (~ています)"),
            taForm = ConjugatedForm("Forme Passée Neutre (た形)", taK, taH, taR, "Passé familier / neutre, expérience (~たことがあります)"),
            naiForm = ConjugatedForm("Forme Négative Neutre (ない形)", naiK, naiH, naiR, "Négatif familier / neutre, interdiction (~ないでください)"),
            potentialForm = ConjugatedForm("Forme Potentielle (可能形)", potK, potH, potR, "Capacité : pouvoir / être capable de faire"),
            volitionalForm = ConjugatedForm("Forme Volitive (意向形)", volK, volH, volR, "Suggestion / invitation amicale : Faisons ! / Allons-y !"),
            conditionalForm = ConjugatedForm("Forme Conditionnelle (ば形)", condK, condH, condR, "Condition : Si... alors..."),
            imperativeForm = ConjugatedForm("Forme Impérative (命令形)", impK, impH, impR, "Ordre direct et impératif"),
            masuNegative = masuNeg,
            masuPast = masuPast,
            masuPastNegative = masuPastNeg,
            teRuleExplanation = teExplanation,
            exampleSentenceJp = exampleJp,
            exampleSentenceRomaji = exampleRomaji,
            exampleSentenceFr = exampleFr
        )
    }
}
