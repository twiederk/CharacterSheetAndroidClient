package com.android.ash.charactersheet.gui.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.RuleException;
import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * Manager to show messages in dialogs to the users.
 */
public class MessageManager {

    private final Context context;
    private final DisplayService displayService;
    private final DialogInterface.OnClickListener onClickListener;

    /**
     * Creates a MessageManager to display messages.
     * 
     * @param context
     *            The context of the activity to display the message in.
     * @param displayService
     *            The display service to retrieve text to display.
     */
    public MessageManager(final Context context, final DisplayService displayService) {
        this.context = context;
        this.displayService = displayService;
        onClickListener = (dialog, arg1) -> {
            // Empty OnClickListener
        };
    }

    /**
     * Shows the message in a dialog with a OK button.
     * 
     * @param exception
     *            The exception containing the message to display.
     */
    public void showErrorMessage(final Exception exception) {
        final String message = getMessage(exception);
        showMessage(R.string.error_dialog_title, message);
    }

    private String getMessage(final Exception exception) {
        if (exception instanceof RuleException) {
            final RuleException ruleException = (RuleException) exception;
            return getMessage(ruleException);
        }
        return exception.getMessage();
    }

    private String getMessage(final RuleException exception) {
        String message = displayService.getErrorMessage(exception.getRuleError());
        if (exception.getValue() != null) {
            message += " (" + exception.getValue() + ")";
        }
        return message;
    }

    /**
     * Shows an info message with the given resource id.
     * 
     * @param resourceId
     *            The id of the resource.
     */
    public void showInfoMessage(final int resourceId) {
        showMessage(resourceId);
    }

    /**
     * Shows an info message with the given title and message.
     *
     * @param resourceId
     *            The message to display.
     */
    private void showMessage(final int resourceId) {
        final String message = context.getResources().getString(resourceId);
        showMessage(R.string.info_dialog_title, message);
    }

    /**
     * Shows a message to the user with the given title and message.
     * 
     * @param title
     *            The title to show.
     * @param message
     *            The message to show.
     */
    private void showMessage(final int title, final String message) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(R.string.button_ok, onClickListener);
        alertDialog.show();
    }

}
