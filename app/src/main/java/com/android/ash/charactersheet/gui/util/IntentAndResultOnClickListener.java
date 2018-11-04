package com.android.ash.charactersheet.gui.util;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * OnClickListener to start an activity with the given Intent.
 */
public class IntentAndResultOnClickListener implements OnClickListener {

    private final Activity activity;
    private final Intent intent;
    private final int requestCode;

    /**
     * Creates OnClickListener to start activity with given intent.
     * 
     * @param activity
     *            The activity to start from for result.
     * @param intent
     *            The intent to start activity.
     * @param requestCode
     *            The request code to get with the result.
     */
    public IntentAndResultOnClickListener(final Activity activity, final Intent intent, final int requestCode) {
        this.activity = activity;
        this.intent = intent;
        this.requestCode = requestCode;
    }

    /**
     * Start activity of given intent.
     */
    @Override
    public void onClick(final View view) {
        activity.startActivityForResult(intent, requestCode);

    }

}
