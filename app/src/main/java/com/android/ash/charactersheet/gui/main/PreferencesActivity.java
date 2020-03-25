package com.android.ash.charactersheet.gui.main;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.android.ash.charactersheet.PreferenceServiceHolder;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.boc.service.PreferenceService;
import com.android.ash.charactersheet.gui.util.LogAppCompatActivity;

import java.util.Objects;

import androidx.appcompat.widget.Toolbar;
import kotlin.Lazy;

import static org.koin.java.KoinJavaComponent.inject;

/**
 * Displays preferences. Preferences to choose background color or image. Preference to select game system to start up
 * with.
 */
public class PreferencesActivity extends LogAppCompatActivity {

    private final Lazy<PreferenceServiceHolder> preferenceServiceHolder = inject(PreferenceServiceHolder.class);

    private PreferenceService preferenceService;

    private static final int[] colors = {Color.BLACK, Color.BLUE, Color.CYAN, Color.DKGRAY, Color.GRAY, Color.GREEN,
            Color.LTGRAY, Color.MAGENTA, Color.RED, Color.WHITE, Color.YELLOW};

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        preferenceService = preferenceServiceHolder.getValue().getPreferenceService();

        setToolbar();
        setRadioButtons();
        setColorSpinner();
        setGameSystemSpinner();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.preferences_title);
        Objects.requireNonNull(getSupportActionBar()).setIcon(R.drawable.icon);
    }

    private void setRadioButtons() {
        final boolean showImageAsBackground = preferenceService.getBoolean(PreferenceService.SHOW_IMAGE_AS_BACKGROUND);
        final OnClickListener onClickListener = view -> save();
        setRadioButton(R.id.preferences_background_image, showImageAsBackground, onClickListener);
        setRadioButton(R.id.preferences_background_color, !showImageAsBackground, onClickListener);
    }

    private void setRadioButton(final int radioButtonId, final boolean showImageAsBackground,
                                final OnClickListener onClickListener) {
        final RadioButton radioButton = findViewById(radioButtonId);
        radioButton.setChecked(showImageAsBackground);
        radioButton.setOnClickListener(onClickListener);
    }

    private void setColorSpinner() {
        final Spinner colorSpinner = findViewById(R.id.preferences_background_colors);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.background_color_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(adapter);
        final int position = getColorPosition();
        colorSpinner.setSelection(position);
        colorSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(final AdapterView<?> parent, final View view, final int pos, final long id) {
                save();
            }

            @Override
            public void onNothingSelected(final AdapterView<?> arg0) {
                // do nothing
            }
        });
    }

    private int getColorPosition() {
        final int color = preferenceService.getInt(PreferenceService.BACKGROUND_COLOR);
        for (int i = 0; i < colors.length; i++) {
            if (color == colors[i]) {
                return i;
            }
        }
        throw new IllegalStateException("Can't determine background color");
    }

    private void setGameSystemSpinner() {
        final Spinner gameSystemSpinner = findViewById(R.id.preferences_game_systems);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.game_system_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gameSystemSpinner.setAdapter(adapter);
        final int position = preferenceService.getInt(PreferenceService.GAME_SYSTEM_TYPE);
        gameSystemSpinner.setSelection(position);
        gameSystemSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(final AdapterView<?> parent, final View view, final int pos, final long id) {
                save();
            }

            @Override
            public void onNothingSelected(final AdapterView<?> arg0) {
                // do nothing
            }
        });

    }

    private void save() {
        final RadioButton imageRadiobutton = findViewById(R.id.preferences_background_image);
        final boolean showImageAsBackground = imageRadiobutton.isChecked();
        preferenceService.setBoolean(PreferenceService.SHOW_IMAGE_AS_BACKGROUND, showImageAsBackground);

        final Spinner colorSpinner = findViewById(R.id.preferences_background_colors);
        int position = colorSpinner.getSelectedItemPosition();
        final int color = colors[position];
        preferenceService.setInt(PreferenceService.BACKGROUND_COLOR, color);

        final Spinner gameSystemSpinner = findViewById(R.id.preferences_game_systems);
        position = gameSystemSpinner.getSelectedItemPosition();
        preferenceService.setInt(PreferenceService.GAME_SYSTEM_TYPE, position);
    }

}
