package com.android.ash.charactersheet.gui.main;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;

import com.android.ash.charactersheet.GameSystemHolder;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.android.ash.charactersheet.dac.dao.sqlite.DBHelper;
import com.android.ash.charactersheet.gui.util.Logger;
import com.d20charactersheet.framework.boc.service.GameSystem;

import kotlin.Lazy;

import static org.koin.java.KoinJavaComponent.inject;

/**
 * Create/Upgrade database and load game system in an asynchronous task, while displaying a wait animation.
 */
public class CreateDatabaseAndGameSystemAsyncTask extends AbstractAsyncTask {

    private final DBHelper dndDBHelper;
    private final DBHelper pathfinderDBHelper;
    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);

    /**
     * Creates an instance to execute the async task. Opens the database and creates the game system.
     *
     * @param activity           The activity to display the wait animation.
     * @param callbackInterface  Called if the game system is loaded.
     * @param dndDBHelper        The helper to the DnDv35 database.
     * @param pathfinderDBHelper The helper to the Pathfinder database.
     * @param gameSystemType     The game system to create the game system for.
     */
    CreateDatabaseAndGameSystemAsyncTask(final Activity activity,
                                         final AbstractAsyncTask.GameSystemLoadable callbackInterface,
                                         final DBHelper dndDBHelper,
                                         final DBHelper pathfinderDBHelper,
                                         final GameSystemType gameSystemType) {
        super(activity, callbackInterface, gameSystemType);
        this.dndDBHelper = dndDBHelper;
        this.pathfinderDBHelper = pathfinderDBHelper;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        waitAnimation.setText(resources.getString(R.string.character_list_wait_create_dndv35_database));
    }

    @Override
    protected TaskResult doInBackground(final Object... params) {
        Logger.debug("doInBackground");
        final SQLiteDatabase dndDatabase = dndDBHelper.getWritableDatabase();
        publishProgress(resources.getString(R.string.character_list_wait_create_pathfinder_database));

        final SQLiteDatabase pathfinderDatabase = pathfinderDBHelper.getWritableDatabase();
        publishProgress(getLoadGameSystemText());

        final SQLiteDatabase currentDatabase = getDatabase(dndDatabase, pathfinderDatabase);
        final GameSystem gameSystem = createGameSystem(currentDatabase);
        gameSystemHolder.getValue().setGameSystem(gameSystem);
        return new TaskResult(true);
    }

    private SQLiteDatabase getDatabase(final SQLiteDatabase dndDatabase, final SQLiteDatabase pathfinderDatabase) {
        switch (gameSystemType) {
            case DNDV35:
                return dndDatabase;

            case PATHFINDER:
                return pathfinderDatabase;

            default:
                throw new IllegalStateException("Can't get database of game system type: " + gameSystemType);
        }
    }

}
