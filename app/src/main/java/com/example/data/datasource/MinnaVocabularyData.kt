package com.example.data.datasource

import com.example.data.model.VocabularyWord
import com.example.data.model.WordCategory

object MinnaVocabularyData {

    val allVocabulary: List<VocabularyWord> by lazy {
        lesson1Vocabulary +
        lesson2Vocabulary +
        lesson3Vocabulary +
        lesson4Vocabulary +
        lesson5Vocabulary +
        lesson6Vocabulary +
        lesson7Vocabulary +
        lesson8Vocabulary +
        lesson9Vocabulary +
        lesson10Vocabulary +
        lesson11Vocabulary +
        lesson12Vocabulary +
        lesson13Vocabulary +
        lesson14Vocabulary +
        lesson15Vocabulary +
        lesson16Vocabulary +
        lesson17Vocabulary +
        lesson18Vocabulary +
        lesson19Vocabulary +
        lesson20Vocabulary
    }

    // ==========================================
    // LEÇON 1 : Présentations & Métiers + PAYS
    // ==========================================
    val lesson1Vocabulary: List<VocabularyWord> = listOf(
        VocabularyWord("1_1", 1, "私", "わたし", "watashi", "Moi / Je", WordCategory.NOUN, "わたしは マイク・ミラーです。", "Watashi wa Maiku Miraa desu.", "Je m'appelle Mike Miller."),
        VocabularyWord("1_2", 1, "あなた", "あなた", "anata", "Vous / Tu", WordCategory.NOUN, "あなたは 学生ですか。", "Anata wa gakusei desu ka.", "Êtes-vous étudiant ?"),
        VocabularyWord("1_3", 1, "あの人", "あのひと", "ano hito", "Cette personne", WordCategory.NOUN, "あの人は だれですか。", "Ano hito wa dare desu ka.", "Qui est cette personne ?"),
        VocabularyWord("1_4", 1, "あの方", "あのかた", "ano kata", "Cette personne (très poli)", WordCategory.NOUN, "あの方は どなたですか。", "Ano kata wa donata desu ka.", "Qui est cette personne ? (poli)"),
        VocabularyWord("1_5", 1, "〜さん", "〜さん", "~san", "Monsieur / Madame (suffixe de politesse)", WordCategory.EXPRESSION, "佐藤さん", "Satou-san", "Mme Sato"),
        VocabularyWord("1_6", 1, "〜ちゃん", "〜ちゃん", "~chan", "Suffixe affectueux (enfants, filles)", WordCategory.EXPRESSION, "タローちゃん", "Tarou-chan", "Le petit Taro"),
        VocabularyWord("1_7", 1, "〜人", "〜じん", "~jin", "Suffixe de nationalité (citoyen de)", WordCategory.EXPRESSION, "日本人 / フランス人", "Nihon-jin / Furansu-jin", "Japonais / Français"),
        VocabularyWord("1_8", 1, "先生", "せんせい", "sensei", "Professeur / Maître / Docteur", WordCategory.NOUN, "ワット先生です。", "Watto sensei desu.", "C'est le professeur Watt."),
        VocabularyWord("1_9", 1, "教師", "きょうし", "kyoushi", "Enseignant (profession déclarée)", WordCategory.NOUN, "わたしは 日本語の 教師です。", "Watashi wa nihongo no kyoushi desu.", "Je suis enseignant de japonais."),
        VocabularyWord("1_10", 1, "学生", "がくせい", "gakusei", "Étudiant(e)", WordCategory.NOUN, "カリナさんは 学生です。", "Karina-san wa gakusei desu.", "Karina est étudiante."),
        VocabularyWord("1_11", 1, "会社員", "かいしゃいん", "kaishain", "Employé(e) d'entreprise", WordCategory.NOUN, "ミラーさんは 会社員です。", "Miraa-san wa kaishain desu.", "M. Miller est employé de bureau."),
        VocabularyWord("1_12", 1, "社員", "しゃいん", "shain", "Employé de (avec nom de firme)", WordCategory.NOUN, "IMCの 社員です。", "IMC no shain desu.", "Employé de la firme IMC."),
        VocabularyWord("1_13", 1, "銀行員", "ぎんこういん", "ginkouin", "Employé(e) de banque", WordCategory.NOUN, "山田さんは 銀行員です。", "Yamada-san wa ginkouin desu.", "M. Yamada est banquier."),
        VocabularyWord("1_14", 1, "医者", "いしゃ", "isha", "Médecin", WordCategory.NOUN, "ワンさんは 医者です。", "Wan-san wa isha desu.", "M. Wang est médecin."),
        VocabularyWord("1_15", 1, "研究者", "けんきゅうしゃ", "kenkyuusha", "Chercheur", WordCategory.NOUN, "イーさんは 研究者です。", "Ii-san wa kenkyuusha desu.", "Mme Lee est chercheuse."),
        VocabularyWord("1_16", 1, "エンジニア", "エンジニア", "enjinia", "Ingénieur", WordCategory.NOUN, "サントスさんは エンジニアです。", "Santosu-san wa enjinia desu.", "M. Santos est ingénieur."),
        VocabularyWord("1_17", 1, "大学", "だいがく", "daigaku", "Université", WordCategory.NOUN, "さくら大学の 学生です。", "Sakura daigaku no gakusei desu.", "Étudiant à l'université Sakura."),
        VocabularyWord("1_18", 1, "病院", "びょういん", "byouin", "Hôpital", WordCategory.NOUN, "神戸病院で 働きます。", "Koube byouin de hatarakimasu.", "Je travaille à l'hôpital de Kobe."),
        VocabularyWord("1_19", 1, "電気", "でんき", "denki", "Électricité / Lumière", WordCategory.NOUN, "パワー電気", "Pawaa denki", "Power Électrique"),
        VocabularyWord("1_20", 1, "だれ", "だれ", "dare", "Qui", WordCategory.NOUN, "あの人は だれですか。", "Ano hito wa dare desu ka.", "Qui est cette personne ?"),
        VocabularyWord("1_21", 1, "どなた", "どなた", "donata", "Qui (forme très polie)", WordCategory.NOUN, "あの方は どなたですか。", "Ano kata wa donata desu ka.", "Qui est cette personne honorable ?"),
        VocabularyWord("1_22", 1, "〜歳", "〜さい", "~sai", "Âge (ans)", WordCategory.NOUN, "２８歳です。", "Nijuuhassai desu.", "J'ai 28 ans."),
        VocabularyWord("1_23", 1, "何歳", "なんさい", "nansai", "Quel âge", WordCategory.NOUN, "何歳ですか。", "Nansai desu ka.", "Quel âge avez-vous ?"),
        VocabularyWord("1_24", 1, "初めまして", "はじめまして", "hajimemashite", "Enchanté (1re rencontre)", WordCategory.GREETING, "はじめまして、ミラーです。", "Hajimemashite, Miraa desu.", "Enchanté, je suis Miller."),
        VocabularyWord("1_25", 1, "どうぞよろしく", "どうぞよろしく[お願いします]", "douzo yoroshiku", "Ravi de faire votre connaissance", WordCategory.GREETING, "どうぞ よろしく お願いします。", "Douzo yoroshiku onegaishimasu.", "Très heureux de faire votre connaissance."),
        // Vocabulaire Pays (PDF L1 Pays)
        VocabularyWord("1_p1", 1, "日本", "にほん", "nihon", "Japon", WordCategory.NOUN, "ここは 日本です。", "Koko wa Nihon desu.", "Ici c'est le Japon."),
        VocabularyWord("1_p2", 1, "フランス", "フランス", "furansu", "France", WordCategory.NOUN, "フランスから 来ました。", "Furansu kara kimashita.", "Je viens de France."),
        VocabularyWord("1_p3", 1, "アメリカ", "アメリカ", "amerika", "États-Unis", WordCategory.NOUN, "アメリカ人です。", "Amerika-jin desu.", "Je suis américain."),
        VocabularyWord("1_p4", 1, "イギリス", "イギリス", "igirisu", "Royaume-Uni / Angleterre", WordCategory.NOUN, "イギリスの 車です。", "Igirisu no kuruma desu.", "C'est une voiture anglaise."),
        VocabularyWord("1_p5", 1, "ドイツ", "ドイツ", "doitsu", "Allemagne", WordCategory.NOUN, "ドイツから 来ました。", "Doitsu kara kimashita.", "Je viens d'Allemagne."),
        VocabularyWord("1_p6", 1, "中国", "ちゅうごく", "chuugoku", "Chine", WordCategory.NOUN, "中国の お茶です。", "Chuugoku no ocha desu.", "C'est du thé chinois."),
        VocabularyWord("1_p7", 1, "韓国", "かんこく", "kankoku", "Corée du Sud", WordCategory.NOUN, "韓国料理が 好きです。", "Kankoku ryouri ga suki desu.", "J'aime la cuisine coréenne."),
        VocabularyWord("1_p8", 1, "ブラジル", "ブラジル", "burajiru", "Brésil", WordCategory.NOUN, "サントスさんは ブラジル人です。", "Santosu-san wa Burajiru-jin desu.", "M. Santos est brésilien."),
        VocabularyWord("1_p9", 1, "タイ", "タイ", "tai", "Thaïlande", WordCategory.NOUN, "ワットさんは タイ人です。", "Watto-san wa Tai-jin desu.", "M. Watt est thaïlandais."),
        VocabularyWord("1_p10", 1, "インド", "インド", "indo", "Inde", WordCategory.NOUN, "インドの カレーです。", "Indo no karee desu.", "C'est un curry indien.")
    )

    // ==========================================
    // LEÇON 2 : Objets & Démonstratifs (これ, それ, あれ)
    // ==========================================
    val lesson2Vocabulary: List<VocabularyWord> = listOf(
        VocabularyWord("2_1", 2, "これ", "これ", "kore", "Ceci (près de moi)", WordCategory.NOUN, "これは 本です。", "Kore wa hon desu.", "Ceci est un livre."),
        VocabularyWord("2_2", 2, "それ", "それ", "sore", "Cela (près de toi)", WordCategory.NOUN, "それは 辞書です。", "Sore wa jisho desu.", "Cela est un dictionnaire."),
        VocabularyWord("2_3", 2, "あれ", "あれ", "are", "Cela là-bas (éloigné)", WordCategory.NOUN, "あれは 車です。", "Are wa kuruma desu.", "Cela là-bas est une voiture."),
        VocabularyWord("2_4", 2, "この", "この", "kono", "Ce... ci (+ nom)", WordCategory.NOUN, "この 傘は 私のです。", "Kono kasa wa watashi no desu.", "Ce parapluie est à moi."),
        VocabularyWord("2_5", 2, "その", "その", "sono", "Ce... là (+ nom)", WordCategory.NOUN, "その 本を ください。", "Sono hon o kudasai.", "Donnez-moi ce livre-là s'il vous plaît."),
        VocabularyWord("2_6", 2, "あの", "あの", "ano", "Ce... là-bas (+ nom)", WordCategory.NOUN, "あの 人は だれですか。", "Ano hito wa dare desu ka.", "Qui est cette personne là-bas ?"),
        VocabularyWord("2_7", 2, "本", "ほん", "hon", "Livre", WordCategory.NOUN, "日本語の 本です。", "Nihongo no hon desu.", "C'est un livre de japonais."),
        VocabularyWord("2_8", 2, "辞書", "じしょ", "jisho", "Dictionnaire", WordCategory.NOUN, "これは 辞書です。", "Kore wa jisho desu.", "Ceci est un dictionnaire."),
        VocabularyWord("2_9", 2, "雑誌", "ざっし", "zasshi", "Magazine / Revue", WordCategory.NOUN, "車の 雑誌です。", "Kuruma no zasshi desu.", "C'est un magazine d'automobiles."),
        VocabularyWord("2_10", 2, "新聞", "しんぶん", "shinbun", "Journal", WordCategory.NOUN, "毎朝 新聞を 読みます。", "Maiasa shinbun o yomimasu.", "Je lis le journal chaque matin."),
        VocabularyWord("2_11", 2, "ノート", "ノート", "nooto", "Cahier", WordCategory.NOUN, "大学の ノートです。", "Daigaku no nooto desu.", "C'est un cahier d'université."),
        VocabularyWord("2_12", 2, "手帳", "てちょう", "techou", "Carnet / Agenda", WordCategory.NOUN, "これは 手帳です。", "Kore wa techou desu.", "C'est un agenda personnel."),
        VocabularyWord("2_13", 2, "名刺", "めいし", "meishi", "Carte de visite", WordCategory.NOUN, "私の 名刺です。", "Watashi no meishi desu.", "Voici ma carte de visite."),
        VocabularyWord("2_14", 2, "鉛筆", "えんぴつ", "enpitsu", "Crayon à papier", WordCategory.NOUN, "鉛筆で 書きます。", "Enpitsu de kakimasu.", "J'écris au crayon."),
        VocabularyWord("2_15", 2, "ボールペン", "ボールペン", "boorupen", "Stylo à bille", WordCategory.NOUN, "青い ボールペンです。", "Aoi boorupen desu.", "C'est un stylo à bille bleu."),
        VocabularyWord("2_16", 2, "鍵", "かぎ", "kagi", "Clé", WordCategory.NOUN, "部屋の 鍵です。", "Heya no kagi desu.", "C'est la clé de la chambre."),
        VocabularyWord("2_17", 2, "時計", "とけい", "tokei", "Montre / Horloge", WordCategory.NOUN, "スイスの 時計です。", "Suisu no tokei desu.", "C'est une montre suisse."),
        VocabularyWord("2_18", 2, "傘", "かさ", "kasa", "Parapluie", WordCategory.NOUN, "だれの 傘ですか。", "Dare no kasa desu ka.", "À qui est ce parapluie ?"),
        VocabularyWord("2_19", 2, "鞄", "かばん", "kaban", "Sac / Cartable", WordCategory.NOUN, "黒い 鞄です。", "Kroi kaban desu.", "C'est un sac noir."),
        VocabularyWord("2_20", 2, "車", "くるま", "kuruma", "Voiture / Automobile", WordCategory.NOUN, "日本の 車です。", "Nihon no kuruma desu.", "C'est une voiture japonaise."),
        VocabularyWord("2_21", 2, "机", "つくえ", "tsukue", "Bureau / Table de travail", WordCategory.NOUN, "机の 上に あります。", "Tsukue no ue ni arimasu.", "C'est sur le bureau."),
        VocabularyWord("2_22", 2, "椅子", "いす", "isu", "Chaise", WordCategory.NOUN, "椅子に 座ります。", "Isu ni suwarimasu.", "Je m'assieds sur la chaise."),
        VocabularyWord("2_23", 2, "お土産", "おみやげ", "omiyage", "Souvenir / Cadeau souvenir", WordCategory.NOUN, "京都の お土産です。", "Kyouto no omiyage desu.", "C'est un souvenir de Kyoto."),
        VocabularyWord("2_24", 2, "何", "なん / なに", "nan / nani", "Quoi / Que", WordCategory.NOUN, "これは 何ですか。", "Kore wa nan desu ka.", "Qu'est-ce que ceci ?"),
        VocabularyWord("2_25", 2, "違います", "ちがいます", "chigaimasu", "Ce n'est pas cela / C'est faux", WordCategory.EXPRESSION, "いいえ、違います。", "Iie, chigaimasu.", "Non, ce n'est pas ça.")
    )

    // ==========================================
    // LEÇON 3 : Lieux, Magasins & Prix (ここ, そこ, あそこ)
    // ==========================================
    val lesson3Vocabulary: List<VocabularyWord> = listOf(
        VocabularyWord("3_1", 3, "ここ", "ここ", "koko", "Ici", WordCategory.NOUN, "ここは 教室です。", "Koko wa kyoushitsu desu.", "Ici, c'est la salle de classe."),
        VocabularyWord("3_2", 3, "そこ", "そこ", "soko", "Là", WordCategory.NOUN, "そこは 受付です。", "Soko wa uketsuke desu.", "Là, c'est l'accueil."),
        VocabularyWord("3_3", 3, "あそこ", "あそこ", "asoko", "Là-bas", WordCategory.NOUN, "あそこは 食堂です。", "Asoko wa shokudou desu.", "Là-bas, c'est la cantine."),
        VocabularyWord("3_4", 3, "どこ", "どこ", "doko", "Où", WordCategory.NOUN, "お手洗いは どこですか。", "Otearai wa doko desu ka.", "Où sont les toilettes ?"),
        VocabularyWord("3_5", 3, "教室", "きょうしつ", "kyoushitsu", "Salle de classe", WordCategory.NOUN, "教室に 入ります。", "Kyoushitsu ni hairimasu.", "J'entre dans la classe."),
        VocabularyWord("3_6", 3, "食堂", "しょくどう", "shokudou", "Cantine / Réfectoire", WordCategory.NOUN, "食堂で 食べます。", "Shokudou de tabemasu.", "Je mange à la cantine."),
        VocabularyWord("3_7", 3, "事務所", "じむしょ", "jimusho", "Bureau administratif", WordCategory.NOUN, "事務所は あちらです。", "Jimusho wa achira desu.", "Le bureau est par là-bas."),
        VocabularyWord("3_8", 3, "会議室", "かいぎしつ", "kaigishitsu", "Salle de réunion", WordCategory.NOUN, "会議室は ２階です。", "Kaigishitsu wa nikai desu.", "La salle de réunion est au 2e étage."),
        VocabularyWord("3_9", 3, "受付", "うけつけ", "uketsuke", "Accueil / Réception", WordCategory.NOUN, "受付で 聞きます。", "Uketsuke de kikimasu.", "Je demande à l'accueil."),
        VocabularyWord("3_10", 3, "部屋", "へや", "heya", "Chambre / Pièce", WordCategory.NOUN, "私の 部屋です。", "Watashi no heya desu.", "C'est ma chambre."),
        VocabularyWord("3_11", 3, "お手洗い", "おてあらい", "otearai", "Toilettes (poli)", WordCategory.NOUN, "お手洗いは こちらです。", "Otearai wa kochira desu.", "Les toilettes sont par ici."),
        VocabularyWord("3_12", 3, "階段", "かいだん", "kaidan", "Escalier", WordCategory.NOUN, "階段を 上がります。", "Kaidan o agarimasu.", "Je monte les escaliers."),
        VocabularyWord("3_13", 3, "エレベーター", "エレベーター", "erebeetaa", "Ascenseur", WordCategory.NOUN, "エレベーターで 行きます。", "Erebeetaa de ikimasu.", "J'y vais par l'ascenseur."),
        VocabularyWord("3_14", 3, "自動販売機", "じどうはんばいき", "jidouhanbaiki", "Distributeur automatique", WordCategory.NOUN, "自販機で ジュースを 買います。", "Jihanki de juusu o kaimasu.", "J'achète un jus au distributeur."),
        VocabularyWord("3_15", 3, "会社", "かいしゃ", "kaisha", "Entreprise / Société", WordCategory.NOUN, "会社へ 行きます。", "Kaisha e ikimasu.", "Je vais au travail / à l'entreprise."),
        VocabularyWord("3_16", 3, "家", "うち", "uchi", "Maison / Chez-soi", WordCategory.NOUN, "うちへ 帰ります。", "Uchi e kaerimasu.", "Je rentre chez moi."),
        VocabularyWord("3_17", 3, "売り場", "うりば", "uriba", "Rayon de vente", WordCategory.NOUN, "ワインの 売り場です。", "Wain no uriba desu.", "C'est le rayon du vin."),
        VocabularyWord("3_18", 3, "地下", "ちか", "chika", "Sous-sol", WordCategory.NOUN, "地下１階です。", "Chika ikkai desu.", "C'est au premier sous-sol."),
        VocabularyWord("3_19", 3, "〜階", "〜かい / 〜がい", "~kai / ~gai", "Étage", WordCategory.NOUN, "何階ですか。", "Nangai desu ka.", "À quel étage est-ce ?"),
        VocabularyWord("3_20", 3, "いくら", "いくら", "ikura", "Combien (prix)", WordCategory.NOUN, "これは いくらですか。", "Kore wa ikura desu ka.", "Combien coûte ceci ?"),
        VocabularyWord("3_21", 3, "百", "ひゃく", "hyaku", "Cent (100)", WordCategory.NOUN, "三百円です。", "Sanbyaku-en desu.", "C'est 300 yens."),
        VocabularyWord("3_22", 3, "千", "せん", "sen", "Mille (1 000)", WordCategory.NOUN, "五千円です。", "Gosen-en desu.", "C'est 5 000 yens."),
        VocabularyWord("3_23", 3, "万", "まん", "man", "Dix-mille (10 000)", WordCategory.NOUN, "一万円です。", "Ichiman-en desu.", "C'est 10 000 yens."),
        VocabularyWord("3_24", 3, "靴", "くつ", "kutsu", "Chaussures", WordCategory.NOUN, "イタリアの 靴です。", "Itaria no kutsu desu.", "Ce sont des chaussures italiennes.")
    )

    // ==========================================
    // LEÇON 4 : L'Heure, Jours de la semaine & Verbes Quotidiens
    // ==========================================
    val lesson4Vocabulary: List<VocabularyWord> = listOf(
        VocabularyWord("4_1", 4, "起きます", "おきます", "okimasu", "Se lever / Se réveiller", WordCategory.VERB_G2, "毎朝 ６時に 起きます。", "Maiasa rokuji ni okimasu.", "Je me lève à 6h chaque matin."),
        VocabularyWord("4_2", 4, "寝ます", "ねます", "nemasu", "Dormir / Se coucher", WordCategory.VERB_G2, "１１時に 寝ます。", "Juuichiji ni nemasu.", "Je me couche à 23h."),
        VocabularyWord("4_3", 4, "働きます", "はたらきます", "hatarakimasu", "Travailler", WordCategory.VERB_G1, "９時から ５時まで 働きます。", "Kuji kara goji made hatarakimasu.", "Je travaille de 9h à 17h."),
        VocabularyWord("4_4", 4, "休みます", "やすみます", "yasumimasu", "Se reposer / Prendre congé", WordCategory.VERB_G1, "日曜日 休みます。", "Nichiyoubi yasumimasu.", "Je me repose le dimanche."),
        VocabularyWord("4_5", 4, "勉強します", "べんきょうします", "benkyoushimasu", "Étudier", WordCategory.VERB_G3, "日本語を 勉強します。", "Nihongo o benkyoushimasu.", "J'étudie le japonais."),
        VocabularyWord("4_6", 4, "終わります", "おわります", "owarimasu", "Se terminer / Finir", WordCategory.VERB_G1, "授業は ５時に 終わります。", "Jugyou wa goji ni owarimasu.", "Le cours se termine à 17h."),
        VocabularyWord("4_7", 4, "銀行", "ぎんこう", "ginkou", "Banque", WordCategory.NOUN, "銀行は ９時からです。", "Ginkou wa kuji kara desu.", "La banque ouvre à partir de 9h."),
        VocabularyWord("4_8", 4, "郵便局", "ゆうびんきょく", "yuubinkyoku", "Bureau de poste", WordCategory.NOUN, "郵便局へ 行きます。", "Yuubinkyoku e ikimasu.", "Je vais à la poste."),
        VocabularyWord("4_9", 4, "図書館", "としょかん", "toshokan", "Bibliothèque", WordCategory.NOUN, "図書館で 勉強します。", "Toshokan de benkyoushimasu.", "J'étudie à la bibliothèque."),
        VocabularyWord("4_10", 4, "美術館", "びじゅつかん", "bijutsukan", "Musée d'art", WordCategory.NOUN, "美術館は 休みです。", "Bijutsukan wa yasumi desu.", "Le musée est fermé."),
        VocabularyWord("4_11", 4, "今", "いま", "ima", "Maintenant", WordCategory.NOUN, "今 何時ですか。", "Ima nanji desu ka.", "Quelle heure est-il maintenant ?"),
        VocabularyWord("4_12", 4, "〜時", "〜じ", "~ji", "Heure (o'clock)", WordCategory.NOUN, "４時です。", "Yoji desu.", "Il est 4 heures."),
        VocabularyWord("4_13", 4, "〜分", "〜ふん / 〜ぷん", "~fun / ~pun", "Minute", WordCategory.NOUN, "１５分です。", "Juugofun desu.", "Il est et quart (15 minutes)."),
        VocabularyWord("4_14", 4, "半", "はん", "han", "Et demi", WordCategory.NOUN, "７時半です。", "Shichijihan desu.", "Il est 7h30."),
        VocabularyWord("4_15", 4, "午前", "ごぜん", "gozen", "Matin (A.M.)", WordCategory.NOUN, "午前９時です。", "Gozen kuji desu.", "Il est 9 heures du matin."),
        VocabularyWord("4_16", 4, "午後", "ごご", "gogo", "Après-midi (P.M.)", WordCategory.NOUN, "午後２時です。", "Gogo niji desu.", "Il est 14h (2h de l'après-midi)."),
        VocabularyWord("4_17", 4, "朝", "あさ", "asa", "Matin", WordCategory.NOUN, "朝 コーヒーを 飲みます。", "Asa koohii o nomimasu.", "Le matin je bois du café."),
        VocabularyWord("4_18", 4, "昼", "ひる", "hiru", "Midi / Journée", WordCategory.NOUN, "昼ご飯を 食べます。", "Hirugohan o tabemasu.", "Je déjeune le midi."),
        VocabularyWord("4_19", 4, "晩", "ばん", "ban", "Soir / Nuit", WordCategory.NOUN, "晩 本を 読みます。", "Ban hon o yomimasu.", "Le soir je lis un livre."),
        VocabularyWord("4_20", 4, "昨日", "きのう", "kinou", "Hier", WordCategory.NOUN, "昨日は 勉強しました。", "Kinou wa benkyoushimashita.", "Hier j'ai étudié."),
        VocabularyWord("4_21", 4, "今日", "きょう", "kyou", "Aujourd'hui", WordCategory.NOUN, "今日は 月曜日です。", "Kyou wa getsuyoubi desu.", "Aujourd'hui nous sommes lundi."),
        VocabularyWord("4_22", 4, "明日", "あした", "ashita", "Demain", WordCategory.NOUN, "明日 休みます。", "Ashita yasumimasu.", "Demain je me repose."),
        VocabularyWord("4_23", 4, "月曜日", "げつようび", "getsuyoubi", "Lundi", WordCategory.NOUN, "月曜日に 働きます。", "Getsuyoubi ni hatarakimasu.", "Je travaille le lundi."),
        VocabularyWord("4_24", 4, "日曜日", "にちようび", "nichiyoubi", "Dimanche", WordCategory.NOUN, "日曜日は 休みです。", "Nichiyoubi wa yasumi desu.", "Le dimanche est un jour de congé.")
    )

    // ==========================================
    // LEÇON 5 : Déplacements & Dates (へ 行きます, で, 日付)
    // ==========================================
    val lesson5Vocabulary: List<VocabularyWord> = listOf(
        VocabularyWord("5_1", 5, "行きます", "いきます", "ikimasu", "Aller", WordCategory.VERB_G1, "京都へ 行きます。", "Kyouto e ikimasu.", "Je vais à Kyoto."),
        VocabularyWord("5_2", 5, "来ます", "きます", "kimasu", "Venir", WordCategory.VERB_G3, "日本へ 来ました。", "Nihon e kimashita.", "Je suis venu au Japon."),
        VocabularyWord("5_3", 5, "帰ります", "かえります", "kaerimasu", "Rentrer chez soi", WordCategory.VERB_G1, "うちへ 帰ります。", "Uchi e kaerimasu.", "Je rentre à la maison."),
        VocabularyWord("5_4", 5, "学校", "がっこう", "gakkou", "École", WordCategory.NOUN, "学校へ 行きます。", "Gakkou e ikimasu.", "Je vais à l'école."),
        VocabularyWord("5_5", 5, "駅", "えき", "eki", "Gare", WordCategory.NOUN, "駅まで 歩きます。", "Eki made arukimasu.", "Je marche jusqu'à la gare."),
        VocabularyWord("5_6", 5, "飛行機", "ひこうき", "hikouki", "Avion", WordCategory.NOUN, "飛行機で 行きました。", "Hikouki de ikimashita.", "J'y suis allé en avion."),
        VocabularyWord("5_7", 5, "電車", "でんしゃ", "densha", "Train électrique", WordCategory.NOUN, "電車で 帰ります。", "Densha de kaerimasu.", "Je rentre en train."),
        VocabularyWord("5_8", 5, "地下鉄", "ちかてつ", "chikatetsu", "Métro", WordCategory.NOUN, "地下鉄に 乗ります。", "Chikatetsu ni norimasu.", "Je prends le métro."),
        VocabularyWord("5_9", 5, "新幹線", "しんかんせん", "shinkansen", "Shinkansen (TGV japonais)", WordCategory.NOUN, "新幹線は 速いです。", "Shinkansen wa hayai desu.", "Le Shinkansen est rapide."),
        VocabularyWord("5_10", 5, "自転車", "じてんしゃ", "jitensha", "Vélo / Bicyclette", WordCategory.NOUN, "自転車で 来ました。", "Jitensha de kimashita.", "Je suis venu à vélo."),
        VocabularyWord("5_11", 5, "歩いて", "あるいて", "aruite", "À pied", WordCategory.ADVERB, "歩いて 行きます。", "Aruite ikimasu.", "J'y vais à pied."),
        VocabularyWord("5_12", 5, "友達", "ともだち", "tomodachi", "Ami(e)", WordCategory.NOUN, "友達と 会います。", "Tomodachi to aimasu.", "Je vois un ami."),
        VocabularyWord("5_13", 5, "家族", "かぞく", "kazoku", "Famille", WordCategory.NOUN, "家族と 旅行します。", "Kazoku to ryokoushimasu.", "Je voyage avec ma famille."),
        VocabularyWord("5_14", 5, "一人で", "ひとりで", "hitoride", "Seul(e)", WordCategory.ADVERB, "一人で 行きました。", "Hitori de ikimashita.", "J'y suis allé seul."),
        VocabularyWord("5_15", 5, "先週", "せんしゅう", "senshuu", "La semaine dernière", WordCategory.NOUN, "先週 買いました。", "Senshuu kaimashita.", "J'ai acheté la semaine dernière."),
        VocabularyWord("5_16", 5, "今週", "こんしゅう", "konshuu", "Cette semaine", WordCategory.NOUN, "今週 忙しいです。", "Konshuu isogashii desu.", "Cette semaine je suis occupé."),
        VocabularyWord("5_17", 5, "来週", "らいしゅう", "raishuu", "La semaine prochaine", WordCategory.NOUN, "来週 行きます。", "Raishuu ikimasu.", "J'y vais la semaine prochaine."),
        VocabularyWord("5_18", 5, "去年", "きょねん", "kyonen", "L'année dernière", WordCategory.NOUN, "去年 日本へ 来ました。", "Kyonen Nihon e kimashita.", "Je suis venu au Japon l'année dernière."),
        VocabularyWord("5_19", 5, "今年", "ことし", "kotoshi", "Cette année", WordCategory.NOUN, "今年 卒業します。", "Kotoshi sotsugyoushimasu.", "Je serai diplômé cette année."),
        VocabularyWord("5_20", 5, "誕生日", "たんじょうび", "tanjoubi", "Anniversaire", WordCategory.NOUN, "誕生日は いつですか。", "Tanjoubi wa itsu desu ka.", "Quand est votre anniversaire ?"),
        VocabularyWord("5_21", 5, "一日", "ついたち", "tsuitachi", "Le 1er du mois", WordCategory.NOUN, "４月１日です。", "Shigatsu tsuitachi desu.", "C'est le 1er avril."),
        VocabularyWord("5_22", 5, "いつ", "いつ", "itsu", "Quand", WordCategory.NOUN, "いつ 行きますか。", "Itsu ikimasu ka.", "Quand partez-vous ?")
    )

    // ==========================================
    // LEÇON 6 : Actions & Verbes Transitifs (を 食べます, で, 〜ませんか)
    // ==========================================
    val lesson6Vocabulary: List<VocabularyWord> = listOf(
        VocabularyWord("6_1", 6, "食べます", "たべます", "tabemasu", "Manger", WordCategory.VERB_G2, "パンを 食べます。", "Pan o tabemasu.", "Je mange du pain."),
        VocabularyWord("6_2", 6, "飲みます", "のみます", "nomimasu", "Boire", WordCategory.VERB_G1, "お茶を 飲みます。", "Ocha o nomimasu.", "Je bois du thé vert."),
        VocabularyWord("6_3", 6, "吸います", "すいます", "suimasu", "Fumer [tabac]", WordCategory.VERB_G1, "たばこを 吸いません。", "Tabako o suimasen.", "Je ne fume pas."),
        VocabularyWord("6_4", 6, "見ます", "みます", "mimasu", "Regarder / Voir", WordCategory.VERB_G2, "映画を 見ます。", "Eiga o mimasu.", "Je regarde un film."),
        VocabularyWord("6_5", 6, "聞きます", "ききます", "kikimasu", "Écouter / Entendre", WordCategory.VERB_G1, "音楽を 聞きます。", "Ongaku o kikimasu.", "J'écoute de la musique."),
        VocabularyWord("6_6", 6, "読みます", "よみます", "yomimasu", "Lire", WordCategory.VERB_G1, "本を 読みます。", "Hon o yomimasu.", "Je lis un livre."),
        VocabularyWord("6_7", 6, "書きます", "かきます", "kakimasu", "Écrire / Dessiner", WordCategory.VERB_G1, "手紙を 書きます。", "Tegami o kakimasu.", "J'écris une lettre."),
        VocabularyWord("6_8", 6, "買います", "かいます", "kaimasu", "Acheter", WordCategory.VERB_G1, "パンを 買いました。", "Pan o kaimashita.", "J'ai acheté du pain."),
        VocabularyWord("6_9", 6, "撮ります", "とります", "torimasu", "Prendre (une photo)", WordCategory.VERB_G1, "写真を 撮ります。", "Shashin o torimasu.", "Je prends une photo."),
        VocabularyWord("6_10", 6, "します", "します", "shimasu", "Faire", WordCategory.VERB_G3, "宿題を します。", "Shukudai o shimasu.", "Je fais mes devoirs."),
        VocabularyWord("6_11", 6, "会います", "あいます", "aimasu", "Rencontrer (友達に会います)", WordCategory.VERB_G1, "友達に 会いました。", "Tomodachi ni aimashita.", "J'ai rencontré un ami."),
        VocabularyWord("6_12", 6, "ご飯", "ごはん", "gohan", "Repas / Riz cuit", WordCategory.NOUN, "朝ご飯を 食べます。", "Asagohan o tabemasu.", "Je prends le petit-déjeuner."),
        VocabularyWord("6_13", 6, "パン", "パン", "pan", "Pain", WordCategory.NOUN, "おいしい パンです。", "Oishii pan desu.", "C'est un bon pain."),
        VocabularyWord("6_14", 6, "卵", "たまご", "tamago", "Œuf", WordCategory.NOUN, "卵を 食べます。", "Tamago o tabemasu.", "Je mange des œufs."),
        VocabularyWord("6_15", 6, "肉", "にく", "niku", "Viande", WordCategory.NOUN, "肉を 食べません。", "Niku o tabemasen.", "Je ne mange pas de viande."),
        VocabularyWord("6_16", 6, "魚", "さかな", "sakana", "Poisson", WordCategory.NOUN, "魚が 好きです。", "Sakana ga suki desu.", "J'aime le poisson."),
        VocabularyWord("6_17", 6, "野菜", "やさい", "yasai", "Légume", WordCategory.NOUN, "野菜を 買います。", "Yasai o kaimasu.", "J'achète des légumes."),
        VocabularyWord("6_18", 6, "水", "みず", "mizu", "Eau", WordCategory.NOUN, "水を 飲みます。", "Mizu o nomimasu.", "Je bois de l'eau."),
        VocabularyWord("6_19", 6, "お茶", "おちゃ", "ocha", "Thé vert japonais", WordCategory.NOUN, "温かい お茶です。", "Atatakai ocha desu.", "C'est un thé chaud."),
        VocabularyWord("6_20", 6, "写真", "しゃしん", "shashin", "Photo / Photographie", WordCategory.NOUN, "写真を 見せてください。", "Shashin o misete kudasai.", "Montrez-moi les photos s'il vous plaît."),
        VocabularyWord("6_21", 6, "庭", "にわ", "niwa", "Jardin", WordCategory.NOUN, "庭で 遊びます。", "Niwa de asobimasu.", "On joue dans le jardin."),
        VocabularyWord("6_22", 6, "一緒に", "いっしょに", "isshoni", "Ensemble", WordCategory.ADVERB, "一緒に 食べませんか。", "Isshoni tabemasen ka.", "Si nous mangions ensemble ?"),
        VocabularyWord("6_23", 6, "ちょっと", "ちょっと", "chotto", "Un peu / Un moment", WordCategory.ADVERB, "ちょっと 待ってください。", "Chotto matte kudasai.", "Attendez un petit instant.")
    )

    // ==========================================
    // LEÇON 7 : Moyens, Échanges & Cadeaux (で, あげます, もらいます)
    // ==========================================
    val lesson7Vocabulary: List<VocabularyWord> = listOf(
        VocabularyWord("7_1", 7, "切ります", "きります", "kirimasu", "Couper", WordCategory.VERB_G1, "ナイフで 切ります。", "Naifu de kirimasu.", "Je coupe avec un couteau."),
        VocabularyWord("7_2", 7, "送ります", "おくります", "okurimasu", "Envoyer / Expédier", WordCategory.VERB_G1, "荷物を 送ります。", "Nimotsu o okurimasu.", "J'envoie un colis."),
        VocabularyWord("7_3", 7, "あげます", "あげます", "agemasu", "Donner / Offrir", WordCategory.VERB_G2, "花を あげます。", "Hana o agemasu.", "J'offre des fleurs."),
        VocabularyWord("7_4", 7, "もらいます", "もらいます", "moraimasu", "Recevoir", WordCategory.VERB_G1, "プレゼントを もらいました。", "Purezento o moraimashita.", "J'ai reçu un cadeau."),
        VocabularyWord("7_5", 7, "貸します", "かします", "kashimasu", "Prêter", WordCategory.VERB_G1, "本を 貸します。", "Hon o kashimasu.", "Je prête un livre."),
        VocabularyWord("7_6", 7, "借ります", "かります", "karimasu", "Emprunter", WordCategory.VERB_G2, "お金を 借りました。", "Okane o karimashita.", "J'ai emprunté de l'argent."),
        VocabularyWord("7_7", 7, "教えます", "おしえます", "oshiemasu", "Enseigner / Renseigner", WordCategory.VERB_G2, "日本語を 教えます。", "Nihongo o oshiemasu.", "J'enseigne le japonais."),
        VocabularyWord("7_8", 7, "習います", "ならいます", "naraimasu", "Apprendre (d'un maître)", WordCategory.VERB_G1, "生け花を 習います。", "Ikebana o naraimasu.", "J'apprends l'arrangement floral."),
        VocabularyWord("7_9", 7, "かけます", "かけます", "kakemasu", "Passer (un coup de fil)", WordCategory.VERB_G2, "電話を かけます。", "Denwa o kakemasu.", "Je passe un appel téléphonique."),
        VocabularyWord("7_10", 7, "手", "て", "te", "Main", WordCategory.NOUN, "手で 食べます。", "Te de tabemasu.", "Je mange avec les mains."),
        VocabularyWord("7_11", 7, "箸", "はし", "hashi", "Baguettes de repas", WordCategory.NOUN, "箸で 食べます。", "Hashi de tabemasu.", "Je mange avec des baguettes."),
        VocabularyWord("7_12", 7, "スプーン", "スプーン", "supuun", "Cuillère", WordCategory.NOUN, "スプーンを ください。", "Supuun o kudasai.", "Une cuillère s'il vous plaît."),
        VocabularyWord("7_13", 7, "はさみ", "はさみ", "hasami", "Ciseaux", WordCategory.NOUN, "はさみで 紙を 切ります。", "Hasami de kami o kirimasu.", "Je coupe du papier aux ciseaux."),
        VocabularyWord("7_14", 7, "消しゴム", "けしゴム", "keshigomu", "Gomme à effacer", WordCategory.NOUN, "消しゴムを 借ります。", "Keshigomu o karimasu.", "J'emprunte une gomme."),
        VocabularyWord("7_15", 7, "紙", "かみ", "kami", "Papier", WordCategory.NOUN, "紙に 書きます。", "Kami ni kakimasu.", "J'écris sur une feuille."),
        VocabularyWord("7_16", 7, "花", "はな", "hana", "Fleur", WordCategory.NOUN, "きれいな 花です。", "Kirei na hana desu.", "C'est une belle fleur."),
        VocabularyWord("7_17", 7, "プレゼント", "プレゼント", "purezento", "Cadeau", WordCategory.NOUN, "誕生日プレゼントです。", "Tanjoubi purezento desu.", "C'est un cadeau d'anniversaire."),
        VocabularyWord("7_18", 7, "荷物", "にもつ", "nimotsu", "Bagages / Colis", WordCategory.NOUN, "荷物を 持ちます。", "Nimotsu o mochimasu.", "Je porte les bagages."),
        VocabularyWord("7_19", 7, "お金", "おかね", "okane", "Argent (monnaie)", WordCategory.NOUN, "お金が ありません。", "Okane ga arimasen.", "Je n'ai pas d'argent."),
        VocabularyWord("7_20", 7, "切符", "きっぷ", "kippu", "Billet / Ticket de train", WordCategory.NOUN, "電車の 切符を 買います。", "Densha no kippu o kaimasu.", "J'achète un ticket de train."),
        VocabularyWord("7_21", 7, "父", "ちち", "chichi", "Mon père", WordCategory.NOUN, "父に 電話します。", "Chichi ni denwashimasu.", "J'appelle mon père."),
        VocabularyWord("7_22", 7, "母", "はは", "haha", "Ma mère", WordCategory.NOUN, "母の 料理です。", "Haha no ryouri desu.", "C'est la cuisine de ma mère."),
        VocabularyWord("7_23", 7, "もう", "もう", "mou", "Déjà", WordCategory.ADVERB, "もう 食べました。", "Mou tabemashita.", "J'ai déjà mangé."),
        VocabularyWord("7_24", 7, "まだ", "まだ", "mada", "Pas encore", WordCategory.ADVERB, "いいえ、まだです。", "Iie, mada desu.", "Non, pas encore.")
    )

    // ==========================================
    // LEÇON 8 : Adjectifs en -i et en -na
    // ==========================================
    val lesson8Vocabulary: List<VocabularyWord> = listOf(
        VocabularyWord("8_1", 8, "ハンサム[な]", "ハンサム", "hansamu", "Beau garçon / Séduisant", WordCategory.ADJ_NA, "ハンサムな 人です。", "Hansamu na hito desu.", "C'est un bel homme."),
        VocabularyWord("8_2", 8, "きれい[な]", "きれい", "kirei", "Beau / Joli / Propre", WordCategory.ADJ_NA, "桜は きれいです。", "Sakura wa kirei desu.", "Les fleurs de cerisier sont belles."),
        VocabularyWord("8_3", 8, "静か[な]", "しずか", "shizuka", "Calme / Tranquille", WordCategory.ADJ_NA, "静かな 町です。", "Shizuka na machi desu.", "C'est une ville calme."),
        VocabularyWord("8_4", 8, "賑やか[な]", "にぎやか", "nigiyaka", "Animé / Vivant", WordCategory.ADJ_NA, "東京は 賑やかです。", "Toukyou wa nigiyaka desu.", "Tokyo est animé."),
        VocabularyWord("8_5", 8, "有名[な]", "ゆうめい", "yuumei", "Célèbre / Connu", WordCategory.ADJ_NA, "有名な お寺です。", "Yuumei na otera desu.", "C'est un temple célèbre."),
        VocabularyWord("8_6", 8, "親切[な]", "しんせつ", "shinsetsu", "Aimable / Gentil", WordCategory.ADJ_NA, "親切な 人です。", "Shinsetsu na hito desu.", "C'est une personne gentille."),
        VocabularyWord("8_7", 8, "元気[な]", "げんき", "genki", "En forme / Énergique", WordCategory.ADJ_NA, "お元気ですか。", "Ogenki desu ka.", "Comment allez-vous ?"),
        VocabularyWord("8_8", 8, "暇[な]", "ひま", "hima", "Libre / Disponible", WordCategory.ADJ_NA, "明日は 暇です。", "Ashita wa hima desu.", "Demain je suis libre."),
        VocabularyWord("8_9", 8, "便利[な]", "べんり", "benri", "Pratique / Commode", WordCategory.ADJ_NA, "地下鉄は 便利です。", "Chikatetsu wa benri desu.", "Le métro est pratique."),
        VocabularyWord("8_10", 8, "大きい", "おおきい", "ookii", "Grand", WordCategory.ADJ_I, "大きい 家です。", "Ookii uchi desu.", "C'est une grande maison."),
        VocabularyWord("8_11", 8, "小さい", "ちいさい", "chiisai", "Petit", WordCategory.ADJ_I, "小さい 車です。", "Chiisai kuruma desu.", "C'est une petite voiture."),
        VocabularyWord("8_12", 8, "新しい", "あたらしい", "atarashii", "Neuf / Nouveau", WordCategory.ADJ_I, "新しい パソコンです。", "Atarashii pasokon desu.", "C'est un nouvel ordinateur."),
        VocabularyWord("8_13", 8, "古い", "ふるい", "furui", "Vieux / Ancien", WordCategory.ADJ_I, "古い お寺です。", "Furui otera desu.", "C'est un vieux temple."),
        VocabularyWord("8_14", 8, "いい", "いい", "ii", "Bon / Bien", WordCategory.ADJ_I, "天気が いいです。", "Tenki ga ii desu.", "Il fait beau temps."),
        VocabularyWord("8_15", 8, "悪い", "わるい", "warui", "Mauvais", WordCategory.ADJ_I, "天気が 悪いです。", "Tenki ga warui desu.", "Le temps est mauvais."),
        VocabularyWord("8_16", 8, "暑い", "あつい", "atsui", "Chaud (climat)", WordCategory.ADJ_I, "日本の 夏は 暑いです。", "Nihon no natsu wa atsui desu.", "L'été japonais est chaud."),
        VocabularyWord("8_17", 8, "寒い", "さむい", "samui", "Froid (climat)", WordCategory.ADJ_I, "冬は 寒いです。", "Fuyu wa samui desu.", "L'hiver est froid."),
        VocabularyWord("8_18", 8, "冷たい", "つめたい", "tsumetai", "Frais / Froid au toucher", WordCategory.ADJ_I, "冷たい ジュースです。", "Tsumetai juusu desu.", "C'est un jus bien frais."),
        VocabularyWord("8_19", 8, "難しい", "むずかしい", "muzukashii", "Difficile", WordCategory.ADJ_I, "漢字は 難しいです。", "Kanji wa muzukashii desu.", "Les kanji sont difficiles."),
        VocabularyWord("8_20", 8, "易しい", "やさしい", "yasashii", "Facile / Simple", WordCategory.ADJ_I, "ひらがなは 易しいです。", "Hiragana wa yasashii desu.", "Les hiragana sont faciles."),
        VocabularyWord("8_21", 8, "高い", "たかい", "takai", "Cher / Élevé / Haut", WordCategory.ADJ_I, "富士山は 高いです。", "Fujisan wa takai desu.", "Le mont Fuji est haut."),
        VocabularyWord("8_22", 8, "安い", "やすい", "yasui", "Bon marché / Pas cher", WordCategory.ADJ_I, "この店は 安いです。", "Kono mise wa yasui desu.", "Ce magasin n'est pas cher."),
        VocabularyWord("8_23", 8, "おいしい", "おいしい", "oishii", "Délicieux", WordCategory.ADJ_I, "日本料理は おいしいです。", "Nihon ryouri wa oishii desu.", "La cuisine japonaise est délicieuse."),
        VocabularyWord("8_24", 8, "忙しい", "いそがしい", "isogashii", "Occupé", WordCategory.ADJ_I, "今日は 忙しいです。", "Kyou wa isogashii desu.", "Aujourd'hui je suis très occupé.")
    )

    // ==========================================
    // LEÇON 9 : Goûts, Aptitudes & Causes (好き, 上手, から)
    // ==========================================
    val lesson9Vocabulary: List<VocabularyWord> = listOf(
        VocabularyWord("9_1", 9, "分かります", "わかります", "wakarimasu", "Comprendre / Saisir", WordCategory.VERB_G1, "日本語が 分かります。", "Nihongo ga wakarimasu.", "Je comprends le japonais."),
        VocabularyWord("9_2", 9, "あります", "あります", "arimasu", "Avoir / Posséder", WordCategory.VERB_G1, "時間が ありません。", "Jikan ga arimasen.", "Je n'ai pas le temps."),
        VocabularyWord("9_3", 9, "好き[な]", "すき", "suki", "Aimé / Aimer", WordCategory.ADJ_NA, "スポーツが 好きです。", "Supootsu ga suki desu.", "J'aime le sport."),
        VocabularyWord("9_4", 9, "嫌い[な]", "きらい", "kirai", "Détesté / Ne pas aimer", WordCategory.ADJ_NA, "納豆が 嫌いです。", "Nattou ga kirai desu.", "Je déteste le natto."),
        VocabularyWord("9_5", 9, "上手[な]", "じょうず", "jouzu", "Doué / Habile", WordCategory.ADJ_NA, "歌が 上手です。", "Uta ga jouzu desu.", "Elle chante très bien."),
        VocabularyWord("9_6", 9, "下手[な]", "へた", "heta", "Maladroit / Pas doué", WordCategory.ADJ_NA, "テニスが 下手です。", "Tenisu ga heta desu.", "Je suis nul au tennis."),
        VocabularyWord("9_7", 9, "料理", "りょうり", "ryouri", "Cuisine / Plat", WordCategory.NOUN, "フランス料理が 好きです。", "Furansu ryouri ga suki desu.", "J'aime la cuisine française."),
        VocabularyWord("9_8", 9, "飲み物", "のみもの", "nomimono", "Boisson", WordCategory.NOUN, "冷たい 飲み物です。", "Tsumetai nomimono desu.", "Une boisson fraîche."),
        VocabularyWord("9_9", 9, "スポーツ", "スポーツ", "supootsu", "Sport", WordCategory.NOUN, "サッカーを します。", "Sakkaa o shimasu.", "Je fais du foot."),
        VocabularyWord("9_10", 9, "音楽", "おんがく", "ongaku", "Musique", WordCategory.NOUN, "クラシック音楽です。", "Kurashikku ongaku desu.", "C'est de la musique classique."),
        VocabularyWord("9_11", 9, "歌", "うた", "uta", "Chanson", WordCategory.NOUN, "日本の 歌を 歌います。", "Nihon no uta o utaimasu.", "Je chante une chanson japonaise."),
        VocabularyWord("9_12", 9, "絵", "え", "e", "Dessin / Peinture", WordCategory.NOUN, "絵を 見ます。", "E o mimasu.", "Je regarde des tableaux."),
        VocabularyWord("9_13", 9, "字", "じ", "ji", "Caractère / Écriture", WordCategory.NOUN, "きれいな 字ですね。", "Kirei na ji desu ne.", "Quelle belle écriture !"),
        VocabularyWord("9_14", 9, "漢字", "かんじ", "kanji", "Kanji (caractères chinois)", WordCategory.NOUN, "漢字を 勉強します。", "Kanji o benkyoushimasu.", "J'étudie les kanji."),
        VocabularyWord("9_15", 9, "時間", "じかん", "jikan", "Temps / Heure", WordCategory.NOUN, "約束の 時間です。", "Yakusoku no jikan desu.", "C'est l'heure du rendez-vous."),
        VocabularyWord("9_16", 9, "用事", "ようじ", "youji", "Affaire à faire / Course", WordCategory.NOUN, "今日は 用事が あります。", "Kyou wa youji ga arimasu.", "J'ai des choses à faire aujourd'hui."),
        VocabularyWord("9_17", 9, "約束", "やくそく", "yakusoku", "Rendez-vous / Promesse", WordCategory.NOUN, "友達と 約束が あります。", "Tomodachi to yakusoku ga arimasu.", "J'ai un rendez-vous avec un ami."),
        VocabularyWord("9_18", 9, "たくさん", "たくさん", "takusan", "Beaucoup", WordCategory.ADVERB, "本が たくさん あります。", "Hon ga takusan arimasu.", "Il y a beaucoup de livres."),
        VocabularyWord("9_19", 9, "少し", "すこし", "sukoshi", "Un peu", WordCategory.ADVERB, "少し 分かります。", "Sukoshi wakarimasu.", "Je comprends un peu."),
        VocabularyWord("9_20", 9, "全然", "ぜんぜん", "zenzen", "Pas du tout (+ négatif)", WordCategory.ADVERB, "全然 分かりません。", "Zenzen wakarimasen.", "Je ne comprends rien du tout."),
        VocabularyWord("9_21", 9, "早く", "はやく", "hayaku", "Tôt / Vite", WordCategory.ADVERB, "早く 帰ります。", "Hayaku kaerimasu.", "Je rentre tôt."),
        VocabularyWord("9_22", 9, "どうして", "どうして", "doushite", "Pourquoi", WordCategory.ADVERB, "どうして 行きませんか。", "Doushite ikimasen ka.", "Pourquoi n'y allez-vous pas ?")
    )

    // ==========================================
    // LEÇON 10 : Existence & Repérage dans l'espace (あります, います, 上, 下...)
    // ==========================================
    val lesson10Vocabulary: List<VocabularyWord> = listOf(
        VocabularyWord("10_1", 10, "あります", "あります", "arimasu", "Il y a / Exister (inanimé)", WordCategory.VERB_G1, "机の 上に 本が あります。", "Tsukue no ue ni hon ga arimasu.", "Il y a un livre sur la table."),
        VocabularyWord("10_2", 10, "います", "います", "imasu", "Il y a / Être (être vivant)", WordCategory.VERB_G2, "庭に 猫が います。", "Niwa ni neko ga imasu.", "Il y a un chat dans le jardin."),
        VocabularyWord("10_3", 10, "いろいろ[な]", "いろいろ", "iroiro", "Divers / Varié", WordCategory.ADJ_NA, "いろいろな 物が あります。", "Iroiro na mono ga arimasu.", "Il y a divers objets."),
        VocabularyWord("10_4", 10, "男の人", "おとこのひと", "otoko no hito", "Homme", WordCategory.NOUN, "あの 男の人は だれですか。", "Ano otoko no hito wa dare desu ka.", "Qui est cet homme ?"),
        VocabularyWord("10_5", 10, "女の人", "おんなのひと", "onna no hito", "Femme", WordCategory.NOUN, "親切な 女の人です。", "Shinsetsu na onna no hito desu.", "C'est une femme aimable."),
        VocabularyWord("10_6", 10, "男の子", "おとこのこ", "otoko no ko", "Garçon", WordCategory.NOUN, "男の子が 遊んでいます。", "Otoko no ko ga asonde imasu.", "Un garçon est en train de jouer."),
        VocabularyWord("10_7", 10, "女の子", "おんなのこ", "onna no ko", "Fille", WordCategory.NOUN, "かわいい 女の子です。", "Kawaii onna no ko desu.", "C'est une jolie petite fille."),
        VocabularyWord("10_8", 10, "犬", "いぬ", "inu", "Chien", WordCategory.NOUN, "白い 犬が います。", "Shiroi inu ga imasu.", "Il y a un chien blanc."),
        VocabularyWord("10_9", 10, "猫", "ねこ", "neko", "Chat", WordCategory.NOUN, "猫が 好きです。", "Neko ga suki desu.", "J'aime les chats."),
        VocabularyWord("10_10", 10, "木", "き", "ki", "Arbre / Bois", WordCategory.NOUN, "大きい 木が あります。", "Ookii ki ga arimasu.", "Il y a un grand arbre."),
        VocabularyWord("10_11", 10, "物", "もの", "mono", "Chose / Objet matériel", WordCategory.NOUN, "いろいろな 物を 買いました。", "Iroiro na mono o kaimashita.", "J'ai acheté diverses choses."),
        VocabularyWord("10_12", 10, "箱", "はこ", "hako", "Boîte", WordCategory.NOUN, "箱の 中に あります。", "Hako no naka ni arimasu.", "C'est dans la boîte."),
        VocabularyWord("10_13", 10, "冷蔵庫", "れいぞうこ", "reizouko", "Réfrigérateur", WordCategory.NOUN, "冷蔵庫に ビールが あります。", "Reizouko ni biiru ga arimasu.", "Il y a de la bière dans le frigo."),
        VocabularyWord("10_14", 10, "棚", "たな", "tana", "Étagère", WordCategory.NOUN, "本棚に 本が あります。", "Hondana ni hon ga arimasu.", "Il y a des livres sur l'étagère."),
        VocabularyWord("10_15", 10, "窓", "まど", "mado", "Fenêtre", WordCategory.NOUN, "窓を 開けます。", "Mado o akemasu.", "J'ouvre la fenêtre."),
        VocabularyWord("10_16", 10, "公園", "こうえん", "kouen", "Parc public", WordCategory.NOUN, "公園を 散歩します。", "Kouen o sanposhimasu.", "Je me promène au parc."),
        VocabularyWord("10_17", 10, "本屋", "ほんや", "honya", "Librairie", WordCategory.NOUN, "本屋で 雑誌を 買いました。", "Honya de zasshi o kaimashita.", "J'ai acheté une revue à la librairie."),
        VocabularyWord("10_18", 10, "上", "うえ", "ue", "Haut / Dessus", WordCategory.NOUN, "机の 上です。", "Tsukue no ue desu.", "Sur la table."),
        VocabularyWord("10_19", 10, "下", "した", "shita", "Bas / Dessous", WordCategory.NOUN, "机の 下に 猫が います。", "Tsukue no shita ni neko ga imasu.", "Il y a un chat sous la table."),
        VocabularyWord("10_20", 10, "前", "まえ", "mae", "Devant / Avant", WordCategory.NOUN, "駅の 前で 会いましょう。", "Eki no mae de aimashou.", "Retrouvons-nous devant la gare."),
        VocabularyWord("10_21", 10, "後ろ", "うしろ", "ushiro", "Derrière", WordCategory.NOUN, "後ろを 見てください。", "Ushiro o mite kudasai.", "Regardez derrière vous."),
        VocabularyWord("10_22", 10, "右", "みぎ", "migi", "Droite", WordCategory.NOUN, "右へ 曲がります。", "Migi e magarimasu.", "Tournez à droite."),
        VocabularyWord("10_23", 10, "左", "ひだり", "hidari", "Gauche", WordCategory.NOUN, "左に 郵便局が あります。", "Hidari ni yuubinkyoku ga arimasu.", "Il y a la poste sur la gauche."),
        VocabularyWord("10_24", 10, "中", "なか", "naka", "Dedans / Intérieur", WordCategory.NOUN, "鞄の 中に 鍵が あります。", "Kaban no naka ni kagi ga arimasu.", "La clé est dans le sac."),
        VocabularyWord("10_25", 10, "隣", "となり", "tonari", "À côté / Voisin immédiat", WordCategory.NOUN, "銀行の 隣です。", "Ginkou no tonari desu.", "C'est à côté de la banque."),
        VocabularyWord("10_26", 10, "間", "あいだ", "aida", "Entre", WordCategory.NOUN, "本屋と 銀行の 間です。", "Honya to ginkou no aida desu.", "Entre la librairie et la banque.")
    )

    // ==========================================
    // LEÇON 11 : Compteurs & Durées (ひとつ, 1人, どのくらい)
    // ==========================================
    val lesson11Vocabulary: List<VocabularyWord> = listOf(
        VocabularyWord("11_1", 11, "ひとつ", "ひとつ", "hitotsu", "Un (compteur générique)", WordCategory.NOUN, "りんごを ひとつ ください。", "Ringo o hitotsu kudasai.", "Une pomme s'il vous plaît."),
        VocabularyWord("11_2", 11, "ふたつ", "ふたつ", "futatsu", "Deux (choses)", WordCategory.NOUN, "みかんを ふたつ 買いました。", "Mikan o futatsu kaimashita.", "J'ai acheté deux mandarines."),
        VocabularyWord("11_3", 11, "みっつ", "みっつ", "mittsu", "Trois (choses)", WordCategory.NOUN, "パンを みっつ 食べました。", "Pan o mittsu tabemashita.", "J'ai mangé trois pains."),
        VocabularyWord("11_4", 11, "いくつ", "いくつ", "ikutsu", "Combien de choses", WordCategory.NOUN, "いくつ ありますか。", "Ikutsu arimasu ka.", "Combien y en a-t-il ?"),
        VocabularyWord("11_5", 11, "一人", "ひとり", "hitori", "Une personne", WordCategory.NOUN, "一人で 旅行します。", "Hitori de ryokoushimasu.", "Je voyage seul."),
        VocabularyWord("11_6", 11, "二人", "ふたり", "futari", "Deux personnes", WordCategory.NOUN, "二人で 行きます。", "Futari de ikimasu.", "Nous y allons à deux."),
        VocabularyWord("11_7", 11, "〜台", "〜だい", "~dai", "Compteur machines / véhicules", WordCategory.NOUN, "車が ２台 あります。", "Kuruma ga nidai arimasu.", "Il y a deux voitures."),
        VocabularyWord("11_8", 11, "〜枚", "〜まい", "~mai", "Compteur objets plats / papier", WordCategory.NOUN, "切手を ５枚 ください。", "Kitte o gomai kudasai.", "Cinq timbres s'il vous plaît."),
        VocabularyWord("11_9", 11, "〜回", "〜かい", "~kai", "Nombre de fois / Fréquence", WordCategory.NOUN, "１週間に ２回 泳ぎます。", "Isshuukan ni nikai oyogimasu.", "Je nage 2 fois par semaine."),
        VocabularyWord("11_10", 11, "りんご", "りんご", "ringo", "Pomme", WordCategory.NOUN, "おいしい りんごです。", "Oishii ringo desu.", "C'est une pomme délicieuse."),
        VocabularyWord("11_11", 11, "みかん", "みかん", "mikan", "Mandarine / Clémentine", WordCategory.NOUN, "みかんを 食べます。", "Mikan o tabemasu.", "Je mange une mandarine."),
        VocabularyWord("11_12", 11, "切手", "きって", "kitte", "Timbre-poste", WordCategory.NOUN, "８４円の 切手です。", "Hachijuuyo-en no kitte desu.", "Un timbre de 84 yens."),
        VocabularyWord("11_13", 11, "葉書", "はがき", "hagaki", "Carte postale", WordCategory.NOUN, "葉書を 出します。", "Hagaki o dashimasu.", "J'envoie une carte postale."),
        VocabularyWord("11_14", 11, "封筒", "ふうとう", "fuutou", "Enveloppe", WordCategory.NOUN, "封筒に 手紙を 入れます。", "Fuutou ni tegami o iremasu.", "Je mets la lettre dans l'enveloppe."),
        VocabularyWord("11_15", 11, "両親", "りょうしん", "ryoushin", "Mes parents", WordCategory.NOUN, "両親は 元気です。", "Ryoushin wa genki desu.", "Mes parents vont bien."),
        VocabularyWord("11_16", 11, "兄弟", "きょうだい", "kyoudai", "Frères et sœurs", WordCategory.NOUN, "兄弟が ３人 います。", "Kyoudai ga sannin imasu.", "J'ai 3 frères et sœurs."),
        VocabularyWord("11_17", 11, "どのくらい", "どのくらい", "dono kurai", "Combien de temps / Combien environ", WordCategory.ADVERB, "どのくらい かかりますか。", "Dono kurai kakarimasu ka.", "Combien de temps cela prend-il ?"),
        VocabularyWord("11_18", 11, "全部で", "ぜんぶで", "zenbu de", "Au total / En tout", WordCategory.ADVERB, "全部で 千円です。", "Zenbu de sen-en desu.", "Cela fait 1000 yens en tout.")
    )

    // ==========================================
    // LEÇON 12 : Comparatifs, Saisons & Météo (〜より, 一番)
    // ==========================================
    val lesson12Vocabulary: List<VocabularyWord> = listOf(
        VocabularyWord("12_1", 12, "簡単[な]", "かんたん", "kantan", "Simple / Facile", WordCategory.ADJ_NA, "簡単な 料理です。", "Kantan na ryouri desu.", "C'est un plat simple."),
        VocabularyWord("12_2", 12, "近い", "ちかい", "chikai", "Proche / Près", WordCategory.ADJ_I, "駅から 近いです。", "Eki kara chikai desu.", "C'est tout près de la gare."),
        VocabularyWord("12_3", 12, "遠い", "とおい", "tooi", "Loin / Éloigné", WordCategory.ADJ_I, "会社まで 遠いです。", "Kaisha made tooi desu.", "C'est loin de l'entreprise."),
        VocabularyWord("12_4", 12, "早い", "はやい", "hayai", "Tôt / Matinal", WordCategory.ADJ_I, "朝 早く 起きます。", "Asa hayaku okimasu.", "Je me lève tôt le matin."),
        VocabularyWord("12_5", 12, "速い", "はやい", "hayai", "Rapide / Vitesse", WordCategory.ADJ_I, "新幹線は 速いです。", "Shinkansen wa hayai desu.", "Le Shinkansen est rapide."),
        VocabularyWord("12_6", 12, "遅い", "おそい", "osoi", "Tard / Lent", WordCategory.ADJ_I, "足が 遅いです。", "Ashi ga osoi desu.", "Je cours lentement."),
        VocabularyWord("12_7", 12, "多い", "おおい", "ooi", "Nombreux / Beaucoup de", WordCategory.ADJ_I, "人が 多いです。", "Hito ga ooi desu.", "Il y a beaucoup de monde."),
        VocabularyWord("12_8", 12, "少ない", "すくない", "sukunai", "Peu nombreux / Rare", WordCategory.ADJ_I, "雨が 少ないです。", "Ame ga sukunai desu.", "Il pleut peu."),
        VocabularyWord("12_9", 12, "暖かい", "あたたかい", "atatakai", "Doux / Tiède (climat)", WordCategory.ADJ_I, "春は 暖かいです。", "Haru wa atatakai desu.", "Le printemps est doux."),
        VocabularyWord("12_10", 12, "涼しい", "すずしい", "suzushii", "Frais / Agréable (climat)", WordCategory.ADJ_I, "秋は 涼しいです。", "Aki wa suzushii desu.", "L'automne est frais."),
        VocabularyWord("12_11", 12, "甘い", "あまい", "amai", "Sucré / Doux", WordCategory.ADJ_I, "この ケーキは 甘いです。", "Kono keeki wa amai desu.", "Ce gâteau est sucré."),
        VocabularyWord("12_12", 12, "辛い", "からい", "karai", "Épicé / Piquant", WordCategory.ADJ_I, "この カレーは 辛いです。", "Kono karee wa karai desu.", "Ce curry est très épicé."),
        VocabularyWord("12_13", 12, "重い", "おもい", "omoi", "Lourd", WordCategory.ADJ_I, "荷物が 重いです。", "Nimotsu ga omoi desu.", "Le bagage est lourd."),
        VocabularyWord("12_14", 12, "軽い", "かるい", "karui", "Léger", WordCategory.ADJ_I, "この 鞄は 軽いです。", "Kono kaban wa karui desu.", "Ce sac est très léger."),
        VocabularyWord("12_15", 12, "季節", "きせつ", "kisetsu", "Saison", WordCategory.NOUN, "どの 季節が 好きですか。", "Dono kisetsu ga suki desu ka.", "Quelle saison préférez-vous ?"),
        VocabularyWord("12_16", 12, "春", "はる", "haru", "Printemps", WordCategory.NOUN, "春は 桜が 咲きます。", "Haru wa sakura ga sakimasu.", "Au printemps les cerisiers fleurissent."),
        VocabularyWord("12_17", 12, "夏", "なつ", "natsu", "Été", WordCategory.NOUN, "夏休みに 海へ 行きます。", "Natsuyasumi ni umi e ikimasu.", "Pendant les vacances d'été je vais à la mer."),
        VocabularyWord("12_18", 12, "秋", "あき", "aki", "Automne", WordCategory.NOUN, "秋の 紅葉は きれいです。", "Aki no momiji wa kirei desu.", "Les érables d'automne sont magnifiques."),
        VocabularyWord("12_19", 12, "冬", "ふゆ", "fuyu", "Hiver", WordCategory.NOUN, "冬 雪が 降ります。", "Fuyu yuki ga furimasu.", "En hiver la neige tombe."),
        VocabularyWord("12_20", 12, "天気", "てんき", "tenki", "Temps météo", WordCategory.NOUN, "いい 天気ですね。", "Ii tenki desu ne.", "Il fait un beau temps n'est-ce pas ?"),
        VocabularyWord("12_21", 12, "雨", "あめ", "ame", "Pluie", WordCategory.NOUN, "雨が 降っています。", "Ame ga futte imasu.", "Il pleut."),
        VocabularyWord("12_22", 12, "雪", "ゆき", "yuki", "Neige", WordCategory.NOUN, "北海道は 雪です。", "Hokkaidou wa yuki desu.", "Il neige à Hokkaido."),
        VocabularyWord("12_23", 12, "ずっと", "ずっと", "zutto", "Bien plus / Beaucoup plus", WordCategory.ADVERB, "新幹線は バスより ずっと 速いです。", "Shinkansen wa basu yori zutto hayai desu.", "Le Shinkansen est bien plus rapide que le bus.")
    )

    // ==========================================
    // LEÇON 13 : Désirs & But du déplacement (欲しい, 〜たい, 〜に行きます)
    // ==========================================
    val lesson13Vocabulary: List<VocabularyWord> = listOf(
        VocabularyWord("13_1", 13, "遊びます", "あそびます", "asobimasu", "Jouer / S'amuser", WordCategory.VERB_G1, "友達と 遊びます。", "Tomodachi to asobimasu.", "Je m'amuse avec des amis."),
        VocabularyWord("13_2", 13, "泳ぎます", "およぎます", "oyogimasu", "Nager", WordCategory.VERB_G1, "プールで 泳ぎます。", "Puuru de oyogimasu.", "Je nage à la piscine."),
        VocabularyWord("13_3", 13, "迎えます", "むかえます", "mukaemasu", "Accueillir / Chercher qqn", WordCategory.VERB_G2, "空港へ 迎えに 行きます。", "Kuukou e mukae ni ikimasu.", "Je vais chercher quelqu'un à l'aéroport."),
        VocabularyWord("13_4", 13, "疲れます", "つかれます", "tsukaremasu", "Se fatiguer", WordCategory.VERB_G2, "とても 疲れました。", "Totemo tsukaremashita.", "Je suis très fatigué."),
        VocabularyWord("13_5", 13, "買い物します", "かいものします", "kaimonoshimasu", "Faire des courses / emplettes", WordCategory.VERB_G3, "デパートで 買い物します。", "Depaato de kaimonoshimasu.", "Je fais des achats au grand magasin."),
        VocabularyWord("13_6", 13, "散歩します", "さんぽします", "sanposhimasu", "Se promener", WordCategory.VERB_G3, "公園を 散歩します。", "Kouen o sanposhimasu.", "Je me promène dans le parc."),
        VocabularyWord("13_7", 13, "欲しい", "ほしい", "hoshii", "Désirer / Vouloir un objet", WordCategory.ADJ_I, "新しい 車が 欲しいです。", "Atarashii kuruma ga hoshii desu.", "Je veux une nouvelle voiture."),
        VocabularyWord("13_8", 13, "寂しい", "さびしい", "sabishii", "Triste / Solitaire", WordCategory.ADJ_I, "一人は 寂しいです。", "Hitori wa sabishii desu.", "C'est triste d'être tout seul."),
        VocabularyWord("13_9", 13, "広い", "ひろい", "hiroi", "Vaste / Spacieux", WordCategory.ADJ_I, "広い 部屋です。", "Hiroi heya desu.", "C'est une pièce spacieuse."),
        VocabularyWord("13_10", 13, "狭い", "せまい", "semai", "Étroit / Petit", WordCategory.ADJ_I, "狭い 道です。", "Semai michi desu.", "C'est un chemin étroit."),
        VocabularyWord("13_11", 13, "週末", "しゅうまつ", "shuumatsu", "Week-end / Fin de semaine", WordCategory.NOUN, "週末 何を しますか。", "Shuumatsu nani o shimasu ka.", "Que faites-vous ce week-end ?"),
        VocabularyWord("13_12", 13, "何か", "なにか", "nanika", "Quelque chose", WordCategory.NOUN, "何か 食べたいです。", "Nanika tabetai desu.", "Je veux manger quelque chose."),
        VocabularyWord("13_13", 13, "どこか", "どこか", "dokoka", "Quelque part", WordCategory.NOUN, "どこかへ 行きましたか。", "Dokoka e ikimashita ka.", "Êtes-vous allé quelque part ?")
    )

    // ==========================================
    // LEÇON 14 : Forme en -Te, Requêtes & Ordres (〜てください, 〜ましょうか)
    // ==========================================
    val lesson14Vocabulary: List<VocabularyWord> = listOf(
        VocabularyWord("14_1", 14, "つけます", "つけます", "tsukemasu", "Allumer (lumière, appareil)", WordCategory.VERB_G2, "電気を つけてください。", "Denki o tsukete kudasai.", "Allumez la lumière s'il vous plaît."),
        VocabularyWord("14_2", 14, "消します", "けします", "keshimasu", "Éteindre", WordCategory.VERB_G1, "テレビを 消します。", "Terebi o keshimasu.", "J'éteins la télé."),
        VocabularyWord("14_3", 14, "開けます", "あけます", "akemasu", "Ouvrir", WordCategory.VERB_G2, "ドアを 開けてください。", "Doa o akete kudasai.", "Ouvrez la porte s'il vous plaît."),
        VocabularyWord("14_4", 14, "閉めます", "しめます", "shimemasu", "Fermer", WordCategory.VERB_G2, "窓を 閉めます。", "Mado o shimemasu.", "Je ferme la fenêtre."),
        VocabularyWord("14_5", 14, "急ぎます", "いそぎます", "isogimasu", "Se dépêcher", WordCategory.VERB_G1, "急いで ください。", "Isoide kudasai.", "Dépêchez-vous s'il vous plaît."),
        VocabularyWord("14_6", 14, "待ちます", "まちます", "machimasu", "Attendre", WordCategory.VERB_G1, "ちょっと 待ってください。", "Chotto matte kudasai.", "Attendez un instant."),
        VocabularyWord("14_7", 14, "止めます", "とめます", "tomemasu", "Arrêter / Stationner", WordCategory.VERB_G2, "車を 止めます。", "Kuruma o tomemasu.", "Je gare la voiture."),
        VocabularyWord("14_8", 14, "曲がります", "まがります", "magarimasu", "Tourner (右へ曲がります)", WordCategory.VERB_G1, "角を 右へ 曲がります。", "Kado o migi e magarimasu.", "Tournez à droite au coin de la rue."),
        VocabularyWord("14_9", 14, "持ちます", "もちます", "mochimasu", "Tenir / Porter", WordCategory.VERB_G1, "荷物を 持ちましょうか。", "Nimotsu o mochimashou ka.", "Voulez-vous que je porte votre sac ?"),
        VocabularyWord("14_10", 14, "取ります", "とります", "torimasu", "Prendre / Passer un objet", WordCategory.VERB_G1, "塩を 取ってください。", "Shio o totte kudasai.", "Passez-moi le sel s'il vous plaît."),
        VocabularyWord("14_11", 14, "手伝います", "てつだいます", "tetsudaimasu", "Aider / Donner un coup de main", WordCategory.VERB_G1, "掃除を 手伝います。", "Souji o tetsudaimasu.", "J'aide à faire le ménage."),
        VocabularyWord("14_12", 14, "呼びます", "よびます", "yobimasu", "Appeler (taxi, personne)", WordCategory.VERB_G1, "タクシーを 呼びます。", "Takushii o yobimasu.", "J'appelle un taxi."),
        VocabularyWord("14_13", 14, "話します", "はなします", "hanashimasu", "Parler / Raconter", WordCategory.VERB_G1, "ゆっくり 話してください。", "Yukkuri hanashite kudasai.", "Parlez lentement s'il vous plaît."),
        VocabularyWord("14_14", 14, "見せます", "みせます", "misemasu", "Montrer", WordCategory.VERB_G2, "パスポートを 見せてください。", "Pasupooto o misete kudasai.", "Montrez votre passeport s'il vous plaît."),
        VocabularyWord("14_15", 14, "教えます", "おしえます", "oshiemasu", "Indiquer / Donner une adresse", WordCategory.VERB_G2, "住所を 教えてください。", "Juusho o oshiete kudasai.", "Donnez-moi votre adresse s'il vous plaît."),
        VocabularyWord("14_16", 14, "降ります", "ふります", "furimasu", "Tomber (雨が降ります)", WordCategory.VERB_G1, "雪が 降っています。", "Yuki ga futte imasu.", "Il neige en ce moment."),
        VocabularyWord("14_17", 14, "住所", "じゅうしょ", "juusho", "Adresse de domicile", WordCategory.NOUN, "住所を 書きます。", "Juusho o kakimasu.", "J'écris l'adresse."),
        VocabularyWord("14_18", 14, "地図", "ちず", "chizu", "Carte / Plan", WordCategory.NOUN, "地図を 見ます。", "Chizu o mimasu.", "Je consulte le plan."),
        VocabularyWord("14_19", 14, "塩", "しお", "shio", "Sel", WordCategory.NOUN, "塩を 少々 入れます。", "Shio o shoushou iremasu.", "Je mets une pincée de sel."),
        VocabularyWord("14_20", 14, "砂糖", "さとう", "satou", "Sucre", WordCategory.NOUN, "砂糖を 入れます。", "Satou o iremasu.", "Je mets du sucre."),
        VocabularyWord("14_21", 14, "ゆっくり", "ゆっくり", "yukkuri", "Lentement / Sans hâte", WordCategory.ADVERB, "ゆっくり 休みました。", "Yukkuri yasumimashita.", "Je me suis bien reposé."),
        VocabularyWord("14_22", 14, "すぐ", "すぐ", "sugu", "Tout de suite / Immédiatement", WordCategory.ADVERB, "すぐ 行きます。", "Sugu ikimasu.", "J'y vais tout de suite.")
    )

    // ==========================================
    // LEÇON 15 : Permissions & Interdictions (〜てもいい, 〜てはいけません)
    // ==========================================
    val lesson15Vocabulary: List<VocabularyWord> = listOf(
        VocabularyWord("15_1", 15, "立ちます", "たちます", "tachimasu", "Se lever / Être debout", WordCategory.VERB_G1, "席を 立ちます。", "Seki o tachimasu.", "Je me lève de ma place."),
        VocabularyWord("15_2", 15, "座ります", "すわります", "suwarimasu", "S'asseoir", WordCategory.VERB_G1, "ここに 座っても いいですか。", "Koko ni suwatte mo ii desu ka.", "Puis-je m'asseoir ici ?"),
        VocabularyWord("15_3", 15, "使います", "つかいます", "tsukaimasu", "Utiliser / Employer", WordCategory.VERB_G1, "辞書を 使います。", "Jisho o tsukaimasu.", "J'utilise un dictionnaire."),
        VocabularyWord("15_4", 15, "置きます", "おきます", "okimasu", "Poser / Placer / Déposer", WordCategory.VERB_G1, "荷物を ここに 置きます。", "Nimotsu o koko ni okimasu.", "Je dépose mon sac ici."),
        VocabularyWord("15_5", 15, "作ります", "つくります", "tsukurimasu", "Fabriquer / Préparer", WordCategory.VERB_G1, "晩ご飯を 作ります。", "Bangohan o tsukurimasu.", "Je prépare le dîner."),
        VocabularyWord("15_6", 15, "売ります", "うります", "urimasu", "Vendre", WordCategory.VERB_G1, "花を 売っています。", "Hana o utte imasu.", "On vend des fleurs."),
        VocabularyWord("15_7", 15, "知ります", "しります", "shirimasu", "Apprendre / Connaître", WordCategory.VERB_G1, "電話番号を 知っています。", "Denwabangou o shitte imasu.", "Je connais le numéro de téléphone."),
        VocabularyWord("15_8", 15, "住みます", "すみます", "sumimasu", "Habiter / Résider", WordCategory.VERB_G1, "東京に 住んでいます。", "Toukyou ni sunde imasu.", "J'habite à Tokyo."),
        VocabularyWord("15_9", 15, "研究します", "けんきゅうします", "kenkyuushimasu", "Faire de la recherche", WordCategory.VERB_G3, "経済を 研究しています。", "Keizai o kenkyuushite imasu.", "Je fais des recherches en économie."),
        VocabularyWord("15_10", 15, "資料", "しりょう", "shiryou", "Documents / Données", WordCategory.NOUN, "資料を 集めます。", "Shiryou o atsumemasu.", "Je rassemble les données."),
        VocabularyWord("15_11", 15, "時刻表", "じこくひょう", "jikokuhyou", "Horaires (de trains, bus)", WordCategory.NOUN, "電車の 時刻表を 見ます。", "Densha no jikokuhyou o mimasu.", "Je consulte les horaires de train."),
        VocabularyWord("15_12", 15, "服", "ふく", "fuku", "Vêtements", WordCategory.NOUN, "新しい 服を 買いました。", "Atarashii fuku o kaimashita.", "J'ai acheté de nouveaux habits."),
        VocabularyWord("15_13", 15, "製品", "せいひん", "seihin", "Produit manufacturé", WordCategory.NOUN, "日本の 製品は 素晴らしいです。", "Nihon no seihin wa subarashii desu.", "Les produits japonais sont de grande qualité."),
        VocabularyWord("15_14", 15, "歯医者", "はいしゃ", "haisha", "Dentiste", WordCategory.NOUN, "歯医者へ 行きます。", "Haisha e ikimasu.", "Je vais chez le dentiste."),
        VocabularyWord("15_15", 15, "独身", "どくしん", "dokushin", "Célibataire", WordCategory.NOUN, "彼は 独身です。", "Kare wa dokushin desu.", "Il est célibataire."),
        VocabularyWord("15_16", 15, "特に", "とくに", "tokuni", "En particulier / Surtout", WordCategory.ADVERB, "特に 日本料理が 好きです。", "Tokuni Nihon ryouri ga suki desu.", "J'aime particulièrement la cuisine japonaise.")
    )

    // ==========================================
    // LEÇON 16 : Enchaînements d'actions & Description physique (〜て、〜て, 〜てから)
    // ==========================================
    val lesson16Vocabulary: List<VocabularyWord> = listOf(
        VocabularyWord("16_1", 16, "乗ります", "のります", "norimasu", "Monter dans (電車に乗ります)", WordCategory.VERB_G1, "電車に 乗ります。", "Densha ni norimasu.", "Je monte dans le train."),
        VocabularyWord("16_2", 16, "降ります", "おります", "orimasu", "Descendre de (電車を降ります)", WordCategory.VERB_G2, "次の 駅で 降ります。", "Tsugi no eki de orimasu.", "Je descends à la prochaine station."),
        VocabularyWord("16_3", 16, "乗り換えます", "のりかえます", "norikaemasu", "Changer de ligne / Correspondance", WordCategory.VERB_G2, "地下鉄に 乗り換えます。", "Chikatetsu ni norikaemasu.", "Je prends une correspondance pour le métro."),
        VocabularyWord("16_4", 16, "浴びます", "あびます", "abimasu", "Prendre (une douche)", WordCategory.VERB_G2, "シャワーを 浴びます。", "Shawaa o abimasu.", "Je prends une douche."),
        VocabularyWord("16_5", 16, "入れます", "いれます", "iremasu", "Insérer / Mettre dedans", WordCategory.VERB_G2, "お金を 入れます。", "Okane o iremasu.", "J'insère l'argent."),
        VocabularyWord("16_6", 16, "出します", "だします", "dashimasu", "Retirer de l'argent / Sortir", WordCategory.VERB_G1, "銀行で お金を 出します。", "Ginkou de okane o dashimasu.", "Je retire de l'argent à la banque."),
        VocabularyWord("16_7", 16, "入ります", "はいります", "hairimasu", "Entrer (大学に入ります)", WordCategory.VERB_G1, "大学に 入りました。", "Daigaku ni hairimashita.", "Je suis entré à l'université."),
        VocabularyWord("16_8", 16, "出ます", "でます", "demasu", "Sortir / Être diplômé", WordCategory.VERB_G2, "大学を 出ました。", "Daigaku o demashita.", "J'ai obtenu mon diplôme universitaire."),
        VocabularyWord("16_9", 16, "押します", "おします", "oshimasu", "Appuyer / Pousser", WordCategory.VERB_G1, "ボタンを 押してください。", "Botan o oshite kudasai.", "Appuyez sur le bouton s'il vous plaît."),
        VocabularyWord("16_10", 16, "若い", "わかい", "wakai", "Jeune", WordCategory.ADJ_I, "若い 先生です。", "Wakai sensei desu.", "C'est un jeune professeur."),
        VocabularyWord("16_11", 16, "長い", "ながい", "nagai", "Long", WordCategory.ADJ_I, "髪が 長いです。", "Kami ga nagai desu.", "Elle a les cheveux longs."),
        VocabularyWord("16_12", 16, "短い", "みじかい", "mijikai", "Court", WordCategory.ADJ_I, "夏休みは 短いです。", "Natsuyasumi wa mijikai desu.", "Les vacances d'été sont courtes."),
        VocabularyWord("16_13", 16, "明るい", "あかるい", "akarui", "Lumineux / Enjoué", WordCategory.ADJ_I, "明るい 部屋です。", "Akarui heya desu.", "C'est une pièce très claire."),
        VocabularyWord("16_14", 16, "暗い", "くらい", "kurai", "Sombre", WordCategory.ADJ_I, "外は 暗いです。", "Soto wa kurai desu.", "Il fait sombre dehors."),
        VocabularyWord("16_15", 16, "背が高い", "せがたかい", "se ga takai", "De grande taille (personne)", WordCategory.ADJ_I, "背が 高い 男の人です。", "Se ga takai otoko no hito desu.", "C'est un homme grand."),
        VocabularyWord("16_16", 16, "頭がいい", "あたまがいい", "atama ga ii", "Intelligent / Brillant", WordCategory.ADJ_I, "カリナさんは 頭が いいです。", "Karina-san wa atama ga ii desu.", "Karina est très intelligente."),
        VocabularyWord("16_17", 16, "体", "からだ", "karada", "Corps / Santé", WordCategory.NOUN, "体に 気をつけてください。", "Karada ni ki o tsukete kudasai.", "Prenez bien soin de votre santé."),
        VocabularyWord("16_18", 16, "頭", "あたま", "atama", "Tête", WordCategory.NOUN, "頭が 痛いです。", "Atama ga itai desu.", "J'ai mal à la tête."),
        VocabularyWord("16_19", 16, "目", "め", "me", "Œil / Yeux", WordCategory.NOUN, "目が 青いです。", "Me ga aoi desu.", "Ses yeux sont bleus."),
        VocabularyWord("16_20", 16, "お寺", "おてら", "otera", "Temple bouddhiste", WordCategory.NOUN, "京都の お寺を 見ます。", "Kyouto no otera o mimasu.", "Je visite les temples de Kyoto."),
        VocabularyWord("16_21", 16, "神社", "じんじゃ", "jinja", "Sanctuaire shintoïste", WordCategory.NOUN, "神社で お祈りを します。", "Jinja de oinori o shimasu.", "Je prie au sanctuaire."),
        VocabularyWord("16_22", 16, "どうやって", "どうやって", "douyatte", "Comment / De quelle façon", WordCategory.ADVERB, "どうやって 行きますか。", "Douyatte ikimasu ka.", "Comment s'y rend-on ?")
    )

    // ==========================================
    // LEÇON 17 : Forme en -Nai & Obligations (〜ないでください, 〜なければなりません)
    // ==========================================
    val lesson17Vocabulary: List<VocabularyWord> = listOf(
        VocabularyWord("17_1", 17, "覚えます", "おぼえます", "oboemasu", "Mémoriser / Retenir", WordCategory.VERB_G2, "単語を 覚えます。", "Tango o oboemasu.", "Je mémorise le vocabulaire."),
        VocabularyWord("17_2", 17, "忘れます", "わすれます", "wasuremasu", "Oublier", WordCategory.VERB_G2, "傘を 忘れないでください。", "Kasa o wasurenaide kudasai.", "N'oubliez pas votre parapluie."),
        VocabularyWord("17_3", 17, "なくします", "なくします", "nakushimasu", "Perdre (un objet)", WordCategory.VERB_G1, "パスポートを なくしました。", "Pasupooto o nakushimashita.", "J'ai perdu mon passeport."),
        VocabularyWord("17_4", 17, "払います", "はらいます", "haraimasu", "Payer / Régler", WordCategory.VERB_G1, "現金で 払います。", "Genkin de haraimasu.", "Je paie en espèces."),
        VocabularyWord("17_5", 17, "返します", "かえします", "kaeshimasu", "Rendre / Restituer", WordCategory.VERB_G1, "本を 返さなければなりません。", "Hon o kaesanakereba narimasen.", "Je dois rendre le livre."),
        VocabularyWord("17_6", 17, "出かけます", "でかけます", "dekakemasu", "Sortir de chez soi", WordCategory.VERB_G2, "午後 出かけます。", "Gogo dekakemasu.", "Je sors cet après-midi."),
        VocabularyWord("17_7", 17, "脱ぎます", "ぬぎます", "nugimasu", "Enlever (vêtements, chaussures)", WordCategory.VERB_G1, "靴を 脱いでください。", "Kutsu o nuide kudasai.", "Enlevez vos chaussures s'il vous plaît."),
        VocabularyWord("17_8", 17, "持って行きます", "もっていきます", "motte ikimasu", "Emporter avec soi", WordCategory.VERB_G1, "保険証を 持って行きます。", "Hokenshou o motte ikimasu.", "J'emporte ma carte d'assurance."),
        VocabularyWord("17_9", 17, "持って来ます", "もってきます", "motte kimasu", "Apporter avec soi", WordCategory.VERB_G3, "宿題を 持って来てください。", "Shukudai o motte kite kudasai.", "Apportez vos devoirs."),
        VocabularyWord("17_10", 17, "心配します", "しんぱいします", "shinpaishimasu", "S'inquiéter", WordCategory.VERB_G3, "心配しないでください。", "Shinpai shinaide kudasai.", "Ne vous inquiétez pas."),
        VocabularyWord("17_11", 17, "残業します", "ざんぎょうします", "zangyoushimasu", "Faire des heures supplémentaires", WordCategory.VERB_G3, "今夜 残業します。", "Kon'ya zangyoushimasu.", "Je fais des heures sup ce soir."),
        VocabularyWord("17_12", 17, "出張します", "しゅっちょうします", "shutchoushimasu", "Partir en voyage d'affaires", WordCategory.VERB_G3, "大阪へ 出張します。", "Oosaka e shutchoushimasu.", "Je pars en déplacement à Osaka."),
        VocabularyWord("17_13", 17, "大切[な]", "たいせつ", "taisetsu", "Précieux / Important", WordCategory.ADJ_NA, "大切な 約束です。", "Taisetsu na yakusoku desu.", "C'est un rendez-vous très important."),
        VocabularyWord("17_14", 17, "大丈夫[な]", "だいじょうぶ", "daijoubu", "Ça va / Pas de souci", WordCategory.ADJ_NA, "大丈夫ですか。", "Daijoubu desu ka.", "Est-ce que ça va ?"),
        VocabularyWord("17_15", 17, "危ない", "あぶない", "abunai", "Dangereux", WordCategory.ADJ_I, "危ないですから 触らないでください。", "Abunai desu kara sawaranaide kudasai.", "Ne touchez pas car c'est dangereux."),
        VocabularyWord("17_16", 17, "禁煙", "きんえん", "kin'en", "Défense de fumer", WordCategory.NOUN, "ここは 禁煙です。", "Koko wa kin'en desu.", "Ici c'est non-fumeur."),
        VocabularyWord("17_17", 17, "保険証", "ほけんしょう", "hokenshou", "Carte d'assurance maladie", WordCategory.NOUN, "保険証を 見せてください。", "Hokenshou o misete kudasai.", "Montrez votre carte de sécurité sociale."),
        VocabularyWord("17_18", 17, "風邪", "かぜ", "kaze", "Rhume", WordCategory.NOUN, "風邪を ひきました。", "Kaze o hikimashita.", "J'ai attrapé un rhume."),
        VocabularyWord("17_19", 17, "熱", "ねつ", "netsu", "Fièvre", WordCategory.NOUN, "熱が あります。", "Netsu ga arimasu.", "J'ai de la fièvre."),
        VocabularyWord("17_20", 17, "病気", "びょうき", "byouki", "Maladie", WordCategory.NOUN, "病気で 休みます。", "Byouki de yasumimasu.", "Je m'absente pour maladie."),
        VocabularyWord("17_21", 17, "薬", "くすり", "kusuri", "Médicament", WordCategory.NOUN, "薬を 飲まなければなりません。", "Kusuri o nomanakereba narimasen.", "Je dois prendre mes médicaments."),
        VocabularyWord("17_22", 17, "お風呂", "おふろ", "ofuro", "Bain japonais", WordCategory.NOUN, "お風呂に 入ります。", "Ofuro ni hairimasu.", "Je prends un bon bain.")
    )

    // ==========================================
    // LEÇON 18 : Forme Dictionnaire & Capacités (〜ことが できます, 趣味)
    // ==========================================
    val lesson18Vocabulary: List<VocabularyWord> = listOf(
        VocabularyWord("18_1", 18, "できます", "できます", "dekimasu", "Pouvoir / Être capable de", WordCategory.VERB_G2, "スキーが できます。", "Sukii ga dekimasu.", "Je sais skier."),
        VocabularyWord("18_2", 18, "洗います", "あらいます", "araimasu", "Laver", WordCategory.VERB_G1, "手を 洗ってください。", "Te o aratte kudasai.", "Lavez-vous les mains s'il vous plaît."),
        VocabularyWord("18_3", 18, "弾きます", "ひきます", "hikimasu", "Jouer (piano, guitare, cordes)", WordCategory.VERB_G1, "ピアノを 弾くことが できます。", "Piano o hiku koto ga dekimasu.", "Je sais jouer du piano."),
        VocabularyWord("18_4", 18, "歌います", "うたいます", "utaimasu", "Chanter", WordCategory.VERB_G1, "日本の 歌を 歌います。", "Nihon no uta o utaimasu.", "Je chante une chanson japonaise."),
        VocabularyWord("18_5", 18, "集めます", "あつめます", "atsumemasu", "Collectionner / Rassembler", WordCategory.VERB_G2, "切手を 集めています。", "Kitte o atsumete imasu.", "Je collectionne les timbres."),
        VocabularyWord("18_6", 18, "捨てます", "すてます", "sutemasu", "Jeter / Mettre aux ordures", WordCategory.VERB_G2, "ゴミを 捨てないでください。", "Gomi o sutenaide kudasai.", "Ne jetez pas d'ordures s'il vous plaît."),
        VocabularyWord("18_7", 18, "換えます", "かえます", "kaemasu", "Changer / Échanger", WordCategory.VERB_G2, "円を ドルに 換えます。", "En o doru ni kaemasu.", "Je change des yens en dollars."),
        VocabularyWord("18_8", 18, "運転します", "うんてんします", "untenshimasu", "Conduire (un véhicule)", WordCategory.VERB_G3, "車を 運転することが できます。", "Kuruma o unten suru koto ga dekimasu.", "Je sais conduire une voiture."),
        VocabularyWord("18_9", 18, "予約します", "よやくします", "yoyakushimasu", "Réserver (hôtel, table)", WordCategory.VERB_G3, "ホテルを 予約しました。", "Hoteru o yoyakushimashita.", "J'ai réservé l'hôtel."),
        VocabularyWord("18_10", 18, "ピアノ", "ピアノ", "piano", "Piano", WordCategory.NOUN, "ピアノの 練習を します。", "Piano no renshuu o shimasu.", "Je m'entraîne au piano."),
        VocabularyWord("18_11", 18, "現金", "げんきん", "genkin", "Argent liquide / Espèces", WordCategory.NOUN, "現金で 払うことが できます。", "Genkin de harau koto ga dekimasu.", "On peut payer en espèces."),
        VocabularyWord("18_12", 18, "趣味", "しゅみ", "shumi", "Passe-temps / Hobby", WordCategory.NOUN, "趣味は 写真を 撮ることです。", "Shumi wa shashin o toru koto desu.", "Mon passe-temps est de prendre des photos."),
        VocabularyWord("18_13", 18, "日記", "にっき", "nikki", "Journal intime", WordCategory.NOUN, "毎晩 日記を 書きます。", "Maiban nikki o kakimasu.", "J'écris mon journal chaque soir."),
        VocabularyWord("18_14", 18, "動物", "どうぶつ", "doubutsu", "Animal", WordCategory.NOUN, "動物が 大好きです。", "Doubutsu ga daisuki desu.", "J'adore les animaux."),
        VocabularyWord("18_15", 18, "馬", "うま", "uma", "Cheval", WordCategory.NOUN, "馬に 乗ることが できます。", "Uma ni noru koto ga dekimasu.", "Je sais monter à cheval."),
        VocabularyWord("18_16", 18, "ぜひ", "ぜひ", "zehi", "Absolument / Vraiment", WordCategory.ADVERB, "ぜひ 遊びに 来てください。", "Zehi asobi ni kite kudasai.", "Venez absolument me rendre visite !")
    )

    // ==========================================
    // LEÇON 19 : Forme en -Ta, Expériences & Changements (〜たことが あります, なります)
    // ==========================================
    val lesson19Vocabulary: List<VocabularyWord> = listOf(
        VocabularyWord("19_1", 19, "登ります", "のぼります", "noborimasu", "Gravir / Monter (山に登ります)", WordCategory.VERB_G1, "富士山に 登ったことが あります。", "Fujisan ni nobotta koto ga arimasu.", "J'ai déjà gravi le mont Fuji."),
        VocabularyWord("19_2", 19, "泊まります", "とまります", "tomarimasu", "Loger / Séjourner (ホテルに泊まります)", WordCategory.VERB_G1, "旅館に 泊まりました。", "Ryokan ni tomarimashita.", "J'ai logé dans un ryokan traditionnel."),
        VocabularyWord("19_3", 19, "掃除します", "そうじします", "soujishimasu", "Faire le ménage / Nettoyer", WordCategory.VERB_G3, "部屋を 掃除しました。", "Heya o soujishimashita.", "J'ai nettoyé ma chambre."),
        VocabularyWord("19_4", 19, "洗濯します", "せんたくします", "sentakushimasu", "Faire la lessive", WordCategory.VERB_G3, "服を 洗濯します。", "Fuku o sentakushimasu.", "Je lave mon linge."),
        VocabularyWord("19_5", 19, "練習します", "れんしゅうします", "renshuushimasu", "S'entraîner / Pratiquer", WordCategory.VERB_G3, "会話を 練習します。", "Kaiwa o renshuushimasu.", "Je m'entraîne à la conversation."),
        VocabularyWord("19_6", 19, "なります", "なります", "narimasu", "Devenir", WordCategory.VERB_G1, "医者に なりたいです。", "Isha ni naritai desu.", "Je veux devenir médecin."),
        VocabularyWord("19_7", 19, "眠い", "ねむい", "nemui", "Avoir sommeil / Somnolent", WordCategory.ADJ_I, "とても 眠いです。", "Totemo nemui desu.", "J'ai très sommeil."),
        VocabularyWord("19_8", 19, "強い", "つよい", "tsuyoi", "Fort / Robuste", WordCategory.ADJ_I, "風が 強いです。", "Kaze ga tsuyoi desu.", "Le vent est très fort."),
        VocabularyWord("19_9", 19, "弱い", "よわい", "yowai", "Faible", WordCategory.ADJ_I, "体が 弱いです。", "Karada ga yowai desu.", "J'ai une santé fragile."),
        VocabularyWord("19_10", 19, "調子がいい", "ちょうしがいい", "choushi ga ii", "En pleine forme / Bon état", WordCategory.ADJ_I, "今日は 体の 調子が いいです。", "Kyou wa karada no choushi ga ii desu.", "Aujourd'hui je me sens en très bonne forme."),
        VocabularyWord("19_11", 19, "相撲", "すもう", "sumou", "Sumo (lutte traditionnelle)", WordCategory.NOUN, "相撲を 見たことが ありますか。", "Sumou o mita koto ga arimasu ka.", "Avez-vous déjà vu un combat de sumo ?"),
        VocabularyWord("19_12", 19, "お茶", "おちゃ", "ocha", "Cérémonie du thé (茶道)", WordCategory.NOUN, "お茶を 習いました。", "Ocha o naraimashita.", "J'ai appris la cérémonie du thé."),
        VocabularyWord("19_13", 19, "一度", "いちど", "ichido", "Une fois", WordCategory.ADVERB, "一度 日本へ 行きたいです。", "Ichido Nihon e ikitai desu.", "J'aimerais aller au Japon au moins une fois."),
        VocabularyWord("19_14", 19, "一度も", "いちども", "ichidomo", "Pas une seule fois (+ négatif)", WordCategory.ADVERB, "一度も 食べたことが ありません。", "Ichidomo tabeta koto ga arimasen.", "Je n'en ai jamais mangé une seule fois."),
        VocabularyWord("19_15", 19, "乾杯", "かんぱい", "kanpai", "Santé ! (trinquer)", WordCategory.EXPRESSION, "みんなで 乾杯しましょう！", "Minna de kanpai shimashou!", "Trinquons tous ensemble !")
    )

    // ==========================================
    // LEÇON 20 : Style Neutre / Familier (普通形, 会話)
    // ==========================================
    val lesson20Vocabulary: List<VocabularyWord> = listOf(
        VocabularyWord("20_1", 20, "要ります", "いります", "irimasu", "Avoir besoin de (ビザが要ります)", WordCategory.VERB_G1, "ビザが 要る？", "Biza ga iru?", "As-tu besoin d'un visa ?"),
        VocabularyWord("20_2", 20, "調べます", "しらべます", "shirabemasu", "Examiner / Chercher une info", WordCategory.VERB_G2, "ネットで 調べるよ。", "Netto de shiraberu yo.", "Je vais regarder sur Internet."),
        VocabularyWord("20_3", 20, "直します", "なおします", "naoshimasu", "Réparer / Corriger", WordCategory.VERB_G1, "自転車を 直した。", "Jitensha o naoshita.", "J'ai réparé le vélo."),
        VocabularyWord("20_4", 20, "修理します", "しゅうりします", "shuurishimasu", "Réparer / Dépanner", WordCategory.VERB_G3, "パソコンを 修理する。", "Pasokon o shuuri suru.", "Je répare l'ordinateur."),
        VocabularyWord("20_5", 20, "電話します", "でんわします", "denwashimasu", "Téléphoner", WordCategory.VERB_G3, "あとで 電話するね。", "Ato de denwa suru ne.", "Je t'appelle plus tard, d'accord ?"),
        VocabularyWord("20_6", 20, "僕", "ぼく", "boku", "Moi / Je (familier masculin)", WordCategory.NOUN, "僕も 行くよ。", "Boku mo iku yo.", "Moi aussi j'y vais."),
        VocabularyWord("20_7", 20, "君", "きみ", "kimi", "Toi / Tu (familier)", WordCategory.NOUN, "君は 知ってる？", "Kimi wa shitteru?", "Tu le savais ?"),
        VocabularyWord("20_8", 20, "うん", "うん", "un", "Oui (familier)", WordCategory.EXPRESSION, "うん、行く。", "Un, iku.", "Ouais, j'y vais."),
        VocabularyWord("20_9", 20, "ううん", "ううん", "uun", "Non (familier)", WordCategory.EXPRESSION, "ううん、行かない。", "Uun, ikanai.", "Non, je n'y vais pas."),
        VocabularyWord("20_10", 20, "言葉", "ことば", "kotoba", "Mot / Vocabulaire / Langage", WordCategory.NOUN, "この 言葉の 意味は？", "Kono kotoba no imi wa?", "Quelle est la signification de ce mot ?"),
        VocabularyWord("20_11", 20, "物価", "ぶっか", "bukka", "Coût de la vie / Prix", WordCategory.NOUN, "東京は 物価が 高い。", "Toukyou wa bukka ga takai.", "Le coût de la vie est élevé à Tokyo."),
        VocabularyWord("20_12", 20, "着物", "きもの", "kimono", "Kimono traditionnel", WordCategory.NOUN, "着物を 着てみたい。", "Kimono o kite mitai.", "J'aimerais essayer un kimono."),
        VocabularyWord("20_13", 20, "ビザ", "ビザ", "biza", "Visa de séjour", WordCategory.NOUN, "ビザを もらった。", "Biza o moratta.", "J'ai obtenu mon visa."),
        VocabularyWord("20_14", 20, "初め", "はじめ", "hajime", "Le début / Commencement", WordCategory.NOUN, "初めは 難しかった。", "Hajime wa muzukashikatta.", "Au début c'était difficile."),
        VocabularyWord("20_15", 20, "終わり", "おわり", "owari", "La fin", WordCategory.NOUN, "映画の 終わり。", "Eiga no owari.", "La fin du film."),
        VocabularyWord("20_16", 20, "こっち", "こっち", "kotchi", "Par ici (familier de こちら)", WordCategory.NOUN, "こっちへ おいで。", "Kotchi e oide.", "Viens par ici."),
        VocabularyWord("20_17", 20, "そっち", "そっち", "sotchi", "Par là (familier de そちら)", WordCategory.NOUN, "そっちは どう？", "Sotchi wa dou?", "Comment ça se passe de ton côté ?"),
        VocabularyWord("20_18", 20, "あっち", "あっち", "atchi", "Là-bas (familier de あちら)", WordCategory.NOUN, "あっちを 見て！", "Atchi o mite!", "Regarde là-bas !")
    )
}
