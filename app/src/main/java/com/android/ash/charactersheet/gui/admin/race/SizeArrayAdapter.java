package com.android.ash.charactersheet.gui.admin.race;

import java.util.List;

import android.content.Context;

import com.android.ash.charactersheet.gui.widget.SpinnerArrayAdapter;
import com.d20charactersheet.framework.boc.model.Size;
import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * Adapter of a ListView to display sizes.
 */
public class SizeArrayAdapter extends SpinnerArrayAdapter<Size> {

    /**
     * Creates SizeArrayAdapter ready to use.
     * 
     * @param context
     *            The context to use the adapter with.
     * @param displayService
     *            DisplayService to display sizes language dependent.
     * @param sizes
     *            The list of sizes to display.
     */
    public SizeArrayAdapter(final Context context, final DisplayService displayService, final List<Size> sizes) {
        super(context, displayService, sizes);
    }

    @Override
    protected String getText(final Size size) {
        return displayService.getDisplaySize(size);
    }

}
