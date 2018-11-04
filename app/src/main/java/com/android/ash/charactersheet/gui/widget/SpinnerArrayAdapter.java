package com.android.ash.charactersheet.gui.widget;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * Adapter to display a name of an object in a spinner. The name is retrieved from the toString method.
 * 
 * @param <T>
 *            The class to display the name of the object of.
 */
public class SpinnerArrayAdapter<T> extends DisplayArrayAdapter<T> {

    /**
     * Creates adapter with given list of items.
     * 
     * @param context
     *            The application context.
     * @param displayService
     *            Service used to display data.
     * @param items
     *            The abilities to display.
     */
    public SpinnerArrayAdapter(final Context context, final DisplayService displayService, final List<T> items) {
        this(context, displayService, R.layout.spinner_name, items);
    }

    /**
     * Creates adapter with given array of items.
     * 
     * @param context
     *            The application context.
     * @param displayService
     *            Service used to display data.
     * @param items
     *            The abilities to display.
     */
    public SpinnerArrayAdapter(final Context context, final DisplayService displayService, final T[] items) {
        this(context, displayService, R.layout.spinner_name, items);
    }

    /**
     * Creates adapater with given arry of items and layout.
     * 
     * @param context
     *            The application context.
     * @param displayService
     *            Service used to display data.
     * @param resourseId
     *            The layout to use.
     * @param items
     *            The items to display.
     */
    public SpinnerArrayAdapter(final Context context, final DisplayService displayService, final int resourseId,
            final List<T> items) {
        super(context, displayService, resourseId, items);
    }

    /**
     * Creates adapater with given arry of items and layout.
     * 
     * @param context
     *            The application context.
     * @param displayService
     *            Service used to display data.
     * @param resourseId
     *            The layout to use.
     * @param items
     *            The items to display.
     */
    public SpinnerArrayAdapter(final Context context, final DisplayService displayService, final int resourseId,
            final T[] items) {
        super(context, displayService, resourseId, items);
    }

    @Override
    protected void fillView(final View view, final T item) {
        final TextView textView = (TextView) view.findViewById(R.id.name);
        textView.setText(getText(item));
    }

    protected String getText(final T item) {
        return item.toString();
    }

}
