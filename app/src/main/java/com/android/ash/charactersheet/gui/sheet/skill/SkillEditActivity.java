package com.android.ash.charactersheet.gui.sheet.skill;

import android.widget.ListView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.BaseCharacterSheetActivity;
import com.d20charactersheet.framework.boc.util.CharacterSkillComparator;

/**
 * The SkillEditActivity contains a list view with an item for each skill. The rank and the modifier for each skill can
 * be edited by using NumberViews. The option menu allows to save or cancel the edited skill values.
 */
public class SkillEditActivity extends BaseCharacterSheetActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.skill_edit;
    }

    @Override
    protected void doCreate() {
        setSkillList();
    }

    private void setSkillList() {
        final SkillEditArrayAdapter skillsArrayAdapter = new SkillEditArrayAdapter(this, character, gameSystem,
                R.layout.listitem_skill_edit);
        skillsArrayAdapter.sort(new CharacterSkillComparator());
        final ListView listView = findViewById(R.id.skill_edit_list);
        listView.setAdapter(skillsArrayAdapter);
        listView.setTextFilterEnabled(true);
    }

}
