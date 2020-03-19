package com.android.ash.charactersheet.gui.sheet.attack;

import android.view.View;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.Logger;
import com.android.ash.charactersheet.gui.widget.dierollview.DefaultDieRollViewController;
import com.android.ash.charactersheet.gui.widget.dierollview.DieRollView;
import com.android.ash.charactersheet.gui.widget.dierollview.DieRollViewController;
import com.d20charactersheet.framework.boc.model.DieRoll;
import com.d20charactersheet.framework.boc.model.WeaponAttack;
import com.d20charactersheet.framework.boc.service.RuleService;

/**
 * Listener for critical damage rolls.
 */
public class CriticalOnClickListener {

    private final RuleService ruleService;
    private final DieRollView dieRollView;

    /**
     * Instantiates CriticalOnClickListener
     * 
     * @param ruleService
     *            The service to execute the critical damage roll.
     * @param dieRollView
     *            The view to display the result.
     */
    public CriticalOnClickListener(final RuleService ruleService, final DieRollView dieRollView) {
        this.ruleService = ruleService;
        this.dieRollView = dieRollView;
    }

    /**
     * Executes critical damage roll. Displays tilte of critical damage roll and displays result.
     * 
     * @param view
     *            The parent view.
     * @param weaponAttack
     *            The weapon attack to roll critical damage for.
     */
    public void onClick(final View view, final WeaponAttack weaponAttack) {
        final String title = view.getResources().getString(R.string.weaponattack_list_critical_roll);
        final DieRoll criticalRoll = ruleService.rollCritical(weaponAttack.getDamage(), weaponAttack.getDamageBonus(),
                weaponAttack.getCritical());
        final DieRollViewController controller = new DefaultDieRollViewController(title, criticalRoll);
        dieRollView.setController(controller);
        dieRollView.setVisibility(View.VISIBLE);
        Logger.debug("criticalRoll: " + criticalRoll);
    }

}
