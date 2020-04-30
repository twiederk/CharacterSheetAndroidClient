package com.android.ash.charactersheet.gui.admin.spell

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.withToolbarTitle
import com.d20charactersheet.framework.boc.model.Spell
import com.d20charactersheet.framework.boc.service.GameSystem
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class SpellAdministrationListActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<SpellAdministrationListActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.allSpells).doReturn(listOf(Spell().apply { id = 1; name = "mySpell" }))
        gameSystemHolder.gameSystem = gameSystem

        scenario = ActivityScenario.launch(SpellAdministrationListActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Spell Administration"))))
        onView(withId(R.id.name)).check(matches(withText("mySpell"))).check(matches(isDisplayed()))
    }

    @Test
    fun fab_onClick_displaySpellAdministrationCreateActivity() {

        // Arrange
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(mock())
        whenever(gameSystem.allSpells).doReturn(listOf(Spell().apply { id = 1; name = "mySpell" }))
        gameSystemHolder.gameSystem = gameSystem

        scenario = ActivityScenario.launch(SpellAdministrationListActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Act
        onView(withId(R.id.favorite_action_button)).perform(click())

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Create Spell"))))
    }

}