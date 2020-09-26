package com.android.ash.charactersheet.dac.dao.sql.sqlite

import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.android.ash.charactersheet.gui.util.Logger.error
import com.d20charactersheet.framework.dac.dao.sql.ContentValues
import com.d20charactersheet.framework.dac.dao.sql.Database
import com.d20charactersheet.framework.dac.dao.sql.QueryResult

class SqliteDatabase(private val db: SQLiteDatabase) : Database {

    override fun delete(tableName: String, whereClause: String, whereArgs: Array<String>): Int {
        return db.delete(tableName, whereClause, whereArgs)
    }

    override fun insertOrThrow(tableName: String, contentValues: ContentValues): Long {
        return db.insertOrThrow(tableName, null, convertContentValues(contentValues))
    }

    override fun queryId(sql: String, rowId: Long): Int {
        val params = arrayOf(rowId.toString())
        var cursor: Cursor? = null
        return try {
            cursor = db.rawQuery(sql, params)
            cursor.moveToFirst()
            cursor.getInt(0)
        } catch (sqlException: SQLException) {
            error("Can't get id of rowId: $rowId")
            throw sqlException
        } finally {
            cursor?.close()
        }
    }

    override fun rawQuery(sql: String, params: Array<String>): QueryResult {
        val cursor: Cursor = db.rawQuery(sql, params)
        return SqliteQueryResult(cursor)
    }

    override fun update(tableName: String, contentValues: ContentValues, whereClause: String, whereArgs: Array<String>): Int {
        return db.update(tableName, convertContentValues(contentValues), whereClause, whereArgs)
    }

    private fun convertContentValues(contentValues: ContentValues): android.content.ContentValues {
        val androidContentValues = android.content.ContentValues()
        contentValues.keys().forEach { key ->
            when (val value = contentValues.get(key)) {
                is Int -> androidContentValues.put(key, value)
                is String -> androidContentValues.put(key, value)
                is ByteArray -> androidContentValues.put(key, value)
                is Float -> androidContentValues.put(key, value)
                is Boolean -> androidContentValues.put(key, value)
            }
        }
        return androidContentValues

    }

}