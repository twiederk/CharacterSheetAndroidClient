package com.android.ash.charactersheet.gui.widget.attackbonusview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.ash.charactersheet.gui.widget.ammunitionview.AmmunitionViewController;

import java.util.List;

/**
 * View to display attack bonus. Each attack bonus is displayed on a separate button. Each click on the a attack bonus
 * send a message to the AttackOnClickListener and the AmmunitionViewController.
 */
public class AttackBonusView extends LinearLayout {

    private static final String EMPTY_SPACE = "  ";

    private AttackBonusViewController controller;
    private AttackOnClickListener attackOnClickListener;
    private AmmunitionViewController ammoController;

    /**
     * Creates an attack bonus view from XML layout.
     * 
     * @param context
     *            The context of view.
     * @param attributeSet
     *            The attributes from the XML layout.
     */
    public AttackBonusView(final Context context, final AttributeSet attributeSet) {
        super(context, attributeSet);
        this.setOrientation(HORIZONTAL);
    }

    /**
     * Sets the AttackBonusViewController and AmmunitionViewController.
     * 
     * @param controller
     *            The AttackBonusViewController.
     * @param ammoController
     *            The AmmunitionViewController.
     */
    public void setController(final AttackBonusViewController controller, final AmmunitionViewController ammoController) {
        this.controller = controller;
        setAttackBonuses(controller.getAttackBonuses());
        this.ammoController = ammoController;
    }

    private void setAttackBonuses(final List<Integer> attackBonuses) {
        this.removeAllViews();
        for (final int attackBonus : attackBonuses) {
            final Button button = createButton(attackBonus);
            addView(button);
        }
    }

    private Button createButton(final int attackBonus) {
        final LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.weight = 1.0f;

        final Button button = new Button(getContext());
        button.setLayoutParams(layoutParams);
        button.setText(getDisplay(attackBonus));
        button.setTextSize(22.0f);
        button.setPadding(4, 4, 4, 4);
        button.setOnClickListener(view -> {
            attackOnClickListener.onClick(view, attackBonus, controller.getWeaponAttack());
            ammoController.decrease();
        });
        return button;
    }

    private String getDisplay(final int attackBonus) {
        final StringBuilder output = new StringBuilder();
        String sign = "";
        if (attackBonus > 0) {
            sign = "+";
        }
        output.append(EMPTY_SPACE);
        output.append(sign);
        output.append(attackBonus);
        output.append(EMPTY_SPACE);
        return output.toString();
    }

    /**
     * Sets the AttackOnClickListener to get a message when an attack bonus button is clicked.
     * 
     * @param attackOnClickListener
     *            The AttackOnClickListener
     */
    public void setAttackOnClickListener(final AttackOnClickListener attackOnClickListener) {
        this.attackOnClickListener = attackOnClickListener;

    }

}
