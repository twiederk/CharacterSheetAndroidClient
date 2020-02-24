package com.android.ash.charactersheet.gui.widget.numberview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * This view offers the possibility to increase and decrease number values. An minus and plus button is displayed to
 * decrease and increase the number. The number is displayed between the buttons. The buttons are of size 48.
 */
public class StepNumberView extends NumberView {

    /**
     * Instantiates NumberView contained in a layout file.
     * 
     * @param context
     *            The context of the activity.
     * @param attributeSet
     *            The attributes of the layout file.
     */
    public StepNumberView(final Context context, final AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    void initView(final Context context, final AttributeSet attributeSet, final LayoutParams layoutParams) {
        addView(createDecreaseTextView(context), layoutParams);
        addView(createNumberTextView(context), layoutParams);
        addView(createNumberEditText(context), layoutParams);
        addView(createIncreaseTextView(context), layoutParams);
    }

    private View createIncreaseTextView(final Context context) {
        final Button increaseButton = createButton(context, "+");
        increaseButton.setOnClickListener(view -> {
            if (controller != null) {
                controller.increase();
                setNumber(getNumber());
            }
        });
        return increaseButton;
    }

    private View createDecreaseTextView(final Context context) {
        final Button decreaseButton = createButton(context, "-");
        decreaseButton.setOnClickListener(view -> {
            if (controller != null) {
                controller.decrease();
                setNumber(getNumber());
            }
        });

        return decreaseButton;
    }

    /**
     * Returns controller. Null if no controller is set.
     * 
     * @return Controller. Null if no controller is set.
     */
    @Override
    public NumberViewController getController() {
        return controller;
    }

    /**
     * Set to true to display an EditText instead of an TextView. The EditText allows to edit the number value directly.
     * 
     * @param editable
     *            True to edit number value directly.
     */
    public void setEditable(final boolean editable) {
        if (editable) {
            numberTextView.setVisibility(View.GONE);
            numberEditText.setVisibility(View.VISIBLE);
        } else {
            numberTextView.setVisibility(View.VISIBLE);
            numberEditText.setVisibility(View.GONE);
        }
    }

}
