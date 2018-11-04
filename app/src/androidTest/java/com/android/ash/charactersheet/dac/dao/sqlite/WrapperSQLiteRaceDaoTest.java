package com.android.ash.charactersheet.dac.dao.sqlite;

import android.test.AndroidTestCase;

public class WrapperSQLiteRaceDaoTest extends AndroidTestCase {

    private SQLiteRaceDaoTest raceDaoSQLiteTest;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        raceDaoSQLiteTest = new SQLiteRaceDaoTest(getContext());
        raceDaoSQLiteTest.setUp();
    }

    public void testGetAllRaces() throws Exception {
        raceDaoSQLiteTest.testGetAllRaces();
    }

    public void testRaceHuman() throws Exception {
        raceDaoSQLiteTest.testRaceHuman();
    }

    public void testCreateRace() throws Exception {
        raceDaoSQLiteTest.testCreateRace();
    }

    public void testUpdateRace() throws Exception {
        raceDaoSQLiteTest.testUpdateRace();
    }
}
