package com.example.service

import com.example.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

data class AiResponse(
    val japanese: String,
    val romaji: String,
    val french: String,
    val grammarTip: String? = null
)

class GeminiChatService {

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    suspend fun sendMessage(
        systemPrompt: String,
        conversationHistory: List<Pair<String, Boolean>>, // text, isUser
        userMessage: String
    ): AiResponse = withContext(Dispatchers.IO) {
        val apiKey = try {
            BuildConfig.GEMINI_API_KEY
        } catch (e: Throwable) {
            ""
        }

        if (apiKey.isBlank() || apiKey == "MY_GEMINI_API_KEY") {
            return@withContext generateOfflineFallback(userMessage, systemPrompt)
        }

        try {
            val endpoint = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent?key=$apiKey"

            val contentsArray = JSONArray()

            // System instructions / history
            for ((text, isUser) in conversationHistory) {
                val role = if (isUser) "user" else "model"
                val partObj = JSONObject().put("text", text)
                val contentObj = JSONObject()
                    .put("role", role)
                    .put("parts", JSONArray().put(partObj))
                contentsArray.put(contentObj)
            }

            // Current user message
            val currentMsg = "SYSTEM: Tu es un tuteur/interlocuteur selon Minna no Nihongo. $systemPrompt. " +
                    "Réponds UNIQUEMENT sous forme d'un objet JSON strict avec 4 clés : " +
                    "\"japanese\" (texte en japonais naturel avec kanji et kana), " +
                    "\"romaji\" (transcription en alphabet latin), " +
                    "\"french\" (traduction française naturelle), " +
                    "\"grammarTip\" (un conseil bienveillant en français sur la grammaire ou le vocabulaire, ou null si la phrase de l'élève est parfaite). " +
                    "\nMESSAGE DE L'ÉLÈVE: $userMessage"

            val currentPart = JSONObject().put("text", currentMsg)
            val currentContent = JSONObject()
                .put("role", "user")
                .put("parts", JSONArray().put(currentPart))
            contentsArray.put(currentContent)

            val requestJson = JSONObject()
                .put("contents", contentsArray)
                .put("generationConfig", JSONObject()
                    .put("responseMimeType", "application/json")
                    .put("temperature", 0.7)
                )

            val body = requestJson.toString().toRequestBody("application/json".toMediaType())
            val request = Request.Builder()
                .url(endpoint)
                .post(body)
                .build()

            val response = client.newCall(request).execute()
            val responseBody = response.body?.string() ?: ""

            if (!response.isSuccessful || responseBody.isBlank()) {
                return@withContext generateOfflineFallback(userMessage, systemPrompt)
            }

            val json = JSONObject(responseBody)
            val candidates = json.optJSONArray("candidates")
            val firstCandidate = candidates?.optJSONObject(0)
            val content = firstCandidate?.optJSONObject("content")
            val parts = content?.optJSONArray("parts")
            val textContent = parts?.optJSONObject(0)?.optString("text") ?: ""

            if (textContent.isNotBlank()) {
                try {
                    val parsed = JSONObject(textContent)
                    return@withContext AiResponse(
                        japanese = parsed.optString("japanese", "はい、わかりました。"),
                        romaji = parsed.optString("romaji", "Hai, wakarimashita."),
                        french = parsed.optString("french", "Oui, j'ai bien compris."),
                        grammarTip = parsed.optString("grammarTip").takeIf { it.isNotBlank() && it != "null" }
                    )
                } catch (pe: Exception) {
                    return@withContext AiResponse(
                        japanese = textContent,
                        romaji = "",
                        french = "Réponse générée par l'IA",
                        grammarTip = null
                    )
                }
            } else {
                return@withContext generateOfflineFallback(userMessage, systemPrompt)
            }
        } catch (e: Exception) {
            return@withContext generateOfflineFallback(userMessage, systemPrompt)
        }
    }

    private fun generateOfflineFallback(userMessage: String, systemPrompt: String): AiResponse {
        val lower = userMessage.lowercase()
        return when {
            lower.contains("bonjour") || lower.contains("こんにちは") || lower.contains("konnichiwa") -> {
                AiResponse(
                    japanese = "こんにちは！お元気ですか？きょうは何を勉強したいですか。",
                    romaji = "Konnichiwa! O-genki desu ka? Kyou wa nani o benkyou shitai desu ka.",
                    french = "Bonjour ! Comment allez-vous ? Que souhaitez-vous étudier aujourd'hui ?",
                    grammarTip = "Astuce Minna no Nihongo : 'お元気ですか' s'utilise pour s'enquérir de la santé d'un interlocuteur."
                )
            }
            lower.contains("merci") || lower.contains("ありがとう") || lower.contains("arigatou") -> {
                AiResponse(
                    japanese = "どういたしまして！いっしょに 頑張りましょう。",
                    romaji = "Douitashimashite! Issho ni ganbarimashou.",
                    french = "Je vous en prie ! Courage, progressons ensemble !",
                    grammarTip = "Formule clé : '〜ましょう' exprime une proposition enthousiaste ('Faisons...') comme dans la leçon 6."
                )
            }
            lower.contains("teishoku") || lower.contains("manger") || lower.contains("menu") || lower.contains("kudasai") || lower.contains("ください") -> {
                AiResponse(
                    japanese = "かしこまりました！少々 お待ちください。お飲み物は いかがですか？",
                    romaji = "Kashikomarimashita! Shoushou omachi kudasai. Onomimono wa ikaga desu ka?",
                    french = "C'est bien noté ! Veuillez patienter un instant. Désirez-vous une boisson ?",
                    grammarTip = "Leçon 13 & 14 : '少々お待ちください' utilise la forme en -Te pour demander poliment de patienter."
                )
            }
            lower.contains("miller") || lower.contains("ミラー") || lower.contains("imc") -> {
                AiResponse(
                    japanese = "わたしは IMCの 社員です。大阪に 住んでいます。日本の 生活は とても 楽しいです！",
                    romaji = "Watashi wa IMC no shain desu. Oosaka ni sunde imasu. Nihon no seikatsu wa totemo tanoshii desu!",
                    french = "Je suis employé chez IMC. J'habite à Osaka. La vie au Japon est vraiment agréable !",
                    grammarTip = "Leçon 15 : '住んでいます' (sunde imasu) est la forme en -Te imasu marquant l'état de résidence."
                )
            }
            else -> {
                AiResponse(
                    japanese = "すばらしいですね！とても いい 日本語です。次は どんな 練習を しますか？",
                    romaji = "Subarashii desu ne! Totemo ii nihongo desu. Tsugi wa donna renshuu o shimasu ka?",
                    french = "C'est remarquable ! Votre japonais est très bien. Quel genre d'entraînement souhaitez-vous faire ensuite ?",
                    grammarTip = "Leçon 8 : 'とても いい' associe l'adverbe intensif 'totemo' avec l'adjectif en -i 'ii' (bon/bien)."
                )
            }
        }
    }
}
