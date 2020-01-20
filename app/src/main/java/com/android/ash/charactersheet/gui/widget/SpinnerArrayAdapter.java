package com.android.ash.charactersheet.gui.widget;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.service.DisplayService;

import java.util.List;

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
        super(context, displayService, R.layout.spinner_name, items);
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
    protected SpinnerArrayAdapter(final Context context, final DisplayService displayService, final T[] items) {
        super(context, displayService, items);
    }


    @Override
    protected void fillView(final View view, final T item) {
        final TextView textView = view.findViewById(R.id.name);
        textView.setText(getText(item));
    }

    protected String getText(final T item) {
        return item.toString();
    }

}
