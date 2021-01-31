package com.android.ash.charactersheet.gui.sheet.combat

import android.view.View
import android.widget.TextView
import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.gui.widget.numberview.PositiveNumberViewController
import com.android.ash.charactersheet.gui.widget.numberview.SimpleNumberViewController
import com.android.ash.charactersheet.gui.widget.numberview.StepNumberView
import com.android.ash.charactersheet.gui.widget.numberview.SumNumberView
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class AbstractCombatEditPanel : CombatEditPanel, KoinComponent {

    protected val gameSystemHolder: GameSystemHolder by inject()
    protected val characterHolder: CharacterHolder by inject()

    override fun setCombatEdit(view: View) {
        val ruleService = gameSystemHolder.gameSystem?.ruleService
        val displayService = gameSystemHolder.gameSystem?.displayService

        val character = characterHolder.character

        val hitPointsQuickNumberView = view.findViewById<SumNumberView>(R.id.combat_hitpoints)
        val hitPointsController = SimpleNumberViewController(character?.hitPoints ?: 0)
        hitPointsQuickNumberView.controller = hitPointsController

        val maxHitPointsNumberView = view.findViewById<SumNumberView>(R.id.combat_max_hitpoints)
        val maxHitPointsController = PositiveNumberViewController(character?.maxHitPoints ?: 0)
        maxHitPointsNumberView.controller = maxHitPointsController

        val armorClassNumberView = view.findViewById<StepNumberView>(R.id.combat_armorclass)
        val armorClassFormularTextView = view.findViewById<TextView>(R.id.combat_armorclass_formular)
        val armorClassController = SimpleNumberViewController(character?.armorClass ?: 0)
        armorClassNumberView.controller = armorClassController
        val dexterityModifier = ruleService?.getModifier(character?.dexterity ?: 0)
        armorClassFormularTextView.text = displayService?.getDisplayArmourClassFormular(dexterityModifier
                ?: 0)

        val initiativeNumberView = view.findViewById<StepNumberView>(R.id.combat_initiative)
        val initiativeFormularTextView = view.findViewById<TextView>(R.id.combat_initiative_formular)
        val initiativeModifierController = SimpleNumberViewController(character?.initiativeModifier
                ?: 0)
        initiativeNumberView.controller = initiativeModifierController
        initiativeFormularTextView.text = displayService?.getDisplaySimpleFormular(dexterityModifier
                ?: 0)

    }

    override fun saveData(view: View) {
        val character = characterHolder.character

        val hitPointsQuickNumberView: SumNumberView = view.findViewById(R.id.combat_hitpoints)
        character?.hitPoints = hitPointsQuickNumberView.controller.number as Int

        val maxHitPointsNumberView: SumNumberView = view.findViewById(R.id.combat_max_hitpoints)
        character?.maxHitPoints = maxHitPointsNumberView.controller.number as Int

        val armorClassNumberView: StepNumberView = view.findViewById(R.id.combat_armorclass)
        character?.armorClass = armorClassNumberView.controller.number as Int

        val initiativeNumberView: StepNumberView = view.findViewById(R.id.combat_initiative)
        character?.initiativeModifier = initiativeNumberView.controller.number as Int
    }


}