package com.android.ash.charactersheet.boc.service

import android.content.res.Resources
import com.android.ash.charactersheet.R
import com.d20charactersheet.framework.boc.model.Save

class DnD5eAndroidDisplayService(resources: Resources) : AbstractAndroidDisplayService(resources) {

    override fun getDisplaySave(save: Save): String {
        return when (save) {
            Save.STRENGTH -> resources.getString(R.string.save_strength)
            Save.DEXTERITY -> resources.getString(R.string.save_dexterity)
            Save.CONSTITUTION -> resources.getString(R.string.save_constitution)
            Save.INTELLIGENCE -> resources.getString(R.string.save_intelligence)
            Save.WISDOM -> resources.getString(R.string.save_wisdom)
            Save.CHARISMA -> resources.getString(R.string.save_charisma)
        }
    }

}