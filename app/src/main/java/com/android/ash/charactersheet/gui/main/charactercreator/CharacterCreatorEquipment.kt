package com.android.ash.charactersheet.gui.main.charactercreator

import com.d20charactersheet.framework.boc.model.*

class CharacterCreatorEquipment {

    fun fillEquipment(
        character: Character,
        equipmentScreenViewModel: EquipmentScreenViewModel
    ) {
        character.equipment.weapons = getWeapons(equipmentScreenViewModel)
        character.equipment.armor = getArmor(equipmentScreenViewModel)
        character.equipment.goods = getGoods(equipmentScreenViewModel)
    }

    fun getWeapons(equipmentScreenViewModel: EquipmentScreenViewModel) =
        filterItemGroups(equipmentScreenViewModel) { itemGroup -> itemGroup.item is Weapon }

    fun getArmor(equipmentScreenViewModel: EquipmentScreenViewModel): List<ItemGroup> =
        filterItemGroups(equipmentScreenViewModel) { itemGroup -> itemGroup.item is Armor }

    fun getGoods(equipmentScreenViewModel: EquipmentScreenViewModel): List<ItemGroup> {
        val equipmentPackItemGroups =
            filterItemGroups(equipmentScreenViewModel) { itemGroup -> itemGroup.item is Good }
        return equipmentPackItemGroups.map {
            ItemGroup().apply {
                item = it.item; number = it.number
            }
        }
    }

    private fun filterItemGroups(
        equipmentScreenViewModel: EquipmentScreenViewModel,
        filter: (ItemGroup) -> Boolean
    ): List<ItemGroup> {
        val itemGroups = mutableListOf<ItemGroup>()
        for (starterPackBoxViewModel in equipmentScreenViewModel.starterPackBoxViewModels.value) {
            val starterPackBoxOption =
                checkNotNull(starterPackBoxViewModel.starterPackBoxOption.value)
            starterPackBoxOption.getItemGroups().filter { filter(it) }
                .forEach { itemGroups.add(it) }
        }
        return itemGroups
    }

}
