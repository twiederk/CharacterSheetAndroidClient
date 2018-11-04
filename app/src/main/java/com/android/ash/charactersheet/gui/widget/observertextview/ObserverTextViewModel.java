package com.android.ash.charactersheet.gui.widget.observertextview;

import java.util.Observer;

/**
 * Models need to implement this interface to be observable by the ObserverTextView.
 */
public interface ObserverTextViewModel extends Observer {

    /**
     * Add Observer of the model.
     * 
     * @param observer
     *            The observer to add.
     */
    public void addObserver(Observer observer);

    /**
     * Returns the text of the model.
     * 
     * @return The text of the model.
     */
    public String getText();

}
