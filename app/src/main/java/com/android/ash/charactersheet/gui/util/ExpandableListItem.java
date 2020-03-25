package com.android.ash.charactersheet.gui.util;

import androidx.annotation.NonNull;

/**
 * Used by ListViews which allow to expand data of list items by touching them. Stores the object to display and the
 * info if list item is expanded or not.
 */
public class ExpandableListItem {

    private final Object object;
    private boolean expanded;

    /**
     * Creates ExpandableListItem containing the given object.
     * 
     * @param object
     *            The object to display as list item.
     */
    public ExpandableListItem(final Object object) {
        this.object = object;
    }


    /**
     * Returns the stored object.
     * 
     * @return The stored object.
     */
    public Object getObject() {
        return object;
    }

    /**
     * Returns true, if item view is expanded.
     * 
     * @return True, if item view is expanded.
     */
    public boolean isExpanded() {
        return expanded;
    }

    /**
     * Set true to expand item view.
     * 
     * @param expanded
     *            True to expand item view.
     */
    public void setExpanded(final boolean expanded) {
        this.expanded = expanded;
    }

    @NonNull
    @Override
    public String toString() {
        return object.toString();
    }

}
