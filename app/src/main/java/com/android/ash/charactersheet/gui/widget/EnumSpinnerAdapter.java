package com.android.ash.charactersheet.gui.widget;

import android.content.Context;

import com.d20charactersheet.framework.boc.service.DisplayService;

import java.util.List;

/**
 * Adapter to display enums in a spinner.
 */
public abstract class EnumSpinnerAdapter extends SpinnerArrayAdapter<Enum<?>> {


    /**
     * Create adapter for enums in a spinner.
     *
     * @param context        The activity containing the spinner.
     * @param displayService The service to display the enum.
     * @param items          The items to display.
     */
    protected EnumSpinnerAdapter(final Context context, final DisplayService displayService,
                                 final List<Enum<?>> items) {
        super(context, displayService, items);
    }

    @Override
    protected abstract String getText(final Enum<?> enumeration);

}
