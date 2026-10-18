package com.example.data.datasource

import com.example.data.model.GrammarExample
import com.example.data.model.GrammarPoint

object MinnaGrammarData {

    val allGrammarPoints: List<GrammarPoint> by lazy {
        lesson1Grammar +
        lesson2Grammar +
        lesson3Grammar +
        lesson4Grammar +
        lesson5Grammar +
        lesson6Grammar +
        lesson7Grammar +
        lesson8Grammar +
        lesson9Grammar +
        lesson10Grammar +
        laterLessonsGrammar
    }

    // ==========================================
    // LEÇON 1 / UNITÉ 1
    // ==========================================
    val lesson1Grammar = listOf(
        GrammarPoint(
            id = "g_1_1",
            lessonId = 1,
            pattern = "N1 は N2 です",
            meaningFr = "N1 est N2",
            explanation = "は (prononcé 'wa') est la particule du thème. Elle indique ce dont on parle (« en ce qui concerne N1 »). です exprime l'affirmation polie ('être').",
            formula = "[Thème/Sujet] は [Nom/Attribut] です",
            examples = listOf(
                GrammarExample("わたしは マイク・ミラーです。", "Watashi wa Maiku Miraa desu.", "Je suis Mike Miller."),
                GrammarExample("サントスさんは ブラジルじんです。", "Santosu-san wa Burajiru-jin desu.", "M. Santos est brésilien.")
            )
        ),
        GrammarPoint(
            id = "g_1_2",
            lessonId = 1,
            pattern = "N1 は N2 じゃ ありません / では ありません",
            meaningFr = "N1 n'est pas N2 (Négation polie)",
            explanation = "じゃ ありません est la forme négative polie de です dans la conversation quotidienne. では ありません est la version plus formelle (discours ou écrit).",
            formula = "[Thème] は [Attribut] じゃ ありません",
            examples = listOf(
                GrammarExample("サントスさんは がくせいじゃ ありません。", "Santosu-san wa gakusei ja arimasen.", "M. Santos n'est pas étudiant."),
                GrammarExample("ワンさんは にほんじんじゃ ありません。", "Wan-san wa nihonjin ja arimasen.", "M. Wang n'est pas japonais.")
            )
        ),
        GrammarPoint(
            id = "g_1_3",
            lessonId = 1,
            pattern = "Phrase + ですか (Particule interrogative)",
            meaningFr = "Est-ce que... ? / Est-ce vrai ?",
            explanation = "か est la particule interrogative placée à la fin de la phrase. Elle correspond au point d'interrogation. L'ordre des mots ne change pas.",
            formula = "[Proposition] ですか。",
            examples = listOf(
                GrammarExample("ミラーさんは かいしゃいんですか。", "Miraa-san wa kaishain desu ka.", "M. Miller est-il employé d'entreprise ?"),
                GrammarExample("はい、かいしゃいんです。", "Hai, kaishain desu.", "Oui, il est employé d'entreprise."),
                GrammarExample("いいえ、かいしゃいんじゃ ありません。", "Iie, kaishain ja arimasen.", "Non, il n'est pas employé d'entreprise.")
            )
        ),
        GrammarPoint(
            id = "g_1_4",
            lessonId = 1,
            pattern = "N も (Particule 'aussi')",
            meaningFr = "N aussi / N également",
            explanation = "も remplace la particule は lorsqu'une proposition identique s'applique également à ce nouveau sujet.",
            formula = "[Nouveau Sujet] も [Attribut] です",
            examples = listOf(
                GrammarExample("ミラーさんは かいしゃいんです。サントスさんも かいしゃいんです。", "Miraa-san wa kaishain desu. Santosu-san mo kaishain desu.", "M. Miller est employé. M. Santos aussi est employé."),
                GrammarExample("わたしも フランスじんです。", "Watashi mo Furansujin desu.", "Moi aussi je suis français.")
            )
        ),
        GrammarPoint(
            id = "g_1_5",
            lessonId = 1,
            pattern = "N1 の N2 (Appartenance & Affiliation)",
            meaningFr = "Le N2 de N1 / N2 lié à N1",
            explanation = "の connecte deux noms en apportant une précision sur le second (possession, affiliation d'entreprise, nationalité, etc.).",
            formula = "[Groupe / Possesseur] の [Élément]",
            examples = listOf(
                GrammarExample("ミラーさんは IMCの しゃいんです。", "Miraa-san wa IMC no shain desu.", "M. Miller est un employé de la société IMC."),
                GrammarExample("神戸病院の 研究者です。", "Koube byouin no kenkyuusha desu.", "C'est un chercheur de l'hôpital de Kobe.")
            )
        ),
        GrammarPoint(
            id = "g_1_6",
            lessonId = 1,
            pattern = "Suffixe 〜さん (Politesse avec autrui)",
            meaningFr = "Monsieur / Madame / Mademoiselle",
            explanation = "Suffixe honorifique d'égal à égal ou supérieur, rattaché au nom ou prénom de son interlocuteur. Attention : on ne l'utilise JAMAIS pour son propre nom.",
            formula = "[Nom de la personne] + さん",
            examples = listOf(
                GrammarExample("あの方は 木村さんです。", "Ano kata wa Kimura-san desu.", "Cette personne là-bas est Mme Kimura.")
            )
        )
    )

    // ==========================================
    // LEÇON 2 / UNITÉ 2
    // ==========================================
    val lesson2Grammar = listOf(
        GrammarPoint(
            id = "g_2_1",
            lessonId = 2,
            pattern = "これ / それ / あれ は N です",
            meaningFr = "Ceci / Cela / Cela là-bas est N (Démonstratifs de choses)",
            explanation = "これ désigne un objet proche du locuteur, それ un objet proche de l'interlocuteur, et あれ un objet éloigné des deux interlocuteurs.",
            formula = "[これ / それ / あれ] は [Nom] です",
            examples = listOf(
                GrammarExample("これは じしょです。", "Kore wa jisho desu.", "Ceci est un dictionnaire."),
                GrammarExample("それは わたしのかさです。", "Sore wa watashi no kasa desu.", "Cela est mon parapluie."),
                GrammarExample("あれは テレビです。", "Are wa terebi desu.", "Cela là-bas est une télévision.")
            )
        ),
        GrammarPoint(
            id = "g_2_2",
            lessonId = 2,
            pattern = "この N / その N / あの N",
            meaningFr = "Ce N-ci / Ce N-là / Ce N là-bas (Déterminants)",
            explanation = "Différence clé avec これ : この, その, あの doivent impérativement être immédiatement suivis d'un nom qu'ils qualifient.",
            formula = "[この / その / あの] + [Nom] は ...",
            examples = listOf(
                GrammarExample("この 本は わたしのです。", "Kono hon wa watashi no desu.", "Ce livre-ci est à moi."),
                GrammarExample("その かぎは だれのですか。", "Sono kagi wa dare no desu ka.", "À qui est cette clé-là ?"),
                GrammarExample("あの 方は どなたですか。", "Ano kata wa donata desu ka.", "Qui est cette personne là-bas ? (très poli)")
            )
        ),
        GrammarPoint(
            id = "g_2_3",
            lessonId = 2,
            pattern = "そう です / そうじゃ ありません (Confirmation)",
            meaningFr = "C'est exact / Ce n'est pas le cas",
            explanation = "Dans une question nominale demandant si une affirmation est exacte, on utilise そう です (oui c'est cela) ou そうじゃ ありません / ちがいます (ce n'est pas cela).",
            formula = "はい、そうです。 / いいえ、ちがいます。",
            examples = listOf(
                GrammarExample("それは テレホンカードですか。……はい、そうです。", "Sore wa terehonkaado desu ka. ...Hai, sou desu.", "Est-ce une carte téléphonique ? ...Oui, c'est bien cela."),
                GrammarExample("これは ミラーさんのですか。……いいえ、ちがいます。", "Kore wa Miraa-san no desu ka. ...Iie, chigaimasu.", "Est-ce à M. Miller ? ...Non, ce n'est pas le cas.")
            )
        ),
        GrammarPoint(
            id = "g_2_4",
            lessonId = 2,
            pattern = "Phrase 1 ですか、Phrase 2 ですか (Alternative)",
            meaningFr = "Est-ce A ou bien B ?",
            explanation = "Question posant un choix alternatif. L'interlocuteur ne répond ni par 'oui' ni par 'non', mais énonce directement le choix retenu.",
            formula = "[Option A] ですか、[Option B] ですか。",
            examples = listOf(
                GrammarExample("これは 「９」ですか、「７」ですか。……「９」です。", "Kore wa 'kyuu' desu ka, 'nana' desu ka. ...'Kyuu' desu.", "Est-ce un 9 ou un 7 ? ...C'est un 9.")
            )
        ),
        GrammarPoint(
            id = "g_2_5",
            lessonId = 2,
            pattern = "N1 の N2 (Contenu, Thème & Possession)",
            meaningFr = "Livre SUR N1 / L'objet DE N1",
            explanation = "の peut préciser le thème traité par l'objet (ex: livre sur les voitures) ou le possesseur (ex: sac de Miller). N2 peut être omis s'il est évident (わたしのです = c'est le mien).",
            formula = "[Sujet / Propriétaire] の [Élément]",
            examples = listOf(
                GrammarExample("これは コンピューターの本です。", "Kore wa konpyuutaa no hon desu.", "Ceci est un livre sur les ordinateurs."),
                GrammarExample("この かばんは だれのですか。……佐藤さんのです。", "Kono kaban wa dare no desu ka. ...Satou-san no desu.", "À qui appartient ce sac ? ...C'est celui de Mme Sato.")
            )
        )
    )

    // ==========================================
    // LEÇON 3 / UNITÉ 3
    // ==========================================
    val lesson3Grammar = listOf(
        GrammarPoint(
            id = "g_3_1",
            lessonId = 3,
            pattern = "ここ / そこ / あそこ / どこ (Désignation de lieux)",
            meaningFr = "Ici / Là / Là-bas / Où",
            explanation = "Désignation de l'espace : ここ (proche du locuteur), そこ (proche de l'interlocuteur), あそこ (éloigné des deux), どこ (lieu interrogatif : où).",
            formula = "[Lieu/Personne] は [ここ / そこ / あそこ / どこ] です",
            examples = listOf(
                GrammarExample("ここは きょうしつです。", "Koko wa kyoushitsu desu.", "Ici, c'est la salle de classe."),
                GrammarExample("お手洗いは あそこです。", "Otearai wa asoko desu.", "Les toilettes se trouvent là-bas."),
                GrammarExample("事務所は どこですか。……あそこです。", "Jimusho wa doko desu ka. ...Asoko desu.", "Où est le bureau ? ...C'est là-bas.")
            )
        ),
        GrammarPoint(
            id = "g_3_2",
            lessonId = 3,
            pattern = "こちら / そちら / あちら / どちら (Direction & Politesse)",
            meaningFr = "Par ici / Par là / De quel côté / Quelle société ou pays (Poli)",
            explanation = "Formes de politesse supérieure pour désigner une direction ou un lieu, mais aussi pour s'enquérir avec déférence du pays d'origine ou de la société de quelqu'un.",
            formula = "[Lieu / Entité] は [どちら] ですか",
            examples = listOf(
                GrammarExample("エレベーターは どちらですか。……あちらです。", "Erebeetaa wa dochira desu ka. ...Achira desu.", "De quel côté se trouve l'ascenseur ? ...Par là-bas."),
                GrammarExample("お国は どちらですか。……アメリカです。", "Okuni wa dochira desu ka. ...Amerika desu.", "De quel pays venez-vous ? (poli) ...Des États-Unis."),
                GrammarExample("会社は どちらですか。……IMCです。", "Kaisha wa dochira desu ka. ...IMC desu.", "Pour quelle entreprise travaillez-vous ? ...Pour IMC.")
            )
        ),
        GrammarPoint(
            id = "g_3_3",
            lessonId = 3,
            pattern = "N1 の N2 (Origine / Fabricant d'un produit)",
            meaningFr = "N2 fabriqué en N1 / par la marque N1",
            explanation = "Quand N1 est un pays ou une entreprise, の indique la provenance ou la marque du produit N2.",
            formula = "[Pays / Fabricant] の [Produit]",
            examples = listOf(
                GrammarExample("これは どこの ワインですか。……フランスの ワインです。", "Kore wa doko no wain desu ka. ...Furansu no wain desu.", "D'où vient ce vin ? ...C'est un vin français."),
                GrammarExample("これは 日本の 車です。", "Kore wa Nihon no kuruma desu.", "C'est une voiture japonaise.")
            )
        ),
        GrammarPoint(
            id = "g_3_4",
            lessonId = 3,
            pattern = "N は いくらですか (Demander le prix)",
            meaningFr = "Combien coûte N ?",
            explanation = "いくら est l'interrogatif de prix. Le prix s'énonce en yens avec le compteur 円 (えん).",
            formula = "[Article] は いくらですか。……[Montant] えんです。",
            examples = listOf(
                GrammarExample("この ネクタイは いくらですか。……1,500円です。", "Kono nekutai wa ikura desu ka. ...Sen gohyaku-en desu.", "Combien coûte cette cravate ? ...Elle coûte 1 500 yens."),
                GrammarExample("全部で いくらですか。", "Zenbu de ikura desu ka.", "Combien cela fait-il au total ?")
            )
        )
    )

    // ==========================================
    // LEÇON 4 / UNITÉ 4
    // ==========================================
    val lesson4Grammar = listOf(
        GrammarPoint(
            id = "g_4_1",
            lessonId = 4,
            pattern = "今 〜時 〜分 です (L'heure et les minutes)",
            meaningFr = "Il est actuellement [Heure] h [Minutes]",
            explanation = "Suffixe 時 (じ) pour les heures, 分 (ふん/ぷん) pour les minutes. 半 (はん) exprime la demi-heure (ex. 7時半 = 7h30).",
            formula = "いま [Chiffre] じ [Chiffre] ふん です",
            examples = listOf(
                GrammarExample("いま 何時ですか。……7時15分です。", "Ima nan-ji desu ka. ...Shichi-ji juu go-fun desu.", "Quelle heure est-il maintenant ? ...Il est 7h15."),
                GrammarExample("ニューヨークは 今 何時ですか。……午前4時です。", "Nyuuyooku wa ima nan-ji desu ka. ...Gozen yo-ji desu.", "Quelle heure est-il à New York ? ...Il est 4h du matin.")
            )
        ),
        GrammarPoint(
            id = "g_4_2",
            lessonId = 4,
            pattern = "Verbe ます / ません / ました / ませんでした",
            meaningFr = "Formes temporelles polies des verbes",
            explanation = "ます (présent/futur affirmatif), ません (négatif), ました (passé affirmatif), ませんでした (passé négatif).",
            formula = "[Radical] + ます / ません / ました / ませんでした",
            examples = listOf(
                GrammarExample("毎朝 6時に 起きます。", "Maiasa roku-ji ni okimasu.", "Chaque matin je me lève à 6 heures."),
                GrammarExample("きのう 勉強しました。", "Kinou benkyoushimashita.", "Hier j'ai étudié."),
                GrammarExample("あした 働きません。", "Ashita hatarakimasen.", "Demain je ne travaillerai pas."),
                GrammarExample("おととい 勉強しませんでした。", "Ototoi benkyoushimasen deshita.", "Avant-hier je n'ai pas étudié.")
            )
        ),
        GrammarPoint(
            id = "g_4_3",
            lessonId = 4,
            pattern = "[Temps précis] に Verbe (Particule de temps)",
            meaningFr = "À [telle heure / tel jour]",
            explanation = "On ajoute に après les repères temporels comportant un chiffre (heures, dates) et les jours de la semaine. On n'utilise PAS de に après きょう, あした, まいにち.",
            formula = "[Heure / Jour / Date] に [Verbe]",
            examples = listOf(
                GrammarExample("毎晩 11時に 寝ます。", "Maiban juuichi-ji ni nemasu.", "Tous les soirs je me couche à 23 heures."),
                GrammarExample("日曜日に 奈良へ 行きます。", "Nichiyoubi ni Nara e ikimasu.", "Dimanche je vais à Nara.")
            )
        ),
        GrammarPoint(
            id = "g_4_4",
            lessonId = 4,
            pattern = "[Départ] から [Arrivée] まで (De... à...)",
            meaningFr = "De [Heure/Lieu] à [Heure/Lieu]",
            explanation = "から indique le point de départ temporel ou spatial, まで indique la limite ou fin de l'action.",
            formula = "[Départ] から [Fin] まで [Verbe / です]",
            examples = listOf(
                GrammarExample("銀行は 9時から 3時までです。", "Ginkou wa ku-ji kara san-ji made desu.", "La banque est ouverte de 9h à 15h."),
                GrammarExample("昼休みは 12時からです。", "Hiruyasumi wa juuni-ji kara desu.", "La pause de midi commence à 12h.")
            )
        ),
        GrammarPoint(
            id = "g_4_5",
            lessonId = 4,
            pattern = "N1 と N2 (Coordination 'et')",
            meaningFr = "N1 et N2 (Énumération exhaustive)",
            explanation = "La particule と relie deux noms de façon limitative et complète.",
            formula = "[Nom 1] と [Nom 2]",
            examples = listOf(
                GrammarExample("銀行の 休みは 土曜日と 日曜日です。", "Ginkou no yasumi wa doyoubi to nichiyoubi desu.", "Les jours de congé de la banque sont le samedi et le dimanche.")
            )
        ),
        GrammarPoint(
            id = "g_4_6",
            lessonId = 4,
            pattern = "Phrase + ね (Recherche d'assentiment)",
            meaningFr = "N'est-ce pas ? / En effet",
            explanation = "Particule finale sollicitant l'accord de l'interlocuteur ou manifestant de la compassion.",
            formula = "[Phrase] ね。",
            examples = listOf(
                GrammarExample("大変ですね。", "Taihen desu ne.", "C'est difficile, dis donc !"),
                GrammarExample("毎晩 10時まで 勉強します。……大変ですね。", "Maiban juu-ji made benkyoushimasu. ...Taihen desu ne.", "J'étudie tous les soirs jusqu'à 22h. ...Quel courage !")
            )
        )
    )

    // ==========================================
    // LEÇON 5 / UNITÉ 5
    // ==========================================
    val lesson5Grammar = listOf(
        GrammarPoint(
            id = "g_5_1",
            lessonId = 5,
            pattern = "[Lieu] へ 行きます / 来ます / 帰ります (Destination)",
            meaningFr = "Aller / Venir / Rentrer à [Lieu]",
            explanation = "へ (se prononce 'e') indique la direction ou le point d'arrivée du mouvement avec 行きます (aller), 来ます (venir), 帰ります (rentrer chez soi).",
            formula = "[Destination] へ いきます / きます / かえります",
            examples = listOf(
                GrammarExample("わたしは 京都へ 行きます。", "Watashi wa Kyouto e ikimasu.", "Je vais à Kyoto."),
                GrammarExample("来週 日本へ 来ます。", "Raishuu Nihon e kimasu.", "La semaine prochaine il vient au Japon."),
                GrammarExample("うちへ 帰ります。", "Uchi e kaerimasu.", "Je rentre à la maison.")
            )
        ),
        GrammarPoint(
            id = "g_5_2",
            lessonId = 5,
            pattern = "どこ [へ] も 行きません (Négation absolue)",
            meaningFr = "Nulle part / Rien du tout",
            explanation = "L'interrogatif suivi de も et d'un verbe négatif exprime une négation complète (« nulle part », « rien »).",
            formula = "どこ [へ] も [Verbe négatif]",
            examples = listOf(
                GrammarExample("きのう どこへも 行きませんでした。", "Kinou doko e mo ikimasen deshita.", "Hier je ne suis allé nulle part."),
                GrammarExample("何も 食べませんでした。", "Nani mo tabemasen deshita.", "Je n'ai rien mangé du tout.")
            )
        ),
        GrammarPoint(
            id = "g_5_3",
            lessonId = 5,
            pattern = "[Moyen de transport] で 行きます",
            meaningFr = "Aller en [Train / Bus / Voiture / etc.]",
            explanation = "で marque le moyen de locomotion. Exception majeure : 歩いて (あるいて, à pied) s'emploie directement sans la particule で.",
            formula = "[Moyen de transport] で 行きます",
            examples = listOf(
                GrammarExample("電車で 行きます。", "Densha de ikimasu.", "J'y vais en train."),
                GrammarExample("タクシーで 来ました。", "Takushii de kimashita.", "Je suis venu en taxi."),
                GrammarExample("駅から うちまで 歩いて 帰ります。", "Eki kara uchi made aruite kaerimasu.", "De la gare à la maison, je rentre à pied.")
            )
        ),
        GrammarPoint(
            id = "g_5_4",
            lessonId = 5,
            pattern = "[Personne] と Verbe (Accompagnement)",
            meaningFr = "Avec [Personne]",
            explanation = "と indique la personne ou l'animal avec qui l'on effectue l'action. Pour agir seul, on dit 一人で (ひとりで) sans particule と.",
            formula = "[Personne] と [Verbe]",
            examples = listOf(
                GrammarExample("家族と 日本へ 来ました。", "Kazoku to Nihon e kimashita.", "Je suis venu au Japon avec ma famille."),
                GrammarExample("一人で 東京へ 行きます。", "Hitori de Toukyou e ikimasu.", "Je vais à Tokyo tout seul.")
            )
        ),
        GrammarPoint(
            id = "g_5_5",
            lessonId = 5,
            pattern = "いつ [Verbe] か (Interrogatif 'quand')",
            meaningFr = "Quand ?",
            explanation = "いつ ne prend jamais la particule に.",
            formula = "いつ [Action] か",
            examples = listOf(
                GrammarExample("いつ 日本へ 来ましたか。……3月25日に 来ました。", "Itsu Nihon e kimashita ka. ...San-gatsu nijuugo-nichi ni kimashita.", "Quand êtes-vous venu au Japon ? ...Le 25 mars."),
                GrammarExample("誕生日は いつですか。", "Tanjoubi wa itsu desu ka.", "Quand est votre anniversaire ?")
            )
        ),
        GrammarPoint(
            id = "g_5_6",
            lessonId = 5,
            pattern = "Phrase + よ (Informatif / Avertissement amical)",
            meaningFr = "Tu sais / Je te préviens / Sache que...",
            explanation = "La particule よ en fin de phrase sert à transmettre avec assurance une information nouvelle pour l'interlocuteur.",
            formula = "[Phrase] よ。",
            examples = listOf(
                GrammarExample("この 電車は 甲子園へ 行きませんよ。", "Kono densha wa Koushien e ikimasen yo.", "Ce train ne va pas à Koshien, vous savez !")
            )
        )
    )

    // ==========================================
    // LEÇON 6 / UNITÉ 6
    // ==========================================
    val lesson6Grammar = listOf(
        GrammarPoint(
            id = "g_6_1",
            lessonId = 6,
            pattern = "N を Verbe transitif (Objet direct)",
            meaningFr = "Faire l'action sur N (COD)",
            explanation = "を (prononcé 'o') indique le complément d'objet direct (COD) sur lequel porte l'action du verbe.",
            formula = "[Objet] を [Verbe transitif]",
            examples = listOf(
                GrammarExample("ジュースを 飲みます。", "Juusu o nomimasu.", "Je bois du jus."),
                GrammarExample("パンを 食べます。", "Pan o tabemasu.", "Je mange du pain."),
                GrammarExample("日本語を 勉強します。", "Nihongo o benkyoushimasu.", "J'étudie le japonais.")
            )
        ),
        GrammarPoint(
            id = "g_6_2",
            lessonId = 6,
            pattern = "何を しますか (Que faites-vous ?)",
            meaningFr = "Qu'est-ce que vous faites ?",
            explanation = "Interroge sur l'action accomplie ou projetée à un moment donné.",
            formula = "[Temps] 何を しますか。",
            examples = listOf(
                GrammarExample("月曜日 何を しますか。……京都へ 行きます。", "Getsuyoubi nani o shimasu ka. ...Kyouto e ikimasu.", "Que faites-vous lundi ? ...Je vais à Kyoto."),
                GrammarExample("きのう 何を しましたか。……サッカーを しました。", "Kinou nani o shimashita ka. ...Sakkaa o shimashita.", "Qu'avez-vous fait hier ? ...J'ai joué au foot.")
            )
        ),
        GrammarPoint(
            id = "g_6_3",
            lessonId = 6,
            pattern = "[Lieu] で Verbe (Lieu de l'action)",
            meaningFr = "Faire l'action à [Lieu]",
            explanation = "で marque l'endroit où se déroule une action active (contrairement à に qui s'utilise pour l'existence statique ou l'arrivée).",
            formula = "[Lieu de l'action] で [Verbe actif]",
            examples = listOf(
                GrammarExample("駅で 新聞を 買います。", "Eki de shinbun o kaimasu.", "J'achète le journal à la gare."),
                GrammarExample("レストランで 昼ごはんを 食べました。", "Resutoran de hirugohan o tabemashita.", "J'ai déjeuné au restaurant.")
            )
        ),
        GrammarPoint(
            id = "g_6_4",
            lessonId = 6,
            pattern = "Verbe ませんか (Invitation polie)",
            meaningFr = "Voulez-vous... ? / Ça vous dirait de... ?",
            explanation = "Permet d'inviter poliment une personne en ménageant sa liberté de refuser (« Ne voulez-vous pas... ? »).",
            formula = "いっしょに [Radical] + ませんか",
            examples = listOf(
                GrammarExample("いっしょに 京都へ 行きませんか。……ええ、行きましょう。", "Issho ni Kyouto e ikimasen ka. ...Ee, ikimashou.", "Ça vous dirait d'aller à Kyoto ensemble ? ...Oui, avec grand plaisir !"),
                GrammarExample("お茶を 飲みませんか。", "Ocha o nomimasen ka.", "Prendriez-vous un thé ?")
            )
        ),
        GrammarPoint(
            id = "g_6_5",
            lessonId = 6,
            pattern = "Verbe ましょう (Proposition enthousiaste)",
            meaningFr = "Faisons... ! / Allons-y !",
            explanation = "Sert à proposer une action commune ou à accepter avec enthousiasme une invitation formulée avec 〜ませんか.",
            formula = "[Radical] + ましょう",
            examples = listOf(
                GrammarExample("ちょっと 休みましょう。", "Chotto yasumimashou.", "Faisons une petite pause !"),
                GrammarExample("いっしょに 帰りましょう。", "Issho ni kaerimashou.", "Rentrons ensemble !")
            )
        )
    )

    // ==========================================
    // LEÇON 7 / UNITÉ 7
    // ==========================================
    val lesson7Grammar = listOf(
        GrammarPoint(
            id = "g_7_1",
            lessonId = 7,
            pattern = "[Outil / Moyen / Langue] で Verbe (Instrument)",
            meaningFr = "Au moyen de / En [Langue] / Avec [Outil]",
            explanation = "で indique l'instrument, l'outil ou la langue avec lesquels on réalise l'action.",
            formula = "[Outil / Langue] で [Verbe]",
            examples = listOf(
                GrammarExample("はしで ごはんを 食べます。", "Hashi de gohan o tabemasu.", "Je mange avec des baguettes."),
                GrammarExample("日本語で レポートを 書きます。", "Nihongo de repooto o kakimasu.", "J'écris le rapport en japonais."),
                GrammarExample("はさみで 紙を 切ります。", "Hasami de kami o kirimasu.", "Je coupe du papier avec des ciseaux.")
            )
        ),
        GrammarPoint(
            id = "g_7_2",
            lessonId = 7,
            pattern = "« Mot / Phrase » は [Langue] で 何ですか",
            meaningFr = "Comment dit-on « ... » en [Langue] ?",
            explanation = "Permet de demander la traduction d'un mot ou d'une expression dans une langue spécifique.",
            formula = "「[Mot]」は [Langue] で 何ですか。",
            examples = listOf(
                GrammarExample("「ありがとう」は フランス語で 何ですか。……「Merci」です。", "'Arigatou' wa Furansugo de nan desu ka. ...'Merci' desu.", "Comment dit-on « Arigatou » en français ? ...On dit « Merci ».")
            )
        ),
        GrammarPoint(
            id = "g_7_3",
            lessonId = 7,
            pattern = "[Personne] に あげます / 貸します / 教えます (Donner à)",
            meaningFr = "Donner / Prêter / Enseigner à [Personne]",
            explanation = "に indique le destinataire vers lequel le bien ou l'information est transmis.",
            formula = "[Destinataire] に [Objet] を あげます / かします / おしえます",
            examples = listOf(
                GrammarExample("わたしは 木村さんに 花を あげました。", "Watashi wa Kimura-san ni hana o agemashita.", "J'ai offert des fleurs à Mme Kimura."),
                GrammarExample("山田さんに 本を 貸しました。", "Yamada-san ni hon o kashimashita.", "J'ai prêté un livre à M. Yamada."),
                GrammarExample("ミラーさんに 英語を 教えます。", "Miraa-san ni eigo o oshiemasu.", "J'enseigne l'anglais à M. Miller.")
            )
        ),
        GrammarPoint(
            id = "g_7_4",
            lessonId = 7,
            pattern = "[Personne] に / から もらいます / 借ります / 習います (Recevoir de)",
            meaningFr = "Recevoir / Emprunter / Apprendre de [Personne]",
            explanation = "に (ou から) indique la provenance d'où l'on reçoit l'objet ou le savoir. Si la source est une entité collective (école, banque), on emploie から.",
            formula = "[Donneur / Institution] に / から [Objet] を もらいます / かります / ならいます",
            examples = listOf(
                GrammarExample("わたしは 木村さんに チョコレートを もらいました。", "Watashi wa Kimura-san ni chokoreeto o moraimashita.", "J'ai reçu des chocolats de Mme Kimura."),
                GrammarExample("カリナさんに CDを 借りました。", "Karina-san ni CD o karimashita.", "J'ai emprunté un CD à Karina."),
                GrammarExample("銀行から お金を 借りました。", "Ginkou kara okane o karimashita.", "J'ai emprunté de l'argent à la banque.")
            )
        ),
        GrammarPoint(
            id = "g_7_5",
            lessonId = 7,
            pattern = "もう Verbe ましたか / いいえ、まだです",
            meaningFr = "Avez-vous déjà fait... ? / Non, pas encore.",
            explanation = "もう + passé signifie « déjà accompli ». En cas de réponse négative, on répond いいえ、まだです (et non pas 〜ませんでした).",
            formula = "もう [Verbe passé] ましたか。……はい、もう〜ました。 / いいえ、まだです。",
            examples = listOf(
                GrammarExample("もう 荷物を 送りましたか。……はい、もう 送りました。", "Mou nimotsu o okurimashita ka. ...Hai, mou okurimashita.", "Avez-vous déjà expédié le colis ? ...Oui, je l'ai déjà envoyé."),
                GrammarExample("もう 昼ごはんを 食べましたか。……いいえ、まだです。", "Mou hirugohan o tabemashita ka. ...Iie, mada desu.", "Avez-vous déjà déjeuné ? ...Non, pas encore.")
            )
        )
    )

    // ==========================================
    // LEÇON 8 / UNITÉ 8
    // ==========================================
    val lesson8Grammar = listOf(
        GrammarPoint(
            id = "g_8_1",
            lessonId = 8,
            pattern = "Les Adjectifs : い-Adjectif です / な-Adjectif です",
            meaningFr = "Prédicats adjectivaux affirmatifs",
            explanation = "Les adjectifs en い se terminent par い (高い, おいしい). Les adjectifs en な perdent leur な quand ils terminent la phrase devant です (親切[な], 静か[な], 元気[な]).",
            formula = "[Sujet] は [い-Adjectif] です / [Sujet] は [な-Adjectif sans な] です",
            examples = listOf(
                GrammarExample("富士山は 高いです。", "Fujisan wa takai desu.", "Le Mont Fuji est élevé."),
                GrammarExample("サントスさんは 親切です。", "Santosu-san wa shinsetsu desu.", "M. Santos est aimable / serviable.")
            )
        ),
        GrammarPoint(
            id = "g_8_2",
            lessonId = 8,
            pattern = "Négation des Adjectifs : 〜くないです / 〜じゃ ありません",
            meaningFr = "Forme négative des adjectifs au présent",
            explanation = "Pour les adjectifs en い : on retire い et on ajoute くないです (exception : いい devient よくないです). Pour les adjectifs en な : on ajoute じゃ ありません.",
            formula = "い-adj: 〜くないです / な-adj: 〜じゃ ありません",
            examples = listOf(
                GrammarExample("この 本は おもしろくないです。", "Kono hon wa omoshirokunai desu.", "Ce livre n'est pas intéressant."),
                GrammarExample("今日は 寒くないです。", "Kyou wa samukunai desu.", "Aujourd'hui il ne fait pas froid."),
                GrammarExample("ここは 静かじゃ ありません。", "Koko wa shizuka ja arimasen.", "Cet endroit n'est pas calme.")
            )
        ),
        GrammarPoint(
            id = "g_8_3",
            lessonId = 8,
            pattern = "Adjectif qualificatif devant un Nom (Épithète)",
            meaningFr = "[Adjectif] + [Nom]",
            explanation = "L'adjectif en い garde son い devant le nom. L'adjectif en な prend impérativement la particule な devant le nom.",
            formula = "[い-Adjectif] + [Nom] / [な-Adjectif] + な + [Nom]",
            examples = listOf(
                GrammarExample("富士山は 高い 山です。", "Fujisan wa takai yama desu.", "Le mont Fuji est une haute montagne."),
                GrammarExample("ワット先生は 親切な 先生です。", "Watto-sensei wa shinsetsu na sensei desu.", "M. Watt est un enseignant aimable."),
                GrammarExample("奈良は 静かな 町です。", "Nara wa shizuka na machi desu.", "Nara est une ville paisible.")
            )
        ),
        GrammarPoint(
            id = "g_8_4",
            lessonId = 8,
            pattern = "とても (Très) & あまり (Pas très / Guère)",
            meaningFr = "Adverbes de degré",
            explanation = "とても s'utilise avec une forme affirmative (« très »). あまり s'utilise impérativement avec une négation pour atténuer le propos (« pas très », « guère »).",
            formula = "とても + [Affirmatif] / あまり + [Négatif]",
            examples = listOf(
                GrammarExample("日本の 食べ物は とても おいしいです。", "Nihon no tabemono wa totemo oishii desu.", "La cuisine japonaise est très délicieuse."),
                GrammarExample("この 車は あまり 高くないです。", "Kono kuruma wa amari takakunai desu.", "Cette voiture n'est pas très chère.")
            )
        ),
        GrammarPoint(
            id = "g_8_5",
            lessonId = 8,
            pattern = "N は どうですか (Comment est... ?)",
            meaningFr = "Comment trouvez-vous N ? / Qu'en pensez-vous ?",
            explanation = "Demande l'impression personnelle ou l'avis de son interlocuteur sur une expérience, un lieu ou une chose.",
            formula = "[Sujet] は どうですか。",
            examples = listOf(
                GrammarExample("日本の 生活は どうですか。……楽しいです。", "Nihon no seikatsu wa dou desu ka. ...Tanoshii desu.", "Comment trouvez-vous la vie au Japon ? ...C'est très agréable !")
            )
        ),
        GrammarPoint(
            id = "g_8_6",
            lessonId = 8,
            pattern = "N1 は どんな N2 ですか (Quelle sorte de...)",
            meaningFr = "Quelle sorte de N2 est N1 ?",
            explanation = "Interroge sur les qualités et la nature de N1 dans sa catégorie N2. On répond par [Adjectif] + [Nom].",
            formula = "[Sujet] は どんな [Catégorie] ですか。",
            examples = listOf(
                GrammarExample("京都は どんな 町ですか。……古い 町です。", "Kyouto wa donna machi desu ka. ...Furui machi desu.", "Quelle sorte de ville est Kyoto ? ...C'est une ville ancienne."),
                GrammarExample("ミラーさんは どんな 人ですか。……親切な 人です。", "Miraa-san wa donna hito desu ka. ...Shinsetsu na hito desu.", "Quel genre de personne est M. Miller ? ...C'est quelqu'un d'aimable.")
            )
        ),
        GrammarPoint(
            id = "g_8_7",
            lessonId = 8,
            pattern = "Phrase 1 が、Phrase 2 (Opposition 'mais')",
            meaningFr = "Proposition 1, mais Proposition 2",
            explanation = "La particule が lie deux propositions contrastées (« mais », « cependant »).",
            formula = "[Phrase 1] が、[Phrase 2]",
            examples = listOf(
                GrammarExample("日本の 食べ物は おいしいですが、高いです。", "Nihon no tabemono wa oishii desu ga, takai desu.", "La nourriture japonaise est délicieuse, mais elle est chère.")
            )
        )
    )

    // ==========================================
    // LEÇON 9 / UNITÉ 9
    // ==========================================
    val lesson9Grammar = listOf(
        GrammarPoint(
            id = "g_9_1",
            lessonId = 9,
            pattern = "N が あります / わかります (Possession & Compréhension)",
            meaningFr = "Avoir N / Comprendre N (Particule が)",
            explanation = "L'objet de あります (posséder) et de わかります (comprendre) prend la particule が (et non を) car ce sont des verbes d'état.",
            formula = "[Thème] は [Objet] が あります / わかります",
            examples = listOf(
                GrammarExample("わたしは 日本語が わかります。", "Watashi wa nihongo ga wakarimasu.", "Je comprends le japonais."),
                GrammarExample("ミラーさんは 車が あります。", "Miraa-san wa kuruma ga arimasu.", "M. Miller possède une voiture."),
                GrammarExample("時間が ありません。", "Jikan ga arimasen.", "Je n'ai pas le temps.")
            )
        ),
        GrammarPoint(
            id = "g_9_2",
            lessonId = 9,
            pattern = "N が 好きです / 嫌いです / 上手です / 下手です",
            meaningFr = "Aimer / Détester / Être doué pour / Être maladroit à",
            explanation = "Les adjectifs en な exprimant le goût (好き, 嫌い) ou la compétence (上手, 下手) prennent la particule が pour marquer leur objet.",
            formula = "[Thème] は [Objet] が すき / きらい / じょうず / へた です",
            examples = listOf(
                GrammarExample("わたしは イタリア料理が 好きです。", "Watashi wa Itaria ryouri ga suki desu.", "J'aime la cuisine italienne."),
                GrammarExample("マリアさんは カラオケが 上手です。", "Maria-san wa karaoke ga jouzu desu.", "Maria chante très bien au karaoké."),
                GrammarExample("わたしは スポーツが 下手です。", "Watashi wa supootsu ga heta desu.", "Je suis maladroit en sport.")
            )
        ),
        GrammarPoint(
            id = "g_9_3",
            lessonId = 9,
            pattern = "どんな N が 好きですか (Préférence précise)",
            meaningFr = "Quelle sorte de N aimez-vous ?",
            explanation = "Permet de demander quelle catégorie précise ou quel genre la personne affectionne particulièrement.",
            formula = "どんな [Catégorie] が すきですか。",
            examples = listOf(
                GrammarExample("どんな スポーツが 好きですか。……野球が 好きです。", "Donna supootsu ga suki desu ka. ...Yakyuu ga suki desu.", "Quel sport aimez-vous ? ...J'aime le baseball.")
            )
        ),
        GrammarPoint(
            id = "g_9_4",
            lessonId = 9,
            pattern = "Adverbes de quantité & degré (よく, だいたい, たくさん, すこし, あまり, ぜんぜん)",
            meaningFr = "Bien, à peu près, beaucoup, un peu, pas beaucoup, pas du tout",
            explanation = "Bien respecter l'accord : よく, だいたい, たくさん, すこし avec l'affirmatif ; あまり, ぜんぜん exclusivement avec la forme négative.",
            formula = "Affirmatif: よく / だいたい / たくさん / すこし ; Négatif: あまり / ぜんぜん 〜ません",
            examples = listOf(
                GrammarExample("英語が よく わかります。", "Eigo ga yoku wakarimasu.", "Je comprends très bien l'anglais."),
                GrammarExample("ひらがなが だいたい わかります。", "Hiragana ga daitai wakarimasu.", "Je comprends à peu près les hiragana."),
                GrammarExample("漢字が ぜんぜん わかりません。", "Kanji ga zenzen wakarimasen.", "Je ne comprends pas du tout les kanji.")
            )
        ),
        GrammarPoint(
            id = "g_9_5",
            lessonId = 9,
            pattern = "[Cause] から、[Conséquence] (Parce que / Donc)",
            meaningFr = "Comme / Parce que... donc...",
            explanation = "La proposition terminée par から indique la cause ou justification qui motive la proposition principale suivante.",
            formula = "[Proposition cause] から、[Proposition principale]",
            examples = listOf(
                GrammarExample("時間が ありませんから、タクシーで 行きます。", "Jikan ga arimasen kara, takushii de ikimasu.", "Comme je n'ai pas le temps, j'y vais en taxi."),
                GrammarExample("お金が ありませんから、買いません。", "Okane ga arimasen kara, kaimasen.", "Puisque je n'ai pas d'argent, je ne l'achète pas.")
            )
        ),
        GrammarPoint(
            id = "g_9_6",
            lessonId = 9,
            pattern = "どうして (Pourquoi) et réponse par 〜から",
            meaningFr = "Pourquoi ? ...Parce que...",
            explanation = "どうして sert à demander la raison. La justification se termine obligatoirement par から / からです.",
            formula = "どうして [Action] か。……[Raison] からです。",
            examples = listOf(
                GrammarExample("どうして きのう 早く 帰りましたか。……用事が ありましたから。", "Doushite kinou hayaku kaerimashita ka. ...Youji ga arimashita kara.", "Pourquoi êtes-vous rentré plus tôt hier ? ...Parce que j'avais une affaire urgente.")
            )
        )
    )

    // ==========================================
    // LEÇON 10 / UNITÉ 10
    // ==========================================
    val lesson10Grammar = listOf(
        GrammarPoint(
            id = "g_10_1",
            lessonId = 10,
            pattern = "[Lieu] に N が あります / います (Existence dans un lieu)",
            meaningFr = "Il y a N à [Lieu]",
            explanation = "あります s'applique aux objets inanimés et aux végétaux. います s'applique aux êtres animés doués de mouvement (êtres humains et animaux).",
            formula = "[Lieu] に [Chose] が あります / [Lieu] に [Être vivant] が います",
            examples = listOf(
                GrammarExample("机の 上に 本が あります。", "Tsukue no ue ni hon ga arimasu.", "Il y a un livre sur le bureau."),
                GrammarExample("あそこに 猫が います。", "Asoko ni neko ga imasu.", "Il y a un chat là-bas."),
                GrammarExample("教室に 学生が います。", "Kyoushitsu ni gakusei ga imasu.", "Il y a des étudiants dans la salle de classe.")
            )
        ),
        GrammarPoint(
            id = "g_10_2",
            lessonId = 10,
            pattern = "N は [Lieu] に あります / います (Localisation du thème)",
            meaningFr = "N se trouve à [Lieu]",
            explanation = "Inverse l'accentuation : le sujet N devient le thème dont on précise l'emplacement.",
            formula = "[Sujet] は [Lieu] に あります / います",
            examples = listOf(
                GrammarExample("東京ディズニーランドは 千葉県に あります。", "Toukyou Dizuniirando wa Chiba-ken ni arimasu.", "Disneyland Tokyo se trouve dans la préfecture de Chiba."),
                GrammarExample("ミラーさんは 事務所に います。", "Miraa-san wa jimusho ni imasu.", "M. Miller se trouve au bureau.")
            )
        ),
        GrammarPoint(
            id = "g_10_3",
            lessonId = 10,
            pattern = "N の [Position spatiale] (上, 下, 前, 後ろ, 右, 左, 中, 外, 隣, 近く, 間)",
            meaningFr = "Sur, sous, devant, derrière, à droite, à gauche, dans, dehors, à côté, près, entre",
            explanation = "Permet de situer avec précision un objet par rapport à un point de repère en utilisant le nom de position relié par の.",
            formula = "[Point de repère] の [Position: 上/下/前/後ろ/右/左/中/隣/間] に",
            examples = listOf(
                GrammarExample("机の 上に 写真が あります。", "Tsukue no ue ni shashin ga arimasu.", "Il y a une photo sur la table."),
                GrammarExample("銀行は 本屋の 隣に あります。", "Ginkou wa hon'ya no tonari ni arimasu.", "La banque se trouve à côté de la librairie."),
                GrammarExample("ポストは 銀行と 花屋の 間に あります。", "Posuto wa ginkou to hanaya no aida ni arimasu.", "La boîte aux lettres est entre la banque et le fleuriste.")
            )
        ),
        GrammarPoint(
            id = "g_10_4",
            lessonId = 10,
            pattern = "N1 や N2 [など] (Énumération non exhaustive)",
            meaningFr = "N1, N2 [entre autres / etc.]",
            explanation = "や cite deux éléments représentatifs parmi d'autres (contrairement à と qui est exhaustif). など peut être rajouté pour signifier 'et ainsi de suite'.",
            formula = "[Nom 1] や [Nom 2] など",
            examples = listOf(
                GrammarExample("箱の 中に 手紙や 写真が あります。", "Hako no naka ni tegami ya shashin ga arimasu.", "Dans la boîte, il y a des lettres et des photos (entre autres)."),
                GrammarExample("店に パンや 卵などが あります。", "Mise ni pan ya tamago nado ga arimasu.", "Dans le magasin, il y a du pain, des œufs, etc.")
            )
        ),
        GrammarPoint(
            id = "g_10_5",
            lessonId = 10,
            pattern = "だれが いますか / 何が ありますか (Questions d'existence)",
            meaningFr = "Qui y a-t-il ? / Qu'y a-t-il ?",
            explanation = "Permet d'interroger sur le contenu ou les présences en un lieu donné.",
            formula = "[Lieu] に [だれ / 何] が いますか / ありますか",
            examples = listOf(
                GrammarExample("庭に だれが いますか。……だれも いません。", "Niwa ni dare ga imasu ka. ...Dare mo imasen.", "Qui y a-t-il dans le jardin ? ...Il n'y a personne."),
                GrammarExample("部屋に 何が ありますか。……机と いすが あります。", "Heya ni nani ga arimasu ka. ...Tsukue to isu ga arimasu.", "Qu'y a-t-il dans la pièce ? ...Il y a un bureau et une chaise.")
            )
        )
    )

    // ==========================================
    // POINTS DE GRAMMAIRE POUR LES LEÇONS SUIVANTES
    // ==========================================
    val laterLessonsGrammar = listOf(
        GrammarPoint(
            id = "g_14_1",
            lessonId = 14,
            pattern = "Verbe [て形] ください",
            meaningFr = "Faites s'il vous plaît (Requête polie)",
            explanation = "La forme en -Te suivie de ください sert à demander poliment à quelqu'un d'effectuer une action.",
            formula = "[Verbe en Te] + ください",
            examples = listOf(
                GrammarExample("ちょっと まってください。", "Chotto matte kudasai.", "Attendez un peu s'il vous plaît."),
                GrammarExample("ここに なまえを かいてください。", "Koko ni namae o kaite kudasai.", "Veuillez écrire votre nom ici.")
            )
        ),
        GrammarPoint(
            id = "g_17_1",
            lessonId = 17,
            pattern = "Verbe [ない形] でください",
            meaningFr = "Ne faites pas s'il vous plaît",
            explanation = "La forme en -Nai suivie de でください exprime une demande bienveillante de ne pas faire quelque chose.",
            formula = "[Verbe en Nai] + でください",
            examples = listOf(
                GrammarExample("ここで たばこを すわないでください。", "Koko de tabako o suwanaide kudasai.", "S'il vous plaît, ne fumez pas ici."),
                GrammarExample("しゃしんを とらないでください。", "Shashin o toranaide kudasai.", "Veuillez ne pas prendre de photos.")
            )
        ),
        GrammarPoint(
            id = "g_18_1",
            lessonId = 18,
            pattern = "V [辞書形] ことが できます",
            meaningFr = "Être capable de faire / Il est possible de faire",
            explanation = "La forme du dictionnaire nominalisée par こと permet d'exprimer une compétence ou une possibilité matérielle.",
            formula = "[Verbe au dictionnaire] + ことが できます",
            examples = listOf(
                GrammarExample("ミラーさんは かんじを よむことが できます。", "Miraa-san wa kanji o yomu koto ga dekimasu.", "M. Miller sait lire les kanji."),
                GrammarExample("カードで はらうことが できます。", "Kaado de harau koto ga dekimasu.", "On peut payer par carte.")
            )
        ),
        GrammarPoint(
            id = "g_19_1",
            lessonId = 19,
            pattern = "V [た形] ことが あります",
            meaningFr = "Avoir déjà fait / Expérience vécue",
            explanation = "Permet de parler d'une expérience accomplie dans le passé (« Avez-vous déjà... ? »).",
            formula = "[Verbe en Ta] + ことが あります",
            examples = listOf(
                GrammarExample("ふじさんに のぼったことが あります。", "Fujisan ni nobotta koto ga arimasu.", "J'ai déjà gravi le Mont Fuji."),
                GrammarExample("すしを たべたことが ありますか。", "Sushi o tabeta koto ga arimasu ka.", "Avez-vous déjà mangé des sushis ?")
            )
        )
    )
}
