package com.android.ash.charactersheet.gui.sheet.classability;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

/**
 * Controller of the ShowAll checkbox in the CharacterClassAbilityActivity.
 */
public class CheckBoxModelController implements OnClickListener {

    private final CharacterAbilityModel model;
    private final CheckBox checkBox;

    /**
     * Creates controller which connects the ShowAll checkbox with the model.
     * 
     * @param model
     *            The model storing class abilities.
     * @param checkBox
     *            The checkbox to show all abilities or only those gained by level.
     */
    public CheckBoxModelController(final CharacterAbilityModel model, final CheckBox checkBox) {
        this.model = model;
        this.checkBox = checkBox;
    }

    @Override
    public void onClick(final View arg0) {
        model.setShowAll(checkBox.isChecked());
    }

}
