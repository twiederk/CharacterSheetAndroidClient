package com.android.ash.charactersheet.gui.sheet

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.model.ItemGroup
import com.d20charactersheet.framework.boc.model.Weapon
import com.d20charactersheet.framework.boc.service.DisplayService
import com.d20charactersheet.framework.boc.service.GameSystem
import com.d20charactersheet.framework.boc.service.RuleService
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class EquipmentPageFragmentEspressoTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val characterHolder: CharacterHolder by inject()

    @Test
    fun onCreateView() {
        // Arrange
        val displayService: DisplayService = mock()
        whenever(displayService.getDisplayItem(any())).doReturn("myWeapon")
        whenever(displayService.getDisplayDamage(any<Weapon>())).doReturn("myDamage")

        val ruleService: RuleService = mock()
        whenever(ruleService.getLoad(any())).doReturn(10.1F)

        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(displayService)
        whenever(gameSystem.ruleService).doReturn(ruleService)

        gameSystemHolder.gameSystem = gameSystem
        characterHolder.character = Character().apply {
            equipment.weapons.add(ItemGroup().apply {
                number = 2
                item = Weapon()
            })
        }

        // Act
        launchFragmentInContainer<EquipmentPageFragment>()

        // Assert
        onView(withId(R.id.equipment_list_load)).check(matches(withText("Load: 10.1 Pound")))
        onView(withId(R.id.weapon_number)).check(matches(withText("2")))
        onView(withId(R.id.weapon_name)).check(matches(withText("myWeapon")))
        onView(withId(R.id.weapon_damage)).check(matches(withText("myDamage")))
    }

}