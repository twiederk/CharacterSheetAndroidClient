package com.android.ash.charactersheet.gui.main.charactercreator

import com.d20charactersheet.framework.boc.model.Alignment
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.model.ClassLevel
import com.d20charactersheet.framework.boc.model.Sex
import com.d20charactersheet.framework.boc.service.GameSystem
import com.d20charactersheet.framework.boc.service.ImageService
import java.util.*

class CharacterCreatorAppearance {

    fun fillAppearance(
        raceScreenViewModel: RaceScreenViewModel,
        classScreenViewModel: ClassScreenViewModel,
        abilityScoresScreenViewModel: AbilityScoresScreenViewModel,
        gameSystem: GameSystem
    ): Character {
        return Character().apply {
            name = classScreenViewModel.name.value
            player = classScreenViewModel.player.value
            race = raceScreenViewModel.race.value
            val clazz = gameSystem.characterClassService.findClassByName(
                classScreenViewModel.clazz.value.name,
                gameSystem.allCharacterClasses
            )
            classLevels = listOf(ClassLevel(clazz, 1))
            sex = Sex.valueOf(classScreenViewModel.gender.value.uppercase(Locale.ROOT))
            alignment = Alignment.valueOf(
                classScreenViewModel.alignment.value.uppercase(Locale.ROOT).replace(" ", "_")
            )
            xpTable = gameSystem.allXpTables[0]
            strength = abilityScoresScreenViewModel.strength
            dexterity = abilityScoresScreenViewModel.dexterity
            constitution = abilityScoresScreenViewModel.constitution
            intelligence = abilityScoresScreenViewModel.intelligence
            wisdom = abilityScoresScreenViewModel.wisdom
            charisma = abilityScoresScreenViewModel.charisma
            imageId = ImageService.DEFAULT_CHARACTER_IMAGE_ID
            thumbImageId = ImageService.DEFAULT_THUMB_IMAGE_ID
        }
    }

}