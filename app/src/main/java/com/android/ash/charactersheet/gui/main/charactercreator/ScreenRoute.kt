package com.android.ash.charactersheet.gui.main.charactercreator

sealed class ScreenRoute(val route: String) {
    object RaceScreenRoute : ScreenRoute("race_screen_route")
    object ClassScreenRoute : ScreenRoute("class_screen_route")
    object AbilityScoresScreenRoute : ScreenRoute("ability_scores_screen_route")
    object EquipmentScreenRoute : ScreenRoute("equipment_screen_route")
}


