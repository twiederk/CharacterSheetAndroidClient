package com.android.ash.charactersheet.gui.sheet.feat

import android.content.Context
import android.content.Intent
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.android.ash.charactersheet.Constants
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.withToolbarTitle
import com.d20charactersheet.framework.boc.model.CharacterFeat
import com.d20charactersheet.framework.boc.model.Feat
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest

class FeatEditActivityInstrumentationTest : KoinTest {

    private lateinit var scenario: ActivityScenario<FeatEditActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val characterFeat = CharacterFeat(Feat().apply { name = "myFeat" }).apply { category = "myCategory" }
        val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(targetContext, FeatEditActivity::class.java).putExtra(Constants.INTENT_EXTRA_DATA_OBJECT, characterFeat)
        scenario = ActivityScenario.launch(intent)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("myFeat"))))
        onView(withId(R.id.feat_edit_category)).check(matches(withText("myCategory")))
    }

}