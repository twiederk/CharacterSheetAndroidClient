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

abstract class AbstractSavePanel : SavePanel, KoinComponent {

    protected val gameSystemHolder: GameSystemHolder by inject()
    protected val characterHolder: CharacterHolder by inject()

    protected fun setSave(
        view: View,
        save: Save,
        saveViewResourceId: Int,
        labelViewResourceId: Int,
        labelTextResourceId: Int
    ) {
        val gameSystem = checkNotNull(gameSystemHolder.gameSystem)
        val ruleService = gameSystem.ruleService
        val displayService = gameSystem.displayService
        val character = checkNotNull(characterHolder.character)

        view.findViewById<TextView>(labelViewResourceId).setText(labelTextResourceId)
        val saveValue: Int = ruleService.getSave(character, save)
        val saveModifier = displayService.getDisplayModifier(saveValue)
        view.findViewById<TextView>(saveViewResourceId).text = saveModifier
    }

    protected fun setSaveOnClickListener(view: View, resourceId: Int, save: Save) {
        val gameSystem = checkNotNull(gameSystemHolder.gameSystem)
        val ruleService = gameSystem.ruleService
        val displayService = gameSystem.displayService
        val character = characterHolder.character

        val button: Button = view.findViewById(resourceId)
        val dieRollView: DieRollView = view.findViewById(R.id.die_roll_view)
        button.setOnClickListener(
            SaveRollOnClickListener(
                character,
                save,
                displayService,
                ruleService,
                dieRollView
            )
        )
    }

    protected fun setEditSaveOnClickListener(view: View) {
        val savingThrowView: View = view.findViewById(R.id.saves_include)
        savingThrowView.setOnClickListener(
            IntentOnClickListener(
                Intent(
                    view.context,
                    SaveEditActivity::class.java
                )
            )
        )
    }

}