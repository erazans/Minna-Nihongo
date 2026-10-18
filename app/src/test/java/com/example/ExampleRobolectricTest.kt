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
    fun `verify user repository streak and xp`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val repo = LearningRepository(context)

        val initialXp = repo.userStats.value.totalXp
        repo.addXp(20)
        assertEquals(initialXp + 20, repo.userStats.value.totalXp)
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
}
