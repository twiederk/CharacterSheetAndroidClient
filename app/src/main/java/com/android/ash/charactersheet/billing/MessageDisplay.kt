package com.android.ash.charactersheet.billing

import android.content.Context
import android.widget.Toast

class MessageDisplay(private val context: Context) {

    fun display(resourceId: Int) {
        Toast.makeText(context, context.getString(resourceId), Toast.LENGTH_LONG).show()
    }

    fun display(resourceId: Int, param: Int) {
        Toast.makeText(context, context.getString(resourceId, param), Toast.LENGTH_LONG).show()
    }

}
