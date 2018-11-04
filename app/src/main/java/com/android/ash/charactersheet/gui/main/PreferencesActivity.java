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

import com.android.ash.charactersheet.CharacterSheetApplication;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.boc.service.PreferenceService;
import com.android.ash.charactersheet.gui.util.LogActivity;

/**
 * Displays preferences. Preferences to choose background color or image. Preference to select game system to start up
 * with.
 */
public class PreferencesActivity extends LogActivity {

    private PreferenceService preferenceService;

    private static final int[] colors = { Color.BLACK, Color.BLUE, Color.CYAN, Color.DKGRAY, Color.GRAY, Color.GREEN,
            Color.LTGRAY, Color.MAGENTA, Color.RED, Color.WHITE, Color.YELLOW };

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences);

        final CharacterSheetApplication application = (CharacterSheetApplication) getApplication();
        preferenceService = application.getPreferenceService();

        setTitle(R.string.preferences_title);
        setRadioButtons();
        setColorSpinner();
        setGameSystemSpinner();
    }

    private void setRadioButtons() {
        final boolean showImageAsBackground = preferenceService.getBoolean(PreferenceService.SHOW_IMAGE_AS_BACKGROUND);
        final OnClickListener onClickListener = new OnClickListener() {

            @Override
            public void onClick(final View view) {
                save();
            }

        };
        setRadioButton(R.id.preferences_background_image, showImageAsBackground, onClickListener);
        setRadioButton(R.id.preferences_background_color, !showImageAsBackground, onClickListener);
    }

    private void setRadioButton(final int radioButtonId, final boolean showImageAsBackground,
            final OnClickListener onClickListener) {
        final RadioButton radioButton = (RadioButton) findViewById(radioButtonId);
        radioButton.setChecked(showImageAsBackground);
        radioButton.setOnClickListener(onClickListener);
    }

    private void setColorSpinner() {
        final Spinner colorSpinner = (Spinner) findViewById(R.id.preferences_background_colors);
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
        final Spinner gameSystemSpinner = (Spinner) findViewById(R.id.preferences_game_systems);
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
        final RadioButton imageRadiobutton = (RadioButton) findViewById(R.id.preferences_background_image);
        final boolean showImageAsBackground = imageRadiobutton.isChecked();
        preferenceService.setBoolean(PreferenceService.SHOW_IMAGE_AS_BACKGROUND, showImageAsBackground);

        final Spinner colorSpinner = (Spinner) findViewById(R.id.preferences_background_colors);
        int position = colorSpinner.getSelectedItemPosition();
        final int color = colors[position];
        preferenceService.setInt(PreferenceService.BACKGROUND_COLOR, color);

        final Spinner gameSystemSpinner = (Spinner) findViewById(R.id.preferences_game_systems);
        position = gameSystemSpinner.getSelectedItemPosition();
        preferenceService.setInt(PreferenceService.GAME_SYSTEM_TYPE, position);
    }

}
