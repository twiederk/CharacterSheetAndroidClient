package com.android.ash.charactersheet.gui.sheet.combat;

import android.content.res.Resources;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.widget.RollOnClickListener;
import com.android.ash.charactersheet.gui.widget.dierollview.DieRollView;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.DieRoll;
import com.d20charactersheet.framework.boc.service.DisplayService;
import com.d20charactersheet.framework.boc.service.RuleService;

/**
 * Listener for combat maneuver defence rolls.
 */
public class CombatManeuverDefenceRollOnClickListener extends RollOnClickListener {

    /**
     * Instanciates CombatManeuverDefenceRollOnClickListener
     * 
     * @param character
     *            The character to roll for.
     * @param displayService
     *            The service to display data.
     * @param ruleService
     *            The service to execute the combat maneuver defence roll.
     * @param dieRollView
     *            The view to display the result.
     */
    public CombatManeuverDefenceRollOnClickListener(final Character character, final DisplayService displayService,
            final RuleService ruleService, final DieRollView dieRollView) {
        super(character, displayService, ruleService, dieRollView);
    }

    @Override
    protected String getTitle(final Resources resources) {
        return resources.getString(R.string.page_sheet_roll_cmd_title);
    }

    @Override
    protected DieRoll getDieRoll() {
        return ruleService.rollCombatManeuverDefence(character);
    }

}
