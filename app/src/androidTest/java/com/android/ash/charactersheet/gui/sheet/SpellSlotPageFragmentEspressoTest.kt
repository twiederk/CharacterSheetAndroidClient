package com.android.ash.charactersheet.gui.sheet

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.d20charactersheet.framework.boc.model.*
import com.d20charactersheet.framework.boc.service.CharacterService
import com.d20charactersheet.framework.boc.service.DisplayService
import com.d20charactersheet.framework.boc.service.GameSystem
import com.d20charactersheet.framework.boc.service.RuleService
import org.hamcrest.Matchers.allOf
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class SpellSlotPageFragmentEspressoTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val characterHolder: CharacterHolder by inject()

    @Test
    fun onCreateView() {
        // Arrange
        val mySpell = Spell().apply {
            name = "mySpell"
            school = School.CONJURATION
            subSchool = SubSchool.CALLING
        }
        val spelllistAbility = SpelllistAbility().apply {
            name = "mySpelllistAbility"
            spelllist = Spelllist().apply {
                name = "mySpelllist"
                spellsByLevel = mapOf(1 to listOf(mySpell))
            }
            spellAttribute = Attribute.INTELLIGENCE
        }

        val displayService: DisplayService = mock()
        whenever(displayService.getDisplayClassLevel(any())).doReturn("myClassLevel")
        whenever(displayService.getDisplayAttributeShort(any())).doReturn("myAttribute")
        whenever(displayService.getDisplaySpellSlotLevel(any())).doReturn("mySpelllevel")
        whenever(displayService.getDisplaySchoolShort(any())).doReturn("mySchool")

        val ruleService: RuleService = mock()

        val characterService: CharacterService = mock()

        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(displayService)
        whenever(gameSystem.ruleService).doReturn(ruleService)
        whenever(gameSystem.characterService).doReturn(characterService)

        gameSystemHolder.gameSystem = gameSystem

        characterHolder.character = Character().apply {
            race = Race().apply { abilities = listOf(spelllistAbility) }
            spellSlots = listOf(SpellSlot().apply {
                spell = mySpell
                spelllistAbilities = listOf(spelllistAbility)
            })
            classLevels = listOf(ClassLevel(CharacterClass().apply {
                name = "myCharacterClass"
                classAbilities = listOf(ClassAbility(spelllistAbility))
            }))
        }

        // Act
        launchFragmentInContainer<SpellSlotPageFragment>()

        // Assert
        onView(withId(R.id.page_spell_slot_class_level)).check(matches(withText("myClassLevel")))
        onView(withId(R.id.page_spell_slot_attribute)).check(matches(withText("myAttribute: 0")))
        onView(allOf(withId(R.id.spelllevel_title), isDisplayed())).check(matches(withText("mySpelllevel")))
        onView(allOf(withId(R.id.spell_name), isDisplayed())).check(matches(withText("mySpell")))
        onView(allOf(withId(R.id.difficulty_class), isDisplayed())).check(matches(withText("DC: 0")))
        onView(allOf(withId(R.id.spelllist_ability), isDisplayed())).check(matches(withText("mySpelllistAbility")))
        onView(allOf(withId(R.id.spell_school), isDisplayed())).check(matches(withText("(mySchool)")))
    }

}