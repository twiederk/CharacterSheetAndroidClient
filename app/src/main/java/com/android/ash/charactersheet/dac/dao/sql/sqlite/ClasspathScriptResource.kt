package com.android.ash.charactersheet.dac.dao.sql.sqlite

import android.content.res.Resources
import java.io.InputStream

class ClasspathScriptResource(private val scriptName: String) : ScriptResource {

    override fun load(resources: Resources): InputStream = this.javaClass.getResourceAsStream(scriptName)
}