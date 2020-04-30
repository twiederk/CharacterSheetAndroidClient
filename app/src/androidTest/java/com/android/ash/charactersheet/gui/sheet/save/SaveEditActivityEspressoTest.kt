package com.android.ash.charactersheet.gui.sheet.save

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
import com.d20charactersheet.framework.boc.model.Save
import com.d20charactersheet.framework.boc.service.DisplayService
import com.d20charactersheet.framework.boc.service.GameSystem
import com.d20charactersheet.framework.boc.service.RuleService
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

class SaveEditActivityEspressoTest : KoinTest {

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
        whenever(ruleService.getBaseSave(character, Save.FORTITUDE)).doReturn(1)
        whenever(ruleService.getBaseSave(character, Save.REFLEX)).doReturn(2)
        whenever(ruleService.getBaseSave(character, Save.WILL)).doReturn(3)
        whenever(ruleService.getSaveAttributeModifier(character, Save.FORTITUDE)).doReturn(1)
        whenever(ruleService.getSaveAttributeModifier(character, Save.REFLEX)).doReturn(2)
        whenever(ruleService.getSaveAttributeModifier(character, Save.WILL)).doReturn(3)
        whenever(ruleService.getSaveModifier(character, Save.FORTITUDE)).doReturn(1)
        whenever(ruleService.getSaveModifier(character, Save.REFLEX)).doReturn(2)
        whenever(ruleService.getSaveModifier(character, Save.WILL)).doReturn(3)

        val displayService: DisplayService = mock()
        whenever(displayService.getDisplayModifier(1)).doReturn("+1")
        whenever(displayService.getDisplayModifier(2)).doReturn("+2")
        whenever(displayService.getDisplayModifier(3)).doReturn("+3")

        val gameSystem: GameSystem = mock()
        gameSystemHolder.gameSystem = gameSystem
        whenever(gameSystem.displayService).doReturn(displayService)
        whenever(gameSystem.ruleService).doReturn(ruleService)

        characterHolder.character = character

        scenario = ActivityScenario.launch(SaveEditActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Saves"))))
        onView(withId(R.id.save_fortitude_base)).check(matches(withText("+1")))
        onView(withId(R.id.save_fortitude_attribute)).check(matches(withText("+1")))
        onView(allOf(withParent(withId(R.id.save_fortitude_modifier)), withClassName(endsWith("TextView")))).check(matches(withText("1")))
        onView(withId(R.id.save_reflex_base)).check(matches(withText("+2")))
        onView(withId(R.id.save_reflex_attribute)).check(matches(withText("+2")))
        onView(allOf(withParent(withId(R.id.save_reflex_modifier)), withClassName(endsWith("TextView")))).check(matches(withText("2")))
        onView(withId(R.id.save_will_base)).check(matches(withText("+3")))
        onView(withId(R.id.save_will_attribute)).check(matches(withText("+3")))
        onView(allOf(withParent(withId(R.id.save_will_modifier)), withClassName(endsWith("TextView")))).check(matches(withText("3")))
    }

}