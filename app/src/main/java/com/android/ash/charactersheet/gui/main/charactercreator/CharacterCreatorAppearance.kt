package com.android.ash.charactersheet.gui.main.charactercreator

import com.d20charactersheet.framework.boc.model.Alignment
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.model.ClassLevel
import com.d20charactersheet.framework.boc.model.Sex
import com.d20charactersheet.framework.boc.service.GameSystem
import com.d20charactersheet.framework.boc.service.ImageService
import org.koin.core.component.KoinApiExtension
import java.util.*

@KoinApiExtension
class CharacterCreatorAppearance {

    fun fillAppearance(
        characterCreatorViewModel: CharacterCreatorViewModel,
        gameSystem: GameSystem
    ): Character {
        return Character().apply {
            name = characterCreatorViewModel.name
            player = characterCreatorViewModel.player
            race = characterCreatorViewModel.race
            val clazz = gameSystem.characterClassService.findClassByName(
                characterCreatorViewModel.clazz.name,
                gameSystem.allCharacterClasses
            )
            classLevels = listOf(ClassLevel(clazz, 1))
            sex = Sex.valueOf(characterCreatorViewModel.gender.uppercase(Locale.ROOT))
            alignment = Alignment.valueOf(
                characterCreatorViewModel.alignment.uppercase(Locale.ROOT).replace(" ", "_")
            )
            xpTable = gameSystem.allXpTables[0]
            strength = characterCreatorViewModel.strength
            dexterity = characterCreatorViewModel.dexterity
            constitution = characterCreatorViewModel.constitution
            intelligence = characterCreatorViewModel.intelligence
            wisdom = characterCreatorViewModel.wisdom
            charisma = characterCreatorViewModel.charisma
            imageId = ImageService.DEFAULT_CHARACTER_IMAGE_ID
            thumbImageId = ImageService.DEFAULT_THUMB_IMAGE_ID
        }
    }

}