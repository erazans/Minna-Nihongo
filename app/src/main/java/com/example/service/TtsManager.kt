package com.example.service

import android.content.Context
import android.media.AudioAttributes
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.speech.tts.Voice
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale

class TtsManager(context: Context) : TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null
    private var isInitialized = false

    private val _isSpeaking = MutableStateFlow(false)
    val isSpeaking: StateFlow<Boolean> = _isSpeaking.asStateFlow()

    init {
        tts = TextToSpeech(context.applicationContext, this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.JAPANESE)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.w("TtsManager", "Japanese TTS not fully supported, falling back")
                tts?.setLanguage(Locale.getDefault())
            }

            // Optimize for human-like, high-fidelity feminine Gemini-style voice
            configureGeminiFeminineVoice()

            tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onStart(utteranceId: String?) {
                    _isSpeaking.value = true
                }

                override fun onDone(utteranceId: String?) {
                    _isSpeaking.value = false
                }

                @Deprecated("Deprecated in Java")
                override fun onError(utteranceId: String?) {
                    _isSpeaking.value = false
                }

                override fun onError(utteranceId: String?, errorCode: Int) {
                    _isSpeaking.value = false
                }
            })

            isInitialized = true
        } else {
            Log.e("TtsManager", "TTS initialization failed")
        }
    }

    /**
     * Configures the voice engine to select the highest-quality human female voice
     * matching the natural, warm, and articulate tone of the Gemini AI assistant.
     */
    private fun configureGeminiFeminineVoice() {
        try {
            // Enhanced speech audio attributes
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_NAVIGATION_GUIDANCE)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build()
            tts?.setAudioAttributes(audioAttributes)

            // Inspect all available Japanese voices
            val availableVoices = tts?.voices?.filter { voice ->
                voice.locale.language.equals("ja", ignoreCase = true) ||
                        voice.locale == Locale.JAPANESE ||
                        voice.locale.toLanguageTag().startsWith("ja", ignoreCase = true)
            }

            if (!availableVoices.isNullOrEmpty()) {
                val bestFeminineVoice = availableVoices.maxByOrNull { v ->
                    var score = 0
                    val nameLower = v.name.lowercase()

                    // Prioritize Japanese feminine voices in Google TTS
                    // "jad", "jab", "htm", "gfm" are Google's flagship female neural voices
                    if (nameLower.contains("jad") || nameLower.contains("jab") ||
                        nameLower.contains("htm") || nameLower.contains("gfm") ||
                        nameLower.contains("female") || nameLower.contains("woman")) {
                        score += 150
                    }

                    // Penalize known male voice models ("jac", "hda", "gmm", "male")
                    if (nameLower.contains("jac") || nameLower.contains("hda") ||
                        nameLower.contains("gmm") || nameLower.contains("male") ||
                        nameLower.contains("man")) {
                        score -= 100
                    }

                    // Neural and Network quality boosts
                    if (nameLower.contains("neural") || nameLower.contains("wavenet") || nameLower.contains("journey")) {
                        score += 60
                    }
                    if (nameLower.contains("network")) {
                        score += 40
                    }

                    // Voice quality constants
                    if (v.quality == Voice.QUALITY_VERY_HIGH) score += 50
                    if (v.quality == Voice.QUALITY_HIGH) score += 30

                    // Latency / responsiveness
                    if (v.latency == Voice.LATENCY_NORMAL || v.latency == Voice.LATENCY_VERY_LOW) score += 10

                    score
                }

                if (bestFeminineVoice != null) {
                    tts?.voice = bestFeminineVoice
                    Log.d("TtsManager", "Selected Gemini-style feminine voice: ${bestFeminineVoice.name}")
                }
            }

            // Warm, natural feminine pitch & fluid conversational cadence (Gemini style)
            // 1.08f pitch provides a pleasant, gentle feminine tone without robotic distortion
            tts?.setPitch(1.08f)
            tts?.setSpeechRate(0.96f)
        } catch (e: Exception) {
            Log.w("TtsManager", "Error configuring advanced voice attributes: ${e.message}")
        }
    }

    /**
     * Cleans up furigana markup and special characters so the voice reads
     * purely phonetically and seamlessly like a human speaker.
     */
    private fun cleanJapaneseTextForSpeech(rawText: String): String {
        var cleaned = rawText
        // Replace [Kanji|Kana] furigana markup with Kana reading for pristine phonetic speech
        cleaned = cleaned.replace(Regex("\\[(.*?)\\|(.*?)\\]")) { matchResult ->
            matchResult.groupValues[2].ifBlank { matchResult.groupValues[1] }
        }
        // Remove brackets, tildes, slashes, and symbols that cause robotic utterances
        cleaned = cleaned.replace("~", "")
            .replace("～", "")
            .replace("・", " ")
            .replace("【", "")
            .replace("】", "")
            .replace("[", "")
            .replace("]", "")
            .replace("（", "")
            .replace("）", "")
            .replace("(", "")
            .replace(")", "")
            .trim()

        return cleaned
    }

    fun speak(text: String, speed: Float = 0.96f) {
        if (!isInitialized || tts == null) return
        val speechText = cleanJapaneseTextForSpeech(text)
        if (speechText.isBlank()) return

        tts?.setSpeechRate(speed)
        _isSpeaking.value = true
        tts?.speak(speechText, TextToSpeech.QUEUE_FLUSH, null, "MinnaTts_${System.currentTimeMillis()}")
    }

    fun stop() {
        tts?.stop()
        _isSpeaking.value = false
    }

    fun destroy() {
        tts?.stop()
        tts?.shutdown()
        tts = null
    }
}
