package com.android.ash.charactersheet.gui.widget;

import android.widget.Filter;

import com.android.ash.charactersheet.gui.util.Logger;

import java.util.List;

/**
 * Filters a list of items.
 * 
 * @param <T>
 *            The class to filter instances of.
 */
public abstract class ListFilter<T> extends Filter {

    protected final ListModel<T> model;

    /**
     * Creates filter with given model.
     * 
     * @param model
     *            The model to filter.
     */
    ListFilter(final ListModel<T> model) {
        super();
        this.model = model;
    }

    /**
     * Sets new filter constraints, but doesn't filter model.
     */
    @Override
    protected FilterResults performFiltering(final CharSequence constraint) {
        Logger.debug("ListFilter.performFiltering(" + constraint + ")");
        return new FilterResults();

    }

    /**
     * Notifies model to filter data. The model notifies its observers after filtering.
     */
    @Override
    protected void publishResults(final CharSequence constraint, final FilterResults results) {
        Logger.debug("ListFilter.publishResults(" + constraint + ")");
        model.filter();
    }

    /**
     * Implement to filter model.
     * 
     * @return The filtered data.
     */
    public abstract List<T> performFiltering();

}
