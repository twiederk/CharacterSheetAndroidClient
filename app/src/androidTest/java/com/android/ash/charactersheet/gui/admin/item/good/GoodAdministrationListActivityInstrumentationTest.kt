package com.android.ash.charactersheet.gui.admin.item.good

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.withToolbarTitle
import com.d20charactersheet.framework.boc.model.Good
import com.d20charactersheet.framework.boc.service.DisplayService
import com.d20charactersheet.framework.boc.service.GameSystem
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class GoodAdministrationListActivityInstrumentationTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<GoodAdministrationListActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val displayService: DisplayService = mock()
        whenever(displayService.getDisplayItem(any())).doReturn("MyGood")
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.allGoods).doReturn(listOf(Good()))
        whenever(gameSystem.displayService).doReturn(displayService)
        gameSystemHolder.gameSystem = gameSystem

        scenario = ActivityScenario.launch(GoodAdministrationListActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java))
                .check(matches(withToolbarTitle(Is.`is`("Good Administration"))))

        onView(withId(R.id.name))
                .check(matches(withText("MyGood")))
                .check(matches(isDisplayed()))
    }

}