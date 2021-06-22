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

class DnD5eCombatEditActivityEspressoTest : KoinTest {

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

        gameSystemHolder.gameSystemType = GameSystemType.DND5E

        characterHolder.character = Character().apply {
            hitPoints = 1
            maxHitPoints = 2
            armorClass = 3
            initiativeModifier = 4
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
        onView(withId(R.id.combat_cmb_row)).check(matches(not(isDisplayed())))
        onView(withId(R.id.combat_cmd_row)).check(matches(not(isDisplayed())))
    }

}