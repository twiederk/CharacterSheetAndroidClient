package com.android.ash.charactersheet.gui.main.exportimport;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.widget.ListAdapter;
import com.android.ash.charactersheet.gui.widget.ListModel;
import com.d20charactersheet.framework.boc.service.exportimport.ImportMessage;
import com.d20charactersheet.framework.boc.service.exportimport.ImportMessage.Type;

/**
 * Displays import messages in different colors.
 */
public class ImportMessageAdapter extends ListAdapter<ImportMessage> {

    /**
     * Creates adapter to display import messages.
     * 
     * @param context
     *            The context of the activity.
     * @param itemViewResourceId
     *            The id of the list item resource file.
     * @param listModel
     *            The import messages to display.
     */
    public ImportMessageAdapter(final Context context, final int itemViewResourceId,
            final ListModel<ImportMessage> listModel) {
        super(context, itemViewResourceId, listModel);
    }

    @Override
    protected void fillView(final View view, final Object item) {
        final ImportMessage importMessage = (ImportMessage) item;
        final TextView textView = getTextView(view, importMessage.getType());
        textView.setText(importMessage.getMessage());
        textView.setTextColor(getColor(importMessage.getType()));
    }

    private TextView getTextView(final View view, final Type type) {
        int messageTextViewId;
        int hiddenTextViewId;
        switch (type) {
            case SUCCESS:
            case FAILURE:
                messageTextViewId = R.id.importmessage_heading;
                hiddenTextViewId = R.id.importmessage_text;
                break;
            case WARNING:
            case ERROR:
            default:
                messageTextViewId = R.id.importmessage_text;
                hiddenTextViewId = R.id.importmessage_heading;
        }
        final View hiddenTextView = view.findViewById(hiddenTextViewId);
        hiddenTextView.setVisibility(View.GONE);

        final TextView messageTextView = view.findViewById(messageTextViewId);
        messageTextView.setVisibility(View.VISIBLE);
        return messageTextView;
    }

    private int getColor(final Type type) {
        switch (type) {
            case WARNING:
                return Color.YELLOW;
            case ERROR:
            case FAILURE:
                return Color.RED;
            case SUCCESS:
            default:
                return Color.GREEN;
        }
    }

}
