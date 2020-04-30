package com.android.ash.charactersheet.gui.sheet.note

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android.ash.charactersheet.*
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.service.GameSystem
import com.nhaarman.mockitokotlin2.mock
import org.hamcrest.Matchers
import org.hamcrest.Matchers.not
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class NoteCreateActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()
    private val characterHolder by inject<CharacterHolder>()
    private val preferenceServiceHolder by inject<PreferenceServiceHolder>()

    private lateinit var scenario: ActivityScenario<NoteCreateActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val gameSystem: GameSystem = mock()
        gameSystemHolder.gameSystem = gameSystem

        preferenceServiceHolder.preferenceService = mock()

        characterHolder.character = Character().apply {
            name = "myCharacter"
        }

        scenario = ActivityScenario.launch(NoteCreateActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("myCharacter"))))
        onView(withId(R.id.note_date)).check(matches(withText(not(Matchers.isEmptyString()))))
        onView(withId(R.id.note_text)).check(matches(withText(Matchers.isEmptyString())))
    }

}