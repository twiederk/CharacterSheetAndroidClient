package com.android.ash.charactersheet.gui.admin.item.weapon

import android.content.Context
import android.content.Intent
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.android.ash.charactersheet.Constants
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.withToolbarTitle
import com.d20charactersheet.framework.boc.model.*
import com.d20charactersheet.framework.boc.service.GameSystem
import com.d20charactersheet.framework.boc.service.ItemService

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.Matchers.*
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class WeaponAdministrationEditActivityInstrumentationTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<WeaponAdministrationEditActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val weapon = Weapon()
        weapon.name = "MyWeapon"
        weapon.weaponType = WeaponType.SIMPLE
        weapon.weight = 1f
        weapon.cost = 0f
        weapon.qualityType = QualityType.NORMAL
        weapon.enhancementBonus = 0
        weapon.damage = Damage(1, Die.D6)
        weapon.critical = Critical(20, 2)
        weapon.description = ""
        weapon.combatType = CombatType.MELEE_WEAPON
        weapon.weaponEncumbrance = WeaponEncumbrance.LIGHT_HANDED
        weapon.weaponCategory = WeaponCategory.NORMAL_WEAPON
        weapon.weaponFamily = WeaponFamily()
        weapon.rangeIncrement = 0

        val itemService: ItemService = mock()
        whenever(itemService.getItemById(any(), any())).doReturn(weapon)

        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(mock())
        whenever(gameSystem.itemService).doReturn(itemService)

        gameSystemHolder.gameSystem = gameSystem

        val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(targetContext, WeaponAdministrationEditActivity::class.java).putExtra(Constants.INTENT_EXTRA_DATA_OBJECT, 1)
        scenario = ActivityScenario.launch(intent)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Edit Weapon"))))
        onView(withId(R.id.item_administration_name)).check(matches(withText("MyWeapon")))
        onView(withId(R.id.item_administration_type)).check(matches(withSpinnerText("SIMPLE")))
        onView(allOf(withParent(withId(R.id.item_administration_cost)), withClassName(endsWith("TextView")))).check(matches(withText("0.0")))
        onView(allOf(withParent(withId(R.id.item_administration_weight)), withClassName(endsWith("TextView")))).check(matches(withText("1.0")))
        onView(withId(R.id.item_administration_quality_type)).check(matches(withSpinnerText("NORMAL")))
        onView(withId(R.id.weapon_administration_enhancement_bonus)).check(matches(withText("0")))
        onView(withId(R.id.weapon_administration_damage_number_of_dice)).check(matches(withText("1")))
        onView(withId(R.id.weapon_administration_damage_die)).check(matches(withSpinnerText("D6")))
        onView(withId(R.id.weapon_administration_critical_hit)).check(matches(withText("20")))
        onView(withId(R.id.weapon_administration_critical_multiplier)).check(matches(withText("2")))
        onView(withId(R.id.weapon_administration_combat_type)).check(matches(withSpinnerText("MELEE_WEAPON")))
        onView(withId(R.id.weapon_administration_weapon_encumbrance)).check(matches(withSpinnerText("LIGHT_HANDED")))
        onView(withId(R.id.weapon_administration_weapon_category)).check(matches(withSpinnerText("NORMAL_WEAPON")))
        onView(withId(R.id.weapon_administration_range_increment)).check(matches(withText("0")))
        onView(withId(R.id.item_administration_desc)).check(matches(withText(isEmptyString())))
    }

}