package com.android.ash.charactersheet.dac.dao.sqlite;

import android.test.AndroidTestCase;

public class WrapperSQLiteSpelllistDaoTest extends AndroidTestCase {

    private SQLiteSpelllistDaoTest spelllistDaoSQLiteTest;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        spelllistDaoSQLiteTest = new SQLiteSpelllistDaoTest(getContext());
        spelllistDaoSQLiteTest.setUp();
    }

    public void testGetAllSpells() {
        spelllistDaoSQLiteTest.testGetAllSpells();
    }

    public void testGetAllSpelllists() {
        spelllistDaoSQLiteTest.testGetAllSpelllists();
    }

    public void testCreateAndDeleteSpell() {
        spelllistDaoSQLiteTest.testCreateAndDeleteSpell();
    }

    public void testGetAllKnownSpellsTables() throws Exception {
        spelllistDaoSQLiteTest.testGetAllKnownSpellsTables();
    }

    public void testGetAllSpellsPerDayTables() throws Exception {
        spelllistDaoSQLiteTest.testGetAllSpellsPerDayTables();
    }

}
