package com.android.ash.charactersheet.gui.admin.skill;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.Attribute;
import com.d20charactersheet.framework.boc.model.Skill;

/**
 * Allows to create an news skill.
 */
public class SkillAdministrationCreateActivity extends SkillAdministrationActivity {

    @Override
    protected Skill createForm() {
        final Skill skill = new Skill();
        skill.setName("");
        skill.setDescription("");
        skill.setAttribute(Attribute.STRENGTH);
        return skill;
    }

    @Override
    protected void saveForm() {
        if (!form.getName().trim().isEmpty()) {
            form = skillService.createSkill(form);
            super.saveForm();
            gameSystem.clear();
        }
    }

    @Override
    protected String getHeading() {
        return getResources().getString(R.string.skill_administration_create_title);
    }

}
