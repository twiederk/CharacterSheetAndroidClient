package com.android.ash.charactersheet.gui.sheet;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.ash.charactersheet.R;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the sections/tabs/pages.
 */
class PageAdapter extends FragmentPagerAdapter {

    private final PageFragment[] pageFragments;
    private final String[] pageTitles;

    /**
     * Instanciates PageFragment of character sheet.
     * 
     * @param fragmentManager
     *            The manager of fragments.
     * @param resources
     *            The resources to read strings from.
     */
    public PageAdapter(final FragmentManager fragmentManager, final Resources resources) {
        super(fragmentManager);

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
