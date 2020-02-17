package com.android.ash.charactersheet.gui.admin.ability;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.widget.numberview.NumberView;
import com.android.ash.charactersheet.gui.widget.numberview.PositiveNumberViewController;
import com.d20charactersheet.framework.boc.model.ExtraSkillPointsAbility;

/**
 * Administration of ExtraFeatsSkillPointsAbilities with NumberView to set number of skill points.
 */
public class AbilityAdministrationEditExtraSkillPointsActivity extends AbilityAdministrationEditActivity {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.ability_administration_edit_extraskillpoints;
    }

    @Override
    protected String getHeading() {
        return getResources().getString(R.string.ability_administration_edit_title_extraskillpoints);
    }

    @Override
    protected void fillViews() {
        super.fillViews();
        final ExtraSkillPointsAbility extraSkillPointsAbility = (ExtraSkillPointsAbility) form;
        final NumberView numberView = findViewById(R.id.ability_administration_extraskillpoints);
        numberView.setController(new PositiveNumberViewController(extraSkillPointsAbility.getSkillPoints()));
    }

    @Override
    protected void fillForm() {
        super.fillForm();
        final ExtraSkillPointsAbility extraSkillPointsAbility = (ExtraSkillPointsAbility) form;
        final NumberView numberView = findViewById(R.id.ability_administration_extraskillpoints);
        final int skillPoints = numberView.getController().getNumber().intValue();
        extraSkillPointsAbility.setSkillPoints(skillPoints);
    }
}
