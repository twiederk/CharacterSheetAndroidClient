package com.android.ash.charactersheet.gui.main.charactercreator

import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.EquipmentScreenViewModel
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.StarterPackBoxViewModel
import com.d20charactersheet.framework.boc.model.AttackWield
import com.d20charactersheet.framework.boc.model.ItemGroup
import com.d20charactersheet.framework.boc.model.StarterPackBox
import com.d20charactersheet.framework.boc.model.StarterPackBoxItemOption
import com.d20charactersheet.framework.boc.model.Weapon
import com.d20charactersheet.framework.boc.model.WeaponCategory
import com.d20charactersheet.framework.boc.model.WeaponEncumbrance
import com.d20charactersheet.framework.dsl.createCharacter
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


class CharacterCreatorWeaponAttackTest {

    private val character = createCharacter { }
    private val equipmentScreenViewModel: EquipmentScreenViewModel = mock()

    @Test
    fun fillWeaponAttacks_noWeapon_noWeaponAttacks() {

        // arrange
        whenever(equipmentScreenViewModel.starterPackBoxViewModels).thenReturn(emptyList())

        // act
        CharacterCreatorWeaponAttack().fillWeaponAttacks(
            character,
            equipmentScreenViewModel
        )

        // assert
        assertThat(character.weaponAttacks).isEmpty()
    }

    @Test
    fun fillWeaponAttacks_oneHandedWeapon_weaponAttackForOneHandedWeapon() {
        // arrange
        val longsword = Weapon().apply {
            name = "Longsword"
            weaponEncumbrance = WeaponEncumbrance.ONE_HANDED
        }
        whenever(equipmentScreenViewModel.starterPackBoxViewModels).thenReturn(
            listOf(createStarterPackBoxViewModelWitWeapon(longsword))
        )

        // act
        CharacterCreatorWeaponAttack().fillWeaponAttacks(
            character,
            equipmentScreenViewModel
        )

        // assert
        assertThat(character.weaponAttacks).hasSize(1)
        assertWeaponAttack(longsword, AttackWield.ONE_HAND)
    }

    @Test
    fun fillWeaponAttacks_lightHandedWeapon_weaponAttackForOneHandedWeapon() {
        // arrange
        val dagger = Weapon().apply {
            name = "Dagger"
            weaponEncumbrance = WeaponEncumbrance.LIGHT_HANDED
        }
        whenever(equipmentScreenViewModel.starterPackBoxViewModels).thenReturn(
            listOf(createStarterPackBoxViewModelWitWeapon(dagger))
        )

        // act
        CharacterCreatorWeaponAttack().fillWeaponAttacks(
            character,
            equipmentScreenViewModel
        )

        // assert
        assertThat(character.weaponAttacks).hasSize(1)
        assertWeaponAttack(dagger, AttackWield.ONE_HAND)
    }

    @Test
    fun fillWeaponAttacks_twoHandedWeapon_weaponAttackForTwoHandedWeapon() {
        // arrange
        val pike = Weapon().apply {
            name = "Pike"
            weaponEncumbrance = WeaponEncumbrance.TWO_HANDED
        }
        whenever(equipmentScreenViewModel.starterPackBoxViewModels).thenReturn(
            listOf(createStarterPackBoxViewModelWitWeapon(pike))
        )

        // act
        CharacterCreatorWeaponAttack().fillWeaponAttacks(
            character,
            equipmentScreenViewModel
        )

        // assert
        assertThat(character.weaponAttacks).hasSize(1)
        assertWeaponAttack(pike, AttackWield.TWO_HANDED)
    }

    @Test
    fun fillWeaponAttacks_rangedWeapon_weaponAttackForRangedWeapon() {
        // arrange
        val shortBow = Weapon().apply {
            name = "Short Bow"
            weaponEncumbrance = WeaponEncumbrance.TWO_HANDED
        }
        whenever(equipmentScreenViewModel.starterPackBoxViewModels).thenReturn(
            listOf(createStarterPackBoxViewModelWitWeapon(shortBow))
        )

        // act
        CharacterCreatorWeaponAttack().fillWeaponAttacks(
            character,
            equipmentScreenViewModel
        )

        // assert
        assertThat(character.weaponAttacks).hasSize(1)
        assertWeaponAttack(shortBow, AttackWield.TWO_HANDED)
    }

    @Test
    fun fillWeaponAttacks_ammunition_noWeaponAttack() {
        // arrange
        val arrows = Weapon().apply {
            name = "Arrows (20)"
            weaponEncumbrance = WeaponEncumbrance.TWO_HANDED
            weaponCategory = WeaponCategory.AMMUNITION
        }
        whenever(equipmentScreenViewModel.starterPackBoxViewModels).thenReturn(
            listOf(createStarterPackBoxViewModelWitWeapon(arrows))
        )

        // act
        CharacterCreatorWeaponAttack().fillWeaponAttacks(
            character,
            equipmentScreenViewModel
        )

        // assert
        assertThat(character.weaponAttacks).isEmpty()
    }

    private fun createStarterPackBoxViewModelWitWeapon(weapon: Weapon) =
        StarterPackBoxViewModel(
            StarterPackBox().also { starterPackBox ->
                starterPackBox.add(
                    StarterPackBoxItemOption().also { starterPackBoxItemOption ->
                        starterPackBoxItemOption.add(
                            ItemGroup().apply { item = weapon }
                        )
                    }
                )
            }
        )

    private fun assertWeaponAttack(
        weapon: Weapon,
        attackWield: AttackWield
    ) {
        val weaponAttack = character.weaponAttacks[0]
        assertThat(weaponAttack.name).isEqualTo(weapon.name)
        assertThat(weaponAttack.description).isEmpty()
        assertThat(weaponAttack.attackWield).isEqualTo(attackWield)
        assertThat(weaponAttack.weapon).isEqualTo(weapon)
        assertThat(weaponAttack.ammunition).isEqualTo(0)
        assertThat(weaponAttack.damageBonus).isEqualTo(0)
        assertThat(weaponAttack.attackBonusModifier).isEqualTo(0)
        assertThat(weaponAttack.damageBonusModifier).isEqualTo(0)
    }

}