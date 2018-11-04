package com.android.ash.charactersheet.gui.admin.ability;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.android.ash.charactersheet.CharacterSheetApplication;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.boc.model.AbilityClass;
import com.android.ash.charactersheet.gui.util.FormularActivity;
import com.android.ash.charactersheet.gui.widget.EnumSpinnerAdapter;
import com.d20charactersheet.framework.boc.model.Ability;
import com.d20charactersheet.framework.boc.model.AbilityType;
import com.d20charactersheet.framework.boc.model.Attribute;
import com.d20charactersheet.framework.boc.model.CastingType;
import com.d20charactersheet.framework.boc.model.ExtraFeatsAbility;
import com.d20charactersheet.framework.boc.model.ExtraSkillPointsAbility;
import com.d20charactersheet.framework.boc.model.School;
import com.d20charactersheet.framework.boc.model.SpellSource;
import com.d20charactersheet.framework.boc.model.SpelllistAbility;
import com.d20charactersheet.framework.boc.service.GameSystem;

/**
 * Creates a new ability.
 */
public class AbilityAdministrationCreateActivity extends FormularActivity<Ability> {

    protected GameSystem gameSystem;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        final CharacterSheetApplication application = (CharacterSheetApplication) getApplication();
        gameSystem = application.getGameSystem();
        super.onCreate(savedInstanceState, R.layout.ability_administration_create);
    }

    @Override
    protected Ability createForm() {
        return new Ability();
    }

    @Override
    protected String getHeading() {
        return getResources().getString(R.string.ability_administration_create_title);
    }

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void fillViews() {
        setAbilityClassSpinner();
        setText(getString(R.string.ability_administration_create_class), R.id.ability_administration_create_explanation);
        setCreateButton();
    }

    protected void setAbilityClassSpinner() {
        final List<AbilityClass> abilityClass = Arrays.asList(AbilityClass.values());
        final List<Enum<?>> enumAbilityTypes = new ArrayList<Enum<?>>(abilityClass);

        final SpinnerAdapter abilityTypeAdapter = new EnumSpinnerAdapter(this, displayService, enumAbilityTypes) {

            @Override
            protected String getText(final Enum<?> enumeration) {
                final AbilityClass abilityClass = (AbilityClass) enumeration;
                switch (abilityClass) {
                case EXTRA_FEATS_ABILITY:
                    return getString(R.string.ability_class_extrafeats);
                case EXTRA_SKILL_POINTS_ABILITY:
                    return getString(R.string.ability_class_extraskillpoints);
                case SPELLLIST_ABILITY:
                    return getString(R.string.ability_class_spelllist);
                default:
                    return getString(R.string.ability_class_description);
                }
            }

        };
        setSpinner(R.id.ability_administration_create_class, abilityTypeAdapter, 0);
        final Spinner spinner = (Spinner) findViewById(R.id.ability_administration_create_class);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(final AdapterView<?> spinner, final View view, final int position, final long id) {
                final AbilityClass abilityClass = (AbilityClass) spinner.getItemAtPosition(position);
                setExplanation(abilityClass);
            }

            @Override
            public void onNothingSelected(final AdapterView<?> spinner) {
                // nothing to do

            }

        });
    }

    @Override
    protected void fillForm() {
        // nothing to do
    }

    private void setCreateButton() {
        final Button createButton = (Button) findViewById(R.id.create_button);
        createButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View view) {
                save();
            }

        });
    }

    @Override
    protected void save() {
        saveForm();
        final Class<?> activityClass = getActivityClass();
        final Intent intent = new Intent(this, activityClass).putExtra(INTENT_EXTRA_DATA_OBJECT, form.getId());
        startActivity(intent);
        finish();
    }

    private Class<?> getActivityClass() {
        if (form instanceof ExtraFeatsAbility) {
            return AbilityAdministrationEditExtraFeatsActivity.class;
        } else if (form instanceof ExtraSkillPointsAbility) {
            return AbilityAdministrationEditExtraSkillPointsActivity.class;
        } else if (form instanceof SpelllistAbility) {
            return AbilityAdministrationEditSpelllistActivity.class;
        }
        return AbilityAdministrationEditActivity.class;
    }

    @Override
    protected void saveForm() {
        final AbilityClass abilityClass = (AbilityClass) getSelectedItemOfSpinner(R.id.ability_administration_create_class);
        switch (abilityClass) {
        case EXTRA_FEATS_ABILITY:
            final ExtraFeatsAbility extraFeatsAbility = new ExtraFeatsAbility();
            extraFeatsAbility.setNumberOfFeats(1);
            form = extraFeatsAbility;
            break;
        case EXTRA_SKILL_POINTS_ABILITY:
            final ExtraSkillPointsAbility extraSkillPointsAbility = new ExtraSkillPointsAbility();
            extraSkillPointsAbility.setSkillPoints(1);
            form = extraSkillPointsAbility;
            break;
        case SPELLLIST_ABILITY:
            final SpelllistAbility spelllistAbility = new SpelllistAbility();
            spelllistAbility.setSpelllist(gameSystem.getAllSpelllists().get(0));
            spelllistAbility.setSpellAttribute(Attribute.INTELLIGENCE);
            spelllistAbility.setCastingType(CastingType.PREPARED);
            spelllistAbility.setSpellSource(SpellSource.ARCANE);
            spelllistAbility.setKnownSpellsTable(gameSystem.getAllKnownSpellsTables().get(0));
            spelllistAbility.setSpellsPerDayTable(gameSystem.getAllSpellsPerDayTables().get(0));
            spelllistAbility.setAttributeBonusSpellSlots(true);
            spelllistAbility.setSchools(EnumSet.allOf(School.class));
            form = spelllistAbility;
            break;
        default:
            break;
        }
        form.setName("");
        form.setAbilityType(AbilityType.EXTRAORDINARY);
        form.setDescription("");

        gameSystem.getAbilityService().createAbility(form);
        gameSystem.clear();
    }

    private void setExplanation(final AbilityClass abilityClass) {
        final String explanation;
        switch (abilityClass) {
        case EXTRA_FEATS_ABILITY:
            explanation = getString(R.string.ability_administration_create_class_extrafeats);
            break;
        case EXTRA_SKILL_POINTS_ABILITY:
            explanation = getString(R.string.ability_administration_create_class_extraskillpoints);
            break;
        case SPELLLIST_ABILITY:
            explanation = getString(R.string.ability_administration_create_class_splelllist);
            break;
        default:
            explanation = getString(R.string.ability_administration_create_class_description);
            break;
        }
        setText(explanation, R.id.ability_administration_create_explanation);
    }
}
