package com.android.ash.charactersheet.gui.admin.feat

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
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.withToolbarTitle
import com.d20charactersheet.framework.boc.model.Feat
import com.d20charactersheet.framework.boc.model.FeatType
import com.d20charactersheet.framework.boc.service.FeatService
import com.d20charactersheet.framework.boc.service.GameSystem
import org.hamcrest.Matchers.*
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class FeatAdministrationEditActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<FeatAdministrationEditActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val feat = Feat()
        feat.name = "myFeat"
        feat.featType = FeatType.GENERAL
        feat.benefit = ""
        feat.prerequisit = ""
        feat.isFighterBonus = false
        feat.isMultiple = false
        feat.isStack = false

        val featService: FeatService = mock()
        whenever(featService.findFeatById(any(), any())).doReturn(feat)

        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(mock())
        whenever(gameSystem.featService).doReturn(featService)

        gameSystemHolder.gameSystem = gameSystem

        val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(targetContext, FeatAdministrationEditActivity::class.java).putExtra(Constants.INTENT_EXTRA_DATA_OBJECT, 1)
        scenario = ActivityScenario.launch(intent)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Edit Feat"))))
        onView(withId(R.id.feat_administration_name)).check(matches(withText("myFeat")))
        onView(withId(R.id.feat_administration_feattype)).check(matches(withSpinnerText("GENERAL")))
        onView(withId(R.id.feat_administration_benefit)).check(matches(withText(isEmptyString())))
        onView(withId(R.id.feat_administration_prerequisite)).check(matches(withText(isEmptyString())))
        onView(allOf(withParent(withId(R.id.feat_administration_spellslot)), withClassName(endsWith("TextView")))).check(matches(withText("0")))
        onView(withId(R.id.feat_administration_fighter_bonus)).check(matches(isNotChecked()))
        onView(withId(R.id.feat_administration_multiple)).check(matches(isNotChecked()))
        onView(withId(R.id.feat_administration_stack)).check(matches(isNotChecked()))
    }

}