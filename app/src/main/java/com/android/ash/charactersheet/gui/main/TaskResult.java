package com.android.ash.charactersheet.gui.main;

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
     * Returns task execution result.
     * 
     * @return Task execution result.
     */
    public boolean isSuccessful() {
        return successful;
    }

    /**
     * Set the exception which occured while task execution.
     * 
     * @param exception
     *            Exception of task execution.
     */
    public void setException(final Exception exception) {
        this.exception = exception;
    }

    /**
     * Returns task execution exception.
     * 
     * @return Task execution exception.
     */
    public Exception getException() {
        return exception;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ImportResult: [" + successful + "," + exception + "]";
    }

}
