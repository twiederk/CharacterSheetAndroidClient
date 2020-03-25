package com.android.ash.charactersheet.gui.widget.attackbonusview;

import com.d20charactersheet.framework.boc.model.WeaponAttack;

import java.util.List;

/**
 * Default implementation of AttackBonusViewContoller.
 */
public class DefaultAttackBonusViewController implements AttackBonusViewController {

    private final WeaponAttack weaponAttack;

    /**
     * Default implementation uses attack bonuses of weapon attack.
     *
     * @param weaponAttack The weapon attack
     */
    public DefaultAttackBonusViewController(final WeaponAttack weaponAttack) {
        this.weaponAttack = weaponAttack;
    }

    @Override
    public List<Integer> getAttackBonuses() {
        return weaponAttack.getAttackBonuses();
    }

    @Override
    public WeaponAttack getWeaponAttack() {
        return weaponAttack;
    }

}
