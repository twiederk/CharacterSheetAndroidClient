package com.android.ash.charactersheet.gui.main;

import android.app.Activity;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.service.GameSystem;

/**
 * Asynchronous task to load the game system while displaying a waitanimation.
 */
public class LoadGameSystemAsyncTask extends AbstractAsyncTask {

    private final GameSystem gameSystem;

    /**
     * Creates instance of task to execute later on.
     * 
     * @param activity
     *            The activity to display the waitanimation on.
     * @param callbackInterface
     *            Called if the game system is loaded
     * @param gameSystem
     *            The game system to load.
     */
    public LoadGameSystemAsyncTask(final Activity activity,
            final AbstractAsyncTask.GameSystemLoadable callbackInterface, final GameSystem gameSystem) {
        super(activity, callbackInterface, null);
        this.gameSystem = gameSystem;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        waitAnimation.setText(resources.getString(R.string.character_list_wait_load_game_system));
    }

    @Override
    protected TaskResult doInBackground(final Object... params) {
        gameSystem.load();
        return new TaskResult(true);
    }

}
