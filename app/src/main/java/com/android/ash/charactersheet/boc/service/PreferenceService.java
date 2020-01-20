package com.android.ash.charactersheet.boc.service;

/**
 * Handles preferences and provides keys of preferences.
 */
public interface PreferenceService {

    /** Preference key of background image or color */
    String SHOW_IMAGE_AS_BACKGROUND = "showImageAsBackground";

    /** Preference key of background color */
    String BACKGROUND_COLOR = "backgroundColor";

    /** Preference key of game system to start up with */
    String GAME_SYSTEM_TYPE = "gameSystemType";

    /**
     * Returns boolean value of preference.
     * 
     * @param key
     *            The preference key.
     * @return The preference value.
     */
    boolean getBoolean(String key);

    /**
     * Returns int value of preference.
     * 
     * @param key
     *            The preference key.
     * @return The preference value.
     */
    int getInt(String key);

    /**
     * Sets boolean value of perference.
     * 
     * @param key
     *            The preference key.
     * @param value
     *            The preference value.
     */
    void setBoolean(String key, boolean value);

    /**
     * Sets int value of perference.
     * 
     * @param key
     *            The preference key.
     * @param value
     *            The preference value.
     */
    void setInt(String key, int value);

}
