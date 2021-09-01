package com.android.ash.charactersheet.gui.sheet.save

import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.gui.util.IntentOnClickListener
import com.android.ash.charactersheet.gui.widget.dierollview.DieRollView
import com.d20charactersheet.framework.boc.model.Save
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DnDv35SavePanel : SavePanel, KoinComponent {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val characterHolder: CharacterHolder by inject()


    override fun setSave(view: View) {
        val ruleService = gameSystemHolder.gameSystem?.ruleService
        val displayService = gameSystemHolder.gameSystem?.displayService
        val character = checkNotNull(characterHolder.character)

        val fortitudeTextView = view.findViewById<TextView>(R.id.save_fortitude)
        var savingThrow: Int = ruleService?.getSave(character, Save.FORTITUDE) ?: 0
        fortitudeTextView.text = displayService?.getDisplayModifier(savingThrow)

        val reflexTextView = view.findViewById<TextView>(R.id.save_reflex)
        savingThrow = ruleService?.getSave(character, Save.REFLEX) ?: 0
        reflexTextView.text = displayService?.getDisplayModifier(savingThrow)

        val willTextView = view.findViewById<TextView>(R.id.save_will)
        savingThrow = ruleService?.getSave(character, Save.WILL) ?: 0
        willTextView.text = displayService?.getDisplayModifier(savingThrow)
    }

    override fun setOnClickListener(view: View) {
        val savingThrowView: View = view.findViewById(R.id.saving_throw_include)
        savingThrowView.setOnClickListener(IntentOnClickListener(Intent(view.context, SaveEditActivity::class.java)))
        setSaveOnClickListener(view, R.id.save_fortitude, Save.FORTITUDE)
        setSaveOnClickListener(view, R.id.save_reflex, Save.REFLEX)
        setSaveOnClickListener(view, R.id.save_will, Save.WILL)
    }

    private fun setSaveOnClickListener(view: View, resourceId: Int, save: Save) {
        val ruleService = gameSystemHolder.gameSystem?.ruleService
        val displayService = gameSystemHolder.gameSystem?.displayService
        val character = characterHolder.character

        val button: Button = view.findViewById(resourceId)
        val dieRollView: DieRollView = view.findViewById(R.id.die_roll_view)
        button.setOnClickListener(SaveRollOnClickListener(character, save, displayService, ruleService, dieRollView))
    }

}
