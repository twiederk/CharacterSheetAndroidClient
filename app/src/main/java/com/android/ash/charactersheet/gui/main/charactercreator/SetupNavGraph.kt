package com.android.ash.charactersheet.gui.main.charactercreator

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.ash.charactersheet.R

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    raceScreenViewModel: RaceScreenViewModel,
    classScreenViewModel: ClassScreenViewModel,
    abilityScoresScreenViewModel: AbilityScoresScreenViewModel,
    equipmentScreenViewModel: EquipmentScreenViewModel,
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
                race = raceScreenViewModel.race.value,
                raceList = raceScreenViewModel.raceList.value,
                getBitmap = { raceScreenViewModel.getBitmap(it) },
                onRaceChange = { raceScreenViewModel.onRaceChange(it) },
                onNavigateToNext = { navController.navigate(route = ScreenRoute.ClassScreenRoute.route) }
            )
        }
        composable(
            route = ScreenRoute.ClassScreenRoute.route
        ) {
            ClassScreen(
                name = classScreenViewModel.name.value,
                player = classScreenViewModel.player.value,
                clazz = classScreenViewModel.clazz.value,
                classList = classScreenViewModel.classList.value,
                gender = classScreenViewModel.gender.value,
                genderList = classScreenViewModel.genderList.value,
                alignment = classScreenViewModel.alignment.value,
                alignmentList = classScreenViewModel.alignmentList.value,
                onChangeName = { classScreenViewModel.onChangeName(it) },
                onChangePlayer = { classScreenViewModel.onChangePlayer(it) },
                onGenderChange = { classScreenViewModel.onGenderChange(it) },
                onAlignmentChange = { classScreenViewModel.onAlignmentChange(it) },
                onClassChange = {
                    classScreenViewModel.onClassChange(it)
                    equipmentScreenViewModel.onClassChange(it)
                },
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
                starterPackBoxViewModels = equipmentScreenViewModel.starterPackBoxViewModels.value,
                onNavigateToPrevious = { navController.popBackStack() },
                onCreateCharacter = { onCreateCharacter() }
            )
        }
    }

}
