package com.android.ash.charactersheet.gui.main

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.withToolbarTitle
import com.d20charactersheet.framework.boc.model.CharacterClass
import com.d20charactersheet.framework.boc.model.Race
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

class CharacterCreateActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<CharacterCreateActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

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
        onView(withId(R.id.create_name)).check(matches(withText(isEmptyString())))
        onView(withId(R.id.create_player)).check(matches(withText(isEmptyString())))
        onView(withId(R.id.create_race)).check(matches(withSpinnerText("myRace")))
        onView(withId(R.id.create_sex)).check(matches(withSpinnerText("MALE")))
        onView(withId(R.id.create_alignment)).check(matches(withSpinnerText("LAWFUL_GOOD")))
        onView(withId(R.id.create_class)).check(matches(withSpinnerText("myClass")))
        onView(withId(R.id.create_button)).check(matches(withText("Create")))
    }

}