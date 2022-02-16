package com.android.ash.charactersheet.gui.admin.spell;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.CastingTime;
import com.d20charactersheet.framework.boc.model.Descriptor;
import com.d20charactersheet.framework.boc.model.School;
import com.d20charactersheet.framework.boc.model.Spell;
import com.d20charactersheet.framework.boc.model.SpellResistance;
import com.d20charactersheet.framework.boc.model.SubSchool;

/**
 * Mask to create a new spell.
 */
public class SpellAdministrationCreateActivity extends SpellAdministrationActivity {

    private static final String EMPTY_STRING = "";

    @Override
    protected String getHeading() {
        return getResources().getString(R.string.spell_administration_create_title);
    }

    @Override
    protected Spell createForm() {
        final Spell spell = new Spell();
        spell.setName(EMPTY_STRING);
        spell.setSchool(School.ABJURATION);
        spell.setSubSchool(SubSchool.NONE);
        spell.setDescriptors(new Descriptor[] { Descriptor.NONE });
        spell.setCastingTime(CastingTime.ONE_STANDARD_ACTION);
        spell.setRange("Close (25 ft. + 5 ft./2 levels)");
        spell.setEffect(EMPTY_STRING);
        spell.setDuration(EMPTY_STRING);
        spell.setSavingThrow(EMPTY_STRING);
        spell.setSpellResistance(SpellResistance.NONE);
        spell.setDescription(EMPTY_STRING);
        spell.setMaterialComponent(EMPTY_STRING);
        spell.setShortDescription(EMPTY_STRING);
        return spell;
    }

    @Override
    protected void saveForm() {
        if (!form.getName().trim().isEmpty()) {
            form = gameSystem.getSpelllistService().createSpell(form);
            gameSystem.clear();
        }
    }

}
