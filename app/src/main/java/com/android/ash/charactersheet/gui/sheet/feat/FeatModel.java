package com.android.ash.charactersheet.gui.sheet.feat;

import androidx.annotation.NonNull;

import com.d20charactersheet.framework.boc.model.CharacterFeat;
import com.d20charactersheet.framework.boc.model.Feat;
import com.d20charactersheet.framework.boc.model.FeatType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Observable;

/**
 * Model of feat list. Stores current filter settings and returns feats according to filter settings. Notifies observers
 * of data change.
 */
public class FeatModel extends Observable {

    private final List<FeatItem> featItems;
    private final List<CharacterFeatItem> characterFeatItems;
    private boolean showAllFeats = false;
    private String filter;
    private CharacterFeatItem characterFeatItem;

    /**
     * Create FeatModel with given list of all feats and feats of character. No filter is set and show all feats is
     * false.
     * 
     * @param allFeats
     *            List of all feats.
     * @param characterFeats
     *            List of feat of character.
     */
    public FeatModel(final List<Feat> allFeats, final List<CharacterFeat> characterFeats) {
        super();
        featItems = createFeatItems(allFeats);
        characterFeatItems = createCharacterFeatItems(characterFeats);
    }

    private List<FeatItem> createFeatItems(final List<Feat> allFeats) {
        final List<FeatItem> featItems = new ArrayList<>();
        for (final Feat feat : allFeats) {
            featItems.add(new FeatItem(feat));
        }
        return featItems;
    }

    private List<CharacterFeatItem> createCharacterFeatItems(final List<CharacterFeat> characterFeats) {
        final List<CharacterFeatItem> characterFeatItems = new ArrayList<>();
        for (final CharacterFeat characterFeat : characterFeats) {
            characterFeatItems.add(new CharacterFeatItem(characterFeat));
        }
        return characterFeatItems;
    }

    /**
     * Returns true, if all feats are shown.
     * 
     * @return True, if all feats are shown.
     */
    public boolean isShowAllFeats() {
        return showAllFeats;
    }

    /**
     * Set true to show all feats.
     * 
     * @param showAllFeats
     *            Set true to show all feats.
     */
    public void setShowAllFeats(final boolean showAllFeats) {
        this.showAllFeats = showAllFeats;
        fireEvent();
    }

    /**
     * Returns feats of the requested feat type. Depending on internal state of show all. If show all is set all static
     * feats are returned. If show all is not set only character feats are returned.
     * 
     * @param featType
     *            The type of the feats to return.
     * @return Feats of the requested feat type.
     */
    public List<FeatListItem> getFeatItems(final FeatType featType) {
        if (showAllFeats) {
            return getFeatItems(featItems, featType);
        }
        return getFeatItems(characterFeatItems, featType);
    }

    private List<FeatListItem> getFeatItems(final List<? extends FeatListItem> featItems, final FeatType featType) {
        final List<FeatListItem> featsOfType = new ArrayList<>();
        for (final FeatListItem featItem : featItems) {
            if (featItem.getFeatType().equals(featType)) {
                featsOfType.add(featItem);
            }
        }
        final List<FeatListItem> filteredFeats = performFiltering(featsOfType);
        filteredFeats.sort(new FeatItemComparator());
        return filteredFeats;
    }

    private List<FeatListItem> performFiltering(final List<FeatListItem> featsOfType) {
        if (filter == null || filter.length() == 0) {
            return new ArrayList<>(featsOfType);
        }
        return filterFeatItems(featsOfType);
    }

    private List<FeatListItem> filterFeatItems(final List<FeatListItem> featsOfType) {
        final List<FeatListItem> filteredFeatItems = new ArrayList<>();
        for (final FeatListItem featItem : featsOfType) {
            final String name = featItem.getName().toLowerCase(Locale.getDefault());

            // First match against the whole, non-splitted value
            if (name.startsWith(filter)) {
                filteredFeatItems.add(featItem);
            } else {
                matchSplittedValue(featItem, name, filteredFeatItems);
            }
        }
        return filteredFeatItems;
    }

    private void matchSplittedValue(final FeatListItem featItem, final String name,
            final List<FeatListItem> filteredFeatItems) {
        final String[] words = name.split(" ");

        for (String word : words) {
            if (word.startsWith(filter)) {
                filteredFeatItems.add(featItem);
                break;
            }
        }
    }

    /**
     * @see java.lang.Object#toString()
     */
    @NonNull
    @Override
    public String toString() {
        return "FeatModel: showAllFeats = " + showAllFeats;
    }

    private void fireEvent() {
        setChanged();
        notifyObservers();
    }

    /**
     * Add the feat to the list of the character feats.
     * 
     * @param featItem
     *            Feat item containing the feat to add.
     */
    public void addFeat(final FeatItem featItem) {
        final CharacterFeat characterFeat = new CharacterFeat(featItem.getFeat());
        final CharacterFeatItem characterFeatItem = new CharacterFeatItem(characterFeat);
        if (featItem.isMultiple() || featItem.isStack()) {
            characterFeatItems.add(characterFeatItem);
        } else {
            addSingleFeat(characterFeatItem);
        }
        fireEvent();
    }

    private void addSingleFeat(final CharacterFeatItem featItem) {
        for (final FeatListItem characterFeatItem : characterFeatItems) {
            if (characterFeatItem.getFeat().getId() == featItem.getId()) {
                return;
            }
        }
        characterFeatItems.add(featItem);
    }

    /**
     * Removes character feat item from feats of character.
     * 
     * @param characterFeatItem
     *            The character feat item to remove.
     */
    public void removeFeat(final CharacterFeatItem characterFeatItem) {
        if (characterFeatItem.isMultiple()) {
            removeMultipleFeat(characterFeatItem);
        } else {
            removeSingleAndStackFeat(characterFeatItem);
        }
        fireEvent();
    }

    private void removeMultipleFeat(final CharacterFeatItem featItem) {
        for (final Iterator<? extends FeatListItem> iterator = characterFeatItems.iterator(); iterator.hasNext();) {
            final CharacterFeatItem characterFeatItem = (CharacterFeatItem) iterator.next();
            if (featItem.getId() == characterFeatItem.getId()) {
                if (featItem.getCategory().equals(characterFeatItem.getCategory())) {
                    iterator.remove();
                    fireEvent();
                    return;
                }
            }
        }
        throw new IllegalStateException("Can't remove feat: " + featItem);
    }

    private void removeSingleAndStackFeat(final FeatListItem featItem) {
        for (final Iterator<? extends FeatListItem> iterator = characterFeatItems.iterator(); iterator.hasNext();) {
            final CharacterFeatItem characterFeatItem = (CharacterFeatItem) iterator.next();
            if (featItem.getId() == characterFeatItem.getId()) {
                iterator.remove();
                fireEvent();
                return;
            }
        }
        throw new IllegalStateException("Can't remove feat: " + featItem);
    }

    /**
     * Sets a filter on the feats. Filters are turned to lower case.
     * 
     * @param filter
     *            The filter to set.
     */
    public void setFilter(final String filter) {
        this.filter = filter.toLowerCase(Locale.getDefault());
        fireEvent();
    }

    /**
     * Sets the current character feat, thus it can be edited.
     * 
     * @param characterFeatItem
     *            The character feat to set for edit.
     */
    public void setCharacterFeatItem(final CharacterFeatItem characterFeatItem) {
        this.characterFeatItem = characterFeatItem;
    }

    /**
     * Sets the given category on the currently set character feat.
     * 
     * @param category
     *            The category to set.
     */
    public void setCategory(final String category) {
        characterFeatItem.setCategory(category);
        fireEvent();
    }

    /**
     * Returns the list of character feats.
     * 
     * @return The list of character feats.
     */
    public List<CharacterFeat> getCharacterFeats() {
        final List<CharacterFeat> characterFeats = new ArrayList<>();
        for (final CharacterFeatItem featListItem : characterFeatItems) {
            characterFeats.add(featListItem.getCharacterFeat());
        }
        return characterFeats;
    }

    /**
     * Returns the current filter.
     * 
     * @return The current filter.
     */
    public String getFilter() {
        return filter;
    }

}
