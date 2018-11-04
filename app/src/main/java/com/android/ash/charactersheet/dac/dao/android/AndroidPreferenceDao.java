package com.android.ash.charactersheet.dac.dao.android;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.ash.charactersheet.dac.dao.PreferenceDao;

/**
 * Access object to preferences using the Android Preferences API.
 */
public class AndroidPreferenceDao implements PreferenceDao {

    /** The name of the file containing the preferences. */
    public static final String FILENAME = "d20cs.prefs";

    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;

    /**
     * Instanciates AndroidPreferenceDao.
     * 
     * @param context
     *            The application context.
     */
    public AndroidPreferenceDao(final Context context) {
        preferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    @Override
    public boolean getBoolean(final String key, final boolean defaultValue) {
        final boolean preference = preferences.getBoolean(key, defaultValue);
        return preference;
    }

    @Override
    public int getInt(final String key, final int defaultValue) {
        final int preference = preferences.getInt(key, defaultValue);
        return preference;
    }

    @Override
    public void setBoolean(final String key, final boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    @Override
    public void setInt(final String key, final int value) {
        editor.putInt(key, value);
        editor.commit();
    }

}
