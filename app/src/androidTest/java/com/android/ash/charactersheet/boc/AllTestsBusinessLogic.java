package com.android.ash.charactersheet.boc;

import junit.framework.TestSuite;

import com.android.ash.charactersheet.boc.service.AndroidDisplayServiceTest;
import com.android.ash.charactersheet.boc.service.AndroidImageServiceTest;
import com.android.ash.charactersheet.boc.service.PreferenceServiceTest;
import com.android.ash.charactersheet.boc.service.WrapperCharacterServiceTest;

public class AllTestsBusinessLogic extends TestSuite {

    public static TestSuite suite() {
        final TestSuite suite = new TestSuite();
        suite.setName("AllTestsBusinessLogic");
        suite.addTestSuite(AndroidDisplayServiceTest.class);
        suite.addTestSuite(AndroidImageServiceTest.class);
        suite.addTestSuite(WrapperCharacterServiceTest.class);
        suite.addTestSuite(PreferenceServiceTest.class);
        return suite;
    }

}
