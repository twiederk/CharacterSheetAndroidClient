package com.android.ash.charactersheet.gui.admin.skill;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

/**
 * Handles clicks on character classes checkboxes. If a checkbox is clicked the skill is a class skill of this class.
 */
public class CharacterClassCheckboxOnClickListener implements OnClickListener {

    private final CharacterClassSkillModel model;

    /**
     * Creates Listener which stores status in the given model, if clicked.
     * 
     * @param model
     *            The model contains which classes the skill is a class skill of.
     */
    public CharacterClassCheckboxOnClickListener(final CharacterClassSkillModel model) {
        this.model = model;
    }

    /**
     * Updates the model with the checkbox status.
     * 
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(final View view) {
        final CheckBox checkBox = (CheckBox) view;
        model.setClassSkill(checkBox.isChecked());
    }

}
