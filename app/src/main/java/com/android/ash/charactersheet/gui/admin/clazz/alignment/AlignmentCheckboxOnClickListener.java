package com.android.ash.charactersheet.gui.admin.clazz.alignment;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

/**
 * OnClickListener for the alignment checkbox in the CharacterClassAlignmentActivity. It updates the model of the
 * alignment according to the state of the checkbox.
 */
class AlignmentCheckboxOnClickListener implements OnClickListener {

    private final AlignmentModel alignmentModel;

    /**
     * Creates OnClickListener with given alignment model.
     * 
     * @param alignmentModel
     *            The alignment model containing the alignment.
     */
    public AlignmentCheckboxOnClickListener(final AlignmentModel alignmentModel) {
        this.alignmentModel = alignmentModel;
    }

    /**
     * Updates the alignment model if clicked.
     * 
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(final View view) {
        final CheckBox checkBox = (CheckBox) view;
        alignmentModel.setClassAlignment(checkBox.isChecked());
    }

}
