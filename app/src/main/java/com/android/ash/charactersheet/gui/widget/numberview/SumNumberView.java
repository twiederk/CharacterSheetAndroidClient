package com.android.ash.charactersheet.gui.widget.numberview;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Displays a number, a textfield, a plus and minus button. By pressing the plus or minus butten the value in the
 * textfield is added/substracted to/from the displayed number.
 */
public class SumNumberView extends NumberView {

    private EditText summandEditText;

    /**
     *
     * Creates a SumNumberview using the given context and attribute Set
     *
     * @param context
     *            The context to use.
     * @param attributeSet
     *            The attributes to use.
     */
    public SumNumberView(final Context context, final AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    void initView(final Context context, final AttributeSet attributeSet, final LayoutParams layoutParams) {
        addView(createNumberTextView(context, attributeSet), layoutParams);
        addView(createNumberEditText(context, attributeSet), layoutParams);
        addView(createSummandEditText(context, attributeSet), layoutParams);
        addView(createDecreaseTextView(context, attributeSet), layoutParams);
        addView(createIncreaseTextView(context, attributeSet), layoutParams);
    }

    private View createSummandEditText(final Context context, final AttributeSet attributeSet) {
        summandEditText = new EditText(context, attributeSet);
        summandEditText.setMinWidth((int) (EDITTEXT_SIZE * scale));
        summandEditText.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE);
        summandEditText.setText("1");
        summandEditText.setGravity(Gravity.RIGHT);
        summandEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        return summandEditText;
    }

    @Override
    View createIncreaseTextView(final Context context, final AttributeSet attributeSet) {
        final Button increaseButton = createButton(context, "+", attributeSet);
        increaseButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View view) {
                if (controller != null) {
                    controller.increase(getNumber(summandEditText));
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
                    controller.decrease(getNumber(summandEditText));
                    setNumber(getNumber());
                }
            }

        });

        return decreaseButton;
    }

    /**
     * Sets the summand in the textfield.
     *
     * @param number
     *            The sumand.
     */
    public void setSummand(final Number number) {
        summandEditText.setText(number.toString());
    }
}
