package com.android.ash.charactersheet.gui.main.charactercreator

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.ash.charactersheet.FBAnalytics
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.gui.sheet.CharacterSheetActivity
import com.android.ash.charactersheet.gui.sheet.appearance.AlignmentArrayAdapter
import com.android.ash.charactersheet.gui.sheet.appearance.RaceArrayAdapter
import com.android.ash.charactersheet.gui.sheet.appearance.SexArrayAdapter
import com.android.ash.charactersheet.gui.sheet.clazz.CharacterClassArrayAdapter
import com.android.ash.charactersheet.gui.util.MessageManager
import com.d20charactersheet.framework.boc.model.Alignment
import com.d20charactersheet.framework.boc.model.CharacterClass
import com.d20charactersheet.framework.boc.model.Race
import com.d20charactersheet.framework.boc.model.Sex
import com.d20charactersheet.framework.boc.service.GameSystem
import com.d20charactersheet.framework.boc.util.CharacterClassComparator
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.*


class RaceAndClassFragment : Fragment(), KoinComponent {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val characterCreator: CharacterCreator by inject()
    private val firebaseAnalytics: FirebaseAnalytics by inject()
    private val characterCreatorViewModel: CharacterCreatorViewModel by inject()

    private var gameSystem: GameSystem? = null

    private lateinit var messageManager: MessageManager

    override fun onResume() {
        super.onResume()
        logScreenNameAndClassToFirebaseAnalytics(FBAnalytics.ScreenName.RACE_AND_CLASS, "RaceAndClassFragment")
    }

    private fun logScreenNameAndClassToFirebaseAnalytics(screenName: String, screenClass: String) {
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, screenClass)
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_race_and_class, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameSystem = gameSystemHolder.gameSystem
        messageManager = MessageManager(context, gameSystem?.displayService)

        view.findViewById<Button>(R.id.create_button).setOnClickListener { createCharacter() }

        configureNavigation(view)

        setToolbarSubtitle(R.string.race_and_class_subtitle)
        setRace(view)
        setSex(view)
        setAlignment(view)
        setClazz(view)
    }

    private fun setToolbarSubtitle(subtitleResourceId: Int) {
        val toolbar = activity?.findViewById<Toolbar>(R.id.toolbar)
        toolbar?.setSubtitle(subtitleResourceId)
    }


    private fun setRace(rootView: View) {
        val allRaces: MutableList<Race>? = gameSystem?.allRaces
        val raceArrayAdapter: ArrayAdapter<Race> = RaceArrayAdapter(this.context, gameSystem?.displayService, allRaces)
        rootView.findViewById<Spinner>(R.id.create_race).adapter = raceArrayAdapter
    }

    private fun setSex(rootView: View) {
        val sexArrayAdapter: ArrayAdapter<Sex> = SexArrayAdapter(this.context, gameSystem?.displayService, Sex.values())
        rootView.findViewById<Spinner>(R.id.create_sex).adapter = sexArrayAdapter
    }

    private fun setAlignment(rootView: View) {
        val alignmentArrayAdapter: ArrayAdapter<Alignment> = AlignmentArrayAdapter(this.context, gameSystem?.displayService, Alignment.values())
        rootView.findViewById<Spinner>(R.id.create_alignment).adapter = alignmentArrayAdapter
    }

    private fun setClazz(rootView: View) {
        val allCharacterClasses = gameSystem?.allCharacterClasses
        allCharacterClasses?.sortWith(CharacterClassComparator())
        val classArrayAdapter: ArrayAdapter<CharacterClass> = CharacterClassArrayAdapter(this.context, gameSystem?.displayService, ArrayList(allCharacterClasses))
        rootView.findViewById<Spinner>(R.id.create_class).adapter = classArrayAdapter
    }

    private fun configureNavigation(view: View) {
        view.findViewById<Button>(R.id.race_and_class_navigate_next_button).setOnClickListener {
            navigateTo(R.id.action_RaceAndClassFragment_to_AbilityScoresFragment)
        }
    }

    private fun navigateTo(actionResourceId: Int) {
        updateCharacterData()
        findNavController().navigate(actionResourceId)
    }


    internal fun updateCharacterData() {
        characterCreatorViewModel.name = getEditText(R.id.create_name)
        characterCreatorViewModel.player = getEditText(R.id.create_player)
        characterCreatorViewModel.race = view?.findViewById<Spinner>(R.id.create_race)?.selectedItem as Race
        characterCreatorViewModel.sex = view?.findViewById<Spinner>(R.id.create_sex)?.selectedItem as Sex
        characterCreatorViewModel.clazz = view?.findViewById<Spinner>(R.id.create_class)?.selectedItem as CharacterClass
        characterCreatorViewModel.alignment = view?.findViewById<Spinner>(R.id.create_alignment)?.selectedItem as Alignment
    }

    private fun getEditText(resourceId: Int): String {
        val editText: EditText? = this.view?.findViewById(resourceId)
        return editText?.text.toString()
    }

    private fun createCharacter() {
        try {
            updateCharacterData()
            characterCreator.createCharacter(characterCreatorViewModel)
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

}
