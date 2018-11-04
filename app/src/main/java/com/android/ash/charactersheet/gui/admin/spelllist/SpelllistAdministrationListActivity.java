package com.android.ash.charactersheet.gui.admin.spelllist;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

import java.util.List;

import android.content.Intent;
import android.widget.ArrayAdapter;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.AdministrationListActivity;
import com.android.ash.charactersheet.gui.widget.NameDisplayArrayAdapter;
import com.d20charactersheet.framework.boc.model.Spelllist;
import com.d20charactersheet.framework.boc.util.SpelllistComparator;

/**
 * Displays all spell lists and allows to create a new one or edit an existing one.
 */
public class SpelllistAdministrationListActivity extends AdministrationListActivity<Spelllist> {

    @Override
    protected int getTitleResource() {
        return R.string.spelllist_administration_list_title;
    }

    @Override
    protected ArrayAdapter<Spelllist> getAdapter() {
        final List<Spelllist> allSpelllists = gameSystem.getAllSpelllists();
        final ArrayAdapter<Spelllist> adapter = new NameDisplayArrayAdapter<Spelllist>(this, displayService,
                allSpelllists);
        adapter.sort(new SpelllistComparator());
        return adapter;
    }

    @Override
    protected Intent getCreateIntent() {
        return new Intent(this, SpelllistAdministrationCreateActivity.class);
    }

    @Override
    protected Intent getEditIntent(final Spelllist spelllist) {
        return new Intent(this, SpelllistAdministrationEditActivity.class).putExtra(INTENT_EXTRA_DATA_OBJECT,
                spelllist.getId());
    }

}
