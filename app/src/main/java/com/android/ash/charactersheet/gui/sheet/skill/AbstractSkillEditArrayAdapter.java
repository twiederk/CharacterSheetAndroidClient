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
import com.d20charactersheet.framework.boc.service.CharacterService;
import com.d20charactersheet.framework.boc.service.GameSystem;
import com.d20charactersheet.framework.boc.service.RuleService;

/**
 * Adapter of skill edit list.
 */
public abstract class AbstractSkillEditArrayAdapter extends DisplayArrayAdapter<CharacterSkill> {

    protected final RuleService ruleService;
    protected final CharacterService characterService;
    protected final Character character;

    protected final MessageManager messageManager;

    /**
     * Creates adapter of skill edit list.
     *
     * @param context            The context activity.
     * @param character          The character of the skills. Necessary to determine class and cross class skills.
     * @param gameSystem         The game system provides access to other services.
     * @param itemViewResourceId The resource id of the item view.
     */
    AbstractSkillEditArrayAdapter(final Context context, final Character character, final GameSystem gameSystem,
                                  final int itemViewResourceId) {
        super(context, gameSystem.getDisplayService(), itemViewResourceId, character.getCharacterSkills());
        this.character = character;
        this.ruleService = gameSystem.getRuleService();
        this.characterService = gameSystem.getCharacterService();
        messageManager = new MessageManager(context, displayService);
    }

    /**
     * Fills item view with name of skill. NumberViews are used to display and modify skill rank and modifier.
     */
    @Override
    protected void fillView(final View view, final CharacterSkill characterSkill) {
        setSkillName(view, characterSkill);
        setSkillRank(view, characterSkill);
        setSkillProficiency(view, characterSkill);
        setSkillMiscModifier(view, characterSkill);
    }

    private void setSkillName(View view, CharacterSkill characterSkill) {
        final TextView skillNameTextView = view.findViewById(R.id.skill_name);
        skillNameTextView.setText(characterSkill.getSkill().getName());
    }

    protected abstract void setSkillRank(View view, CharacterSkill characterSkill);

    protected abstract void setSkillProficiency(View view, CharacterSkill characterSkill);

    private void setSkillMiscModifier(View view, CharacterSkill characterSkill) {
        final StepNumberView skillModifierNumberView = view.findViewById(R.id.skill_modifier);
        skillModifierNumberView.setController(new SkillModifierNumberViewController(characterService, character,
                characterSkill));
    }

}
