package com.android.ash.charactersheet.gui.main.charactercreator

import com.d20charactersheet.framework.boc.model.*

class CharacterCreatorEquipment {

    fun fillEquipment(character: Character, characterCreatorViewModel: CharacterCreatorViewModel) {
        character.equipment.weapons = getWeapons(characterCreatorViewModel)
        character.equipment.armor = getArmor(characterCreatorViewModel)
        character.equipment.goods = getGoods(characterCreatorViewModel)
    }

    fun getWeapons(characterCreatorViewModel: CharacterCreatorViewModel) =
        filterItemGroups(characterCreatorViewModel) { itemGroup -> itemGroup.item is Weapon }

    fun getArmor(characterCreatorViewModel: CharacterCreatorViewModel): List<ItemGroup> =
        filterItemGroups(characterCreatorViewModel) { itemGroup -> itemGroup.item is Armor }

    fun getGoods(characterCreatorViewModel: CharacterCreatorViewModel): List<ItemGroup> {
        val equipmentPackItemGroups =
            filterItemGroups(characterCreatorViewModel) { itemGroup -> itemGroup.item is Good }
        return equipmentPackItemGroups.map {
            ItemGroup().apply {
                item = it.item; number = it.number
            }
        }
    }

    private fun filterItemGroups(
        characterCreatorViewModel: CharacterCreatorViewModel,
        filter: (ItemGroup) -> Boolean
    ): List<ItemGroup> {
        val itemGroups = mutableListOf<ItemGroup>()
        for (starterPackBoxViewModel in characterCreatorViewModel.starterPackBoxViewModels) {
            val starterPackBoxOption =
                checkNotNull(starterPackBoxViewModel.starterPackBoxOption.value)
            starterPackBoxOption.getItemGroups().filter { filter(it) }
                .forEach { itemGroups.add(it) }
        }
        return itemGroups
    }

}
