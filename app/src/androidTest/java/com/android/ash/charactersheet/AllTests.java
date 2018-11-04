package com.android.ash.charactersheet;

import junit.framework.TestSuite;

import com.android.ash.charactersheet.backuprestore.AllTestsBackupAndRestore;
import com.android.ash.charactersheet.boc.AllTestsBusinessLogic;
import com.android.ash.charactersheet.dac.AllTestsPersistence;
import com.android.ash.charactersheet.gui.AllTestsGui;

public class AllTests extends TestSuite {

    public static TestSuite suite() {
        final TestSuite suite = new TestSuite();
        suite.addTest(AllTestsBusinessLogic.suite());
        suite.addTest(AllTestsPersistence.suite());
        suite.addTest(AllTestsGui.suite());
        suite.addTest(AllTestsBackupAndRestore.suite());
        return suite;
    }

}
