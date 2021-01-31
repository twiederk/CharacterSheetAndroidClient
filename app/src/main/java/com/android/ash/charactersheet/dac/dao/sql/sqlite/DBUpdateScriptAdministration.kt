package com.android.ash.charactersheet.dac.dao.sql.sqlite

class DBUpdateScriptAdministration(private val updateScripts: Map<Int, ScriptResource>) {

    fun getUpdateScript(oldVersion: Int): ScriptResource? = updateScripts[oldVersion]

}

