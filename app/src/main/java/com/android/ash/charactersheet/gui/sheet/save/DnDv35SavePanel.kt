package com.android.ash.charactersheet.gui.sheet.save

import android.view.View
import com.android.ash.charactersheet.R
import com.d20charactersheet.framework.boc.model.Save

class DnDv35SavePanel : AbstractSavePanel() {

    override fun setSave(view: View) {

        setSave(
            view,
            Save.STRENGTH,
            R.id.save_strength,
            R.id.save_strength_label,
            R.string.save_fortitude_label,
        )

        setSave(
            view,
            Save.DEXTERITY,
            R.id.save_dexterity,
            R.id.save_dexterity_label,
            R.string.save_reflex_label,
        )

        setSave(
            view,
            Save.CONSTITUTION,
            R.id.save_constitution,
            R.id.save_constitution_label,
            R.string.save_will_label,
        )

        view.findViewById<View>(R.id.save_intelligence_row).visibility = View.GONE
        view.findViewById<View>(R.id.save_wisdom_row).visibility = View.GONE
        view.findViewById<View>(R.id.save_charisma_row).visibility = View.GONE
    }

    override fun setOnClickListener(view: View) {
        setEditSaveOnClickListener(view)

        setSaveOnClickListener(view, R.id.save_strength, Save.STRENGTH)
        setSaveOnClickListener(view, R.id.save_dexterity, Save.DEXTERITY)
        setSaveOnClickListener(view, R.id.save_constitution, Save.CONSTITUTION)
    }

}
