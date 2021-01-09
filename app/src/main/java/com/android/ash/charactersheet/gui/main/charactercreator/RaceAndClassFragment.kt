package com.android.ash.charactersheet.gui.main.charactercreator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.android.ash.charactersheet.FBAnalytics
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.gui.sheet.appearance.AlignmentArrayAdapter
import com.android.ash.charactersheet.gui.sheet.appearance.RaceArrayAdapter
import com.android.ash.charactersheet.gui.sheet.appearance.SexArrayAdapter
import com.android.ash.charactersheet.gui.sheet.clazz.CharacterClassArrayAdapter
import com.d20charactersheet.framework.boc.model.Alignment
import com.d20charactersheet.framework.boc.model.CharacterClass
import com.d20charactersheet.framework.boc.model.Race
import com.d20charactersheet.framework.boc.model.Sex
import com.d20charactersheet.framework.boc.util.CharacterClassComparator
import java.util.*


class RaceAndClassFragment : CharacterCreatorFragment() {

    override fun onResume() {
        super.onResume()
        logScreenNameAndClassToFirebaseAnalytics(FBAnalytics.ScreenName.RACE_AND_CLASS, "RaceAndClassFragment")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_race_and_class, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbarSubtitle(R.string.race_and_class_subtitle)
        setRace(view)
        setSex(view)
        setAlignment(view)
        setClazz(view)
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

    override fun configureNavigation(view: View) {
        view.findViewById<Button>(R.id.race_and_class_navigate_next_button).setOnClickListener {
            navigateTo(R.id.action_RaceAndClassFragment_to_AbilityScoresFragment)
        }
    }

    override fun updateCharacterData() {
        val characterData = characterCreator.characterData
        characterData.name = getEditText(R.id.create_name)
        characterData.player = getEditText(R.id.create_player)
        characterData.race = view?.findViewById<Spinner>(R.id.create_race)?.selectedItem as Race
        characterData.sex = view?.findViewById<Spinner>(R.id.create_sex)?.selectedItem as Sex
        characterData.clazz = view?.findViewById<Spinner>(R.id.create_class)?.selectedItem as CharacterClass
        characterData.alignment = view?.findViewById<Spinner>(R.id.create_alignment)?.selectedItem as Alignment
    }

    private fun getEditText(resourceId: Int): String {
        val editText: EditText? = this.view?.findViewById(resourceId)
        return editText?.text.toString()
    }

}
