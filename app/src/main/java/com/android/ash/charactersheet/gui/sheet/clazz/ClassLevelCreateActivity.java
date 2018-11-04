package com.android.ash.charactersheet.gui.sheet.clazz;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.ash.charactersheet.CharacterSheetApplication;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.LogActivity;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.CharacterClass;
import com.d20charactersheet.framework.boc.model.ClassLevel;
import com.d20charactersheet.framework.boc.service.DisplayService;
import com.d20charactersheet.framework.boc.service.GameSystem;
import com.d20charactersheet.framework.boc.util.CharacterClassComparator;

/**
 * This activity creates a new class level. It displays the classes a spinner. Only classas the character has no levels
 * sofar in are displayed. The level can be entered. The ok and cancel buttons are used to created or cancel the new
 * class level.
 */
public class ClassLevelCreateActivity extends LogActivity {

    private GameSystem gameSystem;
    private DisplayService displayService;
    private Character character;

    private Spinner classSpinner;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_level_create);

        final CharacterSheetApplication application = (CharacterSheetApplication) getApplication();
        gameSystem = application.getGameSystem();
        displayService = gameSystem.getDisplayService();
        character = application.getCharacter();

        setTitle(R.string.class_level_create_title);
        setCharacterClass();

    }

    private void setCharacterClass() {
        final List<CharacterClass> availableCharacterClasses = getAvailableCharacterClasses();
        final ArrayAdapter<CharacterClass> classArrayAdapter = new CharacterClassArrayAdapter(this, displayService,
                new ArrayList<CharacterClass>(availableCharacterClasses));
        classSpinner = (Spinner) findViewById(R.id.class_level_create_class);
        classSpinner.setAdapter(classArrayAdapter);
    }

    private List<CharacterClass> getAvailableCharacterClasses() {
        final List<CharacterClass> availableCharacterClasses = new ArrayList<CharacterClass>(
                gameSystem.getAllCharacterClasses());
        final List<CharacterClass> classesOfCharacter = getClassesOfCharacter();
        availableCharacterClasses.removeAll(classesOfCharacter);
        Collections.sort(availableCharacterClasses, new CharacterClassComparator());
        return availableCharacterClasses;
    }

    private List<CharacterClass> getClassesOfCharacter() {
        final List<CharacterClass> classesOfCharacter = new LinkedList<CharacterClass>();
        for (final ClassLevel classLevel : character.getClassLevels()) {
            classesOfCharacter.add(classLevel.getCharacterClass());
        }
        return classesOfCharacter;
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        if (MotionEvent.ACTION_DOWN == event.getAction()) {
            save();
            finish();
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            save();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void save() {
        final CharacterClass characterClass = (CharacterClass) classSpinner.getSelectedItem();
        final Intent resultIntent = new Intent();
        resultIntent.putExtra(INTENT_EXTRA_DATA_OBJECT, characterClass.getId());
        setResult(RESULT_OK, resultIntent);
    }

}
