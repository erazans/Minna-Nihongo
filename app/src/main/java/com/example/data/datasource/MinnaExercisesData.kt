package com.example.data.datasource

import com.example.data.model.ChapterSectionType
import com.example.data.model.Exercise
import com.example.data.model.ExerciseType

object MinnaExercisesData {

    fun getExercisesForLesson(lessonId: Int, targetSection: ChapterSectionType? = null): List<Exercise> {
        if (targetSection == ChapterSectionType.REVISION) {
            return MinnaReviewGenerator.generateRepeatableReview(lessonId, count = 10)
        }

        val all = when (lessonId) {
            1 -> getLesson1Exercises()
            2 -> getLesson2Exercises()
            3 -> getLesson3Exercises()
            4 -> getLesson4Exercises()
            5 -> getLesson5Exercises()
            6 -> getLesson6Exercises()
            7 -> getLesson7Exercises()
            8 -> getLesson8Exercises()
            9 -> getLesson9Exercises()
            10 -> getLesson10Exercises()
            11 -> getLesson11Exercises()
            12 -> getLesson12Exercises()
            13 -> getLesson13Exercises()
            14 -> getLesson14Exercises()
            15 -> getLesson15Exercises()
            16 -> getLesson16Exercises()
            17 -> getLesson17Exercises()
            18 -> getLesson18Exercises()
            19 -> getLesson19Exercises()
            20 -> getLesson20Exercises()
            else -> getGenericLessonExercises(lessonId)
        }

        if (targetSection == null) return all

        val filtered = all.filter { it.section == targetSection }
        return if (filtered.isNotEmpty()) filtered else all
    }

    private fun getLesson1Exercises(): List<Exercise> = listOf(
        // Renshuu B
        Exercise(
            id = "ex_1_1",
            lessonId = 1,
            section = ChapterSectionType.RENSHUU_B,
            bookReference = "練習B-1 (p.29)",
            type = ExerciseType.MULTIPLE_CHOICE,
            questionFr = "練習B-1 : Associez selon l'exemple (山田さん・日本)",
            promptJp = "やまださんは……",
            targetSentenceJp = "山田さんは 日本人です。",
            targetSentenceRomaji = "Yamada-san wa Nihon-jin desu.",
            options = listOf(
                "山田さんは 日本人です。",
                "山田さんは アメリカ人です。",
                "山田さんは タイ人です。",
                "山田さんは ドイツ人です。"
            ),
            correctOptions = listOf("山田さんは 日本人です。"),
            hint = "Nationalité = Nom du pays + 人 (じん).",
            audioText = "やまださんは にほんじんです。"
        ),
        Exercise(
            id = "ex_1_2",
            lessonId = 1,
            section = ChapterSectionType.RENSHUU_B,
            bookReference = "練習B-2 (p.29)",
            type = ExerciseType.WORD_ORDER,
            questionFr = "練習B-2 : Reconstituez : 'M. Yamada est employé de banque.'",
            promptJp = null,
            targetSentenceJp = "山田さんは 銀行員です。",
            targetSentenceRomaji = "Yamada-san wa ginkouin desu.",
            options = listOf("山田さんは", "銀行員です", "学生じゃ", "ありません", "会社員", "先生"),
            correctOptions = listOf("山田さんは", "銀行員です"),
            hint = "銀行員 (ginkouin) = employé de banque.",
            audioText = "やまださんは ぎんこういんです。"
        ),
        Exercise(
            id = "ex_1_3",
            lessonId = 1,
            section = ChapterSectionType.RENSHUU_B,
            bookReference = "練習B-3 (p.29)",
            type = ExerciseType.MULTIPLE_CHOICE,
            questionFr = "練習B-3 : Formulez la négation : 'M. Santos n'est pas étudiant.'",
            promptJp = "サントスさんは [ ? ]。",
            targetSentenceJp = "サントスさんは 学生じゃ ありません。",
            targetSentenceRomaji = "Santosu-san wa gakusei ja arimasen.",
            options = listOf(
                "学生じゃ ありません",
                "学生です",
                "学生でした",
                "学生じゃ ありませんでした"
            ),
            correctOptions = listOf("学生じゃ ありません"),
            hint = "Forme négative polie de です = じゃ ありません.",
            audioText = "サントスさんは がくせいじゃ ありません。"
        ),
        Exercise(
            id = "ex_1_4",
            lessonId = 1,
            section = ChapterSectionType.RENSHUU_C,
            bookReference = "練習C-1 (p.31)",
            type = ExerciseType.SPEECH_PRONUNCIATION,
            questionFr = "練習C-1 (Expression Orale) : Présentez-vous poliment :",
            promptJp = "初めまして、どうぞ よろしく お願いします",
            targetSentenceJp = "はじめまして どうぞ よろしく おねがいします",
            targetSentenceRomaji = "Hajimemashite douzo yoroshiku onegaishimasu",
            options = emptyList(),
            correctOptions = listOf("はじめましてどうぞよろしくおねがいします", "はじめましてどうぞよろしく", "よろしくおねがいします"),
            hint = "Dites à haute voix : 'Hajimemashite douzo yoroshiku onegaishimasu'.",
            audioText = "はじめまして、どうぞ よろしく おねがいします。"
        ),
        Exercise(
            id = "ex_1_5",
            lessonId = 1,
            section = ChapterSectionType.MONDAI,
            bookReference = "問題-4 (p.33)",
            type = ExerciseType.LISTENING_SELECT,
            questionFr = "問題-4 (Écoute & Validation) : Écoutez attentivement la question de politesse :",
            promptJp = "Écoute audio",
            targetSentenceJp = "あの方は どなたですか。",
            targetSentenceRomaji = "Ano kata wa donata desu ka.",
            options = listOf(
                "あの方は どなたですか。",
                "あの人は だれですか。",
                "お名前は 何ですか。",
                "会社員ですか。"
            ),
            correctOptions = listOf("あの方は どなたですか。"),
            hint = "あの方 (ano kata) = cette personne (poli), どなた = qui (poli).",
            audioText = "あのかたは どなたですか。"
        )
    )

    private fun getLesson2Exercises(): List<Exercise> = listOf(
        Exercise(
            id = "ex_2_1",
            lessonId = 2,
            section = ChapterSectionType.RENSHUU_B,
            bookReference = "練習B-1 (p.37)",
            type = ExerciseType.MULTIPLE_CHOICE,
            questionFr = "練習B-1 : Désignez l'objet près de vous : 'Ceci est un dictionnaire.'",
            promptJp = "[ ? ] は 辞書です。",
            targetSentenceJp = "これは 辞書です。",
            targetSentenceRomaji = "Kore wa jisho desu.",
            options = listOf("これ", "それ", "あれ", "この"),
            correctOptions = listOf("これ"),
            hint = "これ = ceci (proche du locuteur).",
            audioText = "これは じしょです。"
        ),
        Exercise(
            id = "ex_2_2",
            lessonId = 2,
            section = ChapterSectionType.RENSHUU_B,
            bookReference = "練習B-5 (p.38)",
            type = ExerciseType.WORD_ORDER,
            questionFr = "練習B-5 : Reconstituez : 'C'est la clé de la voiture.'",
            promptJp = null,
            targetSentenceJp = "車の 鍵です。",
            targetSentenceRomaji = "Kuruma no kagi desu.",
            options = listOf("車の", "鍵です", "雑誌", "本", "この", "だれ"),
            correctOptions = listOf("車の", "鍵です"),
            hint = "N1 の N2 : de la voiture = 車の.",
            audioText = "くるまの かぎです。"
        ),
        Exercise(
            id = "ex_2_3",
            lessonId = 2,
            section = ChapterSectionType.RENSHUU_C,
            bookReference = "練習C-1 (p.39)",
            type = ExerciseType.SPEECH_PRONUNCIATION,
            questionFr = "練習C-1 (Expression Orale) : Offrez un souvenir à votre voisin :",
            promptJp = "あのう、これ、お土産です。どうぞ。",
            targetSentenceJp = "あのう これ おみやげです どうぞ",
            targetSentenceRomaji = "Anou, kore, omiyage desu. Douzo.",
            options = emptyList(),
            correctOptions = listOf("あのうこれおみやげですどうぞ", "これおみやげです", "おみやげですどうぞ"),
            hint = "Prononcez : 'Anou, kore, omiyage desu. Douzo.'",
            audioText = "あのう、これ、おみやげです。どうぞ。"
        ),
        Exercise(
            id = "ex_2_4",
            lessonId = 2,
            section = ChapterSectionType.MONDAI,
            bookReference = "問題-6 (p.41)",
            type = ExerciseType.FILL_BLANK,
            questionFr = "問題-6 : Complétez : 'À qui est ce parapluie ?'",
            promptJp = "これは [ ? ] の 傘ですか。",
            targetSentenceJp = "これは だれの 傘ですか。",
            targetSentenceRomaji = "Kore wa dare no kasa desu ka.",
            options = listOf("だれ", "なん", "どこ", "どれ"),
            correctOptions = listOf("だれ"),
            hint = "だれの = de qui / à qui.",
            audioText = "これは だれの かさですか。"
        )
    )

    private fun getLesson3Exercises(): List<Exercise> = listOf(
        Exercise(
            id = "ex_3_1",
            lessonId = 3,
            section = ChapterSectionType.RENSHUU_B,
            bookReference = "練習B-2 (p.45)",
            type = ExerciseType.MULTIPLE_CHOICE,
            questionFr = "練習B-2 : Demandez où sont les toilettes :",
            promptJp = "トイレは……",
            targetSentenceJp = "トイレは どこですか。",
            targetSentenceRomaji = "Toire wa doko desu ka.",
            options = listOf(
                "トイレは どこですか。",
                "トイレは いくらですか。",
                "トイレは なんじですか。",
                "トイレは だれですか。"
            ),
            correctOptions = listOf("トイレは どこですか。"),
            hint = "どこ = où.",
            audioText = "トイレは どこですか。"
        ),
        Exercise(
            id = "ex_3_2",
            lessonId = 3,
            section = ChapterSectionType.RENSHUU_B,
            bookReference = "練習B-8 (p.46)",
            type = ExerciseType.LISTENING_SELECT,
            questionFr = "練習B-8 (Compréhension Orale) : Écoutez le prix annoncé :",
            promptJp = "Écoute audio",
            targetSentenceJp = "３,２００円です。",
            targetSentenceRomaji = "San-zen nihyaku-en desu.",
            options = listOf("3,200円", "2,300円", "320円", "32,000円"),
            correctOptions = listOf("3,200円"),
            hint = "さんぜん = 3000, にひゃく = 200.",
            audioText = "さんぜん にひゃく えんです。"
        ),
        Exercise(
            id = "ex_3_3",
            lessonId = 3,
            section = ChapterSectionType.MONDAI,
            bookReference = "問題-5 (p.49)",
            type = ExerciseType.WORD_ORDER,
            questionFr = "問題-5 : Demandez : 'Combien coûte ce vin ?'",
            promptJp = null,
            targetSentenceJp = "この ワインは いくらですか。",
            targetSentenceRomaji = "Kono wain wa ikura desu ka.",
            options = listOf("この", "ワインは", "いくら", "ですか", "どこ", "だれ"),
            correctOptions = listOf("この", "ワインは", "いくら", "ですか"),
            hint = "いくらですか = Combien coûte-t-il ?",
            audioText = "この ワインは いくらですか。"
        )
    )

    private fun getLesson4Exercises(): List<Exercise> = listOf(
        Exercise(
            id = "ex_4_1",
            lessonId = 4,
            section = ChapterSectionType.RENSHUU_B,
            bookReference = "練習B-4 (p.55)",
            type = ExerciseType.WORD_ORDER,
            questionFr = "練習B-4 : Reconstituez : 'La banque est ouverte de 9h à 15h.'",
            promptJp = null,
            targetSentenceJp = "銀行は ９時から ３時までです。",
            targetSentenceRomaji = "Ginkou wa kuji kara sanji made desu.",
            options = listOf("銀行は", "９時から", "３時までです", "勉強します", "休み"),
            correctOptions = listOf("銀行は", "９時から", "３時までです"),
            hint = "〜から (de/depuis) 〜まで (jusqu'à).",
            audioText = "ぎんこうは くじから さんじまでです。"
        ),
        Exercise(
            id = "ex_4_2",
            lessonId = 4,
            section = ChapterSectionType.MONDAI,
            bookReference = "問題-7 (p.59)",
            type = ExerciseType.MULTIPLE_CHOICE,
            questionFr = "問題-7 : Conjuguez au passé négatif : 'Hier je n'ai pas étudié.'",
            promptJp = "きのう [ ? ]。",
            targetSentenceJp = "きのう 勉強しませんでした。",
            targetSentenceRomaji = "Kinou benkyoushimasendeshita.",
            options = listOf(
                "勉強しませんでした",
                "勉強しました",
                "勉強します",
                "勉強しません"
            ),
            correctOptions = listOf("勉強しませんでした"),
            hint = "Passé négatif des verbes = 〜ませんでした.",
            audioText = "きのう べんきょうしませんでした。"
        )
    )

    private fun getLesson5Exercises(): List<Exercise> = listOf(
        Exercise(
            id = "ex_5_1",
            lessonId = 5,
            section = ChapterSectionType.RENSHUU_B,
            bookReference = "練習B-3 (p.63)",
            type = ExerciseType.FILL_BLANK,
            questionFr = "練習B-3 : Complétez le moyen de transport : 'Je vais à Kyoto en Shinkansen.'",
            promptJp = "新幹線 [ ? ] 京都へ 行きます。",
            targetSentenceJp = "新幹線で 京都へ 行きます。",
            targetSentenceRomaji = "Shinkansen de Kyouto e ikimasu.",
            options = listOf("で", "へ", "に", "を"),
            correctOptions = listOf("で"),
            hint = "で marque le moyen de locomotion.",
            audioText = "しんかんせんで きょうとへ いきます。"
        ),
        Exercise(
            id = "ex_5_2",
            lessonId = 5,
            section = ChapterSectionType.RENSHUU_C,
            bookReference = "練習C-1 (p.65)",
            type = ExerciseType.WORD_ORDER,
            questionFr = "練習C-1 : Traduisez : 'Dimanche, je vais au château d'Osaka.'",
            promptJp = null,
            targetSentenceJp = "日曜日は 大阪城へ 行きます。",
            targetSentenceRomaji = "Nichiyoubi wa Oosakajou e ikimasu.",
            options = listOf("日曜日は", "大阪城へ", "行きます", "来ました", "電車で"),
            correctOptions = listOf("日曜日は", "大阪城へ", "行きます"),
            hint = "Destination + へ + 行きます.",
            audioText = "にちようびは おおさかじょうへ いきます。"
        )
    )

    private fun getLesson6Exercises(): List<Exercise> = listOf(
        Exercise(
            id = "ex_6_1",
            lessonId = 6,
            section = ChapterSectionType.RENSHUU_B,
            bookReference = "練習B-1 (p.71)",
            type = ExerciseType.FILL_BLANK,
            questionFr = "練習B-1 : Complétez avec la particule COD : 'Je lis le journal.'",
            promptJp = "新聞 [ ? ] 読みます。",
            targetSentenceJp = "新聞を 読みます。",
            targetSentenceRomaji = "Shinbun o yomimasu.",
            options = listOf("を", "で", "に", "へ"),
            correctOptions = listOf("を"),
            hint = "を (o) marque le complément d'objet direct.",
            audioText = "しんぶんを よみます。"
        ),
        Exercise(
            id = "ex_6_2",
            lessonId = 6,
            section = ChapterSectionType.RENSHUU_B,
            bookReference = "練習B-6 (p.72)",
            type = ExerciseType.MULTIPLE_CHOICE,
            questionFr = "練習B-6 : Comment inviter quelqu'un : 'Si nous allions à Kyoto ensemble ?'",
            promptJp = "いっしょに 京都へ……",
            targetSentenceJp = "いっしょに 京都へ 行きませんか。",
            targetSentenceRomaji = "Issho ni Kyouto e ikimasen ka.",
            options = listOf(
                "行きませんか",
                "行きましたか",
                "行きます",
                "行きませんでした"
            ),
            correctOptions = listOf("行きませんか"),
            hint = "〜ませんか = proposition/invitation polie.",
            audioText = "いっしょに きょうとへ いきませんか。"
        )
    )

    private fun getLesson7Exercises(): List<Exercise> = listOf(
        Exercise(
            id = "ex_7_1",
            lessonId = 7,
            section = ChapterSectionType.RENSHUU_B,
            bookReference = "練習B-1 (p.79)",
            type = ExerciseType.WORD_ORDER,
            questionFr = "練習B-1 : 'Je mange le riz avec des baguettes.'",
            promptJp = null,
            targetSentenceJp = "はしで ごはんを 食べます。",
            targetSentenceRomaji = "Hashi de gohan o tabemasu.",
            options = listOf("はしで", "ごはんを", "食べます", "ナイフ", "スプーン"),
            correctOptions = listOf("はしで", "ごはんを", "食べます"),
            hint = "Outil + で.",
            audioText = "はしで ごはんを たべます。"
        ),
        Exercise(
            id = "ex_7_2",
            lessonId = 7,
            section = ChapterSectionType.RENSHUU_B,
            bookReference = "練習B-3 (p.79)",
            type = ExerciseType.FILL_BLANK,
            questionFr = "練習B-3 : 'J'offre des fleurs à Mme Sato.'",
            promptJp = "佐藤さん [ ? ] 花を あげます。",
            targetSentenceJp = "佐藤さんに 花を あげます。",
            targetSentenceRomaji = "Satou-san ni hana o agemasu.",
            options = listOf("に", "で", "を", "から"),
            correctOptions = listOf("に"),
            hint = "Personne qui reçoit + に + あげます.",
            audioText = "さとうさんに はなを あげます。"
        )
    )

    private fun getLesson8Exercises(): List<Exercise> = listOf(
        Exercise(
            id = "ex_8_1",
            lessonId = 8,
            section = ChapterSectionType.RENSHUU_B,
            bookReference = "練習B-3 (p.89)",
            type = ExerciseType.MULTIPLE_CHOICE,
            questionFr = "練習B-3 : Forme négative de l'adjectif en -i : 'Ce vélo n'est pas neuf (atarashii)'.",
            promptJp = "この 自転車は [ ? ]。",
            targetSentenceJp = "この 自転車は 新しくないです。",
            targetSentenceRomaji = "Kono jitensha wa atarashikunai desu.",
            options = listOf(
                "新しくないです",
                "新しいじゃありません",
                "新しいくないです",
                "新しかったです"
            ),
            correctOptions = listOf("新しくないです"),
            hint = "Règle adjectifs en -i : supprimer le い final et ajouter くないです.",
            audioText = "この じてんしゃは あたらしくないです。"
        )
    )

    private fun getLesson9Exercises(): List<Exercise> = listOf(
        Exercise(
            id = "ex_9_1",
            lessonId = 9,
            section = ChapterSectionType.RENSHUU_B,
            bookReference = "練習B-1 (p.97)",
            type = ExerciseType.FILL_BLANK,
            questionFr = "練習B-1 : Particule marquant le goût : 'J'aime la cuisine japonaise.'",
            promptJp = "日本料理 [ ? ] 好きです。",
            targetSentenceJp = "日本料理が 好きです。",
            targetSentenceRomaji = "Nihon ryouri ga suki desu.",
            options = listOf("が", "を", "は", "に"),
            correctOptions = listOf("が"),
            hint = "好き / 嫌い / 上手 / 下手 prennent la particule が.",
            audioText = "にほんりょうりが すきです。"
        )
    )

    private fun getLesson10Exercises(): List<Exercise> = listOf(
        Exercise(
            id = "ex_10_1",
            lessonId = 10,
            section = ChapterSectionType.RENSHUU_B,
            bookReference = "練習B-1 (p.105)",
            type = ExerciseType.MULTIPLE_CHOICE,
            questionFr = "練習B-1 : Distinguez あります et います : 'Il y a un chien dans le jardin.'",
            promptJp = "庭に 犬が [ ? ]。",
            targetSentenceJp = "庭に 犬が います。",
            targetSentenceRomaji = "Niwa ni inu ga imasu.",
            options = listOf("います", "あります", "でした", "します"),
            correctOptions = listOf("います"),
            hint = "Êtres vivants (humains/animaux) = います. Objets inanimés = あります.",
            audioText = "にわに いぬが います。"
        )
    )

    private fun getLesson11Exercises(): List<Exercise> = listOf(
        Exercise(
            id = "ex_11_1",
            lessonId = 11,
            section = ChapterSectionType.RENSHUU_B,
            bookReference = "練習B-1 (p.113)",
            type = ExerciseType.MULTIPLE_CHOICE,
            questionFr = "練習B-1 : Compteurs : Commandez 4 pommes s'il vous plaît :",
            promptJp = "りんごを [ ? ] ください。",
            targetSentenceJp = "りんごを ４つ ください。",
            targetSentenceRomaji = "Ringo o yottsu kudasai.",
            options = listOf("４つ (よっつ)", "４人 (よにん)", "４枚 (よんまい)", "４台 (よんだい)"),
            correctOptions = listOf("４つ (よっつ)"),
            hint = "Compteur générique : ひとつ、ふたつ、みっつ、よっつ...",
            audioText = "りんごを よっつ ください。"
        ),
        Exercise(
            id = "ex_11_2",
            lessonId = 11,
            section = ChapterSectionType.MONDAI,
            bookReference = "問題-2 (p.117)",
            type = ExerciseType.WORD_ORDER,
            questionFr = "問題-2 : 'Combien de temps faut-il jusqu'à Tokyo ?'",
            promptJp = null,
            targetSentenceJp = "東京まで どのくらい かかりますか。",
            targetSentenceRomaji = "Toukyou made dono kurai kakarimasu ka.",
            options = listOf("東京まで", "どのくらい", "かかりますか", "何人", "時間"),
            correctOptions = listOf("東京まで", "どのくらい", "かかりますか"),
            hint = "どのくらい (dono kurai) = combien de temps environ.",
            audioText = "とうきょうまで どのくらい かかりますか。"
        )
    )

    private fun getLesson12Exercises(): List<Exercise> = listOf(
        Exercise(
            id = "ex_12_1",
            lessonId = 12,
            section = ChapterSectionType.RENSHUU_B,
            bookReference = "練習B-2 (p.123)",
            type = ExerciseType.MULTIPLE_CHOICE,
            questionFr = "練習B-2 : Comparaison : 'L'avion est plus rapide que le train.'",
            promptJp = "飛行機は 電車[ ? ] 速いです。",
            targetSentenceJp = "飛行機は 電車より 速いです。",
            targetSentenceRomaji = "Hikouki wa densha yori hayai desu.",
            options = listOf("より", "ほど", "から", "まで"),
            correctOptions = listOf("より"),
            hint = "Structure du comparatif : A は B より [Adjectif] です.",
            audioText = "ひこうきは でんしゃより はやいです。"
        ),
        Exercise(
            id = "ex_12_2",
            lessonId = 12,
            section = ChapterSectionType.MONDAI,
            bookReference = "問題-3 (p.127)",
            type = ExerciseType.WORD_ORDER,
            questionFr = "問題-3 : 'Hier il faisait froid.' (Passé de l'adjectif 寒い)",
            promptJp = null,
            targetSentenceJp = "昨日は 寒かったです。",
            targetSentenceRomaji = "Kinou wa samukatta desu.",
            options = listOf("昨日は", "寒かったです", "寒いでした", "暑い", "明日"),
            correctOptions = listOf("昨日は", "寒かったです"),
            hint = "Passé des adjectifs en -i : 〜い → 〜かったです.",
            audioText = "きのうは さむかったです。"
        )
    )

    private fun getLesson13Exercises(): List<Exercise> = listOf(
        Exercise(
            id = "ex_13_1",
            lessonId = 13,
            section = ChapterSectionType.RENSHUU_B,
            bookReference = "練習B-1 (p.131)",
            type = ExerciseType.MULTIPLE_CHOICE,
            questionFr = "練習B-1 : Exprimer un souhait (objet) : 'Je veux une nouvelle voiture.'",
            promptJp = "新しい 車が [ ? ]。",
            targetSentenceJp = "新しい 車が 欲しいです。",
            targetSentenceRomaji = "Atarashii kuruma ga hoshii desu.",
            options = listOf("欲しいです", "買いたいです", "好きです", "あります"),
            correctOptions = listOf("欲しいです"),
            hint = "[Objet] が 欲しい (hoshii) = désirer / vouloir un objet.",
            audioText = "あたらしい くるまが ほしいです。"
        ),
        Exercise(
            id = "ex_13_2",
            lessonId = 13,
            section = ChapterSectionType.RENSHUU_C,
            bookReference = "練習C-1 (p.133)",
            type = ExerciseType.WORD_ORDER,
            questionFr = "練習C-1 : But du déplacement : 'Je vais au grand magasin pour faire des achats.'",
            promptJp = null,
            targetSentenceJp = "デパートへ 買い物に 行きます。",
            targetSentenceRomaji = "Depaato e kaimono ni ikimasu.",
            options = listOf("デパートへ", "買い物に", "行きます", "食べに", "電車"),
            correctOptions = listOf("デパートへ", "買い物に", "行きます"),
            hint = "[Lieu] へ [Nom/Radical verbe] に 行きます = aller pour...",
            audioText = "デパートへ かいものに いきます。"
        )
    )

    private fun getLesson14Exercises(): List<Exercise> = listOf(
        Exercise(
            id = "ex_14_1",
            lessonId = 14,
            section = ChapterSectionType.RENSHUU_B,
            bookReference = "練習B-1 (p.141)",
            type = ExerciseType.MULTIPLE_CHOICE,
            questionFr = "練習B-1 : Forme en -Te de 書きます (kakimasu, écrire - G1) :",
            promptJp = "書きます → [ ? ]",
            targetSentenceJp = "書いて (kaite)",
            targetSentenceRomaji = "kaite",
            options = listOf("かいて", "かって", "かんで", "かして"),
            correctOptions = listOf("かいて"),
            hint = "〜きます (ki) → 〜いて (ite).",
            audioText = "かいて"
        ),
        Exercise(
            id = "ex_14_2",
            lessonId = 14,
            section = ChapterSectionType.RENSHUU_C,
            bookReference = "練習C-1 (p.143)",
            type = ExerciseType.SPEECH_PRONUNCIATION,
            questionFr = "練習C-1 (Expression Orale) : 'Attendez un peu s'il vous plaît.'",
            promptJp = "ちょっと 待ってください",
            targetSentenceJp = "ちょっと まってください",
            targetSentenceRomaji = "Chotto matte kudasai",
            options = emptyList(),
            correctOptions = listOf("ちょっとまってください", "ちょっとまって"),
            hint = "Prononcez : 'Chotto matte kudasai'.",
            audioText = "ちょっと まってください。"
        )
    )

    private fun getLesson15Exercises(): List<Exercise> = listOf(
        Exercise(
            id = "ex_15_1",
            lessonId = 15,
            section = ChapterSectionType.RENSHUU_B,
            bookReference = "練習B-1 (p.149)",
            type = ExerciseType.MULTIPLE_CHOICE,
            questionFr = "練習B-1 : Demander la permission : 'Puis-je utiliser cet ordinateur ?'",
            promptJp = "パソコンを [ ? ]。",
            targetSentenceJp = "パソコンを 使っても いいですか。",
            targetSentenceRomaji = "Pasokon o tsukatte mo ii desu ka.",
            options = listOf(
                "使っても いいですか",
                "使っては いけません",
                "使ってください",
                "使いました"
            ),
            correctOptions = listOf("使っても いいですか"),
            hint = "Forme en -Te + もいいですか = permission.",
            audioText = "パソコンを つかっても いいですか。"
        )
    )

    private fun getLesson16Exercises(): List<Exercise> = listOf(
        Exercise(
            id = "ex_16_1",
            lessonId = 16,
            section = ChapterSectionType.RENSHUU_B,
            bookReference = "練習B-1 (p.157)",
            type = ExerciseType.WORD_ORDER,
            questionFr = "練習B-1 : Enchaînement d'actions : 'Le matin je me lève, prends une douche et vais au travail.'",
            promptJp = null,
            targetSentenceJp = "朝 起きて、シャワーを 浴びて、会社へ 行きます。",
            targetSentenceRomaji = "Asa okite, shawaa o abite, kaisha e ikimasu.",
            options = listOf("朝 起きて、", "シャワーを 浴びて、", "会社へ 行きます。", "起きました", "夜"),
            correctOptions = listOf("朝 起きて、", "シャワーを 浴びて、", "会社へ 行きます。"),
            hint = "Verbe 1 (-Te), Verbe 2 (-Te), Verbe 3 (forme finale).",
            audioText = "あさ おきて、シャワーを あびて、かいしゃへ いきます。"
        ),
        Exercise(
            id = "ex_16_2",
            lessonId = 16,
            section = ChapterSectionType.RENSHUU_B,
            bookReference = "練習B-3 (p.157)",
            type = ExerciseType.MULTIPLE_CHOICE,
            questionFr = "練習B-3 : 'Après avoir mis l'argent, appuyez sur le bouton s'il vous plaît.'",
            promptJp = "お金を [ ? ]、ボタンを 押してください。",
            targetSentenceJp = "お金を 入れてから、ボタンを 押してください。",
            targetSentenceRomaji = "Okane o irete kara, botan o oshite kudasai.",
            options = listOf("入れてから", "入れたあと", "入れたら", "入れた"),
            correctOptions = listOf("入れてから"),
            hint = "Forme en -Te + から = après avoir fait...",
            audioText = "おかねを いれてから、ボタンを おしてください。"
        )
    )

    private fun getLesson17Exercises(): List<Exercise> = listOf(
        Exercise(
            id = "ex_17_1",
            lessonId = 17,
            section = ChapterSectionType.RENSHUU_B,
            bookReference = "練習B-2 (p.167)",
            type = ExerciseType.WORD_ORDER,
            questionFr = "練習B-2 : 'C'est non-fumeur, veuillez ne pas fumer s'il vous plaît.'",
            promptJp = null,
            targetSentenceJp = "たばこを 吸わないで ください。",
            targetSentenceRomaji = "Tabako o suwanaide kudasai.",
            options = listOf("たばこを", "吸わないで", "ください", "吸ってください", "禁煙"),
            correctOptions = listOf("たばこを", "吸わないで", "ください"),
            hint = "Forme en -Nai + でください = prière de ne pas faire.",
            audioText = "たばこを すわないで ください。"
        )
    )

    private fun getLesson18Exercises(): List<Exercise> = listOf(
        Exercise(
            id = "ex_18_1",
            lessonId = 18,
            section = ChapterSectionType.RENSHUU_B,
            bookReference = "練習B-2 (p.175)",
            type = ExerciseType.WORD_ORDER,
            questionFr = "練習B-2 : 'M. Miller sait lire les kanji.'",
            promptJp = null,
            targetSentenceJp = "ミラーさんは 漢字を 読むことが できます。",
            targetSentenceRomaji = "Miraa-san wa kanji o yomu koto ga dekimasu.",
            options = listOf("ミラーさんは", "漢字を", "読むことが", "できます", "読みました"),
            correctOptions = listOf("ミラーさんは", "漢字を", "読むことが", "できます"),
            hint = "Verbe au dictionnaire (よむ) + ことが できます.",
            audioText = "ミラーさんは かんじを よむことが できます。"
        )
    )

    private fun getLesson19Exercises(): List<Exercise> = listOf(
        Exercise(
            id = "ex_19_1",
            lessonId = 19,
            section = ChapterSectionType.RENSHUU_B,
            bookReference = "練習B-1 (p.183)",
            type = ExerciseType.MULTIPLE_CHOICE,
            questionFr = "練習B-1 : Exprimer une expérience vécue : 'J'ai déjà été à Hiroshima.'",
            promptJp = "広島へ [ ? ]。",
            targetSentenceJp = "広島へ 行ったことが あります。",
            targetSentenceRomaji = "Hiroshima e itta koto ga arimasu.",
            options = listOf(
                "行ったことが あります",
                "行くことが あります",
                "行きたいです",
                "行きました"
            ),
            correctOptions = listOf("行ったことが あります"),
            hint = "Forme en -Ta + ことが あります = avoir déjà fait.",
            audioText = "ひろしまへ いったことが あります。"
        )
    )

    private fun getLesson20Exercises(): List<Exercise> = listOf(
        Exercise(
            id = "ex_20_1",
            lessonId = 20,
            section = ChapterSectionType.RENSHUU_B,
            bookReference = "練習B-3 (p.193)",
            type = ExerciseType.MULTIPLE_CHOICE,
            questionFr = "練習B-3 : Style familier neutre (Futsuukei) : 'Tu es libre maintenant ?'",
            promptJp = "今 [ ? ]？",
            targetSentenceJp = "今 暇？ (Ima hima?)",
            targetSentenceRomaji = "Ima hima?",
            options = listOf("暇？", "暇ですか？", "暇でした？", "暇じゃない？"),
            correctOptions = listOf("暇？"),
            hint = "Au style neutre familier, on omet です et on monte l'intonation.",
            audioText = "いま ひま？"
        )
    )

    private fun getGenericLessonExercises(lessonId: Int): List<Exercise> = listOf(
        Exercise(
            id = "ex_${lessonId}_renshuu_b",
            lessonId = lessonId,
            section = ChapterSectionType.RENSHUU_B,
            bookReference = "練習B (p. manuel)",
            type = ExerciseType.MULTIPLE_CHOICE,
            questionFr = "Exercice d'application 練習B - Leçon $lessonId :",
            promptJp = "第${lessonId}課の 練習を しましょう。",
            targetSentenceJp = "日本語を 勉強します。",
            targetSentenceRomaji = "Nihongo o benkyoushimasu.",
            options = listOf(
                "日本語を 勉強します。",
                "日本語を 話しません。",
                "英語を 勉強しました。",
                "あした 休みます。"
            ),
            correctOptions = listOf("日本語を 勉強します。"),
            hint = "Appliquez les points de grammaire et de vocabulaire de la leçon $lessonId.",
            audioText = "にほんごを べんきょうします。"
        ),
        Exercise(
            id = "ex_${lessonId}_mondai",
            lessonId = lessonId,
            section = ChapterSectionType.MONDAI,
            bookReference = "問題 (Test)",
            type = ExerciseType.WORD_ORDER,
            questionFr = "Test de synthèse 問題 - Reconstituez la phrase type :",
            promptJp = null,
            targetSentenceJp = "毎日 日本語を 勉強します。",
            targetSentenceRomaji = "Mainichi nihongo o benkyoushimasu.",
            options = listOf("毎日", "日本語を", "勉強します", "昨日", "本"),
            correctOptions = listOf("毎日", "日本語を", "勉強します"),
            hint = "Structure : Sujet/Temps + Objet (o) + Verbe.",
            audioText = "まいにち にほんごを べんきょうします。"
        )
    )
}
