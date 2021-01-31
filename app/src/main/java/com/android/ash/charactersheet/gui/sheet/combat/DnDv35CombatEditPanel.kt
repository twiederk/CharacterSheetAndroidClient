package com.android.ash.charactersheet.gui.sheet.combat

import android.view.View
import android.widget.TextView
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.gui.widget.numberview.SimpleNumberViewController
import com.android.ash.charactersheet.gui.widget.numberview.StepNumberView

class DnDv35CombatEditPanel : AbstractCombatEditPanel() {

    override fun setCombatEdit(view: View) {
        super.setCombatEdit(view)
        val ruleService = gameSystemHolder.gameSystem?.ruleService
        val displayService = gameSystemHolder.gameSystem?.displayService

        val character = characterHolder.character

        val cmbNumberView = view.findViewById<StepNumberView>(R.id.combat_cmb)
        val cmbFormularTextView = view.findViewById<TextView>(R.id.combat_cmb_formular)
        val cmbController = SimpleNumberViewController(character?.cmbModifier ?: 0)
        cmbNumberView.controller = cmbController
        val cmb = (ruleService?.getCombatManeuverBonus(character) ?: 0) - (character?.cmbModifier
                ?: 0)
        cmbFormularTextView.text = displayService?.getDisplaySimpleFormular(cmb)

        val cmdNumberView = view.findViewById<StepNumberView>(R.id.combat_cmd)
        val cmdFormularTextView = view.findViewById<TextView>(R.id.combat_cmd_formular)
        val cmdController = SimpleNumberViewController(character?.cmdModifier ?: 0)
        cmdNumberView.controller = cmdController
        val cmd = (ruleService?.getCombatManeuverDefence(character) ?: 0) - (character?.cmdModifier
                ?: 0)
        cmdFormularTextView.text = displayService?.getDisplaySimpleFormular(cmd)
    }

    override fun saveData(view: View) {
        super.saveData(view)

        val character = characterHolder.character

        val cmbNumberView: StepNumberView = view.findViewById(R.id.combat_cmb)
        character?.cmbModifier = cmbNumberView.controller.number as Int

        val cmdNumberView: StepNumberView = view.findViewById(R.id.combat_cmd)
        character?.cmdModifier = cmdNumberView.controller.number as Int
    }

}
