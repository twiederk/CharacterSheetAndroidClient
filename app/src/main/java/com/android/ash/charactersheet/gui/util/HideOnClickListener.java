package com.android.ash.charactersheet.gui.util;

import android.view.View;
import android.view.View.OnClickListener;

/**
 * Hides view if it is clicked.
 */
public class HideOnClickListener implements OnClickListener {

    /**
     * Hides view if clicked.
     */
    @Override
    public void onClick(final View view) {
        view.setVisibility(View.INVISIBLE);
    }

}
