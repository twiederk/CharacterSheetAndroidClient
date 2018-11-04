package com.android.ash.charactersheet.dac.dao.sqlite.rowmapper;

import junit.framework.TestCase;

import android.database.Cursor;
import android.database.SQLException;

import com.d20charactersheet.framework.boc.model.Alignment;

public class BaseRowMapperTest extends TestCase {

    private BaseRowMapper baseRowMapper;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        baseRowMapper = new BaseRowMapper() {

            @Override
            public Object mapRow(final Cursor cursor) throws SQLException {
                return null;
            }
        };

    }

    public void testGetEnum() {
        final Alignment alignment = (Alignment) baseRowMapper.getEnum(0, Alignment.values());
        assertEquals(Alignment.LAWFUL_GOOD, alignment);
    }

    public void testGetEnumWithException() throws Exception {
        try {
            baseRowMapper.getEnum(100, Alignment.values());
            fail("Missing IllegalArgumentException");
        } catch (final IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage());
            assertEquals("Can't determine Alignment[] of id: 100", illegalArgumentException.getMessage());
        } catch (final Exception exception) {
            fail("Expected IllegalArgumentException");
        }
    }
}
