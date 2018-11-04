package com.android.ash.charactersheet.backuprestore;

import junit.framework.TestSuite;

public class AllTestsBackupAndRestore extends TestSuite {

    public static TestSuite suite() {
        final TestSuite suite = new TestSuite();
        suite.setName("AllTestsBackupRestore");
        suite.addTestSuite(FileBackupAgentTest.class);
        return suite;
    }

}
