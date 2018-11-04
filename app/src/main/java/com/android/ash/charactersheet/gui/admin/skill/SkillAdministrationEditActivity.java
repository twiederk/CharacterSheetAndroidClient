package com.android.ash.charactersheet.gui.admin.skill;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.Skill;

/**
 * Allows to edit an existing skill.
 */
public class SkillAdministrationEditActivity extends SkillAdministrationActivity {

    @Override
    protected Skill createForm() {
        final int skillId = getIntent().getExtras().getInt(INTENT_EXTRA_DATA_OBJECT);
        final Skill skill = gameSystem.getSkillService().getSkillById(skillId, gameSystem.getAllSkills());
        return skill;
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
