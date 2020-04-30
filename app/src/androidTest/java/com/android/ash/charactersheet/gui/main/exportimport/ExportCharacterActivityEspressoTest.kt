package com.android.ash.charactersheet.gui.main.exportimport

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.withToolbarTitle
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.service.GameSystem
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.Matchers.startsWith
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class ExportCharacterActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<ExportCharacterActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val character = Character()
        character.id = 1
        character.name = "myCharacter"
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.allCharacters).doReturn(listOf(character))
        gameSystemHolder.gameSystem = gameSystem

        scenario = ActivityScenario.launch(ExportCharacterActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Export Characters"))))
        onView(withId(R.id.export_export_directory)).check(matches(withText(startsWith("Export Directory:"))))
        onView(withId(R.id.listitem_export_checkbox))
                .check(matches(withText("myCharacter")))
                .check(matches(isNotChecked()))
    }

}