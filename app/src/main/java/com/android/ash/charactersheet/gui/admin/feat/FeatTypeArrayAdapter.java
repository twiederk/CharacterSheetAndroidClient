package com.android.ash.charactersheet.gui.admin.feat;

import java.util.List;

import android.content.Context;

import com.android.ash.charactersheet.gui.widget.SpinnerArrayAdapter;
import com.d20charactersheet.framework.boc.model.FeatType;
import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * ArrayAdapter to display feat types in a ListView or Spinner.
 */
public class FeatTypeArrayAdapter extends SpinnerArrayAdapter<FeatType> {

    /**
     * Creates ArrayAdapter with given feat types.
     * 
     * @param context
     *            The application context.
     * @param displayService
     *            Service used to diplay data.
     * @param featTypes
     *            The feat types to display.
     */
    public FeatTypeArrayAdapter(final Context context, final DisplayService displayService, final List<FeatType> featTypes) {
        super(context, displayService, featTypes);
    }

    @Override
    protected String getText(final FeatType featType) {
        return displayService.getDisplayFeatType(featType);
    }

}
