package com.example.data.datasource

import com.example.data.model.ChapterSectionType
import com.example.data.model.Exercise
import com.example.data.model.ExerciseType
import com.example.data.model.VocabularyWord

object MinnaReviewGenerator {

    /**
     * Generates a dynamic, repeatable session of vocabulary and grammar exercises
     * tied directly to the specified unit (lessonId) for long-term retention (SRS).
     */
    fun generateRepeatableReview(lessonId: Int, count: Int = 10): List<Exercise> {
        val unitWords = MinnaVocabularyData.allVocabulary.filter { it.lessonId == lessonId }.ifEmpty {
            MinnaVocabularyData.lesson1Vocabulary
        }
        val allWords = MinnaVocabularyData.allVocabulary
        val unitGrammar = MinnaGrammarData.allGrammarPoints.filter { it.lessonId == lessonId }

        val grammarCount = if (unitGrammar.isNotEmpty()) minOf(3, unitGrammar.size) else 0
        val vocabCount = count - grammarCount
        val selectedWords = unitWords.shuffled().take(vocabCount)

        val exercises = mutableListOf<Exercise>()

        // 1. Add grammar exercises from the unit
        if (unitGrammar.isNotEmpty()) {
            unitGrammar.shuffled().take(grammarCount).forEachIndexed { gIdx, gPoint ->
                val timestamp = System.currentTimeMillis()
                val example = gPoint.examples.firstOrNull()
                if (example != null && example.jp.contains(" ")) {
                    val tokens = example.jp.split(" ").filter { it.isNotBlank() }
                    val dummyTokens = listOf("は", "が", "を", "に", "で", "です").filter { !tokens.contains(it) }.take(2)
                    exercises.add(
                        Exercise(
                            id = "rev_g_${lessonId}_${gPoint.id}_${timestamp}_$gIdx",
                            lessonId = lessonId,
                            section = ChapterSectionType.REVISION,
                            bookReference = "Grammaire (Unité $lessonId)",
                            type = ExerciseType.WORD_ORDER,
                            questionFr = "Reconstituez la phrase selon la règle '${gPoint.pattern}' : '${example.fr}'",
                            promptJp = gPoint.formula,
                            targetSentenceJp = example.jp,
                            targetSentenceRomaji = example.romaji,
                            options = (tokens + dummyTokens).shuffled(),
                            correctOptions = tokens,
                            hint = "Formule : ${gPoint.formula} • Romaji : ${example.romaji}",
                            audioText = example.jp
                        )
                    )
                } else {
                    val distractors = MinnaGrammarData.allGrammarPoints
                        .filter { it.id != gPoint.id && it.meaningFr != gPoint.meaningFr }
                        .map { it.meaningFr }
                        .distinct()
                        .shuffled()
                        .take(3)
                    val options = (distractors + gPoint.meaningFr).shuffled()
                    exercises.add(
                        Exercise(
                            id = "rev_g_${lessonId}_mc_${gPoint.id}_${timestamp}_$gIdx",
                            lessonId = lessonId,
                            section = ChapterSectionType.REVISION,
                            bookReference = "Règle Grammaticale (Unité $lessonId)",
                            type = ExerciseType.MULTIPLE_CHOICE,
                            questionFr = "Quelle est la signification de la structure '${gPoint.pattern}' ?",
                            promptJp = gPoint.formula,
                            targetSentenceJp = gPoint.pattern,
                            targetSentenceRomaji = gPoint.formula,
                            options = options,
                            correctOptions = listOf(gPoint.meaningFr),
                            hint = gPoint.explanation,
                            audioText = gPoint.examples.firstOrNull()?.jp
                        )
                    )
                }
            }
        }

        selectedWords.forEachIndexed { index, word ->
            val timestamp = System.currentTimeMillis()
            when (index % 5) {
                0 -> {
                    // Type 1: QCM Japonais -> Français
                    val distractors = (unitWords + allWords)
                        .filter { it.id != word.id && it.french != word.french }
                        .map { it.french }
                        .distinct()
                        .shuffled()
                        .take(3)
                    val options = (distractors + word.french).shuffled()
                    val jpPrompt = if (word.kanji != word.kana && word.kanji.isNotBlank()) {
                        "${word.kanji} (${word.kana})"
                    } else word.kana

                    exercises.add(
                        Exercise(
                            id = "rev_${lessonId}_mc_fr_${word.id}_${timestamp}_$index",
                            lessonId = lessonId,
                            section = ChapterSectionType.REVISION,
                            bookReference = "Révision Vocabulaire (Unité $lessonId)",
                            type = ExerciseType.MULTIPLE_CHOICE,
                            questionFr = "Que signifie ce mot de l'Unité $lessonId ?",
                            promptJp = jpPrompt,
                            targetSentenceJp = word.kanji.ifEmpty { word.kana },
                            targetSentenceRomaji = word.romaji,
                            options = options,
                            correctOptions = listOf(word.french),
                            hint = "Lecture : ${word.romaji} • Catégorie : ${word.category.labelFr}",
                            audioText = word.kana
                        )
                    )
                }
                1 -> {
                    // Type 2: QCM Français -> Japonais
                    val distractors = (unitWords + allWords)
                        .filter { it.id != word.id && it.kanji != word.kanji }
                        .map { if (it.kanji != it.kana && it.kanji.isNotBlank()) "${it.kanji} (${it.kana})" else it.kana }
                        .distinct()
                        .shuffled()
                        .take(3)
                    val correctTarget = if (word.kanji != word.kana && word.kanji.isNotBlank()) {
                        "${word.kanji} (${word.kana})"
                    } else word.kana
                    val options = (distractors + correctTarget).shuffled()

                    exercises.add(
                        Exercise(
                            id = "rev_${lessonId}_mc_jp_${word.id}_${timestamp}_$index",
                            lessonId = lessonId,
                            section = ChapterSectionType.REVISION,
                            bookReference = "Traduction Japonaise (Unité $lessonId)",
                            type = ExerciseType.MULTIPLE_CHOICE,
                            questionFr = "Comment dit-on en japonais : '${word.french}' ?",
                            promptJp = null,
                            targetSentenceJp = word.kanji.ifEmpty { word.kana },
                            targetSentenceRomaji = word.romaji,
                            options = options,
                            correctOptions = listOf(correctTarget),
                            hint = "Indice romaji : ${word.romaji}",
                            audioText = word.kana
                        )
                    )
                }
                2 -> {
                    // Type 3: Compréhension Orale / Écoute Audio
                    val distractors = (unitWords + allWords)
                        .filter { it.id != word.id && it.kanji != word.kanji }
                        .map { if (it.kanji != it.kana && it.kanji.isNotBlank()) "${it.kanji} (${it.kana})" else it.kana }
                        .distinct()
                        .shuffled()
                        .take(3)
                    val correctTarget = if (word.kanji != word.kana && word.kanji.isNotBlank()) {
                        "${word.kanji} (${word.kana})"
                    } else word.kana
                    val options = (distractors + correctTarget).shuffled()

                    exercises.add(
                        Exercise(
                            id = "rev_${lessonId}_listen_${word.id}_${timestamp}_$index",
                            lessonId = lessonId,
                            section = ChapterSectionType.REVISION,
                            bookReference = "Écoute Audio (Unité $lessonId)",
                            type = ExerciseType.LISTENING_SELECT,
                            questionFr = "Écoutez l'audio et sélectionnez le mot prononcé :",
                            promptJp = "🔊 Mot à écouter...",
                            targetSentenceJp = word.kanji.ifEmpty { word.kana },
                            targetSentenceRomaji = word.romaji,
                            options = options,
                            correctOptions = listOf(correctTarget),
                            hint = "Sens en français : ${word.french}",
                            audioText = word.kana
                        )
                    )
                }
                3 -> {
                    // Type 4: Remise en ordre des mots ou Complétion
                    if (word.exampleJp.isNotBlank() && word.exampleFr.isNotBlank()) {
                        val tokens = word.exampleJp.split(" ").filter { it.isNotBlank() }
                        if (tokens.size >= 2) {
                            val dummyTokens = listOf("です", "に", "を", "は", "で", "から").filter { !tokens.contains(it) }.take(2)
                            exercises.add(
                                Exercise(
                                    id = "rev_${lessonId}_order_${word.id}_${timestamp}_$index",
                                    lessonId = lessonId,
                                    section = ChapterSectionType.REVISION,
                                    bookReference = "Syntaxe & Contexte (Unité $lessonId)",
                                    type = ExerciseType.WORD_ORDER,
                                    questionFr = "Reconstituez la phrase : '${word.exampleFr}'",
                                    promptJp = null,
                                    targetSentenceJp = word.exampleJp,
                                    targetSentenceRomaji = word.exampleRomaji,
                                    options = (tokens + dummyTokens).shuffled(),
                                    correctOptions = tokens,
                                    hint = "Transcription : ${word.exampleRomaji}",
                                    audioText = word.exampleJp
                                )
                            )
                        } else {
                            addFillBlankFallback(exercises, lessonId, word, index, timestamp)
                        }
                    } else {
                        addFillBlankFallback(exercises, lessonId, word, index, timestamp)
                    }
                }
                4 -> {
                    // Type 5: Expression & Prononciation Orale
                    val promptText = if (word.kanji != word.kana && word.kanji.isNotBlank()) {
                        "${word.kanji} (${word.kana})"
                    } else word.kana

                    exercises.add(
                        Exercise(
                            id = "rev_${lessonId}_speech_${word.id}_${timestamp}_$index",
                            lessonId = lessonId,
                            section = ChapterSectionType.REVISION,
                            bookReference = "Prononciation Orale (Unité $lessonId)",
                            type = ExerciseType.SPEECH_PRONUNCIATION,
                            questionFr = "Prononcez clairement ce mot à voix haute (${word.french}) :",
                            promptJp = promptText,
                            targetSentenceJp = word.kana,
                            targetSentenceRomaji = word.romaji,
                            options = listOf(word.kana, word.kanji.ifEmpty { word.kana }, word.romaji),
                            correctOptions = listOf(word.kana, word.kanji.ifEmpty { word.kana }, word.romaji),
                            hint = "Prononciation attendue : ${word.romaji}",
                            audioText = word.kana
                        )
                    )
                }
            }
        }

        return exercises
    }

    private fun addFillBlankFallback(
        exercises: MutableList<Exercise>,
        lessonId: Int,
        word: VocabularyWord,
        index: Int,
        timestamp: Long
    ) {
        val options = listOf(word.french, "Autre", "Inconnu", "Non").shuffled()
        exercises.add(
            Exercise(
                id = "rev_${lessonId}_fill_${word.id}_${timestamp}_$index",
                lessonId = lessonId,
                section = ChapterSectionType.REVISION,
                bookReference = "Sens & Usage (Unité $lessonId)",
                type = ExerciseType.MULTIPLE_CHOICE,
                questionFr = "Sélectionnez la bonne traduction de '${word.kanji.ifEmpty { word.kana }}' :",
                promptJp = word.kanji.ifEmpty { word.kana },
                targetSentenceJp = word.kanji.ifEmpty { word.kana },
                targetSentenceRomaji = word.romaji,
                options = options,
                correctOptions = listOf(word.french),
                hint = "Romaji : ${word.romaji}",
                audioText = word.kana
            )
        )
    }
}
