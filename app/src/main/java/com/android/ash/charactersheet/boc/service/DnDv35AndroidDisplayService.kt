package com.android.ash.charactersheet.boc.service

import android.content.res.Resources
import com.android.ash.charactersheet.R
import com.d20charactersheet.framework.boc.model.Save

class DnDv35AndroidDisplayService(resources: Resources) : AbstractAndroidDisplayService(resources) {

    override fun getDisplaySave(save: Save): String {
        return when (save) {
            Save.STRENGTH -> resources.getString(R.string.save_fortitude)
            Save.DEXTERITY -> resources.getString(R.string.save_reflex)
            Save.CONSTITUTION -> resources.getString(R.string.save_will)
            Save.INTELLIGENCE -> resources.getString(R.string.save_intelligence)
            Save.WISDOM -> resources.getString(R.string.save_wisdom)
            Save.CHARISMA -> resources.getString(R.string.save_charisma)
        }
    }

}