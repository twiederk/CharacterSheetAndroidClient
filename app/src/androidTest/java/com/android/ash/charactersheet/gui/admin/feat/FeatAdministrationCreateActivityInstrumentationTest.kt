package com.android.ash.charactersheet.gui.admin.feat

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

class FeatAdministrationCreateActivityInstrumentationTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<FeatAdministrationCreateActivity>

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

        scenario = ActivityScenario.launch(FeatAdministrationCreateActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Create Feat"))))
        onView(withId(R.id.feat_administration_name)).check(matches(withText(isEmptyString())))
        onView(withId(R.id.feat_administration_feattype)).check(matches(withSpinnerText("GENERAL")))
        onView(withId(R.id.feat_administration_benefit)).check(matches(withText(isEmptyString())))
        onView(withId(R.id.feat_administration_prerequisite)).check(matches(withText(isEmptyString())))
        onView(allOf(withParent(withId(R.id.feat_administration_spellslot)), withClassName(endsWith("TextView")))).check(matches(withText("0")))
        onView(withId(R.id.feat_administration_fighter_bonus)).check(matches(isNotChecked()))
        onView(withId(R.id.feat_administration_multiple)).check(matches(isNotChecked()))
        onView(withId(R.id.feat_administration_stack)).check(matches(isNotChecked()))
    }

}