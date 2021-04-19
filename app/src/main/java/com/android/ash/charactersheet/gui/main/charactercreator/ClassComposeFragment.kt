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
class ClassComposeFragment : AbstractCharacterCreatorComposeFragment() {

    override fun onResume() {
        super.onResume()
        logScreenNameAndClassToFirebaseAnalytics(FBAnalytics.ScreenName.CLASS, "ClassComposeFragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            id = R.id.ClassComposeFragment

            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setContent {
                D20CharacterSheetTheme {
                    ClassScreen(
                        name = characterCreatorViewModel.name,
                        player = characterCreatorViewModel.player,
                        clazz = characterCreatorViewModel.clazz,
                        classList = characterCreatorViewModel.classList,
                        gender = characterCreatorViewModel.gender,
                        genderList = characterCreatorViewModel.genderList,
                        alignment = characterCreatorViewModel.alignment,
                        alignmentList = characterCreatorViewModel.alignmentList,
                        onChangeName = { characterCreatorViewModel.onChangeName(it) },
                        onChangePlayer = { characterCreatorViewModel.onChangePlayer(it) },
                        onGenderChange = { characterCreatorViewModel.onGenderChange(it) },
                        onAlignmentChange = { characterCreatorViewModel.onAlignmentChange(it) },
                        onClassChange = { characterCreatorViewModel.onClassNameChange(it) },
                        onNavigateToPrevious = { findNavController().navigate(R.id.action_ClassFragment_to_RaceFragment) },
                        onNavigateToNext = { findNavController().navigate(R.id.action_ClassFragment_to_AbilityScoresFragment) }
                    ) { createCharacter() }
                }
            }
        }
    }

}