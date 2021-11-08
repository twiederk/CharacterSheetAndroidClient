package com.android.ash.charactersheet.gui.main.charactercreator

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.boc.service.AndroidImageService
import com.android.ash.charactersheet.gui.main.charactercreator.navigation.SetupNavGraph
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.AbilityScoresScreenViewModel
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.AppearanceScreenViewModel
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.ClassScreenViewModel
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.EquipmentScreenViewModel
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.RaceScreenViewModel
import com.android.ash.charactersheet.gui.sheet.CharacterSheetActivity
import com.android.ash.charactersheet.gui.theme.D20CharacterSheetTheme
import com.android.ash.charactersheet.gui.util.MessageManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Activity containing the character creator
 */
class CharacterCreatorActivity : ComponentActivity(), KoinComponent {

    private val raceScreenViewModel: RaceScreenViewModel by inject()
    private val classScreenViewModel: ClassScreenViewModel by inject()
    private val appearanceScreenViewModel: AppearanceScreenViewModel by inject()
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
                    appearanceScreenViewModel = appearanceScreenViewModel,
                    abilityScoresScreenViewModel = abilityScoresScreenViewModel,
                    equipmentScreenViewModel = equipmentScreenViewModel,
                    getBitmap = { getBitmap(it) },
                    onCreateCharacter = { createCharacter() }
                )
            }
        }
    }

    private fun resetViewModels() {
        raceScreenViewModel.reset()
        classScreenViewModel.reset()
        appearanceScreenViewModel.reset()
        abilityScoresScreenViewModel.reset()
        equipmentScreenViewModel.reset(classScreenViewModel.clazz.id)
    }

    private fun createCharacter() {
        try {
            characterCreator.createCharacter(
                raceScreenViewModel,
                classScreenViewModel,
                appearanceScreenViewModel,
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

    private fun getBitmap(imageId: Int): Bitmap {
        val gameSystem = checkNotNull(gameSystemHolder.gameSystem)
        val imageService = gameSystem.imageService as AndroidImageService
        return imageService.getBitmap(imageId)
    }

}