package com.android.ash.charactersheet.gui.sheet;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

import com.android.ash.charactersheet.CharacterHolder;
import com.android.ash.charactersheet.FBAnalytics;
import com.android.ash.charactersheet.GameSystemHolder;
import com.android.ash.charactersheet.PreferenceServiceHolder;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.boc.service.AndroidImageService;
import com.android.ash.charactersheet.boc.service.PreferenceService;
import com.android.ash.charactersheet.gui.util.LogAppCompatActivity;
import com.android.ash.charactersheet.gui.util.Logger;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.ClassLevel;
import com.d20charactersheet.framework.boc.service.GameSystem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Objects;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import kotlin.Lazy;

import static org.koin.java.KoinJavaComponent.inject;

/**
 * The character sheet of the character. Displays the stats, abilities and combat values of the character. The
 * background shows the image of the character.
 */
public class CharacterSheetActivity extends LogAppCompatActivity {

    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);
    private final Lazy<PreferenceServiceHolder> preferencesServiceHolder = inject(PreferenceServiceHolder.class);
    private final Lazy<CharacterHolder> characterHolder = inject(CharacterHolder.class);
    private final Lazy<FirebaseAnalytics> firebaseAnalytics = inject(FirebaseAnalytics.class);

    private GameSystem gameSystem;
    private PreferenceService preferenceService;
    private Character character;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        Logger.info(getClass().getSimpleName() + ".onCreate()");
        super.onCreate(savedInstanceState);

        gameSystem = gameSystemHolder.getValue().getGameSystem();

        preferenceService = preferencesServiceHolder.getValue().getPreferenceService();
        character = characterHolder.getValue().getCharacter();

        logEventCharacter(Objects.requireNonNull(character));

        setContentView(R.layout.activity_character_sheet);
        setToolbar();
        setBackground();

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), getResources());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }

    void logEventCharacter(Character character) {
        Bundle bundle = new Bundle();
        bundle.putString(FBAnalytics.Param.RACE_NAME, character.getRace().getName());
        bundle.putString(FBAnalytics.Param.CLASS_LEVELS, buildClassLevelsParam(character));
        firebaseAnalytics.getValue().logEvent(FBAnalytics.Event.CHARACTER_OPEN, bundle);
    }

    private String buildClassLevelsParam(Character character) {
        StringBuilder classLevels = new StringBuilder();
        for (ClassLevel classLevel : character.getClassLevels()) {
            classLevels.append(classLevel.getCharacterClass().getName()).append(" (").append(classLevel.getLevel()).append(");");
        }
        return classLevels.toString();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(character.getName());
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

}
