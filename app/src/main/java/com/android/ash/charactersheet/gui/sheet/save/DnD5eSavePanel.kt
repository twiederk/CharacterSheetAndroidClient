package com.android.ash.charactersheet.gui.sheet.save

import android.view.View
import com.android.ash.charactersheet.R
import com.d20charactersheet.framework.boc.model.Save

class DnD5eSavePanel : AbstractSavePanel() {

    override fun setSave(view: View) {

        setSave(
            view,
            Save.STRENGTH,
            R.id.save_strength,
            R.id.save_strength_label,
            R.string.save_strength_label
        )

        setSave(
            view,
            Save.DEXTERITY,
            R.id.save_dexterity,
            R.id.save_dexterity_label,
            R.string.save_dexterity_label
        )

        setSave(
            view,
            Save.CONSTITUTION,
            R.id.save_constitution,
            R.id.save_constitution_label,
            R.string.save_constitution_label
        )

        setSave(
            view,
            Save.INTELLIGENCE,
            R.id.save_intelligence,
            R.id.save_intelligence_label,
            R.string.save_intelligence_label
        )

        setSave(
            view,
            Save.WISDOM,
            R.id.save_wisdom,
            R.id.save_wisdom_label,
            R.string.save_wisdom_label
        )

        setSave(
            view,
            Save.CHARISMA,
            R.id.save_charisma,
            R.id.save_charisma_label,
            R.string.save_charisma_label
        )

    }

    override fun setOnClickListener(view: View) {
        setEditSaveOnClickListener(view)

        setSaveOnClickListener(view, R.id.save_strength, Save.STRENGTH)
        setSaveOnClickListener(view, R.id.save_dexterity, Save.DEXTERITY)
        setSaveOnClickListener(view, R.id.save_constitution, Save.CONSTITUTION)
        setSaveOnClickListener(view, R.id.save_intelligence, Save.INTELLIGENCE)
        setSaveOnClickListener(view, R.id.save_wisdom, Save.WISDOM)
        setSaveOnClickListener(view, R.id.save_charisma, Save.CHARISMA)
    }

}
