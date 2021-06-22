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
class RaceComposeFragment : AbstractCharacterCreatorComposeFragment() {

    override fun onResume() {
        super.onResume()
        logScreenNameAndClassToFirebaseAnalytics(FBAnalytics.ScreenName.RACE, "RaceComposeFragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            id = R.id.RaceComposeFragment

            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setContent {
                D20CharacterSheetTheme {
                    RaceScreen(
                        race = characterCreatorViewModel.race,
                        raceList = characterCreatorViewModel.raceList,
                        getBitmap = { characterCreatorViewModel.getBitmap(it) },
                        onRaceChange = { characterCreatorViewModel.onRaceChange(it) },
                    ) { findNavController().navigate(R.id.action_RaceFragment_to_ClassFragment) }
                }
            }
        }
    }
}