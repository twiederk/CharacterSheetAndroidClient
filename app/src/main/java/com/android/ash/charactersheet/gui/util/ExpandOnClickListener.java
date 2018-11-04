package com.android.ash.charactersheet.gui.util;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;


/**
 * OnClickListener of feat item list. Expands and collapses list item if clicked.
 */
public class ExpandOnClickListener implements OnItemClickListener {

    /**
     * Expands and collapses list item if clicked.
     * 
     * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View,
     *      int, long)
     */
    @Override
    public void onItemClick(final AdapterView<?> listView, final View view, final int position, final long id) {
        final BaseAdapter adapter = (BaseAdapter) listView.getAdapter();
        final ExpandableListItem expandableListItem = (ExpandableListItem) adapter.getItem(position);
        expandableListItem.setExpanded(!expandableListItem.isExpanded());
        adapter.notifyDataSetChanged();
    }

}
