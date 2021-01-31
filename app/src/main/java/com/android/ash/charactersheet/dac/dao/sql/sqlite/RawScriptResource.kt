package com.android.ash.charactersheet.dac.dao.sql.sqlite

import android.content.res.Resources
import java.io.InputStream

class RawScriptResource(val resourceId: Int) : ScriptResource {

    override fun load(resources: Resources): InputStream = resources.openRawResource(resourceId)

}