package com.android.ash.charactersheet.gui.sheet.clazz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.ash.charactersheet.CharacterSheetApplication;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.LogActivity;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.CharacterClass;
import com.d20charactersheet.framework.boc.model.ClassLevel;
import com.d20charactersheet.framework.boc.service.CharacterService;
import com.d20charactersheet.framework.boc.service.GameSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

/**
 * The activity displays a list view of all class levels. The level of the class can be decrease and increased. The
 * class level can be deleted. A new class level buttons open the create class level dialog. The ok and cancel button
 * allows to save or cancel the changes.
 */
public class ClassLevelEditActivity extends LogActivity {

    private static final int RESULT_CODE_NEW_CLASS_LEVEL = 100;

    private GameSystem gameSystem;
    private CharacterService characterService;
    private Character character;
    private ArrayAdapter<ClassLevel> classLevelArrayAdapter;
    private List<ClassLevel> classLevels;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final CharacterSheetApplication application = (CharacterSheetApplication) getApplication();
        gameSystem = application.getGameSystem();
        characterService = gameSystem.getCharacterService();
        character = application.getCharacter();

        setContentView(R.layout.class_level_edit);
        setTitle(R.string.class_level_edit_title);
        setClassLevels();
        setNewClassLevelButton();

    }

    private void setClassLevels() {
        final ListView classLevelListView = findViewById(R.id.class_level_list_view);

        classLevels = getClassLevels();
        classLevelArrayAdapter = new ClassLevelArrayAdapter(this, gameSystem.getDisplayService(),
                R.layout.listitem_class_level, classLevels);
        classLevelListView.setAdapter(classLevelArrayAdapter);

    }

    private List<ClassLevel> getClassLevels() {
        final List<ClassLevel> characterClassLevels = character.getClassLevels();
        final List<ClassLevel> adapterClassLevels = new ArrayList<>();
        for (final ClassLevel classLevel : characterClassLevels) {
            final ClassLevel adapterClassLevel = new ClassLevel(classLevel.getCharacterClass(), classLevel.getLevel());
            adapterClassLevel.setId(classLevel.getId());
            adapterClassLevel.setCharacterAbilities(classLevel.getCharacterAbilities());
            adapterClassLevels.add(adapterClassLevel);

        }
        return adapterClassLevels;
    }

    private void setNewClassLevelButton() {
        final Context context = this;
        final Button newClassLevelButton = findViewById(R.id.class_level_new);
        newClassLevelButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View view) {
                final Intent intent = new Intent(context, ClassLevelCreateActivity.class);
                startActivityForResult(intent, RESULT_CODE_NEW_CLASS_LEVEL);
            }

        });
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
        deleteClassLevels(classLevels);
        updateClassLevels(classLevels);
        createClassLevels(classLevels);
        character.setClassLevels(classLevels);
    }

    private void deleteClassLevels(final List<ClassLevel> classLevels) {
        for (final ClassLevel classLevel : character.getClassLevels()) {
            if (!classLevels.contains(classLevel)) {
                characterService.deleteClassLevel(classLevel);
            }
        }
    }

    private void updateClassLevels(final List<ClassLevel> classLevels) {
        for (final ClassLevel classLevel : classLevels) {
            if (!isNew(classLevel)) {
                characterService.updateClassLevel(classLevel);
            }
        }
    }

    private void createClassLevels(final List<ClassLevel> classLevels) {
        for (final ClassLevel classLevel : classLevels) {
            if (isNew(classLevel)) {
                characterService.createClassLevel(character, classLevel);
            }
        }
    }

    private boolean isNew(final ClassLevel classLevel) {
        return classLevel.getId() == -1;
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent resultIntent) {

        // see which child activity is calling us back.
        if (requestCode == RESULT_CODE_NEW_CLASS_LEVEL) {
            if (resultCode != RESULT_CANCELED) {
                final Bundle extras = resultIntent.getExtras();
                final int classId = (Integer) Objects.requireNonNull(extras).getSerializable(INTENT_EXTRA_DATA_OBJECT);
                final CharacterClass characterClass = getClassById(classId);
                final ClassLevel classLevel = new ClassLevel(characterClass, 1);
                classLevelArrayAdapter.add(classLevel);
            }
        } else {
            throw new IllegalStateException("Result code (" + requestCode + ") is unknown");
        }
    }

    private CharacterClass getClassById(final int classId) {
        final List<CharacterClass> allClasses = gameSystem.getAllCharacterClasses();
        for (final CharacterClass characterClass : allClasses) {
            if (characterClass.getId() == classId) {
                return characterClass;
            }
        }
        throw new IllegalArgumentException("Can't determine class with id: " + classId);
    }
}
