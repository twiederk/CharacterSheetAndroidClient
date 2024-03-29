package com.android.ash.charactersheet.gui.admin.spell;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.Spell;

import java.util.Objects;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

/**
 * Mask to edit an existing spell.
 */
public class SpellAdministrationEditActivity extends SpellAdministrationActivity {

    @Override
    protected String getHeading() {
        return getResources().getString(R.string.spell_administration_edit_title);
    }

    @Override
    protected Spell createForm() {
        final int spellId = Objects.requireNonNull(getIntent().getExtras()).getInt(INTENT_EXTRA_DATA_OBJECT);
        final Spell spell = gameSystem.getSpelllistService().findSpellById(spellId, gameSystem.getAllSpells());
        gameSystem.getSpelllistService().getSpellDescription(spell);
        return spell;
    }

    @Override
    protected void saveForm() {
        gameSystem.getSpelllistService().updateSpell(form);
        gameSystem.clear();
    }

}
