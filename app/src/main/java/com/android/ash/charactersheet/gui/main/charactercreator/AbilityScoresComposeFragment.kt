package com.android.ash.charactersheet.gui.main.charactercreator

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.ash.charactersheet.FBAnalytics
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.gui.sheet.CharacterSheetActivity
import com.android.ash.charactersheet.gui.util.MessageManager
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.core.KoinComponent
import org.koin.core.inject

class AbilityScoresComposeFragment : Fragment(), KoinComponent {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val firebaseAnalytics: FirebaseAnalytics by inject()
    private val characterCreatorViewModel: CharacterCreatorViewModel by inject()

    override fun onResume() {
        super.onResume()
        logScreenNameAndClassToFirebaseAnalytics(FBAnalytics.ScreenName.ABILITY_SCORES, "AbilityScoresComposeFragment")
    }

    private fun logScreenNameAndClassToFirebaseAnalytics(screenName: String, screenClass: String) {
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, screenClass)
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ability_scores_compose, container, false).apply {
            findViewById<ComposeView>(R.id.ability_scores_compose_view).setContent {
                MaterialTheme {
                    AbilityScoresScreen(
                        onRollDice = { characterCreatorViewModel.onRollDice() },
                        onNavigateToRaceAndClassFragment = { findNavController().navigate(R.id.action_AbilityScoresComposeFragment_to_AbilityScoresFragment) },
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarSubtitle(R.string.ability_scores_subtitle)
    }

    private fun setToolbarSubtitle(subtitleResourceId: Int) {
        val toolbar = activity?.findViewById<Toolbar>(R.id.toolbar)
        toolbar?.setSubtitle(subtitleResourceId)
    }


    private fun createCharacter() {
        try {
            characterCreatorViewModel.onCreateCharacter()
            jumpToCharacterSheet()
        } catch (exception: Exception) {
            MessageManager(context, gameSystemHolder.gameSystem?.displayService).showErrorMessage(exception)
        }
    }

    private fun jumpToCharacterSheet() {
        val intent = Intent(activity, CharacterSheetActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

}