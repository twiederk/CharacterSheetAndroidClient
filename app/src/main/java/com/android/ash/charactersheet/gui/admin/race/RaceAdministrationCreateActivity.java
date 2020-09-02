package com.android.ash.charactersheet.gui.admin.race;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.Race;
import com.d20charactersheet.framework.boc.model.Size;

import java.util.ArrayList;

import static com.d20charactersheet.framework.boc.model.CharacterClass.AnyCharacterClass.ANY_CHARACTER_CLASS;

/**
 * Activity to create a new race.
 */
public class RaceAdministrationCreateActivity extends RaceAdministrationActivity {

    @Override
    protected Race createForm() {
        final Race race = new Race();
        race.setName("");
        race.setSize(Size.MEDIUM);
        race.setBaseLandSpeed(30);
        race.setFavoredCharacterClass(ANY_CHARACTER_CLASS);
        race.setAbilities(new ArrayList<>());
        return race;
    }

    @Override
    protected String getHeading() {
        return getResources().getString(R.string.race_administration_create_title);
    }

    @Override
    protected void saveForm() {
        if (!form.getName().trim().isEmpty()) {
            raceService.createRace(form);
            gameSystem.clear();
        }
    }

}
