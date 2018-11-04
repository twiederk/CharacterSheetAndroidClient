package com.android.ash.charactersheet.boc.service;

/**
 * Handles preferences and provides keys of preferences.
 */
public interface PreferenceService {

    /** Preference key of background image or color */
    public static final String SHOW_IMAGE_AS_BACKGROUND = "showImageAsBackground";

    /** Preference key of background color */
    public static final String BACKGROUND_COLOR = "backgroundColor";

    /** Preference key of game system to start up with */
    public static final String GAME_SYSTEM_TYPE = "gameSystemType";

    /**
     * Returns boolean value of preference.
     * 
     * @param key
     *            The preference key.
     * @return The preference value.
     */
    public boolean getBoolean(String key);

    /**
     * Returns int value of preference.
     * 
     * @param key
     *            The preference key.
     * @return The preference value.
     */
    public int getInt(String key);

    /**
     * Sets boolean value of perference.
     * 
     * @param key
     *            The preference key.
     * @param value
     *            The preference value.
     */
    public void setBoolean(String key, boolean value);

    /**
     * Sets int value of perference.
     * 
     * @param key
     *            The preference key.
     * @param value
     *            The preference value.
     */
    public void setInt(String key, int value);

}
