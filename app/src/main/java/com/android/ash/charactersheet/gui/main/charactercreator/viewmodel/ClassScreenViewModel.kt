package com.android.ash.charactersheet.gui.main.charactercreator.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.android.ash.charactersheet.GameSystemHolder
import com.d20charactersheet.framework.boc.model.CharacterClass
import com.d20charactersheet.framework.boc.model.Save
import java.util.*

class ClassScreenViewModel(private val gameSystemHolder: GameSystemHolder) : ViewModel() {

    private var gameSystem = checkNotNull(gameSystemHolder.gameSystem)

    var classList by mutableStateOf(gameSystem.allCharacterClasses.sortedBy { it.name })
    var clazz by mutableStateOf(classList[0])
    var gameSystemType by mutableStateOf(checkNotNull(gameSystemHolder.gameSystemType))

    fun onClassChange(clazz: CharacterClass) {
        this.clazz = clazz
    }

    fun reset() {
        gameSystemType = checkNotNull(gameSystemHolder.gameSystemType)
        gameSystem = checkNotNull(gameSystemHolder.gameSystem)
        classList = gameSystem.allCharacterClasses.sortedBy { it.name }
        clazz = classList[0]
    }

    fun getDisplaySaves(saves: EnumSet<Save>) = gameSystem.displayService.getDisplaySaves(saves)

}