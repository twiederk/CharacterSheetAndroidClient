package com.android.ash.charactersheet.gui.util;

import android.widget.Filter;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Observer;

/**
 * Filter of items. It filters items by name an magic. Stores the current filter settings and notifies all observers.
 */
public class ItemFilter extends Filter {

    private final List<Observer> observers;
    private String name;
    private boolean magic;

    /**
     * Creates EquipmentFilter with empty list of observers.
     */
    public ItemFilter() {
        super();
        observers = new LinkedList<>();
    }

    /**
     * Returns true, if only magic item should be shown.
     * 
     * @return True, if only magic item should be shown.
     */
    public boolean isMagic() {
        return magic;
    }

    /**
     * Sets if only magic items should be shown.
     * 
     * @param magic
     *            True, if only magic items should be shown.
     */
    public void setMagic(final boolean magic) {
        this.magic = magic;
        fireEvent();
    }

    /**
     * Returns the current name to filter.
     * 
     * @return The current name to filter.
     */
    public String getName() {
        return name;
    }

    /**
     * The name to filter
     * 
     * @param name
     *            The name to filter
     */
    private void setName(final String name) {
        this.name = name;
        fireEvent();
    }

    /**
     * Returns empty FilterResult.
     */
    @Override
    protected FilterResults performFiltering(final CharSequence filter) {
        Logger.debug("FeatFilter.performFiltering(" + filter + ")");
        return new FilterResults();

    }

    /**
     * Sets the filter to the feat model, which perfoms the filtering and notifies the views.
     */
    @Override
    protected void publishResults(final CharSequence filter, final FilterResults filterResults) {
        Logger.debug("EquipmentFilter.publishResults(" + filter + ")");
        setName(filter.toString().toLowerCase(Locale.getDefault()));
    }

    private void fireEvent() {
        for (final Observer observer : observers) {
            observer.update(null, null);
        }
    }

    /**
     * Adds observer to the EquipmentFilter.
     * 
     * @param observer
     *            Observes EquipmentFilter.
     */
    public void addObserver(final Observer observer) {
        observers.add(observer);
    }

}
