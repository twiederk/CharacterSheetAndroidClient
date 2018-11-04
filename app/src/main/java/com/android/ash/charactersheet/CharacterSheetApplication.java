package com.android.ash.charactersheet;

import android.app.Application;

import com.android.ash.charactersheet.boc.service.PreferenceService;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.service.GameSystem;

/**
 * The application of the Character Sheet application.
 */
public class CharacterSheetApplication extends Application {

    private GameSystem gameSystem;
    private PreferenceService preferenceService;
    private Character character;

    /**
     * Returns the game system.
     * 
     * @return The game system.
     */
    public GameSystem getGameSystem() {
        return gameSystem;
    }

    /**
     * Sets the game system.
     * 
     * @param gameSystem
     *            The game system to set.
     */
    public void setGameSystem(final GameSystem gameSystem) {
        this.gameSystem = gameSystem;
    }

    /**
     * Returns the current used character.
     * 
     * @return The current character.
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * Sets the current character.
     * 
     * @param character
     *            The current character.
     */
    public void setCharacter(final Character character) {
        this.character = character;
    }

    /**
     * Returns the preference service.
     * 
     * @return The preference service.
     */
    public PreferenceService getPreferenceService() {
        return preferenceService;
    }

    /**
     * Sets the preference service.
     * 
     * @param preferenceService
     *            The preference service.
     */
    public void setPreferenceService(final PreferenceService preferenceService) {
        this.preferenceService = preferenceService;
    }

    /**
     * Sets all instance variables to null to release the resources.
     */
    public void close() {
        gameSystem = null;
        preferenceService = null;
        character = null;
    }

}
