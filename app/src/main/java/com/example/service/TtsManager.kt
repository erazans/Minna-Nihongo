package com.example.service

import android.content.Context
import android.speech.tts.TextToSpeech
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
                // Fallback to default locale if Japanese is not downloaded on some emulators
                Log.w("TtsManager", "Japanese TTS not fully supported, falling back")
                tts?.setLanguage(Locale.getDefault())
            }
            tts?.setSpeechRate(0.85f) // Slightly slower for language learners!
            isInitialized = true
        } else {
            Log.e("TtsManager", "TTS initialization failed")
        }
    }

    fun speak(text: String, speed: Float = 0.85f) {
        if (!isInitialized || tts == null) return
        tts?.setSpeechRate(speed)
        _isSpeaking.value = true
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "MinnaTts_${System.currentTimeMillis()}")
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
