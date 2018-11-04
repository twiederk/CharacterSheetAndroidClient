package com.android.ash.charactersheet.gui.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.ash.charactersheet.CharacterSheetApplication;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.sheet.CharacterSheetActivity;
import com.android.ash.charactersheet.gui.sheet.appearance.AlignmentArrayAdapter;
import com.android.ash.charactersheet.gui.sheet.appearance.RaceArrayAdapter;
import com.android.ash.charactersheet.gui.sheet.appearance.SexArrayAdapter;
import com.android.ash.charactersheet.gui.sheet.clazz.CharacterClassArrayAdapter;
import com.android.ash.charactersheet.gui.util.LogActivity;
import com.android.ash.charactersheet.gui.util.Logger;
import com.android.ash.charactersheet.gui.util.MessageManager;
import com.d20charactersheet.framework.boc.model.Alignment;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.CharacterClass;
import com.d20charactersheet.framework.boc.model.ClassLevel;
import com.d20charactersheet.framework.boc.model.Race;
import com.d20charactersheet.framework.boc.model.Sex;
import com.d20charactersheet.framework.boc.service.CharacterService;
import com.d20charactersheet.framework.boc.service.GameSystem;
import com.d20charactersheet.framework.boc.service.ImageService;
import com.d20charactersheet.framework.boc.util.CharacterClassComparator;

/**
 * Activity to create a new character.
 */
public class CharacterCreateActivity extends LogActivity {

    private CharacterSheetApplication application;
    private GameSystem gameSystem;

    private MessageManager messageManager;

    private Spinner raceSpinner;
    private Spinner sexSpinner;
    private Spinner alignmentSpinner;
    private Spinner classSpinner;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_create);
        setTitle(R.string.create_title);

        application = (CharacterSheetApplication) getApplication();
        gameSystem = application.getGameSystem();

        messageManager = new MessageManager(this, gameSystem.getDisplayService());

        getViews();
        setRace();
        setSex();
        setAlignment();
        setClazz();
        setOnClickListener();
    }

    private void getViews() {
        raceSpinner = (Spinner) findViewById(R.id.create_race);
        sexSpinner = (Spinner) findViewById(R.id.create_sex);
        alignmentSpinner = (Spinner) findViewById(R.id.create_alignment);
        classSpinner = (Spinner) findViewById(R.id.create_class);
    }

    private void setOnClickListener() {
        setCreateButtonOnClickListener();
    }

    private void setCreateButtonOnClickListener() {
        final Button okButton = (Button) findViewById(R.id.create_button);
        okButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View view) {
                createCharacter();
            }
        });
    }

    private void createCharacter() {
        try {
            final Character character = newCharacter();
            application.setCharacter(character);
            jumpToCharacterSheet();
        } catch (final Exception exception) {
            messageManager.showErrorMessage(exception);
        }
    }

    private Character newCharacter() {
        Character character = new Character();
        character.setName(getEditText(R.id.create_name));
        character.setPlayer(getEditText(R.id.create_player));
        character.setRace((Race) raceSpinner.getSelectedItem());
        character.setSex((Sex) sexSpinner.getSelectedItem());
        character.setClassLevels(getClassLevels());
        character.setAlignment((Alignment) alignmentSpinner.getSelectedItem());
        character.setXpTable(gameSystem.getAllXpTables().get(0));
        character.setStrength(10);
        character.setDexterity(10);
        character.setConstitution(10);
        character.setIntelligence(10);
        character.setWisdom(10);
        character.setCharisma(10);
        character.setImageId(ImageService.DEFAULT_CHARACTER_IMAGE_ID);
        character.setThumbImageId(ImageService.DEFAULT_THUMB_IMAGE_ID);

        final CharacterService characterService = gameSystem.getCharacterService();
        character = characterService.createCharacter(character, gameSystem.getAllSkills());

        Logger.debug("character: " + character);
        return character;
    }

    private List<ClassLevel> getClassLevels() {
        final List<ClassLevel> classLevels = new ArrayList<ClassLevel>();
        final CharacterClass clazz = (CharacterClass) classSpinner.getSelectedItem();
        final ClassLevel classLevel = new ClassLevel(clazz, 1);
        classLevels.add(classLevel);
        return classLevels;
    }

    private String getEditText(final int resourceId) {
        final EditText editText = (EditText) findViewById(resourceId);
        return editText.getText().toString();
    }

    private void jumpToCharacterSheet() {
        final Intent intent = new Intent(this, CharacterSheetActivity.class);
        startActivity(intent);
        finish();
    }

    private void setRace() {
        final List<Race> allRaces = gameSystem.getAllRaces();
        final ArrayAdapter<Race> raceArrayAdapter = new RaceArrayAdapter(this, gameSystem.getDisplayService(), allRaces);
        raceSpinner.setAdapter(raceArrayAdapter);
    }

    private void setSex() {
        final ArrayAdapter<Sex> sexArrayAdapter = new SexArrayAdapter(this, gameSystem.getDisplayService(),
                Sex.values());
        sexSpinner.setAdapter(sexArrayAdapter);
    }

    private void setAlignment() {
        final ArrayAdapter<Alignment> alginmentArrayAdapter = new AlignmentArrayAdapter(this,
                gameSystem.getDisplayService(), Alignment.values());
        alignmentSpinner.setAdapter(alginmentArrayAdapter);
    }

    private void setClazz() {
        final List<CharacterClass> allCharacterClasses = gameSystem.getAllCharacterClasses();
        Collections.sort(allCharacterClasses, new CharacterClassComparator());
        final ArrayAdapter<CharacterClass> classArrayAdapter = new CharacterClassArrayAdapter(this,
                gameSystem.getDisplayService(), new ArrayList<CharacterClass>(allCharacterClasses));
        classSpinner.setAdapter(classArrayAdapter);
    }

}
