package com.android.ash.charactersheet.gui.main.charactercreator

import android.graphics.Bitmap
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.android.ash.charactersheet.gui.theme.D20CharacterSheetTheme
import com.d20charactersheet.framework.boc.model.Ability
import com.d20charactersheet.framework.boc.model.Race
import com.d20charactersheet.framework.boc.model.Size
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test

class RaceComposeTest {

    @Rule
    @JvmField
    val composeTestRule = createComposeRule()

    @Test
    fun display_raceScreen() {

        // Arrange
        val raceList = listOf(
            Race().apply {
                id = 1
                name = "firstRace"
                size = Size.MEDIUM
                baseLandSpeed = 30
                abilities = listOf(
                    Ability().apply {
                        name = "Ability Score Increase (+2 Str)"
                        description = "Strength +2"
                    },
                )
            },
            Race().apply {
                id = 2
                name = "secondRace"
                size = Size.SMALL
                baseLandSpeed = 20
                abilities = listOf(
                    Ability().apply {
                        name = "Ability Score Increase (+2 Dex, -2 Con)"
                        description = "Dexterity +2, Constitution -2"
                    },
                )
            }
        )


        // Act
        composeTestRule.setContent {
            D20CharacterSheetTheme {
                RaceScreen(
                    race = raceList[0],
                    raceList = raceList,
                    getBitmap = { Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888) },
                    onRaceChange = { },
                    onNavigateToNext = { }
                )
            }
        }

        // assert
//        composeTestRule.onNodeWithText("firstRace").assertIsDisplayed()
//        composeTestRule.onNodeWithText("Strength +2").assertIsDisplayed()
//        composeTestRule.onNodeWithText("MEDIUM").assertIsDisplayed()
//        composeTestRule.onNodeWithText("20").assertIsDisplayed()
//
//        composeTestRule.onNodeWithText("secondRace").assertIsDisplayed()
//        composeTestRule.onNodeWithText("Dexterity +2, Constitution -2").assertIsDisplayed()
//        composeTestRule.onNodeWithText("SMALL").assertIsDisplayed()
//        composeTestRule.onNodeWithText("30").assertIsDisplayed()

        composeTestRule.onNodeWithText("NEXT").assertIsDisplayed()
    }

    @Test
    fun navigate_ToAbilityScores() {

        // Arrange
        val raceList = listOf(
            Race().apply {
                id = 1
                name = "firstRace"
                size = Size.MEDIUM
                baseLandSpeed = 30
                abilities = listOf(
                    Ability().apply {
                        name = "Ability Score Increase (+2 Str)"
                        description = "Strength +2"
                    },
                )
            }
        )

        val onNavigateToNext = mock<() -> Unit>()

        composeTestRule.setContent {
            D20CharacterSheetTheme {
                RaceScreen(
                    race = raceList[0],
                    raceList = raceList,
                    getBitmap = { Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888) },
                    onRaceChange = { },
                    onNavigateToNext = onNavigateToNext
                )
            }
        }

        // act
        composeTestRule.onNodeWithText("NEXT").performClick()

        // assert
        verify(onNavigateToNext).invoke()
    }

}