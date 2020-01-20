package com.android.ash.charactersheet.gui.widget.attackbonusview;

import com.d20charactersheet.framework.boc.model.WeaponAttack;

import java.util.List;

/**
 * Controller of the AttackBonusView.
 */
interface AttackBonusViewController {

    /**
     * Returns all attack bonuses. For each a button must be displayed by the AttackBonusView.
     * 
     * @return All attack bonuses.
     */
    List<Integer> getAttackBonuses();

    /**
     * Returns the weapon attack.
     * 
     * @return The weapon attack.
     */
    WeaponAttack getWeaponAttack();

}
