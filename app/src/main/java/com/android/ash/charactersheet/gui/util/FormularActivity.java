package com.android.ash.charactersheet.gui.util;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.android.ash.charactersheet.CharacterSheetApplication;
import com.android.ash.charactersheet.gui.widget.numberview.NumberView;
import com.android.ash.charactersheet.gui.widget.numberview.NumberViewController;
import com.d20charactersheet.framework.boc.service.DisplayService;

import java.util.List;

/**
 * Base class to build forms for a specific object.
 * 
 * @param <T>
 *            The object the form allows to modify.
 */
public abstract class FormularActivity<T> extends LogActivity {

    protected DisplayService displayService;
    protected T form;

    private MessageManager messageManager;

    /**
     * Creates a from with the given layout and title, including cancel and ok button. Providing access to form object
     * and display service.
     *
     * @param savedInstanceState The saved instance state
     * @param layoutResourceId The resource id of the layout
     */
    protected void onCreate(final Bundle savedInstanceState, final int layoutResourceId) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResourceId);
        setTitle(getHeading());

        final CharacterSheetApplication application = (CharacterSheetApplication) getApplication();
        displayService = application.getGameSystem().getDisplayService();

        messageManager = new MessageManager(this, displayService);

        form = createForm();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fillViews();
    }

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            save();
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void save() {
        try {
            fillForm();
            saveForm();
            finish();
        } catch (final Exception exception) {
            Logger.error("Can't store data", exception);
            messageManager.showErrorMessage(exception);
        }
    }

    protected void setText(final String text, final int textViewResourceId) {
        final TextView textView = findViewById(textViewResourceId);
        textView.setText(text);
    }

    protected void setSpinner(final int spinnerResourceId, final SpinnerAdapter adapter, final int position) {
        final Spinner spinner = findViewById(spinnerResourceId);
        spinner.setAdapter(adapter);
        spinner.setSelection(position);
    }

    protected void setCheckBox(final boolean checked, final int checkBoxResourceId) {
        final CheckBox checkBox = findViewById(checkBoxResourceId);
        checkBox.setChecked(checked);
    }

    protected String getTextOfTextView(final int textViewResourceId) {
        final TextView textView = findViewById(textViewResourceId);
        return textView.getText().toString();
    }

    protected Object getSelectedItemOfSpinner(final int spinnerResourceId) {
        final Spinner spinner = findViewById(spinnerResourceId);
        return spinner.getSelectedItem();
    }

    protected boolean isChecked(final int checkBoxResourceId) {
        final CheckBox checkBox = findViewById(checkBoxResourceId);
        return checkBox.isChecked();
    }

    protected int getIntegerOfTextView(final int textViewResourceId) {
        int value = 0;
        try {
            value = Integer.parseInt(getTextOfTextView(textViewResourceId));
        } catch (final NumberFormatException numberFormatException) {
            Logger.error("Can't parse text to integer", numberFormatException);
        }
        return value;
    }

    protected int getEnumPosition(final List<Enum<?>> enumerations, final Enum<?> enumeration) {
        for (int i = 0; i < enumerations.size(); i++) {
            if (enumerations.get(i).equals(enumeration)) {
                return i;
            }
        }
        return 0;
    }

    protected void setNumberViewController(final NumberViewController controller, final int numberViewResourceId) {
        final NumberView numberView = findViewById(numberViewResourceId);
        numberView.setController(controller);
    }

    protected int getIntegerOfNumberView(final int numberViewResourceId) {
        final NumberView numberView = findViewById(numberViewResourceId);
        return numberView.getController().getNumber().intValue();
    }

    protected abstract String getHeading();

    protected abstract T createForm();

    protected abstract void fillViews();

    protected abstract void fillForm();

    protected abstract void saveForm();

}
