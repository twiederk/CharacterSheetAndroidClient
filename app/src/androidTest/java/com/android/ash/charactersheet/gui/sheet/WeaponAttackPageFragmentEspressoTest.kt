package com.android.ash.charactersheet.gui.sheet

import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android.ash.charactersheet.*
import com.d20charactersheet.framework.boc.model.*
import com.d20charactersheet.framework.boc.service.DisplayService
import com.d20charactersheet.framework.boc.service.GameSystem
import com.d20charactersheet.framework.boc.service.RuleService
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.endsWith
import org.hamcrest.core.Is
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class WeaponAttackPageFragmentEspressoTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val characterHolder: CharacterHolder by inject()
    private val preferenceServiceHolder by inject<PreferenceServiceHolder>()

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
        launchFragmentInContainer<WeaponAttackPageFragment>(themeResId = R.style.AppTheme)

        // Assert
        onView(withId(R.id.weaponattack_name)).check(matches(withText("myWeaponAttack")))
        onView(allOf(withParent(withId(R.id.weaponattack_attackbonus)), withClassName(endsWith("Button")))).check(matches(withText("  +6  ")))
        onView(withId(R.id.weaponattack_damage)).check(matches(withText("1D8")))
        onView(withId(R.id.weaponattack_critical)).check(matches(withText("19/x3")))
        onView(withId(R.id.weaponattack_range_increment)).check(matches(withText("10 Feet")))
        onView(withId(R.id.weaponattack_ammunition)).check(matches(withText("20")))
        onView(withId(R.id.weaponattack_description)).check(matches(withText("myDescription")))
    }

    @Test
    fun fab_onClick_callWeaponAttackCreateActivity() {

        val displayService: DisplayService = mock()
        whenever(displayService.getDisplayItem(any())).doReturn("myWeapon")

        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(displayService)
        whenever(gameSystem.allWeapons).doReturn(listOf(Weapon().apply { weaponCategory = WeaponCategory.NORMAL_WEAPON }))
        gameSystemHolder.gameSystem = gameSystem

        characterHolder.character = Character().apply { name = "myCharacter" }

        preferenceServiceHolder.preferenceService = mock()

        launchFragmentInContainer<WeaponAttackPageFragment>(themeResId = R.style.AppTheme)

        // Act
        onView(withId(R.id.favorite_action_button)).perform(click())

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("myCharacter"))))
    }

}