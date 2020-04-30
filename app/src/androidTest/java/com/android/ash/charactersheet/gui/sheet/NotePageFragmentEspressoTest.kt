package com.android.ash.charactersheet.gui.sheet

import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.ash.charactersheet.*
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.model.Note
import com.d20charactersheet.framework.boc.service.DisplayService
import com.d20charactersheet.framework.boc.service.GameSystem
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.core.Is
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.inject
import java.text.SimpleDateFormat

@RunWith(AndroidJUnit4::class)
class NotePageFragmentEspressoTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val characterHolder: CharacterHolder by inject()
    private val preferenceServiceHolder by inject<PreferenceServiceHolder>()

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
        launchFragmentInContainer<NotePageFragment>(themeResId = R.style.AppTheme)

        // Assert
        onView(withId(R.id.note_date)).check(matches(withText("04/17/2010 19:30")))
        onView(withId(R.id.note_text)).check(matches(withText("myFirstLineOfNote")))
    }

    @Test
    fun fab_onClick_callNoteCreateActivity() {

        // Arrange
        gameSystemHolder.gameSystem = mock()
        characterHolder.character = Character().apply { name = "myCharacter"; notes = listOf() }
        preferenceServiceHolder.preferenceService = mock()

        launchFragmentInContainer<NotePageFragment>(themeResId = R.style.AppTheme)

        // Act
        onView(withId(R.id.favorite_action_button)).perform(ViewActions.click())

        // Assert
        onView(ViewMatchers.isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("myCharacter"))))
    }

}