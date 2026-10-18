package com.example.data.datasource

import com.example.data.model.KanjiCharacter
import com.example.data.model.KanjiExample

object MinnaKanjiData {

    val allKanji: List<KanjiCharacter> = listOf(
        // === 1. Nombres & Chiffres (Leçon 1-3) ===
        KanjiCharacter(
            kanji = "一",
            meaningFr = "Un (1)",
            onyomi = "イチ, イツ (ICHI, ITSU)",
            kunyomi = "ひと・つ (hito-tsu)",
            strokeCount = 1,
            lessonId = 1,
            categoryFr = "Nombres & Monnaie",
            mnemonicFr = "Un seul trait horizontal pour le chiffre un.",
            examples = listOf(
                KanjiExample("一つ", "ひとつ", "hitotsu", "Une chose"),
                KanjiExample("一日", "ついたち", "tsuitachi", "1er jour du mois"),
                KanjiExample("一人", "ひとり", "hitori", "Une personne (seul)")
            )
        ),
        KanjiCharacter(
            kanji = "二",
            meaningFr = "Deux (2)",
            onyomi = "ニ (NI)",
            kunyomi = "ふた・つ (futa-tsu)",
            strokeCount = 2,
            lessonId = 1,
            categoryFr = "Nombres & Monnaie",
            mnemonicFr = "Deux traits horizontaux superposés.",
            examples = listOf(
                KanjiExample("二つ", "ふたつ", "futatsu", "Deux choses"),
                KanjiExample("二日", "ふつか", "futsuka", "Le 2 du mois / 2 jours"),
                KanjiExample("二人", "ふたり", "futari", "Deux personnes")
            )
        ),
        KanjiCharacter(
            kanji = "三",
            meaningFr = "Trois (3)",
            onyomi = "サン (SAN)",
            kunyomi = "みっ・つ (mit-tsu)",
            strokeCount = 3,
            lessonId = 1,
            categoryFr = "Nombres & Monnaie",
            mnemonicFr = "Trois traits horizontaux superposés.",
            examples = listOf(
                KanjiExample("三つ", "みっつ", "mittsu", "Trois choses"),
                KanjiExample("三日", "みっか", "mikka", "Le 3 du mois"),
                KanjiExample("三人", "さんにん", "sannin", "Trois personnes")
            )
        ),
        KanjiCharacter(
            kanji = "四",
            meaningFr = "Quatre (4)",
            onyomi = "シ (SHI)",
            kunyomi = "よん, よっ・つ (yon, yot-tsu)",
            strokeCount = 5,
            lessonId = 1,
            categoryFr = "Nombres & Monnaie",
            mnemonicFr = "Une fenêtre carrée avec des rideaux ouverts.",
            examples = listOf(
                KanjiExample("四つ", "よっつ", "yottsu", "Quatre choses"),
                KanjiExample("四月", "しがつ", "shigatsu", "Avril"),
                KanjiExample("四時", "よじ", "yoji", "4 heures")
            )
        ),
        KanjiCharacter(
            kanji = "五",
            meaningFr = "Cinq (5)",
            onyomi = "ゴ (GO)",
            kunyomi = "いつ・つ (itsu-tsu)",
            strokeCount = 4,
            lessonId = 1,
            categoryFr = "Nombres & Monnaie",
            mnemonicFr = "Les 5 doigts stylisés d'une main.",
            examples = listOf(
                KanjiExample("五つ", "いつつ", "itsutsu", "Cinq choses"),
                KanjiExample("五月", "ごがつ", "gogatsu", "Mai"),
                KanjiExample("五人", "ごにん", "gonin", "Cinq personnes")
            )
        ),
        KanjiCharacter(
            kanji = "六",
            meaningFr = "Six (6)",
            onyomi = "ロク (ROKU)",
            kunyomi = "むっ・つ (mut-tsu)",
            strokeCount = 4,
            lessonId = 2,
            categoryFr = "Nombres & Monnaie",
            mnemonicFr = "Un petit bonhomme les bras écartés.",
            examples = listOf(
                KanjiExample("六つ", "むっつ", "muttsu", "Six choses"),
                KanjiExample("六月", "ろくがつ", "rokugatsu", "Juin"),
                KanjiExample("六日", "むいか", "muika", "Le 6 du mois")
            )
        ),
        KanjiCharacter(
            kanji = "七",
            meaningFr = "Sept (7)",
            onyomi = "シチ (SHICHI)",
            kunyomi = "なな, なな・つ (nana, nana-tsu)",
            strokeCount = 2,
            lessonId = 2,
            categoryFr = "Nombres & Monnaie",
            mnemonicFr = "Le chiffre 7 inversé traversé d'une ligne.",
            examples = listOf(
                KanjiExample("七つ", "ななつ", "nanatsu", "Sept choses"),
                KanjiExample("七月", "しちがつ", "shichigatsu", "Juillet"),
                KanjiExample("七時", "しちじ", "shichiji", "7 heures")
            )
        ),
        KanjiCharacter(
            kanji = "八",
            meaningFr = "Huit (8)",
            onyomi = "ハチ (HACHI)",
            kunyomi = "やっ・つ (yat-tsu)",
            strokeCount = 2,
            lessonId = 2,
            categoryFr = "Nombres & Monnaie",
            mnemonicFr = "Deux traits qui s'ouvrent vers le bas (forme du mont Fuji).",
            examples = listOf(
                KanjiExample("八つ", "やっつ", "yattsu", "Huit choses"),
                KanjiExample("八月", "はちがつ", "hachigatsu", "Août"),
                KanjiExample("八日", "ようか", "youka", "Le 8 du mois")
            )
        ),
        KanjiCharacter(
            kanji = "九",
            meaningFr = "Neuf (9)",
            onyomi = "キュウ, ク (KYUU, KU)",
            kunyomi = "ここの・つ (kokono-tsu)",
            strokeCount = 2,
            lessonId = 2,
            categoryFr = "Nombres & Monnaie",
            mnemonicFr = "Un bras musclé montrant sa force.",
            examples = listOf(
                KanjiExample("九つ", "ここのつ", "kokonotsu", "Neuf choses"),
                KanjiExample("九月", "くがつ", "kugatsu", "Septembre"),
                KanjiExample("九時", "くじ", "kuji", "9 heures")
            )
        ),
        KanjiCharacter(
            kanji = "十",
            meaningFr = "Dix (10)",
            onyomi = "ジュウ, ジッ (JUU, JIT)",
            kunyomi = "とお (too)",
            strokeCount = 2,
            lessonId = 2,
            categoryFr = "Nombres & Monnaie",
            mnemonicFr = "Une croix parfaite représentant 10.",
            examples = listOf(
                KanjiExample("十", "とお", "too", "Dix choses"),
                KanjiExample("十月", "じゅうがつ", "juugatsu", "Octobre"),
                KanjiExample("十日", "とおか", "tooka", "Le 10 du mois")
            )
        ),
        KanjiCharacter(
            kanji = "百",
            meaningFr = "Cent (100)",
            onyomi = "ヒャク (HYAKU)",
            kunyomi = "もも (momo)",
            strokeCount = 6,
            lessonId = 3,
            categoryFr = "Nombres & Monnaie",
            mnemonicFr = "Le chiffre un (一) sur le blanc (白) = 100.",
            examples = listOf(
                KanjiExample("百", "ひゃく", "hyaku", "Cent"),
                KanjiExample("三百", "さんびゃく", "sanbyaku", "Trois cents"),
                KanjiExample("六百", "ろっぴゃく", "roppyaku", "Six cents")
            )
        ),
        KanjiCharacter(
            kanji = "千",
            meaningFr = "Mille (1000)",
            onyomi = "セン (SEN)",
            kunyomi = "ち (chi)",
            strokeCount = 3,
            lessonId = 3,
            categoryFr = "Nombres & Monnaie",
            mnemonicFr = "Le kanji dix (十) avec un chapeau diagonal.",
            examples = listOf(
                KanjiExample("千", "せん", "sen", "Mille"),
                KanjiExample("三千", "さんぜん", "sanzen", "Trois mille"),
                KanjiExample("八千", "はっせん", "hassen", "Huit mille")
            )
        ),
        KanjiCharacter(
            kanji = "万",
            meaningFr = "Dix mille (10 000)",
            onyomi = "マン, バン (MAN, BAN)",
            kunyomi = "よろず (yorozu)",
            strokeCount = 3,
            lessonId = 3,
            categoryFr = "Nombres & Monnaie",
            mnemonicFr = "Une boîte spéciale contenant une grande quantité.",
            examples = listOf(
                KanjiExample("一万", "いちまん", "ichiman", "Dix mille"),
                KanjiExample("十万", "じゅうまん", "juuman", "Cent mille"),
                KanjiExample("百万", "ひゃくまん", "hyakuman", "Un million")
            )
        ),
        KanjiCharacter(
            kanji = "円",
            meaningFr = "Yen / Cercle, rond",
            onyomi = "エン (EN)",
            kunyomi = "まる・い (maru-i)",
            strokeCount = 4,
            lessonId = 3,
            categoryFr = "Nombres & Monnaie",
            mnemonicFr = "Un portail carré encadrant une pièce de monnaie.",
            examples = listOf(
                KanjiExample("百円", "ひゃくえん", "hyakuen", "100 yens"),
                KanjiExample("円高", "えんだか", "endaka", "Hausse du yen"),
                KanjiExample("円い", "まるい", "marui", "Rond, circulaire")
            )
        ),

        // === 2. Jours, Calendrier & Nature (Leçon 4-6) ===
        KanjiCharacter(
            kanji = "日",
            meaningFr = "Jour, Soleil, Japon",
            onyomi = "ニチ, ジツ (NICHI, JITSU)",
            kunyomi = "ひ, -び, -か (hi, bi, ka)",
            strokeCount = 4,
            lessonId = 4,
            categoryFr = "Temps & Calendrier",
            mnemonicFr = "Le soleil rayonnant avec une barre centrale.",
            examples = listOf(
                KanjiExample("日本", "にほん", "nihon", "Japon"),
                KanjiExample("日曜日", "にちようび", "nichiyoubi", "Dimanche"),
                KanjiExample("毎日", "まいにち", "mainichi", "Tous les jours"),
                KanjiExample("今日", "きょう", "kyou", "Aujourd'hui")
            )
        ),
        KanjiCharacter(
            kanji = "月",
            meaningFr = "Lune, Mois",
            onyomi = "ゲツ, ガツ (GETSU, GATSU)",
            kunyomi = "つき (tsuki)",
            strokeCount = 4,
            lessonId = 4,
            categoryFr = "Temps & Calendrier",
            mnemonicFr = "Un croissant de lune avec deux nuages traversants.",
            examples = listOf(
                KanjiExample("月曜日", "げつようび", "getsuyoubi", "Lundi"),
                KanjiExample("一月", "いちがつ", "ichigatsu", "Janvier"),
                KanjiExample("今月", "こんげつ", "kongetsu", "Ce mois-ci")
            )
        ),
        KanjiCharacter(
            kanji = "火",
            meaningFr = "Feu",
            onyomi = "カ (KA)",
            kunyomi = "ひ (hi)",
            strokeCount = 4,
            lessonId = 4,
            categoryFr = "Temps & Calendrier",
            mnemonicFr = "Des flammes qui dansent dans un foyer.",
            examples = listOf(
                KanjiExample("火曜日", "かようび", "kayoubi", "Mardi"),
                KanjiExample("火", "ひ", "hi", "Feu"),
                KanjiExample("花火", "はなび", "hanabi", "Feu d'artifice")
            )
        ),
        KanjiCharacter(
            kanji = "水",
            meaningFr = "Eau",
            onyomi = "スイ (SUI)",
            kunyomi = "みず (mizu)",
            strokeCount = 4,
            lessonId = 4,
            categoryFr = "Temps & Calendrier",
            mnemonicFr = "Une goutte d'eau tombant et éclaboussant sur les côtés.",
            examples = listOf(
                KanjiExample("水曜日", "すいようび", "suiyoubi", "Mercredi"),
                KanjiExample("水", "みず", "mizu", "Eau"),
                KanjiExample("お水", "おみず", "omizu", "Eau (poli)")
            )
        ),
        KanjiCharacter(
            kanji = "木",
            meaningFr = "Arbre, Bois",
            onyomi = "モク, ボク (MOKU, BOKU)",
            kunyomi = "き (ki)",
            strokeCount = 4,
            lessonId = 4,
            categoryFr = "Temps & Calendrier",
            mnemonicFr = "Un tronc d'arbre avec ses branches et ses racines.",
            examples = listOf(
                KanjiExample("木曜日", "もくようび", "mokuyoubi", "Jeudi"),
                KanjiExample("木", "き", "ki", "Arbre"),
                KanjiExample("木綿", "もめん", "momen", "Coton")
            )
        ),
        KanjiCharacter(
            kanji = "金",
            meaningFr = "Or, Métal, Argent (monnaie)",
            onyomi = "キン, コン (KIN, KON)",
            kunyomi = "かね (kane)",
            strokeCount = 8,
            lessonId = 4,
            categoryFr = "Temps & Calendrier",
            mnemonicFr = "Un toit sous lequel des pépites d'or sont stockées.",
            examples = listOf(
                KanjiExample("金曜日", "きんようび", "kinyoubi", "Vendredi"),
                KanjiExample("お金", "おかね", "okane", "Argent (monnaie)"),
                KanjiExample("金色", "きんいろ", "kin'iro", "Couleur dorée")
            )
        ),
        KanjiCharacter(
            kanji = "土",
            meaningFr = "Terre, Sol",
            onyomi = "ド, ト (DO, TO)",
            kunyomi = "つち (tsuchi)",
            strokeCount = 3,
            lessonId = 4,
            categoryFr = "Temps & Calendrier",
            mnemonicFr = "Une plante qui pousse hors de la terre.",
            examples = listOf(
                KanjiExample("土曜日", "どようび", "doyoubi", "Samedi"),
                KanjiExample("土", "つち", "tsuchi", "Terre, sol"),
                KanjiExample("土地", "とち", "tochi", "Terrain, terre")
            )
        ),
        KanjiCharacter(
            kanji = "年",
            meaningFr = "Année, Âge",
            onyomi = "ネン (NEN)",
            kunyomi = "とし (toshi)",
            strokeCount = 6,
            lessonId = 5,
            categoryFr = "Temps & Calendrier",
            mnemonicFr = "La récolte annuelle du riz.",
            examples = listOf(
                KanjiExample("今年", "ことし", "kotoshi", "Cette année"),
                KanjiExample("来年", "らいねん", "rainen", "L'année prochaine"),
                KanjiExample("去年", "きょねん", "kyonen", "L'année dernière"),
                KanjiExample("一年生", "いちねんせい", "ichinensei", "Élève de 1ère année")
            )
        ),
        KanjiCharacter(
            kanji = "時",
            meaningFr = "Heure, Temps, Moment",
            onyomi = "ジ (JI)",
            kunyomi = "とき (toki)",
            strokeCount = 10,
            lessonId = 4,
            categoryFr = "Temps & Calendrier",
            mnemonicFr = "Le soleil (日) associé au temple (寺) qui sonne les heures.",
            examples = listOf(
                KanjiExample("何時", "なんじ", "nanji", "Quelle heure ?"),
                KanjiExample("時間", "じかん", "jikan", "Temps / heures de durée"),
                KanjiExample("時々", "ときどき", "tokidoki", "De temps en temps")
            )
        ),
        KanjiCharacter(
            kanji = "分",
            meaningFr = "Minute, Partie, Diviser, Comprendre",
            onyomi = "フン, プン, ブン (FUN, PUN, BUN)",
            kunyomi = "わ・かる, わ・ける (wa-karu, wa-keru)",
            strokeCount = 4,
            lessonId = 4,
            categoryFr = "Temps & Calendrier",
            mnemonicFr = "Un couteau (刀) qui divise les minutes.",
            examples = listOf(
                KanjiExample("十分", "じゅっぷん", "juppun", "Dix minutes"),
                KanjiExample("分かります", "わかります", "wakarimasu", "Comprendre"),
                KanjiExample("五分", "ごふん", "gofun", "Cinq minutes")
            )
        ),
        KanjiCharacter(
            kanji = "今",
            meaningFr = "Maintenant, Actuel",
            onyomi = "コン, キン (KON, KIN)",
            kunyomi = "いま (ima)",
            strokeCount = 4,
            lessonId = 4,
            categoryFr = "Temps & Calendrier",
            mnemonicFr = "Un toit au-dessus de l'instant présent.",
            examples = listOf(
                KanjiExample("今", "いま", "ima", "Maintenant"),
                KanjiExample("今日", "きょう", "kyou", "Aujourd'hui"),
                KanjiExample("今月", "こんげつ", "kongetsu", "Ce mois-ci"),
                KanjiExample("今週", "こんしゅう", "konshuu", "Cette semaine")
            )
        ),
        KanjiCharacter(
            kanji = "半",
            meaningFr = "Demi, Moitié",
            onyomi = "ハン (HAN)",
            kunyomi = "なか・ば (naka-ba)",
            strokeCount = 5,
            lessonId = 4,
            categoryFr = "Temps & Calendrier",
            mnemonicFr = "Une ligne centrale qui sépare une chose en deux moitiés.",
            examples = listOf(
                KanjiExample("九時半", "くじはん", "kujihan", "9 heures et demie"),
                KanjiExample("半分", "はんぶん", "hanbun", "Moitié"),
                KanjiExample("半年", "はんとし", "hantoshi", "Six mois (demi-année)")
            )
        ),

        // === 3. Personnes, École & Relations (Leçon 1, 6-10) ===
        KanjiCharacter(
            kanji = "人",
            meaningFr = "Personne, Être humain",
            onyomi = "ジン, ニン (JIN, NIN)",
            kunyomi = "ひと (hito)",
            strokeCount = 2,
            lessonId = 1,
            categoryFr = "Personnes & Société",
            mnemonicFr = "Un être humain debout sur deux jambes.",
            examples = listOf(
                KanjiExample("日本人", "にほんじん", "nihonjin", "Japonais (nationalité)"),
                KanjiExample("フランス人", "ふらんすじん", "furansujin", "Français"),
                KanjiExample("あの人", "あのひと", "anohito", "Cette personne-là")
            )
        ),
        KanjiCharacter(
            kanji = "私",
            meaningFr = "Moi, Je",
            onyomi = "シ (SHI)",
            kunyomi = "わたし, わたくし (watashi, watakushi)",
            strokeCount = 7,
            lessonId = 1,
            categoryFr = "Personnes & Société",
            mnemonicFr = "Une plante de riz (禾) que je garde pour moi privé (ム).",
            examples = listOf(
                KanjiExample("私", "わたし", "watashi", "Moi, je"),
                KanjiExample("私たち", "わたしたち", "watashitachi", "Nous")
            )
        ),
        KanjiCharacter(
            kanji = "先",
            meaningFr = "Précédent, Avant, En tête",
            onyomi = "セン (SEN)",
            kunyomi = "さき (saki)",
            strokeCount = 6,
            lessonId = 1,
            categoryFr = "Personnes & Société",
            mnemonicFr = "Une personne qui marche devant avec de longues jambes.",
            examples = listOf(
                KanjiExample("先生", "せんせい", "sensei", "Professeur"),
                KanjiExample("先週", "せんしゅう", "senshuu", "La semaine dernière"),
                KanjiExample("先月", "せんげつ", "sengetsu", "Le mois dernier")
            )
        ),
        KanjiCharacter(
            kanji = "生",
            meaningFr = "Vie, Naître, Étudiant",
            onyomi = "セイ, ショウ (SEI, SHOU)",
            kunyomi = "い・きる, う・まれる (i-kiru, u-mareru)",
            strokeCount = 5,
            lessonId = 1,
            categoryFr = "Personnes & Société",
            mnemonicFr = "Une jeune pousse qui sort de terre = la vie.",
            examples = listOf(
                KanjiExample("学生", "がくせい", "gakusei", "Étudiant"),
                KanjiExample("先生", "せんせい", "sensei", "Professeur"),
                KanjiExample("留学生", "りゅうがくせい", "ryuugakusei", "Étudiant étranger")
            )
        ),
        KanjiCharacter(
            kanji = "学",
            meaningFr = "Étudier, Apprendre, Science",
            onyomi = "ガク (GAKU)",
            kunyomi = "まな・ぶ (mana-bu)",
            strokeCount = 8,
            lessonId = 1,
            categoryFr = "Personnes & Société",
            mnemonicFr = "Un enfant (子) sous un toit apprenant la sagesse.",
            examples = listOf(
                KanjiExample("大学", "だいがく", "daigaku", "Université"),
                KanjiExample("学校", "がっこう", "gakkou", "École"),
                KanjiExample("学生", "がくせい", "gakusei", "Étudiant")
            )
        ),
        KanjiCharacter(
            kanji = "校",
            meaningFr = "École",
            onyomi = "コウ (KOU)",
            kunyomi = "なし (nashi)",
            strokeCount = 10,
            lessonId = 1,
            categoryFr = "Personnes & Société",
            mnemonicFr = "Un bâtiment en bois (木) où les élèves se croisent (交).",
            examples = listOf(
                KanjiExample("学校", "がっこう", "gakkou", "École"),
                KanjiExample("中学校", "ちゅうがっこう", "chuugakkou", "Collège"),
                KanjiExample("高校", "こうこう", "koukou", "Lycée")
            )
        ),
        KanjiCharacter(
            kanji = "本",
            meaningFr = "Livre, Origine, Racine",
            onyomi = "ホン (HON)",
            kunyomi = "もと (moto)",
            strokeCount = 5,
            lessonId = 2,
            categoryFr = "Personnes & Société",
            mnemonicFr = "Un arbre (木) avec un trait marquant la racine/la source.",
            examples = listOf(
                KanjiExample("本", "ほん", "hon", "Livre"),
                KanjiExample("日本", "にほん", "nihon", "Japon (origine du soleil)"),
                KanjiExample("日本語", "にほんご", "nihongo", "Langue japonaise")
            )
        ),
        KanjiCharacter(
            kanji = "友",
            meaningFr = "Ami, Camarade",
            onyomi = "ユウ (YUU)",
            kunyomi = "とも (tomo)",
            strokeCount = 4,
            lessonId = 5,
            categoryFr = "Personnes & Société",
            mnemonicFr = "Deux mains qui se serrent en signe d'amitié.",
            examples = listOf(
                KanjiExample("友達", "ともだち", "tomodachi", "Ami(s)"),
                KanjiExample("友人", "ゆうじん", "yuujin", "Ami (formel)")
            )
        ),
        KanjiCharacter(
            kanji = "男",
            meaningFr = "Homme, Masculin",
            onyomi = "ダン, ナン (DAN, NAN)",
            kunyomi = "おとこ (otoko)",
            strokeCount = 7,
            lessonId = 10,
            categoryFr = "Personnes & Société",
            mnemonicFr = "La rizière (田) cultivée par la force (力) de l'homme.",
            examples = listOf(
                KanjiExample("男の人", "おとこのひと", "otokonohito", "Un homme"),
                KanjiExample("男の子", "おとこのこ", "otokonoko", "Un garçon")
            )
        ),
        KanjiCharacter(
            kanji = "女",
            meaningFr = "Femme, Féminin",
            onyomi = "ジョ, ニョ (JO, NYO)",
            kunyomi = "おんな, め (onna, me)",
            strokeCount = 3,
            lessonId = 10,
            categoryFr = "Personnes & Société",
            mnemonicFr = "Une femme élégante assise les bras croisés.",
            examples = listOf(
                KanjiExample("女の人", "おんなのひと", "onnanohito", "Une femme"),
                KanjiExample("女の子", "おんなのこ", "onnanoko", "Une fille"),
                KanjiExample("彼女", "かのじょ", "kanojo", "Elle / Petite amie")
            )
        ),
        KanjiCharacter(
            kanji = "子",
            meaningFr = "Enfant",
            onyomi = "シ, ス (SHI, SU)",
            kunyomi = "こ (ko)",
            strokeCount = 3,
            lessonId = 10,
            categoryFr = "Personnes & Société",
            mnemonicFr = "Un bébé emmailloté qui lève les bras.",
            examples = listOf(
                KanjiExample("子供", "こども", "kodomo", "Enfant"),
                KanjiExample("男の子", "おとこのこ", "otokonoko", "Garçon"),
                KanjiExample("女の子", "おんなのこ", "onnanoko", "Fille")
            )
        ),

        // === 4. Verbes & Actions Quotidiennes (Leçon 4-8) ===
        KanjiCharacter(
            kanji = "行",
            meaningFr = "Aller, Effectuer",
            onyomi = "コウ, ギョウ (KOU, GYOU)",
            kunyomi = "い・く, おこな・う (i-ku, okona-u)",
            strokeCount = 6,
            lessonId = 5,
            categoryFr = "Verbes & Actions",
            mnemonicFr = "Un carrefour où les gens circulent.",
            examples = listOf(
                KanjiExample("行きます", "いきます", "ikimasu", "Aller"),
                KanjiExample("旅行", "りょこう", "ryokou", "Voyage"),
                KanjiExample("銀行", "ぎんこう", "ginkou", "Banque")
            )
        ),
        KanjiCharacter(
            kanji = "来",
            meaningFr = "Venir, Prochain",
            onyomi = "ライ (RAI)",
            kunyomi = "く・る, き・ます (ku-ru, ki-masu)",
            strokeCount = 7,
            lessonId = 5,
            categoryFr = "Verbes & Actions",
            mnemonicFr = "Un arbre fruitier d'où les fruits viennent à nous.",
            examples = listOf(
                KanjiExample("来ます", "きます", "kimasu", "Venir"),
                KanjiExample("来週", "らいしゅう", "raishuu", "La semaine prochaine"),
                KanjiExample("来年", "らいねん", "rainen", "L'année prochaine")
            )
        ),
        KanjiCharacter(
            kanji = "帰",
            meaningFr = "Rentrer, Retourner (chez soi)",
            onyomi = "キ (KI)",
            kunyomi = "かえ・る, かえ・す (kae-ru, kae-su)",
            strokeCount = 10,
            lessonId = 5,
            categoryFr = "Verbes & Actions",
            mnemonicFr = "Balayer la maison au retour d'un voyage.",
            examples = listOf(
                KanjiExample("帰ります", "かえります", "kaerimasu", "Rentrer chez soi"),
                KanjiExample("帰国", "きこく", "kikoku", "Retour dans son pays d'origine")
            )
        ),
        KanjiCharacter(
            kanji = "食",
            meaningFr = "Manger, Nourriture",
            onyomi = "ショク, ジキ (SHOKU, JIKI)",
            kunyomi = "た・べる, く・う (ta-beru, ku-u)",
            strokeCount = 9,
            lessonId = 6,
            categoryFr = "Verbes & Actions",
            mnemonicFr = "Un plat sous cloche servi à table.",
            examples = listOf(
                KanjiExample("食べます", "たべます", "tabemasu", "Manger"),
                KanjiExample("食べ物", "たべもの", "tabemono", "Nourriture"),
                KanjiExample("朝食", "ちょうしょく", "choushoku", "Petit-déjeuner"),
                KanjiExample("食堂", "しょくどう", "shokudou", "Cantine / Réfectoire")
            )
        ),
        KanjiCharacter(
            kanji = "飲",
            meaningFr = "Boire, Boisson",
            onyomi = "イン (IN)",
            kunyomi = "の・む (no-mu)",
            strokeCount = 12,
            lessonId = 6,
            categoryFr = "Verbes & Actions",
            mnemonicFr = "Quelqu'un qui ouvre grand la bouche devant la nourriture.",
            examples = listOf(
                KanjiExample("飲んでいます", "のんでいます", "nondemasu", "Être en train de boire"),
                KanjiExample("飲みます", "のみます", "nomimasu", "Boire"),
                KanjiExample("飲み物", "のみもの", "nomimono", "Boisson")
            )
        ),
        KanjiCharacter(
            kanji = "見",
            meaningFr = "Voir, Regarder, Montrer",
            onyomi = "ケン (KEN)",
            kunyomi = "み・る, み・せる (mi-ru, mi-seru)",
            strokeCount = 7,
            lessonId = 6,
            categoryFr = "Verbes & Actions",
            mnemonicFr = "Un œil immense monté sur deux jambes d'observateur.",
            examples = listOf(
                KanjiExample("見ます", "みます", "mimasu", "Regarder / Voir"),
                KanjiExample("見せます", "みせます", "misemasu", "Montrer"),
                KanjiExample("見学", "けんがく", "kengaku", "Visite d'étude")
            )
        ),
        KanjiCharacter(
            kanji = "聞",
            meaningFr = "Écouter, Entendre, Demander",
            onyomi = "ブン, モン (BUN, MON)",
            kunyomi = "き・く, き・こえる (ki-ku, ki-koeru)",
            strokeCount = 14,
            lessonId = 6,
            categoryFr = "Verbes & Actions",
            mnemonicFr = "Une oreille (耳) collée à la porte (門) pour écouter.",
            examples = listOf(
                KanjiExample("聞きます", "ききます", "kikimasu", "Écouter / Demander"),
                KanjiExample("新聞", "しんぶん", "shinbun", "Journal (ce qu'on entend de neuf)")
            )
        ),
        KanjiCharacter(
            kanji = "読",
            meaningFr = "Lire",
            onyomi = "ドク, トク (DOKU, TOKU)",
            kunyomi = "よ・む (yo-mu)",
            strokeCount = 14,
            lessonId = 6,
            categoryFr = "Verbes & Actions",
            mnemonicFr = "Les mots (言) qu'on lit et prononce à haute voix.",
            examples = listOf(
                KanjiExample("読みます", "よみます", "yomimasu", "Lire"),
                KanjiExample("読書", "どくしょ", "dokusho", "Lecture")
            )
        ),
        KanjiCharacter(
            kanji = "書",
            meaningFr = "Écrire, Document",
            onyomi = "ショ (SHO)",
            kunyomi = "か・く (ka-ku)",
            strokeCount = 10,
            lessonId = 6,
            categoryFr = "Verbes & Actions",
            mnemonicFr = "Une main tenant un pinceau de calligraphie.",
            examples = listOf(
                KanjiExample("書きます", "かきます", "kakimasu", "Écrire"),
                KanjiExample("辞書", "じしょ", "jisho", "Dictionnaire"),
                KanjiExample("図書館", "としょかん", "toshokan", "Bibliothèque")
            )
        ),
        KanjiCharacter(
            kanji = "話",
            meaningFr = "Parler, Discussion, Histoire",
            onyomi = "ワ (WA)",
            kunyomi = "はな・す, はなし (hana-su, hanashi)",
            strokeCount = 13,
            lessonId = 6,
            categoryFr = "Verbes & Actions",
            mnemonicFr = "Les mots (言) qui sortent de la langue (舌) = parler.",
            examples = listOf(
                KanjiExample("話します", "はなします", "hanashimasu", "Parler"),
                KanjiExample("電話", "でんわ", "denwa", "Téléphone"),
                KanjiExample("会話", "かいわ", "kaiwa", "Conversation")
            )
        ),
        KanjiCharacter(
            kanji = "買",
            meaningFr = "Acheter",
            onyomi = "バイ (BAI)",
            kunyomi = "か・う (ka-u)",
            strokeCount = 12,
            lessonId = 6,
            categoryFr = "Verbes & Actions",
            mnemonicFr = "Un filet retenant des coquillages (ancienne monnaie).",
            examples = listOf(
                KanjiExample("買います", "かいます", "kaimasu", "Acheter"),
                KanjiExample("買い物", "かいもの", "kaimono", "Courses / Achats")
            )
        ),
        KanjiCharacter(
            kanji = "休",
            meaningFr = "Se reposer, Vacances, Pause",
            onyomi = "キュウ (KYUU)",
            kunyomi = "やす・む, やす・み (yasu-mu, yasu-mi)",
            strokeCount = 6,
            lessonId = 4,
            categoryFr = "Verbes & Actions",
            mnemonicFr = "Un homme (亻) adossé à un arbre (木) pour faire la sieste.",
            examples = listOf(
                KanjiExample("休みます", "やすみます", "yasumimasu", "Se reposer"),
                KanjiExample("休み", "やすみ", "yasumi", "Repos / Congé / Vacances"),
                KanjiExample("昼休み", "ひるやすみ", "hiruyasumi", "Pause déjeuner")
            )
        ),

        // === 5. Lieux, Nature & Société (Leçon 3, 7-10) ===
        KanjiCharacter(
            kanji = "山",
            meaningFr = "Montagne",
            onyomi = "サン (SAN)",
            kunyomi = "やま (yama)",
            strokeCount = 3,
            lessonId = 5,
            categoryFr = "Lieux & Nature",
            mnemonicFr = "Les trois pics d'une chaîne de montagnes.",
            examples = listOf(
                KanjiExample("山", "やま", "yama", "Montagne"),
                KanjiExample("富士山", "ふじさん", "fujisan", "Mont Fuji"),
                KanjiExample("火山", "かざん", "kazan", "Volcan")
            )
        ),
        KanjiCharacter(
            kanji = "川",
            meaningFr = "Rivière, Fleuve",
            onyomi = "セン (SEN)",
            kunyomi = "かわ, -がわ (kawa, gawa)",
            strokeCount = 3,
            lessonId = 5,
            categoryFr = "Lieux & Nature",
            mnemonicFr = "Trois filets d'eau qui coulent parallèlement.",
            examples = listOf(
                KanjiExample("川", "かわ", "kawa", "Rivière"),
                KanjiExample("小川", "おがわ", "ogawa", "Ruisseau")
            )
        ),
        KanjiCharacter(
            kanji = "田",
            meaningFr = "Rizière, Champ",
            onyomi = "デン (DEN)",
            kunyomi = "た (ta)",
            strokeCount = 5,
            lessonId = 1,
            categoryFr = "Lieux & Nature",
            mnemonicFr = "Une rizière vue du ciel divisée en parcelles carrées.",
            examples = listOf(
                KanjiExample("田中さん", "たなかさん", "tanakasan", "M. Tanaka"),
                KanjiExample("山田さん", "やまださん", "yamadasan", "M. Yamada"),
                KanjiExample("水田", "すいでん", "suiden", "Rizière inondée")
            )
        ),
        KanjiCharacter(
            kanji = "車",
            meaningFr = "Voiture, Véhicule, Roue",
            onyomi = "シャ (SHA)",
            kunyomi = "くるま (kuruma)",
            strokeCount = 7,
            lessonId = 2,
            categoryFr = "Lieux & Nature",
            mnemonicFr = "Un char vu du dessus avec ses deux roues et son essieu.",
            examples = listOf(
                KanjiExample("車", "くるま", "kuruma", "Voiture"),
                KanjiExample("電車", "でんしゃ", "densha", "Train électrique"),
                KanjiExample("自転車", "じてんしゃ", "jitensha", "Vélo")
            )
        ),
        KanjiCharacter(
            kanji = "駅",
            meaningFr = "Gare, Station",
            onyomi = "エキ (EKI)",
            kunyomi = "なし (nashi)",
            strokeCount = 14,
            lessonId = 5,
            categoryFr = "Lieux & Nature",
            mnemonicFr = "Le relais de poste où les chevaux (馬) s'arrêtaient.",
            examples = listOf(
                KanjiExample("駅", "えき", "eki", "Gare"),
                KanjiExample("東京駅", "とうきょうえき", "toukyoueki", "Gare de Tokyo"),
                KanjiExample("駅前", "えきまえ", "ekimae", "Devant la gare")
            )
        ),
        KanjiCharacter(
            kanji = "国",
            meaningFr = "Pays, Nation",
            onyomi = "コク (KOKU)",
            kunyomi = "くに (kuni)",
            strokeCount = 8,
            lessonId = 3,
            categoryFr = "Lieux & Nature",
            mnemonicFr = "Un trésor en jade (玉) protégé dans une enceinte fermée (囗).",
            examples = listOf(
                KanjiExample("外国", "がいこく", "gaikoku", "Pays étranger"),
                KanjiExample("お国", "おくにと", "okuni", "Votre pays (poli)"),
                KanjiExample("中国", "ちゅうごく", "chuugoku", "Chine")
            )
        ),
        KanjiCharacter(
            kanji = "会",
            meaningFr = "Rencontrer, Réunion, Assemblée",
            onyomi = "カイ (KAI)",
            kunyomi = "あ・う (a-u)",
            strokeCount = 6,
            lessonId = 6,
            categoryFr = "Lieux & Nature",
            mnemonicFr = "Des personnes réunies sous le même toit.",
            examples = listOf(
                KanjiExample("会います", "あいます", "aimasu", "Rencontrer"),
                KanjiExample("会社", "かいしゃ", "kaisha", "Entreprise / Société"),
                KanjiExample("会話", "かいわ", "kaiwa", "Conversation")
            )
        ),
        KanjiCharacter(
            kanji = "社",
            meaningFr = "Société, Sanctuaire, Compagnie",
            onyomi = "シャ (SHA)",
            kunyomi = "やしろ (yashiro)",
            strokeCount = 7,
            lessonId = 1,
            categoryFr = "Lieux & Nature",
            mnemonicFr = "Un autel rituel (礻) sur la terre sacrée (土).",
            examples = listOf(
                KanjiExample("会社", "かいしゃ", "kaisha", "Entreprise"),
                KanjiExample("社員", "しゃいん", "shain", "Employé d'entreprise"),
                KanjiExample("神社", "じんじゃ", "jinja", "Sanctuaire shinto")
            )
        ),
        KanjiCharacter(
            kanji = "前",
            meaningFr = "Avant, Devant",
            onyomi = "ゼン (ZEN)",
            kunyomi = "まえ (mae)",
            strokeCount = 9,
            lessonId = 10,
            categoryFr = "Lieux & Nature",
            mnemonicFr = "Avancer devant avec un couteau pour ouvrir la voie.",
            examples = listOf(
                KanjiExample("名前", "なまえ", "namae", "Nom / Prénom"),
                KanjiExample("駅前", "えきまえ", "ekimae", "Devant la gare"),
                KanjiExample("午前", "ごぜん", "gozen", "Matin (AM)")
            )
        ),
        KanjiCharacter(
            kanji = "後",
            meaningFr = "Après, Derrière",
            onyomi = "ゴ, コウ (GO, KOU)",
            kunyomi = "うし・ろ, あと (ushi-ro, ato)",
            strokeCount = 9,
            lessonId = 10,
            categoryFr = "Lieux & Nature",
            mnemonicFr = "Quelqu'un qui marche lentement et reste en arrière.",
            examples = listOf(
                KanjiExample("後ろ", "うしろ", "ushiro", "Derrière"),
                KanjiExample("午後", "ごご", "gogo", "Après-midi (PM)"),
                KanjiExample("後で", "あとで", "atode", "Plus tard")
            )
        ),
        KanjiCharacter(
            kanji = "中",
            meaningFr = "Milieu, Intérieur, Dedans, Centre",
            onyomi = "チュウ (CHUU)",
            kunyomi = "なか (naka)",
            strokeCount = 4,
            lessonId = 10,
            categoryFr = "Lieux & Nature",
            mnemonicFr = "Une flèche qui transperce le centre d'une cible carrée.",
            examples = listOf(
                KanjiExample("中", "なか", "naka", "Dedans / Intérieur"),
                KanjiExample("一日中", "いちにちじゅう", "ichinichijuu", "Toute la journée"),
                KanjiExample("中国", "ちゅうごく", "chuugoku", "Chine (empire du milieu)")
            )
        ),
        KanjiCharacter(
            kanji = "上",
            meaningFr = "Haut, Au-dessus, Monter",
            onyomi = "ジョウ (JOU)",
            kunyomi = "うえ, あ・がる (ue, a-garu)",
            strokeCount = 3,
            lessonId = 10,
            categoryFr = "Lieux & Nature",
            mnemonicFr = "Une ligne horizontale avec un repère pointant vers le haut.",
            examples = listOf(
                KanjiExample("上", "うえ", "ue", "Au-dessus / Sur"),
                KanjiExample("上手", "じょうず", "jouzu", "Doué / Habile"),
                KanjiExample("上がります", "あがります", "agarimasu", "Monter")
            )
        ),
        KanjiCharacter(
            kanji = "下",
            meaningFr = "Bas, En-dessous, Descendre",
            onyomi = "カ, ゲ (KA, GE)",
            kunyomi = "した, さ・がる, くだ・さい (shita, sa-garu, kuda-sai)",
            strokeCount = 3,
            lessonId = 10,
            categoryFr = "Lieux & Nature",
            mnemonicFr = "Une ligne horizontale avec un repère pointant vers le bas.",
            examples = listOf(
                KanjiExample("下", "した", "shita", "En-dessous / Sous"),
                KanjiExample("下手", "へた", "heta", "Maladroit"),
                KanjiExample("地下鉄", "ちかてつ", "chikatetsu", "Métro (train sous terre)")
            )
        )
    )

    val categories: List<String> = listOf(
        "Tous",
        "Nombres & Monnaie",
        "Temps & Calendrier",
        "Personnes & Société",
        "Verbes & Actions",
        "Lieux & Nature"
    )
}
