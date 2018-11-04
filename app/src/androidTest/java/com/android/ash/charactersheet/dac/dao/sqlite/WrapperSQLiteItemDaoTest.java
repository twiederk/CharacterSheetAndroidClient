package com.android.ash.charactersheet.dac.dao.sqlite;

import android.test.AndroidTestCase;

public class WrapperSQLiteItemDaoTest extends AndroidTestCase {

    private SQLiteItemDaoTest sqliteItemDaoTest;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        sqliteItemDaoTest = new SQLiteItemDaoTest(getContext());
        sqliteItemDaoTest.setUp();
    }

    public void testGetAllWeapons() throws Exception {
        sqliteItemDaoTest.testGetAllWeapons();
    }

    public void testGetAllArmor() throws Exception {
        sqliteItemDaoTest.testGetAllArmor();
    }

    public void testGetAllGoods() throws Exception {
        sqliteItemDaoTest.testGetAllGoods();
    }

    public void testGetWeaponsOfBelvador() throws Exception {
        sqliteItemDaoTest.testGetWeaponsOfBelvador();
    }

    public void testGetArmorOfBelvador() throws Exception {
        sqliteItemDaoTest.testGetArmorOfBelvador();
    }

    public void testGetGoodsOfBelvador() throws Exception {
        sqliteItemDaoTest.testGetGoodsOfBelvador();
    }

    public void testCreateWeapon() throws Exception {
        sqliteItemDaoTest.testCreateWeapon();
    }

    public void testUpdateWeapon() throws Exception {
        sqliteItemDaoTest.testUpdateWeapon();
    }

    public void testCreateArmor() throws Exception {
        sqliteItemDaoTest.testCreateArmor();
    }

    public void testUpdateArmor() throws Exception {
        sqliteItemDaoTest.testUpdateArmor();
    }

    public void testCreateGood() throws Exception {
        sqliteItemDaoTest.testCreateGood();
    }

    public void testUpdateGood() throws Exception {
        sqliteItemDaoTest.testUpdateGood();
    }

    public void testWeapon() throws Exception {
        sqliteItemDaoTest.testWeapon();
    }
}
