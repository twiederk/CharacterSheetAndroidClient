package com.android.ash.charactersheet.gui.sheet.feat;

import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.CharacterFeat;
import com.d20charactersheet.framework.boc.model.Feat;
import com.d20charactersheet.framework.boc.model.FeatType;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FeatModelTest {

    private FeatModel featModel;
    private Feat singleFeat;
    private Feat multipleFeat;
    private Feat stackFeat;
    private CharacterFeat singleCharacterFeat;
    private CharacterFeat multipleCharacterFeat;
    private CharacterFeat stackCharacterFeat;

    @Before
    public void setUp() {
        List<Feat> feats = createFeats();

        Character character = new Character();
        character.setCharacterFeats(createCharacterFeats());
        featModel = new FeatModel(feats, character.getCharacterFeats());
        featModel.setShowAllFeats(false);
    }

    private List<Feat> createFeats() {
        singleFeat = createFeat("Single Feat", false, false);
        multipleFeat = createFeat("Multiple Feat", true, false);
        stackFeat = createFeat("Stack Feat", false, true);

        final List<Feat> feats = new ArrayList<>();
        feats.add(singleFeat);
        feats.add(multipleFeat);
        feats.add(stackFeat);
        return feats;
    }

    private List<CharacterFeat> createCharacterFeats() {
        final List<CharacterFeat> characterFeats = new ArrayList<>();
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

    @Test
    public void testRemoveSingleFeat() {
        featModel.removeFeat(new CharacterFeatItem(singleCharacterFeat));
        final List<FeatListItem> characterFeats = featModel.getFeatItems(FeatType.GENERAL);
        assertEquals(2, characterFeats.size());
    }

    @Test
    public void testRemoveMultipleFeat() {
        featModel.removeFeat(new CharacterFeatItem(multipleCharacterFeat));
        final List<FeatListItem> characterFeats = featModel.getFeatItems(FeatType.GENERAL);
        assertEquals(2, characterFeats.size());
    }

    @Test
    public void testRemoveStackFeat() {
        featModel.removeFeat(new CharacterFeatItem(stackCharacterFeat));
        final List<FeatListItem> characterFeats = featModel.getFeatItems(FeatType.GENERAL);
        assertEquals(2, characterFeats.size());
    }

    @Test
    public void testAddSingleFeat() {
        featModel.addFeat(new FeatItem(singleFeat));
        final List<FeatListItem> characterFeats = featModel.getFeatItems(FeatType.GENERAL);
        assertEquals(3, characterFeats.size());
    }

    @Test
    public void testAddMultipleFeat() {
        featModel.addFeat(new FeatItem(multipleFeat));
        final List<FeatListItem> characterFeats = featModel.getFeatItems(FeatType.GENERAL);
        assertEquals(4, characterFeats.size());
    }

    @Test
    public void testAddStackFeat() {
        featModel.addFeat(new FeatItem(stackFeat));
        final List<FeatListItem> characterFeats = featModel.getFeatItems(FeatType.GENERAL);
        assertEquals(4, characterFeats.size());
    }

    @Test
    public void testPerformFilteringWithoutFilter() {
        featModel.setShowAllFeats(true);
        final List<FeatListItem> characterFeats = featModel.getFeatItems(FeatType.GENERAL);
        assertEquals(3, characterFeats.size());
    }

    @Test
    public void testPerformFilteringFilterFeat() {
        featModel.setShowAllFeats(true);
        featModel.setFilter("Feat");
        final List<FeatListItem> characterFeats = featModel.getFeatItems(FeatType.GENERAL);
        assertEquals(3, characterFeats.size());
    }

    @Test
    public void testPerformFilteringFilterSingle() {
        featModel.setShowAllFeats(true);
        featModel.setFilter("Single");
        final List<FeatListItem> characterFeats = featModel.getFeatItems(FeatType.GENERAL);
        assertEquals(1, characterFeats.size());
    }

    @Test
    public void testPerformFilteringFilterS() {
        featModel.setShowAllFeats(true);
        featModel.setFilter("S");
        final List<FeatListItem> characterFeats = featModel.getFeatItems(FeatType.GENERAL);
        assertEquals(2, characterFeats.size());
    }

    @Test
    public void testGetCharacterFeats() {
        final List<CharacterFeat> characterFeats = featModel.getCharacterFeats();
        assertNotNull(characterFeats);
        assertEquals(3, characterFeats.size());
        assertNotNull(characterFeats.get(0));
        assertNotNull(characterFeats.get(1));
        assertNotNull(characterFeats.get(2));
    }
}
