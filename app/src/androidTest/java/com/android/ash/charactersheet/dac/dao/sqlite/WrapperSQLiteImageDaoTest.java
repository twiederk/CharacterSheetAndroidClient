package com.android.ash.charactersheet.dac.dao.sqlite;

import android.test.AndroidTestCase;

public class WrapperSQLiteImageDaoTest extends AndroidTestCase {

    private SQLiteImageDaoTest imageDaoSQLiteTest;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        imageDaoSQLiteTest = new SQLiteImageDaoTest(getContext());
        imageDaoSQLiteTest.setUp();
    }

    public void testGetImage() throws Exception {
        imageDaoSQLiteTest.testGetImage();
    }

    public void testCreateImage() throws Exception {
        imageDaoSQLiteTest.testCreateImage();
    }

    public void testDeleteImage() throws Exception {
        imageDaoSQLiteTest.testDeleteImage();
    }

}
