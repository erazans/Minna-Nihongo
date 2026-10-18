package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.datasource.MinnaDatabase
import com.example.data.datasource.MinnaExercisesData
import com.example.data.repository.LearningRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Minna Nihongo", appName)
    }

    @Test
    fun `verify minna curriculum database`() {
        val lessons = MinnaDatabase.lessons
        assertEquals(25, lessons.size)

        val vocab = MinnaDatabase.vocabulary
        assertTrue(vocab.isNotEmpty())

        val grammar = MinnaDatabase.grammarPoints
        assertTrue(grammar.isNotEmpty())

        val lesson1Exercises = MinnaExercisesData.getExercisesForLesson(1)
        assertTrue(lesson1Exercises.isNotEmpty())
    }

    @Test
    fun `verify user repository bookmarks and kana mastery`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val repo = LearningRepository(context)

        repo.toggleBookmarkWord("word_test_1")
        assertTrue(repo.userStats.value.bookmarkedWordIds.contains("word_test_1"))
        repo.toggleBookmarkWord("word_test_1")
        assertTrue(!repo.userStats.value.bookmarkedWordIds.contains("word_test_1"))

        repo.toggleMasterKana("あ")
        assertTrue(repo.userStats.value.masteredKana.contains("あ"))
    }

    @Test
    fun `verify minna book exercises by section`() {
        val renshuuB = MinnaExercisesData.getExercisesForLesson(1, com.example.data.model.ChapterSectionType.RENSHUU_B)
        assertTrue(renshuuB.isNotEmpty())
        assertEquals(com.example.data.model.ChapterSectionType.RENSHUU_B, renshuuB.first().section)
    }

    @Test
    fun `verify all units are unlocked by default from start`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val repo = LearningRepository(context)
        val lessons = repo.lessons.value
        assertEquals(25, lessons.size)
        assertTrue("Toutes les unités doivent être déverrouillées dès le début", lessons.all { it.isUnlocked })
    }

    @Test
    fun `verify repeatable review exercises generation and vocabulary coverage`() {
        // Test repeatable review generator for Unit 1 and Unit 10
        val reviewEx1 = com.example.data.datasource.MinnaReviewGenerator.generateRepeatableReview(1, 10)
        assertEquals(10, reviewEx1.size)
        assertTrue(reviewEx1.all { it.section == com.example.data.model.ChapterSectionType.REVISION })

        val reviewEx10 = com.example.data.datasource.MinnaReviewGenerator.generateRepeatableReview(10, 10)
        assertEquals(10, reviewEx10.size)

        // Test vocabulary presence for Lessons 1 to 20
        val vocab = MinnaDatabase.vocabulary
        for (lessonId in 1..20) {
            val words = vocab.filter { it.lessonId == lessonId }
            assertTrue("La leçon $lessonId doit contenir du vocabulaire", words.isNotEmpty())
        }
    }

    @Test
    fun `verify minna verbs data and declensions`() {
        val verbs = com.example.data.datasource.MinnaVerbData.allVerbs
        assertEquals(105, verbs.size)

        // Verify key verbs have correct conjugations
        val taberu = verbs.find { it.masuKanji == "食べます" }
        assertNotNull(taberu)
        assertEquals("食べる", taberu?.dictionaryKanji)
        assertEquals("食べて", taberu?.teForm?.kanji)
        assertEquals("食べた", taberu?.taForm?.kanji)
        assertEquals("食べない", taberu?.naiForm?.kanji)
        assertEquals(com.example.data.model.VerbGroup.GROUP_2, taberu?.group)

        val iku = verbs.find { it.masuKanji == "行きます" }
        assertNotNull(iku)
        assertEquals("行く", iku?.dictionaryKanji)
        assertEquals("行って", iku?.teForm?.kanji)
        assertEquals(com.example.data.model.VerbGroup.GROUP_1, iku?.group)

        val kuru = verbs.find { it.masuKanji == "来ます" }
        assertNotNull(kuru)
        assertEquals("来る", kuru?.dictionaryKanji)
        assertEquals("来て", kuru?.teForm?.kanji)
        assertEquals("来ない", kuru?.naiForm?.kanji)
        assertEquals(com.example.data.model.VerbGroup.GROUP_3, kuru?.group)
    }
}
