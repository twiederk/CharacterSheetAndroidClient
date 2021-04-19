package com.android.ash.charactersheet.gui.main.charactercreator

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.android.ash.charactersheet.gui.theme.D20CharacterSheetTheme
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test

class ClassComposeTest {

    @Rule
    @JvmField
    val composeTestRule = createComposeRule()

    @Test
    fun display_classScreen() {

        // Act
        composeTestRule.setContent {
            D20CharacterSheetTheme {
                ClassScreen(
                    name = "myName",
                    player = "myPlayer",
                    clazz = "myClass",
                    classList = listOf("myClass"),
                    gender = "myGender",
                    genderList = listOf("myGender"),
                    alignment = "myAlignment",
                    alignmentList = listOf("myAlignment"),
                    onChangeName = { },
                    onChangePlayer = { },
                    onGenderChange = { },
                    onAlignmentChange = { },
                    onClassChange = { },
                    onNavigateToPrevious = { },
                    onNavigateToNext = { }
                ) { }
            }
        }

        // assert
        composeTestRule.onNodeWithText("myName").assertIsDisplayed()
        composeTestRule.onNodeWithText("myClass").assertIsDisplayed()
        composeTestRule.onNodeWithText("myGender").assertIsDisplayed()
        composeTestRule.onNodeWithText("myAlignment").assertIsDisplayed()

        composeTestRule.onNodeWithText("NEXT").assertIsDisplayed()
        composeTestRule.onNodeWithText("PREVIOUS").assertIsDisplayed()
        composeTestRule.onNodeWithText("CREATE CHARACTER").assertIsDisplayed()
    }

    @Test
    fun navigate_ToAbilityScores() {

        // Arrange
        val onNavigateToNext = mock<() -> Unit>()

        composeTestRule.setContent {
            D20CharacterSheetTheme {
                ClassScreen(
                    name = "myName",
                    player = "myPlayer",
                    clazz = "myClass",
                    classList = listOf("myClass"),
                    gender = "Male",
                    genderList = listOf("Male", "Female"),
                    alignment = "myAlignment",
                    alignmentList = listOf("myAlignment"),
                    onChangeName = { },
                    onChangePlayer = { },
                    onGenderChange = { },
                    onAlignmentChange = { },
                    onClassChange = { },
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
    fun createCharacter() {

        // Arrange
        val onCreateCharacter = mock<() -> Unit>()

        composeTestRule.setContent {
            D20CharacterSheetTheme {
                ClassScreen(
                    name = "myName",
                    player = "myPlayer",
                    clazz = "myClass",
                    classList = listOf("myClass"),
                    gender = "Male",
                    genderList = listOf("Male", "Female"),
                    alignment = "myAlignment",
                    alignmentList = listOf("myAlignment"),
                    onChangeName = { },
                    onChangePlayer = { },
                    onGenderChange = { },
                    onAlignmentChange = { },
                    onClassChange = { },
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