package com.android.ash.charactersheet.gui.admin.race

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.withToolbarTitle
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

class RaceAdministrationCreateActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<RaceAdministrationCreateActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(mock())
        gameSystemHolder.gameSystem = gameSystem

        scenario = ActivityScenario.launch(RaceAdministrationCreateActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Create Race"))))
        onView(withId(R.id.race_administration_name)).check(matches(withText(isEmptyString())))
        onView(withId(R.id.race_administration_size)).check(matches(withSpinnerText("MEDIUM")))
        onView(withId(R.id.race_administration_speed)).check(matches(withText("30")))
        onView(withId(R.id.race_administration_class)).check(matches(withSpinnerText("Any")))
        onView(withId(R.id.race_administration_abilities)).check(matches(withText("Abilities:\n")))


    }

}