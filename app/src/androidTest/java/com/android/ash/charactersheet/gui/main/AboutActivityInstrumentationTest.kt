package com.android.ash.charactersheet.gui.main

import android.widget.Button
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.android.ash.charactersheet.BuildConfig
import com.android.ash.charactersheet.R
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AboutActivityInstrumentationTest {

    @get:Rule
    val activityRule = ActivityTestRule(AboutActivity::class.java)

    @Test
    fun onCreate() {
        onView(withId(R.id.app_version)).check(matches(withText(BuildConfig.VERSION_NAME)))
        onView(withId(R.id.about_manual)).check(matches(withText("Manual: http://www.d20CharacterSheet.com/manual.html")))
        val oglButton = activityRule.activity.findViewById<Button>(R.id.about_button_ogl)
        assertThat(oglButton.hasOnClickListeners()).isTrue()
    }
}
