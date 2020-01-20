package com.android.ash.charactersheet.gui.widget;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StringListFilterTest {

    @Test
    public void testPerformFiltering() {
        final List<String> items = Arrays.asList("Alfred", "Bertrand", "Dieter", "Dirk", "Christoph");
        final ListModel<String> listModel = new ListModel<>(items);
        final StringListFilter<String> stringListFilter = new StringListFilter<>(listModel);

        stringListFilter.publishResults("Di", null);
        final List<String> result = stringListFilter.performFiltering();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

}
