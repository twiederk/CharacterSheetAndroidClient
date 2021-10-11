package com.android.ash.charactersheet.gui.main.charactercreator

import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.boc.service.AndroidImageService
import com.d20charactersheet.framework.boc.model.Race

class RaceScreenViewModel(private val gameSystemHolder: GameSystemHolder) : ViewModel() {

    private var gameSystem = checkNotNull(gameSystemHolder.gameSystem)

    var raceList: MutableState<List<Race>> =
        mutableStateOf(gameSystem.allRaces.sortedBy { it.name })
    var race: MutableState<Race> = mutableStateOf(raceList.value[0])

    fun getBitmap(imageId: Int): Bitmap {
        val imageService = gameSystem.imageService as AndroidImageService
        return imageService.getBitmap(imageId)
    }

    fun onRaceChange(race: Race) {
        this.race.value = race
    }

    fun reset() {
        gameSystem = checkNotNull(gameSystemHolder.gameSystem)

        raceList.value = gameSystem.allRaces.sortedBy { it.name }
        race.value = raceList.value[0]

    }

}