package com.android.ash.charactersheet.gui.main.charactercreator.screen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.android.ash.charactersheet.gui.theme.D20CharacterSheetTheme
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class AppearanceScreenComposeTest {

    @Rule
    @JvmField
    val composeTestRule = createComposeRule()

    @Test
    fun display_appearanceScreen() {

        // Act
        composeTestRule.setContent {
            D20CharacterSheetTheme {
                AppearanceScreen(
                    name = "myName",
                    player = "myPlayer",
                    gender = "myGender",
                    genderList = listOf("myGender"),
                    alignment = "myAlignment",
                    alignmentList = listOf("myAlignment"),
                    onChangeName = { },
                    onChangePlayer = { },
                    onGenderChange = { },
                    onAlignmentChange = { },
                    onNavigateToPrevious = { },
                    onNavigateToNext = { }
                ) { }
            }
        }

        // assert
        composeTestRule.onNodeWithText("myName").assertIsDisplayed()
        composeTestRule.onNodeWithText("myGender").assertIsDisplayed()
        composeTestRule.onNodeWithText("myAlignment").assertIsDisplayed()

        composeTestRule.onNodeWithText("NEXT").assertIsDisplayed()
        composeTestRule.onNodeWithText("PREVIOUS").assertIsDisplayed()
        composeTestRule.onNodeWithText("CREATE CHARACTER").assertIsDisplayed()
    }

    @Test
    fun navigate_toNextScreen() {

        // Arrange
        val onNavigateToNext = mock<() -> Unit>()

        composeTestRule.setContent {
            D20CharacterSheetTheme {
                AppearanceScreen(
                    name = "myName",
                    player = "myPlayer",
                    gender = "Male",
                    genderList = listOf("Male", "Female"),
                    alignment = "myAlignment",
                    alignmentList = listOf("myAlignment"),
                    onChangeName = { },
                    onChangePlayer = { },
                    onGenderChange = { },
                    onAlignmentChange = { },
                    onNavigateToPrevious = { },
                    onNavigateToNext = onNavigateToNext,
                    onCreateCharacter = { }
                )
            }
        }

        // act
        composeTestRule.onNodeWithText("NEXT").performClick()

        // assert
        verify(onNavigateToNext).invoke()
    }

    @Test
    fun navigate_toPreviousScreen() {

        // Arrange
        val onNavigateToPrevious = mock<() -> Unit>()

        composeTestRule.setContent {
            D20CharacterSheetTheme {
                AppearanceScreen(
                    name = "myName",
                    player = "myPlayer",
                    gender = "Male",
                    genderList = listOf("Male", "Female"),
                    alignment = "myAlignment",
                    alignmentList = listOf("myAlignment"),
                    onChangeName = { },
                    onChangePlayer = { },
                    onGenderChange = { },
                    onAlignmentChange = { },
                    onNavigateToPrevious = onNavigateToPrevious,
                    onNavigateToNext = { },
                    onCreateCharacter = { }
                )
            }
        }

        // act
        composeTestRule.onNodeWithText("PREVIOUS").performClick()

        // assert
        verify(onNavigateToPrevious).invoke()
    }

    @Test
    fun createCharacter() {

        // Arrange
        val onCreateCharacter = mock<() -> Unit>()

        composeTestRule.setContent {
            D20CharacterSheetTheme {
                AppearanceScreen(
                    name = "myName",
                    player = "myPlayer",
                    gender = "Male",
                    genderList = listOf("Male", "Female"),
                    alignment = "myAlignment",
                    alignmentList = listOf("myAlignment"),
                    onChangeName = { },
                    onChangePlayer = { },
                    onGenderChange = { },
                    onAlignmentChange = { },
                    onNavigateToPrevious = { },
                    onNavigateToNext = { },
                    onCreateCharacter = onCreateCharacter
                )
            }
        }

        // act
        composeTestRule.onNodeWithText("CREATE CHARACTER").performClick()

        // assert
        verify(onCreateCharacter).invoke()
    }

}