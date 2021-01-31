package com.android.ash.charactersheet.gui.sheet.save

import android.view.View
import com.android.ash.charactersheet.R

class DnD5eSavePanel : SavePanel {

    override fun setSave(view: View) {
        val savePanelView: View = view.findViewById(R.id.saving_throw_include)
        savePanelView.visibility = View.GONE
    }

    override fun setOnClickListener(view: View) {
    }

}
