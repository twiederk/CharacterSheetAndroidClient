package com.android.ash.charactersheet.gui.admin.item.weapon;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.CombatType;
import com.d20charactersheet.framework.boc.model.Critical;
import com.d20charactersheet.framework.boc.model.Damage;
import com.d20charactersheet.framework.boc.model.Die;
import com.d20charactersheet.framework.boc.model.QualityType;
import com.d20charactersheet.framework.boc.model.Weapon;
import com.d20charactersheet.framework.boc.model.WeaponCategory;
import com.d20charactersheet.framework.boc.model.WeaponEncumbrance;
import com.d20charactersheet.framework.boc.model.WeaponType;

/**
 * Derived from the WeaponAdministrationActivity it allows to create a new weapon.
 */
public class WeaponAdministrationCreateActivity extends WeaponAdministrationActivity {

    @Override
    protected Weapon createForm() {
        final Weapon weapon = new Weapon();
        weapon.setName("");
        weapon.setWeaponType(WeaponType.SIMPLE);
        weapon.setWeight(1);
        weapon.setCost(0);
        weapon.setQualityType(QualityType.NORMAL);
        weapon.setEnhancementBonus(0);
        weapon.setDamage(new Damage(1, Die.D6));
        weapon.setCritical(createCritical());
        weapon.setDescription("");
        weapon.setCombatType(CombatType.MELEE_WEAPON);
        weapon.setWeaponEncumbrance(WeaponEncumbrance.LIGHT_HANDED);
        weapon.setWeaponCategory(WeaponCategory.NORMAL_WEAPON);
        weapon.setWeaponFamily(itemService.getAllWeaponFamilies().get(0));
        weapon.setRangeIncrement(0);
        return weapon;
    }

    private Critical createCritical() {
        final Critical critical = new Critical();
        critical.setHit(20);
        critical.setMultiplier(2);
        return critical;
    }

    @Override
    protected String getHeading() {
        return getResources().getString(R.string.weapon_administration_create_title);
    }

    @Override
    protected void saveForm() {
        if (!form.getName().trim().isEmpty()) {
            itemService.createWeapon((Weapon) form);
            gameSystem.clear();
        }
    }
}
