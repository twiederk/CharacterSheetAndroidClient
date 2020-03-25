package com.android.ash.charactersheet.gui.main;

import android.database.sqlite.SQLiteDatabase;

import com.android.ash.charactersheet.GameSystemHolder;
import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.android.ash.charactersheet.dac.dao.sqlite.DBHelper;
import com.android.ash.charactersheet.gui.util.Logger;
import com.d20charactersheet.framework.boc.service.GameSystem;

import kotlin.Lazy;

import static org.koin.java.KoinJavaComponent.inject;

/**
 * Create/Upgrade database and load game system in an asynchronous task, while displaying a wait animation.
 */
public class SwitchGameSystemAsyncTask extends AbstractAsyncTask {

    private final DBHelper dbHelper;
    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);


    /**
     * Creates an instance to execute the async task.
     *
     * @param activity          The activity to display the wait animation.
     * @param callbackInterface Called if the game system is loaded.
     * @param dbHelper          The helper to open the database.
     * @param gameSystemType    The game system to create.
     */
    SwitchGameSystemAsyncTask(final CharacterListActivity activity,
                              final AbstractAsyncTask.GameSystemLoadable callbackInterface,
                              final DBHelper dbHelper,
                              final GameSystemType gameSystemType) {
        super(activity, callbackInterface, gameSystemType);
        this.dbHelper = dbHelper;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        final String loadGameSystemText = getLoadGameSystemText();
        waitAnimation.setText(loadGameSystemText);
    }

    @Override
    protected TaskResult doInBackground(final Object... params) {
        Logger.debug("doInBackground");
        final SQLiteDatabase database = dbHelper.getWritableDatabase();

        final GameSystem gameSystem = createGameSystem(database);
        gameSystemHolder.getValue().setGameSystem(gameSystem);

        return new TaskResult(true);
    }

}
