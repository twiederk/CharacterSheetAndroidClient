package com.android.ash.charactersheet.gui.main;

import android.support.annotation.NonNull;

/**
 * Contains information about execution of task. If the task was executes successful only successful is set to true and
 * exception is null. If the task failed successful is false and the exception is set.
 */
public class TaskResult {

    private final boolean successful;
    private Exception exception;

    /**
     * Create TaskResult with information about task execution.
     * 
     * @param successful
     *            True, if task execution was successful.
     */
    public TaskResult(final boolean successful) {
        this.successful = successful;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @NonNull
    @Override
    public String toString() {
        return "ImportResult: [" + successful + "," + exception + "]";
    }

}
