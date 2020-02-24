package com.android.ash.charactersheet.gui.admin.clazz.ability;

import android.content.Intent;
import android.os.Bundle;

import com.android.ash.charactersheet.GameSystemHolder;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.FormActivity;
import com.android.ash.charactersheet.gui.widget.numberview.NumberView;
import com.android.ash.charactersheet.gui.widget.numberview.NumberViewController;
import com.android.ash.charactersheet.gui.widget.numberview.PositiveNumberViewController;
import com.d20charactersheet.framework.boc.model.Ability;
import com.d20charactersheet.framework.boc.model.ClassAbility;
import com.d20charactersheet.framework.boc.service.GameSystem;

import java.util.Objects;

import kotlin.Lazy;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;
import static org.koin.java.KoinJavaComponent.inject;

/**
 * Displays class ability with name and level to create or edit.
 */
public class ClassAdministrationAbilityEditActivity extends FormActivity<ClassAbility> {

    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);

    private NumberViewController levelController;
    private GameSystem gameSystem;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.class_admininistration_edit_ability;
    }

    @Override
    protected void createServices() {
        gameSystem = gameSystemHolder.getValue().getGameSystem();
    }


    @Override
    protected String getHeading() {
        return getResources().getString(R.string.class_administration_ability_edit_title);
    }

    @Override
    protected ClassAbility createForm() {
        final Bundle extras = getIntent().getExtras();
        final int[] classAbilityData = Objects.requireNonNull(extras).getIntArray(INTENT_EXTRA_DATA_OBJECT);
        final int abilityId = Objects.requireNonNull(classAbilityData)[0];
        final int level = classAbilityData[1];
        final Ability ability = gameSystem.getAbilityService().getAbilityById(abilityId, gameSystem.getAllAbilities());
        final ClassAbility classAbility = new ClassAbility(ability);
        classAbility.setLevel(level);

        return classAbility;
    }

    @Override
    protected void fillViews() {
        setText(form.getAbility().getName(), R.id.class_administration_ability_edit_name);
        final NumberView numberView = findViewById(R.id.class_administration_ability_edit_level);
        levelController = new PositiveNumberViewController(form.getLevel());
        numberView.setController(levelController);
    }

    @Override
    protected void fillForm() {
        final int level = levelController.getNumber().intValue();
        form.setLevel(level);
    }

    @Override
    protected void saveForm() {
        final Intent resultIntent = new Intent();
        resultIntent.putExtra(INTENT_EXTRA_DATA_OBJECT, new int[]{form.getAbility().getId(), form.getLevel()});
        setResult(RESULT_OK, resultIntent);
    }

}
