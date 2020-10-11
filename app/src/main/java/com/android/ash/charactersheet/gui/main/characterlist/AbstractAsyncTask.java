package com.android.ash.charactersheet.gui.main.characterlist;

import android.app.Activity;
import android.content.res.Resources;
import android.os.AsyncTask;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.android.ash.charactersheet.gui.sheet.GameSystemLoader;
import com.android.ash.charactersheet.gui.util.Logger;
import com.android.ash.charactersheet.gui.widget.waitanimation.WaitAnimation;
import com.d20charactersheet.framework.boc.service.GameSystem;

/**
 * Create/Upgrade database and load game system in an asynchronous task, while displaying a wait animation.
 */
abstract class AbstractAsyncTask extends AsyncTask<Object, String, TaskResult> {

    private final Activity activity;
    final Resources resources;
    final GameSystemType gameSystemType;
    private final GameSystemLoadable gameSystemLoadable;
    WaitAnimation waitAnimation;

    /**
     * Creates an instance to execute the async task.
     *
     * @param activity           The activity to display the wait animation.
     * @param gameSystemLoadable Class called if the game system is loaded.
     */
    AbstractAsyncTask(final Activity activity, final GameSystemLoadable gameSystemLoadable, GameSystemType gameSystemType) {
        super();
        this.activity = activity;
        this.gameSystemLoadable = gameSystemLoadable;
        this.gameSystemType = gameSystemType;
        resources = activity.getResources();
    }

    @Override
    protected void onPreExecute() {
        activity.setContentView(R.layout.waitanimation);
        waitAnimation = activity.findViewById(R.id.wait_animation);
    }

    @Override
    protected void onProgressUpdate(final String... values) {
        final String text = values[0];
        waitAnimation.setText(text);
    }

    @Override
    protected void onPostExecute(final TaskResult result) {
        gameSystemLoadable.onGameSystemLoaded();
    }

    String getLoadGameSystemText() {
        String text;
        if (GameSystemType.DNDV35.equals(gameSystemType)) {
            text = resources.getString(R.string.character_list_wait_load_dndv35);
        } else {
            text = resources.getString(R.string.character_list_wait_load_pathfinder);
        }
        return text;
    }

    GameSystem createGameSystem() {
        Logger.debug("createGameSystem");
        Logger.debug("gameSystemType: " + gameSystemType);

        return new GameSystemLoader().load(activity, gameSystemType);
    }

    /**
     * Listener interface for the event game system is loaded.
     */
    public interface GameSystemLoadable {

        /**
         * Called if the game system is loaded.
         */
        void onGameSystemLoaded();
    }
}
