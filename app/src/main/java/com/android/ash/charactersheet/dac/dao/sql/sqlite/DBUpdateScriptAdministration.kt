package com.android.ash.charactersheet.dac.dao.sql.sqlite

class DBUpdateScriptAdministration(private val updateScripts: Map<Int, Int>) {

    fun getUpdateScript(oldVersion: Int): Int = updateScripts.getOrDefault(oldVersion, NO_UPDATE_SCRIPT)

    companion object {
        const val NO_UPDATE_SCRIPT = 0
    }

}

