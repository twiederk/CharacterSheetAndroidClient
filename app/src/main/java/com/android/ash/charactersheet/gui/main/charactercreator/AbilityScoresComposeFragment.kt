package com.android.ash.charactersheet.gui.main.charactercreator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.findNavController
import com.android.ash.charactersheet.FBAnalytics
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.gui.theme.D20CharacterSheetTheme
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class AbilityScoresComposeFragment : AbstractCharacterCreatorComposeFragment() {

    override fun onResume() {
        super.onResume()
        logScreenNameAndClassToFirebaseAnalytics(FBAnalytics.ScreenName.ABILITY_SCORES, "AbilityScoresComposeFragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            id = R.id.AbilityScoresComposeFragment

            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setContent {
                D20CharacterSheetTheme {
                    AbilityScoresScreen(
                        onRollDice = { characterCreatorViewModel.onRollDice() },
                        onNavigateToPrevious = { findNavController().navigate(R.id.action_AbilityScoresFragment_to_ClassFragment) },
                        onNavigateToNext = { findNavController().navigate(R.id.action_AbilityScoresFragment_to_EquipmentFragment) },
                        onCreateCharacter = { createCharacter() },
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
        }
    }

}