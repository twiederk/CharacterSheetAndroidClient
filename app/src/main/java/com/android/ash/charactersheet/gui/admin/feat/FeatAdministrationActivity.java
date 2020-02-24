package com.android.ash.charactersheet.gui.admin.feat;

import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import com.android.ash.charactersheet.GameSystemHolder;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.FormActivity;
import com.android.ash.charactersheet.gui.widget.numberview.ZeroAndPositiveNumberViewController;
import com.d20charactersheet.framework.boc.model.Feat;
import com.d20charactersheet.framework.boc.model.FeatType;
import com.d20charactersheet.framework.boc.service.FeatService;
import com.d20charactersheet.framework.boc.service.GameSystem;

import java.util.Arrays;
import java.util.Objects;

import kotlin.Lazy;

import static org.koin.java.KoinJavaComponent.inject;

/**
 * Form to create and edit feats. Displays text boxes to enter name, benefit and prerequisit of feat. The type of feat
 * is displayed in a spinner. Fighter bonus feat, multiple and stack can be set by checkboxes. The multiple and stack
 * checkbox exclude each other.
 */
public abstract class FeatAdministrationActivity extends FormActivity<Feat> {

    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);

    GameSystem gameSystem;
    FeatService featService;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.feat_administration;
    }

    @Override
    protected void createServices() {
        gameSystem = gameSystemHolder.getValue().getGameSystem();
        featService = Objects.requireNonNull(gameSystem).getFeatService();
    }


    @Override
    protected void fillViews() {
        setText(form.getName(), R.id.feat_administration_name);
        setFeatTypeSpinner();
        setText(form.getBenefit(), R.id.feat_administration_benefit);
        setText(form.getPrerequisit(), R.id.feat_administration_prerequisit);
        setNumberViewController(new ZeroAndPositiveNumberViewController(form.getSpellSlot()),
                R.id.feat_administration_spellslot);
        setCheckBox(form.isFighterBonus(), R.id.feat_administration_fighter_bonus);
        setCheckBox(form.isMultiple(), R.id.feat_administration_multiple);
        setCheckBox(form.isStack(), R.id.feat_administration_stack);

        setMultipleAndStackOnClickListener();
    }

    private void setMultipleAndStackOnClickListener() {
        final CheckBox multipleCheckBox = findViewById(R.id.feat_administration_multiple);
        final CheckBox stackCheckBox = findViewById(R.id.feat_administration_stack);

        multipleCheckBox.setOnClickListener(view -> stackCheckBox.setChecked(false));

        stackCheckBox.setOnClickListener(v -> multipleCheckBox.setChecked(false));
    }

    private void setFeatTypeSpinner() {
        final ArrayAdapter<FeatType> featTypeArrayAdapter = new FeatTypeArrayAdapter(this, displayService,
                Arrays.asList(FeatType.values()));
        setSpinner(R.id.feat_administration_feattype, featTypeArrayAdapter, form.getFeatType().ordinal());
    }

    @Override
    protected void fillForm() {
        form.setName(getTextOfTextView(R.id.feat_administration_name));
        form.setFeatType((FeatType) getSelectedItemOfSpinner(R.id.feat_administration_feattype));
        form.setBenefit(getTextOfTextView(R.id.feat_administration_benefit));
        form.setPrerequisit(getTextOfTextView(R.id.feat_administration_prerequisit));
        form.setSpellSlot(getIntegerOfNumberView(R.id.feat_administration_spellslot));
        form.setFighterBonus(isChecked(R.id.feat_administration_fighter_bonus));
        form.setMultiple(isChecked(R.id.feat_administration_multiple));
        form.setStack(isChecked(R.id.feat_administration_stack));
    }

}
