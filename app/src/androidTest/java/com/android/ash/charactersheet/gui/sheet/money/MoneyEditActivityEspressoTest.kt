package com.android.ash.charactersheet.gui.sheet.money

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.withToolbarTitle
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.model.Money
import com.d20charactersheet.framework.boc.service.GameSystem
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.endsWith
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.kotlin.mock

class MoneyEditActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()
    private val characterHolder by inject<CharacterHolder>()

    private lateinit var scenario: ActivityScenario<MoneyEditActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {
        // Arrange
        val gameSystem: GameSystem = mock()
        gameSystemHolder.gameSystem = gameSystem

        characterHolder.character = Character().apply {
            money = Money().apply {
                platinum = 1
                gold = 2
                silver = 3
                copper = 4
            }
        }

        scenario = ActivityScenario.launch(MoneyEditActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Money"))))
        onView(allOf(withParent(withId(R.id.money_platinum)), withClassName(endsWith("TextView")))).check(matches(withText("1")))
        onView(allOf(withParent(withId(R.id.money_platinum)), withClassName(endsWith("EditText")), isDisplayed())).check(matches(withText("1")))
        onView(allOf(withParent(withId(R.id.money_gold)), withClassName(endsWith("TextView")))).check(matches(withText("2")))
        onView(allOf(withParent(withId(R.id.money_gold)), withClassName(endsWith("EditText")), isDisplayed())).check(matches(withText("1")))
        onView(allOf(withParent(withId(R.id.money_silver)), withClassName(endsWith("TextView")))).check(matches(withText("3")))
        onView(allOf(withParent(withId(R.id.money_silver)), withClassName(endsWith("EditText")), isDisplayed())).check(matches(withText("1")))
        onView(allOf(withParent(withId(R.id.money_copper)), withClassName(endsWith("TextView")))).check(matches(withText("4")))
        onView(allOf(withParent(withId(R.id.money_copper)), withClassName(endsWith("EditText")), isDisplayed())).check(matches(withText("1")))
    }

}