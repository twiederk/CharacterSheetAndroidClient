package com.android.ash.charactersheet.gui.main.exportimport

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.withToolbarTitle
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExportMenuActivityEspressoTest {

    private lateinit var scenario: ActivityScenario<ExportMenuActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {
        // Arrange
        scenario = ActivityScenario.launch(ExportMenuActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Export"))))
        onView(withId(R.id.export_menu_character_button)).check(matches(withText(R.string.export_menu_character_button)))
        onView(withId(R.id.export_menu_equipment_button)).check(matches(withText(R.string.export_menu_equipment_button)))
    }

}