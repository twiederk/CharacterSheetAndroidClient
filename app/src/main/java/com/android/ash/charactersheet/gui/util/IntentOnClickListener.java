package com.android.ash.charactersheet.gui.util;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * OnClickListener to start an activity with the given Intent.
 */
public class IntentOnClickListener implements OnClickListener {

    private final Intent intent;

    /**
     * Creates OnClickListener to start activity with given intent.
     * 
     * @param intent
     *            The intent to start activity.
     */
    public IntentOnClickListener(final Intent intent) {
        this.intent = intent;
    }

    /**
     * Start activity of given intent.
     */
    @Override
    public void onClick(final View view) {
        view.getContext().startActivity(intent);
    }

}
