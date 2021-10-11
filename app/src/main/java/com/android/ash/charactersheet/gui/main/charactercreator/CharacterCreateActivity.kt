package com.android.ash.charactersheet.gui.main.charactercreator

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.gui.sheet.CharacterSheetActivity
import com.android.ash.charactersheet.gui.theme.D20CharacterSheetTheme
import com.android.ash.charactersheet.gui.util.LogAppCompatActivity
import com.android.ash.charactersheet.gui.util.MessageManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Activity containing the character creator
 */
class CharacterCreateActivity : LogAppCompatActivity(), KoinComponent {

    private val raceScreenViewModel: RaceScreenViewModel by inject()
    private val classScreenViewModel: ClassScreenViewModel by inject()
    private val abilityScoresScreenViewModel: AbilityScoresScreenViewModel by inject()
    private val equipmentScreenViewModel: EquipmentScreenViewModel by inject()

    private val gameSystemHolder: GameSystemHolder by inject()
    private val characterCreator: CharacterCreator by inject()

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        resetViewModels()

        setContent {
            D20CharacterSheetTheme {
                navController = rememberNavController()
                SetupNavGraph(
                    navController = navController,
                    raceScreenViewModel = raceScreenViewModel,
                    classScreenViewModel = classScreenViewModel,
                    abilityScoresScreenViewModel = abilityScoresScreenViewModel,
                    equipmentScreenViewModel = equipmentScreenViewModel
                ) { createCharacter() }
            }
        }
    }

    private fun resetViewModels() {
        raceScreenViewModel.reset()
        classScreenViewModel.reset()
        abilityScoresScreenViewModel.reset()
        equipmentScreenViewModel.reset(classScreenViewModel.clazz.value.id)
    }

    fun createCharacter() {
        try {
            characterCreator.createCharacter(
                raceScreenViewModel,
                classScreenViewModel,
                abilityScoresScreenViewModel,
                equipmentScreenViewModel
            )
            jumpToCharacterSheet()
        } catch (exception: Exception) {
            MessageManager(this, gameSystemHolder.gameSystem?.displayService).showErrorMessage(
                exception
            )
        }
    }

    private fun jumpToCharacterSheet() {
        val intent = Intent(this, CharacterSheetActivity::class.java)
        startActivity(intent)
        this.finish()
    }

}