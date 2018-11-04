package com.android.ash.charactersheet.dac.dao.sqlite;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;

import com.d20charactersheet.framework.boc.model.Armor;
import com.d20charactersheet.framework.boc.model.Good;
import com.d20charactersheet.framework.boc.model.Weapon;
import com.d20charactersheet.framework.boc.model.WeaponFamily;
import com.d20charactersheet.framework.dac.dao.ItemDao;

/**
 * Provides access to SQLite database on Android device storing items.
 */
public class SQLiteItemDao extends BaseSQLiteDao implements ItemDao {

    private final WeaponHelper weaponHelper;
    private final ArmorHelper armorHelper;
    private final GoodHelper goodHelper;

    /**
     * Creates DAO to acces SQLite database on Android device with given helper.
     * 
     * @param db
     *            The database to access.
     */
    public SQLiteItemDao(final SQLiteDatabase db) {
        super(db);

        final SQLiteItemDaoHelper helper = new SQLiteItemDaoHelper(db);

        weaponHelper = new WeaponHelper(db, helper);
        armorHelper = new ArmorHelper(db, helper);
        goodHelper = new GoodHelper(db, helper);

    }

    @Override
    public List<Weapon> getAllWeapons(final List<WeaponFamily> allWeaponFamilies) {
        return weaponHelper.getAllWeapons(allWeaponFamilies);
    }

    @Override
    public List<Armor> getAllArmor() {
        return armorHelper.getAllArmor();
    }

    @Override
    public List<Good> getAllGoods() {
        return goodHelper.getAllGoods();
    }

    @Override
    public Weapon createWeapon(final Weapon weapon) {
        return weaponHelper.createWeapon(weapon);
    }

    @Override
    public void updateWeapon(final Weapon weapon) {
        weaponHelper.updateWeapon(weapon);
    }

    @Override
    public void deleteWeapon(final Weapon weapon) {
        weaponHelper.deleteWeapon(weapon);
    }

    @Override
    public Armor createArmor(final Armor armor) {
        return armorHelper.createArmor(armor);
    }

    @Override
    public void updateArmor(final Armor armor) {
        armorHelper.updateArmor(armor);
    }

    @Override
    public void deleteArmor(final Armor armor) {
        armorHelper.deleteArmor(armor);
    }

    @Override
    public Good createGood(final Good good) {
        return goodHelper.createGood(good);
    }

    @Override
    public void updateGood(final Good good) {
        goodHelper.updateGood(good);

    }

    @Override
    public void deleteGood(final Good good) {
        goodHelper.deleteGood(good);
    }

    @Override
    public List<WeaponFamily> getAllWeaponFamilies() {
        return weaponHelper.getAllWeaponFamilies();
    }

}
