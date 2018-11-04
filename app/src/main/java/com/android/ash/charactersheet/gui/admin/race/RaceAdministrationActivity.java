package com.android.ash.charactersheet.gui.admin.race;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import com.android.ash.charactersheet.CharacterSheetApplication;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.admin.race.ability.RaceAdministrationAbilityListActivity;
import com.android.ash.charactersheet.gui.util.FormularActivity;
import com.android.ash.charactersheet.gui.util.IntentAndResultOnClickListener;
import com.android.ash.charactersheet.gui.widget.SpinnerArrayAdapter;
import com.d20charactersheet.framework.boc.model.Ability;
import com.d20charactersheet.framework.boc.model.CharacterClass;
import com.d20charactersheet.framework.boc.model.Race;
import com.d20charactersheet.framework.boc.model.Size;
import com.d20charactersheet.framework.boc.service.GameSystem;
import com.d20charactersheet.framework.boc.service.RaceService;
import com.d20charactersheet.framework.boc.util.AbilityComparator;
import com.d20charactersheet.framework.boc.util.CharacterClassComparator;

/**
 * Fills and stores the form of a race. Derive from this class to use the form for create or update.
 */
public abstract class RaceAdministrationActivity extends FormularActivity<Race> {

    private static final int REQUEST_CODE_ABILITIES = 1;

    protected GameSystem gameSystem;
    protected RaceService raceService;

    private List<Ability> abilities;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        final CharacterSheetApplication application = (CharacterSheetApplication) getApplication();
        gameSystem = application.getGameSystem();
        raceService = gameSystem.getRaceService();
        super.onCreate(savedInstanceState, R.layout.race_administration);

        abilities = new ArrayList<Ability>(form.getAbilities());

        createViews();
    }

    private void createViews() {
        setText(form.getName(), R.id.race_administration_name);
        setSpinner(R.id.race_administration_size, getSizeAdapter(), form.getSize().ordinal());
        setText(Integer.toString(form.getBaseLandSpeed()), R.id.race_administration_speed);
        setFavoriteCharacterClassSpinner();
    }

    @Override
    protected void fillViews() {
        setAbilities();
    }

    private SpinnerAdapter getSizeAdapter() {
        final ArrayAdapter<Size> sizeArrayAdapter = new SizeArrayAdapter(this, displayService, Arrays.asList(Size
                .values()));
        return sizeArrayAdapter;
    }

    private void setFavoriteCharacterClassSpinner() {
        final List<CharacterClass> characterClasses = getCharacterClassesOfSpinner();
        setSpinner(R.id.race_administration_class, getCharacterClassAdapter(characterClasses),
                getCharacterClassIndex(characterClasses));
    }

    private List<CharacterClass> getCharacterClassesOfSpinner() {
        final List<CharacterClass> characterClasses = gameSystem.getAllCharacterClasses();
        characterClasses.add(CharacterClass.ANY_CHARACTER_CLASS);
        Collections.sort(characterClasses, new CharacterClassComparator());
        return characterClasses;
    }

    private SpinnerAdapter getCharacterClassAdapter(final List<CharacterClass> characterClasses) {
        final ArrayAdapter<CharacterClass> characterClassArrayAdapter = new SpinnerArrayAdapter<CharacterClass>(this,
                displayService, characterClasses);
        return characterClassArrayAdapter;
    }

    private int getCharacterClassIndex(final List<CharacterClass> characterClasses) {
        int index = 0;
        for (final CharacterClass characterClass : characterClasses) {
            if (characterClass.getId() == form.getFavoredCharacterClass().getId()) {
                break;
            }
            index++;
        }
        return index;
    }

    private void setAbilities() {
        setText(getAbilitiesText(), R.id.race_administration_abilities);
        final View skillView = findViewById(R.id.race_administration_abilities);
        final Intent intent = new Intent(this, RaceAdministrationAbilityListActivity.class);
        final List<Integer> abilityIds = getAbilityIds();
        intent.putExtra(INTENT_EXTRA_DATA_OBJECT, (Serializable) abilityIds);
        skillView.setOnClickListener(new IntentAndResultOnClickListener(this, intent, REQUEST_CODE_ABILITIES));

    }

    private String getAbilitiesText() {
        Collections.sort(abilities, new AbilityComparator());
        final StringBuilder builder = new StringBuilder();
        builder.append(getResources().getString(R.string.race_administration_abilities));
        builder.append(":\n");
        for (final Iterator<Ability> iterator = abilities.iterator(); iterator.hasNext();) {
            final Ability ability = iterator.next();
            builder.append(ability.getName());
            if (iterator.hasNext()) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    private List<Integer> getAbilityIds() {
        final List<Integer> abilityIds = new ArrayList<Integer>(abilities.size());
        for (final Ability ability : abilities) {
            abilityIds.add(ability.getId());
        }
        return abilityIds;
    }

    @Override
    protected void fillForm() {
        form.setName(getTextOfTextView(R.id.race_administration_name));
        form.setSize((Size) getSelectedItemOfSpinner(R.id.race_administration_size));
        form.setBaseLandSpeed(getIntegerOfTextView(R.id.race_administration_speed));
        form.setFavoredCharacterClass((CharacterClass) getSelectedItemOfSpinner(R.id.race_administration_class));
        form.setAbilities(abilities);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent resultIntent) {

        // see which child activity is calling us back.
        switch (requestCode) {

        case REQUEST_CODE_ABILITIES:
            if (resultCode != RESULT_CANCELED) {
                abilities = getAbilitiesFromIntend(resultIntent);

            }
            break;

        default:
            throw new IllegalStateException("Result code (" + requestCode + ") is unknown");
        }
    }

    private List<Ability> getAbilitiesFromIntend(final Intent resultIntent) {
        final Bundle bundle = resultIntent.getExtras();
        final List<Integer> abilityIds = (List<Integer>) bundle.get(INTENT_EXTRA_DATA_OBJECT);
        final List<Ability> abilities = new ArrayList<Ability>(abilityIds.size());
        for (final Integer abilityId : abilityIds) {
            final Ability ability = gameSystem.getAbilityService().getAbilityById(abilityId,
                    gameSystem.getAllAbilities());
            abilities.add(ability);
        }
        return abilities;
    }

}
