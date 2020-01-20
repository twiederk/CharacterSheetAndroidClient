package com.android.ash.charactersheet.gui.admin.ability;

import android.os.Bundle;
import android.widget.SpinnerAdapter;

import com.android.ash.charactersheet.CharacterSheetApplication;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.FormularActivity;
import com.android.ash.charactersheet.gui.widget.EnumSpinnerAdapter;
import com.d20charactersheet.framework.boc.model.Ability;
import com.d20charactersheet.framework.boc.model.AbilityType;
import com.d20charactersheet.framework.boc.service.AbilityService;
import com.d20charactersheet.framework.boc.service.GameSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

/**
 * Displays a form to handle abilities. Derive from this class to create or update a ability using this form.
 */
public class AbilityAdministrationEditActivity extends FormularActivity<Ability> {

    GameSystem gameSystem;
    private AbilityService abilityService;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final CharacterSheetApplication application = (CharacterSheetApplication) getApplication();
        gameSystem = application.getGameSystem();
        abilityService = gameSystem.getAbilityService();

        super.onCreate(savedInstanceState, getLayout());
    }

    int getLayout() {
        return R.layout.ability_administration_edit;
    }

    @Override
    protected void fillViews() {
        setText(form.getName(), R.id.ability_administration_name);
        setAbilityTypeSpinner();
        setText(form.getDescription(), R.id.ability_administration_desc);
    }

    private void setAbilityTypeSpinner() {
        final List<AbilityType> abilityTypes = Arrays.asList(AbilityType.values());
        final List<Enum<?>> enumAbilityTypes = new ArrayList<Enum<?>>(abilityTypes);

        final SpinnerAdapter abilityTypeAdapter = new EnumSpinnerAdapter(this, displayService, enumAbilityTypes) {

            @Override
            protected String getText(final Enum<?> enumeration) {
                return displayService.getDisplayAbilityType((AbilityType) enumeration);
            }

        };
        final int position = getEnumPosition(enumAbilityTypes, form.getAbilityType());
        setSpinner(R.id.ability_administration_type, abilityTypeAdapter, position);
    }

    @Override
    protected void fillForm() {
        form.setName(getTextOfTextView(R.id.ability_administration_name));
        form.setAbilityType((AbilityType) getSelectedItemOfSpinner(R.id.ability_administration_type));
        form.setDescription(getTextOfTextView(R.id.ability_administration_desc));
    }

    @Override
    protected Ability createForm() {
        final int abilityId = Objects.requireNonNull(getIntent().getExtras()).getInt(INTENT_EXTRA_DATA_OBJECT);
        return abilityService.getAbilityById(abilityId, gameSystem.getAllAbilities());
    }

    @Override
    protected String getHeading() {
        return getResources().getString(R.string.ability_administration_edit_title_description);
    }

    @Override
    protected void saveForm() {
        abilityService.updateAbility(form);
        gameSystem.clear();
    }

}
