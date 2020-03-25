package com.android.ash.charactersheet.gui.sheet.attack

import android.content.Context
import android.content.Intent
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.android.ash.charactersheet.*
import com.d20charactersheet.framework.boc.model.*
import com.d20charactersheet.framework.boc.service.DisplayService
import com.d20charactersheet.framework.boc.service.GameSystem
import com.d20charactersheet.framework.boc.service.RuleService
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import java.util.*

class WeaponAttackEditActivityInstrumentationTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()
    private val characterHolder by inject<CharacterHolder>()

    private lateinit var scenario: ActivityScenario<WeaponAttackEditActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val weapon = Weapon().apply {
            id = 1
            name = "myWeapon"
            weaponCategory = WeaponCategory.PROJECTILE_WEAPON
        }

        val displayService: DisplayService = mock()
        whenever(displayService.getDisplayAttackWield(AttackWield.OFF_HAND)).doReturn("myAttackWield")

        val ruleService: RuleService = mock()
        whenever(ruleService.getAttackWields(weapon)).doReturn(EnumSet.of(AttackWield.OFF_HAND))

        val gameSystem: GameSystem = mock()
        gameSystemHolder.gameSystem = gameSystem
        whenever(gameSystem.displayService).doReturn(displayService)
        whenever(gameSystem.ruleService).doReturn(ruleService)
        whenever(gameSystem.allWeapons).doReturn(listOf(weapon))

        characterHolder.character = Character().apply {
            weaponAttacks = listOf(WeaponAttack().apply {
                id = 1
                name = "myWeaponAttack"
                this.weapon = weapon
                attackBonusModifier = 1
                damageBonusModifier = 2
                ammunition = 10
                description = "myDescription"
            })
        }

        val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(targetContext, WeaponAttackEditActivity::class.java).putExtra(Constants.INTENT_EXTRA_DATA_OBJECT, 1)
        scenario = ActivityScenario.launch(intent)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Edit Attack"))))
        onView(withId(R.id.weaponattack_name)).check(matches(withText("myWeaponAttack")))
        onView(withId(R.id.weaponattack_weapon)).check(matches(withText("myWeapon")))
        onView(withId(R.id.weaponattack_attackwield)).check(matches(withSpinnerText("OFF_HAND")))
        onView(withId(R.id.weaponattack_attack_bonus_modifier)).check(matches(withText("1")))
        onView(withId(R.id.weaponattack_damage_bonus_modifier)).check(matches(withText("2")))
        onView(withId(R.id.weaponattack_ammo)).check(matches(withText("10")))
        onView(withId(R.id.weaponattack_desc)).check(matches(withText("myDescription")))
    }

}