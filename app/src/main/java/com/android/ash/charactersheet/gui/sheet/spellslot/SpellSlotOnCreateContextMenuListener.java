package com.android.ash.charactersheet.gui.sheet.spellslot;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;
import android.app.Activity;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.sheet.knownspell.SpellActivity;
import com.d20charactersheet.framework.boc.model.Spell;
import com.d20charactersheet.framework.boc.model.SpellSlot;

/**
 * Creates context menu, which allows to call detailed spell information.
 */
public class SpellSlotOnCreateContextMenuListener implements OnCreateContextMenuListener {

    private static final int CONTEXT_MENU_SHOW_DESCRIPTION = 10;

    private final Activity activity;
    private Spell selectedSpell;

    /**
     * Instanciates SpellSlotOnCreateContextMenuListener
     * 
     * @param activity
     *            The activity the context menu belongs to.
     */
    public SpellSlotOnCreateContextMenuListener(final Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreateContextMenu(final ContextMenu menu, final View view, final ContextMenuInfo menuInfo) {
        final ListView listView = (ListView) view;
        final AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
        final Object objectView = listView.getAdapter().getItem(info.position);

        if (objectView instanceof SpellSlotView) {
            final SpellSlotView spellSlotView = (SpellSlotView) objectView;
            final Spell spell = spellSlotView.getSpellSlot().getSpell();
            if (!SpellSlot.EMPTY_SPELL.equals(spell)) {
                selectedSpell = spell;
                menu.setHeaderTitle(spell.getName());
                menu.add(Menu.NONE, CONTEXT_MENU_SHOW_DESCRIPTION, Menu.CATEGORY_SECONDARY,
                        R.string.page_spell_slot_show_description);
            }
        }
    }

    /**
     * Handle selection on context item. Only item is display detailed information of spell.
     * 
     * @param menuItem
     *            The selected menu item.
     * @return Always true, because the event is consumed.
     */
    public boolean onContextItemSelected(final MenuItem menuItem) {
        switch (menuItem.getItemId()) {
        case CONTEXT_MENU_SHOW_DESCRIPTION:
            final Intent intent = new Intent(activity, SpellActivity.class);
            intent.putExtra(INTENT_EXTRA_DATA_OBJECT, selectedSpell.getId());
            activity.startActivity(intent);
            return true;

        default:
            return true;
        }
    }

}
