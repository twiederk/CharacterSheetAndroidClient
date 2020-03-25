package com.android.ash.charactersheet.gui.sheet.attribute

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

class AttributesEditActivityInstrumentationTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()
    private val characterHolder by inject<CharacterHolder>()

    private lateinit var scenario: ActivityScenario<AttributesEditActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {
        // Arrange
        val ruleService: RuleService = mock()
        whenever(ruleService.getModifier(10)).doReturn(0)
        whenever(ruleService.getModifier(12)).doReturn(1)
        whenever(ruleService.getModifier(14)).doReturn(2)
        whenever(ruleService.getModifier(16)).doReturn(3)
        whenever(ruleService.getModifier(18)).doReturn(4)
        whenever(ruleService.getModifier(20)).doReturn(5)

        val displayService: DisplayService = mock()
        whenever(displayService.getDisplayModifier(0)).doReturn("0")
        whenever(displayService.getDisplayModifier(1)).doReturn("+1")
        whenever(displayService.getDisplayModifier(2)).doReturn("+2")
        whenever(displayService.getDisplayModifier(3)).doReturn("+3")
        whenever(displayService.getDisplayModifier(4)).doReturn("+4")
        whenever(displayService.getDisplayModifier(5)).doReturn("+5")

        val gameSystem: GameSystem = mock()
        gameSystemHolder.gameSystem = gameSystem
        whenever(gameSystem.displayService).doReturn(displayService)
        whenever(gameSystem.ruleService).doReturn(ruleService)

        characterHolder.character = Character().apply {
            name = "myCharacter"
            strength = 10
            dexterity = 12
            constitution = 14
            intelligence = 16
            wisdom = 18
            charisma = 20
        }

        scenario = ActivityScenario.launch(AttributesEditActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Abilities"))))
        onView(allOf(withParent(withId(R.id.attribute_str)), withClassName(endsWith("TextView")))).check(matches(withText("10")))
        onView(allOf(withParent(withId(R.id.attribute_dex)), withClassName(endsWith("TextView")))).check(matches(withText("12")))
        onView(allOf(withParent(withId(R.id.attribute_con)), withClassName(endsWith("TextView")))).check(matches(withText("14")))
        onView(allOf(withParent(withId(R.id.attribute_int)), withClassName(endsWith("TextView")))).check(matches(withText("16")))
        onView(allOf(withParent(withId(R.id.attribute_wis)), withClassName(endsWith("TextView")))).check(matches(withText("18")))
        onView(allOf(withParent(withId(R.id.attribute_cha)), withClassName(endsWith("TextView")))).check(matches(withText("20")))
        onView(withId(R.id.modifier_str)).check(matches(withText("0")))
        onView(withId(R.id.modifier_dex)).check(matches(withText("+1")))
        onView(withId(R.id.modifier_con)).check(matches(withText("+2")))
        onView(withId(R.id.modifier_int)).check(matches(withText("+3")))
        onView(withId(R.id.modifier_wis)).check(matches(withText("+4")))
        onView(withId(R.id.modifier_cha)).check(matches(withText("+5")))
    }

}