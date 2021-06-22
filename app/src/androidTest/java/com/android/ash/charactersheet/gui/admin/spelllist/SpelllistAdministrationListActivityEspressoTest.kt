package com.android.ash.charactersheet.gui.admin.spelllist

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
import com.d20charactersheet.framework.boc.model.Spelllist
import com.d20charactersheet.framework.boc.service.GameSystem
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class SpelllistAdministrationListActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<SpelllistAdministrationListActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.allSpelllists).doReturn(listOf(Spelllist().apply { id = 1; name = "mySpelllist" }))
        gameSystemHolder.gameSystem = gameSystem

        scenario = ActivityScenario.launch(SpelllistAdministrationListActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Spell List Administration"))))
        onView(withId(R.id.name)).check(matches(withText("mySpelllist"))).check(matches(isDisplayed()))
    }

    @Test
    fun fab_onClick_displaySpelllistAdministrationCreateActivity() {

        // Arrange
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(mock())
        whenever(gameSystem.spelllistService).doReturn(mock())
        whenever(gameSystem.allSpelllists).doReturn(listOf(Spelllist().apply { id = 1; name = "mySpelllist" }))
        gameSystemHolder.gameSystem = gameSystem

        scenario = ActivityScenario.launch(SpelllistAdministrationListActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Act
        onView(withId(R.id.favorite_action_button)).perform(click())

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Create Spell List"))))
    }

}