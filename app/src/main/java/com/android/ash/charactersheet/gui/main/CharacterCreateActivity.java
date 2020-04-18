package com.android.ash.charactersheet.gui.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.ash.charactersheet.CharacterHolder;
import com.android.ash.charactersheet.FBAnalytics;
import com.android.ash.charactersheet.GameSystemHolder;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.sheet.CharacterSheetActivity;
import com.android.ash.charactersheet.gui.sheet.appearance.AlignmentArrayAdapter;
import com.android.ash.charactersheet.gui.sheet.appearance.RaceArrayAdapter;
import com.android.ash.charactersheet.gui.sheet.appearance.SexArrayAdapter;
import com.android.ash.charactersheet.gui.sheet.clazz.CharacterClassArrayAdapter;
import com.android.ash.charactersheet.gui.util.LogAppCompatActivity;
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
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import androidx.appcompat.widget.Toolbar;
import kotlin.Lazy;

import static org.koin.java.KoinJavaComponent.inject;

/**
 * Activity to create a new character.
 */
public class CharacterCreateActivity extends LogAppCompatActivity {

    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);
    private final Lazy<CharacterHolder> characterHolder = inject(CharacterHolder.class);
    private final Lazy<FirebaseAnalytics> firebaseAnalytics = inject(FirebaseAnalytics.class);

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
        setToolbar();

        gameSystem = gameSystemHolder.getValue().getGameSystem();

        messageManager = new MessageManager(this, Objects.requireNonNull(gameSystem).getDisplayService());

        getViews();
        setRace();
        setSex();
        setAlignment();
        setClazz();
        setOnClickListener();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.create_title);
        Objects.requireNonNull(getSupportActionBar()).setIcon(R.drawable.icon);
    }


    private void getViews() {
        raceSpinner = findViewById(R.id.create_race);
        sexSpinner = findViewById(R.id.create_sex);
        alignmentSpinner = findViewById(R.id.create_alignment);
        classSpinner = findViewById(R.id.create_class);
    }

    private void setOnClickListener() {
        setCreateButtonOnClickListener();
    }

    private void setCreateButtonOnClickListener() {
        final Button okButton = findViewById(R.id.create_button);
        okButton.setOnClickListener(view -> createCharacter());
    }

    private void createCharacter() {
        try {
            final Character character = newCharacter();
            characterHolder.getValue().setCharacter(character);
            logEventCharacterCreate(character);
            jumpToCharacterSheet();
        } catch (final Exception exception) {
            messageManager.showErrorMessage(exception);
        }
    }

    void logEventCharacterCreate(Character character) {
        Bundle bundle = new Bundle();
        bundle.putString(FBAnalytics.Param.RACE_NAME, character.getRace().getName());
        bundle.putString(FBAnalytics.Param.CLASS_NAME, character.getCharacterClasses().get(0).getName());
        firebaseAnalytics.getValue().logEvent(FBAnalytics.Event.CHARACTER_CREATE, bundle);
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
        final List<ClassLevel> classLevels = new ArrayList<>();
        final CharacterClass clazz = (CharacterClass) classSpinner.getSelectedItem();
        final ClassLevel classLevel = new ClassLevel(clazz, 1);
        classLevels.add(classLevel);
        return classLevels;
    }

    private String getEditText(final int resourceId) {
        final EditText editText = findViewById(resourceId);
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
        final ArrayAdapter<Alignment> alignmentArrayAdapter = new AlignmentArrayAdapter(this,
                gameSystem.getDisplayService(), Alignment.values());
        alignmentSpinner.setAdapter(alignmentArrayAdapter);
    }

    private void setClazz() {
        final List<CharacterClass> allCharacterClasses = gameSystem.getAllCharacterClasses();
        Collections.sort(allCharacterClasses, new CharacterClassComparator());
        final ArrayAdapter<CharacterClass> classArrayAdapter = new CharacterClassArrayAdapter(this,
                gameSystem.getDisplayService(), new ArrayList<>(allCharacterClasses));
        classSpinner.setAdapter(classArrayAdapter);
    }

}
