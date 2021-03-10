package com.android.ash.charactersheet.gui.main.charactercreator

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.android.ash.charactersheet.R
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class AbilityScoresComposeFragmentEspressoTest : KoinTest {

    private val characterCreatorViewModel: CharacterCreatorViewModel by inject()

    @Rule
    @JvmField
    val composeTestRule = createComposeRule()

    @Test
    fun display_attributeRow() {

        // act
        composeTestRule.setContent {
            MaterialTheme {
                AbilityScoresScreen(
                    onRollDice = { },
                    onNavigateToRaceAndClassFragment = { },
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
        composeTestRule.onNodeWithText("< Race and Class").assertIsDisplayed()
        composeTestRule.onNodeWithText("Create Character").assertIsDisplayed()
    }

    @Test
    fun display_allAttributes() {

        // act
        composeTestRule.setContent {
            MaterialTheme {
                AbilityScoresScreen(
                    onRollDice = { characterCreatorViewModel.onRollDice() },
                    onNavigateToRaceAndClassFragment = { },
                    onCreateCharacter = { },
                    AttributeRowData(
                        attributeLabel = R.string.attribute_strength,
                        attributeValue = characterCreatorViewModel.strength,
                        attributeModifier = characterCreatorViewModel.strengthModifier,
                        onIncrease = { characterCreatorViewModel.onIncreaseStrength() },
                        onDecrease = { characterCreatorViewModel.onDecreaseStrength() }
                    ),
                    AttributeRowData(
                        attributeLabel = R.string.attribute_dexterity,
                        attributeValue = characterCreatorViewModel.dexterity,
                        attributeModifier = characterCreatorViewModel.dexterityModifier,
                        onIncrease = { characterCreatorViewModel.onIncreaseDexterity() },
                        onDecrease = { characterCreatorViewModel.onDecreaseDexterity() }
                    ),
                    AttributeRowData(
                        attributeLabel = R.string.attribute_constitution,
                        attributeValue = characterCreatorViewModel.constitution,
                        attributeModifier = characterCreatorViewModel.constitutionModifier,
                        onIncrease = { characterCreatorViewModel.onIncreaseConstitution() },
                        onDecrease = { characterCreatorViewModel.onDecreaseConstitution() }
                    ),
                    AttributeRowData(
                        attributeLabel = R.string.attribute_intelligence,
                        attributeValue = characterCreatorViewModel.intelligence,
                        attributeModifier = characterCreatorViewModel.intelligenceModifier,
                        onIncrease = { characterCreatorViewModel.onIncreaseIntelligence() },
                        onDecrease = { characterCreatorViewModel.onDecreaseIntelligence() }
                    ),
                    AttributeRowData(
                        attributeLabel = R.string.attribute_wisdom,
                        attributeValue = characterCreatorViewModel.wisdom,
                        attributeModifier = characterCreatorViewModel.wisdomModifier,
                        onIncrease = { characterCreatorViewModel.onIncreaseWisdom() },
                        onDecrease = { characterCreatorViewModel.onDecreaseWisdom() }
                    ),
                    AttributeRowData(
                        attributeLabel = R.string.attribute_charisma,
                        attributeValue = characterCreatorViewModel.charisma,
                        attributeModifier = characterCreatorViewModel.charismaModifier,
                        onIncrease = { characterCreatorViewModel.onIncreaseCharisma() },
                        onDecrease = { characterCreatorViewModel.onDecreaseCharisma() }
                    )
                )
            }
        }

        // assert
        composeTestRule.onNodeWithText("Roll Dice (4d6)").assertIsDisplayed()
        composeTestRule.onNodeWithText("Strength").assertIsDisplayed()
        composeTestRule.onNodeWithText("Dexterity").assertIsDisplayed()
        composeTestRule.onNodeWithText("Constitution").assertIsDisplayed()
        composeTestRule.onNodeWithText("Intelligence").assertIsDisplayed()
        composeTestRule.onNodeWithText("Wisdom").assertIsDisplayed()
        composeTestRule.onNodeWithText("Charisma").assertIsDisplayed()
        composeTestRule.onNodeWithText("< Race and Class").assertIsDisplayed()
        composeTestRule.onNodeWithText("Create Character").assertIsDisplayed()
    }

}