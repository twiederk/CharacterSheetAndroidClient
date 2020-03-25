package com.android.ash.charactersheet.gui.util;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

import com.android.ash.charactersheet.CharacterHolder;
import com.android.ash.charactersheet.GameSystemHolder;
import com.android.ash.charactersheet.PreferenceServiceHolder;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.boc.service.AndroidImageService;
import com.android.ash.charactersheet.boc.service.PreferenceService;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.service.CharacterService;
import com.d20charactersheet.framework.boc.service.DisplayService;
import com.d20charactersheet.framework.boc.service.GameSystem;

import java.util.Objects;

import androidx.appcompat.widget.Toolbar;
import kotlin.Lazy;

import static org.koin.java.KoinJavaComponent.inject;

/**
 * Base class of CharacterSheetActivities. Derive activities in the character sheet part of the application from this
 * class. It supplies standard functionality for character sheet activities.
 */
public abstract class BaseCharacterSheetActivity extends LogAppCompatActivity {

    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);
    private final Lazy<PreferenceServiceHolder> preferenceServiceHolder = inject(PreferenceServiceHolder.class);
    private final Lazy<CharacterHolder> characterHolder = inject(CharacterHolder.class);

    protected GameSystem gameSystem;
    protected CharacterService characterService;
    private AndroidImageService imageService;
    protected DisplayService displayService;
    private PreferenceService preferenceService;
    protected Character character;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        gameSystem = gameSystemHolder.getValue().getGameSystem();
        preferenceService = preferenceServiceHolder.getValue().getPreferenceService();
        characterService = Objects.requireNonNull(gameSystem).getCharacterService();
        gameSystem.getRuleService();
        displayService = gameSystem.getDisplayService();
        imageService = (AndroidImageService) gameSystem.getImageService();
        gameSystem.getXpService();
        character = characterHolder.getValue().getCharacter();

        setToolbar();
        setBackground();

        doCreate();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(Objects.requireNonNull(character).getName());
        Objects.requireNonNull(getSupportActionBar()).setIcon(R.drawable.icon);
    }


    private void setBackground() {
        final boolean showImageAsBackground = preferenceService.getBoolean(PreferenceService.SHOW_IMAGE_AS_BACKGROUND);
        if (showImageAsBackground) {
            final Bitmap bitmap = imageService.getBitmap(character.getImageId());
            configImageView(Color.BLACK, bitmap, 0);
        } else {
            final int color = preferenceService.getInt(PreferenceService.BACKGROUND_COLOR);
            final int height = getHeight();
            configImageView(color, null, height);
        }
    }

    private int getHeight() {
        final Display display = getWindowManager().getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    private void configImageView(final int color, final Bitmap bitmap, final int minimumHeight) {
        final ImageView imageView = findViewById(R.id.background);
        imageView.setBackgroundColor(color);
        imageView.setMinimumHeight(minimumHeight);
        imageView.setImageBitmap(bitmap);
    }

    protected abstract int getLayoutId();

    protected abstract void doCreate();

}
