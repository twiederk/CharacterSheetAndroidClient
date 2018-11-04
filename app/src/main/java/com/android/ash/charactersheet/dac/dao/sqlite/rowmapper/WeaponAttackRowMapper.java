package com.android.ash.charactersheet.dac.dao.sqlite.rowmapper;

import java.util.List;

import android.database.Cursor;
import android.database.SQLException;

import com.d20charactersheet.framework.boc.model.AttackWield;
import com.d20charactersheet.framework.boc.model.Weapon;
import com.d20charactersheet.framework.boc.model.WeaponAttack;

/**
 * Maps data row to new WeaponAttack instance.
 */
public class WeaponAttackRowMapper extends BaseRowMapper {

    private final List<Weapon> allWeapons;

    /**
     * Creates WeaponAttackRowMapper with all weapon.
     * 
     * @param allWeapons
     *            All weapons.
     */
    public WeaponAttackRowMapper(final List<Weapon> allWeapons) {
        super();
        this.allWeapons = allWeapons;
    }

    @Override
    public Object mapRow(final Cursor cursor) throws SQLException {
        // id, name, description, attack_wield_id, weapon_id, ammunition
        final WeaponAttack weaponAttack = new WeaponAttack();
        weaponAttack.setId(cursor.getInt(0));
        weaponAttack.setName(cursor.getString(1));
        weaponAttack.setDescription(cursor.getString(2));
        weaponAttack.setAttackWield((AttackWield) getEnum(cursor.getInt(3), AttackWield.values()));
        weaponAttack.setWeapon((Weapon) getItem(cursor.getInt(4), allWeapons));
        weaponAttack.setAmmunition(cursor.getInt(5));
        weaponAttack.setAttackBonusModifier(cursor.getInt(6));
        weaponAttack.setDamageBonusModifier(cursor.getInt(7));
        return weaponAttack;
    }

}
