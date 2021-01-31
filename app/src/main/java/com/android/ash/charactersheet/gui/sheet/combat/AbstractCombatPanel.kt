package com.android.ash.charactersheet.gui.sheet.combat

import android.view.View
import android.widget.ProgressBar
import android.widget.TableRow
import android.widget.TextView
import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.d20charactersheet.framework.boc.model.Character
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class AbstractCombatPanel : KoinComponent, CombatPanel {

    protected val gameSystemHolder: GameSystemHolder by inject()
    protected val characterHolder: CharacterHolder by inject()

    override fun setCombat(view: View) {
        val ruleService = gameSystemHolder.gameSystem?.ruleService
        val character = characterHolder.character

        // hit points
        val hitPointsTextView: TextView = view.findViewById(R.id.combat_hitpoints)
        hitPointsTextView.text = buildHitPointsText(character)
        setHitPointsProgressBar(view, character)

        // armor class
        val armorClassTextView: TextView = view.findViewById(R.id.combat_armorclass)
        armorClassTextView.text = ruleService?.getArmorClass(character).toString()

        // flatfooted armor class
        val flatFootedArmorClassTextView: TextView = view.findViewById(R.id.combat_flatfooted_armorclass)
        flatFootedArmorClassTextView.text = ruleService?.calculateFlatFootedArmorClass(character).toString()

        // touch armor class
        val touchArmorClassTextView: TextView = view.findViewById(R.id.combat_touch_armorclass)
        touchArmorClassTextView.text = ruleService?.calculateTouchArmorClass(character).toString()

        // speed
        val speedTextView: TextView = view.findViewById(R.id.combat_speed)
        speedTextView.text = ruleService?.getSpeed(character).toString()

        // initiative
        val initiative = ruleService?.getInitative(character)
        displayModifier(initiative, view, R.id.combat_initiative)

        // base attack bonus
        val baseAttackBonus = ruleService?.getBaseAttackBonus(character)
        displayModifier(baseAttackBonus, view, R.id.combat_baseattackbonus)


        // cmb
        val cmb = ruleService?.getCombatManeuverBonus(character)
        displayModifier(cmb, view, R.id.combat_cmb)


        // cmd
        val cmd = ruleService?.getCombatManeuverDefence(character)
        displayModifier(cmd, view, R.id.combat_cmd)
    }

    private fun buildHitPointsText(character: Character?) =
            "${character?.hitPoints.toString()} (${character?.maxHitPoints})"

    private fun setHitPointsProgressBar(view: View, character: Character?) {
        val hitPointsProgressBar: ProgressBar = view.findViewById(R.id.combat_hitpoints_progress)
        hitPointsProgressBar.max = character?.maxHitPoints ?: 0
        hitPointsProgressBar.progress = character?.hitPoints ?: 0
    }

    protected fun displayModifier(number: Int?, view: View, textViewResourceId: Int) {
        val displayService = gameSystemHolder.gameSystem?.displayService
        val textView: TextView = view.findViewById(textViewResourceId)
        textView.text = displayService?.getDisplayModifier(number ?: 0)
    }

    protected fun hideTableRow(view: View, tableRowResourceId: Int) {
        val proficiencyBonusRow = view.findViewById<TableRow>(tableRowResourceId)
        proficiencyBonusRow.visibility = View.GONE
    }

}