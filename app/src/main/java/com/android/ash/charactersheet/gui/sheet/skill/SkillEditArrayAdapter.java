package com.android.ash.charactersheet.gui.sheet.skill;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.MessageManager;
import com.android.ash.charactersheet.gui.widget.DisplayArrayAdapter;
import com.android.ash.charactersheet.gui.widget.numberview.StepNumberView;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.CharacterSkill;
import com.d20charactersheet.framework.boc.model.Skill;
import com.d20charactersheet.framework.boc.service.CharacterService;
import com.d20charactersheet.framework.boc.service.GameSystem;
import com.d20charactersheet.framework.boc.service.RuleService;

/**
 * Adapter of skill edit list.
 */
public class SkillEditArrayAdapter extends DisplayArrayAdapter<CharacterSkill> {

    private final RuleService ruleService;
    private final CharacterService characterService;
    private final Character character;

    private final int maxClassSkillRank;
    private final float maxCrossClassSkillRank;

    private final MessageManager messageManager;

    /**
     * Creates adapter of skill edit list.
     * 
     * @param context
     *            The context activity.
     * @param character
     *            The character of the skills. Necessary to determine class and cross class skills.
     * @param gameSystem
     *            The game system provides access to other services.
     * @param itemViewResourceId
     *            The resource id of the item view.
     */
    public SkillEditArrayAdapter(final Context context, final Character character, final GameSystem gameSystem,
            final int itemViewResourceId) {
        super(context, gameSystem.getDisplayService(), itemViewResourceId, character.getCharacterSkills());
        this.character = character;
        this.ruleService = gameSystem.getRuleService();
        this.characterService = gameSystem.getCharacterService();
        this.maxClassSkillRank = ruleService.getMaxClassSkillRank(character);
        this.maxCrossClassSkillRank = ruleService.getMaxCrossClassSkillRank(character);
        messageManager = new MessageManager(context, displayService);

    }

    /**
     * Fills item view with name of skill. NumberViews are used to display and modify skill rank and modifier.
     */
    @Override
    protected void fillView(final View view, final CharacterSkill characterSkill) {
        final TextView skillNameTextView = (TextView) view.findViewById(R.id.skill_name);
        skillNameTextView.setText(characterSkill.getSkill().getName());

        final StepNumberView skillRankNumberView = (StepNumberView) view.findViewById(R.id.skill_rank);

        final float maxRank = getMaxRank(characterSkill.getSkill());
        final float step = ruleService.getRankPerSkillPoint(character, characterSkill.getSkill());

        skillRankNumberView.setController(new SkillRankNumberViewController(characterService, character,
                characterSkill, maxRank, step, messageManager));

        final StepNumberView skillModifierNumberView = (StepNumberView) view.findViewById(R.id.skill_modifier);
        skillModifierNumberView.setController(new SkillModifierNumberViewController(characterService, character,
                characterSkill));

    }

    private float getMaxRank(final Skill skill) {
        if (ruleService.isClassSkill(character, skill)) {
            return maxClassSkillRank;
        }
        return maxCrossClassSkillRank;
    }
}
