package com.android.ash.charactersheet.billing

import android.content.Context
import com.android.ash.charactersheet.gui.util.Logger

class MessageDisplay(private val context: Context) {

    fun display(resourceId: Int) {
        Logger.warn(context.getString(resourceId))
    }

    fun display(resourceId: Int, param: Int) {
        Logger.warn(context.getString(resourceId, param))
    }

}
