package com.android.ash.charactersheet.gui.sheet

import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.android.ash.charactersheet.gui.sheet.combat.CombatEditPanel
import com.android.ash.charactersheet.gui.sheet.combat.CombatPanel
import com.android.ash.charactersheet.gui.sheet.combat.DnD5eCombatEditPanel
import com.android.ash.charactersheet.gui.sheet.combat.DnD5eCombatPanel
import com.android.ash.charactersheet.gui.sheet.combat.DnDv35CombatEditPanel
import com.android.ash.charactersheet.gui.sheet.combat.DnDv35CombatPanel
import com.android.ash.charactersheet.gui.sheet.save.DnD5eSavePanel
import com.android.ash.charactersheet.gui.sheet.save.DnDv35SavePanel
import com.android.ash.charactersheet.gui.sheet.save.SavePanel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SheetPanelFactory : KoinComponent {

    private val gameSystemHolder: GameSystemHolder by inject()

    fun createCombatPanel(): CombatPanel =
        when (gameSystemHolder.gameSystemType) {
            GameSystemType.DND5E -> DnD5eCombatPanel()
            else -> DnDv35CombatPanel()
        }

    fun createSavePanel(): SavePanel =
        when (gameSystemHolder.gameSystemType) {
            GameSystemType.DND5E -> DnD5eSavePanel()
            else -> DnDv35SavePanel()
        }

    fun createSaveEditPanel(): SaveEditPanel =
        when (gameSystemHolder.gameSystemType) {
            GameSystemType.DND5E -> DnD5eSaveEditPanel()
            else -> DnDv35SaveEditPanel()
        }

    fun createCombatEditPanel(): CombatEditPanel =
        when (gameSystemHolder.gameSystemType) {
            GameSystemType.DND5E -> DnD5eCombatEditPanel()
            else -> DnDv35CombatEditPanel()
        }

}
