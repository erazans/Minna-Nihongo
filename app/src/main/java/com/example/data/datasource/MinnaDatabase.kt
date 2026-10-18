package com.example.data.datasource

import com.example.data.model.*

object MinnaDatabase {

    val lessons: List<Lesson> = listOf(
        Lesson(
            id = 1,
            unitNumber = 1,
            unitTitleFr = "Unité 1 : Présentations & Métiers (第１課)",
            titleJp = "第１課",
            titleRomaji = "Dai 1 Ka",
            titleFr = "Présentations & Métiers",
            descriptionFr = "Apprenez à vous présenter, dire votre nationalité, profession et formuler vos premières phrases avec は, です et も.",
            iconEmoji = "🤝",
            wordsCount = 38,
            grammarPointsCount = 6,
            isUnlocked = true,
            isCompleted = false,
            stars = 0
        ),
        Lesson(
            id = 2,
            unitNumber = 2,
            unitTitleFr = "Unité 2 : Objets & Démonstratifs (第２課)",
            titleJp = "第２課",
            titleRomaji = "Dai 2 Ka",
            titleFr = "Objets & Démonstratifs",
            descriptionFr = "Désigner les objets qui vous entourent (ceci, cela, celui-là), poser des questions et exprimer la possession avec の.",
            iconEmoji = "📚",
            wordsCount = 35,
            grammarPointsCount = 5,
            isUnlocked = true,
            isCompleted = false,
            stars = 0
        ),
        Lesson(
            id = 3,
            unitNumber = 3,
            unitTitleFr = "Unité 3 : Lieux & Prix (第３課)",
            titleJp = "第３課",
            titleRomaji = "Dai 3 Ka",
            titleFr = "Lieux & Prix",
            descriptionFr = "Indiquer un lieu (ici, là, là-bas), demander son chemin et demander le prix d'un produit (いくら).",
            iconEmoji = "🏢",
            wordsCount = 38,
            grammarPointsCount = 4,
            isUnlocked = true,
            isCompleted = false,
            stars = 0
        ),
        Lesson(
            id = 4,
            unitNumber = 4,
            unitTitleFr = "Unité 4 : Heure, Jours & Verbes (第４課)",
            titleJp = "第４課",
            titleRomaji = "Dai 4 Ka",
            titleFr = "L'Heure, Jours & Verbes",
            descriptionFr = "Dire l'heure (時, 分), les jours de la semaine, et conjuguer au présent et passé avec 〜ます / 〜ました.",
            iconEmoji = "⏰",
            wordsCount = 42,
            grammarPointsCount = 6,
            isUnlocked = true,
            isCompleted = false,
            stars = 0
        ),
        Lesson(
            id = 5,
            unitNumber = 5,
            unitTitleFr = "Unité 5 : Déplacements & Dates (第５課)",
            titleJp = "第５課",
            titleRomaji = "Dai 5 Ka",
            titleFr = "Déplacements & Dates",
            descriptionFr = "Parler de ses trajets avec へ 行きます, les moyens de transport avec で, et les dates (〜月〜日).",
            iconEmoji = "🚄",
            wordsCount = 40,
            grammarPointsCount = 6,
            isUnlocked = true,
            isCompleted = false,
            stars = 0
        ),
        Lesson(
            id = 6,
            unitNumber = 6,
            unitTitleFr = "Unité 6 : Actions & Invitations (第６課)",
            titleJp = "第６課",
            titleRomaji = "Dai 6 Ka",
            titleFr = "Actions & Invitations",
            descriptionFr = "Exprimer le complément d'objet avec を, le lieu d'action avec で, et inviter quelqu'un avec 〜ませんか.",
            iconEmoji = "🍱",
            wordsCount = 42,
            grammarPointsCount = 5,
            isUnlocked = true,
            isCompleted = false,
            stars = 0
        ),
        Lesson(
            id = 7,
            unitNumber = 7,
            unitTitleFr = "Unité 7 : Moyens, Donner & Recevoir (第７課)",
            titleJp = "第７課",
            titleRomaji = "Dai 7 Ka",
            titleFr = "Moyens, Donner & Recevoir",
            descriptionFr = "Outils (はしで), langues (日本語で), et les verbes d'échange : あげます (donner) et もらいます (recevoir).",
            iconEmoji = "🎁",
            wordsCount = 38,
            grammarPointsCount = 5,
            isUnlocked = true,
            isCompleted = false,
            stars = 0
        ),
        Lesson(
            id = 8,
            unitNumber = 8,
            unitTitleFr = "Unité 8 : Adjectifs en -i et en -na (第８課)",
            titleJp = "第８課",
            titleRomaji = "Dai 8 Ka",
            titleFr = "Adjectifs en -i et en -na",
            descriptionFr = "Décrire le monde, les gens et les objets avec les adjectifs en い et な, leurs formes négatives et intensificateurs.",
            iconEmoji = "🌸",
            wordsCount = 44,
            grammarPointsCount = 7,
            isUnlocked = true,
            isCompleted = false,
            stars = 0
        ),
        Lesson(
            id = 9,
            unitNumber = 9,
            unitTitleFr = "Unité 9 : Goûts, Compétences & Cause (第９課)",
            titleJp = "第９課",
            titleRomaji = "Dai 9 Ka",
            titleFr = "Goûts, Compétences & Cause",
            descriptionFr = "Exprimer ses goûts (好き/嫌い), talents (上手/下手), possessions/compréhensions et expliquer la cause avec 〜から.",
            iconEmoji = "🎨",
            wordsCount = 40,
            grammarPointsCount = 6,
            isUnlocked = true,
            isCompleted = false,
            stars = 0
        ),
        Lesson(
            id = 10,
            unitNumber = 10,
            unitTitleFr = "Unité 10 : Existence & Positions (第１０課)",
            titleJp = "第１０課",
            titleRomaji = "Dai 10 Ka",
            titleFr = "Existence & Position dans l'espace",
            descriptionFr = "Distinguer あります (inanimé) et います (être vivant), et situer les choses (上, 下, 前, 後ろ, 中, 隣, 間).",
            iconEmoji = "📍",
            wordsCount = 38,
            grammarPointsCount = 5,
            isUnlocked = true,
            isCompleted = false,
            stars = 0
        ),
        Lesson(
            id = 11,
            unitNumber = 11,
            unitTitleFr = "Unité 11 : Compteurs & Durées (第１１課)",
            titleJp = "第１１課",
            titleRomaji = "Dai 11 Ka",
            titleFr = "Compteurs & Durées",
            descriptionFr = "Maîtriser les compteurs japonais (ひとつ, 1人, 1枚, 1台), exprimer la durée et la fréquence (1週間に2回).",
            iconEmoji = "🍎",
            wordsCount = 20,
            grammarPointsCount = 4,
            isUnlocked = true,
            isCompleted = false,
            stars = 0
        ),
        Lesson(
            id = 12,
            unitNumber = 12,
            unitTitleFr = "Unité 12 : Passé des Adjectifs & Comparatifs (第１２課)",
            titleJp = "第１２課",
            titleRomaji = "Dai 12 Ka",
            titleFr = "Passé des Adjectifs & Comparatifs",
            descriptionFr = "Parler du passé avec les adjectifs (〜かった, 〜でした) et comparer deux choses (AはBより〜).",
            iconEmoji = "⚖️",
            wordsCount = 20,
            grammarPointsCount = 4,
            isUnlocked = true,
            isCompleted = false,
            stars = 0
        ),
        Lesson(
            id = 13,
            unitNumber = 13,
            unitTitleFr = "Unité 13 : Désirs & But du déplacement (第１３課)",
            titleJp = "第１３課",
            titleRomaji = "Dai 13 Ka",
            titleFr = "Désirs & But du déplacement",
            descriptionFr = "Exprimer ce que l'on veut (欲しい), ce que l'on veut faire (〜たい), et le but d'un déplacement (〜に行きます).",
            iconEmoji = "✨",
            wordsCount = 20,
            grammarPointsCount = 4,
            isUnlocked = true,
            isCompleted = false,
            stars = 0
        ),
        Lesson(
            id = 14,
            unitNumber = 14,
            unitTitleFr = "Unité 14 : Forme en -Te & Requêtes (第１４課)",
            titleJp = "第１４課",
            titleRomaji = "Dai 14 Ka",
            titleFr = "Groupes de Verbes & Forme en -Te",
            descriptionFr = "Le pivot fondamental du japonais : les 3 groupes de verbes, la conjugaison en -Te, les demandes (〜てください) et l'action en cours.",
            iconEmoji = "🧩",
            wordsCount = 25,
            grammarPointsCount = 5,
            isUnlocked = true,
            isCompleted = false,
            stars = 0
        ),
        Lesson(
            id = 15,
            unitNumber = 15,
            unitTitleFr = "Unité 15 : Permission, Interdiction & État (第１５課)",
            titleJp = "第１５課",
            titleRomaji = "Dai 15 Ka",
            titleFr = "Permission, Interdiction & État",
            descriptionFr = "Demander la permission (〜てもいいですか), interdire (〜てはいけません), et décrire un état établi (住んでいます, 知っています).",
            iconEmoji = "🚫",
            wordsCount = 20,
            grammarPointsCount = 4,
            isUnlocked = true,
            isCompleted = false,
            stars = 0
        ),
        Lesson(
            id = 16,
            unitNumber = 16,
            unitTitleFr = "Unité 16 : Enchaînements & -Te kara (第１６課)",
            titleJp = "第１６課",
            titleRomaji = "Dai 16 Ka",
            titleFr = "Enchaînement d'actions & -Te kara",
            descriptionFr = "Enchaîner plusieurs actions chronologiques (〜て、〜て), après avoir fait (〜てから) et relier des adjectifs (〜くて, 〜で).",
            iconEmoji = "🔗",
            wordsCount = 22,
            grammarPointsCount = 4,
            isUnlocked = true,
            isCompleted = false,
            stars = 0
        ),
        Lesson(
            id = 17,
            unitNumber = 17,
            unitTitleFr = "Unité 17 : Forme en -Nai & Obligations (第１７課)",
            titleJp = "第１７課",
            titleRomaji = "Dai 17 Ka",
            titleFr = "La Forme en -Nai & Obligations",
            descriptionFr = "Construire la forme en -Nai (négatif neutre), demander de ne pas faire (〜ないでください) et exprimer l'obligation (〜なければなりません).",
            iconEmoji = "⚠️",
            wordsCount = 24,
            grammarPointsCount = 4,
            isUnlocked = true,
            isCompleted = false,
            stars = 0
        ),
        Lesson(
            id = 18,
            unitNumber = 18,
            unitTitleFr = "Unité 18 : Forme Dictionnaire & Capacité (第１８課)",
            titleJp = "第１８課",
            titleRomaji = "Dai 18 Ka",
            titleFr = "Forme Dictionnaire & Capacité",
            descriptionFr = "La forme du dictionnaire (Jishokei), exprimer ce que l'on sait faire (〜ことができます), parler de ses passions et avant de faire (〜前に).",
            iconEmoji = "📖",
            wordsCount = 22,
            grammarPointsCount = 4,
            isUnlocked = true,
            isCompleted = false,
            stars = 0
        ),
        Lesson(
            id = 19,
            unitNumber = 19,
            unitTitleFr = "Unité 19 : Forme en -Ta & Expériences (第１９課)",
            titleJp = "第１９課",
            titleRomaji = "Dai 19 Ka",
            titleFr = "Forme en -Ta & Expériences",
            descriptionFr = "La forme passée neutre en -Ta, exprimer une expérience passée (〜たことがあります), énumérer des actions (〜たり〜たり) et devenir (〜になります).",
            iconEmoji = "⭐",
            wordsCount = 22,
            grammarPointsCount = 4,
            isUnlocked = true,
            isCompleted = false,
            stars = 0
        ),
        Lesson(
            id = 20,
            unitNumber = 20,
            unitTitleFr = "Unité 20 : Style Familier / Neutre (第２０課)",
            titleJp = "第２０課",
            titleRomaji = "Dai 20 Ka",
            titleFr = "Style Neutre / Familier (Futsuukei)",
            descriptionFr = "Parler comme les Japonais entre amis et en famille : tableau complet des formes neutres et intonations de conversation.",
            iconEmoji = "💬",
            wordsCount = 24,
            grammarPointsCount = 3,
            isUnlocked = true,
            isCompleted = false,
            stars = 0
        ),
        Lesson(
            id = 21,
            unitNumber = 21,
            unitTitleFr = "Unité 21 : Opinions & Citations (第２１課)",
            titleJp = "第２１課",
            titleRomaji = "Dai 21 Ka",
            titleFr = "Opinions & Citations",
            descriptionFr = "Exprimer son opinion personnelle (〜と思います), rapporter les paroles de quelqu'un (〜と言いました) et confirmer avec 〜でしょう.",
            iconEmoji = "💭",
            wordsCount = 20,
            grammarPointsCount = 4,
            isUnlocked = true,
            isCompleted = false,
            stars = 0
        ),
        Lesson(
            id = 22,
            unitNumber = 22,
            unitTitleFr = "Unité 22 : Propositions Relatives (第２２課)",
            titleJp = "第２２課",
            titleRomaji = "Dai 22 Ka",
            titleFr = "Propositions Relatives",
            descriptionFr = "Qualifier un nom par une phrase complète (ex: le gâteau que Miller a préparé, l'homme qui porte des lunettes).",
            iconEmoji = "👔",
            wordsCount = 22,
            grammarPointsCount = 3,
            isUnlocked = true,
            isCompleted = false,
            stars = 0
        ),
        Lesson(
            id = 23,
            unitNumber = 23,
            unitTitleFr = "Unité 23 : Quand & Conséquence (第２３課)",
            titleJp = "第２３課",
            titleRomaji = "Dai 23 Ka",
            titleFr = "Quand (~toki) & Conséquence (~to)",
            descriptionFr = "Exprimer le moment où se déroule une action (〜とき) et les actions automatiques / guidage routier (ボタンを押すと...).",
            iconEmoji = "🚦",
            wordsCount = 20,
            grammarPointsCount = 3,
            isUnlocked = true,
            isCompleted = false,
            stars = 0
        ),
        Lesson(
            id = 24,
            unitNumber = 24,
            unitTitleFr = "Unité 24 : Donner/Recevoir des Services (第２４課)",
            titleJp = "第２４課",
            titleRomaji = "Dai 24 Ka",
            titleFr = "Donner et Recevoir des Services",
            descriptionFr = "Rendre service ou bénéficier de la gentillesse des autres : 〜てあげます, 〜てもらいます, 〜てくれます.",
            iconEmoji = "🤲",
            wordsCount = 20,
            grammarPointsCount = 4,
            isUnlocked = true,
            isCompleted = false,
            stars = 0
        ),
        Lesson(
            id = 25,
            unitNumber = 25,
            unitTitleFr = "Unité 25 : Conditionnels ~tara & ~temo (第２５課)",
            titleJp = "第２５課",
            titleRomaji = "Dai 25 Ka",
            titleFr = "Conditionnel & Concession (~tara / ~temo)",
            descriptionFr = "La conclusion du manuel I : 'si/quand' avec 〜たら et 'même si' avec 〜ても pour converser avec nuance.",
            iconEmoji = "🎓",
            wordsCount = 20,
            grammarPointsCount = 3,
            isUnlocked = true,
            isCompleted = false,
            stars = 0
        )
    )

    // Complete vocabulary items cross-referenced across lessons from MinnaVocabularyData
    val vocabulary: List<VocabularyWord> get() = MinnaVocabularyData.allVocabulary

    // Complete grammar points for lessons with formulas and Minna examples from MinnaGrammarData
    val grammarPoints: List<GrammarPoint> get() = MinnaGrammarData.allGrammarPoints

    // Authentic dialogues from textbook Renshuu C & Kaiwa
    val dialogues: List<LessonDialogue> = listOf(
        LessonDialogue(
            id = "d_1",
            lessonId = 1,
            title = "初めまして",
            titleFr = "Première rencontre au bureau IMC",
            settingDescription = "M. Yamada présente Mike Miller à Mme Sato dans les locaux de l'entreprise IMC.",
            lines = listOf(
                DialogueLine("佐藤 (Satou)", "👩", "おはようございます。", "Ohayou gozaimasu.", "Bonjour (matin)."),
                DialogueLine("山田 (Yamada)", "👨‍💼", "おはようございます。佐藤さん、こちらは マイク・ミラーさんです。", "Ohayou gozaimasu. Satou-san, kochira wa Maiku Miraa-san desu.", "Bonjour. Mme Sato, voici M. Mike Miller."),
                DialogueLine("ミラー (Miller)", "👱‍♂️", "初めまして。マイク・ミラーです。アメリカから 来ました。どうぞ よろしく。", "Hajimemashite. Maiku Miraa desu. Amerika kara kimashita. Douzo yoroshiku.", "Enchanté. Je suis Mike Miller. Je viens des États-Unis. Ravi de faire votre connaissance.", isUserRole = true),
                DialogueLine("佐藤 (Satou)", "👩", "佐藤けい子です。どうぞ よろしく。", "Satou Keiko desu. Douzo yoroshiku.", "Je suis Keiko Sato. Ravie de faire votre connaissance également.")
            )
        ),
        LessonDialogue(
            id = "d_2",
            lessonId = 2,
            title = "これから お世話に なります",
            titleFr = "Cadeau de bienvenue entre voisins",
            settingDescription = "M. Santos emménage dans son appartement et rend visite à son voisin M. Yamada.",
            lines = listOf(
                DialogueLine("山田 (Yamada)", "👨‍💼", "はい。どなたですか。", "Hai. Donata desu ka.", "Oui ? Qui est-ce ?"),
                DialogueLine("サントス (Santos)", "🧔", "４０８の サントスです。こんにちは。これから おせわになります。", "Yon-maru-hachi no Santosu desu. Konnichiwa. Korekara osewa ni narimasu.", "Je suis Santos du 408. Bonjour. Je me réjouis de nos bonnes relations de voisinage.", isUserRole = true),
                DialogueLine("山田 (Yamada)", "👨‍💼", "こちらこそ よろしくお願いします。", "Kochira koso yoroshiku onegaishimasu.", "Moi de même, soyez le bienvenu."),
                DialogueLine("サントス (Santos)", "🧔", "あのう、これ、コーヒーです。どうぞ。", "Anou, kore, koohii desu. Douzo.", "Euh, ceci est du café. Je vous en prie.", isUserRole = true),
                DialogueLine("山田 (Yamada)", "👨‍💼", "どうも ありがとうございます。", "Doumo arigatou gozaimasu.", "Merci infiniment !")
            )
        ),
        LessonDialogue(
            id = "d_3",
            lessonId = 3,
            title = "これを ください",
            titleFr = "Acheter du vin au grand magasin",
            settingDescription = "Maria Santos fait ses courses au sous-sol d'un grand magasin japonais.",
            lines = listOf(
                DialogueLine("店員 (Vendeur)", "🧑‍🍳", "いらっしゃいませ。", "Irasshaimase.", "Bienvenue dans notre boutique !"),
                DialogueLine("マリア (Maria)", "👩", "すみません。ワインうりばは どこですか。", "Sumimasen. Wain uriba wa doko desu ka.", "Excusez-moi. Où se trouve le rayon vins ?", isUserRole = true),
                DialogueLine("店員 (Vendeur)", "🧑‍🍳", "ちか１かいです。", "Chika ikkai desu.", "C'est au premier sous-sol."),
                DialogueLine("マリア (Maria)", "👩", "これは どこの ワインですか。いくらですか。", "Kore wa doko no wain desu ka. Ikura desu ka.", "D'où vient ce vin ? Combien coûte-t-il ?", isUserRole = true),
                DialogueLine("店員 (Vendeur)", "🧑‍🍳", "にほんのです。２,５００えんです。", "Nihon no desu. Ni-sen gohyaku-en desu.", "C'est un vin japonais. Il coûte 2 500 yens."),
                DialogueLine("マリア (Maria)", "👩", "じゃ、これを ください。", "Ja, kore o kudasai.", "Dans ce cas, je le prends, s'il vous plaît.", isUserRole = true)
            )
        )
    )

    // Preset roleplay AI scenarios
    val roleplayScenarios: List<RoleplayScenario> = listOf(
        RoleplayScenario(
            id = "sensei_roleplay",
            title = "Sakura Sensei",
            titleFr = "Tuteur Privé de Japonais",
            characterName = "さくら先生 (Sakura Sensei)",
            characterEmoji = "👩‍🏫",
            settingFr = "Cours particulier bienveillant : entraînez-vous à parler japonais, posez des questions sur le vocabulaire ou la grammaire des 25 leçons.",
            systemPrompt = "Tu es Sakura Sensei, une professeure de japonais chaleureuse, pédagogue et encourageante, qui enseigne selon la méthode Minna no Nihongo (leçons 1 à 25). Réponds toujours d'abord en japonais naturel adapté aux débutants (avec kana et furigana/romaji), puis fournis une brève traduction en français et un conseil grammatical ou d'amélioration si l'utilisateur fait une faute.",
            initialAiMessageJp = "こんにちは！さくら先生です。きょうは いっしょに 日本語を べんきょうしましょう。おなまえは 何ですか？",
            initialAiMessageRomaji = "Konnichiwa! Sakura-sensei desu. Kyou wa issho ni nihongo o benkyoushimashou. Onamae wa nan desu ka?",
            initialAiMessageFr = "Bonjour ! Je suis Sakura Sensei. Aujourd'hui, étudions le japonais ensemble. Quel est votre nom ?",
            suggestions = listOf(
                "初めまして、わたしは [Nom] です。(Hajimemashite...)",
                "先生、こんにちは！(Sensei, konnichiwa!)",
                "きょうは 天気が いいですね。(Kyou wa tenki ga ii desu ne.)"
            )
        ),
        RoleplayScenario(
            id = "restaurant_roleplay",
            title = "Restaurant Japonais",
            titleFr = "Commander au Restaurant 'Tsuruya'",
            characterName = "店員 (Serveur de chez Tsuruya)",
            characterEmoji = "🍱",
            settingFr = "Vous entrez dans un restaurant traditionnel japonais à Osaka (comme dans la leçon 13). Commandez à manger et demandez l'addition.",
            systemPrompt = "Tu joues le rôle du serveur attentionné dans un restaurant japonais traditionnel (Tsuruya). Tu utilises les formules de politesse de Minna no Nihongo (いらっしゃいませ, ご注文は？, ありがとうございます). Réponds en japonais simple avec romaji et traduction française.",
            initialAiMessageJp = "いらっしゃいませ！何名様ですか？こちらの 席へ どうぞ。ご注文は 何に なさいますか？",
            initialAiMessageRomaji = "Irasshaimase! Nanmei-sama desu ka? Kochira no seki e douzo. Gochuumon wa nan ni nasaimasu ka?",
            initialAiMessageFr = "Bienvenue ! Combien de personnes êtes-vous ? Installez-vous ici s'il vous plaît. Que souhaitez-vous commander ?",
            suggestions = listOf(
                "てんぷら定食を ください。(Tenpura teishoku o kudasai.)",
                "おすすめは 何ですか。(Osusume wa nan desu ka.)",
                "お水と ビールを お願いします。(Omizu to biiru o onegaishimasu.)"
            )
        ),
        RoleplayScenario(
            id = "imc_miller_roleplay",
            title = "Bureau IMC avec Miller",
            titleFr = "Discuter avec Mike Miller",
            characterName = "マイク・ミラー (Mike Miller)",
            characterEmoji = "👱‍♂️",
            settingFr = "Vous êtes un nouveau collègue chez IMC Software. Échangez sur votre emploi du temps, vos projets du week-end et la vie au Japon.",
            systemPrompt = "Tu es Mike Miller, le personnage principal de Minna no Nihongo, un américain sympathique travaillant chez IMC à Osaka. Tu t'exprimes avec le japonais poli et clair des leçons 1 à 25. Inclus romaji et traduction française.",
            initialAiMessageJp = "あ、お疲れ様です！ミラーです。今週末は どこかへ 行きますか？",
            initialAiMessageRomaji = "A, otsukaresama desu! Miraa desu. Konshuumatsu wa dokoka e ikimasu ka?",
            initialAiMessageFr = "Ah, salut collègue ! Je suis Miller. Tu vas quelque part ce week-end ?",
            suggestions = listOf(
                "京都へ お寺を 見に 行きます。(Kyouto e otera o mini ikimasu.)",
                "うちで ゆっくり 休みたいです。(Uchi de yukkuri yasumitai desu.)",
                "ミラーさんは 何を しますか。(Miraa-san wa nani o shimasu ka.)"
            )
        )
    )
}
