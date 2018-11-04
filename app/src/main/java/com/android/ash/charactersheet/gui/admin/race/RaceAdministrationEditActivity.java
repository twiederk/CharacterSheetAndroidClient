package com.android.ash.charactersheet.gui.admin.race;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;
import android.content.Intent;
import android.os.Bundle;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.Race;

/**
 * Activity to edit an existing race.
 */
public class RaceAdministrationEditActivity extends RaceAdministrationActivity {

    @Override
    protected Race createForm() {
        final Intent intent = getIntent();
        final Bundle extras = intent.getExtras();
        final int raceId = extras.getInt(INTENT_EXTRA_DATA_OBJECT);
        final Race race = gameSystem.getRaceService().findRaceById(raceId, gameSystem.getAllRaces());
        return race;
    }

    @Override
    protected String getHeading() {
        return getResources().getString(R.string.race_administration_edit_title);
    }

    @Override
    protected void saveForm() {
        raceService.updateRace(form);
        gameSystem.clear();
    }

}
