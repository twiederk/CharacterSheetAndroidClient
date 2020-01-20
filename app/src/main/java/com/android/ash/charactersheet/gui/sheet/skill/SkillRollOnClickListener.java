package com.android.ash.charactersheet.gui.sheet.skill;

import android.view.View;
import android.view.View.OnClickListener;

import com.android.ash.charactersheet.gui.widget.dierollview.DefaultDieRollViewController;
import com.android.ash.charactersheet.gui.widget.dierollview.DieRollView;
import com.android.ash.charactersheet.gui.widget.dierollview.DieRollViewController;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.CharacterSkill;
import com.d20charactersheet.framework.boc.model.DieRoll;
import com.d20charactersheet.framework.boc.service.RuleService;

/**
 * OnClick Listener executing skill roll on click and displaying result.
 */
class SkillRollOnClickListener implements OnClickListener {

    private final Character character;
    private final CharacterSkill characterSkill;
    private final RuleService ruleService;
    private final DieRollView skillRollView;

    /**
     * Creates OnClickListener to execute skill roll on given character using rule service. Displaying result on
     * DieRollView.
     * 
     * @param character
     *            The character performing the skill roll.
     * @param characterSkill
     *            The skill to check.
     * @param ruleService
     *            Necessary to execute skill roll.
     * @param skillRollView
     *            The DieRollView to display the result.
     */
    public SkillRollOnClickListener(final Character character, final CharacterSkill characterSkill,
            final RuleService ruleService, final DieRollView skillRollView) {
        this.character = character;
        this.characterSkill = characterSkill;
        this.ruleService = ruleService;
        this.skillRollView = skillRollView;
    }

    @Override
    public void onClick(final View v) {
        final String title = characterSkill.getSkill().getName();
        final DieRoll skillRoll = ruleService.rollSkill(character, characterSkill);
        final DieRollViewController controller = new DefaultDieRollViewController(title, skillRoll);
        skillRollView.setController(controller);
        skillRollView.setVisibility(View.VISIBLE);
    }
}
