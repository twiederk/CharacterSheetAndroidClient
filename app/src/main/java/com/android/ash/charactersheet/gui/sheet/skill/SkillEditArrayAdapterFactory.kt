package com.android.ash.charactersheet.gui.sheet.skill

import android.content.Context
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.android.ash.charactersheet.gui.widget.DisplayArrayAdapter
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.model.CharacterSkill
import com.d20charactersheet.framework.boc.service.GameSystem
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SkillEditArrayAdapterFactory : KoinComponent {

    private val gameSystemHolder: GameSystemHolder by inject()

    fun createSkillEditArrayAdapter(
        context: Context, character: Character, gameSystem: GameSystem
    ): DisplayArrayAdapter<CharacterSkill> = when (gameSystemHolder.gameSystemType) {
        GameSystemType.DND5E -> DnD5eSkillEditArrayAdapter(
            context,
            character,
            gameSystem,
            R.layout.listitem_skill_edit
        )
        else -> DnDv35SkillEditArrayAdapter(
            context,
            character,
            gameSystem,
            R.layout.listitem_skill_edit
        )
    }

}