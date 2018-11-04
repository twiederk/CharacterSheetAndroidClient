package com.android.ash.charactersheet.gui;

import junit.framework.TestSuite;

import com.android.ash.charactersheet.gui.sheet.ImageHandlerTest;
import com.android.ash.charactersheet.gui.sheet.feat.FeatModelTest;
import com.android.ash.charactersheet.gui.sheet.item.EquipmentHelperTest;
import com.android.ash.charactersheet.gui.sheet.spelllist.SpelllistModelTest;
import com.android.ash.charactersheet.gui.widget.StringListFilterTest;
import com.android.ash.charactersheet.gui.widget.dierollview.DieRollViewTest;

public class AllTestsGui extends TestSuite {

    public static TestSuite suite() {
        final TestSuite suite = new TestSuite();
        suite.setName("AllTestsGui");
        suite.addTestSuite(FeatModelTest.class);
        suite.addTestSuite(EquipmentHelperTest.class);
        suite.addTestSuite(StringListFilterTest.class);
        suite.addTestSuite(ImageHandlerTest.class);
        suite.addTestSuite(SpelllistModelTest.class);
        suite.addTestSuite(DieRollViewTest.class);
        return suite;
    }

}
