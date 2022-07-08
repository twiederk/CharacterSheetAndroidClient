package com.android.ash.charactersheet.gui.sheet;

import android.view.View;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.sheet.skill.DnD5eCharacterSkillArrayAdapter;
import com.android.ash.charactersheet.gui.widget.DisplayArrayAdapter;
import com.android.ash.charactersheet.gui.widget.dierollview.DieRollView;
import com.d20charactersheet.framework.boc.model.CharacterSkill;

import java.util.List;

/**
 * The SkillListActivity displays the max class rank and max cross class rank of the character. It displays the skill
 * points of the character. The skills are displayed in three different skill lists. Each skill list is contained in a
 * separate tab. The option menu allows to call EditSkillActivity. The context menu of the skill lists allow to add or
 * remove them from favorite skill list.
 */
public class DnD5eSkillPageFragment extends AbstractSkillPageFragment {

    protected void setHeader() {
        final View page_skill_header = view.findViewById(R.id.page_skill_header);
        page_skill_header.setVisibility(View.GONE);
    }

    @Override
    protected DisplayArrayAdapter<CharacterSkill> createCharacterSkillArrayAdapter(DieRollView dieRollView, List<CharacterSkill> characterSkills) {
        return new DnD5eCharacterSkillArrayAdapter(getActivity(), character, ruleService, displayService, R.layout.listitem_skill, dieRollView, characterSkills);
    }
}
