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
class EquipmentComposeFragment : AbstractCharacterCreatorComposeFragment() {

    override fun onResume() {
        super.onResume()
        logScreenNameAndClassToFirebaseAnalytics(
            FBAnalytics.ScreenName.EQUIPMENT,
            "EquipmentComposeFragment"
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            id = R.id.EquipmentComposeFragment

            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setContent {
                D20CharacterSheetTheme {
                    EquipmentScreen(
                        starterPackBoxViewModels = characterCreatorViewModel.starterPackBoxViewModels,
                        onNavigateToPrevious = { findNavController().navigate(R.id.action_EquipmentFragment_to_AbilityScoresFragment) }
                    ) { createCharacter() }
                }
            }
        }
    }
}