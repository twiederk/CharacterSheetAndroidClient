package com.android.ash.charactersheet.dac.dao.android

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import com.android.ash.charactersheet.dac.dao.PreferenceDao

/**
 * Access object to preferences using the Android Preferences API.
 *
 * @param context The application context.
 */

class AndroidPreferenceDao @JvmOverloads constructor(context: Context, filename: String = FILENAME) : PreferenceDao {

    private val preferences: SharedPreferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE)
    private val editor: Editor = preferences.edit()

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return preferences.getBoolean(key, defaultValue)
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        return preferences.getInt(key, defaultValue)
    }

    override fun setBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.commit()
    }

    override fun setInt(key: String, value: Int) {
        editor.putInt(key, value)
        editor.commit()
    }

    companion object {
        /** The name of the file containing the preferences.  */
        const val FILENAME = "d20cs.prefs"
    }

}