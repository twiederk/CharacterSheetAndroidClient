package com.android.ash.charactersheet.dac;

import junit.framework.TestSuite;

import com.android.ash.charactersheet.dac.dao.dummy.WrapperImageDaoDummyTest;
import com.android.ash.charactersheet.dac.dao.sqlite.WrapperSQLiteAbilityDaoTest;
import com.android.ash.charactersheet.dac.dao.sqlite.WrapperSQLiteCharacterDaoTest;
import com.android.ash.charactersheet.dac.dao.sqlite.WrapperSQLiteClassDaoTest;
import com.android.ash.charactersheet.dac.dao.sqlite.WrapperSQLiteFeatDaoTest;
import com.android.ash.charactersheet.dac.dao.sqlite.WrapperSQLiteImageDaoTest;
import com.android.ash.charactersheet.dac.dao.sqlite.WrapperSQLiteItemDaoTest;
import com.android.ash.charactersheet.dac.dao.sqlite.WrapperSQLiteRaceDaoTest;
import com.android.ash.charactersheet.dac.dao.sqlite.WrapperSQLiteSkillDaoTest;
import com.android.ash.charactersheet.dac.dao.sqlite.WrapperSQLiteSpelllistDaoTest;
import com.android.ash.charactersheet.dac.dao.sqlite.rowmapper.BaseRowMapperTest;

public class AllTestsPersistence extends TestSuite {

    public static TestSuite suite() {
        final TestSuite suite = new TestSuite();
        suite.setName("AllTestsPersistence");
        suite.addTestSuite(BaseRowMapperTest.class);
        suite.addTestSuite(WrapperImageDaoDummyTest.class);
        suite.addTestSuite(WrapperSQLiteCharacterDaoTest.class);
        suite.addTestSuite(WrapperSQLiteImageDaoTest.class);
        suite.addTestSuite(WrapperSQLiteSkillDaoTest.class);
        suite.addTestSuite(WrapperSQLiteFeatDaoTest.class);
        suite.addTestSuite(WrapperSQLiteClassDaoTest.class);
        suite.addTestSuite(WrapperSQLiteItemDaoTest.class);
        suite.addTestSuite(WrapperSQLiteAbilityDaoTest.class);
        suite.addTestSuite(WrapperSQLiteRaceDaoTest.class);
        suite.addTestSuite(WrapperSQLiteSpelllistDaoTest.class);
        return suite;
    }

}
