package com.android.ash.charactersheet.gui.widget;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * Adapter to display the name of an object in a listview. The name of the object it retrieved by the toString method.
 * 
 * @param <T>
 *            The class to display the name of.
 */
public class NameDisplayArrayAdapter<T> extends DisplayArrayAdapter<T> {

    /**
     * Creates adapter with the given array of items.
     * 
     * @param context
     *            The activity context.
     * @param displayService
     *            The service to display information.
     * @param items
     *            The items to display.
     */
    public NameDisplayArrayAdapter(final Context context, final DisplayService displayService, final T[] items) {
        super(context, displayService, R.layout.listitem_name, items);
    }

    /**
     * Creates adapter with the given list of items and standard layout (R.layout.listitem_name).
     * 
     * @param context
     *            The activity context.
     * @param displayService
     *            The service to display information.
     * @param items
     *            The items to display.
     */
    public NameDisplayArrayAdapter(final Context context, final DisplayService displayService, final List<T> items) {
        this(context, displayService, R.layout.listitem_name, items);
    }

    /**
     * Creates adapter with list of items and given layout.
     * 
     * @param context
     *            The context of the adapter.
     * @param displayService
     *            The service to display information.
     * @param listitemResourceId
     *            The XML layout to use.
     * @param items
     *            The items to display.
     */
    public NameDisplayArrayAdapter(final Context context, final DisplayService displayService,
            final int listitemResourceId, final List<T> items) {
        super(context, displayService, listitemResourceId, items);
    }

    @Override
    protected void fillView(final View view, final T item) {
        final TextView nameTextView = (TextView) view.findViewById(R.id.name);
        nameTextView.setText(item.toString());
    }

}
