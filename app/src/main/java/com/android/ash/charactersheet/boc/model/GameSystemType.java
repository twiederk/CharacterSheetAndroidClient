package com.android.ash.charactersheet.boc.model;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.dac.dao.sql.sqlite.ClasspathScriptResource;
import com.android.ash.charactersheet.dac.dao.sql.sqlite.RawScriptResource;
import com.android.ash.charactersheet.dac.dao.sql.sqlite.ScriptResource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Enumeration of supported GameSystems.
 */
public enum GameSystemType {

    DNDV35(1, //
            "DnD v.3.5", //
            "dndv35_db",
            Arrays.asList(
                    new RawScriptResource(R.raw.create_database), //
                    new RawScriptResource(R.raw.dndv35_phb_data), //
                    new RawScriptResource(R.raw.dndv35_phb_spell), //
                    new RawScriptResource(R.raw.dndv35_phb_character) //
            ), //
            createUpdateScriptMap(new int[]{59},
                    new ScriptResource[]{
                            new RawScriptResource(R.raw.dndv35_upgrade_59_to_60)
                    })
    ),

    PATHFINDER(2, //
            "Pathfinder", //
            "pathfinder_db", //
            Arrays.asList( //
                    new RawScriptResource(R.raw.create_database), //
                    new RawScriptResource(R.raw.pathfinder_crb_data), //
                    new RawScriptResource(R.raw.pathfinder_crb_spell), //
                    new RawScriptResource(R.raw.pathfinder_crb_character), //
                    new RawScriptResource(R.raw.pathfinder_apg_data), //
                    new RawScriptResource(R.raw.pathfinder_apg_spell), //
                    new RawScriptResource(R.raw.pathfinder_arg_data) //
            ), //
            createUpdateScriptMap(
                    new int[]{59, 63},
                    new ScriptResource[]{
                            new RawScriptResource(R.raw.pathfinder_upgrade_59_to_60), //
                            new RawScriptResource(R.raw.pathfinder_upgrade_63_to_64) //
                    })
    ),


    DND5E(3, //
            "DnD 5e", //
            "dnd5e_db", //
            Arrays.asList( //
                    new ClasspathScriptResource("/sql/create_database.sql"), //
                    new ClasspathScriptResource("/sql/dnd5e_phb_data.sql"), //
                    new RawScriptResource(R.raw.dnd5e_phb_character) //
            ), //
            createUpdateScriptMap(
                    new int[]{69, 70},
                    new ScriptResource[]{
                            new RawScriptResource(R.raw.dnd5e_upgrade_69_to_70), //
                            new RawScriptResource(R.raw.dnd5e_upgrade_70_to_71), //
                    })
    );

    private static Map<Integer, ScriptResource> createUpdateScriptMap(final int[] key, final ScriptResource[] value) {
        Map<Integer, ScriptResource> updateScriptMap = new HashMap<>();
        for (int i = 0; i < key.length; i++) {
            updateScriptMap.put(key[i], value[i]);
        }
        return updateScriptMap;
    }

    private final int id;
    private final String name;
    private final String databaseName;
    private final List<ScriptResource> createScriptResources;
    private final Map<Integer, ScriptResource> updateScriptResources;
    private final int[] images;

    GameSystemType(final int id, final String name, final String databaseName,
                   List<ScriptResource> createScriptResources, Map<Integer, ScriptResource> updateScriptResources) {
        this.id = id;
        this.name = name;
        this.databaseName = databaseName;
        this.createScriptResources = createScriptResources;
        this.updateScriptResources = updateScriptResources;
        this.images = new int[]{ //
                // image resource id, face resource id
                R.drawable.char_default, R.drawable.char_default_face, // Default
                R.drawable.char_belvador, R.drawable.char_belvador_face // Belvador
        };
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
     * Returns the resource ids of the images used by the game system.
     *
     * @return The resource ids of the images used by the game system.
     */
    public int[] getImages() {
        return images;
    }

    /**
     * Returns list of scripts to create database and fill with data.
     *
     * @return list of scripts to create database and fill with data.
     */
    public List<ScriptResource> getCreateScriptResources() {
        return createScriptResources;
    }

    /**
     * Returns version and script to update to this version
     *
     * @return Map containing versions with according update script
     */
    public Map<Integer, ? extends ScriptResource> getUpdateScriptResources() {
        return updateScriptResources;
    }

}
