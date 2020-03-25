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
import com.android.ash.charactersheet.Constants
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.withToolbarTitle
import com.d20charactersheet.framework.boc.model.AttackWield
import com.d20charactersheet.framework.boc.model.Weapon
import com.d20charactersheet.framework.boc.service.DisplayService
import com.d20charactersheet.framework.boc.service.GameSystem
import com.d20charactersheet.framework.boc.service.RuleService
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.Matchers.isEmptyString
import org.hamcrest.Matchers.not
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import java.util.*

class WeaponAttackCreateActivityInstrumentationTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<WeaponAttackCreateActivity>

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

        val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(targetContext, WeaponAttackCreateActivity::class.java).putExtra(Constants.INTENT_EXTRA_DATA_OBJECT, 1)
        scenario = ActivityScenario.launch(intent)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Create Attack"))))
        onView(withId(R.id.weaponattack_name)).check(matches(withText("myWeapon")))
        onView(withId(R.id.weaponattack_weapon)).check(matches(withText("myWeapon")))
        onView(withId(R.id.weaponattack_attackwield)).check(matches(withSpinnerText("OFF_HAND")))
        onView(withId(R.id.weaponattack_attack_bonus_modifier)).check(matches(withText("0")))
        onView(withId(R.id.weaponattack_damage_bonus_modifier)).check(matches(withText("0")))
        onView(withId(R.id.weaponattack_ammo)).check(matches(not(isDisplayed())))
        onView(withId(R.id.weaponattack_desc)).check(matches(withText(isEmptyString())))
    }

}