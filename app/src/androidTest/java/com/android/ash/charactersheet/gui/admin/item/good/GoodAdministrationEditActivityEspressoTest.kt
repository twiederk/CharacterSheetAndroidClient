package com.android.ash.charactersheet.gui.admin.item.good

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
import com.d20charactersheet.framework.boc.model.Good
import com.d20charactersheet.framework.boc.model.GoodType
import com.d20charactersheet.framework.boc.model.QualityType
import com.d20charactersheet.framework.boc.service.GameSystem
import com.d20charactersheet.framework.boc.service.ItemService
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

class GoodAdministrationEditActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<GoodAdministrationEditActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val good = Good()
        good.name = ""
        good.goodType = GoodType.ADVENTURING_GEAR
        good.weight = 1f
        good.cost = 0f
        good.qualityType = QualityType.NORMAL
        good.description = ""

        val itemService: ItemService = mock()
        whenever(itemService.getItemById(any(), any())).doReturn(good)

        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(mock())
        whenever(gameSystem.itemService).doReturn(itemService)

        gameSystemHolder.gameSystem = gameSystem

        val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(targetContext, GoodAdministrationEditActivity::class.java).putExtra(Constants.INTENT_EXTRA_DATA_OBJECT, 1)
        scenario = ActivityScenario.launch(intent)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Edit Good"))))
        onView(withId(R.id.item_administration_name)).check(matches(withText(isEmptyString())))
        onView(withId(R.id.item_administration_type)).check(matches(withSpinnerText("ADVENTURING_GEAR")))
        onView(allOf(withParent(withId(R.id.item_administration_cost)), withClassName(endsWith("TextView")))).check(matches(withText("0.0")))
        onView(allOf(withParent(withId(R.id.item_administration_weight)), withClassName(endsWith("TextView")))).check(matches(withText("1.0")))
        onView(withId(R.id.item_administration_quality_type)).check(matches(withSpinnerText("NORMAL")))
        onView(withId(R.id.item_administration_desc)).check(matches(withText(isEmptyString())))
    }

}