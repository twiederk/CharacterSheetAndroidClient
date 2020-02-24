package com.android.ash.charactersheet.gui.admin.item.armor

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
import com.d20charactersheet.framework.boc.service.SimpleDisplayService
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.Matchers.*
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class ArmorAdministrationCreateActivityInstrumentationTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<ArmorAdministrationCreateActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(SimpleDisplayService())
        gameSystemHolder.gameSystem = gameSystem

        scenario = ActivityScenario.launch(ArmorAdministrationCreateActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Create Armor"))))
        onView(withId(R.id.item_administration_name)).check(matches(withText(isEmptyString())))
        onView(withId(R.id.item_administration_type)).check(matches(withSpinnerText("LIGHT")))
        onView(allOf(withParent(withId(R.id.item_administration_cost)), withClassName(endsWith("TextView")))).check(matches(withText("0.0")))
        onView(allOf(withParent(withId(R.id.item_administration_weight)), withClassName(endsWith("TextView")))).check(matches(withText("1.0")))
        onView(withId(R.id.item_administration_quality_type)).check(matches(withSpinnerText("NORMAL")))
        onView(allOf(withParent(withId(R.id.armor_administration_bonus)), withClassName(endsWith("TextView")))).check(matches(withText("1")))
        onView(allOf(withParent(withId(R.id.armor_administration_penalty)), withClassName(endsWith("TextView")))).check(matches(withText("0")))
        onView(withId(R.id.item_administration_desc)).check(matches(withText(isEmptyString())))
    }

}