package com.android.ash.charactersheet.gui.main.charactercreator.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.android.ash.charactersheet.GameSystemHolder
import com.d20charactersheet.framework.boc.model.Race

class RaceScreenViewModel(private val gameSystemHolder: GameSystemHolder) : ViewModel() {

    private var gameSystem = checkNotNull(gameSystemHolder.gameSystem)

    var raceList by mutableStateOf(gameSystem.allRaces.sortedBy { it.name })
    var race by mutableStateOf(raceList[0])

    fun onRaceChange(race: Race) {
        this.race = race
    }

    fun reset() {
        gameSystem = checkNotNull(gameSystemHolder.gameSystem)

        raceList = gameSystem.allRaces.sortedBy { it.name }
        race = raceList[0]
    }

}