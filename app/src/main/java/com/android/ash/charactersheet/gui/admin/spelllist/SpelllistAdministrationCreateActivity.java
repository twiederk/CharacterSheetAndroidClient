package com.android.ash.charactersheet.gui.admin.spelllist;

import java.util.HashMap;
import java.util.List;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.Spell;
import com.d20charactersheet.framework.boc.model.Spelllist;

/**
 * Creates a new spell list and allows to edit it.
 */
public class SpelllistAdministrationCreateActivity extends SpelllistAdministrationActivity {

    @Override
    protected String getHeading() {
        return getString(R.string.spelllist_administration_create_title);
    }

    @Override
    protected Spelllist createForm() {
        final Spelllist spelllist = new Spelllist();
        spelllist.setName("");
        spelllist.setShortname("");
        spelllist.setSpellsByLevel(new HashMap<Integer, List<Spell>>());
        gameSystem.getSpelllistService().createSpelllist(spelllist);
        return spelllist;
    }

}
