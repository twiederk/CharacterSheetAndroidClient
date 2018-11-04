package com.android.ash.charactersheet.gui.widget;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

public class StringListFilterTest extends TestCase {

    public void testPerformFiltering() {
        final List<String> items = Arrays.asList(new String[] { "Alfred", "Bertrand", "Dieter", "Dirk", "Caeser" });
        final ListModel<String> listModel = new ListModel<String>(items);
        final StringListFilter<String> stringListFilter = new StringListFilter<String>(listModel);

        stringListFilter.publishResults("Di", null);
        final List<String> result = stringListFilter.performFiltering();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

}
