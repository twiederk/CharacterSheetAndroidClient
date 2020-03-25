package com.android.ash.charactersheet.gui.util;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.android.ash.charactersheet.CharacterHolder;
import com.android.ash.charactersheet.GameSystemHolder;
import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.service.CharacterService;
import com.d20charactersheet.framework.boc.service.DisplayService;
import com.d20charactersheet.framework.boc.service.GameSystem;
import com.d20charactersheet.framework.boc.service.RuleService;

import java.util.Objects;

import androidx.appcompat.widget.Toolbar;
import kotlin.Lazy;

import static org.koin.java.KoinJavaComponent.inject;

/**
 * Activities to edit values of the character class expand from this class.
 */
public abstract class EditActivity extends LogAppCompatActivity {

    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);
    private final Lazy<CharacterHolder> characterHolder = inject(CharacterHolder.class);

    protected GameSystem gameSystem;
    private CharacterService characterService;
    protected RuleService ruleService;
    protected DisplayService displayService;
    protected Character character;

    private MessageManager messageManager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        setToolbar();

        character = characterHolder.getValue().getCharacter();
        gameSystem = gameSystemHolder.getValue().getGameSystem();

        characterService = Objects.requireNonNull(gameSystem).getCharacterService();
        ruleService = gameSystem.getRuleService();
        displayService = gameSystem.getDisplayService();

        messageManager = new MessageManager(this, displayService);
    }


    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getHeading());
        Objects.requireNonNull(getSupportActionBar()).setIcon(R.drawable.icon);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setData();
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        if (MotionEvent.ACTION_DOWN == event.getAction()) {
            handleInput();
        }
        return true;
    }

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            handleInput();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void handleInput() {
        try {
            saveData();
            characterService.updateCharacter(character);
            finish();
        } catch (final Exception exception) {
            Logger.error("Can't save combat data", exception);
            messageManager.showErrorMessage(exception);
            setData();
        }
    }

    protected abstract int getLayoutId();

    protected abstract int getHeading();

    protected abstract void setData();

    protected abstract void saveData();

}
