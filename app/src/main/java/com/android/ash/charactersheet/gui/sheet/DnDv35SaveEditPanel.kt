package com.android.ash.charactersheet.gui.sheet

import android.view.View
import android.widget.TableRow
import android.widget.TextView
import com.android.ash.charactersheet.R
import com.d20charactersheet.framework.boc.model.Save

class DnDv35SaveEditPanel : AbstractEditSavePanel() {

    override fun setData(view: View) {
        setHeading(view)

        // fortitude
        setNameOfSave(view, R.id.save_strength_label, R.string.save_fortitude_label)
        setSave(
            view,
            Save.STRENGTH,
            R.id.save_strength_base,
            R.id.save_strength_attribute,
            R.id.save_strength_modifier
        )

        // reflex
        setNameOfSave(view, R.id.save_dexterity_label, R.string.save_reflex_label)
        setSave(
            view,
            Save.DEXTERITY,
            R.id.save_dexterity_base,
            R.id.save_dexterity_attribute,
            R.id.save_dexterity_modifier
        )

        // will
        setNameOfSave(view, R.id.save_constitution_label, R.string.save_will_label)
        setSave(
            view,
            Save.CONSTITUTION,
            R.id.save_constitution_base,
            R.id.save_constitution_attribute,
            R.id.save_constitution_modifier
        )

        hideSave(view, R.id.save_intelligence_row)
        hideSave(view, R.id.save_wisdom_row)
        hideSave(view, R.id.save_charisma_row)
    }

    private fun setHeading(view: View) {
        view.findViewById<TextView>(R.id.save_heading_proficiency)
            .setText(R.string.save_heading_base)
    }

    private fun setNameOfSave(view: View, labelViewResourceId: Int, labelTextResourceId: Int) {
        view.findViewById<TextView>(labelViewResourceId).setText(labelTextResourceId)
    }

    private fun hideSave(view: View, saveTableRowResourceId: Int) {
        view.findViewById<TableRow>(saveTableRowResourceId).visibility = View.GONE
    }

    override fun saveData(view: View) {
        val character = checkNotNull(characterHolder.character)
        character.setSaveModifier(Save.STRENGTH, getModifier(view, R.id.save_strength_modifier))
        character.setSaveModifier(Save.DEXTERITY, getModifier(view, R.id.save_dexterity_modifier))
        character.setSaveModifier(
            Save.CONSTITUTION,
            getModifier(view, R.id.save_constitution_modifier)
        )
    }

}
