package com.android.ash.charactersheet.gui.sheet.combat

import android.view.View
import android.widget.TableRow
import com.android.ash.charactersheet.R

class DnD5eCombatEditPanel : AbstractCombatEditPanel() {

    override fun setCombatEdit(view: View) {
        super.setCombatEdit(view)
        view.findViewById<TableRow?>(R.id.combat_cmb_row).visibility = View.GONE
        view.findViewById<TableRow?>(R.id.combat_cmd_row).visibility = View.GONE
    }

}
