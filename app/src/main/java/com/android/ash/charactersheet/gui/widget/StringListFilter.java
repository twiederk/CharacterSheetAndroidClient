package com.android.ash.charactersheet.gui.widget;

import com.android.ash.charactersheet.gui.util.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Filters instances of T by their toString() value.
 * 
 * @param <T>
 *            The class of objects to filter.
 */
public class StringListFilter<T> extends ListFilter<T> {

    /**
     * Creates filter for toString() values.
     * 
     * @param model
     *            The instances to filter.
     */
    public StringListFilter(final ListModel<T> model) {
        super(model);
    }

    @Override
    protected void publishResults(final CharSequence constraint, final FilterResults results) {
        Logger.debug("StringListFilter.publishResults(" + constraint + ")");
        model.setStringConstraint(constraint.toString().toLowerCase(Locale.getDefault()));
        model.filter();
    }

    @Override
    public List<T> performFiltering() {
        final String constraint = model.getStringConstraint();
        if (constraint == null || constraint.length() == 0) {
            return new ArrayList<>(model.getItems());
        }
        return filterItems(model.getItems());
    }

    private List<T> filterItems(final List<T> items) {
        final List<T> filteredItems = new ArrayList<>();
        final String constraint = model.getStringConstraint();
        for (final T item : items) {
            final String name = item.toString().toLowerCase(Locale.getDefault());

            // First match against the whole, non-splitted value
            if (name.startsWith(constraint)) {
                filteredItems.add(item);
            } else {
                matchSplittedValue(item, name, constraint, filteredItems);
            }
        }
        return filteredItems;
    }

    private void matchSplittedValue(final T item, final String name, final String constraint,
            final List<T> filteredItems) {
        final String[] words = name.split(" ");

        for (String word : words) {
            if (word.startsWith(constraint)) {
                filteredItems.add(item);
                break;
            }
        }
    }

}
