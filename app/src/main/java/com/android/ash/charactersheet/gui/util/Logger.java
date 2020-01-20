package com.android.ash.charactersheet.gui.util;

import android.util.Log;

/**
 * Logs messages and exceptions under the log tag "d20cs" to the console.
 */
public class Logger {

    private static final String LOG_TAG = "d20cs";

    /**
     * Logs a message with debug level.
     * 
     * @param message
     *            The message to log.
     */
    public static void debug(final String message) {
        Log.d(LOG_TAG, message);
    }

    /**
     * Logs message and exception with debug level.
     * 
     * @param message
     *            The message to log.
     * @param exception
     *            The exception to log.
     */
    public static void debug(final String message, final Exception exception) {
        Log.d(LOG_TAG, message, exception);
    }

    /**
     * Logs a message with info level.
     * 
     * @param message
     *            The message to log.
     */
    public static void info(final String message) {
        Log.i(LOG_TAG, message);
    }

    /**
     * Logs a message with warn level.
     * 
     * @param message
     *            The message to log.
     */
    public static void warn(final String message) {
        Log.w(LOG_TAG, message);
    }

    /**
     * Logs message and exception with warn level.
     * 
     * @param message
     *            The message to log.
     * @param exception
     *            The exception to log.
     */
    public static void warn(final String message, final Exception exception) {
        Log.w(LOG_TAG, message, exception);
    }

    /**
     * Logs a message with error level.
     * 
     * @param message
     *            The message to log.
     */
    public static void error(final String message) {
        Log.e(LOG_TAG, message);
    }

    /**
     * Logs message and exception with error level.
     * 
     * @param message
     *            The message to log.
     * @param exception
     *            The exception to log.
     */
    public static void error(final String message, final Exception exception) {
        Log.e(LOG_TAG, message, exception);
    }

}
