package com.example.service

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale

class SpeechRecognizerHelper(private val context: Context) {

    private var speechRecognizer: SpeechRecognizer? = null

    private val _isListening = MutableStateFlow(false)
    val isListening: StateFlow<Boolean> = _isListening.asStateFlow()

    private val _recognizedText = MutableStateFlow("")
    val recognizedText: StateFlow<String> = _recognizedText.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun startListening(onResult: (String) -> Unit) {
        if (!SpeechRecognizer.isRecognitionAvailable(context)) {
            _errorMessage.value = "La reconnaissance vocale n'est pas disponible sur cet appareil."
            return
        }

        stopListening()

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context).apply {
            setRecognitionListener(object : RecognitionListener {
                override fun onReadyForSpeech(params: Bundle?) {
                    _isListening.value = true
                    _errorMessage.value = null
                }

                override fun onBeginningOfSpeech() {}
                override fun onRmsChanged(rmsdB: Float) {}
                override fun onBufferReceived(buffer: ByteArray?) {}
                override fun onEndOfSpeech() {
                    _isListening.value = false
                }

                override fun onError(error: Int) {
                    _isListening.value = false
                    val errorMsg = when (error) {
                        SpeechRecognizer.ERROR_AUDIO -> "Erreur d'enregistrement audio."
                        SpeechRecognizer.ERROR_NO_MATCH -> "Aucune parole reconnue. Essayez à nouveau."
                        SpeechRecognizer.ERROR_NETWORK, SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "Erreur réseau pour la reconnaissance."
                        else -> "Erreur lors de la capture vocale ($error)."
                    }
                    _errorMessage.value = errorMsg
                }

                override fun onResults(results: Bundle?) {
                    _isListening.value = false
                    val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                    val text = matches?.firstOrNull() ?: ""
                    _recognizedText.value = text
                    onResult(text)
                }

                override fun onPartialResults(partialResults: Bundle?) {}
                override fun onEvent(eventType: Int, params: Bundle?) {}
            })
        }

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ja-JP")
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "ja-JP")
            putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE, "ja-JP")
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Parlez en japonais...")
        }

        try {
            speechRecognizer?.startListening(intent)
        } catch (e: Exception) {
            _errorMessage.value = e.message
            _isListening.value = false
        }
    }

    fun stopListening() {
        try {
            speechRecognizer?.stopListening()
            speechRecognizer?.destroy()
        } catch (e: Exception) {
            // ignore
        }
        speechRecognizer = null
        _isListening.value = false
    }

    /**
     * Calculates a similarity score between 0.0 and 1.0 comparing candidate to target.
     */
    fun evaluatePronunciation(spoken: String, target: String, acceptableList: List<String>): Float {
        val cleanSpoken = spoken.replace(" ", "").replace("。", "").replace("、", "").lowercase()
        val cleanTarget = target.replace(" ", "").replace("。", "").replace("、", "").lowercase()

        if (cleanSpoken == cleanTarget) return 1.0f

        for (acceptable in acceptableList) {
            val cleanAcc = acceptable.replace(" ", "").replace("。", "").replace("、", "").lowercase()
            if (cleanSpoken.contains(cleanAcc) || cleanAcc.contains(cleanSpoken)) {
                return 0.95f
            }
        }

        // Levenshtein distance similarity
        val dist = levenshtein(cleanSpoken, cleanTarget)
        val maxLen = maxOf(cleanSpoken.length, cleanTarget.length)
        if (maxLen == 0) return 1.0f
        val similarity = 1.0f - (dist.toFloat() / maxLen.toFloat())
        return maxOf(0.0f, similarity)
    }

    private fun levenshtein(s1: String, s2: String): Int {
        val m = s1.length
        val n = s2.length
        val dp = Array(m + 1) { IntArray(n + 1) }

        for (i in 0..m) dp[i][0] = i
        for (j in 0..n) dp[0][j] = j

        for (i in 1..m) {
            for (j in 1..n) {
                val cost = if (s1[i - 1] == s2[j - 1]) 0 else 1
                dp[i][j] = minOf(
                    dp[i - 1][j] + 1,
                    dp[i][j - 1] + 1,
                    dp[i - 1][j - 1] + cost
                )
            }
        }
        return dp[m][n]
    }
}
