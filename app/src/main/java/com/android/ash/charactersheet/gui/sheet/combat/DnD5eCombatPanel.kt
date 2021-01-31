package com.android.ash.charactersheet.gui.sheet.combat

import android.content.Intent
import android.view.View
import android.widget.Button
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.gui.util.IntentOnClickListener
import com.android.ash.charactersheet.gui.widget.dierollview.DieRollView

class DnD5eCombatPanel : AbstractCombatPanel() {

    override fun setCombat(view: View) {
        super.setCombat(view)

        val ruleService = gameSystemHolder.gameSystem?.ruleService
        val character = characterHolder.character

        // proficiency bonus
        val proficiencyBonus = ruleService?.calculateProficiencyBonus(character)
        displayModifier(proficiencyBonus, view, R.id.combat_proficiency_bonus)

        hideTableRow(view, R.id.combat_baseattackbonus_row)
        hideTableRow(view, R.id.combat_cmb_row)
        hideTableRow(view, R.id.combat_cmd_row)
    }

    override fun setOnClickListener(view: View) {
        val ruleService = gameSystemHolder.gameSystem?.ruleService
        val displayService = gameSystemHolder.gameSystem?.displayService
        val character = characterHolder.character

        val combatView: View = view.findViewById(R.id.combat_include)
        combatView.setOnClickListener(IntentOnClickListener(Intent(view.context, CombatEditActivity::class.java)))

        val dieRollView: DieRollView = view.findViewById(R.id.die_roll_view)

        val initiativeButton: Button = view.findViewById(R.id.combat_initiative)
        initiativeButton.setOnClickListener(InitiativeRollOnClickListener(character, displayService, ruleService, dieRollView))
    }

}