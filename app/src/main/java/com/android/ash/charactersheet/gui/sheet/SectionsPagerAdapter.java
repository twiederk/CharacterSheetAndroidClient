package com.android.ash.charactersheet.gui.sheet;

import android.content.res.Resources;

import com.android.ash.charactersheet.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the sections/tabs/pages.
 */
class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    private final PageFragment[] pageFragments;
    private final String[] pageTitles;

    /**
     * Instantiates PageFragment of character sheet.
     *
     * @param fragmentManager
     *            The manager of fragments.
     * @param resources
     *            The resources to read strings from.
     */
    SectionsPagerAdapter(final FragmentManager fragmentManager, final Resources resources) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        pageFragments = new PageFragment[] { //
        //
                new SheetPageFragment(), //
                new WeaponAttackPageFragment(), //
                new SkillPageFragment(), //
                new RaceAbilityPageFragment(), //
                new ClassAbilityPageFragment(), //
                new FeatPageFragment(), //
                new EquipmentPageFragement(), //
                new NotePageFragment(), //
                new KnownSpellPageFragment(), //
                new SpellSlotPageFragment(), //
        };

        pageTitles = new String[] { //
        //
                resources.getString(R.string.page_sheet_title), //
                resources.getString(R.string.page_attack_title), //
                resources.getString(R.string.page_skill_title), //
                resources.getString(R.string.page_race_ability_title), //
                resources.getString(R.string.page_class_ability_title), //
                resources.getString(R.string.page_feat_title), //
                resources.getString(R.string.page_equip_title), //
                resources.getString(R.string.page_note_title), //
                resources.getString(R.string.page_known_spell_title), //
                resources.getString(R.string.page_spell_slot_title), //
        };
    }

    @NonNull
    @Override
    public Fragment getItem(final int position) {
        return pageFragments[position];
    }

    @Override
    public int getCount() {
        return pageFragments.length;
    }

    @Override
    public CharSequence getPageTitle(final int position) {
        return pageTitles[position];
    }
}
