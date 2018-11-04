package com.android.ash.charactersheet.gui.admin.item;

import java.util.List;

import android.content.Context;

import com.android.ash.charactersheet.gui.widget.SpinnerArrayAdapter;
import com.d20charactersheet.framework.boc.model.QualityType;
import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * Lists all quality types for the spinner in the ItemAdministrationActivity.
 */
public class QualityTypeArrayAdapter extends SpinnerArrayAdapter<QualityType> {

    /**
     * Creates QualityTypeArrayAdapter with the given values.
     * 
     * @param context
     *            The application context.
     * @param displayService
     *            Used to display data.
     * @param qualityTypes
     *            The quality types to display.
     */
    public QualityTypeArrayAdapter(final Context context, final DisplayService displayService, final List<QualityType> qualityTypes) {
        super(context, displayService, qualityTypes);
    }

    @Override
    protected String getText(final QualityType qualityType) {
        return displayService.getDisplayQualityType(qualityType);
    }

}
