package com.android.ash.charactersheet.gui.sheet

import android.content.res.Resources
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.android.ash.charactersheet.R

/**
 * A [FragmentStatePagerAdapter] implementation providing page fragments for DnDv35 and Pathfinder
 */
internal class DnDv35FragmentPager(fragmentManager: FragmentManager?, resources: Resources) : AbstractFragmentPager(

        arrayOf( //
                SheetPageFragment(),  //
                WeaponAttackPageFragment(),  //
                SkillPageFragment(),  //
                RaceAbilityPageFragment(),  //
                ClassAbilityPageFragment(),  //
                FeatPageFragment(),  //
                EquipmentPageFragment(),  //
                NotePageFragment(),  //
                KnownSpellPageFragment(),  //
                SpellSlotPageFragment()),

        arrayOf( //
                resources.getString(R.string.page_sheet_title),  //
                resources.getString(R.string.page_attack_title),  //
                resources.getString(R.string.page_skill_title),  //
                resources.getString(R.string.page_race_ability_title),  //
                resources.getString(R.string.page_class_ability_title),  //
                resources.getString(R.string.page_feat_title),  //
                resources.getString(R.string.page_equip_title),  //
                resources.getString(R.string.page_note_title),  //
                resources.getString(R.string.page_known_spell_title),  //
                resources.getString(R.string.page_spell_slot_title)),

        fragmentManager = fragmentManager)
