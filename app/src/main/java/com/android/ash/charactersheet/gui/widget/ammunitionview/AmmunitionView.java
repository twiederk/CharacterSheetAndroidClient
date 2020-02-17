package com.android.ash.charactersheet.gui.widget.ammunitionview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;

import java.util.Observable;
import java.util.Observer;

import androidx.appcompat.widget.AppCompatButton;

/**
 * View to display number of ammunition. The view is a simple button which displays the number of ammunition. The
 * ammunition is decreased by one if the button is clicked.
 */
public class AmmunitionView extends AppCompatButton implements OnClickListener, Observer {

    private AmmunitionViewController controller;

    /**
     * Creates AmmunitionView from XML layout.
     *
     * @param context      The context of the view.
     * @param attributeSet The attributes from XML layout.
     */
    public AmmunitionView(final Context context, final AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnClickListener(this);
    }

    /**
     * Sets the controller of the AmmunitionView.
     *
     * @param controller The controller of the AmmunitionView.
     */
    public void setController(final AmmunitionViewController controller) {
        this.controller = controller;
        controller.addObserver(this);
        setText(controller.getAmmunition());
    }

    @Override
    public void onClick(final View view) {
        controller.increase();
    }

    @Override
    public void update(final Observable observable, final Object data) {
        setText(controller.getAmmunition());
    }

}
