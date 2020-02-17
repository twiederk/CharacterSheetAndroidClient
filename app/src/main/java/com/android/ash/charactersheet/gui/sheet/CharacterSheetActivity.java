package com.android.ash.charactersheet.gui.sheet;

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
import com.android.ash.charactersheet.gui.util.LogAppCompatActivity;
import com.android.ash.charactersheet.gui.util.Logger;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.service.GameSystem;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

/**
 * The character sheet of the character. Displays the stats, abilities and combat values of the character. The
 * background shows the image of the character.
 */
public class CharacterSheetActivity extends LogAppCompatActivity {

    private GameSystem gameSystem;
    private PreferenceService preferenceService;
    private Character character;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        Logger.info(getClass().getSimpleName() + ".onCreate()");
        super.onCreate(savedInstanceState);

        final CharacterSheetApplication application = (CharacterSheetApplication) getApplication();
        gameSystem = application.getGameSystem();

        preferenceService = application.getPreferenceService();
        character = application.getCharacter();

        setContentView(R.layout.activity_character_sheet);
        setToolbar();
        setBackground();

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), getResources());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setIcon(R.drawable.icon);
    }

    void setBackground() {
        final boolean showImageAsBackground = preferenceService.getBoolean(PreferenceService.SHOW_IMAGE_AS_BACKGROUND);
        if (showImageAsBackground) {
            final AndroidImageService imageService = (AndroidImageService) gameSystem.getImageService();
            final Bitmap bitmap = imageService.getBitmap(character.getImageId());
            configImageView(Color.TRANSPARENT, bitmap, 0, 0);
        } else {
            final int color = preferenceService.getInt(PreferenceService.BACKGROUND_COLOR);
            final Display display = getWindowManager().getDefaultDisplay();
            final Point size = new Point();
            display.getSize(size);
            final int minWidth = size.x;
            final int minHeight = size.y;
            configImageView(color, null, minWidth, minHeight);
        }
    }

    private void configImageView(final int color, final Bitmap bitmap, final int minWidth, final int minHeight) {
        final ImageView imageView = findViewById(R.id.background);
        imageView.setBackgroundColor(color);
        imageView.setMinimumWidth(minWidth);
        imageView.setMinimumHeight(minHeight);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    protected void onResume() {
        Logger.info(getClass().getSimpleName() + ".onResume()");
        super.onResume();
        setTitle(character.getName());
    }

    @Override
    protected void onDestroy() {
        Logger.info(getClass().getSimpleName() + ".onDestroy()");
        super.onDestroy();
    }

}
