package com.android.ash.charactersheet.gui.sheet;

import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.sheet.skill.DnDv35CharacterSkillArrayAdapter;
import com.android.ash.charactersheet.gui.widget.DisplayArrayAdapter;
import com.android.ash.charactersheet.gui.widget.dierollview.DieRollView;
import com.d20charactersheet.framework.boc.model.CharacterClass;
import com.d20charactersheet.framework.boc.model.CharacterSkill;

import java.util.List;

/**
 * The SkillListActivity displays the max class rank and max cross class rank of the character. It displays the skill
 * points of the character. The skills are displayed in three different skill lists. Each skill list is contained in a
 * separate tab. The option menu allows to call EditSkillActivity. The context menu of the skill lists allow to add or
 * remove them from favorite skill list.
 */
public class DnDv35SkillPageFragment extends com.android.ash.charactersheet.gui.sheet.AbstractSkillPageFragment {

    protected void setHeader() {
        final TextView maxRanksTextView = view.findViewById(R.id.max_ranks);
        maxRanksTextView.setText(getMaxRank());

        final TextView skillPointsTextView = view.findViewById(R.id.skill_points);
        skillPointsTextView.setText(getSkillPoints());
    }

    private String getMaxRank() {
        final int maxClassSkillRank = ruleService.getMaxClassSkillRank(character);
        final float maxCrossClassSkillRank = ruleService.getMaxCrossClassSkillRank(character);
        return getResources().getString(R.string.skill_list_max_ranks) + ": " + maxClassSkillRank + " / " + maxCrossClassSkillRank;
    }

    private String getSkillPoints() {
        final CharacterClass startClass = character.getClassLevels().get(0).getCharacterClass();
        final int skillPoints = ruleService.getSkillPoints(character, startClass);
        final int spentSkillPoints = ruleService.getSpentSkillPoints(character);
        return getResources().getString(R.string.skill_list_skill_points) + ": " + spentSkillPoints + " " + getResources().getString(R.string.skill_list_skill_points_of) + " " + skillPoints;
    }

    protected DisplayArrayAdapter<CharacterSkill> createCharacterSkillArrayAdapter(DieRollView dieRollView, List<CharacterSkill> characterSkills) {
        return new DnDv35CharacterSkillArrayAdapter(getActivity(),
                character, ruleService, displayService, R.layout.listitem_skill, dieRollView, characterSkills);
    }

}
