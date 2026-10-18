package com.example.data.datasource

import com.example.data.model.KanaCharacter
import com.example.data.model.KanaGroup
import com.example.data.model.KanaType

object KanaDatabase {

    val hiraganaList: List<KanaCharacter> = listOf(
        // Vowels (a, i, u, e, o)
        KanaCharacter("あ", "a", KanaType.HIRAGANA, KanaGroup.SEION, 3, "Comme un 'A' stylisé ou un arbre", "あさ (Matin)", "asa", "Matin"),
        KanaCharacter("い", "i", KanaType.HIRAGANA, KanaGroup.SEION, 2, "Deux traits parallèles comme deux aiguilles", "いぬ (Chien)", "inu", "Chien"),
        KanaCharacter("う", "u", KanaType.HIRAGANA, KanaGroup.SEION, 2, "Un homme voûté sous un fardeau", "うち (Maison)", "uchi", "Maison"),
        KanaCharacter("え", "e", KanaType.HIRAGANA, KanaGroup.SEION, 2, "Comme un oiseau exotique avec une crête", "えき (Gare)", "eki", "Gare"),
        KanaCharacter("お", "o", KanaType.HIRAGANA, KanaGroup.SEION, 3, "Un golfeur sur le point de frapper la balle 'on'", "おちゃ (Thé)", "ocha", "Thé"),

        // K-line (ka, ki, ku, ke, ko)
        KanaCharacter("か", "ka", KanaType.HIRAGANA, KanaGroup.SEION, 3, "Un kangourou qui saute", "かぎ (Clé)", "kagi", "Clé"),
        KanaCharacter("き", "ki", KanaType.HIRAGANA, KanaGroup.SEION, 4, "Une clé (key) à l'ancienne", "きって (Timbre)", "kitte", "Timbre"),
        KanaCharacter("く", "ku", KanaType.HIRAGANA, KanaGroup.SEION, 1, "Le bec ouvert d'un coucou", "くるま (Voiture)", "kuruma", "Voiture"),
        KanaCharacter("け", "ke", KanaType.HIRAGANA, KanaGroup.SEION, 3, "Un tonneau de bière (keg)", "けさ (Ce matin)", "kesa", "Ce matin"),
        KanaCharacter("こ", "ko", KanaType.HIRAGANA, KanaGroup.SEION, 2, "Deux vers de terre face à face", "これ (Ceci)", "kore", "Ceci"),

        // S-line (sa, shi, su, se, so)
        KanaCharacter("さ", "sa", KanaType.HIRAGANA, KanaGroup.SEION, 3, "Comme un 'ki' mais avec un seul trait horizontal", "さかな (Poisson)", "sakana", "Poisson"),
        KanaCharacter("し", "shi", KanaType.HIRAGANA, KanaGroup.SEION, 1, "Un hameçon de pêcheur", "しんぶん (Journal)", "shinbun", "Journal"),
        KanaCharacter("す", "su", KanaType.HIRAGANA, KanaGroup.SEION, 2, "Une boucle de paille à spirale", "すし (Sushi)", "sushi", "Sushi"),
        KanaCharacter("せ", "se", KanaType.HIRAGANA, KanaGroup.SEION, 3, "Deux personnes assises ensemble sur un siège", "せんせい (Professeur)", "sensei", "Professeur"),
        KanaCharacter("そ", "so", KanaType.HIRAGANA, KanaGroup.SEION, 1, "Un zigzag en forme de 'Z' prolongé", "そこ (Là-bas)", "soko", "Là-bas"),

        // T-line (ta, chi, tsu, te, to)
        KanaCharacter("た", "ta", KanaType.HIRAGANA, KanaGroup.SEION, 4, "Ressemble aux lettres 'ta'", "たべます (Manger)", "tabemasu", "Manger"),
        KanaCharacter("ち", "chi", KanaType.HIRAGANA, KanaGroup.SEION, 2, "Un pom-pom girl en équilibre (cheerleader)", "ちかてつ (Métro)", "chikatetsu", "Métro"),
        KanaCharacter("つ", "tsu", KanaType.HIRAGANA, KanaGroup.SEION, 1, "Une vague géante de tsunami", "つくえ (Bureau)", "tsukue", "Bureau"),
        KanaCharacter("て", "te", KanaType.HIRAGANA, KanaGroup.SEION, 1, "Une main ouverte", "てちょう (Agenda)", "techou", "Agenda"),
        KanaCharacter("と", "to", KanaType.HIRAGANA, KanaGroup.SEION, 2, "Une écharde dans un orteil (toe)", "とけい (Montre)", "tokei", "Montre"),

        // N-line (na, ni, nu, ne, no)
        KanaCharacter("な", "na", KanaType.HIRAGANA, KanaGroup.SEION, 4, "Une nonne en prière devant la croix", "なまえ (Nom)", "namae", "Nom"),
        KanaCharacter("に", "ni", KanaType.HIRAGANA, KanaGroup.SEION, 3, "Une aiguille et du fil à coudre", "にほん (Japon)", "nihon", "Japon"),
        KanaCharacter("ぬ", "nu", KanaType.HIRAGANA, KanaGroup.SEION, 2, "Des nouilles prises dans des baguettes", "ぬぎます (Enlever habits)", "nugimasu", "Enlever"),
        KanaCharacter("ね", "ne", KanaType.HIRAGANA, KanaGroup.SEION, 2, "Un chat (neko) avec une queue en boucle", "ねます (Dormir)", "nemasu", "Dormir"),
        KanaCharacter("の", "no", KanaType.HIRAGANA, KanaGroup.SEION, 1, "Un panneau 'Sens interdit' barré", "のみます (Boire)", "nomimasu", "Boire"),

        // H-line (ha, hi, fu, he, ho)
        KanaCharacter("は", "ha", KanaType.HIRAGANA, KanaGroup.SEION, 3, "La lettre 'H' avec un chapeau", "はし (Baguettes)", "hashi", "Baguettes"),
        KanaCharacter("ひ", "hi", KanaType.HIRAGANA, KanaGroup.SEION, 1, "Un grand sourire narquois (hi-hi)", "ひと (Personne)", "hito", "Personne"),
        KanaCharacter("ふ", "fu", KanaType.HIRAGANA, KanaGroup.SEION, 4, "Le mont Fuji vu de profil", "ふじさん (Mont Fuji)", "fujisan", "Mont Fuji"),
        KanaCharacter("へ", "he", KanaType.HIRAGANA, KanaGroup.SEION, 1, "Le sommet d'une colline", "へや (Chambre)", "heya", "Chambre"),
        KanaCharacter("ほ", "ho", KanaType.HIRAGANA, KanaGroup.SEION, 4, "Un bonhomme coiffé d'un casque", "ほん (Livre)", "hon", "Livre"),

        // M-line (ma, mi, mu, me, mo)
        KanaCharacter("ま", "ma", KanaType.HIRAGANA, KanaGroup.SEION, 3, "Deux barres avec une boucle", "まいあさ (Chaque matin)", "maiasa", "Chaque matin"),
        KanaCharacter("み", "mi", KanaType.HIRAGANA, KanaGroup.SEION, 2, "Le chiffre 21 stylisé", "みず (Eau)", "mizu", "Eau"),
        KanaCharacter("む", "mu", KanaType.HIRAGANA, KanaGroup.SEION, 3, "La tête d'une vache qui fait 'meuh'", "むずかしい (Difficile)", "muzukashii", "Difficile"),
        KanaCharacter("め", "me", KanaType.HIRAGANA, KanaGroup.SEION, 2, "Un œil (me en japonais)", "めがね (Lunettes)", "megane", "Lunettes"),
        KanaCharacter("も", "mo", KanaType.HIRAGANA, KanaGroup.SEION, 3, "Un harpon pour attraper plus de poissons", "もらいます (Recevoir)", "moraimasu", "Recevoir"),

        // Y-line (ya, yu, yo)
        KanaCharacter("や", "ya", KanaType.HIRAGANA, KanaGroup.SEION, 3, "Un yak cornu", "やま (Montagne)", "yama", "Montagne"),
        KanaCharacter("ゆ", "yu", KanaType.HIRAGANA, KanaGroup.SEION, 2, "Un poisson dans l'eau", "ゆうびんきょく (Poste)", "yuubinkyoku", "Poste"),
        KanaCharacter("よ", "yo", KanaType.HIRAGANA, KanaGroup.SEION, 2, "Un joueur de yo-yo", "よみます (Lire)", "yomimasu", "Lire"),

        // R-line (ra, ri, ru, re, ro)
        KanaCharacter("ら", "ra", KanaType.HIRAGANA, KanaGroup.SEION, 2, "Un lapin assis (rabbit)", "らいげつ (Mois prochain)", "raigetsu", "Mois prochain"),
        KanaCharacter("り", "ri", KanaType.HIRAGANA, KanaGroup.SEION, 2, "Deux tiges de roseau près d'une rivière", "りんご (Pomme)", "ringo", "Pomme"),
        KanaCharacter("る", "ru", KanaType.HIRAGANA, KanaGroup.SEION, 1, "Un '3' avec une petite boucle au bout", "るす (Absence)", "rusu", "Absence"),
        KanaCharacter("れ", "re", KanaType.HIRAGANA, KanaGroup.SEION, 2, "Une personne à genoux qui se repose", "れんしゅう (Exercice)", "renshuu", "Exercice"),
        KanaCharacter("ろ", "ro", KanaType.HIRAGANA, KanaGroup.SEION, 1, "Un '3' classique sans boucle", "ろく (Six)", "roku", "Six"),

        // W-line & N
        KanaCharacter("わ", "wa", KanaType.HIRAGANA, KanaGroup.SEION, 2, "Un cygne sur l'eau", "わたし (Moi)", "watashi", "Moi"),
        KanaCharacter("を", "wo", KanaType.HIRAGANA, KanaGroup.SEION, 3, "Particule COD uniquement", "ほんを よむ (Lire un livre)", "hon o yomu", "Lire un livre"),
        KanaCharacter("ん", "n", KanaType.HIRAGANA, KanaGroup.SEION, 1, "Un 'n' cursif minuscule", "にほん (Japon)", "nihon", "Japon"),

        // Dakuon & Handakuon (G, Z, D, B, P)
        KanaCharacter("が", "ga", KanaType.HIRAGANA, KanaGroup.DAKUON, 5, "か avec dakuten (tenten)", "がくせい (Étudiant)", "gakusei", "Étudiant"),
        KanaCharacter("ぎ", "gi", KanaType.HIRAGANA, KanaGroup.DAKUON, 6, "き avec dakuten", "ぎんこう (Banque)", "ginkou", "Banque"),
        KanaCharacter("ぐ", "gu", KanaType.HIRAGANA, KanaGroup.DAKUON, 3, "く avec dakuten", "ぐらい (Environ)", "gurai", "Environ"),
        KanaCharacter("げ", "ge", KanaType.HIRAGANA, KanaGroup.DAKUON, 5, "け avec dakuten", "げんき (En forme)", "genki", "En forme"),
        KanaCharacter("ご", "go", KanaType.HIRAGANA, KanaGroup.DAKUON, 4, "こ avec dakuten", "ごはん (Riz/Repas)", "gohan", "Repas"),

        KanaCharacter("ざ", "za", KanaType.HIRAGANA, KanaGroup.DAKUON, 5, "さ avec dakuten", "ざっし (Revue)", "zasshi", "Revue"),
        KanaCharacter("じ", "ji", KanaType.HIRAGANA, KanaGroup.DAKUON, 3, "し avec dakuten", "じしょ (Dictionnaire)", "jisho", "Dictionnaire"),
        KanaCharacter("ず", "zu", KanaType.HIRAGANA, KanaGroup.DAKUON, 4, "す avec dakuten", "ずっと (Depuis toujours)", "zutto", "Toujours"),
        KanaCharacter("ぜ", "ze", KanaType.HIRAGANA, KanaGroup.DAKUON, 5, "せ avec dakuten", "ぜんぜん (Pas du tout)", "zenzen", "Pas du tout"),
        KanaCharacter("ぞ", "zo", KanaType.HIRAGANA, KanaGroup.DAKUON, 3, "そ avec dakuten", "どうぞ (Je vous en prie)", "douzo", "Je vous en prie"),

        KanaCharacter("だ", "da", KanaType.HIRAGANA, KanaGroup.DAKUON, 6, "た avec dakuten", "だれ (Qui)", "dare", "Qui"),
        KanaCharacter("ぢ", "ji", KanaType.HIRAGANA, KanaGroup.DAKUON, 4, "ち avec dakuten", "はなぢ (Saignement de nez)", "hanaji", "Saignement de nez"),
        KanaCharacter("づ", "zu", KanaType.HIRAGANA, KanaGroup.DAKUON, 3, "つ avec dakuten", "つづく (Continuer)", "tsuzuku", "Continuer"),
        KanaCharacter("で", "de", KanaType.HIRAGANA, KanaGroup.DAKUON, 3, "て avec dakuten", "でんしゃ (Train)", "densha", "Train"),
        KanaCharacter("ど", "do", KanaType.HIRAGANA, KanaGroup.DAKUON, 4, "と avec dakuten", "どこ (Où)", "doko", "Où"),

        KanaCharacter("ば", "ba", KanaType.HIRAGANA, KanaGroup.DAKUON, 5, "は avec dakuten", "ばんごう (Numéro)", "bangou", "Numéro"),
        KanaCharacter("び", "bi", KanaType.HIRAGANA, KanaGroup.DAKUON, 3, "ひ avec dakuten", "びょういん (Hôpital)", "byouin", "Hôpital"),
        KanaCharacter("ぶ", "bu", KanaType.HIRAGANA, KanaGroup.DAKUON, 6, "ふ avec dakuten", "ぶたにく (Porc)", "butaniku", "Porc"),
        KanaCharacter("べ", "be", KanaType.HIRAGANA, KanaGroup.DAKUON, 3, "へ avec dakuten", "べんり (Pratique)", "benri", "Pratique"),
        KanaCharacter("ぼ", "bo", KanaType.HIRAGANA, KanaGroup.DAKUON, 6, "ほ avec dakuten", "ぼうし (Chapeau)", "boushi", "Chapeau"),

        KanaCharacter("ぱ", "pa", KanaType.HIRAGANA, KanaGroup.DAKUON, 4, "は avec maru (cercle)", "パン (Pain)", "pan", "Pain"),
        KanaCharacter("ぴ", "pi", KanaType.HIRAGANA, KanaGroup.DAKUON, 2, "ひ avec maru", "ピカピカ (Étincelant)", "pikapika", "Étincelant"),
        KanaCharacter("ぷ", "pu", KanaType.HIRAGANA, KanaGroup.DAKUON, 5, "ふ avec maru", "きっぷ (Billet/Ticket)", "kippu", "Ticket"),
        KanaCharacter("ぺ", "pe", KanaType.HIRAGANA, KanaGroup.DAKUON, 2, "へ avec maru", "ペン (Stylo)", "pen", "Stylo"),
        KanaCharacter("ぽ", "po", KanaType.HIRAGANA, KanaGroup.DAKUON, 5, "ほ avec maru", "ポスト (Boîte aux lettres)", "posuto", "Boîte aux lettres"),

        // Yōon combinations (kya, kyu, kyo, etc.)
        KanaCharacter("きゃ", "kya", KanaType.HIRAGANA, KanaGroup.YOON, 5, "き + petit ゃ", "きゃく (Client)", "kyaku", "Client"),
        KanaCharacter("きゅ", "kyu", KanaType.HIRAGANA, KanaGroup.YOON, 6, "き + petit ゅ", "きゅう (Neuf / 9)", "kyuu", "Neuf"),
        KanaCharacter("きょ", "kyo", KanaType.HIRAGANA, KanaGroup.YOON, 6, "き + petit ょ", "きょう (Aujourd'hui)", "kyou", "Aujourd'hui"),
        KanaCharacter("しゃ", "sha", KanaType.HIRAGANA, KanaGroup.YOON, 4, "し + petit ゃ", "しゃしん (Photo)", "shashin", "Photo"),
        KanaCharacter("しゅ", "shu", KanaType.HIRAGANA, KanaGroup.YOON, 3, "し + petit ゅ", "しゅくだい (Devoirs)", "shukudai", "Devoirs"),
        KanaCharacter("しょ", "sho", KanaType.HIRAGANA, KanaGroup.YOON, 3, "し + petit ょ", "しょくどう (Cantine)", "shokudou", "Cantine"),
        KanaCharacter("ちゃ", "cha", KanaType.HIRAGANA, KanaGroup.YOON, 5, "ち + petit ゃ", "おちゃ (Thé vert)", "ocha", "Thé vert"),
        KanaCharacter("にゃ", "nya", KanaType.HIRAGANA, KanaGroup.YOON, 6, "に + petit ゃ", "にゃんこ (Chaton)", "nyanko", "Chaton"),
        KanaCharacter("りょ", "ryo", KanaType.HIRAGANA, KanaGroup.YOON, 4, "り + petit ょ", "りょこう (Voyage)", "ryokou", "Voyage")
    )

    val katakanaList: List<KanaCharacter> = listOf(
        // Vowels (A, I, U, E, O)
        KanaCharacter("ア", "a", KanaType.KATAKANA, KanaGroup.SEION, 2, "Un coin pointu comme une équerre", "アメリカ (Amérique)", "Amerika", "Amérique"),
        KanaCharacter("イ", "i", KanaType.KATAKANA, KanaGroup.SEION, 2, "Un chevalet ou trépied", "インド (Inde)", "Indo", "Inde"),
        KanaCharacter("ウ", "u", KanaType.KATAKANA, KanaGroup.SEION, 3, "Comme 'u' avec un toit", "ウイスキー (Whisky)", "uisukii", "Whisky"),
        KanaCharacter("エ", "e", KanaType.KATAKANA, KanaGroup.SEION, 3, "Une poutre de charpente en I / H", "エレベーター (Ascenseur)", "erebeetaa", "Ascenseur"),
        KanaCharacter("オ", "o", KanaType.KATAKANA, KanaGroup.SEION, 3, "Une personne debout fière", "オーストラリア (Australie)", "Oosutoraria", "Australie"),

        // K-line (ka, ki, ku, ke, ko)
        KanaCharacter("カ", "ka", KanaType.KATAKANA, KanaGroup.SEION, 2, "Identique à la partie gauche de か", "カメラ (Appareil photo)", "kamera", "Appareil photo"),
        KanaCharacter("キ", "ki", KanaType.KATAKANA, KanaGroup.SEION, 3, "Deux barres traversées sans boucle", "キロ (Kilo)", "kiro", "Kilo"),
        KanaCharacter("ク", "ku", KanaType.KATAKANA, KanaGroup.SEION, 2, "Comme un couteau pointu", "クラス (Classe)", "kurasu", "Classe"),
        KanaCharacter("ケ", "ke", KanaType.KATAKANA, KanaGroup.SEION, 3, "Un K stylisé", "ケーキ (Gâteau)", "keeki", "Gâteau"),
        KanaCharacter("コ", "ko", KanaType.KATAKANA, KanaGroup.SEION, 2, "Un angle droit ouvert", "コーヒー (Café)", "koohii", "Café"),

        // S-line (sa, shi, su, se, so)
        KanaCharacter("サ", "sa", KanaType.KATAKANA, KanaGroup.SEION, 3, "Trois traits droits coupés", "サッカー (Football)", "sakkaa", "Football"),
        KanaCharacter("シ", "shi", KanaType.KATAKANA, KanaGroup.SEION, 3, "Deux gouttes et un trait montant vers le haut", "シャツ (Chemise)", "shatsu", "Chemise"),
        KanaCharacter("ス", "su", KanaType.KATAKANA, KanaGroup.SEION, 2, "Un skieur qui dévale une pente", "スーパー (Supermarché)", "suupaa", "Supermarché"),
        KanaCharacter("セ", "se", KanaType.KATAKANA, KanaGroup.SEION, 2, "Très proche du hiragana せ", "セーター (Pull-over)", "seetaa", "Pull-over"),
        KanaCharacter("ソ", "so", KanaType.KATAKANA, KanaGroup.SEION, 2, "Une aiguille et un trait descendant", "ソフト (Logiciel)", "sofuto", "Logiciel"),

        // T-line (ta, chi, tsu, te, to)
        KanaCharacter("タ", "ta", KanaType.KATAKANA, KanaGroup.SEION, 3, "Proche du kanji 夕 (soir)", "タクシー (Taxi)", "takushii", "Taxi"),
        KanaCharacter("チ", "chi", KanaType.KATAKANA, KanaGroup.SEION, 3, "Comme le chiffre 1000 (千)", "チケット (Billet)", "chiketto", "Billet"),
        KanaCharacter("ツ", "tsu", KanaType.KATAKANA, KanaGroup.SEION, 3, "Deux yeux et une bouche descendantes", "ツアー (Circuit touristique)", "tsuaa", "Tour"),
        KanaCharacter("テ", "te", KanaType.KATAKANA, KanaGroup.SEION, 3, "Une table de tennis de table", "テレビ (Télévision)", "terebi", "Télévision"),
        KanaCharacter("ト", "to", KanaType.KATAKANA, KanaGroup.SEION, 2, "Un poteau avec une branche", "トイレ (Toilettes)", "toire", "Toilettes"),

        // N-line (na, ni, nu, ne, no)
        KanaCharacter("ナ", "na", KanaType.KATAKANA, KanaGroup.SEION, 2, "Une croix avec un bras incliné", "ナイフ (Couteau)", "naifu", "Couteau"),
        KanaCharacter("ニ", "ni", KanaType.KATAKANA, KanaGroup.SEION, 2, "Le kanji du chiffre 2 (二)", "ニュース (Informations/News)", "nyuusu", "News"),
        KanaCharacter("ヌ", "nu", KanaType.KATAKANA, KanaGroup.SEION, 2, "Deux baguettes croisées", "ヌードル (Nouilles)", "nuudoru", "Nouilles"),
        KanaCharacter("ネ", "ne", KanaType.KATAKANA, KanaGroup.SEION, 4, "Comme la clé du vêtement (示)", "ネクタイ (Cravate)", "nekutai", "Cravate"),
        KanaCharacter("ノ", "no", KanaType.KATAKANA, KanaGroup.SEION, 1, "Une seule barre oblique", "ノート (Cahier)", "nooto", "Cahier"),

        // H-line (ha, hi, fu, he, ho)
        KanaCharacter("ハ", "ha", KanaType.KATAKANA, KanaGroup.SEION, 2, "Le chiffre 8 (八)", "ハンサム (Séduisant/Beau)", "hansamu", "Beau"),
        KanaCharacter("ヒ", "hi", KanaType.KATAKANA, KanaGroup.SEION, 2, "Un talon haut (high-heel)", "ビール (Bière)", "biiru", "Bière"),
        KanaCharacter("フ", "fu", KanaType.KATAKANA, KanaGroup.SEION, 1, "Un nez d'oiseau", "フランス (France)", "Furansu", "France"),
        KanaCharacter("ヘ", "he", KanaType.KATAKANA, KanaGroup.SEION, 1, "Identique au hiragana へ", "ヘルメット (Casque)", "herumetto", "Casque"),
        KanaCharacter("ホ", "ho", KanaType.KATAKANA, KanaGroup.SEION, 4, "Une croix avec deux gouttes", "ホテル (Hôtel)", "hoteru", "Hôtel"),

        // M-line (ma, mi, mu, me, mo)
        KanaCharacter("マ", "ma", KanaType.KATAKANA, KanaGroup.SEION, 2, "Un chapeau de marin", "マイク (Micro / Mike)", "Maiku", "Mike"),
        KanaCharacter("ミ", "mi", KanaType.KATAKANA, KanaGroup.SEION, 3, "Trois traits obliques comme le chiffre 3", "ミラー (Miller)", "Miraa", "Miller"),
        KanaCharacter("ム", "mu", KanaType.KATAKANA, KanaGroup.SEION, 2, "Un triangle ouvert", "ムード (Ambiance/Mood)", "muudo", "Ambiance"),
        KanaCharacter("メ", "me", KanaType.KATAKANA, KanaGroup.SEION, 2, "Deux épées qui se croisent", "メール (E-mail)", "meeru", "E-mail"),
        KanaCharacter("モ", "mo", KanaType.KATAKANA, KanaGroup.SEION, 3, "Très proche du hiragana も", "モノレール (Monorail)", "monoreeru", "Monorail"),

        // Y-line (ya, yu, yo)
        KanaCharacter("ヤ", "ya", KanaType.KATAKANA, KanaGroup.SEION, 2, "Forme simplifiée de や", "ヤクルト (Yakult)", "yakuruto", "Yakult"),
        KanaCharacter("ユ", "yu", KanaType.KATAKANA, KanaGroup.SEION, 2, "Un crochet vers la droite", "ユーモア (Humour)", "yuumoa", "Humour"),
        KanaCharacter("ヨ", "yo", KanaType.KATAKANA, KanaGroup.SEION, 3, "Un E inversé", "ヨーロッパ (Europe)", "Yooroppa", "Europe"),

        // R-line (ra, ri, ru, re, ro)
        KanaCharacter("ラ", "ra", KanaType.KATAKANA, KanaGroup.SEION, 2, "Un crochet sous une barre", "ラジオ (Radio)", "rajio", "Radio"),
        KanaCharacter("リ", "ri", KanaType.KATAKANA, KanaGroup.SEION, 2, "Deux barres verticales", "リーダー (Leader)", "riidaa", "Leader"),
        KanaCharacter("ル", "ru", KanaType.KATAKANA, KanaGroup.SEION, 2, "Deux jambes qui courent", "ルール (Règle/Rule)", "ruuru", "Règle"),
        KanaCharacter("レ", "re", KanaType.KATAKANA, KanaGroup.SEION, 1, "Un angle droit aigu", "レポート (Rapport)", "repooto", "Rapport"),
        KanaCharacter("ロ", "ro", KanaType.KATAKANA, KanaGroup.SEION, 3, "Un carré fermé (Bouche/Box)", "ロシア (Russie)", "Roshia", "Russie"),

        // W-line & N
        KanaCharacter("ワ", "wa", KanaType.KATAKANA, KanaGroup.SEION, 2, "Un verre à vin", "ワイン (Vin)", "wain", "Vin"),
        KanaCharacter("ヲ", "wo", KanaType.KATAKANA, KanaGroup.SEION, 2, "Rarement utilisé en katakana moderne", "ヲ", "wo", "Particule"),
        KanaCharacter("ン", "n", KanaType.KATAKANA, KanaGroup.SEION, 2, "Un point et un trait montant", "パン (Pain)", "pan", "Pain"),

        // Katakana Dakuon & Handakuon
        KanaCharacter("ガ", "ga", KanaType.KATAKANA, KanaGroup.DAKUON, 4, "カ + tenten", "ガレージ (Garage)", "gareeji", "Garage"),
        KanaCharacter("ギ", "gi", KanaType.KATAKANA, KanaGroup.DAKUON, 5, "キ + tenten", "ギター (Guitare)", "gitaa", "Guitare"),
        KanaCharacter("グ", "gu", KanaType.KATAKANA, KanaGroup.DAKUON, 4, "ク + tenten", "グラス (Verre)", "gurasu", "Verre"),
        KanaCharacter("ゲ", "ge", KanaType.KATAKANA, KanaGroup.DAKUON, 5, "ケ + tenten", "ゲーム (Jeu vidéo)", "geemu", "Jeu vidéo"),
        KanaCharacter("ゴ", "go", KanaType.KATAKANA, KanaGroup.DAKUON, 4, "コ + tenten", "ゴルフ (Golf)", "gorufu", "Golf"),

        KanaCharacter("ザ", "za", KanaType.KATAKANA, KanaGroup.DAKUON, 5, "サ + tenten", "デザート (Dessert)", "dezaato", "Dessert"),
        KanaCharacter("ジ", "ji", KanaType.KATAKANA, KanaGroup.DAKUON, 5, "シ + tenten", "ジャズ (Jazz)", "jazu", "Jazz"),
        KanaCharacter("ズ", "zu", KanaType.KATAKANA, KanaGroup.DAKUON, 4, "ス + tenten", "チーズ (Fromage)", "chiizu", "Fromage"),
        KanaCharacter("ゼ", "ze", KanaType.KATAKANA, KanaGroup.DAKUON, 4, "セ + tenten", "ゼロ (Zéro)", "zero", "Zéro"),
        KanaCharacter("ゾ", "zo", KanaType.KATAKANA, KanaGroup.DAKUON, 4, "ソ + tenten", "ゾーン (Zone)", "zoon", "Zone"),

        KanaCharacter("ダ", "da", KanaType.KATAKANA, KanaGroup.DAKUON, 5, "タ + tenten", "ダンス (Danse)", "dansu", "Danse"),
        KanaCharacter("デ", "de", KanaType.KATAKANA, KanaGroup.DAKUON, 5, "テ + tenten", "デパート (Grand magasin)", "depaato", "Grand magasin"),
        KanaCharacter("ド", "do", KanaType.KATAKANA, KanaGroup.DAKUON, 4, "ト + tenten", "ドア (Porte)", "doa", "Porte"),

        KanaCharacter("バ", "ba", KanaType.KATAKANA, KanaGroup.DAKUON, 4, "ハ + tenten", "バス (Bus)", "basu", "Bus"),
        KanaCharacter("ビ", "bi", KanaType.KATAKANA, KanaGroup.DAKUON, 4, "ヒ + tenten", "ビル (Immeuble)", "biru", "Immeuble"),
        KanaCharacter("ブ", "bu", KanaType.KATAKANA, KanaGroup.DAKUON, 3, "フ + tenten", "ブラジル (Brésil)", "Burajiru", "Brésil"),
        KanaCharacter("ベ", "be", KanaType.KATAKANA, KanaGroup.DAKUON, 3, "ヘ + tenten", "ベッド (Lit)", "beddo", "Lit"),
        KanaCharacter("ボ", "bo", KanaType.KATAKANA, KanaGroup.DAKUON, 6, "ホ + tenten", "ボールペン (Stylo à bille)", "boorupen", "Stylo à bille"),

        KanaCharacter("パ", "pa", KanaType.KATAKANA, KanaGroup.DAKUON, 3, "ハ + maru", "パソコン (Ordinateur portable)", "pasokon", "Ordinateur"),
        KanaCharacter("ピ", "pi", KanaType.KATAKANA, KanaGroup.DAKUON, 3, "ヒ + maru", "ピアノ (Piano)", "piano", "Piano"),
        KanaCharacter("プ", "pu", KanaType.KATAKANA, KanaGroup.DAKUON, 2, "フ + maru", "プール (Piscine)", "puuru", "Piscine"),
        KanaCharacter("ペ", "pe", KanaType.KATAKANA, KanaGroup.DAKUON, 2, "ヘ + maru", "ペン (Stylo)", "pen", "Stylo"),
        KanaCharacter("ポ", "po", KanaType.KATAKANA, KanaGroup.DAKUON, 5, "ホ + maru", "ポスト (Boîte aux lettres)", "posuto", "Boîte aux lettres")
    )
}
