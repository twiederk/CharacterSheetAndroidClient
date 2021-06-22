package com.android.ash.charactersheet.gui.main.charactercreator

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.android.ash.charactersheet.gui.theme.D20CharacterSheetTheme
import com.d20charactersheet.framework.boc.model.ItemGroup
import com.d20charactersheet.framework.boc.model.StarterPackBox
import com.d20charactersheet.framework.boc.model.StarterPackBoxItemOption
import com.d20charactersheet.framework.boc.model.Weapon
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class EquipmentComposeTest {

    @Rule
    @JvmField
    val composeTestRule = createComposeRule()

    @Test
    fun navigate_toPreviousScreen() {

        // Arrange
        val onNavigateToPrevious = mock<() -> Unit>()

        // act
        composeTestRule.setContent {
            D20CharacterSheetTheme {
                EquipmentScreen(
                    starterPackBoxViewModels = listOf(),
                    onNavigateToPrevious = onNavigateToPrevious,
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
                EquipmentScreen(
                    starterPackBoxViewModels = listOf(),
                    onNavigateToPrevious = { },
                    onCreateCharacter = onCreateCharacter
                )
            }
        }

        // act
        composeTestRule.onNodeWithText("CREATE CHARACTER").performClick()

        // assert
        verify(onCreateCharacter).invoke()
    }

    @Test
    fun display_equipmentScreenWithStarterBox() {

        // Arrange
        val starterPackBoxViewModels = listOf(
            StarterPackBoxViewModel(
                StarterPackBox(id = 1, name = "Primary Hand").also {
                    it.add(
                        StarterPackBoxItemOption().apply {
                            add(ItemGroup().apply {
                                this.item = Weapon().apply { id = 1; name = "myWeapon" }
                                number = 1
                            })
                        }
                    )
                }
            )
        )

        // Act
        composeTestRule.setContent {
            D20CharacterSheetTheme {
                EquipmentScreen(
                    starterPackBoxViewModels = starterPackBoxViewModels,
                    onNavigateToPrevious = { },
                    onCreateCharacter = { }
                )
            }
        }

        // assert
        composeTestRule.onNodeWithText("Primary Hand").assertIsDisplayed()
        composeTestRule.onNodeWithText("myWeapon").assertIsDisplayed()

        composeTestRule.onNodeWithText("PREVIOUS").assertIsDisplayed()
        composeTestRule.onNodeWithText("CREATE CHARACTER").assertIsDisplayed()
    }

    @Test
    fun display_equipmentScreenWithoutStarterBox() {

        // Act
        composeTestRule.setContent {
            D20CharacterSheetTheme {
                EquipmentScreen(
                    starterPackBoxViewModels = listOf(),
                    onNavigateToPrevious = { },
                    onCreateCharacter = { }
                )
            }
        }

        // assert
        composeTestRule.onNodeWithText("The starting equipment for this class is missing. If you have suggestions for the starting equipment, please send them to torsten.wiederkehr@gmail.com.")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("PREVIOUS").assertIsDisplayed()
        composeTestRule.onNodeWithText("CREATE CHARACTER").assertIsDisplayed()
    }

}