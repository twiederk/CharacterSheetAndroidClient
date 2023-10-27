package com.android.ash.charactersheet.gui.main.charactercreator.navigation

sealed class ScreenRoute(val route: String) {
    data object RaceScreenRoute : ScreenRoute("race_screen_route")
    data object ClassScreenRoute : ScreenRoute("class_screen_route")
    data object AppearanceScreenRoute : ScreenRoute("appearance_screen_route")
    data object AbilityScoresScreenRoute : ScreenRoute("ability_scores_screen_route")
    data object EquipmentScreenRoute : ScreenRoute("equipment_screen_route")
}


