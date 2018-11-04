package com.android.ash.charactersheet.gui.widget.attackbonusview;

import java.util.List;

import com.d20charactersheet.framework.boc.model.WeaponAttack;

/**
 * Controller of the AttackBonusView.
 */
public interface AttackBonusViewController {

    /**
     * Returns all attack bonuses. For each a button must be displayed by the AttackBonusView.
     * 
     * @return All attack bonuses.
     */
    public List<Integer> getAttackBonuses();

    /**
     * Returns the weapon attack.
     * 
     * @return The weapon attack.
     */
    public WeaponAttack getWeaponAttack();

}
