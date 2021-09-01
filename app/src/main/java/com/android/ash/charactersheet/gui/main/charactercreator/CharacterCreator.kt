package com.android.ash.charactersheet.gui.main.charactercreator

import android.os.Bundle
import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.FBAnalytics
import com.android.ash.charactersheet.GameSystemHolder
import com.d20charactersheet.framework.boc.model.Character
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharacterCreator(
    private val characterCreatorAppearance: CharacterCreatorAppearance = CharacterCreatorAppearance(),
    private val characterCreatorEquipment: CharacterCreatorEquipment = CharacterCreatorEquipment()
) : KoinComponent {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val characterHolder: CharacterHolder by inject()
    private val firebaseAnalytics: FirebaseAnalytics by inject()

    fun createCharacter(characterCreatorViewModel: CharacterCreatorViewModel): Character {
        val character = createCharacterInternal(characterCreatorViewModel)
        storeCharacter(character)
        logEventCharacterCreate(character)
        return character
    }

    private fun createCharacterInternal(characterCreatorViewModel: CharacterCreatorViewModel): Character {
        val gameSystem = checkNotNull(gameSystemHolder.gameSystem)
        val character =
            characterCreatorAppearance.fillAppearance(characterCreatorViewModel, gameSystem)
        characterCreatorEquipment.fillEquipment(character, characterCreatorViewModel)
        return character
    }

    private fun storeCharacter(character: Character) {
        val gameSystem = checkNotNull(gameSystemHolder.gameSystem)
        gameSystem.characterService.createCharacter(character, gameSystem.allSkills)
        val weapons = character.equipment.weapons
        val armor = character.equipment.armor
        val goods = character.equipment.goods
        character.equipment.weapons = emptyList()
        character.equipment.armor = emptyList()
        character.equipment.goods = emptyList()
        gameSystem.characterService.updateWeapons(character, weapons)
        gameSystem.characterService.updateArmor(character, armor)
        gameSystem.characterService.updateGoods(character, goods)
        characterHolder.character = character
    }

    private fun logEventCharacterCreate(character: Character) {
        val bundle = Bundle()
        bundle.putString(FBAnalytics.Param.RACE_NAME, character.race.name)
        bundle.putString(FBAnalytics.Param.CLASS_NAME, character.characterClasses[0].name)
        firebaseAnalytics.logEvent(FBAnalytics.Event.CHARACTER_CREATE, bundle)
    }

}
