package com.android.ash.charactersheet.gui.admin.item.weapon

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.d20charactersheet.framework.boc.model.*
import com.d20charactersheet.framework.boc.service.ItemService
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
@MediumTest
class WeaponAdministrationCreateActivityRobotronicTest {

    @Test
    fun createForm() {

        // Arrange
        val weaponFamily = WeaponFamily()
        weaponFamily.id = 1
        val itemService: ItemService = mock()
        whenever(itemService.allWeaponFamilies).doReturn(listOf(weaponFamily))
        val underTest = WeaponAdministrationCreateActivity()
        underTest.itemService = itemService

        // Act
        val weapon = underTest.createForm() as Weapon

        // Assert
        assertThat(weapon.name).isEmpty()
        assertThat(weapon.weaponType).isEqualTo(WeaponType.SIMPLE)
        assertThat(weapon.cost).isEqualTo(0F)
        assertThat(weapon.weight).isEqualTo(1F)
        assertThat(weapon.qualityType).isEqualTo(QualityType.NORMAL)
        assertThat(weapon.enhancementBonus).isEqualTo(0)
        assertThat(weapon.damage).isEqualTo(Damage(1, Die.D6))
        assertThat(weapon.critical).isEqualTo(Critical(20, 2))
        assertThat(weapon.combatType).isEqualTo(CombatType.MELEE_WEAPON)
        assertThat(weapon.weaponEncumbrance).isEqualTo(WeaponEncumbrance.LIGHT_HANDED)
        assertThat(weapon.weaponCategory).isEqualTo(WeaponCategory.NORMAL_WEAPON)
        assertThat(weapon.weaponFamily).isEqualTo(weaponFamily)
        assertThat(weapon.rangeIncrement).isEqualTo(0)
        assertThat(weapon.description).isEmpty()
    }

}
