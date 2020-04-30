package com.android.ash.charactersheet.gui.sheet.feat;

import android.content.Context;
import android.os.Looper;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.CharacterFeat;
import com.d20charactersheet.framework.boc.model.Feat;
import com.d20charactersheet.framework.boc.model.FeatType;
import com.d20charactersheet.framework.dac.dao.FeatDao;
import com.d20charactersheet.framework.dac.dao.dummy.DummyFeatDao;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import static com.d20charactersheet.framework.dac.dao.dummy.storage.DnDv35FeatStorage.FEAT;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class FeatListEspressoTest {

    private FeatModel featModel;
    private List<Feat> feats;
    private FeatListAdapter generalFeatListAdapter;

    @BeforeClass
    static public void beforeClass() {
        Looper.prepare();
    }

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        final FeatDao featDao = new DummyFeatDao(FEAT);
        feats = featDao.getAllFeats();
        Character character = createCharacter();
        featModel = new FeatModel(feats, character.getCharacterFeats());
        generalFeatListAdapter = new FeatListAdapter(context, R.layout.listitem_feat, featModel, FeatType.GENERAL);

    }

    private Character createCharacter() {
        final Character character = new Character();
        character.setCharacterFeats(createCharacterFeats());
        return character;
    }

    private List<CharacterFeat> createCharacterFeats() {
        final List<CharacterFeat> characterFeats = new ArrayList<>();
        characterFeats.add(new CharacterFeat(getFeat()));
        return characterFeats;
    }

    private Feat getFeat() {
        for (final Feat feat : feats) {
            if (feat.getName().equals("Augment Summoning")) {
                return feat;
            }
        }
        throw new IllegalArgumentException("Can't find feat with name: " + "Augment Summoning");
    }

    @Test
    public void testGetAllGeneralFeats() {
        featModel.setShowAllFeats(true);
        final int numberOfFeats = generalFeatListAdapter.getCount();
        assertEquals(92, numberOfFeats);
    }

    @Test
    public void testGetCharacterGeneralFeats() {
        featModel.setShowAllFeats(false);
        final int numberOfFeats = generalFeatListAdapter.getCount();
        assertEquals(1, numberOfFeats);
    }

    @Test
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
