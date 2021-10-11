package com.android.ash.charactersheet.gui.main.charactercreator

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.android.ash.charactersheet.GameSystemHolder
import com.d20charactersheet.framework.boc.model.Alignment
import com.d20charactersheet.framework.boc.model.CharacterClass
import com.d20charactersheet.framework.boc.model.Sex

class ClassScreenViewModel(private val gameSystemHolder: GameSystemHolder) : ViewModel() {

    private var gameSystem = checkNotNull(gameSystemHolder.gameSystem)
    private var displayService = gameSystem.displayService

    var classList: MutableState<List<CharacterClass>> =
        mutableStateOf(gameSystem.allCharacterClasses.sortedBy { it.name })
    val genderList: MutableState<List<String>> = mutableStateOf(
        (Sex.values().map { checkNotNull(displayService.getDisplaySex(it)) }.toList())
    )
    val alignmentList: MutableState<List<String>> = mutableStateOf(
        (Alignment.values().map { checkNotNull(displayService.getDisplayAlignment(it)) }.toList())
    )

    var name: MutableState<String> = mutableStateOf("")
    var player: MutableState<String> = mutableStateOf("")
    var clazz: MutableState<CharacterClass> = mutableStateOf(classList.value[0])
    var gender: MutableState<String> = mutableStateOf(genderList.value[0])
    var alignment: MutableState<String> = mutableStateOf(alignmentList.value[0])

    fun onChangeName(name: String) {
        this.name.value = name
    }

    fun onChangePlayer(player: String) {
        this.player.value = player
    }

    fun onClassChange(clazz: CharacterClass) {
        this.clazz.value = clazz
    }

    fun onGenderChange(gender: String) {
        this.gender.value = gender
    }

    fun onAlignmentChange(alignment: String) {
        this.alignment.value = alignment
    }

    fun reset() {
        gameSystem = checkNotNull(gameSystemHolder.gameSystem)

        displayService = gameSystem.displayService

        classList.value = gameSystem.allCharacterClasses.sortedBy { it.name }

        name.value = ""
        player.value = ""
        clazz.value = classList.value[0]
        gender.value = genderList.value[0]
        alignment.value = alignmentList.value[0]
    }

}