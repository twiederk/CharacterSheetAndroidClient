package com.android.ash.charactersheet.dac.dao.sql.sqlite

class DBUpdateScriptAdministration(private val updateScripts: Map<Int, ScriptResource>, private val updateImages: Map<Int, ImageResources>) {

    fun getUpdateScript(oldVersion: Int): ScriptResource? = updateScripts[oldVersion]
    fun getUpdateImage(oldVersion: Int): ImageResources? = updateImages[oldVersion]

}

