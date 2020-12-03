package com.android.ash.charactersheet.gui.main

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.ash.charactersheet.BuildConfig
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.withToolbarTitle
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AboutActivityEspressoTest {

    private lateinit var scenario: ActivityScenario<AboutActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {
        // Arrange
        scenario = ActivityScenario.launch(AboutActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Manual"))))
        onView(withId(R.id.app_version)).check(matches(withText(BuildConfig.VERSION_NAME)))
        onView(withId(R.id.about_manual)).check(matches(withText("Manual: http://www.d20CharacterSheet.com/manual.html")))
        onView(withId(R.id.about_thanks)).check(matches(withText(R.string.about_thanks)))
        onView(withId(R.id.about_button_ogl)).check(matches(withText(R.string.about_ogl)))
    }

    @Test
    fun setOnClickListener_clickOglButton_startOglActivity() {
        // Arrange
        scenario = ActivityScenario.launch(AboutActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Act
        onView(withId(R.id.about_button_ogl)).perform(click())

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Open Gaming Licence"))))
    }

}
