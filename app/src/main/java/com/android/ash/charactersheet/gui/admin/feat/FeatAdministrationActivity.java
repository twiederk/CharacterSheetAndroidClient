package com.android.ash.charactersheet.gui.admin.feat;

import java.util.Arrays;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import com.android.ash.charactersheet.CharacterSheetApplication;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.FormularActivity;
import com.android.ash.charactersheet.gui.widget.numberview.ZeroAndPositiveNumberViewController;
import com.d20charactersheet.framework.boc.model.Feat;
import com.d20charactersheet.framework.boc.model.FeatType;
import com.d20charactersheet.framework.boc.service.FeatService;
import com.d20charactersheet.framework.boc.service.GameSystem;

/**
 * Form to create and edit feats. Displays text boxes to enter name, benefit and prerequisit of feat. The type of feat
 * is displayed in a spinner. Fighter bonus feat, multiple and stack can be set by checkboxes. The multiple and stack
 * checkbox exclude each other.
 */
public abstract class FeatAdministrationActivity extends FormularActivity<Feat> {

    protected GameSystem gameSystem;
    protected FeatService featService;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        final CharacterSheetApplication application = (CharacterSheetApplication) getApplication();
        gameSystem = application.getGameSystem();
        featService = gameSystem.getFeatService();

        super.onCreate(savedInstanceState, R.layout.feat_administration);
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
        final CheckBox multipleCheckBox = (CheckBox) findViewById(R.id.feat_administration_multiple);
        final CheckBox stackCheckBox = (CheckBox) findViewById(R.id.feat_administration_stack);

        multipleCheckBox.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View view) {
                stackCheckBox.setChecked(false);
            }
        });

        stackCheckBox.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {
                multipleCheckBox.setChecked(false);
            }
        });
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
