package com.android.ash.charactersheet.gui.admin.race

import android.content.Context
import android.content.Intent
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.android.ash.charactersheet.Constants
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.withToolbarTitle
import com.d20charactersheet.framework.boc.model.Ability
import com.d20charactersheet.framework.boc.model.CharacterClass
import com.d20charactersheet.framework.boc.model.Race
import com.d20charactersheet.framework.boc.model.Size
import com.d20charactersheet.framework.boc.service.GameSystem
import com.d20charactersheet.framework.boc.service.RaceService

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class RaceAdministrationEditActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<RaceAdministrationEditActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val allRaces = listOf<Race>()
        val race = Race()
        race.id = 1
        race.name = "High Elf"
        race.size = Size.MEDIUM
        race.baseLandSpeed = 30
        race.favoredCharacterClass = CharacterClass.ANY_CHARACTER_CLASS
        val ability = Ability()
        ability.name = "Resist Sleep"
        race.abilities = listOf(ability)

        val raceService: RaceService = mock()
        whenever(raceService.findRaceById(race.id, allRaces)).doReturn(race)

        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(mock())
        whenever(gameSystem.raceService).doReturn(raceService)
        whenever(gameSystem.allRaces).doReturn(allRaces)

        gameSystemHolder.gameSystem = gameSystem

        val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(targetContext, RaceAdministrationEditActivity::class.java).putExtra(Constants.INTENT_EXTRA_DATA_OBJECT, 1)
        scenario = ActivityScenario.launch(intent)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Edit Race"))))
        onView(withId(R.id.race_administration_name)).check(matches(withText("High Elf")))
        onView(withId(R.id.race_administration_size)).check(matches(withSpinnerText("MEDIUM")))
        onView(withId(R.id.race_administration_speed)).check(matches(withText("30")))
        onView(withId(R.id.race_administration_class)).check(matches(withSpinnerText("Any")))
        onView(withId(R.id.race_administration_abilities)).check(matches(withText("Abilities:\nResist Sleep")))
    }

}