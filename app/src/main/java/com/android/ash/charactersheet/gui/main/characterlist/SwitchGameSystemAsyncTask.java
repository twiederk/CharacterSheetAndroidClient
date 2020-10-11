package com.android.ash.charactersheet.gui.main.characterlist;

import com.android.ash.charactersheet.GameSystemHolder;
import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.android.ash.charactersheet.gui.util.Logger;
import com.d20charactersheet.framework.boc.service.GameSystem;

import androidx.appcompat.app.AppCompatActivity;
import kotlin.Lazy;

import static org.koin.java.KoinJavaComponent.inject;

/**
 * Create/Upgrade database and load game system in an asynchronous task, while displaying a wait animation.
 */
public class SwitchGameSystemAsyncTask extends AbstractAsyncTask {

    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);


    /**
     * Creates an instance to execute the async task.
     *
     * @param activity          The activity to display the wait animation.
     * @param callbackInterface Called if the game system is loaded.
     * @param gameSystemType    The game system to load
     */
    SwitchGameSystemAsyncTask(final AppCompatActivity activity, final GameSystemLoadable callbackInterface, GameSystemType gameSystemType) {
        super(activity, callbackInterface, gameSystemType);
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

        final GameSystem gameSystem = createGameSystem();
        gameSystemHolder.getValue().setGameSystem(gameSystem);

        return new TaskResult(true);
    }

}
