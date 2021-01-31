package com.android.ash.charactersheet.gui.main.characterlist

import android.view.View
import com.android.ash.charactersheet.boc.model.GameSystemType

class GameSystemSelectorOnClickListener(
        private val gameSystemSelector: GameSystemSelector,
        val gameSystemType: GameSystemType
) : View.OnClickListener {


    override fun onClick(view: View?) {
        gameSystemSelector.switchGameSystem(gameSystemType)
    }

}