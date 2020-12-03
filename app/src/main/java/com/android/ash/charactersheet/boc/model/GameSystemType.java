package com.android.ash.charactersheet.boc.model;

import com.android.ash.charactersheet.R;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

/**
 * Enumeration of supported GameSystems.
 */
public enum GameSystemType {

    /** Dungeons & Dragons v.3.5 */
    DNDV35(DnDv35.ID, DnDv35.NAME, DnDv35.DATABASE_NAME, DnDv35.CREATE_SCRIPTS, DnDv35.UPDATE_SCRIPTS, DnDv35.IMAGES),

    /** Pathfinder */
    PATHFINDER(Pathfinder.ID, Pathfinder.NAME, Pathfinder.DATABASE_NAME, Pathfinder.CREATE_SCRIPTS,
            Pathfinder.UPDATE_SCRIPTS, Pathfinder.IMAGES);

    private final int id;
    private final String name;
    private final String databaseName;
    private final int[] createScripts;
    private final Map<Integer, Integer> updateScripts;
    private final int[] images;

    GameSystemType(final int id, final String name, final String databaseName, final int[] createScripts,
                   final Map<Integer, Integer> updateScripts, final int[] images) {
        this.id = id;
        this.name = name;
        this.databaseName = databaseName;
        this.createScripts = createScripts;
        this.updateScripts = updateScripts;
        this.images = images;
    }

    /**
     * Returns the id of the game system.
     * 
     * @return The id of the game system.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the game system.
     * 
     * @return The name of the game system.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the name of the database used by the game system.
     * 
     * @return The name of the database used by the game system.
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * Returns the resource ids of scripts to create and fill the database of the game system. The scripts will be
     * executed in the given order.
     * 
     * @return The resource ids of scripts to create and fill the database of the game system.
     */
    public int[] getCreateScripts() {
        return createScripts;
    }

    /**
     * Returns the resource ids of scripts to update the database of the game system.
     *
     * @return The resource ids of scripts to update the database of the game system.
     */
    public Map<Integer, Integer> getUpdateScripts() {
        return updateScripts;
    }

    /**
     * Returns the resource ids of the images used by the game system.
     * 
     * @return The resource ids of the images used by the game system.
     */
    public int[] getImages() {
        return images;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    private static class DnDv35 {

        static final int ID = 1;

        static final String NAME = "Dungeons & Dragons v.3.5";

        static final String DATABASE_NAME = "dndv35_db";

        static final int[] CREATE_SCRIPTS = { //
                // scripts
                R.raw.create_database, //
                R.raw.dndv35_phb_data, //
                R.raw.dndv35_phb_spell, //
                R.raw.dndv35_phb_character, //
        };

        static final Map<Integer, Integer> UPDATE_SCRIPTS = new HashMap<>();

        static final int[] IMAGES = { //
                // image resource id, face resource id
                R.drawable.char_default, R.drawable.char_default_face, // Default
                R.drawable.char_belvador, R.drawable.char_belvador_face, // Belvador
        };

        static {
            UPDATE_SCRIPTS.put(59, R.raw.dndv35_upgrade_59_to_60);
        }

    }

    private static class Pathfinder {

        static final int ID = 2;

        static final String NAME = "Pathfinder";

        static final String DATABASE_NAME = "pathfinder_db";

        static final int[] CREATE_SCRIPTS = { //
                // create scripts
                R.raw.create_database, //
                R.raw.pathfinder_crb_data, //
                R.raw.pathfinder_crb_spell, //
                R.raw.pathfinder_crb_character, //
                R.raw.pathfinder_apg_data, //
                R.raw.pathfinder_apg_spell, //
                R.raw.pathfinder_arg_data, //
        };

        static final Map<Integer, Integer> UPDATE_SCRIPTS = new HashMap<>();

        static final int[] IMAGES = { //
                // image resource id, face resource id
                R.drawable.char_default, R.drawable.char_default_face, // Default
                R.drawable.char_belvador, R.drawable.char_belvador_face // Belvador
        };

        static {
            UPDATE_SCRIPTS.put(59, R.raw.pathfinder_upgrade_59_to_60);
            UPDATE_SCRIPTS.put(63, R.raw.pathfinder_upgrade_63_to_64);
        }


    }
}
