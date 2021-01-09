package com.android.ash.charactersheet.gui.main.charactercreator

import android.os.Bundle
import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.FBAnalytics
import com.android.ash.charactersheet.GameSystemHolder
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.model.ClassLevel
import com.d20charactersheet.framework.boc.service.ImageService
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.core.KoinComponent
import org.koin.core.inject


class CharacterCreator(val characterData: CharacterData = CharacterData()) : KoinComponent {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val characterHolder: CharacterHolder by inject()
    private val firebaseAnalytics: FirebaseAnalytics by inject()


    fun createCharacter(): Character {
        val character = createCharacterInternal()
        storeCharacter(character)
        logEventCharacterCreate(character)
        return character
    }

    private fun createCharacterInternal(): Character {
        return Character().apply {
            name = characterData.name
            player = characterData.player
            race = characterData.race
            classLevels = listOf(ClassLevel(characterData.clazz, 1))
            sex = characterData.sex
            alignment = characterData.alignment
            xpTable = gameSystemHolder.gameSystem?.allXpTables?.get(0)
            strength = characterData.strength
            dexterity = characterData.dexterity
            constitution = characterData.constitution
            intelligence = characterData.intelligence
            wisdom = characterData.wisdom
            charisma = characterData.charisma
            imageId = ImageService.DEFAULT_CHARACTER_IMAGE_ID
            thumbImageId = ImageService.DEFAULT_THUMB_IMAGE_ID
        }

    }

    private fun storeCharacter(character: Character) {
        val gameSystem = gameSystemHolder.gameSystem
        gameSystem?.characterService?.createCharacter(character, gameSystem.allSkills)
        characterHolder.character = character
    }

    private fun logEventCharacterCreate(character: Character) {
        val bundle = Bundle()
        bundle.putString(FBAnalytics.Param.RACE_NAME, character.race.name)
        bundle.putString(FBAnalytics.Param.CLASS_NAME, character.characterClasses[0].name)
        firebaseAnalytics.logEvent(FBAnalytics.Event.CHARACTER_CREATE, bundle)
    }

}
