package com.android.ash.charactersheet.dac.dao.sqlite.rowmapper;

import android.database.Cursor;
import android.database.SQLException;
import android.support.test.runner.AndroidJUnit4;

import com.d20charactersheet.framework.boc.model.Alignment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class BaseRowMapperTest {

    private BaseRowMapper baseRowMapper;

    @Before
    public void setUp() {
        baseRowMapper = new BaseRowMapper() {

            @Override
            public Object mapRow(final Cursor cursor) throws SQLException {
                return null;
            }
        };

    }

    @Test
    public void testGetEnum() {
        final Alignment alignment = (Alignment) baseRowMapper.getEnum(0, Alignment.values());
        assertEquals(Alignment.LAWFUL_GOOD, alignment);
    }

    @Test
    public void testGetEnumWithException() {
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
