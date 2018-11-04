package com.android.ash.charactersheet.gui.admin.race;

import java.util.ArrayList;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.Ability;
import com.d20charactersheet.framework.boc.model.CharacterClass;
import com.d20charactersheet.framework.boc.model.Race;
import com.d20charactersheet.framework.boc.model.Size;

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
        race.setFavoredCharacterClass(CharacterClass.ANY_CHARACTER_CLASS);
        race.setAbilities(new ArrayList<Ability>());
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
