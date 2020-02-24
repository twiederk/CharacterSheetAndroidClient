package com.android.ash.charactersheet.gui.admin.spelllist;

import android.annotation.SuppressLint;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.Spelllist;

import java.util.HashMap;

/**
 * Creates a new spell list and allows to edit it.
 */
public class SpelllistAdministrationCreateActivity extends SpelllistAdministrationActivity {

    @Override
    protected String getHeading() {
        return getString(R.string.spelllist_administration_create_title);
    }

    @SuppressLint("UseSparseArrays")
    @Override
    protected Spelllist createForm() {
        final Spelllist spelllist = new Spelllist();
        spelllist.setName("");
        spelllist.setShortname("");
        spelllist.setSpellsByLevel(new HashMap<>());
        gameSystem.getSpelllistService().createSpelllist(spelllist);
        return spelllist;
    }

}
