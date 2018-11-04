package com.android.ash.charactersheet.gui.util;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * OnClickListener starts a new activity and finishes exisiting activity if clicked.
 */
public class IntentAndFinishOnClickListener implements OnClickListener {

    private final Intent intent;
    private final Activity activity;

    /**
     * Creates OnClickListener to start activity with given intent.
     * 
     * @param intent
     *            The intent to start activity.
     * @param activity
     *            The activity to finish.
     */
    public IntentAndFinishOnClickListener(final Intent intent, final Activity activity) {
        this.intent = intent;
        this.activity = activity;
    }

    /**
     * Start activity of given intent.
     */
    @Override
    public void onClick(final View view) {
        view.getContext().startActivity(intent);
        activity.finish();
    }

}
