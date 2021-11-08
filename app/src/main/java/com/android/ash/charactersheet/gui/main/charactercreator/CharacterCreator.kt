package com.android.ash.charactersheet.gui.main.charactercreator

import android.os.Bundle
import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.FBAnalytics
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.AbilityScoresScreenViewModel
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.AppearanceScreenViewModel
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.ClassScreenViewModel
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.EquipmentScreenViewModel
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.RaceScreenViewModel
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.service.CharacterService
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharacterCreator(
    private val characterCreatorAppearance: CharacterCreatorAppearance = CharacterCreatorAppearance(),
    private val characterCreatorEquipment: CharacterCreatorEquipment = CharacterCreatorEquipment(),
    private val characterCreatorWeaponAttack: CharacterCreatorWeaponAttack = CharacterCreatorWeaponAttack()
) : KoinComponent {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val characterHolder: CharacterHolder by inject()
    private val firebaseAnalytics: FirebaseAnalytics by inject()

    fun createCharacter(
        raceScreenViewModel: RaceScreenViewModel,
        classScreenViewModel: ClassScreenViewModel,
        appearanceScreenViewModel: AppearanceScreenViewModel,
        abilityScoresScreenViewModel: AbilityScoresScreenViewModel,
        equipmentScreenViewModel: EquipmentScreenViewModel
    ): Character {
        val character = createCharacterInternal(
            abilityScoresScreenViewModel,
            classScreenViewModel,
            appearanceScreenViewModel,
            raceScreenViewModel,
            equipmentScreenViewModel
        )
        storeCharacter(character)
        logEventCharacterCreate(character)
        return character
    }

    private fun createCharacterInternal(
        abilityScoresScreenViewModel: AbilityScoresScreenViewModel,
        classScreenViewModel: ClassScreenViewModel,
        appearanceScreenViewModel: AppearanceScreenViewModel,
        raceScreenViewModel: RaceScreenViewModel,
        equipmentScreenViewModel: EquipmentScreenViewModel
    ): Character {
        val gameSystem = checkNotNull(gameSystemHolder.gameSystem)
        val character =
            characterCreatorAppearance.fillAppearance(
                raceScreenViewModel,
                classScreenViewModel,
                appearanceScreenViewModel,
                abilityScoresScreenViewModel,
                gameSystem
            )
        characterCreatorEquipment.fillEquipment(
            character,
            equipmentScreenViewModel
        )
        characterCreatorWeaponAttack.fillWeaponAttacks(
            character,
            equipmentScreenViewModel
        )
        return character
    }

    private fun storeCharacter(character: Character) {
        val gameSystem = checkNotNull(gameSystemHolder.gameSystem)
        gameSystem.characterService.createCharacter(character, gameSystem.allSkills)
        storeWeaponAttack(character, gameSystem.characterService)
        storeEquipment(character, gameSystem.characterService)
        characterHolder.character = character
    }

    private fun storeWeaponAttack(character: Character, characterService: CharacterService) {
        val weaponAttacks = character.weaponAttacks
        character.weaponAttacks = mutableListOf()
        for (weaponAttack in weaponAttacks) {
            characterService.createWeaponAttack(character, weaponAttack)
        }
    }

    private fun storeEquipment(character: Character, characterService: CharacterService) {
        val weapons = character.equipment.weapons
        val armor = character.equipment.armor
        val goods = character.equipment.goods
        character.equipment.weapons = emptyList()
        character.equipment.armor = emptyList()
        character.equipment.goods = emptyList()
        characterService.updateWeapons(character, weapons)
        characterService.updateArmor(character, armor)
        characterService.updateGoods(character, goods)
    }

    private fun logEventCharacterCreate(character: Character) {
        val bundle = Bundle()
        bundle.putString(FBAnalytics.Param.RACE_NAME, character.race.name)
        bundle.putString(FBAnalytics.Param.CLASS_NAME, character.characterClasses[0].name)
        firebaseAnalytics.logEvent(FBAnalytics.Event.CHARACTER_CREATE, bundle)
    }

}
