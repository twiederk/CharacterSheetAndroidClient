package com.android.ash.charactersheet.gui.widget.ammunitionview;

import java.util.Observable;

import com.d20charactersheet.framework.boc.model.WeaponAttack;
import com.d20charactersheet.framework.boc.model.WeaponCategory;

/**
 * The controller of the AmmunitionView. It increases and decreases the number of ammunition for projectile weapons.
 */
public class AmmunitionViewController extends Observable {

    private final WeaponAttack weaponAttack;

    /**
     * Creates AmmunitionViewController instance for the given weapon attack.
     * 
     * @param weaponAttack
     *            The given weapon attack.
     */
    public AmmunitionViewController(final WeaponAttack weaponAttack) {
        super();
        this.weaponAttack = weaponAttack;
    }

    /**
     * Increase ammunition by one for projectile weapons.
     */
    public void increase() {
        if (isProjectileWeapon()) {
            weaponAttack.setAmmunition(weaponAttack.getAmmunition() + 1);
            fireEvent();
        }
    }

    /**
     * Decrease ammunition by one for projectile weapons.
     */
    public void decrease() {
        if (isProjectileWeapon() && weaponAttack.getAmmunition() > 0) {
            weaponAttack.setAmmunition(weaponAttack.getAmmunition() - 1);
            fireEvent();
        }
    }

    /**
     * Returns the weapon attack.
     * 
     * @return The weapon attack.
     */
    public WeaponAttack getWeaponAttack() {
        return weaponAttack;
    }

    /**
     * Returns the current number of ammunition of projectile weapon. A dash(-) is return for weapons of other
     * categories.
     * 
     * @return The current number of ammunition of projectile weapon
     */
    public String getAmmunition() {
        if (isProjectileWeapon()) {
            return Integer.toString(weaponAttack.getAmmunition());
        }
        return "-";
    }

    private boolean isProjectileWeapon() {
        if (WeaponCategory.PROJECTILE_WEAPON.equals(weaponAttack.getWeapon().getWeaponCategory())) {
            return true;
        }
        return false;
    }

    private void fireEvent() {
        setChanged();
        notifyObservers();
    }
}
