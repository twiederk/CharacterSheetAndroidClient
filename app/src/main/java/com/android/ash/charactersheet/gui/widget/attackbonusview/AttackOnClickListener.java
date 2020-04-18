package com.android.ash.charactersheet.gui.widget.attackbonusview;

import android.content.res.Resources;
import android.graphics.Color;
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
 * Listener for attack rolls.
 */
public class AttackOnClickListener {

    private final Lazy<FirebaseAnalytics> firebaseAnalytics = inject(FirebaseAnalytics.class);

    private final RuleService ruleService;
    private final DieRollView dieRollView;

    /**
     * Instantiates AttackOnClickListener.
     *
     * @param ruleService The service to execute the attack roll.
     * @param dieRollView The view to display the result.
     */
    public AttackOnClickListener(final RuleService ruleService, final DieRollView dieRollView) {
        this.ruleService = ruleService;
        this.dieRollView = dieRollView;
    }

    /**
     * Executes attack roll and displays result. Standard attacks are displayed with a blue die. Critical hits are
     * displayed with a red die. The result of the critical confirmation roll is displayed in the subtitle. Fumble is
     * displayed with a white die.
     *
     * @param view         The parent view.
     * @param attackBonus  The bonus of the attack.
     * @param weaponAttack The weapon attack to roll the attack for.
     */
    public void onClick(final View view, final int attackBonus, final WeaponAttack weaponAttack) {
        final Resources resources = view.getResources();

        final DieRoll attackRoll = ruleService.rollAttack(attackBonus);
        final String title = getTitle(resources);
        int color = Color.BLUE;
        String subtitle = "";

        if (ruleService.isCritical(attackRoll, weaponAttack.getWeapon().getCritical())) {
            final DieRoll confirmRoll = ruleService.rollAttack(attackBonus);
            Logger.debug("confirmRoll: " + confirmRoll);
            subtitle = getCriticalSubtitle(confirmRoll, resources);
            color = Color.RED;
        } else if (ruleService.isFumble(attackRoll)) {
            subtitle = getFumbleSubtitle(resources);
            color = Color.WHITE;
        }

        final DieRollViewController controller = new DefaultDieRollViewController(title, attackRoll, color, subtitle);
        dieRollView.setController(controller);
        dieRollView.setVisibility(View.VISIBLE);
        logEventDieRoll(title);
        Logger.debug("attackRoll: " + attackRoll);
    }

    private void logEventDieRoll(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(FBAnalytics.Param.DIE_ROLL_NAME, title);
        firebaseAnalytics.getValue().logEvent(FBAnalytics.Event.DIE_ROLL, bundle);
    }


    private String getTitle(final Resources resources) {
        return resources.getString(R.string.weaponattack_list_attack_roll);
    }

    private String getCriticalSubtitle(final DieRoll confirmRoll, final Resources resources) {
        return resources.getString(R.string.weaponattack_list_critical_subtitle) +
                " " +
                confirmRoll.getResult();
    }

    private String getFumbleSubtitle(final Resources resources) {
        return resources.getString(R.string.weaponattack_list_fumble_subtitle);
    }

}
