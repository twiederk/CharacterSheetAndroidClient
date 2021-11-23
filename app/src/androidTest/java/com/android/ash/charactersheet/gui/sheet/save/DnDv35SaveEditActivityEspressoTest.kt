package com.android.ash.charactersheet.gui.sheet.save

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.android.ash.charactersheet.withToolbarTitle
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.model.Save
import com.d20charactersheet.framework.boc.service.DisplayService
import com.d20charactersheet.framework.boc.service.GameSystem
import com.d20charactersheet.framework.boc.service.RuleService
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.endsWith
import org.hamcrest.Matchers.not
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class DnDv35SaveEditActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()
    private val characterHolder by inject<CharacterHolder>()

    private lateinit var scenario: ActivityScenario<SaveEditActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {
        // Arrange
        val character = Character()

        val ruleService: RuleService = mock()
        whenever(ruleService.getProficiencySave(character, Save.STRENGTH)).doReturn(1)
        whenever(ruleService.getProficiencySave(character, Save.DEXTERITY)).doReturn(2)
        whenever(ruleService.getProficiencySave(character, Save.CONSTITUTION)).doReturn(3)
        whenever(ruleService.getSaveAttributeModifier(character, Save.STRENGTH)).doReturn(1)
        whenever(ruleService.getSaveAttributeModifier(character, Save.DEXTERITY)).doReturn(2)
        whenever(ruleService.getSaveAttributeModifier(character, Save.CONSTITUTION)).doReturn(3)
        whenever(ruleService.getSaveModifier(character, Save.STRENGTH)).doReturn(1)
        whenever(ruleService.getSaveModifier(character, Save.DEXTERITY)).doReturn(2)
        whenever(ruleService.getSaveModifier(character, Save.CONSTITUTION)).doReturn(3)

        val displayService: DisplayService = mock()
        whenever(displayService.getDisplayModifier(1)).doReturn("+1")
        whenever(displayService.getDisplayModifier(2)).doReturn("+2")
        whenever(displayService.getDisplayModifier(3)).doReturn("+3")

        val gameSystem: GameSystem = mock()
        gameSystemHolder.gameSystem = gameSystem
        whenever(gameSystem.displayService).doReturn(displayService)
        whenever(gameSystem.ruleService).doReturn(ruleService)

        gameSystemHolder.gameSystemType = GameSystemType.DNDV35

        characterHolder.character = character

        scenario = ActivityScenario.launch(SaveEditActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Saves"))))
        onView(withId(R.id.save_heading_proficiency)).check(matches(withText("Base")))

        // fortitude
        onView(withId(R.id.save_strength_label)).check(matches(withText("Fortitude:")))
        onView(withId(R.id.save_strength_base)).check(matches(withText("+1")))
        onView(withId(R.id.save_strength_attribute)).check(matches(withText("+1")))
        onView(
            allOf(
                withParent(withId(R.id.save_strength_modifier)),
                withClassName(endsWith("TextView"))
            )
        ).check(matches(withText("1")))

        // reflex
        onView(withId(R.id.save_dexterity_label)).check(matches(withText("Reflex:")))
        onView(withId(R.id.save_dexterity_base)).check(matches(withText("+2")))
        onView(withId(R.id.save_dexterity_attribute)).check(matches(withText("+2")))
        onView(
            allOf(
                withParent(withId(R.id.save_dexterity_modifier)),
                withClassName(endsWith("TextView"))
            )
        ).check(matches(withText("2")))

        // will
        onView(withId(R.id.save_constitution_label)).check(matches(withText("Will:")))
        onView(withId(R.id.save_constitution_base)).check(matches(withText("+3")))
        onView(withId(R.id.save_constitution_attribute)).check(matches(withText("+3")))
        onView(
            allOf(
                withParent(withId(R.id.save_constitution_modifier)),
                withClassName(endsWith("TextView"))
            )
        ).check(matches(withText("3")))

        onView(withId(R.id.save_intelligence_row)).check(matches(not(isDisplayed())))
        onView(withId(R.id.save_wisdom_row)).check(matches(not(isDisplayed())))
        onView(withId(R.id.save_charisma_row)).check(matches(not(isDisplayed())))
    }

}