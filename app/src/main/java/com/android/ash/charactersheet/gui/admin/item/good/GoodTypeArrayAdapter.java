package com.android.ash.charactersheet.gui.admin.item.good;

import java.util.List;

import android.content.Context;

import com.android.ash.charactersheet.gui.widget.SpinnerArrayAdapter;
import com.d20charactersheet.framework.boc.model.GoodType;
import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * Lists all good types for the spinner in the GoodAdministrationActivity.
 */
public class GoodTypeArrayAdapter extends SpinnerArrayAdapter<GoodType> {

    /**
     * Creates GoodTypeArrayAdapter with the given values.
     * 
     * @param context
     *            The application context.
     * @param displayService
     *            Used to display data.
     * @param goodTypes
     *            The good types to display.
     */
    public GoodTypeArrayAdapter(final Context context, final DisplayService displayService, final List<GoodType> goodTypes) {
        super(context, displayService, goodTypes);
    }

    @Override
    protected String getText(final GoodType goodType) {
        return displayService.getDisplayGoodType(goodType);
    }

}
