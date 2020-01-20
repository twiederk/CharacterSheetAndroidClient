package com.android.ash.charactersheet.dac.dao.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.android.ash.charactersheet.dac.dao.sqlite.rowmapper.WeaponFamilyRowMapper;
import com.android.ash.charactersheet.dac.dao.sqlite.rowmapper.WeaponRowMapper;
import com.android.ash.charactersheet.gui.util.Logger;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.Critical;
import com.d20charactersheet.framework.boc.model.Damage;
import com.d20charactersheet.framework.boc.model.Item;
import com.d20charactersheet.framework.boc.model.ItemGroup;
import com.d20charactersheet.framework.boc.model.Weapon;
import com.d20charactersheet.framework.boc.model.WeaponFamily;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class of the ItemDao. Handles all database activities concerning weapons. Its like a WeaponDao.
 */
class WeaponHelper extends ItemHelper {

    private static final String SQL_GET_ALL_WEAPONS = SELECT
            + "id, name, description, cost, weight, number_of_dice, die_id, "
            + "enhancement_bonus, critical_hit, critical_mod, weapon_type_id, quality_type_id, "
            + "combat_type_id, weapon_encumbrance_id, weapon_category_id, weapon_family_id, range_increment FROM "
            + TABLE_WEAPON;

    private static final String SQL_GET_CHARACTER_WEAPONS = SELECT + "id, weapon_id, number " //
            + FROM + TABLE_CHARAKTER_WEAPON + " " //
            + WHERE + "charakter_id = ?";

    private static final String SQL_GET_WEAPON_ID = SELECT + "id FROM " + TABLE_WEAPON + " WHERE rowid = ?";

    private static final String SQL_GET_ALL_WEAPON_FAMILIES = SELECT + "id, name FROM " + TABLE_WEAPON_FAMILY;

    private final WeaponFamilyRowMapper weaponFamilyRowMapper;

    /**
     * Creates WeaponHelper with given database and helper.
     * 
     * @param db
     *            The database to connect to.
     * @param helper
     *            The helper to handle basic item tasks.
     */
    public WeaponHelper(final SQLiteDatabase db, final SQLiteItemDaoHelper helper) {
        super(db, helper);
        weaponFamilyRowMapper = new WeaponFamilyRowMapper();
    }

    /**
     * Returns all weapons.
     * 
     * @param allWeaponFamilies
     *            List of all weapon families.
     * 
     * @return All weaopns.
     */
    public List<Weapon> getAllWeapons(final List<WeaponFamily> allWeaponFamilies) {
        final List<Item> allItems = getAllItems(SQL_GET_ALL_WEAPONS, new WeaponRowMapper(allWeaponFamilies));
        final List<Weapon> allWeapons = new ArrayList<>();
        for (final Item item : allItems) {
            allWeapons.add((Weapon) item);
        }
        return allWeapons;
    }

    /**
     * Returns all weapons of the character.
     * 
     * @param character
     *            The character.
     * @param allWeapons
     *            All available weapons.
     * @return All weapons of the character.
     */
    public List<ItemGroup> getWeapons(final Character character, final List<Weapon> allWeapons) {
        return getItemGroups(SQL_GET_CHARACTER_WEAPONS, character, allWeapons);
    }

    /**
     * Updates the list of weapons owned by the character.
     * 
     * @param character
     *            The character to update its list.
     * @param weapons
     *            The new list of weapons of the character.
     * @return The updated list of weapons of the character.
     */
    public List<ItemGroup> updateWeapons(final Character character, final List<ItemGroup> weapons) {
        return helper.updateItems(character.getEquipment().getWeapons(), weapons, TABLE_CHARAKTER_WEAPON,
                character.getId());
    }

    /**
     * Creates a new weapon.
     * 
     * @param weapon
     *            The weapon to create.
     * @return The new weapon with its created id.
     */
    public Weapon createWeapon(final Weapon weapon) {
        insertWeaponTable(weapon);
        return weapon;
    }

    private void insertWeaponTable(final Weapon weapon) {
        final ContentValues contentValues = getContentValues(weapon);
        contentValues.putNull(COLUMN_ID);
        synchronized (DBHelper.DB_LOCK) {
            final long rowId = db.insertOrThrow(TABLE_WEAPON, null, contentValues);
            if (rowId == -1) {
                throw new SQLException("Can't insert weapon in weapon table");
            }
            weapon.setId(getId(SQL_GET_WEAPON_ID, rowId));
        }
    }

    private ContentValues getContentValues(final Weapon weapon) {
        final Damage damage = weapon.getDamage();
        final Critical critical = weapon.getCritical();
        final ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, weapon.getName());
        contentValues.put(COLUMN_DESCRIPTION, weapon.getDescription());
        contentValues.put(COLUMN_COST, weapon.getCost());
        contentValues.put(COLUMN_WEIGHT, weapon.getWeight());
        contentValues.put(COLUMN_NUMBER_OF_DICE, damage.getNumberOfDice());
        contentValues.put(COLUMN_DIE_ID, damage.getDie().ordinal());
        contentValues.put(COLUMN_ENHANCEMENT_BONUS, weapon.getEnhancementBonus());
        contentValues.put(COLUMN_CRITICAL_HIT, critical.getHit());
        contentValues.put(COLUMN_CRITICAL_MOD, critical.getMultiplier());
        contentValues.put(COLUMN_WEAPON_TYPE_ID, weapon.getWeaponType().ordinal());
        contentValues.put(COLUMN_QUALITY_TYPE_ID, weapon.getQualityType().ordinal());
        contentValues.put(COLUMN_COMBAT_TYPE_ID, weapon.getCombatType().ordinal());
        contentValues.put(COLUMN_WEAPON_ENCUMBRANCE_ID, weapon.getWeaponEncumbrance().ordinal());
        contentValues.put(COLUMN_WEAPON_CATEGORY_ID, weapon.getWeaponCategory().ordinal());
        contentValues.put(COLUMN_WEAPON_FAMILY_ID, weapon.getWeaponFamily().getId());
        contentValues.put(COLUMN_RANGE_INCREMENT, weapon.getRangeIncrement());
        return contentValues;
    }

    /**
     * Updates the weapon.
     * 
     * @param weapon
     *            The weapon to update.
     */
    public void updateWeapon(final Weapon weapon) {
        deleteWeapon(weapon);
        updateWeaponTable(weapon);
    }

    private void updateWeaponTable(final Weapon weapon) {
        final ContentValues contentValues = getContentValues(weapon);
        contentValues.put(COLUMN_ID, weapon.getId());
        synchronized (DBHelper.DB_LOCK) {
            final long rowId = db.insertOrThrow(TABLE_WEAPON, null, contentValues);
            if (rowId == -1) {
                throw new SQLException("Can't insert weapon in weapon table");
            }
        }
    }

    /**
     * Deletes the weapon.
     * 
     * @param weapon
     *            The weapon to update.
     */
    public void deleteWeapon(final Weapon weapon) {
        final String[] weaponId = new String[] { Integer.toString(weapon.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            db.delete(TABLE_WEAPON, COLUMN_ID + " = ?", weaponId);
        }
    }

    /**
     * Returns all weapon families from the database.
     * 
     * @return All weapon families.
     */
    public List<WeaponFamily> getAllWeaponFamilies() {
        final List<WeaponFamily> allWeaponFamilies = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(SQL_GET_ALL_WEAPON_FAMILIES, new String[0]);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                final WeaponFamily weaponFamily = (WeaponFamily) weaponFamilyRowMapper.mapRow(cursor);
                allWeaponFamilies.add(weaponFamily);
            }
        } catch (final SQLException sqlException) {
            Logger.error("Can't get all weapon families", sqlException);
        } finally {
            close(cursor);
        }
        return allWeaponFamilies;
    }

}
