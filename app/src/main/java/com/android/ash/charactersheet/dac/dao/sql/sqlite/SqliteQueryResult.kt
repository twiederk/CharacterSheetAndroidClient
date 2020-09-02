package com.android.ash.charactersheet.dac.dao.sql.sqlite

import android.database.Cursor
import com.d20charactersheet.framework.dac.dao.sql.DataRow
import com.d20charactersheet.framework.dac.dao.sql.QueryResult

class SqliteQueryResult(private val cursor: Cursor) : QueryResult {

    override fun close() {
        cursor.close()
    }

    override fun getDataRow(): DataRow {
        return SqliteDataRow(cursor)
    }

    override fun isAfterLast(): Boolean {
        return cursor.isAfterLast
    }

    override fun moveToFirst() {
        cursor.moveToFirst()
    }

    override fun moveToNext() {
        cursor.moveToNext()
    }

}
