package com.android.ash.charactersheet.gui.sheet.skill;

import static org.koin.java.KoinJavaComponent.inject;

import android.widget.ListView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.BaseCharacterSheetActivity;
import com.android.ash.charactersheet.gui.widget.DisplayArrayAdapter;
import com.d20charactersheet.framework.boc.model.CharacterSkill;
import com.d20charactersheet.framework.boc.util.CharacterSkillComparator;

import kotlin.Lazy;

/**
 * The SkillEditActivity contains a list view with an item for each skill. The rank and the modifier for each skill can
 * be edited by using NumberViews. The option menu allows to save or cancel the edited skill values.
 */
public class SkillEditActivity extends BaseCharacterSheetActivity {

    private final Lazy<SkillEditArrayAdapterFactory> skillEditArrayAdapterFactory = inject(SkillEditArrayAdapterFactory.class);

    @Override
    protected int getLayoutId() {
        return R.layout.skill_edit;
    }

    @Override
    protected void doCreate() {
        setSkillList();
    }

    private void setSkillList() {
        final DisplayArrayAdapter<CharacterSkill> skillsArrayAdapter =
                skillEditArrayAdapterFactory.getValue().createSkillEditArrayAdapter(this, character, gameSystem);
        skillsArrayAdapter.sort(new CharacterSkillComparator());
        final ListView listView = findViewById(R.id.skill_edit_list);
        listView.setAdapter(skillsArrayAdapter);
        listView.setTextFilterEnabled(true);
    }

}
