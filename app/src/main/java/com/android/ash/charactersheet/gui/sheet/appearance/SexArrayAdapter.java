package com.android.ash.charactersheet.gui.sheet.appearance;

import android.content.Context;

import com.android.ash.charactersheet.gui.widget.SpinnerArrayAdapter;
import com.d20charactersheet.framework.boc.model.Sex;
import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * ArrayAdapter to display sexes in a ListView or Spinner.
 */
public class SexArrayAdapter extends SpinnerArrayAdapter<Sex> {

    /**
     * Create instance to display given sexes.
     * 
     * @param context
     *            The context of the view.
     * @param displayService
     *            The display service to display data in the GUI.
     * @param sexes
     *            The sexes to display.
     */
    public SexArrayAdapter(final Context context, final DisplayService displayService, final Sex[] sexes) {
        super(context, displayService, sexes);
    }

    @Override
    protected String getText(final Sex sex) {
        return displayService.getDisplaySex(sex);
    }

}
