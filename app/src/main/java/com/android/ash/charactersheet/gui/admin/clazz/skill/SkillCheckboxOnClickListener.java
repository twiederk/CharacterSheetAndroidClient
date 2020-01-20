package com.android.ash.charactersheet.gui.admin.clazz.skill;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

/**
 * OnClickListener of the checkboxes in the character class skill activity.
 */
class SkillCheckboxOnClickListener implements OnClickListener {

    private final SkillModel skillModel;

    /**
     * Creates SkillCheckboxOnClickListener with the given skill model.
     * 
     * @param skillModel
     *            The skill model storing the skill and if it is a class skill or not.
     */
    public SkillCheckboxOnClickListener(final SkillModel skillModel) {
        this.skillModel = skillModel;
    }

    /**
     * Updates the skill model according to the state of the checkbox.
     * 
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(final View view) {
        final CheckBox checkBox = (CheckBox) view;
        skillModel.setClassSkill(checkBox.isChecked());
    }

}
