package com.android.ash.charactersheet.gui.widget;

import java.util.List;

import android.content.Context;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * Adapter to display enums in a spinner.
 */
public abstract class EnumSpinnerAdapter extends SpinnerArrayAdapter<Enum<?>> {

    /**
     * Create adapter for enums in a spinner.
     * 
     * @param context
     *            The activity containing the spinner.
     * @param displayService
     *            The service to display the enum.
     * @param items
     *            The items to display.
     */
    public EnumSpinnerAdapter(final Context context, final DisplayService displayService, final List<Enum<?>> items) {
        this(context, displayService, R.layout.spinner_name, items);
    }

    /**
     * Create adapter for enums in a spinner.
     * 
     * @param context
     *            The activity containing the spinner.
     * @param displayService
     *            The service to display the enum.
     * @param items
     *            The items to display.
     */
    public EnumSpinnerAdapter(final Context context, final DisplayService displayService, final Enum<?>[] items) {
        this(context, displayService, R.layout.spinner_name, items);
    }

    /**
     * Create adapter for enums in a spinner.
     * 
     * @param context
     *            The activity containing the spinner.
     * @param displayService
     *            The service to display the enum.
     * @param resourseId
     *            The resource id of the layout.
     * @param items
     *            The items to display.
     */
    public EnumSpinnerAdapter(final Context context, final DisplayService displayService, final int resourseId,
            final List<Enum<?>> items) {
        super(context, displayService, resourseId, items);
    }

    /**
     * Create adapter for enums in a spinner.
     * 
     * @param context
     *            The activity containing the spinner.
     * @param displayService
     *            The service to display the enum.
     * @param resourseId
     *            The resource id fo the layout.
     * @param items
     *            The items to display.
     */
    public EnumSpinnerAdapter(final Context context, final DisplayService displayService, final int resourseId,
            final Enum<?>[] items) {
        super(context, displayService, resourseId, items);
    }

    @Override
    protected abstract String getText(final Enum<?> enumeration);

}
