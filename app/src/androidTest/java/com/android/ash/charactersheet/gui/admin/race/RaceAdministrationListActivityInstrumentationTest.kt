package com.android.ash.charactersheet.gui.admin.race

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.withToolbarTitle
import com.d20charactersheet.framework.boc.model.Race
import com.d20charactersheet.framework.boc.service.GameSystem
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.inject


@RunWith(AndroidJUnit4::class)
class RaceAdministrationListActivityInstrumentationTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<RaceAdministrationListActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val race = Race()
        race.id = 1
        race.name = "MyRace"
        val allRaces = listOf(race)
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.allRaces).doReturn(allRaces)
        gameSystemHolder.gameSystem = gameSystem

        scenario = ActivityScenario.launch(RaceAdministrationListActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java))
                .check(matches(withToolbarTitle(Is.`is`("Race Administration"))))

        onView(withId(R.id.name))
                .check(matches(withText("MyRace")))
                .check(matches(isDisplayed()))

    }

}