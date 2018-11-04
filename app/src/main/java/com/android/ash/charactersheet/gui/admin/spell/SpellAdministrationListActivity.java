package com.android.ash.charactersheet.gui.admin.spell;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

import java.util.List;

import android.content.Intent;
import android.widget.ArrayAdapter;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.AdministrationListActivity;
import com.android.ash.charactersheet.gui.widget.NameDisplayArrayAdapter;
import com.d20charactersheet.framework.boc.model.Spell;
import com.d20charactersheet.framework.boc.util.SpellComparator;

/**
 * Displays all spells and allows to create a new one or edit an existing one.
 */
public class SpellAdministrationListActivity extends AdministrationListActivity<Spell> {

    @Override
    protected int getTitleResource() {
        return R.string.spell_administration_list_title;
    }

    @Override
    protected ArrayAdapter<Spell> getAdapter() {
        final List<Spell> allSpells = gameSystem.getAllSpells();
        final ArrayAdapter<Spell> adapter = new NameDisplayArrayAdapter<Spell>(this, displayService, allSpells);
        adapter.sort(new SpellComparator());
        return adapter;
    }

    @Override
    protected Intent getCreateIntent() {
        return new Intent(this, SpellAdministrationCreateActivity.class);
    }

    @Override
    protected Intent getEditIntent(final Spell spell) {
        return new Intent(this, SpellAdministrationEditActivity.class)
                .putExtra(INTENT_EXTRA_DATA_OBJECT, spell.getId());
    }

}
