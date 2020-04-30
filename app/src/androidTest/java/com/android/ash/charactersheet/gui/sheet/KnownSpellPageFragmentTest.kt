package com.android.ash.charactersheet.gui.sheet

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.d20charactersheet.framework.boc.model.*
import com.d20charactersheet.framework.boc.service.DisplayService
import com.d20charactersheet.framework.boc.service.GameSystem
import com.d20charactersheet.framework.boc.service.RuleService
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.Matchers.allOf
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class KnownSpellPageFragmentTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val characterHolder: CharacterHolder by inject()

    @Test
    fun onCreateView() {
        // Arrange
        val displayService: DisplayService = mock()
        whenever(displayService.getDisplaySpellLevel(any())).doReturn("mySpellLevel")
        whenever(displayService.getDisplayNumberOfKnownSpells(any(), any(), any())).doReturn("myNumberOfKnownSpells")

        val ruleService: RuleService = mock()

        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(displayService)
        whenever(gameSystem.ruleService).doReturn(ruleService)

        gameSystemHolder.gameSystem = gameSystem
        characterHolder.character = Character().apply {
            race = Race().apply {
                abilities = listOf(SpelllistAbility().apply {
                    spelllist = Spelllist().apply {
                        name = "mySpelllist"
                        spellsByLevel = mapOf(1 to listOf(Spell().apply {
                            name = "mySpell"
                            shortDescription = "myShortDescription"
                        }))
                    }
                })
            }
        }

        // Act
        launchFragmentInContainer<KnownSpellPageFragment>()

        // Assert
        onView(allOf(withId(R.id.spelllevel_title), isDisplayed())).check(matches(withText("mySpellLevel")))
        onView(allOf(withId(R.id.spelllevel_number_of_known_spells), isDisplayed())).check(matches(withText("myNumberOfKnownSpells")))
        onView(allOf(withId(R.id.spell_known_spell), isDisplayed())).check(matches(isNotChecked()))
        onView(allOf(withId(R.id.spell_name), isDisplayed())).check(matches(withText("mySpell")))
        onView(allOf(withId(R.id.spell_short_description), isDisplayed())).check(matches(withText("myShortDescription")))
    }

}