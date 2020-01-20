package com.android.ash.charactersheet.gui.admin.race;

import android.content.Intent;
import android.widget.ArrayAdapter;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.AdministrationListActivity;
import com.android.ash.charactersheet.gui.widget.NameDisplayArrayAdapter;
import com.d20charactersheet.framework.boc.model.Race;
import com.d20charactersheet.framework.boc.util.RaceComparator;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

/**
 * Lists all available races alphabetically and allows to create new races or edit existing.
 */
public class RaceAdministrationListActivity extends AdministrationListActivity<Race> {

    @Override
    protected int getTitleResource() {
        return R.string.race_administration_list_title;
    }

    @Override
    protected ArrayAdapter<Race> getAdapter() {
        final ArrayAdapter<Race> adapter = new NameDisplayArrayAdapter<>(this, displayService,
                gameSystem.getAllRaces());
        adapter.sort(new RaceComparator());
        return adapter;
    }

    @Override
    protected Intent getCreateIntent() {
        return new Intent(this, RaceAdministrationCreateActivity.class);
    }

    @Override
    protected Intent getEditIntent(final Race race) {
        return new Intent(this, RaceAdministrationEditActivity.class).putExtra(INTENT_EXTRA_DATA_OBJECT, race.getId());
    }

}
