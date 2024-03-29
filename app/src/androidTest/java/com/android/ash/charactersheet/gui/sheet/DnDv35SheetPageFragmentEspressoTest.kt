package com.android.ash.charactersheet.gui.sheet

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.model.CharacterClass
import com.d20charactersheet.framework.boc.model.ClassLevel
import com.d20charactersheet.framework.boc.model.Race
import com.d20charactersheet.framework.boc.model.Sex
import com.d20charactersheet.framework.boc.model.XpTable
import com.d20charactersheet.framework.boc.service.DisplayService
import com.d20charactersheet.framework.boc.service.GameSystem
import com.d20charactersheet.framework.boc.service.RuleService
import com.d20charactersheet.framework.boc.service.XpService
import org.hamcrest.Matchers.not
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class DnDv35SheetPageFragmentEspressoTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val characterHolder: CharacterHolder by inject()

    @Test
    fun onCreateView() {
        // Arrange
        val displayService: DisplayService = mock()
        whenever(displayService.getDisplaySex(Sex.MALE)).doReturn("mySex")
        whenever(displayService.getDisplayClassLevel(any())).doReturn("myClass (1)")
        whenever(displayService.getDisplayModifier(any())).doReturn("+1")

        val xpService: XpService = mock()
        whenever(xpService.getNextLevelAt(any(), any())).doReturn(1000)

        val ruleService: RuleService = mock()
        whenever(ruleService.getGold(any())).doReturn(12.34F)
        whenever(ruleService.getSave(any(), any())).doReturn(1)
        whenever(ruleService.getArmorClass(any())).doReturn(10)
        whenever(ruleService.calculateFlatFootedArmorClass(any())).doReturn(11)
        whenever(ruleService.calculateTouchArmorClass(any())).doReturn(12)
        whenever(ruleService.getSpeed(any())).doReturn(20)

        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(displayService)
        whenever(gameSystem.xpService).doReturn(xpService)
        whenever(gameSystem.ruleService).doReturn(ruleService)

        gameSystemHolder.gameSystem = gameSystem
        gameSystemHolder.gameSystemType = GameSystemType.DNDV35

        characterHolder.character = Character().apply {
            name = "myCharacter"
            player = "myPlayer"
            race = Race().apply { name = "myRace" }
            sex = Sex.MALE
            classLevels = listOf(ClassLevel(CharacterClass().apply { name = "myClass"; classAbilities = listOf() }, 1))
            experiencePoints = 100
            xpTable = XpTable()
            strength = 10
            dexterity = 11
            constitution = 12
            intelligence = 13
            wisdom = 14
            charisma = 15
            hitPoints = 1
            maxHitPoints = 2
        }

        // Act
        launchFragmentInContainer<SheetPageFragment>()

        // Assert
        onView(withId(R.id.appearance_player)).check(matches(withText("myPlayer")))
        onView(withId(R.id.appearance_race)).check(matches(withText("myRace")))
        onView(withId(R.id.appearance_sex)).check(matches(withText("mySex")))
        onView(withId(R.id.appearance_class_level)).check(matches(withText("myClass (1)")))
        onView(withId(R.id.appearance_experience)).check(matches(withText("100 / 1000")))

        onView(withId(R.id.money_gold)).check(matches(withText("12,34")))

        onView(withId(R.id.attribute_str_value)).check(matches(withText("10")))
        onView(withId(R.id.attribute_str_mod)).check(matches(withText("+1")))
        onView(withId(R.id.attribute_dex_value)).check(matches(withText("11")))
        onView(withId(R.id.attribute_dex_mod)).check(matches(withText("+1")))
        onView(withId(R.id.attribute_con_value)).check(matches(withText("12")))
        onView(withId(R.id.attribute_con_mod)).check(matches(withText("+1")))
        onView(withId(R.id.attribute_int_value)).check(matches(withText("13")))
        onView(withId(R.id.attribute_int_mod)).check(matches(withText("+1")))
        onView(withId(R.id.attribute_wis_value)).check(matches(withText("14")))
        onView(withId(R.id.attribute_wis_mod)).check(matches(withText("+1")))
        onView(withId(R.id.attribute_cha_value)).check(matches(withText("15")))
        onView(withId(R.id.attribute_cha_mod)).check(matches(withText("+1")))

        onView(withId(R.id.saves_include)).check(matches(isDisplayed()))
        onView(withId(R.id.save_strength_label)).check(matches(withText("Fortitude:")))
        onView(withId(R.id.save_strength)).check(matches(withText("+1")))
        onView(withId(R.id.save_dexterity_label)).check(matches(withText("Reflex:")))
        onView(withId(R.id.save_dexterity)).check(matches(withText("+1")))
        onView(withId(R.id.save_constitution_label)).check(matches(withText("Will:")))
        onView(withId(R.id.save_constitution)).check(matches(withText("+1")))
        onView(withId(R.id.save_intelligence_label)).check(matches(not(isDisplayed())))
        onView(withId(R.id.save_intelligence)).check(matches(not(isDisplayed())))
        onView(withId(R.id.save_wisdom_label)).check(matches(not(isDisplayed())))
        onView(withId(R.id.save_wisdom)).check(matches(not(isDisplayed())))
        onView(withId(R.id.save_charisma_label)).check(matches(not(isDisplayed())))
        onView(withId(R.id.save_charisma)).check(matches(not(isDisplayed())))

        onView(withId(R.id.combat_hitpoints)).check(matches(withText("1 (2)")))
        onView(withId(R.id.combat_proficiency_bonus_row)).check(matches(not(isDisplayed())))
        onView(withId(R.id.combat_armorclass)).check(matches(withText("10")))
        onView(withId(R.id.combat_flatfooted_armorclass)).check(matches(withText("11")))
        onView(withId(R.id.combat_touch_armorclass)).check(matches(withText("12")))
        onView(withId(R.id.combat_speed)).check(matches(withText("20")))
        onView(withId(R.id.combat_initiative)).check(matches(withText("+1")))
        onView(withId(R.id.combat_baseattackbonus)).check(matches(withText("+1")))
        onView(withId(R.id.combat_cmb)).check(matches(withText("+1")))
        onView(withId(R.id.combat_cmd)).check(matches(withText("+1")))
    }

}