package com.android.ash.charactersheet.dac.dao.sql.sqlite

import android.content.res.Resources
import java.io.InputStream

interface ScriptResource {

    fun load(resources: Resources): InputStream

}