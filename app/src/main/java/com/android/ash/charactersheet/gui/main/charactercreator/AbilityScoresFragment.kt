package com.android.ash.charactersheet.gui.main.charactercreator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.android.ash.charactersheet.FBAnalytics
import com.android.ash.charactersheet.R

class AbilityScoresFragment : CharacterCreatorFragment() {

    override fun onResume() {
        super.onResume()
        logScreenNameAndClassToFirebaseAnalytics(FBAnalytics.ScreenName.ABILITY_SCORES, "AbilityScoresFragment")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ability_scores, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarSubtitle(R.string.ability_scores_subtitle)
        fillAbilityScores()
        view.findViewById<Button>(R.id.ability_scores_roll_dice).setOnClickListener { rollDice() }
    }

    override fun configureNavigation(view: View) {
        view.findViewById<Button>(R.id.ability_scores_navigate_previous_button).setOnClickListener {
            navigateTo(R.id.action_AbilityScoresFragment_to_RaceAndClassFragment)
        }
    }

    override fun updateCharacterData() {
        val characterData = characterCreator.characterData
        characterData.strength = view?.findViewById<EditText>(R.id.ability_scores_str)?.text.toString().toInt()
        characterData.dexterity = view?.findViewById<EditText>(R.id.ability_scores_dex)?.text.toString().toInt()
        characterData.constitution = view?.findViewById<EditText>(R.id.ability_scores_con)?.text.toString().toInt()
        characterData.intelligence = view?.findViewById<EditText>(R.id.ability_scores_int)?.text.toString().toInt()
        characterData.wisdom = view?.findViewById<EditText>(R.id.ability_scores_wis)?.text.toString().toInt()
        characterData.charisma = view?.findViewById<EditText>(R.id.ability_scores_cha)?.text.toString().toInt()
    }

    fun fillAbilityScores() {
        val characterData = characterCreator.characterData
        view?.findViewById<EditText>(R.id.ability_scores_str)?.setText(characterData.strength.toString())
        view?.findViewById<EditText>(R.id.ability_scores_dex)?.setText(characterData.dexterity.toString())
        view?.findViewById<EditText>(R.id.ability_scores_con)?.setText(characterData.constitution.toString())
        view?.findViewById<EditText>(R.id.ability_scores_int)?.setText(characterData.intelligence.toString())
        view?.findViewById<EditText>(R.id.ability_scores_wis)?.setText(characterData.wisdom.toString())
        view?.findViewById<EditText>(R.id.ability_scores_cha)?.setText(characterData.charisma.toString())
    }

    private fun rollDice() {
        val characterData = characterCreator.characterData
        gameSystem?.characterCreatorService?.let {
            characterData.strength = it.rollAttributeWithStandardMethod()
            characterData.dexterity = it.rollAttributeWithStandardMethod()
            characterData.constitution = it.rollAttributeWithStandardMethod()
            characterData.intelligence = it.rollAttributeWithStandardMethod()
            characterData.wisdom = it.rollAttributeWithStandardMethod()
            characterData.charisma = it.rollAttributeWithStandardMethod()
        }
        fillAbilityScores()
    }

}