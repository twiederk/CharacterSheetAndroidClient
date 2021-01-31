package com.android.ash.charactersheet.gui.util

import android.util.Log

/**
 * Logs messages and exceptions under the log tag "d20cs" to the console.
 */
object Logger {

    private const val LOG_TAG = "d20cs"

    /**
     * Logs a message with debug level.
     *
     * @param message The message to log.
     */
    @JvmStatic
    fun debug(message: String) {
        Log.d(LOG_TAG, message)
    }

    /**
     * Logs message and exception with debug level.
     *
     * @param message The message to log.
     * @param exception The exception to log.
     */
    @JvmStatic
    fun debug(message: String, exception: Exception?) {
        Log.d(LOG_TAG, message, exception)
    }

    /**
     * Logs a message with info level.
     *
     * @param message The message to log.
     */
    @JvmStatic
    fun info(message: String) {
        Log.i(LOG_TAG, message)
    }

    /**
     * Logs a message with warn level.
     *
     * @param message
     * The message to log.
     */
    @JvmStatic
    fun warn(message: String) {
        Log.w(LOG_TAG, message)
    }

    /**
     * Logs a message with error level.
     *
     * @param message The message to log.
     */
    @JvmStatic
    fun error(message: String) {
        Log.e(LOG_TAG, message)
    }

    /**
     * Logs message and exception with error level.
     *
     * @param message The message to log.
     * @param exception The exception to log.
     */
    @JvmStatic
    fun error(message: String, exception: Exception?) {
        Log.e(LOG_TAG, message, exception)
    }
}