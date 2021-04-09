package com.android.ash.charactersheet.gui.main.charactercreator

import android.os.Bundle
import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.FBAnalytics
import com.android.ash.charactersheet.GameSystemHolder
import com.d20charactersheet.framework.boc.model.Alignment
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.model.ClassLevel
import com.d20charactersheet.framework.boc.model.Sex
import com.d20charactersheet.framework.boc.service.ImageService
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*

@KoinApiExtension
class CharacterCreator : KoinComponent {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val characterHolder: CharacterHolder by inject()
    private val firebaseAnalytics: FirebaseAnalytics by inject()

    fun createCharacter(characterCreatorViewModel: CharacterCreatorViewModel): Character {
        val character = createCharacterInternal(characterCreatorViewModel)
        storeCharacter(character)
        logEventCharacterCreate(character)
        return character
    }

    private fun createCharacterInternal(characterCreatorViewModel: CharacterCreatorViewModel) = Character().apply {
        val gameSystem = gameSystemHolder.gameSystem
        name = characterCreatorViewModel.name
        player = characterCreatorViewModel.player
        race = gameSystem?.raceService?.findRaceByName(characterCreatorViewModel.race, gameSystem.allRaces)
        val clazz = gameSystem?.characterClassService?.findClassByName(characterCreatorViewModel.clazz, gameSystem.allCharacterClasses)
        classLevels = listOf(ClassLevel(clazz, 1))
        sex = Sex.valueOf(characterCreatorViewModel.sex.toUpperCase(Locale.ROOT))
        alignment = Alignment.valueOf(characterCreatorViewModel.alignment.toUpperCase(Locale.ROOT).replace(" ", "_"))
        xpTable = gameSystem?.allXpTables?.get(0)
        strength = characterCreatorViewModel.strength
        dexterity = characterCreatorViewModel.dexterity
        constitution = characterCreatorViewModel.constitution
        intelligence = characterCreatorViewModel.intelligence
        wisdom = characterCreatorViewModel.wisdom
        charisma = characterCreatorViewModel.charisma
        imageId = ImageService.DEFAULT_CHARACTER_IMAGE_ID
        thumbImageId = ImageService.DEFAULT_THUMB_IMAGE_ID
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
