package com.android.ash.charactersheet.gui.sheet.spellslot;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;

import com.android.ash.charactersheet.gui.sheet.SpellSlotPageFragment;
import com.d20charactersheet.framework.boc.model.SpellSlot;
import com.d20charactersheet.framework.boc.service.CharacterService;

/**
 * Listens for clicks on spell slot list. Click on spell level expands or collapse spell level. Click on uncast spell
 * slot, sets the spell slot to cast. Click on cast spell slot opens SpellSlotActivity.
 */
public class SpellSlotOnItemClickListener implements OnItemClickListener {

    private final Fragment fragment;
    private final CharacterService characterService;

    /**
     * Instanciate SpellSlotOnItemClickListener.
     * 
     * @param fragment
     *            The fragment.
     * @param characterService
     *            The character service.
     */
    public SpellSlotOnItemClickListener(final Fragment fragment, final CharacterService characterService) {
        this.fragment = fragment;
        this.characterService = characterService;
    }

    @Override
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int position, final long id) {
        final Object item = adapterView.getItemAtPosition(position);
        final BaseAdapter adapter = (BaseAdapter) adapterView.getAdapter();

        if (item instanceof LevelView) {
            onClickLevelView(adapter, (LevelView) item);
        } else if (item instanceof SpellSlotView) {
            onClickSpellSlotView(adapter, (SpellSlotView) item);
        }
    }

    private void onClickLevelView(final BaseAdapter adapter, final LevelView levelView) {
        levelView.setExpanded(!levelView.isExpanded());
        adapter.notifyDataSetChanged();
    }

    private void onClickSpellSlotView(final BaseAdapter adapter, final SpellSlotView spellSlotView) {
        final SpellSlot spellSlot = spellSlotView.getSpellSlot();
        if (spellSlot.isCast() || SpellSlot.EMPTY_SPELL.equals(spellSlot.getSpell())) {
            startSpellSlotActivity(spellSlot.getId());
        } else {
            spellSlot.setCast(true);
            characterService.updateSpellSlot(spellSlot);
            adapter.notifyDataSetChanged();
        }
    }

    private void startSpellSlotActivity(final int spellSlotId) {
        final Intent intent = new Intent(fragment.getActivity(), SpellSlotActivity.class);
        intent.putExtra(INTENT_EXTRA_DATA_OBJECT, spellSlotId);
        fragment.startActivityForResult(intent, SpellSlotPageFragment.REQUEST_CODE);

    }
}
