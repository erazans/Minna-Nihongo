package com.example.data.datasource

import com.example.data.model.GrammarExample

data class JapaneseParticle(
    val id: String,
    val name: String,
    val romaji: String,
    val meaningFr: String,
    val functionFr: String,
    val lessonReference: String,
    val formula: String,
    val nuancesFr: String,
    val examples: List<GrammarExample>
)

object MinnaParticlesData {

    val allParticles: List<JapaneseParticle> = listOf(
        JapaneseParticle(
            id = "part_wa",
            name = "は",
            romaji = "wa",
            meaningFr = "Particule de Thème (« En ce qui concerne... »)",
            functionFr = "Indique le thème général de la phrase ou ce dont on parle. Remplace les particules が et を quand l'élément devient le thème principal.",
            lessonReference = "Leçon 1 (et Leçon 2, 8, etc.)",
            formula = "[Thème / Sujet abordé] は [Prédicat / Information]",
            nuancesFr = "Prononcée 'wa' bien qu'écrite avec le kana は (ha). Contrairement à が qui focalise sur le sujet précis, は met l'accent sur l'information qui suit.",
            examples = listOf(
                GrammarExample("わたしは マイク・ミラーです。", "Watashi wa Maiku Miraa desu.", "Je suis Mike Miller."),
                GrammarExample("サントスさんは ブラジル人です。", "Santosu-san wa Burajiru-jin desu.", "M. Santos est brésilien."),
                GrammarExample("この 本は わたしのです。", "Kono hon wa watashi no desu.", "Ce livre-ci est à moi.")
            )
        ),
        JapaneseParticle(
            id = "part_ga",
            name = "が",
            romaji = "ga",
            meaningFr = "Sujet réel, Objet d'état / désir, Opposition (« mais »)",
            functionFr = "1. Marque le sujet grammatical précis.\n2. Marque l'objet des verbes d'état (わかります, あります, います) et adjectifs de sentiment/goût/capacité (好き, 嫌い, 上手, 下手, ほしい).\n3. Conjonction d'opposition « mais » en fin de proposition.",
            lessonReference = "Leçons 8, 9, 10, 11, 13",
            formula = "[Sujet] が [Verbe] / [Objet] が あります・好きです / [Phrase 1] が、[Phrase 2]",
            nuancesFr = "Différence は / が : は présente un thème déjà connu ; が introduit un élément nouveau ou insiste sur « qui/quoi » précisément.",
            examples = listOf(
                GrammarExample("わたしは 日本語が わかります。", "Watashi wa nihongo ga wakarimasu.", "Je comprends le japonais."),
                GrammarExample("イタリア料理が 好きです。", "Itaria ryouri ga suki desu.", "J'aime la cuisine italienne."),
                GrammarExample("机の 上に 本が あります。", "Tsukue no ue ni hon ga arimasu.", "Il y a un livre sur le bureau."),
                GrammarExample("日本の 食べ物は おいしいですが、高いです。", "Nihon no tabemono wa oishii desu ga, takai desu.", "La cuisine japonaise est bonne, mais elle est chère.")
            )
        ),
        JapaneseParticle(
            id = "part_o",
            name = "を",
            romaji = "o",
            meaningFr = "Complément d'Objet Direct (COD)",
            functionFr = "Désigne l'objet direct qui reçoit l'action d'un verbe transitif (manger, boire, acheter, lire, etc.). S'emploie aussi avec les verbes de déplacement pour désigner le lieu traversé ou quitté (道を渡る, 電車を降りる).",
            lessonReference = "Leçon 6 (et Leçon 16, 23)",
            formula = "[Objet direct] を [Verbe transitif]",
            nuancesFr = "Prononcée 'o', exclusivement écrite avec le kana を (jamais avec お). Ne s'emploie pas avec les verbes d'état comme あります ou わかります.",
            examples = listOf(
                GrammarExample("ジュースを 飲みます。", "Juusu o nomimasu.", "Je bois du jus de fruit."),
                GrammarExample("パンを 食べます。", "Pan o tabemasu.", "Je mange du pain."),
                GrammarExample("駅で 新聞を 買いました。", "Eki de shinbun o kaimashita.", "J'ai acheté le journal à la gare.")
            )
        ),
        JapaneseParticle(
            id = "part_ni",
            name = "に",
            romaji = "ni",
            meaningFr = "Temps précis, Destination, Lieu d'existence, Destinataire, But",
            functionFr = "Particule polyvalente essentielle :\n1. Moment précis chiffré (heures, dates, jours : 7時に).\n2. Destination du mouvement (学校に 行きます).\n3. Lieu d'existence statique avec あります / います (部屋に 机が あります).\n4. Destinataire d'un don ou contact (友達に 電話を かけます).\n5. But du déplacement (買い物に 行きます).",
            lessonReference = "Leçons 4, 5, 7, 10, 13",
            formula = "[Temps précis] に / [Lieu] に あります・います / [Personne] に あげます / [But] に 行きます",
            nuancesFr = "Différence に / で : に marque la présence statique (où une chose EXISTE) ; で marque le lieu où a lieu une ACTION.",
            examples = listOf(
                GrammarExample("毎朝 6時に 起きます。", "Maiasa roku-ji ni okimasu.", "Chaque matin je me lève à 6 heures."),
                GrammarExample("庭に 犬が います。", "Niwa ni inu ga imasu.", "Il y a un chien dans le jardin."),
                GrammarExample("木村さんに 花を あげました。", "Kimura-san ni hana o agemashita.", "J'ai offert des fleurs à Mme Kimura."),
                GrammarExample("デパートへ 買い物に 行きます。", "Depaato e kaimono ni ikimasu.", "Je vais au grand magasin pour faire des achats.")
            )
        ),
        JapaneseParticle(
            id = "part_de",
            name = "で",
            romaji = "de",
            meaningFr = "Moyen, Outil, Langue, Lieu de l'action, Cause",
            functionFr = "1. Lieu où se déroule une action active.\n2. Moyen de transport (電車で).\n3. Outil ou instrument (はしで 食べます).\n4. Langue de communication (日本語で レポートを 書きます).\n5. Cause ou raison (病気で 休みます).\n6. Totalisation (全部で いくらですか).",
            lessonReference = "Leçons 5, 6, 7, 9, 20",
            formula = "[Lieu d'action / Moyen / Outil / Langue] で [Verbe]",
            nuancesFr = "Exception : « à pied » se dit 歩いて (あるいて) sans la particule で.",
            examples = listOf(
                GrammarExample("レストランで 昼ごはんを 食べました。", "Resutoran de hirugohan o tabemashita.", "J'ai déjeuné au restaurant."),
                GrammarExample("はしで ごはんを 食べます。", "Hashi de gohan o tabemasu.", "Je mange avec des baguettes."),
                GrammarExample("電車で 行きます。", "Densha de ikimasu.", "J'y vais en train."),
                GrammarExample("日本語で 何ですか。", "Nihongo de nan desu ka.", "Comment dit-on cela en japonais ?")
            )
        ),
        JapaneseParticle(
            id = "part_e",
            name = "へ",
            romaji = "e",
            meaningFr = "Direction & Destination du déplacement",
            functionFr = "Indique la direction ou le point d'arrivée vers lequel s'oriente un verbe de déplacement (行きます, 来ます, 帰ります).",
            lessonReference = "Leçon 5",
            formula = "[Lieu / Destination] へ 行きます / 来ます / 帰ります",
            nuancesFr = "Prononcée 'e' (bien qu'écrite avec le kana へ). Très proche de に pour la destination, mais met davantage l'accent sur la trajectoire.",
            examples = listOf(
                GrammarExample("わたしは 京都へ 行きます。", "Watashi wa Kyouto e ikimasu.", "Je vais à Kyoto."),
                GrammarExample("来週 日本へ 来ます。", "Raishuu Nihon e kimasu.", "La semaine prochaine il vient au Japon."),
                GrammarExample("うちへ 帰ります。", "Uchi e kaerimasu.", "Je rentre chez moi.")
            )
        ),
        JapaneseParticle(
            id = "part_to",
            name = "と",
            romaji = "to",
            meaningFr = "Coordination exhaustive (« et »), Accompagnement (« avec »)",
            functionFr = "1. Relie deux noms de manière complète et exhaustive (« A et B »).\n2. Indique la personne avec qui l'on accomplit une action (« avec »).",
            lessonReference = "Leçons 4, 5, 21",
            formula = "[Nom 1] と [Nom 2] / [Personne] と [Verbe]",
            nuancesFr = "Pour exprimer « tout seul », on dit 一人で (ひとりで) sans la particule と.",
            examples = listOf(
                GrammarExample("銀行の 休みは 土曜日と 日曜日です。", "Ginkou no yasumi wa doyoubi to nichiyoubi desu.", "La banque ferme le samedi et le dimanche."),
                GrammarExample("家族と 日本へ 来ました。", "Kazoku to Nihon e kimashita.", "Je suis venu au Japon avec ma famille."),
                GrammarExample("友達と 映画を 見ました。", "Tomodachi to eiga o mimashita.", "J'ai regardé un film avec un ami.")
            )
        ),
        JapaneseParticle(
            id = "part_mo",
            name = "も",
            romaji = "mo",
            meaningFr = "Inclusion (« aussi »), Négation absolue (« aucun / nulle part »)",
            functionFr = "1. Remplace は, が ou を lorsqu'une proposition s'applique également à ce nouveau sujet (« moi aussi », « cela aussi »).\n2. Associée à un mot interrogatif et à un verbe négatif, exprime la négation totale (« nulle part », « rien »).",
            lessonReference = "Leçons 1, 5",
            formula = "[Nouveau Sujet] も [Attribut/Verbe] / [Interrogatif] も [Négatif]",
            nuancesFr = "も remplace directement は et を, mais s'ajoute après に ou で (ex: 京都にも行きます).",
            examples = listOf(
                GrammarExample("ミラーさんは 会社員です。サントスさんも 会社員です。", "Miraa-san wa kaishain desu. Santosu-san mo kaishain desu.", "M. Miller est employé. M. Santos aussi est employé."),
                GrammarExample("きのう どこへも 行きませんでした。", "Kinou doko e mo ikimasen deshita.", "Hier je ne suis allé nulle part."),
                GrammarExample("何も 食べませんでした。", "Nani mo tabemasen deshita.", "Je n'ai rien mangé du tout.")
            )
        ),
        JapaneseParticle(
            id = "part_no",
            name = "の",
            romaji = "no",
            meaningFr = "Appartenance, Origine, Thème, Qualification de nom",
            functionFr = "Connecte deux noms : le premier qualifie, possède ou définit le second (possession, nationalité, affiliation, fabricant, contenu).",
            lessonReference = "Leçons 1, 2, 3",
            formula = "[Possesseur / Origine / Thème] の [Nom qualifié]",
            nuancesFr = "Le second nom peut être sous-entendu lorsqu'il est évident (ex: わたしのです = c'est le mien).",
            examples = listOf(
                GrammarExample("ミラーさんは IMCの 社員です。", "Miraa-san wa IMC no shain desu.", "M. Miller est employé d'IMC."),
                GrammarExample("これは コンピューターの本です。", "Kore wa konpyuutaa no hon desu.", "C'est un livre d'informatique."),
                GrammarExample("これは 日本の 車です。", "Kore wa Nihon no kuruma desu.", "C'est une voiture japonaise.")
            )
        ),
        JapaneseParticle(
            id = "part_kara",
            name = "から",
            romaji = "kara",
            meaningFr = "Point de départ (« de », « depuis »), Cause (« parce que »)",
            functionFr = "1. Indique le point de départ temporel ou spatial (« à partir de », « depuis »).\n2. En fin de proposition, marque la cause ou justification (« parce que... », « comme... donc... »).",
            lessonReference = "Leçons 4, 7, 9",
            formula = "[Heure / Lieu de départ] から / [Proposition de cause] から、[Action]",
            nuancesFr = "Répond souvent à la question どうして (Pourquoi ?).",
            examples = listOf(
                GrammarExample("9時から 働きます。", "Ku-ji kara hatarakimasu.", "Je travaille à partir de 9 heures."),
                GrammarExample("木村さんから チョコレートを もらいました。", "Kimura-san kara chokoreeto o moraimashita.", "J'ai reçu des chocolats de la part de Mme Kimura."),
                GrammarExample("時間が ありませんから、タクシーで 行きます。", "Jikan ga arimasen kara, takushii de ikimasu.", "Comme je n'ai pas le temps, j'y vais en taxi.")
            )
        ),
        JapaneseParticle(
            id = "part_made",
            name = "まで",
            romaji = "made",
            meaningFr = "Limite temporelle ou spatiale (« jusqu'à »)",
            functionFr = "Indique le point d'arrivée ou le moment de fin de l'action.",
            lessonReference = "Leçons 4, 11",
            formula = "[Fin / Limite] まで [Verbe]",
            nuancesFr = "S'associe très souvent avec から dans la structure 〜から 〜まで (de ... à ...).",
            examples = listOf(
                GrammarExample("銀行は 9時から 3時までです。", "Ginkou wa ku-ji kara san-ji made desu.", "La banque est ouverte de 9h à 15h."),
                GrammarExample("毎晩 10時まで 勉強します。", "Maiban juu-ji made benkyoushimasu.", "Chaque soir j'étudie jusqu'à 22 heures.")
            )
        ),
        JapaneseParticle(
            id = "part_ya",
            name = "や",
            romaji = "ya",
            meaningFr = "Énumération non exhaustive (« et entre autres »)",
            functionFr = "Relie des noms en citant deux ou trois exemples sans que la liste soit complète. Peut être complétée par など (« etc. ») après le dernier nom.",
            lessonReference = "Leçon 10",
            formula = "[Nom 1] や [Nom 2] [など]",
            nuancesFr = "Différence と / や : と énumère tous les éléments de façon fermée ; や indique que d'autres objets existent.",
            examples = listOf(
                GrammarExample("箱の 中に 手紙や 写真が あります。", "Hako no naka ni tegami ya shashin ga arimasu.", "Dans la boîte, il y a des lettres et des photos (entre autres)."),
                GrammarExample("店に パンや 卵などが あります。", "Mise ni pan ya tamago nado ga arimasu.", "Dans la boutique, il y a du pain, des œufs, etc.")
            )
        ),
        JapaneseParticle(
            id = "part_ka",
            name = "か",
            romaji = "ka",
            meaningFr = "Interrogation finale, Alternative (« ou bien »)",
            functionFr = "1. Placée en fin de phrase, transforme la phrase en question (équivalent du point d'interrogation).\n2. Entre deux noms, exprime un choix alternatif (« ou »).",
            lessonReference = "Leçons 1, 2, 24",
            formula = "[Proposition] ですか / [Nom A] か [Nom B]",
            nuancesFr = "L'ordre des mots en japonais ne change pas lors de l'interrogation.",
            examples = listOf(
                GrammarExample("ミラーさんは アメリカ人ですか。", "Miraa-san wa Amerikajin desu ka.", "M. Miller est-il américain ?"),
                GrammarExample("「９」ですか、「７」ですか。……「９」です。", "'Kyuu' desu ka, 'nana' desu ka. ...'Kyuu' desu.", "Est-ce un 9 ou un 7 ? ...C'est un 9.")
            )
        ),
        JapaneseParticle(
            id = "part_ne",
            name = "ね",
            romaji = "ne",
            meaningFr = "Particule finale d'accord et empathie (« n'est-ce pas ? »)",
            functionFr = "Placée à la fin de la phrase pour solliciter l'assentiment de l'interlocuteur ou exprimer sa sympathie et bienveillance.",
            lessonReference = "Leçons 4, 8",
            formula = "[Phrase] ね。",
            nuancesFr = "Équivalent du français « n'est-ce pas ? », « dis donc », ou « c'est vrai, hein ».",
            examples = listOf(
                GrammarExample("大変ですね。", "Taihen desu ne.", "C'est difficile, dis donc !"),
                GrammarExample("いい 天気ですね。", "Ii tenki desu ne.", "Il fait beau aujourd'hui, n'est-ce pas ?")
            )
        ),
        JapaneseParticle(
            id = "part_yo",
            name = "よ",
            romaji = "yo",
            meaningFr = "Particule finale informative (« tu sais / je te préviens »)",
            functionFr = "Placée à la fin de la phrase pour transmettre avec conviction une information nouvelle ou mettre en garde l'interlocuteur.",
            lessonReference = "Leçon 5",
            formula = "[Phrase] よ。",
            nuancesFr = "Marque que le locuteur sait quelque chose que l'interlocuteur ignore.",
            examples = listOf(
                GrammarExample("この 電車は 甲子園へ 行きませんよ。", "Kono densha wa Koushien e ikimasen yo.", "Ce train ne va pas à Koshien, vous savez !")
            )
        ),
        JapaneseParticle(
            id = "part_yori",
            name = "より",
            romaji = "yori",
            meaningFr = "Repère de comparaison (« par rapport à », « que »)",
            functionFr = "Sert de référence de comparaison pour exprimer la supériorité ou la différence.",
            lessonReference = "Leçon 12",
            formula = "A は B より [Adjectif] です",
            nuancesFr = "B est le terme comparé (« A est plus [Adj] que B »).",
            examples = listOf(
                GrammarExample("新幹線は 飛行機より 安いです。", "Shinkansen wa hikouki yori yasui desu.", "Le Shinkansen est moins cher que l'avion."),
                GrammarExample("この 本は あの 本より おもしろいです。", "Kono hon wa ano hon yori omoshiroi desu.", "Ce livre est plus intéressant que celui-là.")
            )
        ),
        JapaneseParticle(
            id = "part_dake",
            name = "だけ",
            romaji = "dake",
            meaningFr = "Restriction (« seulement », « uniquement »)",
            functionFr = "Indique une quantité ou un choix limité à ce qui est énoncé, sans nuance négative particulière.",
            lessonReference = "Leçon 11",
            formula = "[Nom / Quantité] だけ",
            nuancesFr = "S'emploie avec des verbes affirmatifs ou négatifs.",
            examples = listOf(
                GrammarExample("休みは 日曜日だけです。", "Yasumi wa nichiyoubi dake desu.", "Le seul jour de congé est le dimanche."),
                GrammarExample("一人だけ 来ました。", "Hitori dake kimashita.", "Une seule personne est venue.")
            )
        ),
        JapaneseParticle(
            id = "part_shika",
            name = "しか",
            romaji = "shika",
            meaningFr = "Restriction exclusive (« ne ... que », avec négation)",
            functionFr = "Indique une restriction forte avec une nuance d'insuffisance ou de regret. S'emploie impérativement avec un verbe à la forme négative.",
            lessonReference = "Leçon 27",
            formula = "[Nom] しか [Verbe négatif]",
            nuancesFr = "« しか + négatif » a le même sens positif que « だけ + affirmatif », mais sous-entend que la quantité est insuffisante.",
            examples = listOf(
                GrammarExample("ひらがなしか わかりません。", "Hiragana shika wakarimasen.", "Je ne comprends rien d'autre que les hiragana."),
                GrammarExample("1,000円しか ありません。", "Sen-en shika arimasen.", "Je n'ai que 1 000 yens (et ce n'est pas assez).")
            )
        )
    )
}
