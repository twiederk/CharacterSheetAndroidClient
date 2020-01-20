package com.android.ash.charactersheet.gui.admin.clazz;

import android.content.Intent;
import android.os.Bundle;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.CharacterClass;

import java.util.Objects;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

/**
 * Activity to edit an character class using the CharacterClassAdministrationActivity as base.
 */
public class CharacterClassAdministrationEditActivity extends CharacterClassAdministrationActivity {

    @Override
    protected CharacterClass createForm() {
        final Intent intent = getIntent();
        final Bundle extras = intent.getExtras();
        final int classId = Objects.requireNonNull(extras).getInt(INTENT_EXTRA_DATA_OBJECT);
        return getClassById(classId);
    }

    private CharacterClass getClassById(final int classId) {
        for (final CharacterClass clazz : gameSystem.getAllCharacterClasses()) {
            if (clazz.getId() == classId) {
                return clazz;
            }
        }
        throw new IllegalArgumentException("Can't find class with id: " + classId);
    }

    @Override
    protected String getHeading() {
        return getResources().getString(R.string.character_class_administration_edit_title);
    }

    @Override
    protected void saveForm() {
        characterService.deleteCharacterAbilities(form, helper.getDeletedClassAbilities());
        characterClassService.updateCharacterClass(form);
        gameSystem.clear();
    }

}
