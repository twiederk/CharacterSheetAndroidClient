package com.android.ash.charactersheet.gui.widget;

import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Filterable model of a list view.
 * 
 * @param <T>
 *            The class of objects to be displayed.
 */
public class ListModel<T> extends Observable {

    private List<T> items;
    private List<T> filteredItems;
    private ListFilter<T> listFilter;

    private String stringConstraint;

    /**
     * Create ListModel instance with empty list and no filter.
     */
    protected ListModel() {
        this(new ArrayList<>(), null);
    }

    /**
     * Creates model of given items. Items are filterable by its toString() value.
     * 
     * @param items
     *            The items of the model.
     */
    public ListModel(final List<T> items) {
        this(items, null);
    }

    /**
     * Create model with given items. Filterable by the given filter.
     * 
     * @param items
     *            The items of the model.
     * @param listFilter
     *            The filter of the items.
     */
    private ListModel(final List<T> items, final ListFilter<T> listFilter) {
        super();
        this.items = items;
        this.filteredItems = new ArrayList<>(items);
        if (listFilter == null) {
            this.listFilter = new StringListFilter<>(this);
        } else {
            this.listFilter = listFilter;
        }
    }

    protected void fireEvent() {
        setChanged();
        notifyObservers();
    }

    /**
     * Returns all items.
     * 
     * @return All items.
     */
    public List<T> getItems() {
        return items;
    }

    /**
     * Sets the items of the model.
     * 
     * @param items
     *            The items of the model.
     */
    protected void setItems(final List<T> items) {
        this.items = items;
        this.filteredItems = new ArrayList<>(items);
        fireEvent();
    }

    /**
     * Filters the model with the given filter.
     */
    public void filter() {
        filteredItems = listFilter.performFiltering();
        fireEvent();
    }

    /**
     * Returns the number of items.
     * 
     * @return The number of items.
     */
    public int getCount() {
        return filteredItems.size();
    }

    /**
     * Returns the item on the given position.
     * 
     * @param position
     *            The position of the item.
     * @return The item on the given position.
     */
    public Object getItem(final int position) {
        return filteredItems.get(position);
    }

    /**
     * Returns the position of the item.
     * 
     * @param position
     *            The position of the item.
     * @return The position of the item.
     */
    public long getItemId(final int position) {
        if (position >= filteredItems.size()) {
            return -1;
        }
        return position;
    }

    /**
     * Returns the filter.
     * 
     * @return The filter.
     */
    public Filter getFilter() {
        return listFilter;
    }

    /**
     * Sets the filter.
     * 
     * @param listFilter
     *            The filter to set.
     */
    protected void setFilter(final ListFilter<T> listFilter) {
        this.listFilter = listFilter;
    }

    /**
     * Returns the string constraint to filter with.
     * 
     * @return The string constraint.
     */
    public String getStringConstraint() {
        return stringConstraint;
    }

    /**
     * Sets the sgring contraint to filter with.
     * 
     * @param stringConstraint
     *            The string contraint.
     */
    public void setStringConstraint(final String stringConstraint) {
        this.stringConstraint = stringConstraint;
    }

    /**
     * Remove item from model.
     * 
     * @param item
     *            The item to delete.
     */
    public void remove(final T item) {
        items.remove(item);
        filter();
        fireEvent();
    }

}
