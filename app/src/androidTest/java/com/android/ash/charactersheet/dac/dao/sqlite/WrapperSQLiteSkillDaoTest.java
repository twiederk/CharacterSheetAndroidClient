package com.android.ash.charactersheet.dac.dao.sqlite;

import android.test.AndroidTestCase;

public class WrapperSQLiteSkillDaoTest extends AndroidTestCase {

    private SQLiteSkillDaoTest skillDaoSQLiteTest;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        skillDaoSQLiteTest = new SQLiteSkillDaoTest(getContext());
        skillDaoSQLiteTest.setUp();
    }

    public void testGetAllSkills() throws Exception {
        skillDaoSQLiteTest.testGetAllSkills();
    }

    public void testUpdateSkill() throws Exception {
        skillDaoSQLiteTest.testUpdateSkill();
    }

    public void testCreateSkill() throws Exception {
        skillDaoSQLiteTest.testCreateSkill();
    }

    public void testDeleteSkill() throws Exception {
        skillDaoSQLiteTest.testDeleteSkill();
    }

    public void testGetCharacterSkills() throws Exception {
        skillDaoSQLiteTest.testGetCharacterSkills();
    }

}
