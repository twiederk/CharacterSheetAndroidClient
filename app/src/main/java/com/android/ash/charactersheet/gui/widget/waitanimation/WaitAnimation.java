package com.android.ash.charactersheet.gui.widget.waitanimation;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Displays a text with an endless animationen. This animation is used as a wait animation.
 */
public class WaitAnimation extends LinearLayout {

    private TextView textView;

    /**
     * Create WaitAnimation based on a XML layout file.
     * 
     * @param context
     *            The context of the WaitAnimation.
     * @param attributeSet
     *            The attributes of the XML layout file.
     */
    public WaitAnimation(final Context context, final AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    private void initView(final Context context) {
        setOrientation(HORIZONTAL);
        addView(createProgressBar(context));
        addView(createTextView(context));
    }

    private View createTextView(final Context context) {
        textView = new TextView(context);
        textView.setTextColor(0xFFFFFFFF);
        final LayoutParams layoutParams = createLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(layoutParams);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        return textView;
    }

    private View createProgressBar(final Context context) {
        final ProgressBar progressBar = new ProgressBar(context);
        final LayoutParams layoutParams = createLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        progressBar.setIndeterminate(true);
        progressBar.setLayoutParams(layoutParams);
        return progressBar;
    }

    private LayoutParams createLayoutParams(final int layoutWidth, final int layoutHeight) {
        final LayoutParams layoutParams = new LinearLayout.LayoutParams(layoutWidth, layoutHeight);
        layoutParams.setMargins(4, 0, 4, 0);
        return layoutParams;
    }

    /**
     * Set the text to display.
     * 
     * @param text
     *            The text to display.
     */
    public void setText(final CharSequence text) {
        textView.setText(text);
    }

    /**
     * Set the text to display, based on the given resource id.
     * 
     * @param resourceId
     *            The resource id of the text to display.
     */
    public void setText(final int resourceId) {
        textView.setText(resourceId);
    }

    /**
     * Show the wait animation.
     */
    public void show() {
        setVisibility(View.VISIBLE);
    }

    /**
     * Hide the wait animation.
     */
    public void hide() {
        setVisibility(View.INVISIBLE);
    }
}
