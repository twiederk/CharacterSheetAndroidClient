package com.android.ash.charactersheet.gui.admin.ability;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.widget.numberview.NumberView;
import com.android.ash.charactersheet.gui.widget.numberview.PositiveNumberViewController;
import com.d20charactersheet.framework.boc.model.ExtraFeatsAbility;

/**
 * Administration of ExtraFeatsAbilities with NumberView to set number of feats.
 */
public class AbilityAdministrationEditExtraFeatsActivity extends AbilityAdministrationEditActivity {

    @Override
    protected int getLayout() {
        return R.layout.ability_administration_edit_extrafeats;
    }

    @Override
    protected String getHeading() {
        return getResources().getString(R.string.ability_administration_edit_title_extrafeats);
    }

    @Override
    protected void fillViews() {
        super.fillViews();
        final ExtraFeatsAbility extraFeatsAbility = (ExtraFeatsAbility) form;
        final NumberView numberView = findViewById(R.id.ability_administration_extrafeats);
        numberView.setController(new PositiveNumberViewController(extraFeatsAbility.getNumberOfFeats()));
    }

    @Override
    protected void fillForm() {
        super.fillForm();
        final ExtraFeatsAbility extraFeatsAbility = (ExtraFeatsAbility) form;
        final NumberView numberView = findViewById(R.id.ability_administration_extrafeats);
        final int numberOfFeats = numberView.getController().getNumber().intValue();
        extraFeatsAbility.setNumberOfFeats(numberOfFeats);
    }
}
