package com.android.ash.charactersheet.gui.admin.item.weapon

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.withToolbarTitle
import com.d20charactersheet.framework.boc.model.Weapon
import com.d20charactersheet.framework.boc.model.WeaponFamily
import com.d20charactersheet.framework.boc.service.DisplayService
import com.d20charactersheet.framework.boc.service.GameSystem
import com.d20charactersheet.framework.boc.service.ItemService
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class WeaponAdministrationListActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<WeaponAdministrationListActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val displayService: DisplayService = mock()
        whenever(displayService.getDisplayItem(any())).doReturn("myWeapon")
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.allWeapons).doReturn(listOf(Weapon()))
        whenever(gameSystem.displayService).doReturn(displayService)
        gameSystemHolder.gameSystem = gameSystem

        scenario = ActivityScenario.launch(WeaponAdministrationListActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Weapon Administration"))))
        onView(withId(R.id.name)).check(matches(withText("myWeapon"))).check(matches(isDisplayed()))
    }

    @Test
    fun fab_onClick_displayWeaponAdministrationCreateActivity() {

        // Arrange
        val itemService: ItemService = mock()
        whenever(itemService.allWeaponFamilies).doReturn(listOf(WeaponFamily()))

        val displayService: DisplayService = mock()
        whenever(displayService.getDisplayItem(any())).doReturn("myWeapon")

        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(displayService)
        whenever(gameSystem.itemService).doReturn(itemService)
        whenever(gameSystem.allWeapons).doReturn(listOf(Weapon()))
        gameSystemHolder.gameSystem = gameSystem

        scenario = ActivityScenario.launch(WeaponAdministrationListActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Act
        onView(withId(R.id.favorite_action_button)).perform(click())

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Create Weapon"))))
    }

}