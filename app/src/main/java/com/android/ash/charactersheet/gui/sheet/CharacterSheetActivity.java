package com.android.ash.charactersheet.gui.sheet;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.widget.ImageView;

import com.android.ash.charactersheet.CharacterHolder;
import com.android.ash.charactersheet.FBAnalytics;
import com.android.ash.charactersheet.GameSystemHolder;
import com.android.ash.charactersheet.PreferenceServiceHolder;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.billing.Billing;
import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.android.ash.charactersheet.boc.service.AndroidImageService;
import com.android.ash.charactersheet.boc.service.PreferenceService;
import com.android.ash.charactersheet.gui.main.characterlist.CharacterListActivity;
import com.android.ash.charactersheet.gui.util.AdViewConfiguration;
import com.android.ash.charactersheet.gui.util.LogAppCompatActivity;
import com.android.billingclient.api.BillingClient;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.ClassLevel;
import com.d20charactersheet.framework.boc.service.GameSystem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import kotlin.Lazy;

import static org.koin.java.KoinJavaComponent.inject;

/**
 * The character sheet of the character. Displays the stats, abilities and combat values of the character. The
 * background shows the image of the character.
 */
public class CharacterSheetActivity extends LogAppCompatActivity {

    private static final String INSTANCE_STATE_CHARACTER_ID = "characterId";
    private static final String INSTANCE_STATE_GAME_SYSTEM_ID = "gameSystemId";

    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);
    private final Lazy<PreferenceServiceHolder> preferencesServiceHolder = inject(PreferenceServiceHolder.class);
    private final Lazy<CharacterHolder> characterHolder = inject(CharacterHolder.class);
    private final Lazy<FirebaseAnalytics> firebaseAnalytics = inject(FirebaseAnalytics.class);
    private final Lazy<Billing> billing = inject(Billing.class);

    private GameSystem gameSystem;
    private PreferenceService preferenceService;
    private Character character;
    private boolean reloadedFromBackground;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        billing.getValue().startConnection(BillingClient.newBuilder(this));

        gameSystem = gameSystemHolder.getValue().getGameSystem();
        if (gameSystem == null) {
            reloadedFromBackground = true;
            reloadGameSystem(savedInstanceState);
            reloadCharacter(savedInstanceState);
        }

        preferenceService = preferencesServiceHolder.getValue().getPreferenceService();
        character = characterHolder.getValue().getCharacter();

        logEventCharacter(Objects.requireNonNull(character));

        setContentView(R.layout.activity_character_sheet);
        setToolbar();
        setBackground();
        new AdViewConfiguration().setAdView(this);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), getResources());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }

    private void reloadGameSystem(Bundle savedInstanceState) {
        GameSystemLoader gameSystemLoader = new GameSystemLoader();
        gameSystemLoader.connectDatabases(this);
        GameSystemType gameSystemType = getGameSystemType(savedInstanceState);
        gameSystem = new GameSystemLoader().load(this, gameSystemType);

    }

    private GameSystemType getGameSystemType(Bundle savedInstanceState) {
        int gameSystemId = savedInstanceState.getInt(INSTANCE_STATE_GAME_SYSTEM_ID);
        for (GameSystemType gameSystemType : GameSystemType.values()) {
            if (gameSystemType.ordinal() == gameSystemId) {
                return gameSystemType;
            }
        }
        throw new IllegalStateException("Can't determine game system with id: " + gameSystemId);
    }

    private void reloadCharacter(Bundle savedInstanceState) {
        int characterId = savedInstanceState.getInt(CharacterSheetActivity.INSTANCE_STATE_CHARACTER_ID);
        Character character = gameSystem.getCharacter(characterId);
        characterHolder.getValue().setCharacter(character);
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

    @Override
    protected void onResume() {
        super.onResume();
        billing.getValue().startConnection(BillingClient.newBuilder(this));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        savedInstanceState.putInt(INSTANCE_STATE_GAME_SYSTEM_ID, gameSystem.getId());
        savedInstanceState.putInt(INSTANCE_STATE_CHARACTER_ID, character.getId());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode && reloadedFromBackground) {
            startActivity(new Intent(this, CharacterListActivity.class));
        }
        return super.onKeyDown(keyCode, event);
    }


}
