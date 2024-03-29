package com.android.ash.charactersheet.gui.main

import android.graphics.Color
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.PreferenceServiceHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.boc.service.PreferenceService
import com.android.ash.charactersheet.withToolbarTitle
import org.hamcrest.Matchers.allOf
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class PreferencesActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()
    private val preferenceServiceHolder by inject<PreferenceServiceHolder>()

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

        preferenceServiceHolder.preferenceService = preferenceService

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