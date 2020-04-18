package com.android.ash.charactersheet.gui.sheet.attack;

import android.os.Bundle;
import android.view.View;

import com.android.ash.charactersheet.FBAnalytics;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.Logger;
import com.android.ash.charactersheet.gui.widget.dierollview.DefaultDieRollViewController;
import com.android.ash.charactersheet.gui.widget.dierollview.DieRollView;
import com.android.ash.charactersheet.gui.widget.dierollview.DieRollViewController;
import com.d20charactersheet.framework.boc.model.DieRoll;
import com.d20charactersheet.framework.boc.model.WeaponAttack;
import com.d20charactersheet.framework.boc.service.RuleService;
import com.google.firebase.analytics.FirebaseAnalytics;

import kotlin.Lazy;

import static org.koin.java.KoinJavaComponent.inject;

/**
 * Listener for damage rolls.
 */
public class DamageOnClickListener {

    private final Lazy<FirebaseAnalytics> firebaseAnalytics = inject(FirebaseAnalytics.class);

    private final RuleService ruleService;
    private final DieRollView dieRollView;

    /**
     * Instantiates DamageOnClickListener.
     * 
     * @param ruleService
     *            The service to execute the damage roll.
     * @param dieRollView
     *            The view to display the result.
     */
    public DamageOnClickListener(final RuleService ruleService, final DieRollView dieRollView) {
        this.ruleService = ruleService;
        this.dieRollView = dieRollView;
    }

    /**
     * Executes the damage roll and displays the result.
     * 
     * @param view
     *            The parent view.
     * @param weaponAttack
     *            The weapon attack to roll damage for.
     */
    public void onClick(final View view, final WeaponAttack weaponAttack) {
        final String title = view.getResources().getString(R.string.weaponattack_list_damage_roll);
        final DieRoll damageRoll = ruleService.rollDamage(weaponAttack.getDamage(), weaponAttack.getDamageBonus());
        final DieRollViewController controller = new DefaultDieRollViewController(title, damageRoll);
        dieRollView.setController(controller);
        dieRollView.setVisibility(View.VISIBLE);
        logEventDieRoll(title);
        Logger.debug("damageRoll: " + damageRoll);
    }

    private void logEventDieRoll(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(FBAnalytics.Param.DIE_ROLL_NAME, title);
        firebaseAnalytics.getValue().logEvent(FBAnalytics.Event.DIE_ROLL, bundle);
    }

}
