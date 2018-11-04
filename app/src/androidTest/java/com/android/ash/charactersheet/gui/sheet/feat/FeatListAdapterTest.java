package com.android.ash.charactersheet.gui.sheet.feat;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.test.AndroidTestCase;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.CharacterFeat;
import com.d20charactersheet.framework.boc.model.Feat;
import com.d20charactersheet.framework.boc.model.FeatType;
import com.d20charactersheet.framework.dac.dao.FeatDao;
import com.d20charactersheet.framework.dac.dao.dummy.DummyFeatDao;

public class FeatListAdapterTest extends AndroidTestCase {

    private FeatModel featModel;
    private Character character;
    private List<Feat> feats;
    private Context context;
    private FeatListAdapter generalFeatListAdapter;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        final FeatDao featDao = new DummyFeatDao(FEAT);
        feats = featDao.getAllFeats();
        character = createCharacter();
        featModel = new FeatModel(feats, character.getCharacterFeats());
        context = getContext();
        generalFeatListAdapter = new FeatListAdapter(context, R.layout.listitem_feat, featModel, FeatType.GENERAL);

    }

    private Character createCharacter() {
        final Character character = new Character();
        character.setCharacterFeats(createCharacterFeats());
        return character;
    }

    private List<CharacterFeat> createCharacterFeats() {
        final List<CharacterFeat> characterFeats = new ArrayList<CharacterFeat>();
        characterFeats.add(new CharacterFeat(getFeat("Augment Summoning")));
        return characterFeats;
    }

    private Feat getFeat(final String name) {
        for (final Feat feat : feats) {
            if (feat.getName().equals(name)) {
                return feat;
            }
        }
        throw new IllegalArgumentException("Can't find feat with name: " + name);
    }

    public void testGetAllGeneralFeats() throws Exception {
        featModel.setShowAllFeats(true);
        final int numberOfFeats = generalFeatListAdapter.getCount();
        assertEquals(92, numberOfFeats);
    }

    public void testGetCharacterGeneralFeats() throws Exception {
        featModel.setShowAllFeats(false);
        final int numberOfFeats = generalFeatListAdapter.getCount();
        assertEquals(1, numberOfFeats);
    }

    public void testGetFighterBonusAndKind() {
        final Feat feat = new Feat();
        final FeatListItem featItem = new FeatItem(feat);

        // no fighter bonus, no multiple, no stack
        feat.setFighterBonus(false);
        feat.setMultiple(false);
        feat.setStack(false);
        String fighterAndKind = generalFeatListAdapter.getFighterBonusAndKind(featItem);
        assertEquals("", fighterAndKind);

        // fighter bonus, no multiple, no stack
        feat.setFighterBonus(true);
        feat.setMultiple(false);
        feat.setStack(false);
        fighterAndKind = generalFeatListAdapter.getFighterBonusAndKind(featItem);
        assertEquals("[Fighter Bonus Feat]", fighterAndKind);

        // fighter bonus, multiple, no stack
        feat.setFighterBonus(true);
        feat.setMultiple(true);
        feat.setStack(false);
        fighterAndKind = generalFeatListAdapter.getFighterBonusAndKind(featItem);
        assertEquals("[Fighter Bonus Feat, Multiple times, do not stack]", fighterAndKind);

        // fighter bonus, no multiple, stack
        feat.setFighterBonus(true);
        feat.setMultiple(false);
        feat.setStack(true);
        fighterAndKind = generalFeatListAdapter.getFighterBonusAndKind(featItem);
        assertEquals("[Fighter Bonus Feat, Multiple times, do stack]", fighterAndKind);

        // no fighter bonus, no multiple, stack
        feat.setFighterBonus(false);
        feat.setMultiple(false);
        feat.setStack(true);
        fighterAndKind = generalFeatListAdapter.getFighterBonusAndKind(featItem);
        assertEquals("[Multiple times, do stack]", fighterAndKind);

    }
}
