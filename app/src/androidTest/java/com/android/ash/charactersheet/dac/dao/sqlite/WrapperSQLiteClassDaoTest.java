package com.android.ash.charactersheet.dac.dao.sqlite;

import android.test.AndroidTestCase;

public class WrapperSQLiteClassDaoTest extends AndroidTestCase {

    private SQLiteClassDaoTest classDaoSQLiteTest;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        classDaoSQLiteTest = new SQLiteClassDaoTest(getContext());
        classDaoSQLiteTest.setUp();
    }

    public void testBaseClasses() throws Exception {
        classDaoSQLiteTest.testBaseClasses();
    }

    public void testSkillsOfCharacterClasses() throws Exception {
        classDaoSQLiteTest.testSkillsOfCharacterClasses();
    }

    public void testAbilitiesOfCharacterClasses() throws Exception {
        classDaoSQLiteTest.testAbilitiesOfCharacterClasses();
    }
}
