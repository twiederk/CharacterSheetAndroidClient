package com.android.ash.charactersheet.gui.admin.spelllist

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
import org.hamcrest.Matchers.*
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class SpelllistAdministrationCreateActivityInstrumentationTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<SpelllistAdministrationCreateActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(mock())
        whenever(gameSystem.spelllistService).doReturn(mock())
        gameSystemHolder.gameSystem = gameSystem

        scenario = ActivityScenario.launch(SpelllistAdministrationCreateActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Create Spell List"))))
        onView(withId(R.id.spelllist_administration_name)).check(matches(withText(isEmptyString())))
        onView(withId(R.id.spelllist_administration_short_name)).check(matches(withText(isEmptyString())))
        onView(withId(R.id.spelllist_administration_domain)).check(matches(isNotChecked()))
        onView(allOf(withParent(withId(R.id.spelllist_administration_min_level)), withClassName(endsWith("TextView")))).check(matches(withText("0")))
        onView(allOf(withParent(withId(R.id.spelllist_administration_max_level)), withClassName(endsWith("TextView")))).check(matches(withText("0")))
    }

}