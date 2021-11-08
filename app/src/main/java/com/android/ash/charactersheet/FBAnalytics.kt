package com.android.ash.charactersheet

object FBAnalytics {

    object UserProperty {
        const val GAME_SYSTEM = "game_system"
        const val CHARACTER_COUNT = "character_count"
    }

    object Event {
        const val CHARACTER_OPEN = "character_open"
        const val CHARACTER_CREATE = "character_create"
        const val CHARACTER_DELETE = "character_delete"
        const val IMAGE_ADD = "image_add"
        const val THUMBNAIL_ADD = "thumbnail_add"
        const val WEAPON_ATTACK_CREATE = "weapon_attack_create"
        const val SPELL_DESCRIPTION_SHOW = "spell_description_show"
        const val WEAPON_EDIT = "weapon_edit"
        const val ARMOR_EDIT = "armor_edit"
        const val GOOD_EDIT = "good_edit"
        const val SPELL_SLOT_ASSIGN = "spell_slot_assign"
        const val DIE_ROLL = "die_roll"
        const val PURCHASE_DIALOG_SHOW = "purchase_dialog_show"
        const val PURCHASE_PERFORM = "purchase_perform"
        const val PURCHASE_CANCEL = "purchase_cancel"
        const val PURCHASE_RESTORE = "purchase_restore"
        const val STANDARD_METHOD_DICE_ROLL = "standard_method_dice_roll"
        const val GAME_SYSTEM_SELECT_DNDV35 = "game_system_select_dndv35"
        const val GAME_SYSTEM_SELECT_DND5E = "game_system_select_dnd5e"
        const val GAME_SYSTEM_SELECT_PATHFINDER = "game_system_select_pathfinder"
        const val EQUIPMENT_SELECTION = "equipment_selection"
    }

    object Param {
        const val CLASS_LEVELS = "class_levels"
        const val RACE_NAME = "race_name"
        const val CLASS_NAME = "class_name"
        const val WEAPON_NAME = "weapon_name"
        const val SPELL_NAME = "spell_name"
        const val DIE_ROLL_NAME = "die_roll_name"
    }

    object ScreenName {
        const val SHEET = "sheet_fragment"
        const val WEAPON_ATTACK = "weapon_attack_fragment"
        const val SKILL = "skill_fragment"
        const val RACE_ABILITY = "race_ability_fragment"
        const val CLASS_ABILITY = "class_ability_fragment"
        const val FEAT = "feat_fragment"
        const val EQUIPMENT = "equipment_fragment"
        const val NOTE = "note_fragment"
        const val KNOWN_SPELL = "known_spell_fragment"
        const val SPELL_SLOT = "spell_slot_fragment"
        const val RACE = "race_fragment"
        const val CLASS = "class_fragment"
    }

}
