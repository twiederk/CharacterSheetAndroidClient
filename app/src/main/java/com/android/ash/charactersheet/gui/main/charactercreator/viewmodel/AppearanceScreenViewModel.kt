package com.android.ash.charactersheet.gui.main.charactercreator.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.android.ash.charactersheet.GameSystemHolder
import com.d20charactersheet.framework.boc.model.Alignment
import com.d20charactersheet.framework.boc.model.Sex

class AppearanceScreenViewModel(private val gameSystemHolder: GameSystemHolder) : ViewModel() {

    private var gameSystem = checkNotNull(gameSystemHolder.gameSystem)
    private var displayService = gameSystem.displayService

    val genderList by mutableStateOf(
        (Sex.values().map { checkNotNull(displayService.getDisplaySex(it)) }.toList())
    )
    val alignmentList by mutableStateOf(
        (Alignment.values().map { checkNotNull(displayService.getDisplayAlignment(it)) }.toList())
    )

    var name by mutableStateOf("")
    var player by mutableStateOf("")
    var gender by mutableStateOf(genderList[0])
    var alignment by mutableStateOf(alignmentList[0])

    fun onChangeName(name: String) {
        this.name = name
    }

    fun onChangePlayer(player: String) {
        this.player = player
    }

    fun onGenderChange(gender: String) {
        this.gender = gender
    }

    fun onAlignmentChange(alignment: String) {
        this.alignment = alignment
    }

    fun reset() {
        gameSystem = checkNotNull(gameSystemHolder.gameSystem)

        displayService = gameSystem.displayService

        name = ""
        player = ""
        gender = genderList[0]
        alignment = alignmentList[0]
    }

}
