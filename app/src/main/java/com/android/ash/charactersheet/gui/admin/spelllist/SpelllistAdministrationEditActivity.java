package com.android.ash.charactersheet.gui.admin.spelllist;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.Spelllist;

import java.util.Objects;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

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
        final int spelllistId = Objects.requireNonNull(getIntent().getExtras()).getInt(INTENT_EXTRA_DATA_OBJECT);
        return gameSystem.getSpelllistService().findSpelllistById(spelllistId,
                gameSystem.getAllSpelllists());
    }

}
