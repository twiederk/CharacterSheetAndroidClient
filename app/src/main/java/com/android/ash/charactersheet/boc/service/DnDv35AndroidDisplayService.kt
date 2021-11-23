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
            else -> throw IllegalArgumentException("Can't determine save: $save")
        }
    }

}