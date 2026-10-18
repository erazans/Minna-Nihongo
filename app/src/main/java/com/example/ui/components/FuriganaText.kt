package com.example.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.RomajiDisplayMode
import com.example.ui.theme.JapanRedPrimary

data class FuriganaSegment(
    val mainText: String,
    val furigana: String? = null,
    val romaji: String = "",
    val isKatakana: Boolean = false,
    val isHiragana: Boolean = false
)

object FuriganaDictionary {

    // Comprehensive Minna no Nihongo Kanji to Furigana + Romaji mapping
    private val dictionary: List<Pair<String, Pair<String, String>>> = listOf(
        // Leçon 1 à 5
        "わたし" to ("わたし" to "watashi"),
        "私" to ("わたし" to "watashi"),
        "山田" to ("やまだ" to "Yamada"),
        "佐藤" to ("さとう" to "Satou"),
        "木村" to ("きむら" to "Kimura"),
        "松本" to ("まつもと" to "Matsumoto"),
        "井上" to ("いのうえ" to "Inoue"),
        "日本人" to ("にほんじん" to "Nihon-jin"),
        "日本" to ("にほん" to "Nihon"),
        "人" to ("ひと" to "hito"),
        "方" to ("かた" to "kata"),
        "先生" to ("せんせい" to "sensei"),
        "学生" to ("がくせい" to "gakusei"),
        "会社員" to ("かいしゃいん" to "kaishain"),
        "会社" to ("かいしゃ" to "kaisha"),
        "社員" to ("しゃいん" to "shain"),
        "銀行員" to ("ぎんこういん" to "ginkouin"),
        "銀行" to ("ぎんこう" to "ginkou"),
        "医者" to ("いしゃ" to "isha"),
        "研究者" to ("けんきゅうしゃ" to "kenkyuusha"),
        "大学" to ("だいがく" to "daigaku"),
        "病院" to ("びょういん" to "byouin"),
        "何歳" to ("なんさい" to "nansai"),
        "歳" to ("さい" to "sai"),
        "初めまして" to ("はじめまして" to "hajimemashite"),
        "本" to ("ほん" to "hon"),
        "辞書" to ("じしょ" to "jisho"),
        "雑誌" to ("ざっし" to "zasshi"),
        "新聞" to ("しんぶん" to "shinbun"),
        "手帳" to ("てちょう" to "techou"),
        "名刺" to ("めいし" to "meishi"),
        "鉛筆" to ("えんぴつ" to "enpitsu"),
        "鍵" to ("かぎ" to "kagi"),
        "時計" to ("とけい" to "tokei"),
        "傘" to ("かさ" to "kasa"),
        "鞄" to ("かばん" to "kaban"),
        "車" to ("くるま" to "kuruma"),
        "机" to ("つくえ" to "tsukue"),
        "椅子" to ("いす" to "isu"),
        "教室" to ("きょうしつ" to "kyoushitsu"),
        "食堂" to ("しょくどう" to "shokudou"),
        "事務所" to ("じむしょ" to "jimusho"),
        "会議室" to ("かいぎしつ" to "kaigishitsu"),
        "受付" to ("うけつけ" to "uketsuke"),
        "部屋" to ("へや" to "heya"),
        "お手洗い" to ("おてあらい" to "otearai"),
        "階段" to ("かいだん" to "kaidan"),
        "国" to ("くに" to "kuni"),
        "お国" to ("おくにかた" to "okuni"),
        "電話" to ("でんわ" to "denwa"),
        "靴" to ("くつ" to "kutsu"),
        "売り場" to ("うりば" to "uriba"),
        "地下" to ("ちか" to "chika"),
        "階" to ("かい" to "kai"),
        "円" to ("えん" to "en"),
        "百" to ("ひゃく" to "hyaku"),
        "千" to ("せん" to "sen"),
        "万" to ("まん" to "man"),
        "今" to ("いま" to "ima"),
        "時" to ("じ" to "ji"),
        "分" to ("ふん" to "fun"),
        "半" to ("はん" to "han"),
        "何時" to ("なんじ" to "nanji"),
        "何分" to ("なんぷん" to "nanpun"),
        "午前" to ("ごぜん" to "gozen"),
        "午後" to ("ごご" to "gogo"),
        "朝" to ("あさ" to "asa"),
        "昼" to ("ひる" to "hiru"),
        "晩" to ("ばん" to "ban"),
        "夜" to ("よる" to "yoru"),
        "一昨日" to ("おととい" to "ototoi"),
        "昨日" to ("きのう" to "kinou"),
        "今日" to ("きょう" to "kyou"),
        "明日" to ("あした" to "ashita"),
        "明後日" to ("あさって" to "asatte"),
        "今朝" to ("けさ" to "kesa"),
        "今晩" to ("こんばん" to "konban"),
        "休み" to ("やすみ" to "yasumi"),
        "昼休み" to ("ひるやすみ" to "hiruyasumi"),
        "毎週" to ("まいしゅう" to "maishuu"),
        "毎朝" to ("まいあさ" to "maiasa"),
        "毎晩" to ("まいばん" to "maiban"),
        "毎日" to ("まいにち" to "mainichi"),
        "月曜日" to ("げつようび" to "getsuyoubi"),
        "火曜日" to ("かようび" to "kayoubi"),
        "水曜日" to ("すいようび" to "suiyoubi"),
        "木曜日" to ("もくようび" to "mokuyoubi"),
        "金曜日" to ("きんようび" to "kinyoubi"),
        "土曜日" to ("どようび" to "doyoubi"),
        "日曜日" to ("にちようび" to "nichiyoubi"),
        "何曜日" to ("なんようび" to "nanyoubi"),
        "番号" to ("ばんごう" to "bangou"),
        "何番" to ("なんばん" to "nanban"),
        "起きます" to ("おきます" to "okimasu"),
        "寝ます" to ("ねます" to "nemasu"),
        "働きます" to ("はたらきます" to "hatarakimasu"),
        "休みます" to ("やすみます" to "yasumimasu"),
        "勉強します" to ("べんきょうします" to "benkyoushimasu"),
        "終わります" to ("おわります" to "owarimasu"),
        "行きます" to ("いきます" to "ikimasu"),
        "来ます" to ("きます" to "kimasu"),
        "帰ります" to ("かえります" to "kaerimasu"),
        "学校" to ("がっこう" to "gakkou"),
        "駅" to ("えき" to "eki"),
        "飛行機" to ("ひこうき" to "hikouki"),
        "船" to ("ふね" to "fune"),
        "電車" to ("でんしゃ" to "densha"),
        "地下鉄" to ("ちかてつ" to "chikatetsu"),
        "新幹線" to ("しんかんせん" to "shinkansen"),
        "自転車" to ("じてんしゃ" to "jitensha"),
        "歩いて" to ("あるいて" to "aruite"),
        "友達" to ("ともだち" to "tomodachi"),
        "彼" to ("かれ" to "kare"),
        "彼女" to ("かのじょ" to "kanojo"),
        "家族" to ("かぞく" to "kazoku"),
        "一人で" to ("ひとりで" to "hitoride"),
        "先週" to ("せんしゅう" to "senshuu"),
        "今週" to ("こんしゅう" to "konshuu"),
        "来週" to ("らいしゅう" to "raishuu"),
        "先月" to ("せんげつ" to "sengetsu"),
        "今月" to ("こんげつ" to "kongetsu"),
        "来月" to ("らいげつ" to "raigetsu"),
        "去年" to ("きょねん" to "kyonen"),
        "今年" to ("ことし" to "kotoshi"),
        "来年" to ("らいねん" to "rainen"),
        "誕生日" to ("たんじょうび" to "tanjoubi"),
        "普通" to ("ふつう" to "futsuu"),
        "急行" to ("きゅうこう" to "kyuukou"),
        "特急" to ("とっきゅう" to "tokkyuu"),
        // Leçons 6 à 10
        "食べます" to ("たべます" to "tabemasu"),
        "飲みます" to ("のみます" to "nomimasu"),
        "吸います" to ("すいます" to "suimasu"),
        "見ます" to ("みます" to "mimasu"),
        "聞きます" to ("ききます" to "kikimasu"),
        "読みます" to ("よみます" to "yomimasu"),
        "書きます" to ("かきます" to "kakimasu"),
        "買います" to ("かいます" to "kaimasu"),
        "撮ります" to ("とります" to "torimasu"),
        "します" to ("します" to "shimasu"),
        "会います" to ("あいます" to "aimasu"),
        "ご飯" to ("ごはん" to "gohan"),
        "朝ご飯" to ("あさごはん" to "asagohan"),
        "昼ご飯" to ("ひるごはん" to "hirugohan"),
        "晩ご飯" to ("ばんごはん" to "bangohan"),
        "卵" to ("たまご" to "tamago"),
        "肉" to ("にく" to "niku"),
        "魚" to ("さかな" to "sakana"),
        "野菜" to ("やさい" to "yasai"),
        "果物" to ("くだもの" to "kudamono"),
        "水" to ("みず" to "mizu"),
        "お茶" to ("おちゃ" to "ocha"),
        "紅茶" to ("こうちゃ" to "koucha"),
        "牛乳" to ("ぎゅうにゅう" to "gyuunyuu"),
        "お酒" to ("おさけ" to "osake"),
        "映画" to ("えいが" to "eiga"),
        "手紙" to ("てがみ" to "tegami"),
        "写真" to ("しゃしん" to "shashin"),
        "店" to ("みせ" to "mise"),
        "庭" to ("にわ" to "niwa"),
        "宿題" to ("しゅくだい" to "shukudai"),
        "花見" to ("はなみ" to "hanami"),
        "切ります" to ("きります" to "kirimasu"),
        "送ります" to ("おくります" to "okurimasu"),
        "あげます" to ("あげます" to "agemasu"),
        "もらいます" to ("もらいます" to "moraimasu"),
        "貸します" to ("かします" to "kashimasu"),
        "借ります" to ("かります" to "karimasu"),
        "教えます" to ("おしえます" to "oshiemasu"),
        "習います" to ("ならいます" to "naraimasu"),
        "手" to ("て" to "te"),
        "箸" to ("はし" to "hashi"),
        "紙" to ("かみ" to "kami"),
        "花" to ("はな" to "hana"),
        "荷物" to ("にもつ" to "nimotsu"),
        "切符" to ("きっぷ" to "kippu"),
        "父" to ("ちち" to "chichi"),
        "母" to ("はは" to "haha"),
        "お父さん" to ("おとうさん" to "otousan"),
        "お母さん" to ("おかあさん" to "okaasan"),
        "もう" to ("もう" to "mou"),
        "まだ" to ("まだ" to "mada"),
        "これから" to ("これから" to "korekara"),
        // Adjectifs & Leçons suivantes
        "綺麗" to ("きれい" to "kirei"),
        "きれい" to ("きれい" to "kirei"),
        "ハンサム" to ("ハンサム" to "hansamu"),
        "静か" to ("しずか" to "shizuka"),
        "賑やか" to ("にぎやか" to "nigiyaka"),
        "有名" to ("ゆうめい" to "yuumei"),
        "親切" to ("しんせつ" to "shinsetsu"),
        "元気" to ("げんき" to "genki"),
        "暇" to ("ひま" to "hima"),
        "便利" to ("べんり" to "benri"),
        "素敵" to ("すてき" to "suteki"),
        "大きい" to ("おおきい" to "ookii"),
        "小さい" to ("ちいさい" to "chiisai"),
        "新しい" to ("あたらしい" to "atarashii"),
        "古い" to ("ふるい" to "furui"),
        "良い" to ("いい" to "ii"),
        "悪い" to ("わるい" to "warui"),
        "暑い" to ("あつい" to "atsui"),
        "熱い" to ("あつい" to "atsui"),
        "寒い" to ("さむい" to "samui"),
        "冷たい" to ("つめたい" to "tsumetai"),
        "難しい" to ("むずかしい" to "muzukashii"),
        "易しい" to ("やさしい" to "yasashii"),
        "高い" to ("たかい" to "takai"),
        "安い" to ("やすい" to "yasui"),
        "低い" to ("ひくい" to "hikui"),
        "面白い" to ("おもしろい" to "omoshiroi"),
        "美味しい" to ("おいしい" to "oishii"),
        "おいしい" to ("おいしい" to "oishii"),
        "忙しい" to ("いそがしい" to "isogashii"),
        "楽しい" to ("たのしい" to "tanoshii"),
        "白い" to ("しろい" to "shiroi"),
        "黒い" to ("くろい" to "kuroi"),
        "赤い" to ("あかい" to "akai"),
        "青い" to ("あおい" to "aoi"),
        "桜" to ("さくら" to "sakura"),
        "山" to ("やま" to "yama"),
        "町" to ("まち" to "machi"),
        "食べ物" to ("たべもの" to "tabemono"),
        "生活" to ("せいかつ" to "seikatsu"),
        "仕事" to ("しごと" to "shigoto"),
        "どう" to ("どう" to "dou"),
        "どんな" to ("どんな" to "donna"),
        "とても" to ("とても" to "totemo"),
        "あまり" to ("あまり" to "amari"),
        "そして" to ("そして" to "soshite"),
        "好き" to ("すき" to "suki"),
        "嫌い" to ("きらい" to "kirai"),
        "上手" to ("じょうず" to "jouzu"),
        "下手" to ("へた" to "heta"),
        "料理" to ("りょうり" to "ryouri"),
        "飲み物" to ("のみもの" to "nomimono"),
        "野球" to ("やきゅう" to "yakyuu"),
        "音楽" to ("おんがく" to "ongaku"),
        "歌" to ("うた" to "uta"),
        "歌舞伎" to ("かぶき" to "kabuki"),
        "絵" to ("え" to "e"),
        "字" to ("じ" to "ji"),
        "漢字" to ("かんじ" to "kanji"),
        "平仮名" to ("ひらがな" to "hiragana"),
        "片仮名" to ("かたかな" to "katakana"),
        "ローマ字" to ("ローマじ" to "roomaji"),
        "細かいお金" to ("こまかいおかね" to "komakai okane"),
        "お金" to ("おかね" to "okane"),
        "時間" to ("じかん" to "jikan"),
        "用事" to ("ようじ" to "youji"),
        "約束" to ("やくそく" to "yakusoku"),
        "ご主人" to ("ごしゅじん" to "goshujin"),
        "夫" to ("おっと" to "otto"),
        "奥さん" to ("おくさん" to "okusan"),
        "妻" to ("つま" to "tsuma"),
        "子ども" to ("こども" to "kodomo"),
        "子供" to ("こども" to "kodomo"),
        "よく" to ("よく" to "yoku"),
        "大体" to ("だいたい" to "daitai"),
        "たくさん" to ("たくさん" to "takusan"),
        "少し" to ("すこし" to "sukoshi"),
        "全然" to ("ぜんぜん" to "zenzen"),
        "早く" to ("はやく" to "hayaku"),
        "速く" to ("はやく" to "hayaku"),
        "犬" to ("いぬ" to "inu"),
        "猫" to ("ねこ" to "neko"),
        "木" to ("き" to "ki"),
        "物" to ("もの" to "mono"),
        "箱" to ("はこ" to "hako"),
        "冷蔵庫" to ("れいぞうこ" to "reizouko"),
        "棚" to ("たな" to "tana"),
        "窓" to ("まど" to "mado"),
        "公園" to ("こうえん" to "kouen"),
        "喫茶店" to ("きっさてん" to "kissaten"),
        "本屋" to ("ほんや" to "hon'ya"),
        "上" to ("うえ" to "ue"),
        "下" to ("した" to "shita"),
        "前" to ("まえ" to "mae"),
        "後ろ" to ("うしろ" to "ushiro"),
        "右" to ("みぎ" to "migi"),
        "左" to ("ひだり" to "hidari"),
        "中" to ("なか" to "naka"),
        "外" to ("そと" to "soto"),
        "隣" to ("となり" to "tonari"),
        "近く" to ("ちかく" to "chikaku"),
        "間" to ("あいだ" to "aida"),
        // Formes verbales et suites
        "書いて" to ("かいて" to "kaite"),
        "待って" to ("まって" to "matte"),
        "飲んで" to ("のんで" to "nonde"),
        "食べて" to ("たべて" to "tabete"),
        "行って" to ("いって" to "itte"),
        "来て" to ("きて" to "kite"),
        "吸わないで" to ("すわないで" to "suwanaide"),
        "行かないで" to ("いかないで" to "ikanaide"),
        "帰らなければ" to ("かえらなければ" to "kaeranakereba"),
        "読むこと" to ("よむこと" to "yomu koto"),
        "行ったこと" to ("いったこと" to "itta koto"),
        "登ったこと" to ("のぼったこと" to "nobotta koto"),
        "思います" to ("おもいます" to "omoimasu"),
        "言いました" to ("いいました" to "iimashita"),
        "大阪城" to ("おおさかじょう" to "Oosakajou"),
        "京都" to ("きょうと" to "Kyouto"),
        "東京" to ("とうきょう" to "Toukyou"),
        "富士山" to ("ふじさん" to "Fujisan")
    )

    private val singleKanjiMap: Map<Char, Pair<String, String>> = mapOf(
        '私' to ("わたし" to "watashi"),
        '人' to ("ひと" to "hito"),
        '日' to ("ひ" to "hi"),
        '本' to ("ほん" to "hon"),
        '語' to ("ご" to "go"),
        '学' to ("がく" to "gaku"),
        '生' to ("せい" to "sei"),
        '先' to ("せん" to "sen"),
        '校' to ("こう" to "kou"),
        '時' to ("じ" to "ji"),
        '分' to ("ふん" to "fun"),
        '半' to ("はん" to "han"),
        '何' to ("なん" to "nan"),
        '誰' to ("だれ" to "dare"),
        '車' to ("くるま" to "kuruma"),
        '電' to ("でん" to "den"),
        '駅' to ("えき" to "eki"),
        '行' to ("い" to "i"),
        '来' to ("き" to "ki"),
        '帰' to ("かえ" to "kae"),
        '食' to ("た" to "ta"),
        '飲' to ("の" to "no"),
        '見' to ("み" to "mi"),
        '聞' to ("き" to "ki"),
        '読' to ("よ" to "yo"),
        '書' to ("か" to "ka"),
        '買' to ("か" to "ka"),
        '休' to ("やす" to "yasu"),
        '会' to ("あ" to "a"),
        '社' to ("しゃ" to "sha"),
        '員' to ("いん" to "in"),
        '父' to ("ちち" to "chichi"),
        '母' to ("はは" to "haha"),
        '友' to ("とも" to "tomo"),
        '達' to ("だち" to "dachi"),
        '山' to ("やま" to "yama"),
        '川' to ("かわ" to "kawa"),
        '田' to ("た" to "ta"),
        '花' to ("はな" to "hana"),
        '魚' to ("さかな" to "sakana"),
        '肉' to ("にく" to "niku"),
        '水' to ("みず" to "mizu"),
        '木' to ("き" to "ki"),
        '金' to ("かね" to "kane"),
        '土' to ("つち" to "tsuchi"),
        '天' to ("てん" to "ten"),
        '気' to ("き" to "ki"),
        '雨' to ("あめ" to "ame"),
        '白' to ("しろ" to "shiro"),
        '黒' to ("くろ" to "kuro"),
        '赤' to ("あか" to "aka"),
        '青' to ("あお" to "ao"),
        '高' to ("たか" to "taka"),
        '安' to ("やす" to "yasu"),
        '新' to ("あたら" to "atara"),
        '古' to ("ふる" to "furu"),
        '長' to ("なが" to "naga"),
        '少' to ("すこ" to "suko"),
        '多' to ("おお" to "oo"),
        '大' to ("おお" to "oo"),
        '小' to ("ちい" to "chii"),
        '中' to ("なか" to "naka"),
        '上' to ("うえ" to "ue"),
        '下' to ("した" to "shita"),
        '前' to ("まえ" to "mae"),
        '後' to ("うしろ" to "ushiro"),
        '右' to ("みぎ" to "migi"),
        '左' to ("ひだり" to "hidari"),
        '間' to ("あいだ" to "aida"),
        '門' to ("もん" to "mon"),
        '道' to ("みち" to "michi"),
        '店' to ("みせ" to "mise"),
        '手' to ("て" to "te"),
        '紙' to ("かみ" to "kami"),
        '物' to ("もの" to "mono"),
        '事' to ("こと" to "koto"),
        '名' to ("な" to "na"),
        '円' to ("えん" to "en"),
        '万' to ("まん" to "man"),
        '千' to ("せん" to "sen"),
        '百' to ("ひゃく" to "hyaku"),
        '一' to ("いち" to "ichi"),
        '二' to ("に" to "ni"),
        '三' to ("さん" to "san"),
        '四' to ("よん" to "yon"),
        '五' to ("ご" to "go"),
        '六' to ("ろく" to "roku"),
        '七' to ("なな" to "nana"),
        '八' to ("はち" to "hachi"),
        '九' to ("きゅう" to "kyuu"),
        '十' to ("じゅう" to "juu"),
        '男' to ("おとこ" to "otoko"),
        '女' to ("おんな" to "onna"),
        '子' to ("こ" to "ko"),
        '国' to ("くに" to "kuni"),
        '年' to ("とし" to "toshi"),
        '月' to ("つき" to "tsuki"),
        '火' to ("ひ" to "hi")
    )

    // Lazy compiled dictionary merging static list + all MinnaDatabase vocabulary
    private val compiledSortedDict: List<Pair<String, Pair<String, String>>> by lazy {
        val map = mutableMapOf<String, Pair<String, String>>()
        try {
            com.example.data.datasource.MinnaDatabase.vocabulary.forEach { word ->
                if (word.kanji.isNotBlank() && word.kanji != word.kana) {
                    map[word.kanji] = word.kana to word.romaji
                }
            }
        } catch (_: Throwable) {}
        dictionary.forEach { (k, v) ->
            if (!map.containsKey(k)) {
                map[k] = v
            }
        }
        map.toList().sortedByDescending { it.first.length }
    }

    fun parse(text: String): List<FuriganaSegment> {
        if (text.isBlank()) return emptyList()

        val segments = mutableListOf<FuriganaSegment>()
        val sortedDict = compiledSortedDict

        var i = 0
        while (i < text.length) {
            // Check for explicit bracket format [Kanji|Furigana]
            if (text[i] == '[' && text.indexOf('|', i) > i && text.indexOf(']', i) > text.indexOf('|', i)) {
                val pipeIndex = text.indexOf('|', i)
                val closeIndex = text.indexOf(']', i)
                val kanji = text.substring(i + 1, pipeIndex)
                val furi = text.substring(pipeIndex + 1, closeIndex)
                segments.add(
                    FuriganaSegment(
                        mainText = kanji,
                        furigana = furi,
                        romaji = kanaToRomaji(furi)
                    )
                )
                i = closeIndex + 1
                continue
            }

            // Check dictionary match for compound words
            var matched = false
            for ((kanjiKey, pair) in sortedDict) {
                if (text.startsWith(kanjiKey, i)) {
                    val (furi, romaji) = pair
                    segments.add(
                        FuriganaSegment(
                            mainText = kanjiKey,
                            furigana = furi,
                            romaji = romaji
                        )
                    )
                    i += kanjiKey.length
                    matched = true
                    break
                }
            }

            if (!matched) {
                val c = text[i]
                val char = c.toString()
                val isKata = isKatakanaChar(c)
                val isHira = isHiraganaChar(c)
                val singleKanjiReading = singleKanjiMap[c]

                if (singleKanjiReading != null) {
                    segments.add(
                        FuriganaSegment(
                            mainText = char,
                            furigana = singleKanjiReading.first,
                            romaji = singleKanjiReading.second
                        )
                    )
                } else {
                    val romaji = if (isKata || isHira) kanaCharToRomaji(c) else ""
                    segments.add(
                        FuriganaSegment(
                            mainText = char,
                            furigana = null,
                            romaji = romaji,
                            isKatakana = isKata,
                            isHiragana = isHira
                        )
                    )
                }
                i++
            }
        }
        return segments
    }

    private fun isKatakanaChar(c: Char): Boolean = c in '\u30A0'..'\u30FF'
    private fun isHiraganaChar(c: Char): Boolean = c in '\u3040'..'\u309F'

    private fun kanaCharToRomaji(c: Char): String {
        return when (c) {
            'あ', 'ア' -> "a"
            'い', 'イ' -> "i"
            'う', 'ウ' -> "u"
            'え', 'エ' -> "e"
            'お', 'オ' -> "o"
            'か', 'カ' -> "ka"
            'き', 'キ' -> "ki"
            'く', 'ク' -> "ku"
            'け', 'ケ' -> "ke"
            'こ', 'コ' -> "ko"
            'さ', 'サ' -> "sa"
            'し', 'シ' -> "shi"
            'す', 'ス' -> "su"
            'せ', 'セ' -> "se"
            'そ', 'ソ' -> "so"
            'た', 'タ' -> "ta"
            'ち', 'チ' -> "chi"
            'つ', 'ツ' -> "tsu"
            'て', 'テ' -> "te"
            'と', 'ト' -> "to"
            'な', 'ナ' -> "na"
            'に', 'ニ' -> "ni"
            'ぬ', 'ヌ' -> "nu"
            'ね', 'ネ' -> "ne"
            'の', 'ノ' -> "no"
            'は', 'ハ' -> "ha"
            'ひ', 'ヒ' -> "hi"
            'ふ', 'フ' -> "fu"
            'へ', 'ヘ' -> "he"
            'ほ', 'ホ' -> "ho"
            'ま', 'マ' -> "ma"
            'み', 'ミ' -> "mi"
            'む', 'ム' -> "mu"
            'め', 'メ' -> "me"
            'も', 'モ' -> "mo"
            'や', 'ヤ' -> "ya"
            'ゆ', 'ユ' -> "yu"
            'よ', 'ヨ' -> "yo"
            'ら', 'ラ' -> "ra"
            'り', 'リ' -> "ri"
            'る', 'ル' -> "ru"
            'れ', 'レ' -> "re"
            'ろ', 'ロ' -> "ro"
            'わ', 'ワ' -> "wa"
            'を', 'ヲ' -> "o"
            'ん', 'ン' -> "n"
            'が', 'ガ' -> "ga"
            'ぎ', 'ギ' -> "gi"
            'ぐ', 'グ' -> "gu"
            'げ', 'ゲ' -> "ge"
            'ご', 'ゴ' -> "go"
            'ざ', 'ザ' -> "za"
            'じ', 'ジ' -> "ji"
            'ず', 'ズ' -> "zu"
            'ぜ', 'ゼ' -> "ze"
            'ぞ', 'ゾ' -> "zo"
            'だ', 'ダ' -> "da"
            'で', 'デ' -> "de"
            'ど', 'ド' -> "do"
            'ば', 'バ' -> "ba"
            'び', 'ビ' -> "bi"
            'ぶ', 'ブ' -> "bu"
            'べ', 'ベ' -> "be"
            'ぼ', 'ボ' -> "bo"
            'ぱ', 'パ' -> "pa"
            'ぴ', 'ピ' -> "pi"
            'ぷ', 'プ' -> "pu"
            'ぺ', 'ペ' -> "pe"
            'ぽ', 'ポ' -> "po"
            else -> ""
        }
    }

    private fun kanaToRomaji(kana: String): String {
        val sb = StringBuilder()
        for (c in kana) {
            val r = kanaCharToRomaji(c)
            if (r.isNotEmpty()) sb.append(r) else sb.append(c)
        }
        return sb.toString()
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun JapaneseFuriganaText(
    text: String,
    modifier: Modifier = Modifier,
    romajiMode: RomajiDisplayMode = RomajiDisplayMode.BOTH,
    fontSize: TextUnit = 20.sp,
    rubySize: TextUnit = 10.sp,
    romajiSize: TextUnit = 10.sp,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    rubyColor: Color = MaterialTheme.colorScheme.primary,
    fontWeight: FontWeight = FontWeight.Bold,
    onClick: (() -> Unit)? = null
) {
    val segments = FuriganaDictionary.parse(text)

    FlowRow(
        modifier = modifier
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier),
        horizontalArrangement = Arrangement.spacedBy(1.dp),
        verticalArrangement = Arrangement.Center
    ) {
        segments.forEach { seg ->
            val showRomaji = when (romajiMode) {
                RomajiDisplayMode.NONE -> false
                RomajiDisplayMode.HIRAGANA_ONLY -> seg.isHiragana || (seg.furigana != null)
                RomajiDisplayMode.KATAKANA_ONLY -> seg.isKatakana
                RomajiDisplayMode.BOTH -> seg.isHiragana || seg.isKatakana || (seg.furigana != null)
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 0.5.dp)
            ) {
                // Top: Furigana ruby reading
                if (seg.furigana != null) {
                    Text(
                        text = seg.furigana,
                        fontSize = rubySize,
                        fontWeight = FontWeight.SemiBold,
                        color = rubyColor,
                        lineHeight = rubySize
                    )
                } else {
                    // Empty placeholder line with same height to keep perfect alignment
                    Spacer(modifier = Modifier.height(rubySize.value.dp))
                }

                // Middle: Main character (Kanji or Kana)
                Text(
                    text = seg.mainText,
                    fontSize = fontSize,
                    fontWeight = fontWeight,
                    color = textColor,
                    lineHeight = fontSize
                )

                // Bottom: Latin alphabet (Romaji) based on user option
                if (showRomaji && seg.romaji.isNotBlank()) {
                    Text(
                        text = seg.romaji,
                        fontSize = romajiSize,
                        color = MaterialTheme.colorScheme.outline,
                        fontWeight = FontWeight.Medium,
                        lineHeight = romajiSize
                    )
                }
            }
        }
    }
}

@Composable
fun RomajiModeSelectorBar(
    currentMode: RomajiDisplayMode,
    onModeSelected: (RomajiDisplayMode) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 6.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = "🔤 Alphabet latin (Romaji) sous les caractères :",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                RomajiModePill(
                    title = "Aucun",
                    isSelected = currentMode == RomajiDisplayMode.NONE,
                    onClick = { onModeSelected(RomajiDisplayMode.NONE) },
                    modifier = Modifier.weight(1f)
                )
                RomajiModePill(
                    title = "Hiragana",
                    isSelected = currentMode == RomajiDisplayMode.HIRAGANA_ONLY,
                    onClick = { onModeSelected(RomajiDisplayMode.HIRAGANA_ONLY) },
                    modifier = Modifier.weight(1.1f)
                )
                RomajiModePill(
                    title = "Katakana",
                    isSelected = currentMode == RomajiDisplayMode.KATAKANA_ONLY,
                    onClick = { onModeSelected(RomajiDisplayMode.KATAKANA_ONLY) },
                    modifier = Modifier.weight(1.1f)
                )
                RomajiModePill(
                    title = "Les 2",
                    isSelected = currentMode == RomajiDisplayMode.BOTH,
                    onClick = { onModeSelected(RomajiDisplayMode.BOTH) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun RomajiModePill(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .border(
                1.dp,
                if (isSelected) JapanRedPrimary else Color.Transparent,
                RoundedCornerShape(8.dp)
            ),
        color = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
    ) {
        Box(
            modifier = Modifier.padding(vertical = 6.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                fontSize = 11.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                color = if (isSelected) JapanRedPrimary else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

