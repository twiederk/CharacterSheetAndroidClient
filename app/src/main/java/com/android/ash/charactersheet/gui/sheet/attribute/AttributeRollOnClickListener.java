package com.android.ash.charactersheet.gui.sheet.attribute;

import android.content.res.Resources;

import com.android.ash.charactersheet.gui.widget.RollOnClickListener;
import com.android.ash.charactersheet.gui.widget.dierollview.DieRollView;
import com.d20charactersheet.framework.boc.model.Attribute;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.DieRoll;
import com.d20charactersheet.framework.boc.service.DisplayService;
import com.d20charactersheet.framework.boc.service.RuleService;

/**
 * Listener for attribute rolls.
 */
public class AttributeRollOnClickListener extends RollOnClickListener {

    private final Attribute attribute;

    /**
     * Instanciates AttributeRollOnClickListener
     * 
     * @param character
     *            The character to roll for.
     * @param attribute
     *            The attribute to roll for.
     * @param displayService
     *            The service to display data.
     * @param ruleService
     *            The service to execute the attribute roll.
     * @param dieRollView
     *            The view to display the result.
     */
    public AttributeRollOnClickListener(final Character character, final Attribute attribute,
            final DisplayService displayService, final RuleService ruleService, final DieRollView dieRollView) {
        super(character, displayService, ruleService, dieRollView);
        this.attribute = attribute;
    }

    @Override
    protected String getTitle(final Resources resources) {
        return displayService.getDisplayAttribute(attribute);
    }

    @Override
    protected DieRoll getDieRoll() {
        return ruleService.rollAttribute(character, attribute);
    }

}
