package com.android.ash.charactersheet.gui.sheet

import android.view.View
import com.android.ash.charactersheet.R
import com.d20charactersheet.framework.boc.model.Save

class DnD5eSaveEditPanel : AbstractEditSavePanel() {

    override fun setData(view: View) {
        setSave(
            view,
            Save.STRENGTH,
            R.id.save_strength_base,
            R.id.save_strength_attribute,
            R.id.save_strength_modifier
        )
        setSave(
            view,
            Save.DEXTERITY,
            R.id.save_dexterity_base,
            R.id.save_dexterity_attribute,
            R.id.save_dexterity_modifier
        )
        setSave(
            view,
            Save.CONSTITUTION,
            R.id.save_constitution_base,
            R.id.save_constitution_attribute,
            R.id.save_constitution_modifier
        )
        setSave(
            view,
            Save.INTELLIGENCE,
            R.id.save_intelligence_base,
            R.id.save_intelligence_attribute,
            R.id.save_intelligence_modifier
        )
        setSave(
            view,
            Save.WISDOM,
            R.id.save_wisdom_base,
            R.id.save_wisdom_attribute,
            R.id.save_wisdom_modifier
        )
        setSave(
            view,
            Save.CHARISMA,
            R.id.save_charisma_base,
            R.id.save_charisma_attribute,
            R.id.save_charisma_modifier
        )
    }

    override fun saveData(view: View) {
        val character = checkNotNull(characterHolder.character)
        character.setSaveModifier(Save.STRENGTH, getModifier(view, R.id.save_strength_modifier))
        character.setSaveModifier(Save.DEXTERITY, getModifier(view, R.id.save_dexterity_modifier))
        character.setSaveModifier(
            Save.CONSTITUTION,
            getModifier(view, R.id.save_constitution_modifier)
        )
        character.setSaveModifier(
            Save.INTELLIGENCE,
            getModifier(view, R.id.save_intelligence_modifier)
        )
        character.setSaveModifier(Save.WISDOM, getModifier(view, R.id.save_wisdom_modifier))
        character.setSaveModifier(Save.CHARISMA, getModifier(view, R.id.save_charisma_modifier))
    }

}
