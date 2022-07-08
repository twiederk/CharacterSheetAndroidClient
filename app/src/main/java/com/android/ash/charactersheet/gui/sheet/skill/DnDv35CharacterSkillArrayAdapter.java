package com.android.ash.charactersheet.gui.sheet.skill;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.widget.dierollview.DieRollView;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.CharacterSkill;
import com.d20charactersheet.framework.boc.service.DisplayService;
import com.d20charactersheet.framework.boc.service.RuleService;

import java.util.List;

/**
 * Adapter of skill list.
 */
public class DnDv35CharacterSkillArrayAdapter extends AbstractCharacterSkillArrayAdapter {

    /**
     * Creates adapter of skill list.
     *
     * @param context            The context.
     * @param character          The character owning the skills.
     * @param ruleService        The rule service.
     * @param displayService     The display service.
     * @param itemViewResourceId The resource id to display a item of the skill list.
     * @param dieRollView        The DieRollView to display the result of a skill roll.
     * @param characterSkills    The character skills.
     */
    public DnDv35CharacterSkillArrayAdapter(final Context context, final Character character, final RuleService ruleService,
                                            final DisplayService displayService, final int itemViewResourceId, final DieRollView dieRollView,
                                            final List<CharacterSkill> characterSkills) {
        super(context, character, ruleService, displayService, itemViewResourceId, dieRollView, characterSkills);
    }

    @Override
    protected void fillRank(View view, CharacterSkill characterSkill) {
        final TextView rankTextView = view.findViewById(R.id.skill_rank);
        rankTextView.setText(getRankModifier(characterSkill));
    }

    private String getRankModifier(final CharacterSkill characterSkill) {
        return getContext().getResources().getString(R.string.skill_list_rank) + COLON + characterSkill.getRank();
    }

}
