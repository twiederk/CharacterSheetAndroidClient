package com.android.ash.charactersheet.gui.widget.numberview;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * This view offers the possibility to increase and decrease number values. An minus and plus button is displayed to
 * decrease and increase the number. The number is displayed between the buttons. The buttons are of size 48.
 */
public abstract class NumberView extends LinearLayout {

    static final int EDIT_TEXT_SIZE = 48;
    static final int TEXT_SIZE = 15;

    private static final int BUTTON_SIZE = 48;

    final float scale;

    NumberViewController controller;
    TextView numberTextView;
    EditText numberEditText;

    /**
     * Instantiates NumberView contained in a layout file.
     * 
     * @param context
     *            The context of the activity.
     * @param attributeSet
     *            The attributes of the layout file.
     */
    public NumberView(final Context context, final AttributeSet attributeSet) {
        super(context, attributeSet);
        scale = getResources().getDisplayMetrics().density;
        this.setOrientation(HORIZONTAL);
        initView(context, attributeSet, createLayoutParams());
    }

    abstract void initView(Context context, AttributeSet attributeSet, LayoutParams layoutParams);

    private LayoutParams createLayoutParams() {
        final LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(8, 0, 8, 0);
        return layoutParams;
    }

    Button createButton(final Context context, final String text) {
        final Button button = new Button(context);
        button.setText(text);
        button.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE);
        button.setWidth((int) (BUTTON_SIZE * scale));
        button.setHeight((int) (BUTTON_SIZE * scale));
        button.setGravity(Gravity.CENTER);

        return button;
    }

    View createNumberTextView(final Context context) {
        numberTextView = new TextView(context);
        numberTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE);
        return numberTextView;
    }

    View createNumberEditText(final Context context) {
        numberEditText = new EditText(context);
        numberEditText.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE);
        numberEditText.setVisibility(View.GONE);
        numberEditText.setMinWidth((int) (EDIT_TEXT_SIZE * scale));
        numberEditText.setGravity(Gravity.END);
        numberEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        numberEditText.setOnKeyListener((view, keyCode, event) -> {
            if (controller != null) {
                controller.setNumber(getNumber(numberEditText));
            }
            return false;
        });
        return numberEditText;
    }

    /**
     * Returns controller. Null if no controller is set.
     * 
     * @return Controller. Null if no controller is set.
     */
    public NumberViewController getController() {
        return controller;
    }

    /**
     * Sets the controller of the NumberView. Displaying its number immediately.
     * 
     * @param controller
     *            The controller of the NumberView.
     */
    public void setController(final NumberViewController controller) {
        this.controller = controller;
        setNumber(getNumber());
    }

    void setNumber(final String number) {
        numberTextView.setText(number);
        numberEditText.setText(number);
    }

    String getNumber() {
        if (controller != null) {
            final Number number = controller.getNumber();
            if (number instanceof Integer) {
                final Integer integerNumber = (Integer) number;
                return integerNumber.toString();
            } else if (number instanceof Float) {
                final Float floatNumber = (Float) number;
                return floatNumber.toString();
            }
            throw new IllegalStateException("Not supported number: " + number.getClass());
        }
        return "";
    }

    Number getNumber(final TextView textView) {
        final String text = textView.getText().toString();
        if ("".equals(text)) {
            return 0;
        }
        return Float.valueOf(text);
    }

}
