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
 * Displays a number, a text field, a plus and minus button. By pressing the plus or minus button the value in the
 * text field is added/subtracted to/from the displayed number.
 */
public class SumNumberView extends NumberView {

    private EditText summandEditText;

    /**
     *
     * Creates a SumNumberView using the given context and attribute Set
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
    void initView(final Context context, final LayoutParams layoutParams) {
        addView(createNumberTextView(context), layoutParams);
        addView(createNumberEditText(context), layoutParams);
        addView(createSummandEditText(context), layoutParams);
        addView(createDecreaseTextView(context), layoutParams);
        addView(createIncreaseTextView(context), layoutParams);
    }

    private View createSummandEditText(final Context context) {
        summandEditText = new EditText(context);
        summandEditText.setMinWidth((int) (EDIT_TEXT_SIZE * scale));
        summandEditText.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE);
        summandEditText.setText("1");
        summandEditText.setGravity(Gravity.END);
        summandEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        return summandEditText;
    }

    private View createIncreaseTextView(final Context context) {
        final Button increaseButton = createButton(context, "+");
        increaseButton.setOnClickListener(view -> {
            if (controller != null) {
                controller.increase(getNumber(summandEditText));
                setNumber(getNumber());
            }
        });
        return increaseButton;
    }

    private View createDecreaseTextView(final Context context) {
        final Button decreaseButton = createButton(context, "-");
        decreaseButton.setOnClickListener(view -> {
            if (controller != null) {
                controller.decrease(getNumber(summandEditText));
                setNumber(getNumber());
            }
        });

        return decreaseButton;
    }

}
