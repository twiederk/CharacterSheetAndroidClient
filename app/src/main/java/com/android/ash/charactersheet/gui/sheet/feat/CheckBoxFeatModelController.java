package com.android.ash.charactersheet.gui.sheet.feat;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

import com.android.ash.charactersheet.gui.util.Logger;

/**
 * The controller between the show all Feats checkbox and the FeatModel.
 */
public class CheckBoxFeatModelController implements OnClickListener {

    private final FeatModel featModel;
    private final CheckBox checkBox;

    /**
     * Instanciates controller connecting the given FeatModel and CheckBox.
     * 
     * @param featModel
     *            The feat model to modify.
     * @param checkBox
     *            The Checkbox (view) to get the events of.
     */
    public CheckBoxFeatModelController(final FeatModel featModel, final CheckBox checkBox) {
        this.featModel = featModel;
        this.checkBox = checkBox;
    }

    /**
     * Modifies the FeatModel if the checkbox is modified.
     * 
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(final View view) {
        featModel.setShowAllFeats(checkBox.isChecked());
        Logger.debug("featModel: " + featModel);
    }

}
