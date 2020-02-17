package com.android.ash.charactersheet.gui.widget.observertextview;

import android.content.Context;
import android.util.AttributeSet;

import com.android.ash.charactersheet.gui.util.Logger;

import java.util.Observable;
import java.util.Observer;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * This TextView observes its model and updates its text if the model changes.
 */
public class ObserverTextView extends AppCompatTextView implements Observer {

    private ObserverTextViewModel model;

    /**
     * Instantiates ObserverTextView.
     * 
     * @param context
     *            The application context.
     */
    public ObserverTextView(final Context context) {
        super(context);
    }

    /**
     * Instantiates ObserverTextView from XML resource.
     * 
     * @param context
     *            The application context.
     * @param attrs
     *            The XML attributes.
     */
    public ObserverTextView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Instantiates ObserverTextView from XML resource with defStyle.
     * 
     * @param context
     *            The application context.
     * @param attrs
     *            The XML attributes.
     * @param defStyle
     *            The def style.
     */
    public ObserverTextView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * The model changed, update text.
     */
    @Override
    public void update(final Observable observable, final Object data) {
        Logger.debug("ObserverTextView.update()");
        setText(model.getText());
    }

    /**
     * Set the model to observe.
     * 
     * @param model
     *            The model to observe.
     */
    public void setModel(final ObserverTextViewModel model) {
        this.model = model;
        model.addObserver(this);
        setText(model.getText());
    }

}
