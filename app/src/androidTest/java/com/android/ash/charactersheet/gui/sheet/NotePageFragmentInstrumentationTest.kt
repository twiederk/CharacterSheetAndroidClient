package com.android.ash.charactersheet.gui.sheet

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.model.Note
import com.d20charactersheet.framework.boc.service.DisplayService
import com.d20charactersheet.framework.boc.service.GameSystem
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.inject
import java.text.SimpleDateFormat

@RunWith(AndroidJUnit4::class)
class NotePageFragmentInstrumentationTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val characterHolder: CharacterHolder by inject()

    @Test
    fun onCreateView() {
        // Arrange
        val displayService: DisplayService = mock()
        whenever(displayService.getDisplayNoteFirstLine(any())).doReturn("myFirstLineOfNote")

        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(displayService)

        gameSystemHolder.gameSystem = gameSystem
        val note = Note().apply {
            text = "myNote"
            date = SimpleDateFormat("MM/dd/yyyy HH:mm").parse("04/17/2010 19:30")
        }
        characterHolder.character = Character().apply { notes = listOf(note) }

        // Act
        launchFragmentInContainer<NotePageFragment>()

        // Assert
        onView(withId(R.id.note_date)).check(matches(withText("04/17/2010 19:30")))
        onView(withId(R.id.note_text)).check(matches(withText("myFirstLineOfNote")))
    }

}