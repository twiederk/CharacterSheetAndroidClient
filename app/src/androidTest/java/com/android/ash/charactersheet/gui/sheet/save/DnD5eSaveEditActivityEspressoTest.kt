package com.android.ash.charactersheet.gui.sheet.save

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
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
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class DnD5eSaveEditActivityEspressoTest : KoinTest {

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
        whenever(ruleService.getProficiencySave(character, Save.INTELLIGENCE)).doReturn(4)
        whenever(ruleService.getProficiencySave(character, Save.WISDOM)).doReturn(5)
        whenever(ruleService.getProficiencySave(character, Save.CHARISMA)).doReturn(6)
        whenever(ruleService.getSaveAttributeModifier(character, Save.STRENGTH)).doReturn(1)
        whenever(ruleService.getSaveAttributeModifier(character, Save.DEXTERITY)).doReturn(2)
        whenever(ruleService.getSaveAttributeModifier(character, Save.CONSTITUTION)).doReturn(3)
        whenever(ruleService.getSaveAttributeModifier(character, Save.INTELLIGENCE)).doReturn(4)
        whenever(ruleService.getSaveAttributeModifier(character, Save.WISDOM)).doReturn(5)
        whenever(ruleService.getSaveAttributeModifier(character, Save.CHARISMA)).doReturn(6)
        whenever(ruleService.getSaveModifier(character, Save.STRENGTH)).doReturn(1)
        whenever(ruleService.getSaveModifier(character, Save.DEXTERITY)).doReturn(2)
        whenever(ruleService.getSaveModifier(character, Save.CONSTITUTION)).doReturn(3)
        whenever(ruleService.getSaveModifier(character, Save.INTELLIGENCE)).doReturn(4)
        whenever(ruleService.getSaveModifier(character, Save.WISDOM)).doReturn(5)
        whenever(ruleService.getSaveModifier(character, Save.CHARISMA)).doReturn(6)

        val displayService: DisplayService = mock()
        whenever(displayService.getDisplayModifier(1)).doReturn("+1")
        whenever(displayService.getDisplayModifier(2)).doReturn("+2")
        whenever(displayService.getDisplayModifier(3)).doReturn("+3")
        whenever(displayService.getDisplayModifier(4)).doReturn("+4")
        whenever(displayService.getDisplayModifier(5)).doReturn("+5")
        whenever(displayService.getDisplayModifier(6)).doReturn("+6")

        val gameSystem: GameSystem = mock()
        gameSystemHolder.gameSystem = gameSystem
        whenever(gameSystem.displayService).doReturn(displayService)
        whenever(gameSystem.ruleService).doReturn(ruleService)

        gameSystemHolder.gameSystemType = GameSystemType.DND5E

        characterHolder.character = character

        scenario = ActivityScenario.launch(SaveEditActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Saves"))))
        onView(withId(R.id.save_heading_proficiency)).check(matches(withText("Prof.")))

        // strength
        onView(withId(R.id.save_strength_label)).check(matches(withText("Strength:")))
        onView(withId(R.id.save_strength_base)).check(matches(withText("+1")))
        onView(withId(R.id.save_strength_attribute)).check(matches(withText("+1")))
        onView(
            allOf(
                withParent(withId(R.id.save_strength_modifier)),
                withClassName(endsWith("TextView"))
            )
        ).check(matches(withText("1")))

        // dexterity
        onView(withId(R.id.save_dexterity_label)).check(matches(withText("Dexterity:")))
        onView(withId(R.id.save_dexterity_base)).check(matches(withText("+2")))
        onView(withId(R.id.save_dexterity_attribute)).check(matches(withText("+2")))
        onView(
            allOf(
                withParent(withId(R.id.save_dexterity_modifier)),
                withClassName(endsWith("TextView"))
            )
        ).check(matches(withText("2")))

        // constitution
        onView(withId(R.id.save_constitution_label)).check(matches(withText("Constitution:")))
        onView(withId(R.id.save_constitution_base)).check(matches(withText("+3")))
        onView(withId(R.id.save_constitution_attribute)).check(matches(withText("+3")))
        onView(
            allOf(
                withParent(withId(R.id.save_constitution_modifier)),
                withClassName(endsWith("TextView"))
            )
        ).check(matches(withText("3")))

        // intelligence
        onView(withId(R.id.save_intelligence_label)).check(matches(withText("Intelligence:")))
        onView(withId(R.id.save_intelligence_base)).check(matches(withText("+4")))
        onView(withId(R.id.save_intelligence_attribute)).check(matches(withText("+4")))
        onView(
            allOf(
                withParent(withId(R.id.save_intelligence_modifier)),
                withClassName(endsWith("TextView"))
            )
        ).check(matches(withText("4")))

        // wisdom
        onView(withId(R.id.save_wisdom_label)).check(matches(withText("Wisdom:")))
        onView(withId(R.id.save_wisdom_base)).check(matches(withText("+5")))
        onView(withId(R.id.save_wisdom_attribute)).check(matches(withText("+5")))
        onView(
            allOf(
                withParent(withId(R.id.save_wisdom_modifier)),
                withClassName(endsWith("TextView"))
            )
        ).check(matches(withText("5")))

        // charisma
        onView(withId(R.id.save_charisma_label)).check(matches(withText("Charisma:")))
        onView(withId(R.id.save_charisma_base)).check(matches(withText("+6")))
        onView(withId(R.id.save_charisma_attribute)).check(matches(withText("+6")))
        onView(
            allOf(
                withParent(withId(R.id.save_charisma_modifier)),
                withClassName(endsWith("TextView"))
            )
        ).check(matches(withText("6")))
    }

}