package com.android.ash.charactersheet.gui.admin.clazz;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

import java.util.List;

import android.content.Intent;
import android.widget.ArrayAdapter;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.AdministrationListActivity;
import com.android.ash.charactersheet.gui.widget.NameDisplayArrayAdapter;
import com.d20charactersheet.framework.boc.model.CharacterClass;
import com.d20charactersheet.framework.boc.util.CharacterClassComparator;

/**
 * Administration of character classes. Displays all character class. Single touch opens character class for edit. The
 * option menu offers to create a new character class.
 */
public class CharacterClassAdministrationListActivity extends AdministrationListActivity<CharacterClass> {

    @Override
    protected int getTitleResource() {
        return R.string.character_class_administration_list_title;
    }

    @Override
    protected ArrayAdapter<CharacterClass> getAdapter() {
        final List<CharacterClass> characterClasses = gameSystem.getAllCharacterClasses();
        final ArrayAdapter<CharacterClass> adapter = new NameDisplayArrayAdapter<CharacterClass>(this, displayService,
                characterClasses);
        adapter.sort(new CharacterClassComparator());
        return adapter;
    }

    @Override
    protected Intent getCreateIntent() {
        return new Intent(this, CharacterClassAdministrationCreateActivity.class);
    }

    @Override
    protected Intent getEditIntent(final CharacterClass clazz) {
        return new Intent(this, CharacterClassAdministrationEditActivity.class).putExtra(INTENT_EXTRA_DATA_OBJECT,
                clazz.getId());
    }
}
