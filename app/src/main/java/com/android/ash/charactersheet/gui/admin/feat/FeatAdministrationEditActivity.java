package com.android.ash.charactersheet.gui.admin.feat;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.Feat;

import java.util.Objects;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

/**
 * Form to edit a feat.
 */
public class FeatAdministrationEditActivity extends FeatAdministrationActivity {

    @Override
    protected Feat createForm() {
        final int featId = Objects.requireNonNull(getIntent().getExtras()).getInt(INTENT_EXTRA_DATA_OBJECT);
        return gameSystem.getFeatService().findFeatById(featId, gameSystem.getAllFeats());
    }

    @Override
    protected void saveForm() {
        featService.updateFeat(form);
        gameSystem.clear();
    }

    @Override
    protected String getHeading() {
        return getResources().getString(R.string.feat_administration_edit_title);
    }

}
