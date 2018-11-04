package com.android.ash.charactersheet.gui.sheet.knownspell;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

/**
 * Controller of Show All check box on SpellListPageFragment.
 */
public class CheckBoxSpelllistModelController implements OnClickListener {

    private final KnownSpellPageModel spelllistModel;

    /**
     * Creates controller which connects the ShowAll checkbox with the model.
     * 
     * @param spelllistModel
     *            The model storing class abilties.
     */
    public CheckBoxSpelllistModelController(final KnownSpellPageModel spelllistModel) {
        this.spelllistModel = spelllistModel;
    }

    @Override
    public void onClick(final View view) {
        final CheckBox checkBox = (CheckBox) view;
        spelllistModel.setShowAll(checkBox.isChecked());
    }

}
