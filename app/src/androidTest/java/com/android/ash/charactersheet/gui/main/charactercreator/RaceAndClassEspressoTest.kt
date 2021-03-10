package com.android.ash.charactersheet.gui.main.charactercreator

import android.graphics.Color
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android.ash.charactersheet.*
import com.android.ash.charactersheet.boc.service.PreferenceService
import com.d20charactersheet.framework.boc.model.CharacterClass
import com.d20charactersheet.framework.boc.model.Race
import com.d20charactersheet.framework.boc.model.XpTable
import com.d20charactersheet.framework.boc.service.GameSystem
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.Matchers.isEmptyString
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class RaceAndClassEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()
    private val preferencesServiceHolder by inject<PreferenceServiceHolder>()

    private lateinit var scenario: ActivityScenario<CharacterCreateActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun display() {

        // Arrange
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(mock())
        whenever(gameSystem.allRaces).doReturn(listOf(Race().apply { name = "myRace" }))
        whenever(gameSystem.allCharacterClasses).doReturn(listOf(CharacterClass().apply { name = "myClass" }))
        gameSystemHolder.gameSystem = gameSystem

        scenario = ActivityScenario.launch(CharacterCreateActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Create Character"))))
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarSubtitle(Is.`is`("Race and Class"))))
        onView(withId(R.id.create_name)).check(matches(withText(isEmptyString())))
        onView(withId(R.id.create_player)).check(matches(withText(isEmptyString())))
        onView(withId(R.id.create_race)).check(matches(withSpinnerText("myRace")))
        onView(withId(R.id.create_sex)).check(matches(withSpinnerText("MALE")))
        onView(withId(R.id.create_alignment)).check(matches(withSpinnerText("LAWFUL_GOOD")))
        onView(withId(R.id.create_class)).check(matches(withSpinnerText("myClass")))
        onView(withId(R.id.create_button)).check(matches(withText("Create Character")))
        onView(withId(R.id.race_and_class_navigate_next_button)).check(matches(withText("Ability Scores >")))
    }

    @Test
    fun navigate_ToAbilityScores() {

        // Arrange
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(mock())
        whenever(gameSystem.allRaces).doReturn(listOf(Race().apply { name = "myRace" }))
        whenever(gameSystem.allCharacterClasses).doReturn(listOf(CharacterClass().apply { name = "myClass" }))
        gameSystemHolder.gameSystem = gameSystem

        scenario = ActivityScenario.launch(CharacterCreateActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Act
        onView(withId(R.id.race_and_class_navigate_next_button)).perform(click())

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Create Character"))))
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarSubtitle(Is.`is`("Ability Scores"))))
    }

    @Test
    fun createCharacter() {

        // Arrange
        val preferenceService: PreferenceService = mock()
        whenever(preferenceService.getBoolean(PreferenceService.SHOW_IMAGE_AS_BACKGROUND)).doReturn(false)
        whenever(preferenceService.getInt(PreferenceService.BACKGROUND_COLOR)).doReturn(Color.YELLOW)
        preferencesServiceHolder.preferenceService = preferenceService

        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(mock())
        whenever(gameSystem.xpService).doReturn(mock())
        whenever(gameSystem.ruleService).doReturn(mock())
        whenever(gameSystem.characterService).doReturn(mock())
        whenever(gameSystem.allRaces).doReturn(listOf(Race().apply { name = "myRace" }))
        whenever(gameSystem.allCharacterClasses).doReturn(listOf(CharacterClass().apply { name = "myClass"; classAbilities = listOf() }))
        whenever(gameSystem.allXpTables).doReturn(listOf(XpTable()))
        gameSystemHolder.gameSystem = gameSystem

        scenario = ActivityScenario.launch(CharacterCreateActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)

        onView(withId(R.id.create_name)).perform(typeText("myName"))
        onView(withId(R.id.create_player)).perform(typeText("myPlayer"))

        // Act
        onView(withId(R.id.create_button)).perform(click())

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("myName"))))
    }

}