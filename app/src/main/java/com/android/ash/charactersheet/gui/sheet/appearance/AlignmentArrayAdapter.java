package com.android.ash.charactersheet.gui.sheet.appearance;

import android.content.Context;

import com.android.ash.charactersheet.gui.widget.SpinnerArrayAdapter;
import com.d20charactersheet.framework.boc.model.Alignment;
import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * Adapter providing display text of alignments to display in a list view or spinner.
 */
public class AlignmentArrayAdapter extends SpinnerArrayAdapter<Alignment> {

    /**
     * Creates Adapter with alignments.
     * 
     * @param context
     *            The context the view is displayed in.
     * @param displayService
     *            The display service to display data.
     * @param alignments
     *            The alignment to display.
     */
    public AlignmentArrayAdapter(final Context context, final DisplayService displayService, final Alignment[] alignments) {
        super(context, displayService, alignments);
    }

    @Override
    protected String getText(final Alignment alignment) {
        return displayService.getDisplayAlignment(alignment);
    }
}
