package com.android.ash.charactersheet.gui.sheet

import android.view.View
import android.widget.TextView
import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.gui.widget.numberview.SimpleNumberViewController
import com.android.ash.charactersheet.gui.widget.numberview.StepNumberView
import com.d20charactersheet.framework.boc.model.Save
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class AbstractEditSavePanel : SaveEditPanel, KoinComponent {

    protected val gameSystemHolder: GameSystemHolder by inject()
    protected val characterHolder: CharacterHolder by inject()

    protected fun setSave(
        view: View, save: Save, baseResourceId: Int,
        attributeResourceId: Int,
        modifierResourceId: Int
    ) {
        val character = checkNotNull(characterHolder.character)

        val gameSystem = checkNotNull(gameSystemHolder.gameSystem)
        val ruleService = gameSystem.ruleService
        val displayService = gameSystem.displayService

        val baseSave: Int = ruleService.getProficiencySave(character, save)
        val baseTextView: TextView = view.findViewById(baseResourceId)
        baseTextView.text = displayService.getDisplayModifier(baseSave)

        val attributeModifier: Int = ruleService.getSaveAttributeModifier(character, save)
        val attributeTextView: TextView = view.findViewById(attributeResourceId)
        attributeTextView.text = displayService.getDisplayModifier(attributeModifier)

        val modifier: Int = ruleService.getSaveModifier(character, save)
        val modifierNumberView: StepNumberView = view.findViewById(modifierResourceId)
        modifierNumberView.controller = SimpleNumberViewController(modifier)
    }

    protected fun getModifier(view: View, modifierResourceId: Int): Int {
        val modifierNumberView: StepNumberView = view.findViewById(modifierResourceId)
        val controller = modifierNumberView.controller
        return controller.number as Int
    }

}