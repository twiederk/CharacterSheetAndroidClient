package com.android.ash.charactersheet.dac.dao.sqlite

import android.database.Cursor
import com.d20charactersheet.framework.dac.dao.DataRow

class SQLiteDataRow(private val cursor: Cursor) : DataRow {

    override fun getInt(columnIndex: Int): Int = cursor.getInt(columnIndex)

    override fun getString(columnIndex: Int): String = cursor.getString(columnIndex)

    override fun getFloat(columnIndex: Int): Float = cursor.getFloat(columnIndex)
}