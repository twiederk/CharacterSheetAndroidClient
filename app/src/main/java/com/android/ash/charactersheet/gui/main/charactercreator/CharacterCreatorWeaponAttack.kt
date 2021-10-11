package com.android.ash.charactersheet.gui.main.charactercreator

import com.d20charactersheet.framework.boc.model.*

class CharacterCreatorWeaponAttack {
    fun fillWeaponAttacks(
        character: Character,
        equipmentScreenViewModel: EquipmentScreenViewModel
    ) {
        val weapons = getWeapons(equipmentScreenViewModel)
        val weaponAttacks = mutableListOf<WeaponAttack>()
        for (weapon in weapons) {
            weaponAttacks.add(createWeaponAttack(weapon))
        }
        character.weaponAttacks = weaponAttacks
    }

    private fun createWeaponAttack(weapon: Weapon): WeaponAttack {
        return WeaponAttack().apply {
            name = weapon.name
            description = ""
            attackWield = determineAttackWield(weapon.weaponEncumbrance)
            this.weapon = weapon
        }
    }

    private fun determineAttackWield(weaponEncumbrance: WeaponEncumbrance?): AttackWield =
        if (weaponEncumbrance == WeaponEncumbrance.TWO_HANDED) AttackWield.TWO_HANDED
        else AttackWield.ONE_HAND

    private fun getWeapons(equipmentScreenViewModel: EquipmentScreenViewModel): List<Weapon> {
        val weapons = mutableListOf<Weapon>()
        for (starterPackBoxViewModel in equipmentScreenViewModel.starterPackBoxViewModels.value) {
            val starterPackBoxOption =
                checkNotNull(starterPackBoxViewModel.starterPackBoxOption.value)
            starterPackBoxOption.getItemGroups()
                .filter { itemGroup -> itemGroup.item is Weapon }
                .map { itemGroup -> itemGroup.item as Weapon }
                .filter { weapon -> weapon.weaponCategory != WeaponCategory.AMMUNITION }
                .forEach { weapons.add(it) }
        }
        return weapons
    }

}
