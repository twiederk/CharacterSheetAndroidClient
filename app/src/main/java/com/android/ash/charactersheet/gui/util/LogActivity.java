package com.android.ash.charactersheet.gui.util;

import android.app.Activity;
import android.os.Bundle;

/**
 * Logs life cycle events of activity.
 */
public abstract class LogActivity extends Activity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        Logger.info(getClass().getSimpleName() + ".onCreate()");
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        Logger.info(getClass().getSimpleName() + ".onResume()");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Logger.info(getClass().getSimpleName() + ".onDestroy()");
        super.onDestroy();
    }

}
