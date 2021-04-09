package com.android.ash.charactersheet.gui.main.charactercreator

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test

class RaceAndClassComposeTest {

    @Rule
    @JvmField
    val composeTestRule = createComposeRule()

    @Test
    fun display_raceAndClassScreen() {

        // Act
        composeTestRule.setContent {
            MaterialTheme {
                RaceAndClassScreen(
                    name = "myName",
                    player = "myPlayer",
                    race = "myRace",
                    raceList = listOf("myRace"),
                    clazz = "myClass",
                    classList = listOf("myClass"),
                    sex = "mySex",
                    sexList = listOf("mySex"),
                    alignment = "myAlignment",
                    alignmentList = listOf("myAlignment"),

                    onChangeName = { },
                    onChangePlayer = { },
                    onRaceChange = { },
                    onSexChange = { },
                    onAlignmentChange = { },
                    onClassChange = { },
                    onNavigateToRaceAndClassFragment = { },
                    onCreateCharacter = { }
                )
            }
        }

        // assert
        composeTestRule.onNodeWithText("Race and Class").assertIsDisplayed()

        composeTestRule.onNodeWithText("myName").assertIsDisplayed()
        composeTestRule.onNodeWithText("myRace").assertIsDisplayed()
        composeTestRule.onNodeWithText("myClass").assertIsDisplayed()
        composeTestRule.onNodeWithText("mySex").assertIsDisplayed()
        composeTestRule.onNodeWithText("myAlignment").assertIsDisplayed()

        composeTestRule.onNodeWithText("Ability Scores >").assertIsDisplayed()
        composeTestRule.onNodeWithText("Create Character").assertIsDisplayed()
    }

    @Test
    fun navigate_ToAbilityScores() {

        // Arrange
        val onNavigateToRaceAndClassFragment = mock<() -> Unit>()

        composeTestRule.setContent {
            MaterialTheme {
                RaceAndClassScreen(
                    name = "myName",
                    player = "myPlayer",
                    race = "myRace",
                    raceList = listOf("myRace"),
                    clazz = "myClass",
                    classList = listOf("myClass"),
                    sex = "mySex",
                    sexList = listOf("mySex"),
                    alignment = "myAlignment",
                    alignmentList = listOf("myAlignment"),

                    onChangeName = { },
                    onChangePlayer = { },
                    onRaceChange = { },
                    onSexChange = { },
                    onAlignmentChange = { },
                    onClassChange = { },
                    onNavigateToRaceAndClassFragment = onNavigateToRaceAndClassFragment,
                    onCreateCharacter = { }
                )
            }
        }

        // act
        composeTestRule.onNodeWithText("Ability Scores >").performClick()

        // assert
        verify(onNavigateToRaceAndClassFragment).invoke()
    }

    @Test
    fun createCharacter() {

        // Arrange
        val onCreateCharacter = mock<() -> Unit>()

        composeTestRule.setContent {
            MaterialTheme {
                RaceAndClassScreen(
                    name = "myName",
                    player = "myPlayer",
                    race = "myRace",
                    raceList = listOf("myRace"),
                    clazz = "myClass",
                    classList = listOf("myClass"),
                    sex = "mySex",
                    sexList = listOf("mySex"),
                    alignment = "myAlignment",
                    alignmentList = listOf("myAlignment"),

                    onChangeName = { },
                    onChangePlayer = { },
                    onRaceChange = { },
                    onSexChange = { },
                    onAlignmentChange = { },
                    onClassChange = { },
                    onNavigateToRaceAndClassFragment = { },
                    onCreateCharacter = onCreateCharacter
                )
            }
        }

        // act
        composeTestRule.onNodeWithText("Create Character").performClick()

        // assert
        verify(onCreateCharacter).invoke()
    }

}