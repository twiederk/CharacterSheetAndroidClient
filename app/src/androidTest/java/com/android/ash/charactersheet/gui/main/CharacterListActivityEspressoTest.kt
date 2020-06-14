package com.android.ash.charactersheet.gui.main

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.withToolbarTitle
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class CharacterListActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<CharacterListActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        gameSystemHolder.gameSystem = null
        scenario = ActivityScenario.launch(CharacterListActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Character List - DnD v.3.5"))))
        onView(withId(R.id.favorite_action_button)).check(matches(isDisplayed()))
        onView(withId(R.id.character_name)).check(matches(withText("Belvador the Summoner")))
        onView(withId(R.id.character_stats)).check(matches(withText("High Elf, Wizard (5)")))
    }

    @Test
    fun fab_onClick_callCharacterCreateActivity() {

        // Arrange
        gameSystemHolder.gameSystem = null
        scenario = ActivityScenario.launch(CharacterListActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Act
        onView(withId(R.id.favorite_action_button)).perform(click())

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Create Character"))))
    }

    @Test
    fun actionBarOverflowMenu_onClickPurchasePremiumVersion_displayPurchaseDialog() {

        // Arrange
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        gameSystemHolder.gameSystem = null
        scenario = ActivityScenario.launch(CharacterListActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)
        openActionBarOverflowOrOptionsMenu(context)

        // Act
        onView(withText(R.string.character_list_menu_purchase)).perform(click())

        // Assert
        onView(withText(R.string.purchase_dialog_premium_version)).check(matches(isDisplayed()))
        onView(withText(R.string.purchase_dialog_purchase_button)).check(matches(isDisplayed()))
        onView(withText(R.string.purchase_dialog_restore_purchase_button)).check(matches(isDisplayed()))
        onView(withText(R.string.purchase_dialog_cancel_button)).check(matches(isDisplayed()))
    }

}