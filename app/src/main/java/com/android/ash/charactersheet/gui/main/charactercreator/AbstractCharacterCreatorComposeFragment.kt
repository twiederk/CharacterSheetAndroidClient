package com.android.ash.charactersheet.gui.main.charactercreator

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.gui.sheet.CharacterSheetActivity
import com.android.ash.charactersheet.gui.util.MessageManager
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class AbstractCharacterCreatorComposeFragment : Fragment(), KoinComponent {

    protected val characterCreatorViewModel: CharacterCreatorViewModel by inject()

    private val gameSystemHolder: GameSystemHolder by inject()
    private val firebaseAnalytics: FirebaseAnalytics by inject()

    protected fun logScreenNameAndClassToFirebaseAnalytics(screenName: String, screenClass: String) {
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, screenClass)
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }

    protected fun createCharacter() {
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