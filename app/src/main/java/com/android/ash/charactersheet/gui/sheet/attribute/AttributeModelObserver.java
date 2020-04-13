package com.android.ash.charactersheet.gui.sheet.attribute;

import com.android.ash.charactersheet.gui.widget.observertextview.ObserverTextViewModel;
import com.d20charactersheet.framework.boc.service.DisplayService;
import com.d20charactersheet.framework.boc.service.RuleService;

import java.util.Observable;

/**
 * Observes an AttributeModel and informs TextView if the attribute value changed.
 */
public class AttributeModelObserver extends Observable implements ObserverTextViewModel {

    private final AttributeModel attributeModel;
    private final RuleService ruleService;
    private final DisplayService displayService;

    /**
     * Creates observer which observes the given AttributeModel.
     * 
     * @param attributeModel
     *            The AttributeModel to observe.
     * @param ruleService
     *            The RuleService to determine the modifier of the attribute value.
     * @param displayService
     *            Use to display the modifier of the attribute value.
     */
    AttributeModelObserver(final AttributeModel attributeModel, final RuleService ruleService, final DisplayService displayService) {
        super();
        this.attributeModel = attributeModel;
        this.ruleService = ruleService;
        this.displayService = displayService;
        attributeModel.addObserver(this);
    }

    /**
     * Notifies observer if the attribute model changed.
     * 
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(final Observable observable, final Object data) {
        setChanged();
        notifyObservers(data);
    }

    /**
     * Returns the modifier of the observer attribute value.
     * 
     * @return The modifier of the observer attribute value.
     * @see com.android.ash.charactersheet.gui.widget.observertextview.ObserverTextViewModel#getText()
     */
    @Override
    public String getText() {
        final int modifier = ruleService.getModifier(attributeModel.getAttributeValue());
        return displayService.getDisplayModifier(modifier);
    }
}
