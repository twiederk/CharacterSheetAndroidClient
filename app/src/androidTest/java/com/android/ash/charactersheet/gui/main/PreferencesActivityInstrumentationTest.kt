package com.android.ash.charactersheet.gui.main

import android.content.Context
import android.graphics.Color
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.android.ash.charactersheet.CharacterSheetApplication
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.boc.service.PreferenceService
import com.android.ash.charactersheet.withToolbarTitle
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.Matchers.allOf
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class PreferencesActivityInstrumentationTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<PreferencesActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val preferenceService: PreferenceService = mock()
        whenever(preferenceService.getBoolean(PreferenceService.SHOW_IMAGE_AS_BACKGROUND)).doReturn(true)
        whenever(preferenceService.getInt(PreferenceService.GAME_SYSTEM_TYPE)).doReturn(0)
        whenever(preferenceService.getInt(PreferenceService.BACKGROUND_COLOR)).doReturn(Color.BLACK)

        val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val application = targetContext.applicationContext as CharacterSheetApplication
        application.preferenceService = preferenceService

        gameSystemHolder.gameSystem = mock()

        scenario = ActivityScenario.launch(PreferencesActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Preferences"))))
        onView(withId(R.id.preferences_background_image)).check(matches(isChecked()))
        onView(withId(R.id.preferences_background_color)).check(matches(isNotChecked()))
        onView(allOf(withParent(withId(R.id.preferences_background_colors)), withId(android.R.id.text1))).check(matches(withText("Black"))).check(matches(isDisplayed()))
        onView(allOf(withParent(withId(R.id.preferences_game_systems)), withId(android.R.id.text1))).check(matches(withText("DnD v.3.5"))).check(matches(isDisplayed()))
    }

}