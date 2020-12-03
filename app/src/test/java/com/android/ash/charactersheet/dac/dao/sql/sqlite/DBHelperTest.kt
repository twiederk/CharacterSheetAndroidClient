package com.android.ash.charactersheet.dac.dao.sql.sqlite

import android.database.sqlite.SQLiteDatabase
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class DBHelperTest {

    @Test
    fun executeSqlScript_scriptFromFramework_loadScriptFromClasspath() {
        // arrange
        val sqlLiteDatabase: SQLiteDatabase = mock()
        val scriptName = "/sql/create_database.sql"

        // act
        DBHelper(mock(), 0, GameSystemType.DNDV35).executeSqlStatements(sqlLiteDatabase, scriptName)

        // assert
        verify(sqlLiteDatabase, times(54)).execSQL(anyString())
    }

}