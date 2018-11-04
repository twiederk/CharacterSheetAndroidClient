package com.android.ash.charactersheet.gui.sheet.skill;

import android.view.View;
import android.widget.TabHost.OnTabChangeListener;

/**
 * Hides view ich tab is changed.
 */
public class HideOnTabChangeListener implements OnTabChangeListener {

    private final View view;

    /**
     * Creates TabChangeListener hiding given view on change.
     * 
     * @param view
     *            The view to hide.
     */
    public HideOnTabChangeListener(final View view) {
        this.view = view;
    }

    /**
     * Hides view if tab is changed.
     */
    @Override
    public void onTabChanged(final String tabId) {
        view.setVisibility(View.INVISIBLE);
    }

}
