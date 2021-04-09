package com.android.ash.charactersheet.gui.main.charactercreator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.findNavController
import com.android.ash.charactersheet.FBAnalytics
import com.android.ash.charactersheet.R

class RaceAndClassComposeFragment : AbstractCharacterCreatorComposeFragment() {

    override fun onResume() {
        super.onResume()
        logScreenNameAndClassToFirebaseAnalytics(FBAnalytics.ScreenName.RACE_AND_CLASS, "RaceAndClassFragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            id = R.id.RaceAndClassComposeFragment

            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setContent {
                MaterialTheme {
                    RaceAndClassScreen(
                        name = characterCreatorViewModel.name,
                        player = characterCreatorViewModel.player,
                        race = characterCreatorViewModel.race,
                        raceList = characterCreatorViewModel.raceList,
                        sex = characterCreatorViewModel.sex,
                        sexList = characterCreatorViewModel.sexList,
                        alignment = characterCreatorViewModel.alignment,
                        alignmentList = characterCreatorViewModel.alignmentList,
                        clazz = characterCreatorViewModel.clazz,
                        classList = characterCreatorViewModel.classList,

                        onChangeName = { characterCreatorViewModel.onChangeName(it) },
                        onChangePlayer = { characterCreatorViewModel.onChangePlayer(it) },
                        onRaceChange = { characterCreatorViewModel.onRaceChange(it) },
                        onSexChange = { characterCreatorViewModel.onSexChange(it) },
                        onAlignmentChange = { characterCreatorViewModel.onAlignmentChange(it) },
                        onClassChange = { characterCreatorViewModel.onClassNameChange(it) },
                        onNavigateToRaceAndClassFragment = { findNavController().navigate(R.id.action_RaceAndClassFragment_to_AbilityScoresFragment) },
                        onCreateCharacter = { createCharacter() }
                    )
                }
            }
        }
    }

}