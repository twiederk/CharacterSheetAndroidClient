package com.android.ash.charactersheet.gui.sheet.skill;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.widget.DisplayArrayAdapter;
import com.android.ash.charactersheet.gui.widget.dierollview.DieRollView;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.CharacterSkill;
import com.d20charactersheet.framework.boc.service.DisplayService;
import com.d20charactersheet.framework.boc.service.RuleService;

/**
 * Adapter of skill list.
 */
public class CharacterSkillArrayAdapter extends DisplayArrayAdapter<CharacterSkill> {

    private static final String COLON = ": ";

    private final RuleService ruleService;
    private final Character character;
    private final DieRollView dieRollView;

    /**
     * Creates adapter of skill list.
     * 
     * @param context
     *            The context.
     * @param character
     *            The character owning the skills.
     * @param ruleService
     *            The rule service.
     * @param displayService
     *            The display service.
     * @param itemViewResourceId
     *            The resource id to display a item of the skill list.
     * @param dieRollView
     *            The DieRollView to display the result of a skill roll.
     * @param characterSkills
     *            The character skills.
     */
    public CharacterSkillArrayAdapter(final Context context, final Character character, final RuleService ruleService,
            final DisplayService displayService, final int itemViewResourceId, final DieRollView dieRollView,
            final List<CharacterSkill> characterSkills) {
        super(context, displayService, itemViewResourceId, characterSkills);
        this.character = character;
        this.dieRollView = dieRollView;
        this.ruleService = ruleService;
    }

    /**
     * Fill list item view with name of skill. The calculated skill modifer, the attribute, the rank and modifier.
     */
    @Override
    protected void fillView(final View view, final CharacterSkill characterSkill) {

        // first row
        final TextView skillNameTextView = (TextView) view.findViewById(R.id.skill_name);
        skillNameTextView.setText(characterSkill.getSkill().getName());

        final Button skillModifierButton = (Button) view.findViewById(R.id.skill_roll_button);
        final int skillModifier = ruleService.getSkillModifier(character, characterSkill);
        skillModifierButton.setText(displayService.getDisplayModifier(skillModifier));

        skillModifierButton.setOnClickListener(new SkillRollOnClickListener(character, characterSkill, ruleService,
                dieRollView));

        // second row
        final TextView attributeModifierTextView = (TextView) view.findViewById(R.id.skill_attribute_modifier);
        attributeModifierTextView.setText(getAttributeModifier(characterSkill));

        final TextView rankTextView = (TextView) view.findViewById(R.id.skill_rank);
        rankTextView.setText(getRankModifier(characterSkill));

        final TextView modifierTextView = (TextView) view.findViewById(R.id.skill_modifier);
        modifierTextView.setText(getModifier(characterSkill));
    }

    private String getAttributeModifier(final CharacterSkill characterSkill) {
        final int attributeModifier = ruleService.getAttributeModifier(character, characterSkill.getSkill()
                .getAttribute());
        final StringBuilder text = new StringBuilder();
        text.append(displayService.getDisplayAttributeShort(characterSkill.getSkill().getAttribute()));
        text.append(COLON);
        text.append(displayService.getDisplayModifier(attributeModifier));
        return text.toString();
    }

    private String getRankModifier(final CharacterSkill characterSkill) {
        final StringBuilder text = new StringBuilder();
        text.append(getContext().getResources().getString(R.string.skill_list_rank));
        text.append(COLON);
        text.append(characterSkill.getRank());
        return text.toString();
    }

    private CharSequence getModifier(final CharacterSkill characterSkill) {
        final StringBuilder text = new StringBuilder();
        text.append(getContext().getResources().getString(R.string.skill_list_mod));
        text.append(COLON);
        text.append(displayService.getDisplayModifier(characterSkill.getModifier()));
        return text.toString();
    }
}
