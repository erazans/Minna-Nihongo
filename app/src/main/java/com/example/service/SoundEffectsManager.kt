package com.example.service

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import android.media.AudioManager
import android.media.ToneGenerator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.sin

object SoundEffectsManager {

    private val scope = CoroutineScope(Dispatchers.Default)
    private var toneGen: ToneGenerator? = null

    init {
        try {
            toneGen = ToneGenerator(AudioManager.STREAM_MUSIC, 80)
        } catch (e: Throwable) {
            // ToneGenerator fallback
        }
    }

    /**
     * Joyful ascending chime for correct answers (Duolingo style: E5 -> A5)
     */
    fun playCorrectSound() {
        scope.launch {
            try {
                // Play harmonic two-tone chime
                playTones(
                    listOf(
                        Tone(frequency = 659.25f, durationMs = 90),  // E5
                        Tone(frequency = 880.00f, durationMs = 180)  // A5
                    )
                )
            } catch (e: Throwable) {
                // Fallback to ToneGenerator
                toneGen?.startTone(ToneGenerator.TONE_PROP_PROMPT, 150)
            }
        }
    }

    /**
     * Gentle low buzzer for incorrect answer
     */
    fun playIncorrectSound() {
        scope.launch {
            try {
                playTones(
                    listOf(
                        Tone(frequency = 220.0f, durationMs = 120),
                        Tone(frequency = 175.0f, durationMs = 180)
                    )
                )
            } catch (e: Throwable) {
                toneGen?.startTone(ToneGenerator.TONE_PROP_NACK, 200)
            }
        }
    }

    /**
     * Grand celebratory fanfare when completing a lesson or unit (C5 -> E5 -> G5 -> C6)
     */
    fun playLessonCompleteSound() {
        scope.launch {
            try {
                playTones(
                    listOf(
                        Tone(frequency = 523.25f, durationMs = 100), // C5
                        Tone(frequency = 659.25f, durationMs = 100), // E5
                        Tone(frequency = 783.99f, durationMs = 120), // G5
                        Tone(frequency = 1046.50f, durationMs = 350) // C6 high bell
                    )
                )
            } catch (e: Throwable) {
                toneGen?.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 400)
            }
        }
    }

    /**
     * Flame / streak sound
     */
    fun playStreakSound() {
        scope.launch {
            try {
                playTones(
                    listOf(
                        Tone(frequency = 440.0f, durationMs = 80),
                        Tone(frequency = 554.37f, durationMs = 80),
                        Tone(frequency = 659.25f, durationMs = 200)
                    )
                )
            } catch (e: Throwable) {
                toneGen?.startTone(ToneGenerator.TONE_PROP_ACK, 200)
            }
        }
    }

    private data class Tone(val frequency: Float, val durationMs: Int)

    private fun playTones(tones: List<Tone>) {
        val sampleRate = 44100
        val totalSamples = tones.sumOf { (it.durationMs * sampleRate) / 1000 }
        val buffer = ShortArray(totalSamples)

        var offset = 0
        for (tone in tones) {
            val count = (tone.durationMs * sampleRate) / 1000
            val angleStep = (2.0 * Math.PI * tone.frequency) / sampleRate

            for (i in 0 until count) {
                // Envelope window: soft attack and gentle decay to avoid audio clicks
                val progress = i.toFloat() / count.toFloat()
                val envelope = when {
                    progress < 0.1f -> progress / 0.1f
                    progress > 0.7f -> (1.0f - progress) / 0.3f
                    else -> 1.0f
                }
                val sampleValue = (sin(i * angleStep) * Short.MAX_VALUE * 0.75f * envelope).toInt()
                if (offset + i < buffer.size) {
                    buffer[offset + i] = sampleValue.coerceIn(Short.MIN_VALUE.toInt(), Short.MAX_VALUE.toInt()).toShort()
                }
            }
            offset += count
        }

        val minBufferSize = AudioTrack.getMinBufferSize(
            sampleRate,
            AudioFormat.CHANNEL_OUT_MONO,
            AudioFormat.ENCODING_PCM_16BIT
        )

        val track = AudioTrack.Builder()
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build()
            )
            .setAudioFormat(
                AudioFormat.Builder()
                    .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                    .setSampleRate(sampleRate)
                    .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                    .build()
            )
            .setBufferSizeInBytes(maxOf(minBufferSize, buffer.size * 2))
            .setTransferMode(AudioTrack.MODE_STATIC)
            .build()

        track.write(buffer, 0, buffer.size)
        track.play()

        // Wait tone duration before releasing
        val totalDuration = tones.sumOf { it.durationMs }
        Thread.sleep(totalDuration.toLong() + 50L)
        track.stop()
        track.release()
    }
}
