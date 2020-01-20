package com.android.ash.charactersheet.gui.sheet.feat;

import android.widget.ListView;
import android.widget.TabHost.OnTabChangeListener;

import java.util.LinkedList;
import java.util.List;

/**
 * Sets the current filter to the new list view which is displayed on tab change.
 */
public class FeatListOnTabChangeListener implements OnTabChangeListener {

    private final FeatModel featModel;
    private final List<ListView> listViews;

    /**
     * Creates FeatListOnTabChangeListener.
     * 
     * @param featModel
     *            Contains filter to set.
     */
    public FeatListOnTabChangeListener(final FeatModel featModel) {
        this.featModel = featModel;
        listViews = new LinkedList<>();
    }

    /**
     * Sets the filter to the new list view.
     * 
     * @see android.widget.TabHost.OnTabChangeListener#onTabChanged(java.lang.String)
     */
    @Override
    public void onTabChanged(final String tabId) {
        final String filter = featModel.getFilter();
        for (final ListView listView : listViews) {
            if ("".equals(filter)) {
                listView.clearTextFilter();
            } else {
                listView.setFilterText(filter);
            }
        }
    }

    /**
     * Add list view to update filter on tab change.
     * 
     * @param listView
     *            The list view to add.
     */
    public void addListView(final ListView listView) {
        listViews.add(listView);
    }

}
