package com.android.ash.charactersheet.dac.dao;

/**
 * Provides access to preferences of the persistent layer.
 */
public interface PreferenceDao {

    /**
     * Returns boolean value of perference.
     * 
     * @param key
     *            The key of the preference.
     * @param defaultValue
     *            The default value of the preference.
     * @return boolean value of perference.
     */
    boolean getBoolean(String key, boolean defaultValue);

    /**
     * Returns int value of preference.
     * 
     * @param key
     *            The key of the preference.
     * @param defaultValue
     *            The default value of the preference.
     * @return int value of the preference.
     */
    int getInt(String key, int defaultValue);

    /**
     * Sets boolean value of preference.
     * 
     * @param key
     *            The key of the preference.
     * @param value
     *            The value to set.
     */
    void setBoolean(String key, boolean value);

    /**
     * Sets int value of preference.
     * 
     * @param key
     *            The preference key.
     * @param value
     *            The preference value.
     */
    void setInt(String key, int value);

}
