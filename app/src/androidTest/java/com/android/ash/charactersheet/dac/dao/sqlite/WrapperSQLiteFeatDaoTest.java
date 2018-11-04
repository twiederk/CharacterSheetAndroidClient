package com.android.ash.charactersheet.dac.dao.sqlite;

import android.test.AndroidTestCase;

public class WrapperSQLiteFeatDaoTest extends AndroidTestCase {

    private SQLiteFeatDaoTest sqliteFeatDaoTest;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        sqliteFeatDaoTest = new SQLiteFeatDaoTest(getContext());
        sqliteFeatDaoTest.setUp();
    }

    public void testGetAllFeats() throws Exception {
        sqliteFeatDaoTest.testGetAllFeats();
    }

    public void testGetFeatsOfCharacter() throws Exception {
        sqliteFeatDaoTest.testGetFeatsOfCharacter();
    }

}
