package com.android.ash.charactersheet.gui.widget;

import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * Abstract base class to expand by ArrayAdapters of ListView and Spinner to display data using the DisplayManager and a
 * custom layout.
 * 
 * @param <T>
 *            The item object to display the data of.
 */
public abstract class DisplayArrayAdapter<T> extends ArrayAdapter<T> {

    private int itemViewResourceId;
    private LayoutInflater mInflater;
    protected DisplayService displayService;

    /**
     * Instanciates the ArrayAdapter with an array of the given items
     * 
     * @param context
     *            The context the view is shown.
     * @param displayService
     *            The display service to display data in the GUI.
     * @param itemViewResourceId
     *            The resource id of the view.
     * @param items
     *            The items to display.
     */
    public DisplayArrayAdapter(final Context context, final DisplayService displayService, final int itemViewResourceId, final T[] items) {
        this(context, displayService, itemViewResourceId, Arrays.asList(items));
    }

    /**
     * Instanciates the ArrayAdapter with a list of the given items
     * 
     * @param context
     *            The context the view is shown.
     * @param displayService
     *            The display service to display data in the GUI.
     * @param itemViewResourceId
     *            The resource id of the view.
     * @param items
     *            The items to display.
     */
    public DisplayArrayAdapter(final Context context, final DisplayService displayService, final int itemViewResourceId, final List<T> items) {
        super(context, itemViewResourceId, items);
        this.displayService = displayService;
        this.itemViewResourceId = itemViewResourceId;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * Returns the filled view.
     * 
     * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
     */
    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        final View view = prepareView(convertView, parent);
        final T item = getItem(position);
        fillView(view, item);
        return view;
    }

    private View prepareView(final View convertView, final ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = mInflater.inflate(itemViewResourceId, parent, false);
        } else {
            view = convertView;
        }
        return view;
    }

    /**
     * Use the filled view also by drop down lists.
     * 
     * @see android.widget.ArrayAdapter#getDropDownView(int, android.view.View, android.view.ViewGroup)
     */
    @Override
    public View getDropDownView(final int position, final View convertView, final ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    /**
     * Override this message to fill the custom view with the data of the item.
     * 
     * @param view
     *            The custom view.
     * @param item
     *            The item with the data to display.
     */
    protected abstract void fillView(View view, T item);

}
