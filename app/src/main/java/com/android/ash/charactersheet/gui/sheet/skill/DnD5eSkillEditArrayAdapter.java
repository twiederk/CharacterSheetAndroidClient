package com.android.ash.charactersheet.gui.sheet.skill;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.widget.numberview.StepNumberView;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.CharacterSkill;
import com.d20charactersheet.framework.boc.service.GameSystem;

/**
 * Adapter of skill edit list.
 */
public class DnD5eSkillEditArrayAdapter extends AbstractSkillEditArrayAdapter {

    /**
     * Creates adapter of skill edit list.
     *
     * @param context            The context activity.
     * @param character          The character of the skills. Necessary to determine class and cross class skills.
     * @param gameSystem         The game system provides access to other services.
     * @param itemViewResourceId The resource id of the item view.
     */
    DnD5eSkillEditArrayAdapter(final Context context, final Character character, final GameSystem gameSystem,
                               final int itemViewResourceId) {
        super(context, character, gameSystem, itemViewResourceId);
    }

    protected void setSkillRank(View view, CharacterSkill characterSkill) {
        final View skillRankLabel = view.findViewById(R.id.skill_rank_label);
        skillRankLabel.setVisibility(View.GONE);

        final StepNumberView skillRank = view.findViewById(R.id.skill_rank);
        skillRank.setVisibility(View.GONE);
    }

    protected void setSkillProficiency(View view, CharacterSkill characterSkill) {
        final CheckBox proficiencyCheckbox = view.findViewById(R.id.skill_proficiency);
        proficiencyCheckbox.setChecked(characterSkill.getRank() != 0.0F);
        proficiencyCheckbox.setOnClickListener(view1 -> {
            final CheckBox checkBox = (CheckBox) view1;
            if (checkBox.isChecked()) {
                characterSkill.setRank(1.0F);
            } else {
                characterSkill.setRank(0.0F);
            }
            characterService.updateCharacterSkill(character, characterSkill);
        });
    }

}
