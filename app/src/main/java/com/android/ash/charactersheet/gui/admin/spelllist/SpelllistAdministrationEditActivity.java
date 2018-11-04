package com.android.ash.charactersheet.gui.admin.spelllist;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.Spelllist;

/**
 * Allows to edit existing spell list.
 */
public class SpelllistAdministrationEditActivity extends SpelllistAdministrationActivity {

    @Override
    protected String getHeading() {
        return getString(R.string.spelllist_administration_edit_title);
    }

    @Override
    protected Spelllist createForm() {
        final int spelllistId = getIntent().getExtras().getInt(INTENT_EXTRA_DATA_OBJECT);
        final Spelllist spelllist = gameSystem.getSpelllistService().findSpelllistById(spelllistId,
                gameSystem.getAllSpelllists());
        return spelllist;
    }

}
