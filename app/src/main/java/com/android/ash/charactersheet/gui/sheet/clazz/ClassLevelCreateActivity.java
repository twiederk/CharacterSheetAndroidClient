package com.android.ash.charactersheet.gui.sheet.clazz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;

import com.android.ash.charactersheet.CharacterHolder;
import com.android.ash.charactersheet.GameSystemHolder;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.LogAppCompatActivity;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.CharacterClass;
import com.d20charactersheet.framework.boc.model.ClassLevel;
import com.d20charactersheet.framework.boc.service.DisplayService;
import com.d20charactersheet.framework.boc.service.GameSystem;
import com.d20charactersheet.framework.boc.util.CharacterClassComparator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import kotlin.Lazy;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;
import static org.koin.java.KoinJavaComponent.inject;

/**
 * This activity creates a new class level. It displays the classes a spinner. Only classes the character has no levels
 * so far in are displayed. The level can be entered. The ok and cancel buttons are used to created or cancel the new
 * class level.
 */
public class ClassLevelCreateActivity extends LogAppCompatActivity implements AdapterView.OnItemClickListener {

    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);
    private final Lazy<CharacterHolder> characterHolder = inject(CharacterHolder.class);

    private GameSystem gameSystem;
    private DisplayService displayService;
    private Character character;

    private ArrayList<CharacterClass> availableCharacterClasses;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_level_create);

        gameSystem = gameSystemHolder.getValue().getGameSystem();
        displayService = Objects.requireNonNull(gameSystem).getDisplayService();
        character = characterHolder.getValue().getCharacter();

        setToolbar();
        setCharacterClass();

    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.class_level_create_title);
        Objects.requireNonNull(getSupportActionBar()).setIcon(R.drawable.icon);
    }

    private void setCharacterClass() {
        final List<CharacterClass> availableCharacterClasses = getAvailableCharacterClasses();
        final ArrayAdapter<CharacterClass> classArrayAdapter = new CharacterClassArrayAdapter(this, displayService,
                new ArrayList<>(availableCharacterClasses));
        ListView classListView = findViewById(R.id.class_level_create_class);
        classListView.setAdapter(classArrayAdapter);
        classListView.setOnItemClickListener(this);

    }

    private List<CharacterClass> getAvailableCharacterClasses() {
        availableCharacterClasses = new ArrayList<>(gameSystem.getAllCharacterClasses());
        final List<CharacterClass> classesOfCharacter = getClassesOfCharacter();
        availableCharacterClasses.removeAll(classesOfCharacter);
        availableCharacterClasses.sort(new CharacterClassComparator());
        return availableCharacterClasses;
    }

    private List<CharacterClass> getClassesOfCharacter() {
        final List<CharacterClass> classesOfCharacter = new LinkedList<>();
        for (final ClassLevel classLevel : character.getClassLevels()) {
            classesOfCharacter.add(classLevel.getCharacterClass());
        }
        return classesOfCharacter;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final CharacterClass characterClass = availableCharacterClasses.get(position);
        sendSelectedCharacterClass(characterClass);
        finish();

    }

    private void sendSelectedCharacterClass(CharacterClass characterClass) {
        final Intent resultIntent = new Intent();
        resultIntent.putExtra(INTENT_EXTRA_DATA_OBJECT, characterClass.getId());
        setResult(RESULT_OK, resultIntent);
    }
}
