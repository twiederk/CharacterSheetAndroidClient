package com.android.ash.charactersheet.gui.main.charactercreator.screen

import android.graphics.Bitmap
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.android.ash.charactersheet.gui.theme.D20CharacterSheetTheme
import com.d20charactersheet.framework.boc.model.BaseAttackBonus
import com.d20charactersheet.framework.boc.model.CharacterClass
import com.d20charactersheet.framework.boc.model.Die
import com.d20charactersheet.framework.boc.model.Save
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import java.util.*

class ClassScreenComposeTest {

    @Rule
    @JvmField
    val composeTestRule = createComposeRule()

    private val clazzDnD5e = CharacterClass().apply {
        id = 1
        name = "myDnD5eClass"
        hitDie = Die.D8
        saves = EnumSet.of(Save.STRENGTH, Save.CONSTITUTION)
        baseAttackBonus = BaseAttackBonus.POOR
        skillPointsPerLevel = -1
    }

    private val clazzDnDv35 = CharacterClass().apply {
        id = 1
        name = "myDnDv35Class"
        hitDie = Die.D10
        saves = EnumSet.of(Save.STRENGTH, Save.CONSTITUTION)
        baseAttackBonus = BaseAttackBonus.GOOD
        skillPointsPerLevel = 4
    }

    @Test
    fun display_classScreenDnD5e() {

        // act
        composeTestRule.setContent {
            D20CharacterSheetTheme {
                ClassScreen(
                    clazz = clazzDnD5e,
                    classList = listOf(clazzDnD5e),
                    gameSystemType = GameSystemType.DND5E,
                    getBitmap = { Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888) },
                    getDisplaySaves = { "Strength, Constitution" },
                    onClassChange = { },
                    onNavigateToPrevious = { },
                    onNavigateToNext = { },
                    onCreateCharacter = { })
            }
        }

        Thread.sleep(5000)

        // assert
        composeTestRule.onNodeWithText("myDnD5eClass").assertIsDisplayed()
        composeTestRule.onNodeWithText("Hit Die: D8").assertIsDisplayed()
        composeTestRule.onNodeWithText("Saves: Strength, Constitution").assertIsDisplayed()
        composeTestRule.onNodeWithText("Base Attack Bonus: POOR").assertDoesNotExist()
        composeTestRule.onNodeWithText("Skill Points Per Level: -1").assertDoesNotExist()

        composeTestRule.onNodeWithText("NEXT").assertIsDisplayed()
        composeTestRule.onNodeWithText("PREVIOUS").assertIsDisplayed()
        composeTestRule.onNodeWithText("CREATE CHARACTER").assertIsDisplayed()
    }

    @Test
    fun display_classScreenDnDv35() {

        // act
        composeTestRule.setContent {
            D20CharacterSheetTheme {
                ClassScreen(
                    clazz = clazzDnDv35,
                    classList = listOf(clazzDnDv35),
                    gameSystemType = GameSystemType.DNDV35,
                    getBitmap = { Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888) },
                    getDisplaySaves = { "Fortitude, Will" },
                    onClassChange = { },
                    onNavigateToPrevious = { },
                    onNavigateToNext = { },
                    onCreateCharacter = { })
            }
        }

        // assert
        composeTestRule.onNodeWithText("myDnDv35Class").assertIsDisplayed()
        composeTestRule.onNodeWithText("Hit Die: D10").assertIsDisplayed()
        composeTestRule.onNodeWithText("Saves: Fortitude, Will").assertIsDisplayed()
        composeTestRule.onNodeWithText("Base Attack Bonus: GOOD").assertIsDisplayed()
        composeTestRule.onNodeWithText("Skill Points Per Level: 4").assertIsDisplayed()

        composeTestRule.onNodeWithText("NEXT").assertIsDisplayed()
        composeTestRule.onNodeWithText("PREVIOUS").assertIsDisplayed()
        composeTestRule.onNodeWithText("CREATE CHARACTER").assertIsDisplayed()
    }

    @Test
    fun navigate_toNextScreen() {

        // arrange
        val onNavigateToNext = mock<() -> Unit>()

        composeTestRule.setContent {
            D20CharacterSheetTheme {
                ClassScreen(
                    clazz = clazzDnDv35,
                    classList = listOf(clazzDnDv35),
                    gameSystemType = GameSystemType.DNDV35,
                    getBitmap = { Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888) },
                    getDisplaySaves = { "" },
                    onClassChange = { },
                    onNavigateToPrevious = { },
                    onNavigateToNext = onNavigateToNext,
                    onCreateCharacter = { })
            }
        }

        // act
        composeTestRule.onNodeWithText("NEXT").performClick()

        // assert
        verify(onNavigateToNext).invoke()
    }

    @Test
    fun navigate_toPreviousScreen() {

        // arrange
        val onNavigateToPrevious = mock<() -> Unit>()

        composeTestRule.setContent {
            D20CharacterSheetTheme {
                ClassScreen(
                    clazz = clazzDnDv35,
                    classList = listOf(clazzDnDv35),
                    gameSystemType = GameSystemType.DNDV35,
                    getBitmap = { Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888) },
                    getDisplaySaves = { "" },
                    onClassChange = { },
                    onNavigateToPrevious = onNavigateToPrevious,
                    onNavigateToNext = { },
                    onCreateCharacter = { })
            }
        }

        // act
        composeTestRule.onNodeWithText("PREVIOUS").performClick()

        // assert
        verify(onNavigateToPrevious).invoke()
    }

    @Test
    fun createCharacter() {

        // arrange
        val onCreateCharacter = mock<() -> Unit>()

        composeTestRule.setContent {
            D20CharacterSheetTheme {
                ClassScreen(
                    clazz = clazzDnDv35,
                    classList = listOf(clazzDnDv35),
                    gameSystemType = GameSystemType.DNDV35,
                    getBitmap = { Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888) },
                    getDisplaySaves = { "" },
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