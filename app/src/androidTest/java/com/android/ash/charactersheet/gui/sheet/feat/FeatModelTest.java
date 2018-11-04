package com.android.ash.charactersheet.gui.sheet.feat;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.CharacterFeat;
import com.d20charactersheet.framework.boc.model.Feat;
import com.d20charactersheet.framework.boc.model.FeatType;

public class FeatModelTest extends TestCase {

    private FeatModel featModel;
    private Character character;
    private List<Feat> feats;
    private Feat singleFeat;
    private Feat multipleFeat;
    private Feat stackFeat;
    private CharacterFeat singleCharacterFeat;
    private CharacterFeat multipleCharacterFeat;
    private CharacterFeat stackCharacterFeat;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        feats = createFeats();

        character = new Character();
        character.setCharacterFeats(createCharacterFeats());
        featModel = new FeatModel(feats, character.getCharacterFeats());
        featModel.setShowAllFeats(false);
    }

    private List<Feat> createFeats() {
        singleFeat = createFeat("Single Feat", false, false);
        multipleFeat = createFeat("Multiple Feat", true, false);
        stackFeat = createFeat("Stack Feat", false, true);

        final List<Feat> feats = new ArrayList<Feat>();
        feats.add(singleFeat);
        feats.add(multipleFeat);
        feats.add(stackFeat);
        return feats;
    }

    private List<CharacterFeat> createCharacterFeats() {
        final List<CharacterFeat> characterFeats = new ArrayList<CharacterFeat>();
        singleCharacterFeat = new CharacterFeat(singleFeat);
        multipleCharacterFeat = new CharacterFeat(multipleFeat);
        multipleCharacterFeat.setCategory("category");
        stackCharacterFeat = new CharacterFeat(stackFeat);

        characterFeats.add(singleCharacterFeat);
        characterFeats.add(multipleCharacterFeat);
        characterFeats.add(stackCharacterFeat);
        return characterFeats;
    }

    private Feat createFeat(final String name, final boolean multiple, final boolean stack) {
        final Feat feat = new Feat();
        feat.setName(name);
        feat.setFeatType(FeatType.GENERAL);
        feat.setMultiple(multiple);
        feat.setStack(stack);
        return feat;
    }

    public void testRemoveSingleFeat() {
        featModel.removeFeat(new CharacterFeatItem(singleCharacterFeat));
        final List<FeatListItem> characterFeats = featModel.getFeatItems(FeatType.GENERAL);
        assertEquals(2, characterFeats.size());
    }

    public void testRemoveMultipleFeat() throws Exception {
        featModel.removeFeat(new CharacterFeatItem(multipleCharacterFeat));
        final List<FeatListItem> characterFeats = featModel.getFeatItems(FeatType.GENERAL);
        assertEquals(2, characterFeats.size());
    }

    public void testRemoveStackFeat() throws Exception {
        featModel.removeFeat(new CharacterFeatItem(stackCharacterFeat));
        final List<FeatListItem> characterFeats = featModel.getFeatItems(FeatType.GENERAL);
        assertEquals(2, characterFeats.size());
    }

    public void testAddSingleFeat() throws Exception {
        featModel.addFeat(new FeatItem(singleFeat));
        final List<FeatListItem> characterFeats = featModel.getFeatItems(FeatType.GENERAL);
        assertEquals(3, characterFeats.size());
    }

    public void testAddMultipleFeat() throws Exception {
        featModel.addFeat(new FeatItem(multipleFeat));
        final List<FeatListItem> characterFeats = featModel.getFeatItems(FeatType.GENERAL);
        assertEquals(4, characterFeats.size());
    }

    public void testAddStackFeat() throws Exception {
        featModel.addFeat(new FeatItem(stackFeat));
        final List<FeatListItem> characterFeats = featModel.getFeatItems(FeatType.GENERAL);
        assertEquals(4, characterFeats.size());
    }

    public void testPerformFilteringWithoutFilter() throws Exception {
        featModel.setShowAllFeats(true);
        final List<FeatListItem> characterFeats = featModel.getFeatItems(FeatType.GENERAL);
        assertEquals(3, characterFeats.size());
    }

    public void testPerformFilteringFilterFeat() throws Exception {
        featModel.setShowAllFeats(true);
        featModel.setFilter("Feat");
        final List<FeatListItem> characterFeats = featModel.getFeatItems(FeatType.GENERAL);
        assertEquals(3, characterFeats.size());
    }

    public void testPerformFilteringFilterSingle() throws Exception {
        featModel.setShowAllFeats(true);
        featModel.setFilter("Single");
        final List<FeatListItem> characterFeats = featModel.getFeatItems(FeatType.GENERAL);
        assertEquals(1, characterFeats.size());
    }

    public void testPerformFilteringFilterS() throws Exception {
        featModel.setShowAllFeats(true);
        featModel.setFilter("S");
        final List<FeatListItem> characterFeats = featModel.getFeatItems(FeatType.GENERAL);
        assertEquals(2, characterFeats.size());
    }

    public void testGetCharacterFeats() throws Exception {
        final List<CharacterFeat> characterFeats = featModel.getCharacterFeats();
        assertNotNull(characterFeats);
        assertEquals(3, characterFeats.size());
        assertTrue(characterFeats.get(0) instanceof CharacterFeat);
        assertTrue(characterFeats.get(1) instanceof CharacterFeat);
        assertTrue(characterFeats.get(2) instanceof CharacterFeat);
    }
}
