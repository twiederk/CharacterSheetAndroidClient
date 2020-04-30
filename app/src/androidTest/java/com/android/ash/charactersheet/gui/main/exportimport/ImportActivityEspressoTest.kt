package com.android.ash.charactersheet.gui.main.exportimport

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.withToolbarTitle
import org.hamcrest.Matchers
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest

class ImportActivityEspressoTest : KoinTest {

    private lateinit var scenario: ActivityScenario<ImportActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        scenario = ActivityScenario.launch(ImportActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Import Characters"))))
        onView(withId(R.id.import_import_directory)).check(matches(withText(Matchers.startsWith("Import Directory:"))))
    }

}