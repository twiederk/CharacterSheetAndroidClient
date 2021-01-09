package com.android.ash.charactersheet.gui.main.charactercreator

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.gui.sheet.CharacterSheetActivity
import com.android.ash.charactersheet.gui.util.MessageManager
import com.d20charactersheet.framework.boc.service.GameSystem
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class CharacterCreatorFragment : Fragment(), KoinComponent {

    private val gameSystemHolder: GameSystemHolder by inject()
    protected val characterCreator: CharacterCreator by inject()
    private val firebaseAnalytics: FirebaseAnalytics by inject()

    private lateinit var messageManager: MessageManager

    protected var gameSystem: GameSystem? = null

    protected fun logScreenNameAndClassToFirebaseAnalytics(screenName: String, screenClass: String) {
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, screenClass)
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameSystem = gameSystemHolder.gameSystem
        messageManager = MessageManager(context, gameSystem?.displayService)

        view.findViewById<Button>(R.id.create_button).setOnClickListener { createCharacter() }

        configureNavigation(view)
    }

    protected fun setToolbarSubtitle(subtitleResourceId: Int) {
        val toolbar = activity?.findViewById<Toolbar>(R.id.toolbar)
        toolbar?.setSubtitle(subtitleResourceId)
    }

    protected fun navigateTo(actionResourceId: Int) {
        updateCharacterData()
        findNavController().navigate(actionResourceId)
    }

    private fun createCharacter() {
        try {
            updateCharacterData()
            characterCreator.createCharacter()
            jumpToCharacterSheet()
        } catch (exception: Exception) {
            messageManager.showErrorMessage(exception)
        }
    }

    private fun jumpToCharacterSheet() {
        val intent = Intent(activity, CharacterSheetActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    internal abstract fun updateCharacterData()

    protected abstract fun configureNavigation(view: View)
}

