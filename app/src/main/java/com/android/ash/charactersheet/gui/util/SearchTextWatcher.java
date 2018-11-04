package com.android.ash.charactersheet.gui.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ListView;


/**
 * Listens to text entries and filters assigned list view.
 */
public class SearchTextWatcher implements TextWatcher {

    private final ListView listView;

    /**
     * Instanciates SearchTextWatcher and assigns given list view.
     * 
     * @param listView
     *            The list view to filter by text entry.
     */
    public SearchTextWatcher(final ListView listView) {
        this.listView = listView;
    }

    @Override
    public void afterTextChanged(final Editable text) {
        // TODO Auto-generated method stub

    }

    @Override
    public void beforeTextChanged(final CharSequence text, final int start, final int count, final int after) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTextChanged(final CharSequence text, final int start, final int before, final int count) {
        Logger.debug("text: " + text);
        listView.setFilterText(text.toString());
    }

}
