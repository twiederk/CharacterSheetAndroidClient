package com.android.ash.charactersheet.gui.sheet.combat

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.android.ash.charactersheet.withToolbarTitle
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.service.DisplayService
import com.d20charactersheet.framework.boc.service.GameSystem
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.endsWith
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class DnDv35CombatEditActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()
    private val characterHolder by inject<CharacterHolder>()

    private lateinit var scenario: ActivityScenario<CombatEditActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {
        // Arrange
        val displayService: DisplayService = mock()
        whenever(displayService.getDisplayArmourClassFormular(any())).doReturn("10+(+4)+")
        whenever(displayService.getDisplaySimpleFormular(any())).doReturn("myFormular")

        val gameSystem: GameSystem = mock()
        gameSystemHolder.gameSystem = gameSystem
        whenever(gameSystem.displayService).doReturn(displayService)
        whenever(gameSystem.ruleService).doReturn(mock())

        gameSystemHolder.gameSystemType = GameSystemType.DNDV35

        characterHolder.character = Character().apply {
            hitPoints = 1
            maxHitPoints = 2
            armorClass = 3
            initiativeModifier = 4
            cmbModifier = 5
            cmdModifier = 6
        }

        scenario = ActivityScenario.launch(CombatEditActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Combat"))))
        onView(allOf(withParent(withId(R.id.combat_hitpoints)), withClassName(endsWith("TextView")))).check(matches(withText("1")))
        onView(allOf(withParent(withId(R.id.combat_hitpoints)), withClassName(endsWith("EditText")), isDisplayed())).check(matches(withText("1")))
        onView(allOf(withParent(withId(R.id.combat_max_hitpoints)), withClassName(endsWith("TextView")))).check(matches(withText("2")))
        onView(allOf(withParent(withId(R.id.combat_max_hitpoints)), withClassName(endsWith("EditText")), isDisplayed())).check(matches(withText("1")))
        onView(withId(R.id.combat_armorclass_formular)).check(matches(withText("10+(+4)+")))
        onView(allOf(withParent(withId(R.id.combat_armorclass)), withClassName(endsWith("TextView")))).check(matches(withText("3")))
        onView(withId(R.id.combat_initiative_formular)).check(matches(withText("myFormular")))
        onView(allOf(withParent(withId(R.id.combat_initiative)), withClassName(endsWith("TextView")))).check(matches(withText("4")))
        onView(withId(R.id.combat_cmb_formular)).check(matches(withText("myFormular")))
        onView(allOf(withParent(withId(R.id.combat_cmb)), withClassName(endsWith("TextView")))).check(matches(withText("5")))
        onView(withId(R.id.combat_cmd_formular)).check(matches(withText("myFormular")))
        onView(allOf(withParent(withId(R.id.combat_cmd)), withClassName(endsWith("TextView")))).check(matches(withText("6")))
    }

}