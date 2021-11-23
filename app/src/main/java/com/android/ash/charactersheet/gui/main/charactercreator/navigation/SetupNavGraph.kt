package com.android.ash.charactersheet.gui.main.charactercreator.navigation

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.gui.main.charactercreator.screen.AbilityScoresScreen
import com.android.ash.charactersheet.gui.main.charactercreator.screen.AppearanceScreen
import com.android.ash.charactersheet.gui.main.charactercreator.screen.AttributeRowData
import com.android.ash.charactersheet.gui.main.charactercreator.screen.ClassScreen
import com.android.ash.charactersheet.gui.main.charactercreator.screen.EquipmentScreen
import com.android.ash.charactersheet.gui.main.charactercreator.screen.RaceScreen
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.AbilityScoresScreenViewModel
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.AppearanceScreenViewModel
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.ClassScreenViewModel
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.EquipmentScreenViewModel
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.RaceScreenViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    raceScreenViewModel: RaceScreenViewModel,
    classScreenViewModel: ClassScreenViewModel,
    appearanceScreenViewModel: AppearanceScreenViewModel,
    abilityScoresScreenViewModel: AbilityScoresScreenViewModel,
    equipmentScreenViewModel: EquipmentScreenViewModel,
    getBitmap: (Int) -> Bitmap,
    onCreateCharacter: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.RaceScreenRoute.route
    ) {
        composable(
            route = ScreenRoute.RaceScreenRoute.route
        ) {
            RaceScreen(
                race = raceScreenViewModel.race,
                raceList = raceScreenViewModel.raceList,
                getBitmap = { getBitmap(it) },
                onRaceChange = { raceScreenViewModel.onRaceChange(it) },
                onNavigateToNext = { navController.navigate(route = ScreenRoute.ClassScreenRoute.route) }
            )
        }
        composable(
            route = ScreenRoute.ClassScreenRoute.route
        ) {
            ClassScreen(
                clazz = classScreenViewModel.clazz,
                classList = classScreenViewModel.classList,
                gameSystemType = classScreenViewModel.gameSystemType,
                getBitmap = { getBitmap(it) },
                getDisplaySaves = { classScreenViewModel.getDisplaySaves(it) },
                onClassChange = {
                    classScreenViewModel.onClassChange(it)
                    equipmentScreenViewModel.onClassChange(it)
                },
                onNavigateToPrevious = { navController.popBackStack() },
                onNavigateToNext = { navController.navigate(route = ScreenRoute.AppearanceScreenRoute.route) },
                onCreateCharacter = { onCreateCharacter() })
        }
        composable(
            route = ScreenRoute.AppearanceScreenRoute.route
        ) {
            AppearanceScreen(
                name = appearanceScreenViewModel.name,
                player = appearanceScreenViewModel.player,
                gender = appearanceScreenViewModel.gender,
                genderList = appearanceScreenViewModel.genderList,
                alignment = appearanceScreenViewModel.alignment,
                alignmentList = appearanceScreenViewModel.alignmentList,
                onChangeName = { appearanceScreenViewModel.onChangeName(it) },
                onChangePlayer = { appearanceScreenViewModel.onChangePlayer(it) },
                onGenderChange = { appearanceScreenViewModel.onGenderChange(it) },
                onAlignmentChange = { appearanceScreenViewModel.onAlignmentChange(it) },
                onNavigateToPrevious = { navController.popBackStack() },
                onNavigateToNext = { navController.navigate(route = ScreenRoute.AbilityScoresScreenRoute.route) },
                onCreateCharacter = { onCreateCharacter() }
            )
        }
        composable(
            route = ScreenRoute.AbilityScoresScreenRoute.route
        ) {
            AbilityScoresScreen(
                onRollDice = { abilityScoresScreenViewModel.onRollDice() },
                onNavigateToPrevious = { navController.popBackStack() },
                onNavigateToNext = { navController.navigate(route = ScreenRoute.EquipmentScreenRoute.route) },
                onCreateCharacter = { onCreateCharacter() },
                AttributeRowData(
                    attributeLabel = R.string.attribute_strength,
                    attributeValue = abilityScoresScreenViewModel.strength,
                    attributeModifier = abilityScoresScreenViewModel.strengthModifier,
                    onIncrease = { abilityScoresScreenViewModel.onIncreaseStrength() },
                    onDecrease = { abilityScoresScreenViewModel.onDecreaseStrength() }
                ),
                AttributeRowData(
                    attributeLabel = R.string.attribute_dexterity,
                    attributeValue = abilityScoresScreenViewModel.dexterity,
                    attributeModifier = abilityScoresScreenViewModel.dexterityModifier,
                    onIncrease = { abilityScoresScreenViewModel.onIncreaseDexterity() },
                    onDecrease = { abilityScoresScreenViewModel.onDecreaseDexterity() }
                ),
                AttributeRowData(
                    attributeLabel = R.string.attribute_constitution,
                    attributeValue = abilityScoresScreenViewModel.constitution,
                    attributeModifier = abilityScoresScreenViewModel.constitutionModifier,
                    onIncrease = { abilityScoresScreenViewModel.onIncreaseConstitution() },
                    onDecrease = { abilityScoresScreenViewModel.onDecreaseConstitution() }
                ),
                AttributeRowData(
                    attributeLabel = R.string.attribute_intelligence,
                    attributeValue = abilityScoresScreenViewModel.intelligence,
                    attributeModifier = abilityScoresScreenViewModel.intelligenceModifier,
                    onIncrease = { abilityScoresScreenViewModel.onIncreaseIntelligence() },
                    onDecrease = { abilityScoresScreenViewModel.onDecreaseIntelligence() }
                ),
                AttributeRowData(
                    attributeLabel = R.string.attribute_wisdom,
                    attributeValue = abilityScoresScreenViewModel.wisdom,
                    attributeModifier = abilityScoresScreenViewModel.wisdomModifier,
                    onIncrease = { abilityScoresScreenViewModel.onIncreaseWisdom() },
                    onDecrease = { abilityScoresScreenViewModel.onDecreaseWisdom() }
                ),
                AttributeRowData(
                    attributeLabel = R.string.attribute_charisma,
                    attributeValue = abilityScoresScreenViewModel.charisma,
                    attributeModifier = abilityScoresScreenViewModel.charismaModifier,
                    onIncrease = { abilityScoresScreenViewModel.onIncreaseCharisma() },
                    onDecrease = { abilityScoresScreenViewModel.onDecreaseCharisma() }
                )
            )
        }
        composable(
            route = ScreenRoute.EquipmentScreenRoute.route
        ) {
            EquipmentScreen(
                starterPackBoxViewModels = equipmentScreenViewModel.starterPackBoxViewModels,
                onNavigateToPrevious = { navController.popBackStack() },
                onCreateCharacter = { onCreateCharacter() }
            )
        }
    }

}
