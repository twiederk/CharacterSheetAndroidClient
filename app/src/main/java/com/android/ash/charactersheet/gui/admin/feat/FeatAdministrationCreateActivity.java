package com.android.ash.charactersheet.gui.admin.feat;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.Feat;
import com.d20charactersheet.framework.boc.model.FeatType;

/**
 * Form to create a feat.
 */
public class FeatAdministrationCreateActivity extends FeatAdministrationActivity {

    @Override
    protected Feat createForm() {
        final Feat feat = new Feat();
        feat.setName("");
        feat.setFeatType(FeatType.GENERAL);
        feat.setBenefit("");
        feat.setPrerequisit("");
        feat.setFighterBonus(false);
        feat.setMultiple(false);
        feat.setStack(false);
        return feat;
    }

    @Override
    protected void saveForm() {
        if (!form.getName().trim().isEmpty()) {
            featService.createFeat(form);
            gameSystem.clear();
        }
    }

    @Override
    protected String getHeading() {
        return getResources().getString(R.string.feat_administration_create_title);
    }

}
