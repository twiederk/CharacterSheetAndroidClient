package com.android.ash.charactersheet.gui.admin.race;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;
import static com.d20charactersheet.framework.boc.model.CharacterClass.AnyCharacterClass.ANY_CHARACTER_CLASS;
import static org.koin.java.KoinJavaComponent.inject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SpinnerAdapter;

import com.android.ash.charactersheet.GameSystemHolder;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.admin.race.ability.RaceAdministrationAbilityListActivity;
import com.android.ash.charactersheet.gui.util.FormActivity;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import kotlin.Lazy;

/**
 * Fills and stores the form of a race. Derive from this class to use the form for create or update.
 */
public abstract class RaceAdministrationActivity extends FormActivity<Race> {

    private static final int REQUEST_CODE_ABILITIES = 1;

    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);


    GameSystem gameSystem;
    RaceService raceService;

    private List<Ability> abilities;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        abilities = new ArrayList<>(form.getAbilities());
        createViews();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.race_administration;
    }

    @Override
    protected void createServices() {
        gameSystem = gameSystemHolder.getValue().getGameSystem();
        raceService = Objects.requireNonNull(gameSystem).getRaceService();
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
        return new SizeArrayAdapter(this, displayService, Arrays.asList(Size.values()));
    }

    private void setFavoriteCharacterClassSpinner() {
        final List<CharacterClass> characterClasses = getCharacterClassesOfSpinner();
        setSpinner(R.id.race_administration_class, getCharacterClassAdapter(characterClasses),
                getCharacterClassIndex(characterClasses));
    }

    private List<CharacterClass> getCharacterClassesOfSpinner() {
        final List<CharacterClass> characterClasses = gameSystem.getAllCharacterClasses();
        characterClasses.add(ANY_CHARACTER_CLASS);
        characterClasses.sort(new CharacterClassComparator());
        return characterClasses;
    }

    private SpinnerAdapter getCharacterClassAdapter(final List<CharacterClass> characterClasses) {
        return new SpinnerArrayAdapter<>(this, displayService, characterClasses);
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
        abilities.sort(new AbilityComparator());
        final StringBuilder builder = new StringBuilder();
        builder.append(getResources().getString(R.string.race_administration_abilities));
        builder.append(":\n");
        for (final Iterator<Ability> iterator = abilities.iterator(); iterator.hasNext(); ) {
            final Ability ability = iterator.next();
            builder.append(ability.getName());
            if (iterator.hasNext()) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    private List<Integer> getAbilityIds() {
        final List<Integer> abilityIds = new ArrayList<>(abilities.size());
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
        super.onActivityResult(requestCode, resultCode, resultIntent);

        if (requestCode == REQUEST_CODE_ABILITIES) {
            if (resultCode != RESULT_CANCELED) {
                abilities = getAbilitiesFromIntend(resultIntent);

            }
        } else {
            throw new IllegalStateException("Result code (" + requestCode + ") is unknown");
        }
    }

    @SuppressWarnings("unchecked")
    private List<Ability> getAbilitiesFromIntend(final Intent resultIntent) {
        final Bundle bundle = resultIntent.getExtras();
        final List<Integer> abilityIds = (List<Integer>) Objects.requireNonNull(bundle).get(INTENT_EXTRA_DATA_OBJECT);
        final List<Ability> abilities = new ArrayList<>(Objects.requireNonNull(abilityIds).size());
        for (final Integer abilityId : abilityIds) {
            final Ability ability = gameSystem.getAbilityService().getAbilityById(abilityId,
                    gameSystem.getAllAbilities());
            abilities.add(ability);
        }
        return abilities;
    }

}
