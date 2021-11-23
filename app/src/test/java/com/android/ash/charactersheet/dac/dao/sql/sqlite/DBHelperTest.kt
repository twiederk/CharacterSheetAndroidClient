package com.android.ash.charactersheet.dac.dao.sql.sqlite

import android.database.sqlite.SQLiteDatabase
import com.android.ash.charactersheet.boc.model.GameSystemType
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class DBHelperTest {

    @Test
    fun executeSqlScript_scriptFromFramework_loadScriptFromClasspath() {
        // arrange
        val sqlLiteDatabase: SQLiteDatabase = mock()
        val scriptName = "/sql/create_database.sql"

        // act
        DBHelper(mock(), 0, GameSystemType.DNDV35).executeSqlStatements(sqlLiteDatabase, scriptName)

        // assert
        verify(sqlLiteDatabase, times(57)).execSQL(anyString())
    }

}