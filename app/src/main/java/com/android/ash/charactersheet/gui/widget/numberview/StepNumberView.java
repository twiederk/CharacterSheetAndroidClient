package com.android.ash.charactersheet.gui.widget.numberview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * This view offers the possiblity to increase and decrease number values. An minus and plus button is displayed to
 * decrease and increase the number. The number is displayed inbetween the buttons. The buttons are of size 48.
 */
public class StepNumberView extends NumberView {

    /**
     * Instanciates NumberView contained in a layout file.
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
        addView(createDecreaseTextView(context, attributeSet), layoutParams);
        addView(createNumberTextView(context, attributeSet), layoutParams);
        addView(createNumberEditText(context, attributeSet), layoutParams);
        addView(createIncreaseTextView(context, attributeSet), layoutParams);
    }

    @Override
    View createIncreaseTextView(final Context context, final AttributeSet attributeSet) {
        final Button increaseButton = createButton(context, "+", attributeSet);
        increaseButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View view) {
                if (controller != null) {
                    controller.increase();
                    setNumber(getNumber());
                }
            }

        });
        return increaseButton;
    }

    @Override
    View createDecreaseTextView(final Context context, final AttributeSet attributeSet) {
        final Button decreaseButton = createButton(context, "-", attributeSet);
        decreaseButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View view) {
                if (controller != null) {
                    controller.decrease();
                    setNumber(getNumber());
                }
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
    @Override
    public void setEditable(final boolean editable) {
        if (editable) {
            numberTextView.setVisibility(View.GONE);
            numberEditText.setVisibility(View.VISIBLE);
        } else {
            numberTextView.setVisibility(View.VISIBLE);
            numberEditText.setVisibility(View.GONE);
        }
        this.editable = editable;
    }

    /**
     * Returns true, if number value is editable directly.
     * 
     * @return True, if number value is editable directly.
     */
    @Override
    public boolean isEditable() {
        return editable;
    }
}
