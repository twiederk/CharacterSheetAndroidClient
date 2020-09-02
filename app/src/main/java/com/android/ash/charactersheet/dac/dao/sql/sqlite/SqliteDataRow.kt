package com.android.ash.charactersheet.dac.dao.sql.sqlite

import android.database.Cursor
import com.d20charactersheet.framework.dac.dao.sql.DataRow

class SqliteDataRow(private val cursor: Cursor) : DataRow {

    override fun getInt(columnIndex: Int): Int = cursor.getInt(columnIndex)

    override fun getString(columnIndex: Int): String = cursor.getString(columnIndex)

    override fun getFloat(columnIndex: Int): Float = cursor.getFloat(columnIndex)

    override fun getBlob(columnIndex: Int): ByteArray = cursor.getBlob(columnIndex)
}