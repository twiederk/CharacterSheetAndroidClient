package com.android.ash.charactersheet.gui.sheet.skill;

import android.content.Context;
import android.view.View;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.widget.numberview.StepNumberView;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.CharacterSkill;
import com.d20charactersheet.framework.boc.model.Skill;
import com.d20charactersheet.framework.boc.service.GameSystem;

/**
 * Adapter of skill edit list for DnDv35.
 */
public class DnDv35SkillEditArrayAdapter extends AbstractSkillEditArrayAdapter {

    /**
     * Creates adapter of skill edit list.
     *
     * @param context            The context activity.
     * @param character          The character of the skills. Necessary to determine class and cross class skills.
     * @param gameSystem         The game system provides access to other services.
     * @param itemViewResourceId The resource id of the item view.
     */
    DnDv35SkillEditArrayAdapter(final Context context, final Character character, final GameSystem gameSystem, final int itemViewResourceId) {
        super(context, character, gameSystem, itemViewResourceId);
    }

    protected void setSkillRank(View view, CharacterSkill characterSkill) {
        final StepNumberView skillRankNumberView = view.findViewById(R.id.skill_rank);

        final float maxRank = getMaxRank(characterSkill.getSkill());
        final float step = ruleService.getRankPerSkillPoint(character, characterSkill.getSkill());

        skillRankNumberView.setController(new SkillRankNumberViewController(characterService, character,
                characterSkill, maxRank, step, messageManager));
    }

    private float getMaxRank(final Skill skill) {
        if (ruleService.isClassSkill(character, skill)) {
            return ruleService.getMaxClassSkillRank(character);
        }
        return ruleService.getMaxCrossClassSkillRank(character);
    }

    protected void setSkillProficiency(View view, CharacterSkill characterSkill) {
        final View skillProfessionLabel = view.findViewById(R.id.skill_proficiency_label);
        skillProfessionLabel.setVisibility(View.GONE);

        final View skillProfession = view.findViewById(R.id.skill_proficiency);
        skillProfession.setVisibility(View.GONE);
    }

}
