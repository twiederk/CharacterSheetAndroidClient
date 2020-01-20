package com.android.ash.charactersheet.gui.util;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

import com.android.ash.charactersheet.CharacterSheetApplication;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.boc.service.AndroidImageService;
import com.android.ash.charactersheet.boc.service.PreferenceService;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.service.CharacterService;
import com.d20charactersheet.framework.boc.service.DisplayService;
import com.d20charactersheet.framework.boc.service.GameSystem;

/**
 * Base class of CharacterSheetActivities. Derive activities in the character sheet part of the application from this
 * class. It supplies standard functionallity for character sheet activities.
 */
public abstract class BaseCharacterSheetActivity extends LogActivity {

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

        final CharacterSheetApplication application = (CharacterSheetApplication) getApplication();
        gameSystem = application.getGameSystem();
        characterService = gameSystem.getCharacterService();
        gameSystem.getRuleService();
        displayService = gameSystem.getDisplayService();
        imageService = (AndroidImageService) gameSystem.getImageService();
        preferenceService = application.getPreferenceService();
        gameSystem.getXpService();
        character = application.getCharacter();

        setTitle(character.getName());
        setBackground();

        doCreate();
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

    @Override
    protected void onResume() {
        super.onResume();
        doResume();
    }

    protected abstract int getLayoutId();

    protected abstract void doCreate();

    protected abstract void doResume();

}
