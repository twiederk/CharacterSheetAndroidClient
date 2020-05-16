package com.android.ash.charactersheet.dac.dao.sqlite.rowmapper

import android.database.Cursor
import android.database.SQLException
import com.d20charactersheet.framework.boc.model.Alignment
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class BaseRowMapperTest {

    private lateinit var baseRowMapper: BaseRowMapper

    @Before
    fun setUp() {
        baseRowMapper = object : BaseRowMapper() {
            @Throws(SQLException::class)
            override fun mapRow(cursor: Cursor): Any? {
                return null
            }
        }
    }

    @Test
    fun testGetEnum() {
        val alignment = baseRowMapper.getEnum(0, Alignment.values()) as Alignment
        Assert.assertEquals(Alignment.LAWFUL_GOOD, alignment)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testGetEnumWithException() {
        baseRowMapper.getEnum(100, Alignment.values())
    }

}