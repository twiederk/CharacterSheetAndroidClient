package com.android.ash.charactersheet.gui.sheet.appearance;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.sheet.clazz.ClassLevelEditActivity;
import com.android.ash.charactersheet.gui.util.EditActivity;
import com.android.ash.charactersheet.gui.util.IntentOnClickListener;
import com.d20charactersheet.framework.boc.model.Alignment;
import com.d20charactersheet.framework.boc.model.Race;
import com.d20charactersheet.framework.boc.model.Sex;
import com.d20charactersheet.framework.boc.model.XpTable;
import com.d20charactersheet.framework.boc.util.RaceComparator;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Allow to edit the stats of the character.
 */
public class AppearanceEditActivity extends EditActivity {

    private List<Race> sortedRaces;

    private EditText nameEditText;
    private EditText playerEditText;
    private Spinner raceSpinner;
    private Spinner sexSpinner;
    private Spinner alignmentSpinner;
    private Spinner xpTableSpinner;
    private EditText experienceEditText;
    private Button classLevelButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        setContentView(R.layout.activity_appearance_edit);
        setTitle(R.string.appearance_title);
        super.onCreate(savedInstanceState);
        sortedRaces = getSortedRaces();
        getViews();
        createSpinnerAdapter();
    }

    private List<Race> getSortedRaces() {
        final List<Race> races = gameSystem.getAllRaces();
        Collections.sort(races, new RaceComparator());
        return races;
    }

    private void getViews() {
        nameEditText = findViewById(R.id.appearance_name);
        playerEditText = findViewById(R.id.appearance_player);
        raceSpinner = findViewById(R.id.appearance_race);
        sexSpinner = findViewById(R.id.appearance_sex);
        classLevelButton = findViewById(R.id.appearance_class_level);
        alignmentSpinner = findViewById(R.id.appearance_alignment);
        xpTableSpinner = findViewById(R.id.appearance_xptable);
        experienceEditText = findViewById(R.id.appearance_experience);
    }

    private void createSpinnerAdapter() {
        final ArrayAdapter<Race> raceArrayAdapter = new RaceArrayAdapter(this, displayService, sortedRaces);
        raceSpinner.setAdapter(raceArrayAdapter);
        final ArrayAdapter<Sex> sexArrayAdapter = new SexArrayAdapter(this, displayService, Sex.values());
        sexSpinner.setAdapter(sexArrayAdapter);
        final ArrayAdapter<Alignment> alignmentArrayAdapter = new AlignmentArrayAdapter(this, displayService,
                Alignment.values());
        alignmentSpinner.setAdapter(alignmentArrayAdapter);
        final ArrayAdapter<XpTable> xpTableArrayAdapter = new XpTableArrayAdapter(this, displayService,
                gameSystem.getAllXpTables());
        xpTableSpinner.setAdapter(xpTableArrayAdapter);
    }

    @Override
    protected void setData() {
        nameEditText.setText(character.getName());
        playerEditText.setText(character.getPlayer());
        raceSpinner.setSelection(getRaceIndex(character.getRace()));
        sexSpinner.setSelection(character.getSex().ordinal());
        classLevelButton.setOnClickListener(new IntentOnClickListener(new Intent(this, ClassLevelEditActivity.class)));
        alignmentSpinner.setSelection(character.getAlignment().ordinal());
        xpTableSpinner.setSelection(getXpTableIndex(character.getXpTable()));
        experienceEditText.setText(String.format(Locale.US, "%d", character.getExperiencePoints()));
    }

    private int getRaceIndex(final Race race) {
        for (int index = 0; index < sortedRaces.size(); index++) {
            if (race.equals(sortedRaces.get(index))) {
                return index;
            }
        }
        throw new IllegalArgumentException("Can't get sorted index of race: " + race);
    }

    private int getXpTableIndex(final XpTable xpTable) {
        final List<XpTable> xpTables = gameSystem.getAllXpTables();
        for (int index = 0; index < xpTables.size(); index++) {
            if (xpTable.getId() == xpTables.get(index).getId()) {
                return index;
            }
        }
        throw new IllegalArgumentException("Can't get index of xpTable: " + xpTable);
    }

    @Override
    protected void saveData() {
        character.setPlayer(playerEditText.getText().toString());
        character.setName(nameEditText.getText().toString());
        character.setRace((Race) raceSpinner.getSelectedItem());
        character.setSex((Sex) sexSpinner.getSelectedItem());
        character.setAlignment((Alignment) alignmentSpinner.getSelectedItem());
        character.setXpTable((XpTable) xpTableSpinner.getSelectedItem());
        character.setExperiencePoints(Integer.parseInt(experienceEditText.getText().toString()));
    }

}
