package com.android.ash.charactersheet.gui.sheet.clazz;

import android.view.View;
import android.view.View.OnClickListener;

import com.android.ash.charactersheet.gui.util.MessageManager;
import com.d20charactersheet.framework.boc.model.ClassLevel;
import com.d20charactersheet.framework.boc.model.RuleError;
import com.d20charactersheet.framework.boc.model.RuleException;

/**
 * Deletes a class level of the character.
 */
class DeleteLevelOnClickListener implements OnClickListener {

    private final ClassLevelArrayAdapter adapter;
    private final ClassLevel classLevel;

    private final MessageManager messageManager;

    /**
     * Instanciates the listener.
     * 
     * @param adapter
     *            The adapter containing all levels of the character.
     * @param classLevel
     *            The level to delete.
     * @param messageManager
     *            Used to display a message, if this is the last level of the character.
     */
    public DeleteLevelOnClickListener(final ClassLevelArrayAdapter adapter, final ClassLevel classLevel,
            final MessageManager messageManager) {
        this.adapter = adapter;
        this.classLevel = classLevel;
        this.messageManager = messageManager;
    }

    /**
     * Deletes the level or displays an error message if the last level of the character is to be deleted.
     * 
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(final View view) {
        if (adapter.getCount() > 1) {
            adapter.remove(classLevel);
        } else {
            messageManager.showErrorMessage(new RuleException(RuleError.NONE_CLASS_LEVEL));
        }
    }
}
