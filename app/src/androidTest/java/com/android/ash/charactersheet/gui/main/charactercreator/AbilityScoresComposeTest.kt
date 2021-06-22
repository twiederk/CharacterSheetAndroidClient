package com.android.ash.charactersheet.gui.main.charactercreator

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.gui.theme.D20CharacterSheetTheme
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class AbilityScoresComposeTest {

    @Rule
    @JvmField
    val composeTestRule = createComposeRule()

    @Test
    fun display_attributeRow() {

        // act
        composeTestRule.setContent {
            D20CharacterSheetTheme {
                AbilityScoresScreen(
                    onRollDice = { },
                    onNavigateToPrevious = { },
                    onNavigateToNext = { },
                    onCreateCharacter = { },
                    AttributeRowData(
                        attributeLabel = R.string.attribute_strength,
                        attributeValue = 10,
                        attributeModifier = "0",
                        onIncrease = {},
                        onDecrease = {}
                    ))
            }
        }

        // assert
        composeTestRule.onNodeWithText("Roll Dice (4d6)").assertIsDisplayed()
        composeTestRule.onNodeWithText("Strength").assertIsDisplayed()
        composeTestRule.onNodeWithText("+").assertIsDisplayed()
        composeTestRule.onNodeWithText("10").assertIsDisplayed()
        composeTestRule.onNodeWithText("-").assertIsDisplayed()
        composeTestRule.onNodeWithText("0").assertIsDisplayed()
        composeTestRule.onNodeWithText("PREVIOUS").assertIsDisplayed()
        composeTestRule.onNodeWithText("CREATE CHARACTER").assertIsDisplayed()
    }

    @Test
    fun display_allAttributes() {

        // act
        composeTestRule.setContent {
            D20CharacterSheetTheme {
                AbilityScoresScreen(
                    onRollDice = { },
                    onNavigateToPrevious = { },
                    onNavigateToNext = { },
                    onCreateCharacter = { },
                    AttributeRowData(
                        attributeLabel = R.string.attribute_strength,
                        attributeValue = 10,
                        attributeModifier = "0",
                        onIncrease = { },
                        onDecrease = { }
                    ),
                    AttributeRowData(
                        attributeLabel = R.string.attribute_dexterity,
                        attributeValue = 12,
                        attributeModifier = "+1",
                        onIncrease = { },
                        onDecrease = { }
                    ),
                    AttributeRowData(
                        attributeLabel = R.string.attribute_constitution,
                        attributeValue = 14,
                        attributeModifier = "+2",
                        onIncrease = { },
                        onDecrease = { }
                    ),
                    AttributeRowData(
                        attributeLabel = R.string.attribute_intelligence,
                        attributeValue = 16,
                        attributeModifier = "+3",
                        onIncrease = { },
                        onDecrease = { }
                    ),
                    AttributeRowData(
                        attributeLabel = R.string.attribute_wisdom,
                        attributeValue = 18,
                        attributeModifier = "+4",
                        onIncrease = { },
                        onDecrease = { }
                    ),
                    AttributeRowData(
                        attributeLabel = R.string.attribute_charisma,
                        attributeValue = 20,
                        attributeModifier = "+5",
                        onIncrease = { },
                        onDecrease = { }
                    )
                )
            }
        }

        // assert
        composeTestRule.onNodeWithText("Roll Dice (4d6)").assertIsDisplayed()

        composeTestRule.onNodeWithText("Strength").assertIsDisplayed()
        composeTestRule.onNodeWithText("10").assertIsDisplayed()
        composeTestRule.onNodeWithText("0").assertIsDisplayed()

        composeTestRule.onNodeWithText("Dexterity").assertIsDisplayed()
        composeTestRule.onNodeWithText("12").assertIsDisplayed()
        composeTestRule.onNodeWithText("+1").assertIsDisplayed()

        composeTestRule.onNodeWithText("Constitution").assertIsDisplayed()
        composeTestRule.onNodeWithText("14").assertIsDisplayed()
        composeTestRule.onNodeWithText("+2").assertIsDisplayed()

        composeTestRule.onNodeWithText("Intelligence").assertIsDisplayed()
        composeTestRule.onNodeWithText("16").assertIsDisplayed()
        composeTestRule.onNodeWithText("+3").assertIsDisplayed()

        composeTestRule.onNodeWithText("Wisdom").assertIsDisplayed()
        composeTestRule.onNodeWithText("18").assertIsDisplayed()
        composeTestRule.onNodeWithText("+4").assertIsDisplayed()

        composeTestRule.onNodeWithText("Charisma").assertIsDisplayed()
        composeTestRule.onNodeWithText("20").assertIsDisplayed()
        composeTestRule.onNodeWithText("+5").assertIsDisplayed()

        composeTestRule.onNodeWithText("PREVIOUS").assertIsDisplayed()
        composeTestRule.onNodeWithText("NEXT").assertIsDisplayed()
        composeTestRule.onNodeWithText("CREATE CHARACTER").assertIsDisplayed()
    }

    @Test
    fun navigate_toPreviousScreen() {

        // Arrange
        val onNavigateToPrevious = mock<() -> Unit>()

        // act
        composeTestRule.setContent {
            D20CharacterSheetTheme {
                AbilityScoresScreen(
                    onRollDice = { },
                    onNavigateToPrevious = onNavigateToPrevious,
                    onNavigateToNext = { },
                    onCreateCharacter = { },
                    AttributeRowData(
                        attributeLabel = R.string.attribute_strength,
                        attributeValue = 10,
                        attributeModifier = "0",
                        onIncrease = {},
                        onDecrease = {}
                    ))
            }
        }
        // act
        composeTestRule.onNodeWithText("PREVIOUS").performClick()

        // assert
        verify(onNavigateToPrevious).invoke()
    }

    @Test
    fun navigate_toNextScreen() {

        // Arrange
        val onNavigateToNext = mock<() -> Unit>()

        // act
        composeTestRule.setContent {
            D20CharacterSheetTheme {
                AbilityScoresScreen(
                    onRollDice = { },
                    onNavigateToPrevious = { },
                    onNavigateToNext = onNavigateToNext,
                    onCreateCharacter = { },
                    AttributeRowData(
                        attributeLabel = R.string.attribute_strength,
                        attributeValue = 10,
                        attributeModifier = "0",
                        onIncrease = {},
                        onDecrease = {}
                    ))
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
                AbilityScoresScreen(
                    onRollDice = { },
                    onNavigateToPrevious = { },
                    onNavigateToNext = { },
                    onCreateCharacter = onCreateCharacter,
                    AttributeRowData(
                        attributeLabel = R.string.attribute_strength,
                        attributeValue = 10,
                        attributeModifier = "0",
                        onIncrease = {},
                        onDecrease = {}
                    ))
            }
        }

        // act
        composeTestRule.onNodeWithText("CREATE CHARACTER").performClick()

        // assert
        verify(onCreateCharacter).invoke()
    }

}