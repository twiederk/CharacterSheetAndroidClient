package com.android.ash.charactersheet.gui.sheet

import android.graphics.Color
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.PreferenceServiceHolder
import com.android.ash.charactersheet.boc.service.PreferenceService
import com.android.ash.charactersheet.withToolbarTitle
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.model.Race
import com.d20charactersheet.framework.boc.model.Sex
import com.d20charactersheet.framework.boc.service.GameSystem
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class CharacterSheetActivityInstrumentationTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()
    private val preferencesServiceHolder by inject<PreferenceServiceHolder>()
    private val characterHolder by inject<CharacterHolder>()

    private lateinit var scenario: ActivityScenario<CharacterSheetActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val preferenceService: PreferenceService = mock()
        whenever(preferenceService.getBoolean(PreferenceService.SHOW_IMAGE_AS_BACKGROUND)).doReturn(false)
        whenever(preferenceService.getInt(PreferenceService.BACKGROUND_COLOR)).doReturn(Color.YELLOW)
        preferencesServiceHolder.preferenceService = preferenceService

        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(mock())
        whenever(gameSystem.xpService).doReturn(mock())
        whenever(gameSystem.ruleService).doReturn(mock())
        gameSystemHolder.gameSystem = gameSystem

        val character = Character().apply {
            name = "myCharacter"
            race = Race().apply { name = "myRace" }
            sex = Sex.MALE
        }
        characterHolder.character = character

        scenario = ActivityScenario.launch(CharacterSheetActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("myCharacter"))))
    }

}