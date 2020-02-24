package com.android.ash.charactersheet.gui.admin.ability;

import android.widget.SpinnerAdapter;

import com.android.ash.charactersheet.GameSystemHolder;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.FormActivity;
import com.android.ash.charactersheet.gui.widget.EnumSpinnerAdapter;
import com.d20charactersheet.framework.boc.model.Ability;
import com.d20charactersheet.framework.boc.model.AbilityType;
import com.d20charactersheet.framework.boc.service.AbilityService;
import com.d20charactersheet.framework.boc.service.GameSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import kotlin.Lazy;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;
import static org.koin.java.KoinJavaComponent.inject;

/**
 * Displays a form to handle abilities. Derive from this class to create or update a ability using this form.
 */
public class AbilityAdministrationEditActivity extends FormActivity<Ability> {

    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);

    GameSystem gameSystem;
    private AbilityService abilityService;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.ability_administration_edit;
    }

    @Override
    protected void createServices() {
        gameSystem = gameSystemHolder.getValue().getGameSystem();
        abilityService = Objects.requireNonNull(gameSystem).getAbilityService();
    }

    @Override
    protected void fillViews() {
        setText(form.getName(), R.id.ability_administration_name);
        setAbilityTypeSpinner();
        setText(form.getDescription(), R.id.ability_administration_desc);
    }

    private void setAbilityTypeSpinner() {
        final List<AbilityType> abilityTypes = Arrays.asList(AbilityType.values());
        final List<Enum<?>> enumAbilityTypes = new ArrayList<>(abilityTypes);

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
