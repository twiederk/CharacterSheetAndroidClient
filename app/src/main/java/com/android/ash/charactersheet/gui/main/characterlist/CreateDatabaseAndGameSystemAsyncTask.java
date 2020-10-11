package com.android.ash.charactersheet.gui.main.characterlist;

import android.app.Activity;

import com.android.ash.charactersheet.GameSystemHolder;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.android.ash.charactersheet.gui.util.Logger;
import com.d20charactersheet.framework.boc.service.GameSystem;

import kotlin.Lazy;

import static org.koin.java.KoinJavaComponent.inject;

/**
 * Create/Upgrade database and load game system in an asynchronous task, while displaying a wait animation.
 */
public class CreateDatabaseAndGameSystemAsyncTask extends AbstractAsyncTask {

    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);

    /**
     * Creates an instance to execute the async task. Opens the database and creates the game system.
     *
     * @param activity          The activity to display the wait animation.
     * @param callbackInterface Called if the game system is loaded.
     * @param gameSystemType    The game system to load
     */
    CreateDatabaseAndGameSystemAsyncTask(final Activity activity, final GameSystemLoadable callbackInterface, GameSystemType gameSystemType) {
        super(activity, callbackInterface, gameSystemType);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        waitAnimation.setText(resources.getString(R.string.character_list_wait_create_dndv35_database));
    }

    @Override
    protected TaskResult doInBackground(final Object... params) {
        Logger.debug("doInBackground");
        publishProgress(resources.getString(R.string.character_list_wait_create_pathfinder_database));
        publishProgress(getLoadGameSystemText());

        final GameSystem gameSystem = createGameSystem();
        gameSystemHolder.getValue().setGameSystem(gameSystem);
        return new TaskResult(true);
    }

}
