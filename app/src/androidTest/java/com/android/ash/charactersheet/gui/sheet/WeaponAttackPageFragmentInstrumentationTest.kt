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
import org.hamcrest.Matchers.endsWith
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class WeaponAttackPageFragmentInstrumentationTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val characterHolder: CharacterHolder by inject()

    @Test
    fun onCreateView() {
        // Arrange
        val weaponAttack = WeaponAttack().apply {
            name = "myWeaponAttack"
            attackBonuses = listOf(6)
            weapon = Weapon().apply {
                critical = Critical(19, 3)
                rangeIncrement = 10
                weaponCategory = WeaponCategory.PROJECTILE_WEAPON
            }
            ammunition = 20
            description = "myDescription"
        }

        val displayService: DisplayService = mock()
        whenever(displayService.getDisplayDamage(weaponAttack)).doReturn("1D8")
        whenever(displayService.getDisplayCritical(any())).doReturn("19/x3")
        whenever(displayService.getDisplayRangeIncrement(any())).doReturn("10 Feet")
        val ruleService: RuleService = mock()

        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(displayService)
        whenever(gameSystem.ruleService).doReturn(ruleService)


        gameSystemHolder.gameSystem = gameSystem
        characterHolder.character = Character().apply { weaponAttacks = listOf(weaponAttack) }

        // Act
        launchFragmentInContainer<WeaponAttackPageFragment>()

        // Assert
        onView(withId(R.id.weaponattack_name)).check(matches(withText("myWeaponAttack")))
        onView(allOf(withParent(withId(R.id.weaponattack_attackbonus)), withClassName(endsWith("Button")))).check(matches(withText("  +6  ")))
        onView(withId(R.id.weaponattack_damage)).check(matches(withText("1D8")))
        onView(withId(R.id.weaponattack_critical)).check(matches(withText("19/x3")))
        onView(withId(R.id.weaponattack_range_increment)).check(matches(withText("10 Feet")))
        onView(withId(R.id.weaponattack_ammunition)).check(matches(withText("20")))
        onView(withId(R.id.weaponattack_description)).check(matches(withText("myDescription")))
    }

}