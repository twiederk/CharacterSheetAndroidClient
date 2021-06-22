package com.android.ash.charactersheet.gui.admin.clazz

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.withToolbarTitle
import com.d20charactersheet.framework.boc.model.CharacterClass
import com.d20charactersheet.framework.boc.service.DisplayService
import com.d20charactersheet.framework.boc.service.GameSystem
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class CharacterClassAdministrationListActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<CharacterClassAdministrationListActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.allCharacterClasses).doReturn(listOf(CharacterClass().apply { id = 1; name = "myClass" }))
        gameSystemHolder.gameSystem = gameSystem

        scenario = ActivityScenario.launch(CharacterClassAdministrationListActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Character Class Administration"))))
        onView(withId(R.id.name)).check(matches(withText("myClass"))).check(matches(isDisplayed()))
    }

    @Test
    fun fab_onClick_displayCharacterClassAdministrationCreateActivity() {

        // Arrange
        val displayService: DisplayService = mock()
        whenever(displayService.getDisplayAlignment(any())).doReturn("MyAlignment")

        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(displayService)
        whenever(gameSystem.allCharacterClasses).doReturn(listOf(CharacterClass().apply { id = 1; name = "myClass" }))
        gameSystemHolder.gameSystem = gameSystem

        scenario = ActivityScenario.launch(CharacterClassAdministrationListActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Act
        onView(withId(R.id.favorite_action_button)).perform(ViewActions.click())

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Create Character Class"))))
    }

}