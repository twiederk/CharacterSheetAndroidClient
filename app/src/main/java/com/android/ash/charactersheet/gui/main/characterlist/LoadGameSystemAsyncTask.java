package com.android.ash.charactersheet.gui.main.characterlist;

import android.app.Activity;

import com.android.ash.charactersheet.GameSystemHolder;
import com.android.ash.charactersheet.R;

import java.util.Objects;

import kotlin.Lazy;

import static org.koin.java.KoinJavaComponent.inject;

/**
 * Asynchronous task to load the game system while displaying a wait animation.
 */
public class LoadGameSystemAsyncTask extends AbstractAsyncTask {

    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);

    /**
     * Creates instance of task to execute later on.
     *
     * @param activity          The activity to display the wait animation on.
     * @param callbackInterface Called if the game system is loaded
     */
    public LoadGameSystemAsyncTask(final Activity activity, final GameSystemLoadable callbackInterface) {
        super(activity, callbackInterface);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        waitAnimation.setText(resources.getString(R.string.character_list_wait_load_game_system));
    }

    @Override
    protected TaskResult doInBackground(final Object... params) {
        Objects.requireNonNull(gameSystemHolder.getValue().getGameSystem()).load();
        return new TaskResult(true);
    }

}
