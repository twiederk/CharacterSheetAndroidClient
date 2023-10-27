package com.android.ash.charactersheet.gui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun D20CharacterSheetTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = lightColors,
        content = content
    )
}

private val lightColors = lightColors(
    primary = PrimaryColor,
    primaryVariant = PrimaryDarkColor,
    onPrimary = PrimaryTextColor,
    secondary = SecondaryColor,
    secondaryVariant = SecondaryDarkColor,
    onSecondary = SecondaryTextColor,
    error = Color.Red
)
