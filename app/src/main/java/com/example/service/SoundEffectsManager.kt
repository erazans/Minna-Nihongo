package com.example.service

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import android.media.ToneGenerator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.exp
import kotlin.math.sin

object SoundEffectsManager {

    private val scope = CoroutineScope(Dispatchers.Default)
    private var toneGen: ToneGenerator? = null

    init {
        try {
            toneGen = ToneGenerator(AudioManager.STREAM_MUSIC, 85)
        } catch (_: Throwable) {
            // ToneGenerator fallback
        }
    }

    /**
     * Données d'une note polyphonique avec enveloppe de cloche cristalline (style Duolingo)
     */
    private data class ChimeNote(
        val frequency: Float,
        val startMs: Int,
        val durationMs: Int,
        val amplitude: Float = 1.0f,
        val decayRate: Float = 5.2f,
        val brightHarmonics: Boolean = true
    )

    /**
     * Son de bonne réponse ultra-clair et cristallin (style Duolingo : double carillon ascendant B5 -> E6
     * avec harmoniques de célesta/marimba et résonance polyphonique).
     */
    fun playCorrectSound() {
        scope.launch {
            try {
                playPolyphonicChime(
                    listOf(
                        // 1ère note : attaque vive et claire (B5 + corps harmonique E5)
                        ChimeNote(
                            frequency = 987.77f, // B5
                            startMs = 0,
                            durationMs = 240,
                            amplitude = 0.78f,
                            decayRate = 5.8f,
                            brightHarmonics = true
                        ),
                        ChimeNote(
                            frequency = 659.25f, // E5 (chaleur du marimba)
                            startMs = 0,
                            durationMs = 200,
                            amplitude = 0.30f,
                            decayRate = 6.5f,
                            brightHarmonics = false
                        ),
                        // 2ème note (85ms plus tard) : résolution lumineuse et cristalline sur l'accord de Mi Majeur (E6 + G#5 + B5)
                        ChimeNote(
                            frequency = 1318.51f, // E6 (cloche cristalline aiguë)
                            startMs = 85,
                            durationMs = 380,
                            amplitude = 0.95f,
                            decayRate = 4.6f,
                            brightHarmonics = true
                        ),
                        ChimeNote(
                            frequency = 830.61f, // G#5 (tierce majeure chaleureuse)
                            startMs = 85,
                            durationMs = 340,
                            amplitude = 0.36f,
                            decayRate = 5.2f,
                            brightHarmonics = false
                        ),
                        ChimeNote(
                            frequency = 987.77f, // B5 (quinte)
                            startMs = 85,
                            durationMs = 340,
                            amplitude = 0.32f,
                            decayRate = 5.4f,
                            brightHarmonics = false
                        )
                    )
                )
            } catch (_: Throwable) {
                toneGen?.startTone(ToneGenerator.TONE_PROP_PROMPT, 150)
            }
        }
    }

    /**
     * Son doux et feutré de mauvaise réponse (double note boisée descendante, non agressive)
     */
    fun playIncorrectSound() {
        scope.launch {
            try {
                playPolyphonicChime(
                    listOf(
                        ChimeNote(
                            frequency = 329.63f, // E4
                            startMs = 0,
                            durationMs = 150,
                            amplitude = 0.65f,
                            decayRate = 5.5f,
                            brightHarmonics = false
                        ),
                        ChimeNote(
                            frequency = 246.94f, // B3
                            startMs = 95,
                            durationMs = 240,
                            amplitude = 0.70f,
                            decayRate = 4.8f,
                            brightHarmonics = false
                        )
                    )
                )
            } catch (_: Throwable) {
                toneGen?.startTone(ToneGenerator.TONE_PROP_NACK, 200)
            }
        }
    }

    /**
     * Fanfare cristalline de victoire à la fin d'une leçon (arpège lumineux C5 -> E5 -> G5 -> C6)
     */
    fun playLessonCompleteSound() {
        scope.launch {
            try {
                playPolyphonicChime(
                    listOf(
                        ChimeNote(frequency = 523.25f, startMs = 0, durationMs = 240, amplitude = 0.65f, decayRate = 5.0f),   // C5
                        ChimeNote(frequency = 659.25f, startMs = 90, durationMs = 260, amplitude = 0.72f, decayRate = 5.0f),  // E5
                        ChimeNote(frequency = 783.99f, startMs = 180, durationMs = 280, amplitude = 0.80f, decayRate = 4.8f), // G5
                        ChimeNote(frequency = 1046.50f, startMs = 275, durationMs = 560, amplitude = 0.95f, decayRate = 3.6f), // C6
                        ChimeNote(frequency = 659.25f, startMs = 275, durationMs = 500, amplitude = 0.35f, decayRate = 4.2f, brightHarmonics = false),
                        ChimeNote(frequency = 783.99f, startMs = 275, durationMs = 500, amplitude = 0.35f, decayRate = 4.2f, brightHarmonics = false)
                    )
                )
            } catch (_: Throwable) {
                toneGen?.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 400)
            }
        }
    }

    /**
     * Carillon dynamique pour le maintien de la série (streak)
     */
    fun playStreakSound() {
        scope.launch {
            try {
                playPolyphonicChime(
                    listOf(
                        ChimeNote(frequency = 587.33f, startMs = 0, durationMs = 180, amplitude = 0.70f, decayRate = 5.5f),   // D5
                        ChimeNote(frequency = 739.99f, startMs = 75, durationMs = 200, amplitude = 0.78f, decayRate = 5.2f),  // F#5
                        ChimeNote(frequency = 880.00f, startMs = 150, durationMs = 220, amplitude = 0.85f, decayRate = 5.0f), // A5
                        ChimeNote(frequency = 1174.66f, startMs = 225, durationMs = 420, amplitude = 0.95f, decayRate = 4.0f) // D6
                    )
                )
            } catch (_: Throwable) {
                toneGen?.startTone(ToneGenerator.TONE_PROP_ACK, 200)
            }
        }
    }

    /**
     * Synthétiseur PCM polyphonique à enveloppe percussive exponentielle et harmoniques de célesta.
     */
    private suspend fun playPolyphonicChime(notes: List<ChimeNote>) {
        if (notes.isEmpty()) return

        val sampleRate = 44100
        val totalDurationMs = notes.maxOf { it.startMs + it.durationMs } + 25
        val totalSamples = (totalDurationMs * sampleRate) / 1000
        val mixBuffer = FloatArray(totalSamples)

        for (note in notes) {
            val startSample = (note.startMs * sampleRate) / 1000
            val sampleCount = (note.durationMs * sampleRate) / 1000
            val attackSamples = maxOf(1, (3.5f * sampleRate / 1000f).toInt()) // 3.5ms crisp attack

            val twoPiOverSr = (2.0 * Math.PI) / sampleRate
            val w1 = twoPiOverSr * note.frequency
            val w2 = twoPiOverSr * (note.frequency * 2.0f)
            val w3 = twoPiOverSr * (note.frequency * 3.0f)
            val w4 = twoPiOverSr * (note.frequency * 4.0f)

            for (i in 0 until sampleCount) {
                val targetIndex = startSample + i
                if (targetIndex >= totalSamples) break

                val progress = i.toFloat() / sampleCount.toFloat()

                // Attack / exponential bell decay + smooth tail fade-out to zero
                val attackEnv = if (i < attackSamples) {
                    i.toFloat() / attackSamples.toFloat()
                } else {
                    1.0f
                }
                val expDecay = exp(-note.decayRate * progress)
                val tailFade = if (progress > 0.85f) {
                    (1.0f - progress) / 0.15f
                } else {
                    1.0f
                }
                val env = attackEnv * expDecay * tailFade

                // Fundamental + crystalline bell harmonics (octave, 12th, double-octave sparkle)
                var rawWave = sin(i * w1).toFloat()
                if (note.brightHarmonics) {
                    val h2Env = exp(-note.decayRate * 1.45f * progress)
                    val h3Env = exp(-note.decayRate * 2.1f * progress)
                    val h4Env = exp(-note.decayRate * 3.2f * progress)
                    rawWave += 0.36f * sin(i * w2).toFloat() * h2Env
                    rawWave += 0.14f * sin(i * w3).toFloat() * h3Env
                    rawWave += 0.06f * sin(i * w4).toFloat() * h4Env
                } else {
                    val h2Env = exp(-note.decayRate * 1.6f * progress)
                    rawWave += 0.20f * sin(i * w2).toFloat() * h2Env
                }

                mixBuffer[targetIndex] += rawWave * note.amplitude * env
            }
        }

        // Normalisation douce pour éviter toute saturation tout en gardant un volume clair et présent
        var peak = 0.01f
        for (s in mixBuffer) {
            val absVal = kotlin.math.abs(s)
            if (absVal > peak) peak = absVal
        }
        val targetGain = if (peak > 0.92f) 0.92f / peak else 0.88f / maxOf(peak, 0.88f)

        val pcmBuffer = ShortArray(totalSamples)
        for (i in 0 until totalSamples) {
            val normalized = (mixBuffer[i] * targetGain * Short.MAX_VALUE).toInt()
            pcmBuffer[i] = normalized.coerceIn(Short.MIN_VALUE.toInt(), Short.MAX_VALUE.toInt()).toShort()
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
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
            .setAudioFormat(
                AudioFormat.Builder()
                    .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                    .setSampleRate(sampleRate)
                    .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                    .build()
            )
            .setBufferSizeInBytes(maxOf(minBufferSize, pcmBuffer.size * 2))
            .setTransferMode(AudioTrack.MODE_STATIC)
            .build()

        track.write(pcmBuffer, 0, pcmBuffer.size)
        track.play()

        delay(totalDurationMs.toLong() + 45L)
        try {
            track.stop()
        } catch (_: Throwable) {
        }
        track.release()
    }
}
