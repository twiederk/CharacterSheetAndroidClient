package com.android.ash.charactersheet.dac.dao.sqlite;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.android.ash.charactersheet.dac.dao.sqlite.rowmapper.ArmorRowMapper;
import com.d20charactersheet.framework.boc.model.Armor;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.Item;
import com.d20charactersheet.framework.boc.model.ItemGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class of the ItemDao. Handles all database activities concerning armor. Its like an ArmorDao.
 */
class ArmorHelper extends ItemHelper {

    private static final String SQL_GET_ALL_ARMOR = SELECT
            + "id, name, description, cost, weight, armor_bonus, armor_penalty, armor_type_id, quality_type_id FROM "
            + TABLE_ARMOR;

    private static final String SQL_GET_CHARACTER_ARMOR = SELECT + "id, armor_id, number " //
            + FROM + TABLE_CHARAKTER_ARMOR + " " //
            + WHERE + "charakter_id = ?";

    private static final String SQL_GET_ARMOR_ID = SELECT + "id FROM " + TABLE_ARMOR + " WHERE rowid = ?";

    private final ArmorRowMapper armorRowMapper;

    /**
     * Creates an ArmorHelper with given database and helper instance.
     * 
     * @param db
     *            The database to connect to.
     * @param helper
     *            The helper with basic item tasks.
     */
    public ArmorHelper(final SQLiteDatabase db, final SQLiteItemDaoHelper helper) {
        super(db, helper);
        armorRowMapper = new ArmorRowMapper();
    }

    /**
     * Returns list of all armor.
     * 
     * @return List of all armor.
     */
    public List<Armor> getAllArmor() {
        final List<Item> allItems = getAllItems(SQL_GET_ALL_ARMOR, armorRowMapper);
        final List<Armor> allArmor = new ArrayList<>();
        for (final Item item : allItems) {
            allArmor.add((Armor) item);
        }
        return allArmor;
    }

    /**
     * Returns all armor of the character.
     * 
     * @param character
     *            The character to retrieve the armor of.
     * @param allArmor
     *            All available armor in the system.
     * @return The armor of the character.
     */
    public List<ItemGroup> getArmor(final Character character, final List<Armor> allArmor) {
        return getItemGroups(SQL_GET_CHARACTER_ARMOR, character, allArmor);
    }

    /**
     * Updates the list of armor of the character.
     * 
     * @param character
     *            The character to update its armor.
     * @param armor
     *            The armor of the character.
     * @return The updated list of the armor of the character.
     */
    public List<ItemGroup> updateArmor(final Character character, final List<ItemGroup> armor) {
        return helper.updateItems(character.getEquipment().getArmor(), armor, TABLE_CHARAKTER_ARMOR, character.getId());
    }

    /**
     * Create a new armor.
     * 
     * @param armor
     *            The armor to create.
     * @return The new created armor, including its id.
     */
    public Armor createArmor(final Armor armor) {
        insertArmorTable(armor);
        return armor;
    }

    private void insertArmorTable(final Armor armor) {
        final ContentValues contentValues = getContentValues(armor);
        contentValues.putNull(COLUMN_ID);
        synchronized (DBHelper.DB_LOCK) {
            final long rowId = db.insertOrThrow(TABLE_ARMOR, null, contentValues);
            if (rowId == -1) {
                throw new SQLException("Can't insert armor in armor table");
            }
            armor.setId(getId(SQL_GET_ARMOR_ID, rowId));
        }
    }

    private ContentValues getContentValues(final Armor armor) {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, armor.getName());
        contentValues.put(COLUMN_DESCRIPTION, armor.getDescription());
        contentValues.put(COLUMN_COST, armor.getCost());
        contentValues.put(COLUMN_WEIGHT, armor.getWeight());
        contentValues.put(COLUMN_ARMOR_BONUS, armor.getArmorBonus());
        contentValues.put(COLUMN_ARMOR_PENALTY, armor.getArmorCheckPenalty());
        contentValues.put(COLUMN_ARMOR_TYPE_ID, armor.getArmorType().ordinal());
        contentValues.put(COLUMN_QUALITY_TYPE_ID, armor.getQualityType().ordinal());
        return contentValues;
    }

    /**
     * Delete the armor.
     * 
     * @param armor
     *            The armor to delete.
     */
    public void deleteArmor(final Armor armor) {
        final String[] armorId = new String[] { Integer.toString(armor.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            db.delete(TABLE_ARMOR, COLUMN_ID + " = ?", armorId);
        }
    }

    /**
     * Update the armor.
     * 
     * @param armor
     *            The armor to update.
     */
    public void updateArmor(final Armor armor) {
        deleteArmor(armor);
        updateArmorTable(armor);
    }

    private void updateArmorTable(final Armor armor) {
        final ContentValues contentValues = getContentValues(armor);
        contentValues.put(COLUMN_ID, armor.getId());
        synchronized (DBHelper.DB_LOCK) {
            final long rowId = db.insertOrThrow(TABLE_ARMOR, null, contentValues);
            if (rowId == -1) {
                throw new SQLException("Can't insert armor in armor table");
            }
        }
    }

}
