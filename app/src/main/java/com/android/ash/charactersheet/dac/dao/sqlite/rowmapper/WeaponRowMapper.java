package com.android.ash.charactersheet.dac.dao.sqlite.rowmapper;

import java.util.List;

import android.database.Cursor;
import android.database.SQLException;

import com.d20charactersheet.framework.boc.model.CombatType;
import com.d20charactersheet.framework.boc.model.Critical;
import com.d20charactersheet.framework.boc.model.Damage;
import com.d20charactersheet.framework.boc.model.Die;
import com.d20charactersheet.framework.boc.model.Weapon;
import com.d20charactersheet.framework.boc.model.WeaponCategory;
import com.d20charactersheet.framework.boc.model.WeaponEncumbrance;
import com.d20charactersheet.framework.boc.model.WeaponFamily;
import com.d20charactersheet.framework.boc.model.WeaponType;

/**
 * Maps a weapon from the database to an Weapon object.
 */
public class WeaponRowMapper extends BaseRowMapper {

    private final List<WeaponFamily> allWeaponFamilies;

    /**
     * Creates WeaponRowMapper with weapon families.
     * 
     * @param allWeaponFamilies
     *            All weapon families.
     */
    public WeaponRowMapper(final List<WeaponFamily> allWeaponFamilies) {
        super();
        this.allWeaponFamilies = allWeaponFamilies;
    }

    @Override
    public Object mapRow(final Cursor cursor) throws SQLException {
        // id, weapon_text.name, cost, weight, number_of_dice, die_id, bonus, critical_hit, critical_mod,
        // weapon_type_id, description
        final Weapon weapon = new Weapon();
        weapon.setId(cursor.getInt(0));
        weapon.setName(cursor.getString(1));
        weapon.setDescription(cursor.getString(2));
        weapon.setCost(cursor.getFloat(3));
        weapon.setWeight(cursor.getFloat(4));
        weapon.setDamage(createDamage(cursor));
        weapon.setEnhancementBonus(cursor.getInt(7));
        weapon.setCritical(createCritical(cursor));
        weapon.setWeaponType((WeaponType) getEnum(cursor.getInt(10), WeaponType.values()));
        weapon.setQualityType(getQualityType(cursor.getInt(11)));
        weapon.setCombatType((CombatType) getEnum(cursor.getInt(12), CombatType.values()));
        weapon.setWeaponEncumbrance((WeaponEncumbrance) getEnum(cursor.getInt(13), WeaponEncumbrance.values()));
        weapon.setWeaponCategory((WeaponCategory) getEnum(cursor.getInt(14), WeaponCategory.values()));
        weapon.setWeaponFamily(getWeaponFamily(cursor.getInt(15)));
        weapon.setRangeIncrement(cursor.getInt(16));
        return weapon;
    }

    private WeaponFamily getWeaponFamily(final int weaponFamilyId) {
        for (final WeaponFamily weaponFamily : allWeaponFamilies) {
            if (weaponFamily.getId() == weaponFamilyId) {
                return weaponFamily;
            }
        }
        throw new IllegalArgumentException("Can't get weapon family with id: " + weaponFamilyId);
    }

    private Damage createDamage(final Cursor cursor) {
        final Damage damage = new Damage();
        damage.setNumberOfDice(cursor.getInt(5));
        damage.setDie((Die) getEnum(cursor.getInt(6), Die.values()));
        return damage;
    }

    private Critical createCritical(final Cursor cursor) {
        final Critical critical = new Critical();
        critical.setHit(cursor.getInt(8));
        critical.setMultiplier(cursor.getInt(9));
        return critical;
    }
}
