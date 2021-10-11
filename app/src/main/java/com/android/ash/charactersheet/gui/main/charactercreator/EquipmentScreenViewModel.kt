package com.android.ash.charactersheet.gui.main.charactercreator

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.android.ash.charactersheet.GameSystemHolder
import com.d20charactersheet.framework.boc.model.CharacterClass
import com.d20charactersheet.framework.boc.model.StarterPackData

class EquipmentScreenViewModel(private val gameSystemHolder: GameSystemHolder) : ViewModel() {

    private var gameSystem = checkNotNull(gameSystemHolder.gameSystem)

    private var characterClassService = gameSystem.characterClassService

    var starterPackBoxViewModels: MutableState<List<StarterPackBoxViewModel>> = mutableStateOf(
        createStarterPackViewModelsOfClass()
    )

    private fun createStarterPackViewModelsOfClass(classId: Int = 1): List<StarterPackBoxViewModel> {
        val gameSystem = checkNotNull(gameSystemHolder.gameSystem)
        val starterPack = checkNotNull(
            characterClassService.getStarterPack(
                CharacterClass().apply { id = classId },
                StarterPackData(
                    gameSystem.itemService,
                    gameSystem.allWeapons,
                    gameSystem.allArmor,
                    gameSystem.allGoods,
                    gameSystem.allEquipmentPacks
                )
            )
        )

        starterPack.starterPackBoxes.sortBy { it.id }
        starterPack.starterPackBoxes.forEach { starterPackBox ->
            starterPackBox.options.sortBy { it.getTitle() }
        }

        return starterPack.starterPackBoxes.map { StarterPackBoxViewModel(it) }.toList()
    }

    fun onClassChange(clazz: CharacterClass) {
        this.starterPackBoxViewModels.value = createStarterPackViewModelsOfClass(clazz.id)
    }


    fun reset(classId: Int) {
        gameSystem = checkNotNull(gameSystemHolder.gameSystem)

        characterClassService = gameSystem.characterClassService

        starterPackBoxViewModels.value = createStarterPackViewModelsOfClass(classId)
    }

}