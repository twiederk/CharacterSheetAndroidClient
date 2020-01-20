package com.android.ash.charactersheet.gui.admin.skill;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.Skill;

import java.util.Objects;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

/**
 * Allows to edit an existing skill.
 */
public class SkillAdministrationEditActivity extends SkillAdministrationActivity {

    @Override
    protected Skill createForm() {
        final int skillId = Objects.requireNonNull(getIntent().getExtras()).getInt(INTENT_EXTRA_DATA_OBJECT);
        return gameSystem.getSkillService().getSkillById(skillId, gameSystem.getAllSkills());
    }

    @Override
    protected void saveForm() {
        skillService.updateSkill(form);
        super.saveForm();
        gameSystem.clear();
    }

    @Override
    protected String getHeading() {
        return getResources().getString(R.string.skill_administration_edit_title);
    }

}
